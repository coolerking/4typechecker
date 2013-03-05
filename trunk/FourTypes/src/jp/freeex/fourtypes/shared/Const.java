package jp.freeex.fourtypes.shared;
/**
 * 定数用インターフェイス。
 * @author tasuku
 */
public interface Const {
	/**
	 * HTML ID: サブタイトル
	 */
	public static final String HTMLID_SUBTITLE = "subtitle";
	/**
	 * HTML ID: 説明
	 */
	public static final String HTMLID_DIRECTION = "direction";
	/**
	 * HTML ID: 本文
	 */
	public static final String HTMLID_MAIN = "main";
	/**
	 * HTML ID: ボタン領域
	 */
	public static final String HTMLID_BUTTONS = "buttons";
	/**
	 * HTML ID: フッタ
	 */
	public static final String HTMLID_FOOTER = "footer";
	
	/**
	 * タイプ：注目型
	 */
	public static final String TYPE_KING = "注目型";
	/**
	 * タイプ：司令型
	 */
	public static final String TYPE_SOLD = "司令型";
	/**
	 * タイプ：法則型
	 */
	public static final String TYPE_SCHO = "法則型";
	/**
	 * タイプ：理想型
	 */
	public static final String TYPE_CRFT = "理想型";
	/**
	 * タイプ：外向的
	 */
	public static final String TYPE_EXTR = "外交的行動型(注目と司令の中間)";
	/**
	 * タイプ：内向的
	 */
	public static final String TYPE_INTR = "内向的行動型(法則と理想の中間)";
	/**
	 * タイプ：現実的
	 */
	public static final String TYPE_REAL = "現実的思考型(司令と法則の中間)";
	/**
	 * タイプ：抽象的
	 */
	public static final String TYPE_ABST = "抽象的思考型(注目と理想の中間)";
	/**
	 * タイプ：原点
	 */
	public static final String TYPE_ZERO = 
			"均衡型(外向/内向性と現実/理想的思考が均衡)";
	
	/**
	 * タイプ色；注目型
	 */
	public static final String TYPECOLOR_KING = "yellow";
	/**
	 * タイプ色；司令型
	 */
	public static final String TYPECOLOR_SOLD = "blue";
	/**
	 * タイプ色；法則型
	 */
	public static final String TYPECOLOR_SCHO = "green";
	/**
	 * タイプ色；理想型
	 */
	public static final String TYPECOLOR_CRFT = "red";
	/**
	 * タイプ色；その他
	 */
	public static final String TYPECOLOR_OTHR = "white";
	
	/**
	 * 白色
	 */
	public static final String COLOR_WHITE = TYPECOLOR_OTHR;
	/**
	 * 黒色
	 */
	public static final String COLOR_BLACK = "black";
	/**
	 * 灰色
	 */
	public static final String COLOR_GRAY = "rgb(91, 91, 91)";
	/**
	 * フォント18px
	 */
	public static final String FONTTYPE_TYPE = "18px 'ＭＳ　Ｐゴシック'";
	/**
	 * フォント9px
	 */
	public static final String FONTTYPE_NUM = "9px 'ＭＳ　Ｐゴシック'";
	
	/**
	 * ４タイプに関する説明ページ
	 */
	public static final String URL_BASE = "/About4types.html";
	/**
	 * アンケート用フォームページ
	 */
	public static final String URL_ENQ = "/Enquete.html";
	/**
	 * エラーページ
	 */
	public static final String URL_SORRY = "/Sorry.html";
	
	/**
	 * サービスURLサフィックス
	 */
	public static final String URL_SERVICE = "fourtypes/stat";
	/**
	 * name値：概要
	 */
	public static final String NAME_ABSTRACT = "#4-types";
	/**
	 * name値：注目型
	 */
	public static final String NAME_KING = "#king";
	/**
	 * name値：司令型
	 */
	public static final String NAME_SOLD = "#solder";
	/**
	 * name値：法則型
	 */
	public static final String NAME_SCHO = "#scholar";
	/**
	 * name値：理想型
	 */
	public static final String NAME_CRFT = "#craftsman";
	/**
	 * name値：２つの法則
	 */
	public static final String NAME_2LAWS = "#2-laws";
	/**
	 * name値：優位劣位の法則
	 */
	public static final String NAME_INFSUP = "#infsup";
	/**
	 * name値：対角線の法則
	 */
	public static final String NAME_DIAGONAL = "#diagonal";
	/**
	 * 向性テスト
	 */
	public static final String TBL_1ST = "向性テスト";
	/**
	 * 注目／司令テスト
	 */
	public static final String TBL_2ND_EXT = TYPE_KING + "・" + 
			Utils.TYPE_SOLD + "テスト";
	/**
	 * 法則／理想テスト
	 */
	public static final String TBL_2ND_INT = TYPE_SCHO + "・" + 
			Utils.TYPE_CRFT + "テスト";
	/**
	 * スコア
	 */
	public static final String TBL_SCORE = "スコア";
	/**
	 * X座標
	 */
 	public static final String TBL_COORD_X = " (X座標)";
 	/**
 	 * Y座標
 	 */
 	public static final String TBL_COORD_Y = " (Y座標)";

 	/**
 	 * トップページ
 	 */
	public static final String SUBT_TOP = "トップページ";
	/**
	 * 1ページ目テスト
	 */
	public static final String SUBT_1ST = TBL_1ST + " (1/2)";
	/**
	 * 2ページ目テスト（外向的）
	 */
	public static final String SUBT_2ND_EXT = TBL_2ND_EXT + " (2/2)";
	/**
	 * 2ページ目テスト（内向的）
	 */
	public static final String SUBT_2ND_INT = TBL_2ND_INT+ " (2/2)";
	
	/**
	 * 結果：プレフィックス
	 */
	public static final String SUBT_RESULT_PREFIX = "あなたは";
	/**
	 * 結果：サフィックス
	 */
	public static final String SUBT_RESULT_SUFFIX = "です。";
	
	/**
	 * 説明：トップページ
	 */
	public static final String DIRC_TOP = "開始ボタンを押して下さい。";
	/**
	 * 説明：１ページ目テスト
	 */
	public static final String DIRC_1ST = "以下の設問に回答し、次へボタンを押して下さい。";
	/**
	 * 説明：２ページ目テスト
	 */
	public static final String DIRC_2ND = "以下の設問に回答し、次へボタンを押して下さい。";
	/**
	 * 説明：2ページ目テスト（外向的）
	 */
	public static final String DIRC_2ND_EXT = DIRC_2ND;
	/**
	 * 説明：2ページ目テスト（内向的）
	 */
	public static final String DIRC_2ND_INT = DIRC_2ND;

	/**
	 * 説明；結果プレフィックス
	 */
	public static final String DIRC_RESULT_PREFIX = "詳しい説明は";
	/**
	 * 説明；リンク内文字列
	 */
	public static final String DIRC_RESULT_BODY = "こちら";
	/**
	 * 説明；結果サフィックス
	 */
	public static final String DIRC_RESULT_SUFFIX = "を参照して下さい。";
	
	/**
	 * ボタン：開始
	 */
	public static final String BUT_START = "開始";
	/**
	 * ボタン：次へ
	 */
	public static final String BUT_NEXT = "次へ";
	/**
	 * ボタン：クリア
	 */
	public static final String BUT_CLEAR = "クリア";
	/**
	 * ボタン：再実行
	 */
	public static final String BUT_RETRY = "再実行";
	
	/**
	 * 回答：２番め
	 */
	public static final String ANS_UPPER = "↑";
	/**
	 * 回答：３番め
	 */
	public static final String ANS_MIDDLE = "とちらともいえない。";
	/**
	 * 回答：４番め
	 */
	public static final String ANS_LOWER = "↓";
	
	/**
	 * メッセージ：１ページ目均衡
	 */
	public static final String MSG_1ST_EVEN =
			"向性が均衡しています。\nすみませんが1問だけ回答を変更し、再度次へボタンを押して下さい。";
	/**
	 * メッセージ：２ページ目均衡
	 */
	public static final String MSG_2ND_EVEN =
			"特性が均衡しています。\nすみませんが1問だけ回答を変更し、再度次へボタンを押して下さい。";
	
	/**
	 * メッセージ：アンケート
	 */
	public static final String FOT_ENG_MSG = "4タイプ判定テストのご意見をお聞かせください";
	
	/**
	 * ツールチップ：結果グラフサフィックス
	 */
	public static final String TTP_PREFIX = "(x, y)=(";
	/**
	 * ツールチップ：結果グラフセパレータ１
	 */
	public static final String TTP_SEP = ", ";
	/**
	 * ツールチップ：結果グラフセパレータ２
	 */
	public static final String TTP_X = ")\nX座標： ";
	/**
	 * ツールチップ：結果グラフサフィックス
	 */
	public static final String TTP_SUFFIX = "のスコア\nY座標： 向性テストのスコア\n\n" +
			"白色の点は最近評価した方の結果座標です。";
	
	/**
	 * ツールチップ：サマリ表
	 */
	public static final String TTP_SUMMARY = "これまで本サイトで判定した結果のサマリ";
	
	/**
	 * サマリ表：その他
	 */
	public static final String SUM_OTHERS = "その他";
	/**
	 * サマリ表：合計
	 */
	public static final String SUM_TOTAL = "合計";
}
