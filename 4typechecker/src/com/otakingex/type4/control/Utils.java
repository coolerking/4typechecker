package com.otakingex.type4.control;

import java.util.Iterator;
import java.util.Map;

public class Utils {
	public static boolean isExistsKey(Map<String, String> params, String str){
		if(params==null) return false;
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key!=null && key.contains(str)) return true;
		}
		return false;
	}
	
	protected static String fillZero(int target, int length){
		String tgt = new Integer(target).toString();
		if(length<0 || tgt.length() >= length){
			return tgt;
		}else{
			StringBuffer buf = new StringBuffer();
			int total = length - tgt.length();
			for(int i=0;i<total;i++){
				buf.append("0");
			}
			buf.append(tgt);
			return buf.toString();
		}
	}
}
