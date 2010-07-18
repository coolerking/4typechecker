package com.otakingex.type4;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EntryServlet extends BaseServlet {

	private static final long serialVersionUID = -1141919162647029771L;
	private Logger log = Logger.getLogger(getClass().getName());
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try{
			// サブタイトルなしで
			req.setAttribute(REQ_ATTRKEY_SUBTITLE, " ");
			
			// ユーザ情報を作成し、Attributeとして格納
			req.setAttribute(REQ_ATTRKEY_USER, getUser(req));
			sendRedirect(JSP_ENTRY, req, resp);

		}catch(Exception e){
			log.log(Level.INFO, "EntryServlet#doGet()処理中例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		try{
			// サブタイトルなしで
			req.setAttribute(REQ_ATTRKEY_SUBTITLE, " ");
			
			// ユーザ情報を作成し、Attributeとして格納
			req.setAttribute(REQ_ATTRKEY_USER, getUser(req));
			sendRedirect(JSP_ENTRY, req, resp);

		}catch(Exception e){
			log.log(Level.INFO, "EntryServlet#doPost()処理中例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
}
