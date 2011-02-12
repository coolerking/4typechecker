package com.otakingex.type4.control;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import com.otakingex.type4.ViewConstants;

public class Utils implements ViewConstants{
	public static Map<String, String> getContainsMap(Map<String, String> params, String prefix, String key){
		Map<String, String> result = new HashMap<String, String>();
		if(params==null || prefix==null || key==null) return result;
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String name = it.next();
			if(name==null || (!name.startsWith(prefix)) || (!name.contains(key))) continue;
			String value = params.get(name);
			if(value==null) continue;
			result.put(name, value);
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getParameters(HttpServletRequest req){
		Map<String, String> params = new HashMap<String, String>();
		Enumeration<String> e = req.getParameterNames();
		while(e.hasMoreElements()){
			String name = e.nextElement();
			String value = req.getParameter(name);
			params.put(name, value);
		}		
		return params;
	}
	
	
	public static String getRequestKey(String testType, int id, int order, boolean forward){
		if(testType==null || id<0 || order<0) return null;
		StringBuffer key = new StringBuffer();
		key.append(REQ_KEY_QUESTION_PREFIX);
		key.append(REQ_KEY_QUESTION_SEP);
		key.append(testType);
		key.append(REQ_KEY_QUESTION_SEP);
		key.append(Utils.fillZero(order, 3));
		key.append(REQ_KEY_QUESTION_SEP);
		key.append(Utils.fillZero(id, 3));
		key.append(REQ_KEY_QUESTION_SEP);
		if(forward) key.append(REQ_KEY_QUESTION_FWD_SUFFIX);
		else key.append(REQ_KEY_QUESTION_REW_SUFFIX);
		return key.toString();
	}
	
	
	public static boolean isExistsKey(Map<String, String> params, String str){
		if(params==null) return false;
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key!=null && key.contains(str)) return true;
		}
		return false;
	}
	
	public static String fillZero(int target, int length){
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
	
	protected static int[] createOrderArray(int length){
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
	
	private static class Num implements Comparable<Num>{
		private int number;
		
		Num(int number){
			this.number = number;
		}

		@Override
		public int compareTo(Num arg0) {
			if(Utils.getRandomBoolean()) return -1;
			return 1;
		}
	}
	
	protected static boolean getRandomBoolean(){
		Calendar cal = Calendar.getInstance();
		int val = cal.get(Calendar.DAY_OF_YEAR);
		val += cal.get(Calendar.YEAR);
		val += cal.get(Calendar.MONTH);
		val += cal.get(Calendar.DATE);
		val += cal.get(Calendar.HOUR_OF_DAY);
		val += cal.get(Calendar.MINUTE);
		val += cal.get(Calendar.MILLISECOND);
		val = new Random().nextInt(val);
		val++;
		if(val<0) val *= -1;
		return (val % 2)==1;
	}
	
    public static String now(){
        StringBuffer buf = new StringBuffer();
        Calendar cal = Calendar.getInstance();
		TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
		cal.setTimeZone(tz);
        buf.append(cal.get(Calendar.YEAR));
        buf.append("/");
        buf.append(cal.get(Calendar.MONTH) + 1);
        buf.append("/");
        buf.append(cal.get(Calendar.DATE));
        buf.append(" ");
        buf.append(cal.get(Calendar.HOUR_OF_DAY));
        buf.append(":");
        buf.append(cal.get(Calendar.MINUTE));
        buf.append(":");
        buf.append(cal.get(Calendar.SECOND));
        return buf.toString();
    }

    public static String omitZero(String num){
        if(num==null || "".equals(num)) return num;
        String org = num.trim();
        StringBuffer buf = new StringBuffer();
        boolean omit = true;
        for(int pos=0; pos<org.length();pos++){
                char c = org.charAt(pos);
                if('0'==c){
                        if(!omit) buf.append(c);
                }else{
                        omit = false;
                        buf.append(c);
                }
        }
        if(buf.length()==0) return "0";
        return buf.toString();
	}
	public static String omitTag(String org){
        if(org==null || "".equals(org)) return org;
        String org2 = org.trim();
        StringBuffer buf = new StringBuffer();
        boolean omit = false;
        for(int pos=0; pos<org2.length();pos++){
                char c = org2.charAt(pos);
                if('<' == c || '>' == c){
                        omit = (!omit);
                        continue;
                } 
                if(!omit) buf.append(c);
        }
        return buf.toString();
	}
	
	public static String getDate(Date date){
		StringBuffer buf = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
		cal.setTimeZone(tz);
        buf.append(cal.get(Calendar.YEAR));
        buf.append("/");
        buf.append(cal.get(Calendar.MONTH) + 1);
        buf.append("/");
        buf.append(cal.get(Calendar.DATE));
        buf.append(" ");
        buf.append(cal.get(Calendar.HOUR_OF_DAY));
        buf.append(":");
        buf.append(cal.get(Calendar.MINUTE));
        buf.append(":");
        buf.append(cal.get(Calendar.SECOND));
		return buf.toString();
	}
}
