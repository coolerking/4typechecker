package jp.freeex.fourtypes.client.widget;

import jp.freeex.fourtypes.client.ClientUtils;
import jp.freeex.fourtypes.client.q.Question;
import jp.freeex.fourtypes.shared.Const;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
/**
 * 質問テーブルクラス。
 * @author tasuku
 */
public class QuestionTable extends FlexTable implements Const{
	/**
	 * スコア
	 */
	private int score = 0;
	/**
	 * 回答用の５つのラジオボタン
	 */
	private RadioButton[] radios = new RadioButton[5];

	/**
	 * 唯一のコンストラクタ。
	 * @param question　質問クラス
	 */
	public QuestionTable(Question question){
		String statement = question.getStatement();
		String upperAns = question.getUpperAnswer();
		String lowerAns = question.getLowerAnswer();
		final boolean order = ClientUtils.getRandomBoolean();
		GWT.log("[QuestionTable] order is " + (order?"asc":"desc"));
		final Label statLabel = new Label(statement);
		this.setWidget(0, 0, statLabel);

		radios[0] = new RadioButton(statement, order?upperAns:lowerAns);
		radios[0].addValueChangeHandler(new ValueChangeHandler<Boolean>(){
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event){
				if(event.getValue().booleanValue()){
					score = order?2:-2;
				}
			}
		});
		radios[0].setValue(false);
		this.setWidget(1, 0, radios[0]);
		
		radios[1] = new RadioButton(statement, ANS_UPPER);
		radios[1].addValueChangeHandler(new ValueChangeHandler<Boolean>(){
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event){
				if(event.getValue().booleanValue()){
					score = order?1:-1;
				}
			}
		});
		radios[1].setValue(false);
		this.setWidget(2, 0, radios[1]);
		
		radios[2] = new RadioButton(statement, ANS_MIDDLE);
		radios[2].addValueChangeHandler(new ValueChangeHandler<Boolean>(){
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event){
				if(event.getValue().booleanValue()){
					score = 0;
				}
			}
		});
		radios[2].setValue(true);
		this.setWidget(3, 0, radios[2]);
		
		radios[3] = new RadioButton(statement, ANS_LOWER);
		radios[3].addValueChangeHandler(new ValueChangeHandler<Boolean>(){
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event){
				if(event.getValue().booleanValue()){
					score = order?-1:1;
				}
			}
		});
		radios[3].setValue(false);
		this.setWidget(4, 0, radios[3]);
		
		radios[4] = new RadioButton(statement, order?lowerAns:upperAns);
		radios[4].addValueChangeHandler(new ValueChangeHandler<Boolean>(){
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event){
				if(event.getValue().booleanValue()){
					score = order?-2:2;
				}
			}
		});
		radios[4].setValue(false);
		this.setWidget(5, 0, radios[4]);
		
		HTML newLine = new HTML(ClientUtils.getNewLine());
		this.setWidget(6, 0, newLine);
	}
	/**
	 * スコアを取得する。
	 * @return スコア
	 */
	public int getScore(){ return score; }
	/**
	 * すべての回答を中間位置にもどし、スコアを0にする。
	 */
	public void clear(){
		radios[0].setValue(false);
		radios[1].setValue(false);
		radios[2].setValue(true);
		radios[3].setValue(false);
		radios[4].setValue(false);
		score = 0;
	}
}
