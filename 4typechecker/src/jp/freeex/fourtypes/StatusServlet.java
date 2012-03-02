package jp.freeex.fourtypes;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.freeex.fourtypes.model.Count;
import jp.freeex.fourtypes.store.BigTableStoreManager;

public class StatusServlet extends BaseServlet implements ViewConstants{

	private static final long serialVersionUID = 6713413426700444993L;
	private Logger log = Logger.getLogger(getClass().getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try{
			BigTableStoreManager manager = BigTableStoreManager.getInstance();
			Count c = manager.getCount();
		
			req.setAttribute(REQ_KEY_COUNT, c);
			
			sendRedirect(JSP_STATUS, req, resp);
		}catch(Exception e){
			log.log(Level.INFO, "StatusServlet実行中の例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		sendRedirect(JSP_NOLINK, req, resp);
	}
}
