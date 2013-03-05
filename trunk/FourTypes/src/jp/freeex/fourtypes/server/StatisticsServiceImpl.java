package jp.freeex.fourtypes.server;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import jp.freeex.fourtypes.client.StatisticsService;
import jp.freeex.fourtypes.shared.Summary;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
/**
 * 統計サービスの実装クラス。
 * @author tasuku
 */
public class StatisticsServiceImpl extends RemoteServiceServlet implements
		StatisticsService {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 483984716288216511L;
	/**
	 * ロガー(App Engine)
	 */
	private static final Logger log = 
			Logger.getLogger(StatisticsServiceImpl.class.getName());

	/**
	 * 結果をキャッシュへ格納する。
	 * @param x X座標
	 * @param y Y座標
	 * @param evaluatedAt　計測日時
	 */
	@Override
	public void setResult(int x, int y, Date evaluatedAt) {
		long elapse = System.currentTimeMillis();
		log.info("[StatisticsServiceImpl#setResult()] start");
		try{
			StatisticsManager.getInstance().setResult(x, y, evaluatedAt);
		}finally{
			log.info("[StatisticsServiceImpl#setResult()] end:" +
					(System.currentTimeMillis() - elapse) + "mSec.");
		}
	}

	/**
	 * サマリをキャッシュから取得する。
	 * @return Summary サマリ
	 */
	@Override
	public Summary getSummary() {
		long elapse = System.currentTimeMillis();
		log.info("[StatisticsServiceImpl#getSummary()] start");
		try{
			return StatisticsManager.getInstance().getSummary();
		}finally{
			log.info("[StatisticsServiceImpl#getSummary()] end:" +
					(System.currentTimeMillis() - elapse) + "mSec.");
		}
	}

	/**
	 * 結果群をキャッシュから取得する。
	 * @return List<long[]> long[]は3つの要素を持つ。0:X座標、1:Y座標、2:計測日時(mSec)
	 */
	@Override
	public List<long[]> getResults() {
		long elapse = System.currentTimeMillis();
		log.info("[StatisticsServiceImpl#getResults()] start");
		try{
			return StatisticsManager.getInstance().getResults().getResults();
		}finally{
			log.info("[StatisticsServiceImpl#getResults()] end:" +
					(System.currentTimeMillis() - elapse) + "mSec.");
		}
	}
}
