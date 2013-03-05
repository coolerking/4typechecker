package jp.freeex.fourtypes.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * サーバ、クライアント両方で使用可能なユーティリティクラス。
 * @author tasuku
 */
public class Utils implements Const{

	/**
	 * 注目型かどうか判別する
	 * @param x X座標
	 * @param y Y座標
	 * @return boolean 真：注目型、偽：以外
	 */
	public static boolean isKing(int x, int y){
		return (y>0 && x<0);
	}
	/**
	 * 司令型かどうか判別する
	 * @param x X座標
	 * @param y Y座標
	 * @return boolean 真：司令型、偽：以外
	 */
	public static boolean isSolder(int x, int y){
		return (y>0 && x>0);
	}
	/**
	 * 法則型かどうか判別する
	 * @param x X座標
	 * @param y Y座標
	 * @return boolean 真：法則型、偽：以外
	 */
	public static boolean isScholar(int x, int y){
		return (y<0 && x>0);
	}
	public static boolean isCraftsman(int x, int y){
		return (y<0 && x<0);
	}
	/**
	 * タイプを取得する
	 * @param firstScore　向性テストスコア
	 * @param secondScore 注目/司令テストor法則/理想テストスコア
	 * @return タイプ
	 */
	public static String getType(int firstScore, int secondScore){
		int x = secondScore;
		int y = firstScore;
		if(y>0){
			if(x<0) return TYPE_KING;
			else if(x>0) return TYPE_SOLD;
			else return TYPE_EXTR;
		}else if(y<0){
			if(x>0) return TYPE_SCHO;
			else if(x<0) return TYPE_CRFT;
			else return TYPE_INTR;
		}else{
			if(x>0) return TYPE_REAL;
			else if(x<0) return TYPE_ABST;
			else return TYPE_ZERO;
		}
	}
	/**
	 * タイプ色を取得する
	 * @param firstScore　向性テストスコア
	 * @param secondScore 注目/司令テストor法則/理想テストスコア
	 * @return タイプ色
	 */
	public static String getTypeColor(int firstScore, int secondScore){
		int x = secondScore;
		int y = firstScore;
		if(y>0){
			if(x<0) return TYPECOLOR_KING;
			else if(x>0) return TYPECOLOR_SOLD;
			else return TYPECOLOR_OTHR;
		}else if(y<0){
			if(x>0) return TYPECOLOR_SCHO;
			else if(x<0) return TYPECOLOR_CRFT;
			else return TYPECOLOR_OTHR;
		}else{
			return TYPECOLOR_OTHR;
		}
	}

	/**
	 * サマリを加算する。
	 * @param x X座標
	 * @param y Y座標
	 * @param evaluatedAt 評価日時
	 * @param sum 加算前のサマリ
	 * @return 加算後のサマリ
	 */
	public static Summary add(int x, int y, Date evaluatedAt, Summary sum){
		if(sum==null) sum = new Summary();
		sum.setTotal(sum.getTotal()+1);
		if(Utils.isKing(x, y)) sum.setKing(sum.getKing()+1);
		else if(Utils.isSolder(x, y)) sum.setSolder(sum.getSolder()+1);
		else if(Utils.isScholar(x, y)) sum.setScholar(sum.getScholar()+1);
		else if(Utils.isCraftsman(x, y)) sum.setCraftsman(sum.getCraftsman()+1);
		return sum;
	}

	/**
	 * セパレータ文字列で分割する。
	 * @param separator セパレータ文字列
	 * @param original 対象文字列
	 * @return　分割された結果
	 */
	public static List<String> split2List(String separator, String original){
		List<String> ret = new ArrayList<String>();
		String org = new String(original);
		while(true){
			int pos = org.indexOf(separator);
			if(pos<0){
				ret.add(org);
				break;
			}
			String element = org.substring(0, pos);
			org = org.substring(pos+separator.length());
			ret.add(element);
		}
		return ret;
	}

	/**
	 * セパレータ文字列で分割する。
	 * @param separator セパレータ文字列
	 * @param original 対象文字列
	 * @return　分割された結果
	 */
	public static String[] split2Array(String separator, String original){
		List<String> elements = Utils.split2List(separator, original);
		String[] ret = new String[elements.size()];
		for(int pos=0; pos<ret.length;pos++){
			ret[pos] = elements.get(pos);
		}
		return ret;
	}
}
