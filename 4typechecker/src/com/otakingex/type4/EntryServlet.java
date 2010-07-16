package com.otakingex.type4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EntryServlet extends BaseServlet {

	private static final long serialVersionUID = -1141919162647029771L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try{
			// サブタイトルなしで
			req.setAttribute(REQ_ATTRKEY_SUBTITLE, " ");
			
			// ユーザ情報を作成し、Attributeとして格納
			req.setAttribute(REQ_ATTRKEY_USER, getUser(req));
			sendRedirect(JSP_ENTRY, req, resp);

		}catch(Exception e){
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
}
