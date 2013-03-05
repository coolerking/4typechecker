package jp.freeex.fourtypes.server;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import jp.freeex.fourtypes.shared.Result;
import jp.freeex.fourtypes.shared.Results;
/**
 * 結果群をDBへ書き込むcronサービス
 * @author tasuku
 */
public class ResultsCronService extends BaseCronService {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 2394788072302421023L;

	/**
	 * ロガー
	 */
	private static Logger log = 
			Logger.getLogger(ResultsCronService.class.getName());

	/**
	 * memcache上の結果群をすべてDBへ書き込む。
	 * キャッシュ自体はクリアしない。
	 */
	@Override
	public void execute() {
		long elapse = System.currentTimeMillis();
		log.info("[ResultsCronService#execute()] start");
		Results results = StatisticsManager.getInstance().getResults();
		if(results==null||results.size()<=0){
			log.info("[ResultsCronService#execute()] no-results/end: " + 
					(System.currentTimeMillis() - elapse) + "mSec.");
			return;
		}
		List<Result> resultList = results.getResultList();
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = null;
		try{
			pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			try{
				for(Result r: resultList){
					tx.begin();
					log.info("[ResultsCronService#execute()] " +
							"insert result list (pre): " + r);
					pm.makePersistentAll(r);
					log.info("[ResultsCronService#execute()] " +
							"insert result (post): " + resultList.size());
					tx.commit();
					
					log.info("[ResultsCronService#execute()] commited");
				}
				StatisticsManager.getInstance().clearResults();
			}finally{
				if(tx!=null&&tx.isActive()) tx.rollback();
			}
		}finally{
			if(pm!=null) pm.close();
		}
		log.info("[ResultsCronService#execute()] end: " + 
				(System.currentTimeMillis() - elapse) + "mSec.");

	}

}
