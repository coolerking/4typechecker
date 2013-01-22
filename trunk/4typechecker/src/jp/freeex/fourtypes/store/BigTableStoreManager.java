package jp.freeex.fourtypes.store;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;



import jp.freeex.fourtypes.ViewConstants;
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
		if(!store) return;
		PersonalSummary sum = new PersonalSummary();
		User user = con.getUser();
		sum.setName(user.getName());
		sum.setAge(user.getAge());
		sum.setMale(user.isMale());
// bug fixed 2012/05/09
//		sum.setKingOrSolderScore(con.getScholarOrCraftsmanScore());
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
			//log.info("Result:" +sum.toString());
		}finally{
			pm.close();
		}
	}

	public Count getCount(WorkoutContext con){
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = null;
		try{
			pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			Count c = null;
			try{
				tx.begin();
				try{
					c = pm.getObjectById(Count.class, ViewConstants.PRIMARYKEY_ID);
				}catch(Exception e){
					log.log(Level.INFO, "Count 取得中例外", e);
					c = new Count();
				}
				c.setTotal(c.getTotal() + 1);
				if(con.getKingOrSolderScore()>con.getScholarOrCraftsmanScore()){
					c.setKingOrSolder(c.getKingOrSolder() + 1);
					if(con.getKingScore()>con.getSolderScore()){
						c.setKing(c.getKing() + 1);
					}else if(con.getKingScore()<con.getSolderScore()){
						c.setSolder(c.getSolder() + 1);
					}
					
				}else if(con.getKingOrSolderScore()<con.getScholarOrCraftsmanScore()){
					c.setScholarOrCraftsman(c.getScholarOrCraftsman() + 1);
					if(con.getScholarScore()>con.getCraftsmanScore()){
						c.setScholar(c.getScholar() + 1);
					}else if(con.getScholarScore()<con.getCraftsmanScore()){
						c.setCraftsman(c.getCraftsman() + 1);
					}
				}
				pm.makePersistent(c);
				
				tx.commit();
				log.info("Count:" + c.toString());
				return c;
			}finally{
				if(tx.isActive()) tx.rollback();
			}
		}finally{
			if(pm!=null) pm.close();
		}
	}
	
	public Count getCount(){
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = null;
		try{
			pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();
			Count c = null;
			try{
				tx.begin();
				try{
					c = pm.getObjectById(Count.class, ViewConstants.PRIMARYKEY_ID);
				}catch(Exception e){
					log.log(Level.INFO, "Count 取得中例外", e);
					c = new Count();
				}
				tx.commit();
				log.info("Count:" + c.toString());
				return c;
			}finally{
				if(tx.isActive()) tx.rollback();
			}
		}finally{
			if(pm!=null) pm.close();
		}
	}
}
