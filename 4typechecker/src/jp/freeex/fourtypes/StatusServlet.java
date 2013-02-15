package jp.freeex.fourtypes;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.freeex.fourtypes.model.Count;
import jp.freeex.fourtypes.store.BigTableStoreManager;

public class StatusServlet extends BaseServlet implements ViewConstants{

	private static final long serialVersionUID = 6713413426700444993L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try{
			BigTableStoreManager manager = 
					BigTableStoreManager.getInstance();
			Count c = manager.getCount();

			req.setAttribute(REQ_KEY_COUNT, c);

			sendRedirect(JSP_STATUS, req, resp);
		}catch(Exception e){
			info(getClass().getName(), "StatusServlet実行中の例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		sendRedirect(JSP_NOLINK, req, resp);
	}
}
