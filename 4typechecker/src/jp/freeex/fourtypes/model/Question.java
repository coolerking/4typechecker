package jp.freeex.fourtypes.model;

import java.io.Serializable;
import java.util.List;

import jp.freeex.fourtypes.control.Utils;

public class Question implements Comparable<Question>, Serializable {

	private static final long serialVersionUID = -3028548285462980195L;
	
	
	private int id = -1;
	private int order = -1;
//	private String label = null;
//	private String testMasterClassName = null;
//	private String testType = null;
	private String statement = null;
//	private String requestKey = null;
	private List<String> answers = null;
	private boolean forward = true;

	public int getId(){ return id; }
	public int getOrder(){ return order; }
	public String getLabel(){
		return "Q" + Utils.fillZero(order+1, 3);
	}
//	public String getTestMasterClassName(){ return testMasterClassName; }
//	public String getTestType(){ return testType; }
//	public String getRequestKey(){ return requestKey; }
	public String getStatement(){ return statement; }
	public List<String> getAnswers(){ return answers; }
	public boolean isForward(){ return forward; }
	
	public void setId(int id){ this.id = id; }
	public void setOrder(int order){ this.order = order; }
//	public void setLabel(String label){ this.label = label; }
//	public void setTestMasterClassName(String testMasterClassName){ this.testMasterClassName = testMasterClassName; }
//	public void setTestType(String testType){ this.testType = testType; }
//	public void setRequestKey(String requestKey){ this.requestKey = requestKey; }
	public void setStatement(String statement){ this.statement = statement; }
	public void setAnswers(List<String> answers){ this.answers = answers; }
	public void setForward(boolean forward){ this.forward = forward; }
	
	@Override
	public int compareTo(Question q){
		return order - q.getOrder();
	}
	

}
