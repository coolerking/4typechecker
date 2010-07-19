package com.otakingex.type4;

import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.otakingex.type4.control.Utils;
import com.otakingex.type4.control.WorkoutContext;
import com.otakingex.type4.model.Question;

public class OptionWorkoutServlet extends BaseServlet {

	private static final long serialVersionUID = -2485056706970305246L;
	private Logger log = Logger.getLogger(getClass().getName());

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		try{
			WorkoutContext con = new WorkoutContext(Utils.getParameters(req));
			String testKey = con.getTestKey();
			
			if(con.getKingScore()<0){
				// 王様・軍人テスト
				req.setAttribute(REQ_ATTRKEY_SUBTITLE, "王様・軍人テスト");
				req.setAttribute(
						REQ_ATTRKEY_KINGSOLD_LIST, 
						con.getKingOrSolderQuestions(testKey));
				req.setAttribute(
						REQ_ATTRKEY_SCHLCRFT_LIST, 
						new LinkedList<Question>());
				
			}else{
				// 学者・職人テスト
				req.setAttribute(REQ_ATTRKEY_SUBTITLE, "王様・軍人テスト");
				req.setAttribute(
						REQ_ATTRKEY_KINGSOLD_LIST, 
						new LinkedList<Question>());
				req.setAttribute(
						REQ_ATTRKEY_SCHLCRFT_LIST, 
						con.getScholarOrCraftsmanQuestions(testKey));	
			}
			
			// hidden
			Map<String, String> hiddens = con.getHiddenMap();
			req.setAttribute(REQ_ATTRKEY_HIDDENMAP, hiddens);
			
			// send to jsp file
			sendRedirect(JSP_OPTION, req, resp);
		}catch(Exception e){
			log.log(Level.INFO, "SummaryServlet実行中の例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
		
	}

}
