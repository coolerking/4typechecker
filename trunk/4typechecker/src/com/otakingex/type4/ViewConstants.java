package com.otakingex.type4;
/**
 * ViewConstants
 * 定数をまとめたインターフェイス。
 * @author Tasuku
 */
public interface ViewConstants {
	// URL
	/*
	public static final String URL_TOP = "http://www.amazon.co.jp/exec/obidos/ASIN/4023308838/otakingex01-22/";
	public static final String URL_KING = "http://otaking-ex.jp/wp/?p=14334";
	public static final String URL_SOLD = "http://otaking-ex.jp/wp/?p=14351";
	public static final String URL_SCHL = "http://otaking-ex.jp/wp/?p=14358";
	public static final String URL_CRFT = "http://otaking-ex.jp/wp/?p=14371";
	*/
	public static final String URL_TOP = "http://on.fb.me/ebpNKn";
	public static final String URL_KING = "http://on.fb.me/gHm7Q9";
	public static final String URL_SOLD = "http://on.fb.me/e28Uc3";
	public static final String URL_SCHL = "http://on.fb.me/ez9B77";
	public static final String URL_CRFT = "http://on.fb.me/f0nlQj";

	// JSP
	public static final String JSP_ENTRY = "/01entry.jsp";
	public static final String JSP_WORKOUT = "/02workout.jsp";
	public static final String JSP_OPTION = "/03option.jsp";
	public static final String JSP_SUMMARY = "/10summary.jsp";
	public static final String JSP_THANKS = "/22thanks.html";
	public static final String JSP_STATUS = "/80status.jsp";
	public static final String JSP_ERROR = "/99error.jsp";
	public static final String JSP_NOLINK = "/98error.jsp";
	
	// HTTP Request Attr
	public static final String REQ_ATTRKEY_USER = "User";
	public static final String REQ_ATTRKEY_SUBTITLE = "SUBTITLE";
	public static final String REQ_ATTRKEY_HIDDENMAP = "HIDDENMAP";
	public static final String REQ_ATTRKEY_COUNT = "COUNT";
	
	// HTTP Request Key
	
	// entry page
	public static final String REQ_KEY_NAME = "NAME";
	public static final String REQ_KEY_AGE = "AGE";
	public static final String REQ_KEY_SEX = "SEX";
	public static final String REQ_VALUE_SEX_MALE = "MALE";
	public static final String REQ_VALUE_SEX_FEMALE = "FEMALE";
	
	// status page
	public static final String REQ_KEY_COUNT = "COUNT";
	
	// workout page
	

	// Request key
	public static final String REQ_ATTRKEY_TROPISM_LIST = "TROPISM_LIST";
	public static final String REQ_ATTRKEY_KINGSOLD_LIST = "KINGSOLD_LIST";
	public static final String REQ_ATTRKEY_SCHLCRFT_LIST = "SCHLCRFT_LIST";
	
	public static final String REQ_KEY_TESTKEY = "TESTKEY";
	public static final String REQ_ATTRKEY_SENDSUMMARY = "IS_SEND_SUMMARY";
	
	public static final String REQ_ATTRKEY_RESULT = "RESULT";
	
	// Qxxx_TEST1_xxx_A
	public static final String REQ_KEY_QUESTION_PREFIX = "Q";
	public static final String REQ_KEY_QUESTION_SEP = "_";
	public static final int REQ_KEY_ID_LENGTH = 3;
	public static final int REQ_KEY_ORDER_LENGTH = 3;
	public static final String REQ_KEY_QUESTION_TEST1 = "TEST1";
	public static final String REQ_KEY_QUESTION_TEST2 = "TEST2";
	public static final String REQ_KEY_QUESTION_TEST3 = "TEST3";
	public static final String REQ_KEY_QUESTION_FWD_SUFFIX = "A";
	public static final String REQ_KEY_QUESTION_REW_SUFFIX = "B";
	
	
	public static final String SUBTITLE_TROPISM = "向性テスト";
	public static final String SUBTITLE_KINGSOLD = "<a href=\"" + URL_KING + "\">注目型</a>・<a href=\"" + URL_SOLD + "\">司令型</a>テスト";
	public static final String SUBTITLE_SCHLCRFT = "<a href=\"" + URL_SCHL + "\">法則型</a>・<a href=\"" + URL_CRFT + "\">理想型</a>テスト";
	public static final String SUBTITLE_UNKNOWN = "<a href=\"" + URL_KING + "\">注目</a>・<a href=\"" + URL_SOLD + "\">司令型</a>/<a href=\"" + URL_SCHL + "\">法則</a>・<a href=\"" + URL_CRFT + "\">理想型</a>テスト";
	
	
 	public static final String TESTTYPE_TROPISM = REQ_KEY_QUESTION_TEST1;
	public static final String TESTTYPE_KINGSOLD = REQ_KEY_QUESTION_TEST2;
	public static final String TESTTYPE_SCHLCRFT = REQ_KEY_QUESTION_TEST2;
	
	public static final String TESTKEY_OUTER_SEP = "*";
	public static final String TESTKEY_MIDDLE_SEP = "=";
	public static final String TESTKEY_INNER_SEP = "-";
	
	public static final String SYSTEM_PROP_IS_STORE = "type4.store";
	public static final String SYSTEM_PROP_MAILADDRESS = "type4.store.mailaddress";
	
	public static final String REQ_KEY_SCORE_KINGSOLD = "SCORE_KINGSOLD";
	public static final String REQ_KEY_SCORE_SCHLCRFT = "SCORE_SCHLCRFT";
	public static final String REQ_KEY_SCORE_KING = "SCORE_KING";
	public static final String REQ_KEY_SCORE_SOLDER = "SCORE_SOLDER";
	public static final String REQ_KEY_SCORE_SCHOLAR = "SCORE_SCHOLAR";
	public static final String REQ_KEY_SCORE_CRAFTSMAN = "SCORE_CRAFTSMAN";
	
	public static final String REQ_KEY_ANSWERS_TROPISM = "ANSWERS_TROPISM";
	public static final String REQ_KEY_ANSWERS_KINGSOLD = "ANSWERS_KINGSOLD";
	public static final String REQ_KEY_ANSWERS_SCHLCRFT = "ANSWERS_SCHLCRFT";
	
	public static final String REQ_KEY_ESTIMATE = "ESTIMATE";
	public static final String REQ_VALUE_ESTIMATE_KING = "ESTIMATE_KING";
	public static final String REQ_VALUE_ESTIMATE_SOLDER = "ESTIMATE_SOLDER";
	public static final String REQ_VALUE_ESTIMATE_SCHOLAR = "ESTIMATE_SCHOLAR";
	public static final String REQ_VALUE_ESTIMATE_CRAFTSMAN = "ESTIMATE_CRAFTSMAN";
	public static final String REQ_VALUE_ESTIMATE_NOANSWER = "ESTIMATE_NOANSWER";
	
	public static final String LABEL_ANSWER_2 = "↑";
	public static final String LABEL_ANSWER_3 = "どっちともいえない";
	public static final String LABEL_ANSWER_4 = "↓";
	
	
	public static final String LABEL_RESULT_KING = "<a href=\""+ URL_KING + "\">注目型</a>";
	public static final String LABEL_RESULT_AND_KING = "<a href=\"" + URL_KING + "\">注目型</a>より";
	public static final String LABEL_RESULT_SOLD = "<a href=\""+ URL_SOLD + "\">司令型</a>";
	public static final String LABEL_RESULT_AND_SOLD = "<a href=\""+ URL_SOLD + "\">司令型</a>より";
	public static final String LABEL_RESULT_NOVECTOR = "向性不明で";
	public static final String LABEL_RESULT_KINGSOLD = "<a href=\""+ URL_KING + "\">注目型</a>と<a href=\""+ URL_SOLD + "\">司令型</a>の中間";
	public static final String LABEL_RESULT_KINGSOLD_AND = "<a href=\""+ URL_KING + "\">注目型</a>と<a href=\""+ URL_SOLD + "\">司令型</a>の中間で";
	public static final String LABEL_RESULT_SCHL = "<a href=\""+ URL_SCHL + "\">法則型</a>";
	public static final String LABEL_RESULT_CRFT = "<a href=\""+ URL_CRFT + "\">理想型</a>";
	public static final String LABEL_RESULT_AND_SCHL = "<a href=\""+ URL_SCHL + "\">法則型</a>より";
	public static final String LABEL_RESULT_AND_CRFT = "<a href=\""+ URL_CRFT + "\">理想型</a>より";
	public static final String LABEL_RESULT_SCHLCRFT = "<a href=\""+ URL_SCHL + "\">法則型</a>と<a href=\""+ URL_CRFT + "\">理想型</a>の中間";
	public static final String LABEL_RESULT_KINGSOLD_UNKNOWN = "<a href=\""+ URL_KING + "\">注目</a>・<a href=\""+ URL_SOLD + "\">司令型</a>判別不明で";
	public static final String LABEL_RESULT_SCHLCRFT_UNKNOWN = "<a href=\""+ URL_SCHL + "\">法則</a>・<a href=\""+ URL_CRFT + "\">理想型</a>判別不明";
	
	public static final String PRIMARYKEY_ID = "4TYPEcounter";
	
	public static final String REQ_KEY_RESULT_CODE = "RESULT_CODE";
}
