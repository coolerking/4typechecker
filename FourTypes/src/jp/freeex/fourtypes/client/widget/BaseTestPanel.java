package jp.freeex.fourtypes.client.widget;

import java.util.ArrayList;
import java.util.List;

import jp.freeex.fourtypes.client.q.Question;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class BaseTestPanel extends VerticalPanel {
	/**
	 * 質問テーブルリスト（採点時に使用する）
	 */
	private List<QuestionTable> qts = new ArrayList<QuestionTable>();
	
	/**
	 * デフォルトコンストラクタ。
	 * 本コンストラクタで、本文パネルの構成を完了させる。
	 * ボタンイベントについては<code>QuestionButtonPanel</code>サブクラスへ実装する。
	 * 抽象メソッド<code>getQuestions()</code>を呼び出して質問ルリストを取得し、
	 * 質問テーブル化してから自分自身へ追加（縦に配置）する。
	 */
	public BaseTestPanel(){
		// 抽象メソッドを呼び出して質問リストを完成
		List<Question> questions = getQuestions();
		// 質問テーブルを直列に格納し、質問テーブルリストに格納する
		for(Question question: questions){
			QuestionTable qt = new QuestionTable(question);
			this.add(qt);
			qts.add(qt);
		}
	}
	
	/**
	 * 判定不可な回答（採点結果がゼロ）かどうかを判別する。
	 * @return boolean 真：判定不能、偽：判定可能
	 */
	public boolean isBalance(){
		int score = getScore();
		return score==0;
	}
	
	/**
	 * テスト小計を採点する。
	 * 質問テーブルリストごとの得点を集計する
	 * @return int テスト小計
	 */
	public int getScore(){
		int score = 0;
		for(QuestionTable qt: qts){
			score += qt.getScore();
		}
		return score;
	}
	
	/**
	 * すべての質問テーブルを中間位置に戻す。
	 */
	public void clear(){
		for(QuestionTable qt: qts){
			qt.clear();
		}
		GWT.log("[BaseTestPanel:clear()] called: score=" + getScore());
	}
	
	/**
	 * 質問リストを取得する。
	 * 各テスト本文で設問が異なるため、各サブクラスで実装する。
	 * なお、<code>Question</code>クラスは、設問、回答文２つが格納されている
	 * 単純なJava Beanである。
	 * @return List<Question> 質問リスト
	 */
	protected abstract List<Question> getQuestions();
}
