package com.otakingex.type4;

public interface ViewConstants {
	// JSP
	public static final String JSP_ENTRY = "/01entry.jsp";
	public static final String JSP_WORKOUT = "/02workout.jsp";
	public static final String JSP_OPTION = "/03option.jsp";
	public static final String JSP_SUMMARY = "/10summary.jsp";
	public static final String JSP_ERROR = "/99error.jsp";
	
	// HTTP Request Attr
	public static final String REQ_ATTRKEY_USER = "User";
	public static final String REQ_ATTRKEY_SUBTITLE = "SUBTITLE";
	public static final String REQ_ATTRKEY_HIDDENMAP = "HIDDENMAP";
	
	// HTTP Request Key
	
	// entry page
	public static final String REQ_KEY_NAME = "NAME";
	public static final String REQ_KEY_AGE = "AGE";
	public static final String REQ_KEY_SEX = "SEX";
	public static final String REQ_VALUE_SEX_MALE = "MALE";
	public static final String REQ_VALUE_SEX_FEMALE = "FEMALE";
	
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
	public static final String SUBTITLE_KINGSOLD = "王様・軍人テスト";
	public static final String SUBTITLE_SCHLCRFT = "学者・職人テスト";
	public static final String SUBTITLE_UNKNOWN = "王様・軍人/学者・職人テスト";
	
	
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
}
