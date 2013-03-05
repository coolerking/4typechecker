package jp.freeex.fourtypes.server;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;


import jp.freeex.fourtypes.shared.Summary;
/**
 * サマリをDBへ書き込むcronサービス。
 * @author tasuku
 */
public class SummaryCronService extends BaseCronService {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -6364330515499150464L;
	
	/**
	 * ロガー
	 */
	private static Logger log = 
			Logger.getLogger(SummaryCronService.class.getName());

	/**
	 * キャッシュ上のサマリをDBへ書き込む。
	 */
	@Override
	public void execute() {
		long elapse = System.currentTimeMillis();
		log.info("[SummaryCronService#execute()] start");
		Summary sum = StatisticsManager.getInstance().getSummary();
		if(sum==null){
			log.info("[SummaryCronService#execute()] no-summary/end: " + 
					(System.currentTimeMillis() - elapse) + "mSec.");
			return;
		}
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = null;
		try{
			pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try{
				tx.begin();
				pm.makePersistent(sum);
				tx.commit();
				log.info("[SummaryCronService#execute()] save summary: " + sum);
			}finally{
				if(tx!=null&&tx.isActive()) tx.rollback();
			}
		}finally{
			if(pm!=null) pm.close();
		}
		log.info("[SummaryCronService#execute()] end: " + 
				(System.currentTimeMillis() - elapse) + "mSec.");
	}

}
