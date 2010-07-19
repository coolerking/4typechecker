package com.otakingex.type4.store;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.otakingex.type4.control.WorkoutContext;
import com.otakingex.type4.model.PersonalSummary;
import com.otakingex.type4.model.User;

public class BigTableStoreManager {
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
		sum.setKingOrSolderScore(con.getScholarOrCraftsmanScore());
		sum.setScholarOrCraftsmanScore(con.getScholarOrCraftsmanScore());
		sum.setKingScore(con.getKingScore());
		sum.setSolderScore(con.getSolderScore());
		sum.setScholarScore(con.getScholarScore());
		sum.setCraftsmanScore(con.getCraftsmanScore());
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = pmf.getPersistenceManager();
		try{
			pm.makePersistent(sum);
		}finally{
			pm.close();
		}
	}

}
