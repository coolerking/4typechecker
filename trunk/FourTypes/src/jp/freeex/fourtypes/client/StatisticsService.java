package jp.freeex.fourtypes.client;

import java.util.Date;
import java.util.List;

import jp.freeex.fourtypes.shared.Summary;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
/**
 * 統計サービスのスタブ側インターフェイス。
 * @author tasuku
 */
@RemoteServiceRelativePath("stat")
public interface StatisticsService extends RemoteService {
	/**
	 * 結果を格納する。
	 * @param x X座標
	 * @param y Y座標
	 * @param evaluatedAt　計測日時
	 */
	void setResult(int x, int y, Date evaluatedAt);
	/**
	 * サマリを取得する。
	 * @return Summary サマリ
	 */
	Summary getSummary();
	/**
	 * 結果群を取得する。
	 * @return List<long[]> long[]は3つの要素を持つ。0:X座標、1:Y座標、2:計測日時(mSec)
	 */
	List<long[]> getResults();

}
