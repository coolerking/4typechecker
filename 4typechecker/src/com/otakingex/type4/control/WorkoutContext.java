package com.otakingex.type4.control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.otakingex.type4.ViewConstants;
import com.otakingex.type4.model.Question;
import com.otakingex.type4.model.User;

public class WorkoutContext implements ViewConstants{
	
	private Map<String, String> params = null;
	
	public WorkoutContext(Map<String, String> params){
		this.params = params;
	}
	
	public User getUser(){
		User user = new User();
		user.setName(params.get(REQ_KEY_NAME));
		try{
			user.setAge(Integer.parseInt(params.get(REQ_KEY_AGE)));
		}catch(RuntimeException e){}
		user.setMale(REQ_VALUE_SEX_MALE.equals(params.get(REQ_KEY_SEX)));
		return user;
	}
	
	public String getSubTitle(){
		if(needTropismQuestions()){
			return SUBTITLE_TROPISM;
		}else{
			if(isKingOrSold()){
				return SUBTITLE_KINGSOLD;
			}else if(isSchlOrCrft()){
				return SUBTITLE_SCHLCRFT;
			}else{
				return SUBTITLE_UNKNOWN;
			}
		}
	}
	
	public List<Question> getTropismQuestions(){
		if(needTropismQuestions()){
			
		}
		return new LinkedList<Question>();
	}
	
	public List<Question> getKingOrSolderQuestions(){
		if(needKingSoldQuestions()){
			
		}
		return new LinkedList<Question>();
	}
	
	public List<Question> getScolarOrCraftsmanQuestions(){
		if(needSchlCrftQuestions()){
			
		}
		return new LinkedList<Question>();
	}

	protected boolean needTropismQuestions(){
		return Utils.isExistsKey(params, REQ_KEY_QUESTION_TEST1);
	}

	protected boolean needKingSoldQuestions(){
		return Utils.isExistsKey(params, REQ_KEY_QUESTION_TEST2);
	}

	protected boolean needSchlCrftQuestions(){
		return Utils.isExistsKey(params, REQ_KEY_QUESTION_TEST3);
	}
	
	protected boolean isKingOrSold(){
		if(needTropismQuestions()) return false;
		//TODO 集計して結果どちらかを判定する
		return false;
	}
	
	protected boolean isSchlOrCrft(){
		if(needTropismQuestions()) return false;
		//TODO 集計して結果どちらかを判定する
		return false;
	}

	public Map<String, String> getHiddenMap(){
		Map<String, String> result = new HashMap<String, String>();
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key==null) continue;
			String value = params.get(key);
			if(value==null) continue;
			result.put(key, value);
		}
		return result;
	}
}
