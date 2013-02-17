package jp.freeex.fourtypes.store;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import jp.freeex.fourtypes.control.WorkoutContext;
import jp.freeex.fourtypes.model.Count;
import jp.freeex.fourtypes.model.PersonalSummary;
import jp.freeex.fourtypes.model.User;

public class BigTableStoreManager {
	private Logger log = Logger.getLogger(getClass().getName());
	public static final String SYSTEM_PROP_IS_STORE = "type4.store";
	private static BigTableStoreManager manager = new BigTableStoreManager();
	private boolean store = false;
	
	private BigTableStoreManager(){
		String doStore = System.getProperty(SYSTEM_PROP_IS_STORE);
		doStore = doStore.trim().toLowerCase();
		if("true".equals(doStore)){
			store = true;
		}else{
			store = false;
		}
	}
	public static synchronized BigTableStoreManager getInstance(){
		return manager;
	}
	
	public void doStore(WorkoutContext con){
		if(!store){
			log.info("store init option off, ignore saving result");
			return;
		}
		PersonalSummary sum = new PersonalSummary();
		User user = con.getUser();
		sum.setName(user.getName());
		sum.setAge(user.getAge());
		sum.setMale(user.isMale());
		sum.setKingOrSolderScore(con.getKingOrSolderScore());
		sum.setScholarOrCraftsmanScore(con.getScholarOrCraftsmanScore());
		sum.setKingScore(con.getKingScore());
		sum.setSolderScore(con.getSolderScore());
		sum.setScholarScore(con.getScholarScore());
		sum.setCraftsmanScore(con.getCraftsmanScore());
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = pmf.getPersistenceManager();
		try{
			pm.makePersistent(sum);
			log.info("saving result:" +sum.toString());
		}finally{
			pm.close();
		}
	}

	public synchronized Count getCount(WorkoutContext con){
		Count c = getCount();
		
		// コンテキスト上の1件を追加する処理
		c.setTotal(c.getTotal() + 1);
		
		// 外向的な場合
		if(con.getKingOrSolderScore()>con.getScholarOrCraftsmanScore()){
			c.setKingOrSolder(c.getKingOrSolder() + 1);

			// 注目型の場合
			if(con.getKingScore()>con.getSolderScore()){
				c.setKing(c.getKing() + 1);

			// 司令型の場合
			}else if(con.getKingScore()<con.getSolderScore()){
				c.setSolder(c.getSolder() + 1);
			}

		// 内向的な場合
		}else if(con.getKingOrSolderScore()<con.getScholarOrCraftsmanScore()){
			c.setScholarOrCraftsman(c.getScholarOrCraftsman() + 1);

			// 法則型の場合
			if(con.getScholarScore()>con.getCraftsmanScore()){
				c.setScholar(c.getScholar() + 1);

			// 理想型の場合
			}else if(con.getScholarScore()<con.getCraftsmanScore()){
				c.setCraftsman(c.getCraftsman() + 1);
			}
		}
		return c;
	}
	
	public synchronized Count getCount(){
		return CountManager.getInstance().getCount();
	}
}
