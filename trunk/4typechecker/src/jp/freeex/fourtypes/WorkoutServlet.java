package jp.freeex.fourtypes;

import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.freeex.fourtypes.control.Utils;
import jp.freeex.fourtypes.control.WorkoutContext;
import jp.freeex.fourtypes.model.Question;

public class WorkoutServlet extends BaseServlet {

	private static final long serialVersionUID = -8524950581332082797L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		sendRedirect(JSP_NOLINK, req, resp);
	}

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
			info(getClass().getName(), "WorkoutServlet実行中の例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
}
