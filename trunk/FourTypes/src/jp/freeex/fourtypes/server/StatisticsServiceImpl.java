package jp.freeex.fourtypes.server;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import jp.freeex.fourtypes.client.StatisticsService;
import jp.freeex.fourtypes.shared.Summary;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class StatisticsServiceImpl extends RemoteServiceServlet implements
		StatisticsService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 483984716288216511L;
	/**
	 * ロガー(App Engine)
	 */
	private static final Logger log = 
			Logger.getLogger(StatisticsServiceImpl.class.getName());

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
