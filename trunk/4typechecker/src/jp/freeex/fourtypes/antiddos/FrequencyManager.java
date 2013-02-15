package jp.freeex.fourtypes.antiddos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;


public class FrequencyManager implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -7842803446109060946L;
	
	/**
	 * キャッシュクリアされるデフォルトのタイムアウト時間（秒）
	 */
	public static final int DEF_TIMEOUT = 3600;
	
	/**
	 * アクセス回数上限（リクエスト数）
	 */
	public static final int DEF_MAXCOUNTS = 200;
	
	/**
	 * appengine-web.xml上に定義するプロパティキー：タイムアウト時間（秒）
	 */
	public static final String PROPKEY_TIMEOUT = "4type.cache.timeout";
	
	/**
	 * appengine-web.xml上に定義するプロパティキー：リクエスト上限回数
	 */
	public static final String PROPKEY_MAXCOUNTS = "4type.cache.maxcounts";
	
	/**
	 * キャッシュ名
	 */
	public static final String CACHE_NAME = "4type.frequnecy";
	
	/**
	 * ロガー(App Engine)
	 */
	private static final Logger log = 
			Logger.getLogger(FrequencyManager.class.getName());
	
	/**
	 * 唯一のFrequencyManagerインスタンス
	 */
	private static final FrequencyManager freqManager = new FrequencyManager();
	
//	private Cache cache = null;
	private int maxCounts = 200;
	private int timeout = DEF_TIMEOUT;

	/**
	 * 外部から呼び出し不可のコンストラクタ。
	 * プロパティ値が設定されていればその値でタイムアウト、上限アクセス数を変更し、
	 * キャッシュを生成する。
	 * キャッシュ生成時の例外は無視される(App Engineログへ出力)。
	 */
	@SuppressWarnings("unchecked")
	private FrequencyManager(){
		// タイムアウト値の確定
		String propTimeout = System.getProperty(PROPKEY_TIMEOUT);
		if(propTimeout!=null){
			try{
				timeout = Integer.parseInt(propTimeout);
			}catch(RuntimeException e){
			}
		}
		
		// 最大アクセス回数の確定
		String propMaxCounts = System.getProperty(PROPKEY_MAXCOUNTS);
		if(propMaxCounts!=null){
			try{
				maxCounts = Integer.parseInt(propMaxCounts);
			}catch(RuntimeException e){
			}
		}
		
		// キャッシュの生成
		try {
			@SuppressWarnings("rawtypes")
			// キャッシュ設定用Map生成
			Map props = new HashMap();
			// タイムアウト値を設定
			props.put(GCacheFactory.EXPIRATION_DELTA, timeout);
			// キャッシュ名を設定
			props.put("name", CACHE_NAME);
			// キャッシュマネージャを取得
			CacheManager manager = CacheManager.getInstance();
			// キャッシュファクトリを取得
			CacheFactory factory = manager.getCacheFactory();
			// キャッシュを生成
			Cache cache = factory.createCache(props);
			// 生成したキャッシュをキャッシュ名をつけて登録
			manager.registerCache(CACHE_NAME, cache);
		} catch (CacheException e) {
			// 例外は再スローしない
			log.log(Level.WARNING, "Exception in constructor", e);
		}
	}
	
	/**
	 * FrequencyManagerインスタンスを取得する。
	 * @return FrequencyManager インスタンス
	 */
	public static final FrequencyManager getInstance(){
		return freqManager;
	}
	
	/**
	 * 指定したipアドレスの利用回数が上限に達しているかを確認する。
	 * @param ip IPアドレス
	 * @return boolean 真：超過している、偽：範囲内
	 */
	public boolean isHeavyAccess(String ip){
		Frequency freq = null;
		Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
		freq = (Frequency) cache.get(ip);
		if(freq==null){
			freq = new Frequency(timeout, maxCounts);
			cache.put(ip, freq);
			log.log(Level.FINE, "create Frequency ip=" + ip);
		}
		return freq.isHeavyAccess();
	}

	/**
	 * 頻度クラス（インナークラス）。
	 * キャッシュに格納される唯一のオブジェクト。
	 * @author harahara-development
	 */
	class Frequency implements Serializable{
		/**
		 * シリアルバージョンUID
		 */
		private static final long serialVersionUID = -2430486326348134045L;
		/**
		 * デフォルトの最大アクセス回数
		 */
		private int maxCounts = DEF_MAXCOUNTS;
		/**
		 * デフォルトのタイムアウト値（ミリ秒）
		 */
		private long timeoutMills = DEF_TIMEOUT*1000L;
		/**
		 * インスタンス生成時刻
		 */
		private long createdAtMills = System.currentTimeMillis();
		/**
		 * アクセス回数
		 */
		private int count = 0;
		/**
		 * インスタンスを生成する。
		 * @param timeout タイムアウト時間（秒）
		 * @param maxCounts 最大アクセス回数
		 */
		Frequency(int timeout, int maxCounts){
			this.timeoutMills = timeout*1000L;
			this.maxCounts = maxCounts;
		}
		/**
		 * 利用回数が上限に達しているかを確認する。
		 * 判断とともにカウンタを１回加算する。
		 * @return boolean 真：超過している、偽：範囲内
		 */
		public boolean isHeavyAccess(){
			// 有効期限を超えた場合、カウンタをリセットする
			if(System.currentTimeMillis()>(createdAtMills+timeoutMills)){
				count = 0;
				createdAtMills = System.currentTimeMillis();
				log.log(Level.FINE, "Frequency reseted");
				return true;
			}
			// カウンタを1加算し最大カウント回数を超過したかどうかbooleanを返却する
			return ((++count)>maxCounts);
		}
		
		@Override
		public String toString(){
			return "Count(" + count + "/" + maxCounts + ")";
		}
	}
}
