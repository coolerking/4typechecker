package jp.freeex.fourtypes.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.freeex.fourtypes.shared.HTMLConst;

public abstract class BaseCronService extends HttpServlet implements HTMLConst{

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 8132957214483025646L;
	
	/**
	 * ロガー
	 */
	private static Logger log = 
			Logger.getLogger(BaseCronService.class.getName());

	public abstract void execute();
	
	private boolean checkIpAddress(HttpServletRequest req){
		String ip = req.getRemoteAddr();
		return ServerUtils.isJapaneseIP(ip);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		if(checkIpAddress(req)){
			execute();
		}else{
			log.info("[BaseCronService#doGet()] ignore access/out of range :" + 
					req.getRemoteAddr());
		}
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		if(checkIpAddress(req)){
			execute();
		}else{
			log.info("[BaseCronService#doPost()] ignore access/out of range :" + 
					req.getRemoteAddr());
		}
	}
	
	/**
	 * リダイレクトへ進む
	 * @param jspName JSP名
	 * @param req リクエスト
	 * @param resp レスポンス
	 */
	void sendRedirect(String jspName, HttpServletRequest req, 
			HttpServletResponse resp){
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jspName);
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			log.log(Level.WARNING, "[BaseCronService] exception occured", e);
			try {
				resp.sendRedirect(URL_SORRY);
			} catch (IOException ioe) {
				log.log(Level.WARNING, 
						"[BaseCronService] exception occured while sending " +
						"sorry page", e);
			}
		}
	}
}
