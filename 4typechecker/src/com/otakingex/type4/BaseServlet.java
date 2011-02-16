package com.otakingex.type4;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakingex.type4.control.Utils;
import com.otakingex.type4.model.User;
/**
 * BaseServlet Servletで使用する共通機能を実装。
 * @author Tasuku
 */
public abstract class BaseServlet extends HttpServlet implements ViewConstants{

	private static final long serialVersionUID = 2180730094602666191L;
	private Logger log = Logger.getLogger(getClass().getName());
	
	/**
	 * User オブジェクトの取得
	 * @param req
	 * @return com.otakingex.type4.model.User
	 */
	User getUser(HttpServletRequest req){
		Map<String, String> params = Utils.getParameters(req);
		String name = params.get(REQ_KEY_NAME);
		User user = new User();
		if(name == null) return user;
		user.setName(name);
		try{
			user.setAge(Integer.parseInt(params.get(REQ_KEY_AGE)));
		}catch(RuntimeException e){
			//nop
		}
		String sex = params.get(REQ_KEY_SEX);
		user.setMale(REQ_VALUE_SEX_MALE.equals(sex));
		return user;
	}
	
	/**
	 * リダイレクトへ進む
	 * @param jspName JSP名
	 * @param req リクエスト
	 * @param resp レスポンス
	 */
	void sendRedirect(String jspName, HttpServletRequest req, HttpServletResponse resp){
		ServletContext context = getServletContext();
		RequestDispatcher rd = context.getRequestDispatcher(jspName);
		try {
			rd.forward(req, resp);
		} catch (Exception e) {
			log.log(Level.INFO, "フォワード中の例外", e);
			try {
				resp.sendRedirect(JSP_ERROR);
			} catch (IOException ioe) {
				log.log(Level.INFO, "エラーページへのフォワード中の例外", ioe);				
			}
		}
	}

}
