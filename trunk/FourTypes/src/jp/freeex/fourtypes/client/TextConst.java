package jp.freeex.fourtypes.client;

import jp.freeex.fourtypes.shared.Utils;

public interface TextConst {
	public static final String SUBT_TOP = "トップページ";
	public static final String SUBT_1ST = "向性テスト (1/2)";
	public static final String SUBT_2ND_EXT = Utils.TYPE_KING + "・" + 
			Utils.TYPE_SOLD + "テスト (2/2)";
	public static final String SUBT_2ND_INT = Utils.TYPE_SCHO + "・" + 
			Utils.TYPE_CRFT + "テスト (2/2";
	public static final String SUBT_RESULT_PREFIX = "あなたは";
	public static final String SUBT_RESULT_SUFFIX = "です。";
	
	public static final String DIRC_TOP = "開始ボタンを押して下さい。";
	public static final String DIRC_1ST = "以下の設問に回答し、次へボタンを押して下さい。";
	public static final String DIRC_2ND = "以下の設問に回答し、次へボタンを押して下さい。";
	public static final String DIRC_2ND_EXT = DIRC_2ND;
	public static final String DIRC_2ND_INT = DIRC_2ND;
	
	public static final String DIRC_RESULT_PREFIX = "詳しい説明は";
	public static final String DIRC_RESULT_BODY = "こちら";
	public static final String DIRC_RESULT_SUFFIX = "を参照して下さい。";
	
	public static final String BUT_START = "開始";
	public static final String BUT_NEXT = "次へ";
	public static final String BUT_CLEAR = "クリア";
	public static final String BUT_RETRY = "再実行";
	
	public static final String ANS_UPPER = "↑";
	public static final String ANS_MIDDLE = "とちらともいえない。";
	public static final String ANS_LOWER = "↓";
	
	public static final String MSG_1ST_EVEN =
			"向性が均衡しています。\nすみませんが1問だけ回答を変更し、再度次へボタンを押して下さい。";
	public static final String MSG_2ND_EVEN =
			"特性が均衡しています。\nすみませんが1問だけ回答を変更し、再度次へボタンを押して下さい。";
}
