package jp.freeex.fourtypes.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
/**
 * PMFインスタンスを保持するクラス。
 * @author tasuku
 *
 */
public class PMF {
	/**
	 * PMFインスタンス
	 */
	private static final PersistenceManagerFactory pmfInstance =
			JDOHelper.getPersistenceManagerFactory("transactions-optional");

	/**
	 * デフォルトコンストラクタ（呼び出し不可）
	 */
	private PMF() {}

	/**
	 * PMFインスタンスを取得する。
	 * @return PMFインスタンス
	 */
	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
}
