﻿package jp.freeex.fourtypes;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.freeex.fourtypes.control.Utils;
import jp.freeex.fourtypes.control.WorkoutContext;
import jp.freeex.fourtypes.model.Count;

public class SummaryServlet extends BaseServlet {

	private static final long serialVersionUID = 4314954898375142805L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		sendRedirect(JSP_NOLINK, req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		try{
			WorkoutContext con = 
					new WorkoutContext(Utils.getParameters(req));
			String testKey = con.getTestKey();

			req.setAttribute(REQ_ATTRKEY_RESULT, con.getResult());
			req.setAttribute(REQ_KEY_TESTKEY, testKey);
			req.setAttribute(
				REQ_ATTRKEY_SUBTITLE, 
				"結果");

			int kingOrSolder = con.getKingOrSolderScore();
			int scholarOrCraftsman = con.getScholarOrCraftsmanScore();
			int king = con.getKingScore();
			int solder = con.getSolderScore();
			int scholar = con.getScholarScore();
			int craftsman = con.getCraftsmanScore();

			req.setAttribute(REQ_KEY_SCORE_KINGSOLD, 
					new Integer(kingOrSolder).toString());
			req.setAttribute(REQ_KEY_SCORE_SCHLCRFT, 
					new Integer(scholarOrCraftsman).toString());
			if(king<0) req.setAttribute(REQ_KEY_SCORE_KING, " ");
			else req.setAttribute(REQ_KEY_SCORE_KING, 
					new Integer(king).toString());
			if(solder<0) req.setAttribute(REQ_KEY_SCORE_SOLDER, " ");
			else req.setAttribute(REQ_KEY_SCORE_SOLDER, 
					new Integer(solder).toString());
			if(scholar<0) req.setAttribute(REQ_KEY_SCORE_SCHOLAR, " ");
			else req.setAttribute(REQ_KEY_SCORE_SCHOLAR, 
					new Integer(scholar).toString());
			if(craftsman<0) req.setAttribute(REQ_KEY_SCORE_CRAFTSMAN, " ");
			else req.setAttribute(REQ_KEY_SCORE_CRAFTSMAN, 
					new Integer(craftsman).toString());

			req.setAttribute(REQ_KEY_ANSWERS_TROPISM, 
					con.getTropismAnswers());
			req.setAttribute(REQ_KEY_ANSWERS_KINGSOLD, 
					con.getKingOrSolderAnswers());
			req.setAttribute(REQ_KEY_ANSWERS_SCHLCRFT, 
					con.getScholarOrCraftsmanAnswers());

			// for hidden
			Map<String, String> hiddens = con.getHiddenMap();
			hiddens.put(REQ_KEY_TESTKEY, testKey);
			hiddens.put(REQ_ATTRKEY_RESULT, con.getResult());

			req.setAttribute(
				REQ_ATTRKEY_HIDDENMAP, 
				hiddens);

			Count c = con.store();
			req.setAttribute(REQ_ATTRKEY_COUNT, c);

			sendRedirect(JSP_SUMMARY, req, resp);
		}catch(Exception e){
			info(getClass().getName(), "SummaryServlet実行中の例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
}
