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

public class WorkoutServlet extends BaseServlet {

	private static final long serialVersionUID = -8524950581332082797L;
	private Logger log = Logger.getLogger(getClass().getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		try{
			WorkoutContext con = new WorkoutContext(Utils.getParameters(req));
			
			req.setAttribute(
					REQ_ATTRKEY_SUBTITLE, 
					con.getSubTitle());

			String testKey = con.getTestKey();
			Boolean lastTest = Boolean.TRUE;
			if(testKey==null){
				testKey = con.createTestKey();
				lastTest = Boolean.FALSE;
			}
			req.setAttribute(REQ_KEY_TESTKEY, testKey);
			
			int kingOrSolder = con.getKingOrSolderScore();
			int scholarOrCraftsman = con.getScholarOrCraftsmanScore();

			if(kingOrSolder>=0){
				req.setAttribute(
					REQ_ATTRKEY_TROPISM_LIST, 
					new LinkedList<Question>());
				if(kingOrSolder>scholarOrCraftsman){
					req.setAttribute(
							REQ_ATTRKEY_KINGSOLD_LIST, 
							con.getKingOrSolderQuestions(testKey));
					req.setAttribute(
							REQ_ATTRKEY_SCHLCRFT_LIST, 
							new LinkedList<Question>());
				}else if(kingOrSolder<scholarOrCraftsman){
					req.setAttribute(
							REQ_ATTRKEY_KINGSOLD_LIST, 
							new LinkedList<Question>());
					req.setAttribute(
							REQ_ATTRKEY_SCHLCRFT_LIST, 
							con.getScholarOrCraftsmanQuestions(testKey));	
				}else{
					req.setAttribute(
							REQ_ATTRKEY_KINGSOLD_LIST, 
							con.getKingOrSolderQuestions(testKey));
					req.setAttribute(
							REQ_ATTRKEY_SCHLCRFT_LIST, 
							con.getScholarOrCraftsmanQuestions(testKey));						
				}
			}else{
				req.setAttribute(
						REQ_ATTRKEY_TROPISM_LIST, 
						con.getTropismQuestions(testKey));
				req.setAttribute(
						REQ_ATTRKEY_KINGSOLD_LIST, 
						new LinkedList<Question>());
				req.setAttribute(
						REQ_ATTRKEY_SCHLCRFT_LIST, 
						new LinkedList<Question>());
				
			}
			
			// for hidden
			Map<String, String> hiddens = con.getHiddenMap();
			hiddens.put(REQ_KEY_TESTKEY, testKey);

			req.setAttribute(
					REQ_ATTRKEY_HIDDENMAP, 
					hiddens);
			req.setAttribute(REQ_ATTRKEY_SENDSUMMARY, lastTest);
			
			sendRedirect(JSP_WORKOUT, req, resp);

		}catch(Exception e){
			log.log(Level.INFO, "WorkoutServlet実行中の例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
}
