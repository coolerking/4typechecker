package jp.freeex.fourtypes.server;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import jp.freeex.fourtypes.shared.Results;
import jp.freeex.fourtypes.shared.Summary;
import jp.freeex.fourtypes.shared.Utils;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;
/**
 * 統計管理クラス。サーバ側処理の中心となるクラス。
 * @author hori
 *
 */
public class StatisticsManager implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 6784614482129795557L;
	/**
	 * ロガー(App Engine)
	 */
	private static final Logger log = 
			Logger.getLogger(StatisticsManager.class.getName());
	
	/**
	 * キャッシュネームスペース
	 */
	private static final String C_NAMESPACE = "fourtypes";
	/**
	 * キャッシュ名
	 */
	private static final String C_NAME = C_NAMESPACE;
	/**
	 * タイムアウト
	 */
//	private static final String C_TIMEOUT = "10830";
	/**
	 * キャッシュキー：結果
	 */
	private static final String C_KEY_RESULTS = "results";
	/**
	 * キャッシュキー：サマリ
	 */
	private static final String C_KEY_SUMMARY = "summary";
	
	/**
	 * 統計管理クラスインスタンス
	 */
	private static StatisticsManager manager = new StatisticsManager();
	
	/**
	 * 唯一のコンストラクタ（外部呼び出し不可）。
	 * キャッシュを初期化する。
	 */
	private StatisticsManager(){
		Map<String, String> props = new HashMap<String, String>();
		props.put(GCacheFactory.NAMESPACE, C_NAMESPACE);
//		props.put(GCacheFactory.EXPIRATION_DELTA, C_TIMEOUT);
		CacheManager cMan = CacheManager.getInstance();
		props.put("name", C_NAME);
		try{
			CacheFactory cFac = cMan.getCacheFactory();
			Cache cache = cFac.createCache(props);
			cMan.registerCache(C_NAME, cache);
			cache.put(C_KEY_RESULTS, new Results());
			cache.put(C_KEY_SUMMARY, retrieveSummary());
		}catch(CacheException e){
			
		}
	}
	/**
	 * 統計管理クラスインスタンスを取得する。
	 * @return 統計管理クラスインスタンス
	 */
	public static final StatisticsManager getInstance(){
		return manager;
	}
	
	/**
	 * DBからサマリを取得する。
	 * @return サマリ
	 */
	private Summary retrieveSummary(){
		long elapse = System.currentTimeMillis();
		log.info("[StatisticsManager#retrieveSummary()] start");
		Summary summary = null;
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = null;
		try{
			pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try{
				tx.begin();
				summary = 
						pm.getObjectById(Summary.class, Summary.DBKEY_SUMMARY);
				log.info("[StatisticsManager#retrieveSummary()] got from " +
						"database: " + summary);
				tx.commit();
			}catch(Exception e){
				log.log(Level.INFO, 
						"[StatisticsManager#retrieveSummary()] exception " +
						"during getting summary", e);
				if(tx!=null && tx.isActive()) tx.rollback();
				summary = null;
			}
		}finally{
			if(pm!=null){
				pm.close();
			}
		}
		
		// データベース上に集計結果がない場合
		if(summary==null){
			// 0件の集計結果をとりあえずデータベースへ保存する
			summary = new Summary();
			log.info("[StatisticsManager#retrieveSummary()] create new " +
					"summary: " + summary);
		}
		log.info("[StatisticsManager#retrieveSummary()] end: " +
				(System.currentTimeMillis() - elapse) + "mSec.");
		return summary;
	}
	/**
	 * 結果をキャッシュへ書き込む。
	 * @param x X座標
	 * @param y Y座標
	 * @param evaluatedAt　評価日時
	 */
	public void setResult(int x, int y, Date evaluatedAt){
		long elapse = System.currentTimeMillis();
		log.info("[StatisticsManager#setResult()] (x,y)=(" + x +", " + y +")");
		// キャッシュ取得
		Cache cache = 
				CacheManager.getInstance().getCache(C_NAME);
		// 集計結果キャッシュを加算
		Summary summary = (Summary) cache.get(C_KEY_SUMMARY);
		log.info("[StatisticsManager#setResult()] got summary from cache: " + 
				summary);
		if(summary == null){
			summary = new Summary();
			log.info("[StatisticsManager#setResult()] summary(cache) is null " +
					"/create new summary: " + summary.toString());
		}
		
		summary = Utils.add(x, y, evaluatedAt, summary);
		log.info("[StatisticsManager#setResult()] count up summary: " + 
				summary.toString());
		cache.put(C_KEY_SUMMARY, summary);

		// 結果キャッシュを更新
		Results results = 
				(Results) cache.get(C_KEY_RESULTS);
		log.info("[StatisticsManager#setResult()] got results from cache: " + 
				results);
		if(results == null){
			results = new Results();
			log.info("[StatisticsManager#setResult()] results(cache) is null " +
					"/create new result: " + results.toString());
		}
		results.add(x, y, evaluatedAt);
		log.info("[StatisticsManager#setResult()] count up results: " + 
				results.toString());
		cache.put(C_KEY_RESULTS, results);
		log.info("[StatisticsManager#setResult()] update result cache:" + 
				results.toString());
		log.info("[StatisticsManager#setResult()] end" +
				(System.currentTimeMillis() - elapse) + "mSec.");
	}
	/**
	 * キャッシュ上の結果群を取得する。
	 * @return 結果群
	 */
	public Results getResults(){
		long elapse = System.currentTimeMillis();
		log.info("[StatisticsManager#getResults()] start");
		// キャッシュ上のすべての結果をリストへ移動
		Cache cache = 
				CacheManager.getInstance().getCache(C_NAME);
		Results results = 
					(Results) cache.get(C_KEY_RESULTS);
		if(results==null){
			results = new Results();
		}
		log.info("[StatisticsManager#getResults()] end: " +
				(System.currentTimeMillis() - elapse) + "mSec.");
		return results;
		
	}
	/**
	 * サマリをキャッシュから取得する。
	 * @return サマリ
	 */
	public Summary getSummary(){
		long elapse = System.currentTimeMillis();
		log.info("[StatisticsManager#getSummary()] start");
		Cache cache = 
				CacheManager.getInstance().getCache(C_NAME);
		Summary summary = (Summary)cache.get(C_KEY_SUMMARY);
		log.info("[StatisticsManager#getSummary()] got summary from cache: " +
				summary);
		if(summary == null){
			summary = new Summary();
			log.info("[StatisticsManager#getSummary()] no summary cache/create:"
					+ summary.toString());
			cache.put(C_KEY_SUMMARY, summary);
			log.info("[StatisticsManager#getSummary()] update summary cache: " +
					summary.toString());
		}
		log.info("[StatisticsManager#getSummary()] end: " +
				(System.currentTimeMillis() - elapse) + "mSec.");
		return summary;
	}
	/**
	 * キャッシュ上の結果をすべてクリアする。
	 */
	public void clearResults(){
		long elapse = System.currentTimeMillis();
		log.info("[StatisticsManager#clearResults()] start");
		// キャッシュ上のすべての結果をリストへ移動
		Cache cache = 
				CacheManager.getInstance().getCache(C_NAME);
		cache.put(C_KEY_RESULTS, new Results());
		log.info("[StatisticsManager#clearResults()] end: " +
				(System.currentTimeMillis() - elapse) + "mSec.");
	}
}
