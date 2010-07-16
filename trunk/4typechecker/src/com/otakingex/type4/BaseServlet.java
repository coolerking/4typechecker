package com.otakingex.type4;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakingex.type4.model.User;

public abstract class BaseServlet extends HttpServlet implements ViewConstants{

	private static final long serialVersionUID = 2180730094602666191L;
	private Logger log = Logger.getLogger(getClass().getName());

	@SuppressWarnings("unchecked")
	Map<String, String> getParams(HttpServletRequest req){
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> e = req.getParameterNames();
		while(e.hasMoreElements()){
			String name = e.nextElement();
			String value = req.getParameter(name);
			params.put(name, value);
		}		
		return params;
	}
	
	User getUser(HttpServletRequest req){
		Map<String, String> params = getParams(req);
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
