package jp.freeex.fourtypes.client.widget;

import java.util.List;

import jp.freeex.fourtypes.client.q.Question;
import jp.freeex.fourtypes.client.q.QuestionManager;
/**
 * 向性テスト本文パネル。
 * 向性テスト固有の処理のみ実装しており、主要な処理は親クラス<code>QuestionPanel</code>
 * 上に存在する。
 * @author tasuku
 */
public class TropismTestPanel extends BaseTestPanel {

	/**
	 * 向性テスト質問リストを取得する。
	 * 質問リストは<code>QuestionManager</code>内で生成される。
	 * @return List<Question> 向性テスト質問リスト
	 */
	@Override
	protected List<Question> getQuestions() {
		return QuestionManager.getInstance().getTripismQuestions();
	}

}
