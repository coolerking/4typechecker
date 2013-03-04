package jp.freeex.fourtypes.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Results implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8166602905682328440L;
	
	public static final String SEP_COORDINATES = ",";
	public static final String SEP_ELEMENTS = ";";
	
	private List<long[]> results = new ArrayList<long[]>();
	
	public Results(){}
	
	public Results(String resultsStr){
		results = Results.convertList(resultsStr);
	}
	
	public void setResults(List<long[]> results){
		this.results = results;
	}

	public List<long[]> getResults(){
		return results;
	}

	public void add(int x, int y, Date evaluatedAt){
		long[] result = new long[3];
		result[0] = (long)x;
		result[1] = (long)y;
		result[2] = evaluatedAt.getTime();
		results.add(result);
	}

	public List<Result> getResultList(){
		List<Result> ret = new ArrayList<Result>();
		for(long[] result: results){
			Date evaluatedAt = new Date();
			evaluatedAt.setTime(result[2]);
			ret.add(new Result((int)result[0], (int)result[1], evaluatedAt));
		}
		return ret;
	}
	
	public int size(){
		return results.size();
	}
	
	public Result getResult(int pos){
		long[] result = results.get(pos);
		Date evaluatedAt = new Date();
		evaluatedAt.setTime(result[2]);
		return new Result((int)result[0], (int)result[1], evaluatedAt);
	}
	
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
