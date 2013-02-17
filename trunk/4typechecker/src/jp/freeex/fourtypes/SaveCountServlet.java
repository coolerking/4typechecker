package jp.freeex.fourtypes;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.freeex.fourtypes.model.Count;
import jp.freeex.fourtypes.store.CountManager;
import jp.freeex.fourtypes.store.PMF;

public class SaveCountServlet extends BaseServlet {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -2763974562246041746L;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		info(getClass().getName(), "saving count process start");
		PersistenceManagerFactory pmf = PMF.get();
		PersistenceManager pm = null;
		try{
			pm = pmf.getPersistenceManager();
			Transaction tx = pm.currentTransaction();	
			Count count = CountManager.getInstance().getCount();
			try{
				tx.begin();
				pm.makePersistent(count);
				tx.commit();
				info(getClass().getName(), "saving count done :" + 
						count.toString());
			}finally{
				if(tx!=null&&tx.isActive()) tx.rollback();
			}
		}finally{
			if(pm!=null) pm.close();
			info(getClass().getName(), "saving count process end");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		info(getClass().getName(), "no operation fowarding to error page");
		sendRedirect(JSP_ERROR, req, resp);
	}

}
