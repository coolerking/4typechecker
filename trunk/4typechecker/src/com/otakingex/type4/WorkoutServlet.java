package com.otakingex.type4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakingex.type4.control.WorkoutContext;

public class WorkoutServlet extends BaseServlet {

	private static final long serialVersionUID = -8524950581332082797L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		try{
			WorkoutContext con = new WorkoutContext(getParams(req));
			
			req.setAttribute(
					REQ_ATTRKEY_SUBTITLE, 
					con.getSubTitle());

			req.setAttribute(
					REQ_ATTRKEY_TROPISM_LIST, 
					con.getTropismQuestions());
			req.setAttribute(
					REQ_ATTRKEY_KINGSOLD_LIST, 
					con.getKingOrSolderQuestions());
			req.setAttribute(
					REQ_ATTRKEY_SCHLCRFT_LIST, 
					con.getScolarOrCraftsmanQuestions());
			
			// for hidden
			req.setAttribute(
					REQ_ATTRKEY_HIDDENMAP, 
					con.getHiddenMap());
			
			
			sendRedirect(JSP_WORKOUT, req, resp);

		}catch(Exception e){
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
}
