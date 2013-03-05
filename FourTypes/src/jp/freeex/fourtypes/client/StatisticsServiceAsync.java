package jp.freeex.fourtypes.client;

import java.util.Date;
import java.util.List;

import jp.freeex.fourtypes.shared.Summary;

import com.google.gwt.user.client.rpc.AsyncCallback;
/**
 * 統計サービスのドライバ側インタフェイス
 * @author tasuku
 */
public interface StatisticsServiceAsync {
	/**
	 * 結果を格納する。
	 * @param x X座標
	 * @param y Y座標
	 * @param evaluatedAt　計測日時
	 * @param callback コールバック時の処理を実装したクラス
	 */
	void setResult(int x, int y, Date evaluatedAt, 
			AsyncCallback<Void> callback);
	/**
	 * サマリを取得する。
	 *@param callback コールバック時の処理を実装したクラス
	 */
	void getSummary(AsyncCallback<Summary> callback);
	/**
	 * 結果群を取得する。
	 * @param callback コールバック時の処理を実装したクラス
	 */
	void getResults(AsyncCallback<List<long[]>> callback);

}
