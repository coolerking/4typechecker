package jp.freeex.fourtypes.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 結果群をあらわすクラス。
 * 通信には使用できない。
 * @author tasuku
 */
public class Results implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -8166602905682328440L;

	/**
	 * 文字列化時使用する：セパレータ
	 */
	public static final String SEP_COORDINATES = ",";
	/**
	 * 文字列化時使用する：要素セパレータ
	 */
	public static final String SEP_ELEMENTS = ";";
	/**
	 * 結果を格納するリスト
	 */
	private List<long[]> results = new ArrayList<long[]>();
	
	/**
	 * デフォルトコンストラクタ
	 */
	public Results(){}
	/**
	 * 結果を表す文字列を元にインスタンス化するコンストラクタ
	 * @param resultsStr　結果を表す文字列
	 */
	public Results(String resultsStr){
		results = Results.convertList(resultsStr);
	}
	/**
	 * 結果を表す配列リストを元にインスタンス化するコンストラクタ
	 * @param results 結果を表す配列リスト
	 */
	public void setResults(List<long[]> results){
		this.results = results;
	}

	/**
	 * 結果をあらわす配列リストを取得する
	 * @return　結果をあらわす配列リスト
	 */
	public List<long[]> getResults(){
		return results;
	}
	/**
	 * 結果をあらわす配列リストを格納する
	 * @param　結果をあらわす配列リスト
	 */
	public void add(int x, int y, Date evaluatedAt){
		long[] result = new long[3];
		result[0] = (long)x;
		result[1] = (long)y;
		result[2] = evaluatedAt.getTime();
		results.add(result);
	}

	/**
	 * 結果をあらわすResultリストを取得する
	 * @return　結果をあらわすResultリスト
	 */
	public List<Result> getResultList(){
		List<Result> ret = new ArrayList<Result>();
		for(long[] result: results){
			Date evaluatedAt = new Date();
			evaluatedAt.setTime(result[2]);
			ret.add(new Result((int)result[0], (int)result[1], evaluatedAt));
		}
		return ret;
	}
	/**
	 * 結果の件数を取得する。
	 * @return 結果の件数
	 */
	public int size(){
		return results.size();
	}
	/**
	 * 指定した位置のResultを取得する
	 * @param pos 指定位置
	 * @return Resultインスタンス
	 */
	public Result getResult(int pos){
		long[] result = results.get(pos);
		Date evaluatedAt = new Date();
		evaluatedAt.setTime(result[2]);
		return new Result((int)result[0], (int)result[1], evaluatedAt);
	}
	/**
	 * 結果を表す文字列を配列リスト化する。
	 * @param resultsStr 結果を表す文字列
	 * @return 配列リスト
	 */
	public static List<long[]> convertList(String resultsStr){
		List<long[]> rets = new ArrayList<long[]>();
		List<String> coords = Utils.split2List(SEP_ELEMENTS, resultsStr);
		for(String coord: coords){
			String[] e = Utils.split2Array(SEP_COORDINATES, coord);
			long[] ret = new long[3];
			ret[0] = Long.parseLong(e[0]);
			ret[1] = Long.parseLong(e[1]);
			ret[2] = Long.parseLong(e[2]);
			rets.add(ret);
		}
		return rets;
	}
	/**
	 * 結果を表す文字列を作成する。
	 * @return 結果を表す文字列
	 */
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("");
		for(long[] result: results){
			if(buf.length()>0) buf.append(SEP_ELEMENTS);
			buf.append(result[0]).append(SEP_COORDINATES);
			buf.append(result[1]).append(SEP_COORDINATES);
			buf.append(result[2]);
		}
		return buf.toString();
	}
}
