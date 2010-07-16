package com.otakingex.type4.model;

import java.io.Serializable;
import java.util.List;

public class Question implements Comparable<Question>, Serializable {

	private static final long serialVersionUID = -3028548285462980195L;

	private String label = null;
	private String statement = null;
	private String requestKey = null;
	private List<String> answers = null;

	public String getLabel(){ return label; }
	public String getRequestKey(){ return requestKey; }
	public String getStatement(){ return statement; }
	public List<String> getAnswers(){ return answers; }
	
	public void setLabel(String label){ this.label = label; }
	public void setRequestKey(String requestKey){ this.requestKey = requestKey; }
	public void setStatement(String statement){ this.statement = statement; }
	public void setAnswers(List<String> answers){ this.answers = answers; }
	
	@Override
	public int compareTo(Question q){
		if(label!=null) return label.compareTo(q.getLabel());
		else return hashCode() - q.hashCode();
	}
}
