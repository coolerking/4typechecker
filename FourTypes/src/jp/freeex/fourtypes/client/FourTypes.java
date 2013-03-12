package jp.freeex.fourtypes.client;


import jp.freeex.fourtypes.client.widget.TopButtonPanel;
import jp.freeex.fourtypes.shared.Const;
import jp.freeex.fourtypes.shared.Utils;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * 4タイプ判定テストのエントリポイントとなるクラス。
 * @author tasuku
 */
public class FourTypes implements EntryPoint, Const {


	/**
	 * 最初に呼び出されるメソッド。
	 * トップページを描画し、ボタン用ハンドラを登録する。
	 */
	public void onModuleLoad() {
		long elapse = System.currentTimeMillis();
		GWT.log("[FourTypes#showTopPage()] start");
		showTopPage();
		GWT.log("[FourTypes#showTopPage()] end: " + 
				(System.currentTimeMillis() - elapse) + "mSec.");
	}
	
	public void showTopPage(){
		Label topSub = new Label(SUBT_TOP);
		RootPanel.get(Utils.HTMLID_SUBTITLE).clear();
		RootPanel.get(Utils.HTMLID_SUBTITLE).add(topSub);
		
		Label topDirc = new Label(DIRC_TOP);
		RootPanel.get(Utils.HTMLID_DIRECTION).clear();
		RootPanel.get(Utils.HTMLID_DIRECTION).add(topDirc);

		RootPanel.get(Utils.HTMLID_MAIN).clear();

		// ボタン押下後の処理は本クラス内に実装
		TopButtonPanel topButs = new TopButtonPanel();
		RootPanel.get(Utils.HTMLID_BUTTONS).clear();
		RootPanel.get(Utils.HTMLID_BUTTONS).add(topButs);

		HTML footer = new HTML(ClientUtils.getAboutEachTypes() +
				ClientUtils.getNewLine() +
				ClientUtils.getNewLine() +
				ClientUtils.getNewLine() +
				ClientUtils.getNewLine() +
				ClientUtils.getAboutFourType() );
		RootPanel.get(Utils.HTMLID_FOOTER).clear();
		RootPanel.get(Utils.HTMLID_FOOTER).add(footer);

	}
}
