package jp.freeex.fourtypes.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Utils {
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
	

	public static final String TYPE_KING = "注目型";
	public static final String TYPE_SOLD = "司令型";
	public static final String TYPE_SCHO = "法則型";
	public static final String TYPE_CRFT = "理想型";
	public static final String TYPE_EXTR = "外交的行動型(注目と司令の中間)";
	public static final String TYPE_INTR = "内向的行動型(法則と理想の中間)";
	public static final String TYPE_REAL = "現実的思考型(司令と法則の中間)";
	public static final String TYPE_ABST = "抽象的思考型(注目と理想の中間)";
	public static final String TYPE_ZERO = 
			"均衡型(外向/内向性と現実/理想的思考が均衡)";
	
	public static final String TYPECOLOR_KING = "yellow";
	public static final String TYPECOLOR_SOLD = "blue";
	public static final String TYPECOLOR_SCHO = "green";
	public static final String TYPECOLOR_CRFT = "red";
	public static final String TYPECOLOR_OTHR = "white";
	
	public static final String COLOR_WHITE = TYPECOLOR_OTHR;
	public static final String COLOR_BLACK = "black";
	public static final String COLOR_GRAY = "rgb(91, 91, 91)";
	
	public static final String FONTTYPE_TYPE = "18px 'ＭＳ　Ｐゴシック'";
	public static final String FONTTYPE_NUM = "9px 'ＭＳ　Ｐゴシック'";

	public static boolean isKing(int x, int y){
		return (y>0 && x<0);
	}
	public static boolean isSolder(int x, int y){
		return (y>0 && x>0);
	}
	public static boolean isScholar(int x, int y){
		return (y<0 && x>0);
	}
	public static boolean isCraftsman(int x, int y){
		return (y<0 && x<0);
	}
	
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

	public static Summary add(int x, int y, Date evaluatedAt, Summary sum){
		if(sum==null) sum = new Summary();
		sum.setTotal(sum.getTotal()+1);
		if(Utils.isKing(x, y)) sum.setKing(sum.getKing()+1);
		else if(Utils.isSolder(x, y)) sum.setSolder(sum.getSolder()+1);
		else if(Utils.isScholar(x, y)) sum.setScholar(sum.getScholar()+1);
		else if(Utils.isCraftsman(x, y)) sum.setCraftsman(sum.getCraftsman()+1);
		return sum;
	}

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

	public static String[] split2Array(String separator, String original){
		List<String> elements = Utils.split2List(separator, original);
		String[] ret = new String[elements.size()];
		for(int pos=0; pos<ret.length;pos++){
			ret[pos] = elements.get(pos);
		}
		return ret;
	}
}
