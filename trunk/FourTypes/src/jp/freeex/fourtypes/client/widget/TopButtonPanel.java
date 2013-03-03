package jp.freeex.fourtypes.client.widget;

import jp.freeex.fourtypes.client.ClientUtils;
import jp.freeex.fourtypes.client.TextConst;
import jp.freeex.fourtypes.shared.Utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class TopButtonPanel extends HorizontalPanel implements TextConst{
	public TopButtonPanel(){
		long elapse = System.currentTimeMillis();
		GWT.log("[TopButtonPanel] start");
		setupButtons();
		GWT.log("[TopButtonPanel] end: " + 
				(System.currentTimeMillis() - elapse) + "mSec.");
	}
	
	private void setupButtons(){
		final Button startBut = new Button(BUT_START);
		this.add(startBut);
		
		startBut.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				showTropismTestPage();
				
			}

		});
	}
	
	private void showTropismTestPage(){
		Label topSub = new Label(SUBT_1ST);
		RootPanel.get(Utils.HTMLID_SUBTITLE).clear();
		RootPanel.get(Utils.HTMLID_SUBTITLE).add(topSub);
		
		Label topDirc = new Label(DIRC_1ST);
		RootPanel.get(Utils.HTMLID_DIRECTION).clear();
		RootPanel.get(Utils.HTMLID_DIRECTION).add(topDirc);

		BaseTestPanel tropismMain = new TropismTestPanel();
		RootPanel.get(Utils.HTMLID_MAIN).clear();
		RootPanel.get(Utils.HTMLID_MAIN).add(tropismMain);

		TestButtonPanel testButs = new TestButtonPanel(tropismMain);
		RootPanel.get(Utils.HTMLID_BUTTONS).clear();
		RootPanel.get(Utils.HTMLID_BUTTONS).add(testButs);

		HTML footer = new HTML(ClientUtils.getAboutFourType() +
				ClientUtils.getNewLine() +
				ClientUtils.getAboutEachTypes());
		RootPanel.get(Utils.HTMLID_FOOTER).clear();
		RootPanel.get(Utils.HTMLID_FOOTER).add(footer);
	}
}
