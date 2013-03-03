package jp.freeex.fourtypes.client.q;

public class Question {
	/**
	 * 設問
	 */
	private String statement = null;
	/**
	 * 回答１（加算される回答）
	 */
	private String upperAnswer = null;
	/**
	 * 回答２（減算される回答）
	 */
	private String lowerAnswer = null;
	/**
	 * コンストラクタ。
	 * 設問、回答２種類を指定してインスタンスを作成する。
	 * @param statement 設問
	 * @param upperAnswer 回答１（加算される回答）
	 * @param lowerAnswer 回答２（減算される回答）
	 */
	public Question(String statement, String upperAnswer, String lowerAnswer){
		this.statement = statement;
		this.upperAnswer = upperAnswer;
		this.lowerAnswer = lowerAnswer;
	}
	
	/**
	 * 設問を取得する。
	 * @return String 設問
	 */
	public String getStatement() {
		return statement;
	}

	/**
	 * 設問を格納する。
	 * @param statement 設問
	 */
	public void setStatement(String statement) {
		this.statement = statement;
	}

	/**
	 * 回答１（加算される回答）を取得する。
	 * @return String 回答１（加算される回答）
	 */
	public String getUpperAnswer() {
		return upperAnswer;
	}

	/**
	 * 回答１（加算される回答）を格納する。
	 * @param upperAnswer 回答１（加算される回答）
	 */
	public void setUpperAnswer(String upperAnswer) {
		this.upperAnswer = upperAnswer;
	}

	/**
	 * 回答２（減算される回答）を取得する。
	 * @return String 回答２（減算される回答）
	 */
	public String getLowerAnswer() {
		return lowerAnswer;
	}

	/**
	 * 回答２（減算される回答）を格納する。
	 * @param lowerAnswer 回答２（減算される回答）
	 */
	public void setLowerAnswer(String lowerAnswer) {
		this.lowerAnswer = lowerAnswer;
	}

	/**
	 * デバッグ用文字列を取得する。
	 * @return String デバッグ用文字列
	 */
	@Override
	public String toString(){
		return "[" + statement + "] (" + upperAnswer + ")(" + lowerAnswer +")";
	}
}
