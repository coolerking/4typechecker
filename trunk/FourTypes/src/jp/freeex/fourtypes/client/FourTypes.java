package jp.freeex.fourtypes.client;


import jp.freeex.fourtypes.client.widget.TopButtonPanel;
import jp.freeex.fourtypes.shared.Utils;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FourTypes implements EntryPoint, TextConst {


	/**
	 * This is the entry point method.
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

		TopButtonPanel topButs = new TopButtonPanel();
		RootPanel.get(Utils.HTMLID_BUTTONS).clear();
		RootPanel.get(Utils.HTMLID_BUTTONS).add(topButs);

		HTML footer = new HTML(ClientUtils.getAboutFourType() +
				ClientUtils.getNewLine() +
				ClientUtils.getAboutEachTypes());
		RootPanel.get(Utils.HTMLID_FOOTER).clear();
		RootPanel.get(Utils.HTMLID_FOOTER).add(footer);

	}
}
