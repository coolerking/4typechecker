package jp.freeex.fourtypes.client.widget;

import java.util.List;

import jp.freeex.fourtypes.client.q.Question;
import jp.freeex.fourtypes.client.q.QuestionManager;

public class IntrovertTestPanel extends BaseTestPanel {

	/**
	 * 理想／法則テスト質問リストを取得する。
	 * 質問リストは<code>QuestionManager</code>内で生成される。
	 * @return List<Question> 理想／法則テスト質問リスト
	 */
	@Override
	protected List<Question> getQuestions() {
		return QuestionManager.getInstance().getIntrovertQuestions();
	}

}
