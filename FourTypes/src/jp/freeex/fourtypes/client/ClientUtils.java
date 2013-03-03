package jp.freeex.fourtypes.client;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeSet;

import jp.freeex.fourtypes.shared.Utils;

public class ClientUtils implements HTMLConst{
	/**
	 * 長さに合わせたint型配列を順番バラバラで格納して渡す。
	 * 各int値は競合せず、0からlength-1までの数字が格納される。
	 * @param length 長さ
	 * @return int[] 生成した数値型配列
	 */
	public static int[] createOrderArray(int length){
		if(length<=0) return new int[0];
		int[] orders = new int[length];
		TreeSet<Num> set = new TreeSet<Num>();
		for(int i=0;i<orders.length;i++){
			Num num = new Num(i);
			set.add(num);
		}
		Iterator<Num> it = set.iterator();
		int pos = 0;
		while(it.hasNext()){
			Num num = it.next();
			orders[pos++] = num.number;
		}
		
		return orders;
	}
	/**
	 * createOrderArray()内で順番をバラバラにするために使用したインナークラス。
	 * @author tasuku
	 */
	private static class Num implements Comparable<Num>{
		/**
		 * 数値
		 */
		private int number;
		/**
		 * コンストラクタ
		 * @param number 数値
		 */
		Num(int number){
			this.number = number;
		}

		/**
		 * 比較メソッド（数値返却）。
		 * このなかで乱数的にint値を変化させTree系コレクションのオーダーを狂わせる。
		 * @param arg0  比較対象
		 * @return int 比較結果
		 */
		@Override
		public int compareTo(Num arg0) {
			if(ClientUtils.getRandomBoolean()) return -1;
			return 1;
		}
		
		/**
		 * 比較メソッド。
		 * compareTo()を使った結果で評価を返す。
		 * @return boolean 真：同じ、偽：違う
		 */
		@Override
		public boolean equals(Object tgt){
			if(tgt==null||(!(tgt instanceof Num))) return false;
			return compareTo((Num)tgt)==0;
		}
	}

	/**
	 * ランダムにboolean値を返す。
	 * @return boolean 真偽値（ランダム）
	 */
	public static boolean getRandomBoolean(){
		Date now = new Date();
		int val = (int)(now.getTime()/12345L);
		val = new Random().nextInt(val);
		val++;
		if(val<0) val *= -1;
		return (val % 2)==1;
	}
	
	/**
	 * リンク付きタイプ名HTML文字列を取得する。リンク内文字列はタイプ名
	 * @param firstScore 向性テストスコア
	 * @param secondScore 現実的／抽象的思考テストスコア
	 * @return リンク付きタイプ名HTML文字列
	 */
	public static String getTypeWithLink(int firstScore, int secondScore){
		String type = Utils.getType(firstScore, secondScore);
		StringBuffer buf = new StringBuffer();
		buf.append("<a href=\"");
		buf.append(URL_BASE);
		if(Utils.TYPE_KING.equals(type)){
			buf.append(NAME_KING);
		}else if(Utils.TYPE_SOLD.equals(type)){
			buf.append(NAME_SOLD);
		}else if(Utils.TYPE_SCHO.equals(type)){
			buf.append(NAME_SCHO);
		}else if(Utils.TYPE_CRFT.equals(type)){
			buf.append(NAME_CRFT);
		}else{
			buf.append(NAME_ABSTRACT);
		}
		buf.append("\" target=\"_blank\">");
		buf.append(type);
		buf.append("</a>");
		return buf.toString();
	}
	
	/**
	 * リンク付きタイプ名HTML文字列を取得する。リンク内文字列を任意に指定可能。
	 * @param firstScore 向性テストスコア
	 * @param secondScore 現実的／抽象的思考テストスコア
	 * @param message リンク内文字列
	 * @return リンク付きタイプ名HTML文字列（リンク無い文字列はmessage値）
	 */
	public static String getTypeWithLink(int firstScore, int secondScore, 
			String message){
		String type = Utils.getType(firstScore, secondScore);
		StringBuffer buf = new StringBuffer();
		buf.append("<a href=\"");
		buf.append(URL_BASE);
		if(Utils.TYPE_KING.equals(type)){
			buf.append(NAME_KING);
		}else if(Utils.TYPE_SOLD.equals(type)){
			buf.append(NAME_SOLD);
		}else if(Utils.TYPE_SCHO.equals(type)){
			buf.append(NAME_SCHO);
		}else if(Utils.TYPE_CRFT.equals(type)){
			buf.append(NAME_CRFT);
		}else{
			buf.append(NAME_ABSTRACT);
		}
		buf.append("\" target=\"_blank\">");
		buf.append(message);
		buf.append("</a>");
		return buf.toString();
	}

	/**
	 * 統計チャートURL文字列を作成する。
	 * @param total 累積利用者数
	 * @param king 注目型人数
	 * @param solder 司令型人数
	 * @param scholar 法則型人数
	 * @param craftsman　理想型人数
	 * @return 統計チャートURL文字列（UTF-8)
	 * @throws UnsupportedEncodingException　非サポート文字列例外
	 */
	public static String getSummaryChart(int total, int king, int solder,
			int scholar, int craftsman) throws UnsupportedEncodingException{
		int kingPer = (int)(100.0F * ((float)king) / ((float)total));
		int soldPer = (int)(100.0F * ((float)solder) / ((float)total));
		int schoPer = (int)(100.0F * ((float)scholar) / ((float)total));
		int crftPer = (int)(100.0F * ((float)craftsman) / ((float)total));
		int othrPer = 100 - kingPer - soldPer - schoPer - crftPer;
		StringBuffer buf = new StringBuffer();
		buf.append("http://chart.apis.google.com/chart?cht=p3");
		buf.append("&amp;chco=0000FF");
		buf.append("&amp;chd=t:");
		buf.append(kingPer);
		buf.append(",");
		buf.append(soldPer);
		buf.append(",");
		buf.append(schoPer);
		buf.append(",");
		buf.append(crftPer);
		buf.append(",");
		buf.append(othrPer);
		buf.append("&amp;chs=500x250");
		buf.append("&amp;chf=bg,s,E6EAE9");
		buf.append("&amp;chl=");
		buf.append(ClientUtils.toUTF8(Utils.TYPE_KING));
		buf.append("|");
		buf.append(ClientUtils.toUTF8(Utils.TYPE_SOLD));
		buf.append("|");
		buf.append(ClientUtils.toUTF8(Utils.TYPE_SCHO));
		buf.append("|");
		buf.append(ClientUtils.toUTF8(Utils.TYPE_CRFT));
		buf.append("|");
		buf.append(ClientUtils.toUTF8("その他"));
		return buf.toString();
	}

	/**
	 * StringをUTF-8に変換する。
	 * @param org 変換元文字列
	 * @return UTF-8変換後文字列
	 * @throws UnsupportedEncodingException　非サポート文字列例外
	 */
	public static String toUTF8(String org) throws UnsupportedEncodingException{
		byte[] utf8 = org.getBytes("UTF-8");
		return new String(utf8, "UTF-8");
	}

	/**
	 * 4タイプアンケートページへのリンク文字列を取得する。
	 * @return 4タイプアンケートページへのリンク文字列
	 */
	public static String getAboutFourType(){
		StringBuffer buf = new StringBuffer();
		buf.append("<a href=/");
		buf.append(URL_ENQ);
		buf.append("\" target=\"_blank\">");
		buf.append("4タイプ判定テストのご意見をお聞かせください");
		buf.append("</a>");
		return buf.toString();
	}

	/**
	 * 各タイプ説明リンク文字列を取得する。
	 * @return 各タイプ説明リンク文字列
	 */
	public static String getAboutEachTypes(){
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		buf.append(ClientUtils.getTypeWithLink(1, -1));
		buf.append("]");
		buf.append("[");
		buf.append(ClientUtils.getTypeWithLink(1, 1));
		buf.append("]");
		buf.append("[");
		buf.append(ClientUtils.getTypeWithLink(-1, 1));
		buf.append("]");
		buf.append("[");
		buf.append(ClientUtils.getTypeWithLink(-1, -1));
		buf.append("]");
		return buf.toString();
	}

	/**
	 * 改行文字列を取得する。
	 * @return 改行文字列。
	 */
	public static String getNewLine(){
		return "<br />";
	}

}
