﻿package jp.freeex.fourtypes;

import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.freeex.fourtypes.control.Utils;
import jp.freeex.fourtypes.control.WorkoutContext;
import jp.freeex.fourtypes.model.Question;

public class OptionWorkoutServlet extends BaseServlet {

	private static final long serialVersionUID = -2485056706970305246L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		sendRedirect(JSP_NOLINK, req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		try{
			WorkoutContext con = 
					new WorkoutContext(Utils.getParameters(req));
			String testKey = con.getTestKey();

			if(con.getKingScore()<0){
				// 王様・軍人テスト
				req.setAttribute(REQ_ATTRKEY_SUBTITLE, SUBTITLE_KINGSOLD);
				req.setAttribute(
					REQ_ATTRKEY_KINGSOLD_LIST, 
					con.getKingOrSolderQuestions(testKey));
				req.setAttribute(
					REQ_ATTRKEY_SCHLCRFT_LIST, 
					new LinkedList<Question>());
			}else{
				// 学者・職人テスト
				req.setAttribute(REQ_ATTRKEY_SUBTITLE, SUBTITLE_SCHLCRFT);
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
			info(getClass().getName(), "SummaryServlet実行中の例外", e);
			sendRedirect(JSP_ERROR, req, resp);
		}
	}
}
