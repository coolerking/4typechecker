package jp.freeex.fourtypes.model;

public class Answer {
	private String label;
	private String statement;
	private int fwdScore = -1;
	private int rewScore = -1;
	
	public String getLabel(){ return label; }
	public String getStatement(){ return statement; }
	public int getFwdScore(){ return fwdScore; }
	public int getRewScore(){ return rewScore; }
	
	public void setLabel(String label){ this.label = label; }
	public void setStatement(String statement){ this.statement = statement; }
	public void setFwdScore(int fwdScore){ this.fwdScore = fwdScore; }
	public void setRewScore(int rewScore){ this.rewScore = rewScore; }
}
