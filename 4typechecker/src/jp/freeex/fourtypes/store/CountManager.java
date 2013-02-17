package jp.freeex.fourtypes.store;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;

import jp.freeex.fourtypes.ViewConstants;
import jp.freeex.fourtypes.model.Count;

public class CountManager implements Serializable {

	/**
	 * ロガー
	 */
	private Logger log = Logger.getLogger(CountManager.class.getName());

	/**
	 * 唯一のCountManagerインスタンス
	 */
	private static final CountManager manager = new CountManager();
	
	/**
	 * キャッシュ：ネームスペース
	 */
	private static final String CACHE_NAMESPACE = "fourtypes";
	
	/**
	 * キャッシュ：キャッシュ名
	 */
	private static final String CACHE_NAME = "count";
	
	/**
	 * キャッシュキー：カウンタオブジェクト
	 */
	private static final String CACHEKEY_COUNT = "count";

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -8098464184546929978L;

	/**
	 * コンストラクタ（外部からの呼び出し不可）
	 */
	@SuppressWarnings("unchecked")
	private CountManager(){
		@SuppressWarnings("rawtypes")
		Map props = new HashMap();
		props.put("name", CACHE_NAME);
		props.put(GCacheFactory.NAMESPACE, CACHE_NAMESPACE);
		// 生存期間は3時間30分
		props.put(GCacheFactory.EXPIRATION_DELTA, 60*60*3+30);
		
		CacheManager cMan = CacheManager.getInstance();
		try{
			CacheFactory cFac = cMan.getCacheFactory();
			Cache cache = cFac.createCache(props);
			cMan.registerCache(CACHE_NAME, cache);

			// DBからカウンタ情報取得
			Count count = null;
			try{
				count = retrieveCount();
			}catch(Exception e){
				count = new Count();
				count.setTotal(41696);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, 2010);
				cal.set(Calendar.MONTH, 8);
				cal.set(Calendar.DAY_OF_MONTH, 6);
				cal.set(Calendar.HOUR_OF_DAY, 14);
				cal.set(Calendar.MINUTE, 1);
				cal.set(Calendar.SECOND, 41);
				cal.set(Calendar.MILLISECOND, 177);
				count.setCreatedAt(cal.getTime());
				count.setKing(4402);
				count.setSolder(2651);
				count.setScholar(11600);
				count.setCraftsman(17191);
				count.setKingOrSolder(7864);
				count.setScholarOrCraftsman(31795);
				log.log(Level.INFO, 
						"[constructor] DB exception so create count dummy as " + 
						count.toString(), e);
			}
			cache.put(CACHEKEY_COUNT, count);
			log.log(Level.INFO, 
					"[CountCacheManager] set cache from bigtable :" + 
					count.toString());
		}catch(CacheException e){
			log.log(Level.WARNING, 
					"[CountCacheManager] exception in constructor", e);
		}
	}
	
	private Count retrieveCount(){
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = null;
		try{
			pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			Count c = null;
			try{
				tx.begin();
				try{
					c = pm.getObjectById(
							Count.class, ViewConstants.PRIMARYKEY_ID);
				}catch(Exception e){
					log.log(Level.INFO, "Count 取得中例外", e);
					c = new Count();
				}
				tx.commit();
				if(c==null){
					log.info("no data then create Count");
					c = new Count();
				}
				log.info("Count:" + c.toString());
				return c;
			}finally{
				if(tx.isActive()) tx.rollback();
			}
		}finally{
			if(pm!=null) pm.close();
		}
	}
	
	public final static synchronized CountManager getInstance(){
		return manager;
	}
	
	public Count getCount(){
		Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
		Count count = (Count) cache.get(CACHEKEY_COUNT);
		// データがキャッシュにない場合はデータベースから復元
		if(count==null){
			count = retrieveCount();
			log.log(Level.INFO, 
					"[getManager] get cache from bigtable :" + 
					count.toString());
			cache.put(CACHEKEY_COUNT, count);
		}
		return count;
	}
	
	public void setCount(Count count){
		Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
		cache.put(CACHEKEY_COUNT, count);
		log.log(Level.INFO, "cache in count:" + count.toString());
	}
}
