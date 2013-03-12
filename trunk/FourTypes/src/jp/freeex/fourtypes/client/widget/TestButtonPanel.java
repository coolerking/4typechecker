package jp.freeex.fourtypes.client.widget;

import java.util.Date;

import jp.freeex.fourtypes.client.ClientUtils;
import jp.freeex.fourtypes.client.FourTypes;
import jp.freeex.fourtypes.client.StatisticsService;
import jp.freeex.fourtypes.client.StatisticsServiceAsync;
import jp.freeex.fourtypes.shared.Const;
import jp.freeex.fourtypes.shared.Utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
/**
 * テスト画面のボタン群部分を提供する。
 * すべてのテストパネル共通で、向性テスト用、それ以外用のコンストラクタ呼び出しで使い分ける。
 * @author tasuku
 */
public class TestButtonPanel extends HorizontalPanel implements Const{
	/**
	 * 次へボタン
	 */
	private Button nextButton = new Button(BUT_NEXT);

	/**
	 * クリアボタン
	 */
	private Button clearButton = new Button(BUT_CLEAR);

	/**
	 * 次へボタンへ押下時処理を実装したハンドラを追加します。
	 * @param handler 次へボタン用クリックハンドラ
	 */
	protected void addNextClickHandler(ClickHandler handler){
		nextButton.addClickHandler(handler);
	}
	
	/**
	 * クリアボタンへ押下時処理を実装したハンドラを追加します。
	 * @param handler クリアボタン用クリックハンドラ
	 */
	protected void addClearClickHandler(ClickHandler handler){
		clearButton.addClickHandler(handler);
	}
	
	/**
	 * デフォルトコンストラクタ。
	 * 各テストのボタン群パネル共通の処理である、次へボタンとクリアボタンを作成し、
	 * パネルに横位置に登録する処理のみ実装する。
	 * 本コンストラクタは、他のコンストラクタからのみ使用される。
	 */
	private TestButtonPanel(){
		this.add(nextButton);
		this.add(clearButton);
	}
	
	/**
	 * 向性テスト用コンストラクタ。
	 * 引数で渡された本文パネルは、向性テスト小計を取得するために使用する。
	 * @param tropismPanel 向性テスト用本文パネル
	 */
	public TestButtonPanel(final BaseTestPanel tropismPanel){
		this();
		long elapse = System.currentTimeMillis();
		GWT.log("[TestButtonPanel:1st:constructor] start");

		// 次へボタン押下時処理うを実装する
		addNextClickHandler(new ClickHandler(){
			/**
			 * 向性テストにて次へボタンが黄化された時の処理を実装する。
			 * 向性テスト結果を集計し、ゼロ出会った場合は、アラートダイアログを表示し再入力させる。
			 * 正値である場合、注目／司令テスト画面を構成する。
			 * 負値である場合、理想／法則テスト画面を構成する。
			 * @param event クリックイベント（使用しない）
			 */
			@Override
			public void onClick(ClickEvent event) {
				long elapseNext = System.currentTimeMillis();
				GWT.log("[TestButtonPanel:1st:constructor:NEXT:onClick()]" +
						" start");

				// 向性テスト小計を集計する
				int y = tropismPanel.getScore();
				GWT.log("[TestButtonPanel:1st:constructor:NEXT:onClick()]" +
						" 1st score=" + y);

				// 得点が均衡している場合はアラートダイアログを表示
				if(y==0){
					GWT.log("[TestButtonPanel:1st:constructor:NEXT:" +
							"onClick()] open alert dialog");
					Window.alert(MSG_1ST_EVEN);

				// 正値の場合、注目／司令テスト画面を構成
				}else if(y>0) showExtrovertTestPage(y);
				else showIntrovertTestPage(y);
				
				// 一番上までスクロールを戻す
				Window.scrollTo(0, 0);

				GWT.log("[TestButtonPanel:1st:constructor:NEXT:onClick()]" +
						" end: " + (System.currentTimeMillis() - elapseNext) + 
						"mSec.");
			}
		});
		
		// クリアボタン押下時処理うを実装する
		addClearClickHandler(new ClickHandler(){
			/**
			 * 向性テストクリアボタン押下時処理。
			 * 向性テストの回答をすべて中間位置へ戻す。
			 * @param event クリックイベント（使用しない）
			 */
			@Override
			public void onClick(ClickEvent event) {
				tropismPanel.clear();
				Window.scrollTo(0, 0);
				GWT.log("[QuestionButtonPanel:1st:constructor:NEXT:" +
						"onClick()] clear 1st test answers");
			}
		});

		GWT.log("[QuestionButtonPanel:1st:constructor] end: " + 
				(System.currentTimeMillis() - elapse) + "mSec.");
	}

	/**
	 * <p>現実的／抽象的思考テスト（注目／司令、理想／法則を兼用）コンストラクタ。</p>
	 * <p>現実的／抽象的思考テスト用ボタン群のハンドラを登録する。</p>
	 * <p>次へボタンは、
	 * <ul>
	 * <li>スコアがゼロの場合、ダイアログを表示するのみで終了</li>
	 * <li>スコアを集計し、結果画面を構成、キャッシュへ結果を登録する</li>
	 * </ul>
	 * 処理を実装する。キャッシュ書き込み時サーバへアクセスする。</p>
	 * <p>クリアボタンは、すべての質問テーブルを中央値にもどす。</p>
	 * @param score　向性テストスコア小計
	 * @param secondPanel 現実的／抽象的思考テスト用本体パネル
	 */
	public TestButtonPanel(final int y, final BaseTestPanel secondTestPanel){
		this();
		long elapse = System.currentTimeMillis();
		GWT.log("[TestButtonPanel:2nd:constructor] start");

		// 次へボタン構成
		addNextClickHandler(new ClickHandler(){
			/**
			 * 注目／司令テスト次へボタンクリック時処理を実装する。
			 * @param ClickEvent クリックイベント（使用していない）
			 */
			@Override
			public void onClick(ClickEvent event) {
				long elapseNext = System.currentTimeMillis();
				GWT.log("[TestButtonPanel:2nd-con>NEXT:onClick()] start");
				// 注目／司令テスト用本体パネルからスコアを集計する
				int x = secondTestPanel.getScore();

				// スコアが均衡している場合は、アラートダイアログを表示する
				if(x==0){
					Window.alert(MSG_2ND_EVEN);
					Window.scrollTo(0, 0);
					GWT.log("[TestButtonPanel:2nd-con>NEXT:onClick()] " +
							"end: " + 
							(System.currentTimeMillis() - elapseNext) + 
							"mSec.");
					return;
				}
				
				// 結果をサーバへ送信
				sendResult(x, y);

				// 結果画面表示
				showResultPage(x, y);

				GWT.log("[TestButtonPanel:2nd-con>NEXT:onClick()] end: " +
						(System.currentTimeMillis() - elapseNext) + "mSec.");
			}
		});

		
		// クリアボタン押下時ハンドラを登録する
		addClearClickHandler(new ClickHandler(){
			/**
			 * 注目／司令テスト回答をすべて中間位置へ戻す。
			 * @param event クリックイベント（使用しない）
			 */
			@Override
			public void onClick(ClickEvent event) {
				secondTestPanel.clear();
				Window.scrollTo(0, 0);
				GWT.log("[TestButtonPanel:2nd-con>CLEAR:onClick()] " +
						"clear 2nd test result");
			}
		});

		GWT.log("[TestButtonPanel:2nd:constructor] end: " + 
				(System.currentTimeMillis() - elapse) + "mSec.");
	}
	
	/**
	 * 注目／司令型テスト画面を構成する。
	 * @param y Y座標
	 */
	public void showExtrovertTestPage(int y){
		// 注目／司令テストサブタイトル変更
		final Label subtitle = new Label(SUBT_2ND_EXT);
		RootPanel.get(HTMLID_SUBTITLE).clear();
		RootPanel.get(HTMLID_SUBTITLE).add(subtitle);
		GWT.log("[TestButtonPanel:showExtrovertTestPage()] added 2nd " +
				"subtitle(ext)");

		// 注目／司令テスト本体パネル変更
		final BaseTestPanel ePanel = new ExtrovertTestPanel();
		RootPanel.get(HTMLID_MAIN).clear();
		RootPanel.get(HTMLID_MAIN).add(ePanel);
		GWT.log("[TestButtonPanel:showExtrovertTestPage()] added 2nd " +
				"body(ext)");

		// 注目／司令テストボタン群変更
		final TestButtonPanel ebPanel = 
				new TestButtonPanel(y, ePanel); // for 2nd
		RootPanel.get(HTMLID_BUTTONS).clear();
		RootPanel.get(HTMLID_BUTTONS).add(ebPanel);
		GWT.log("[TestButtonPanel:showExtrovertTestPage()] added 2nd " +
				"button panel(ext)");


	}
	
	/**
	 * 法則／理想型テスト画面を構成する。
	 * @param y Y座標
	 */
	public void showIntrovertTestPage(int y){
		// 理想／法則テストサブタイトル変更
		final Label subtitle = new Label(SUBT_2ND_INT);
		RootPanel.get(HTMLID_SUBTITLE).clear();
		RootPanel.get(HTMLID_SUBTITLE).add(subtitle);
		GWT.log("[TestButtonPanel:showIntrovertTestPage()] added 2nd " +
				"subtitle(int)");

		// 理想／法則テスト本体パネル変更
		final BaseTestPanel iPanel = new IntrovertTestPanel();
		RootPanel.get(HTMLID_MAIN).clear();
		RootPanel.get(HTMLID_MAIN).add(iPanel);
		GWT.log("[TestButtonPanel:showIntrovertTestPage()] added 2nd " +
				"body(int)");

		// 理想／法則テストボタン群変更
		final TestButtonPanel ibPanel = 
				new TestButtonPanel(y, iPanel); // for 2nd
		RootPanel.get(HTMLID_BUTTONS).clear();
		RootPanel.get(HTMLID_BUTTONS).add(ibPanel);
		GWT.log("[TestButtonPanel:showIntrovertTestPage()] added 2nd " +
				"button panel(int)");

	}
	/**
	 * 結果パネルを構成する。
	 * @param x X座標
	 * @param y Y座標
	 */
	public void showResultPage(int x, int y){
		// サブタイトル変更
		final HTML subtitle = 
				new HTML("<font=\"+2\"><b>"+ SUBT_RESULT_PREFIX +
						"<font color=\"" +
						Utils.getTypeColor(y, x) +
						"\">" +
						ClientUtils.getTypeWithLink(y, x) +
						"</font>" + SUBT_RESULT_SUFFIX +"</b></font>");
		RootPanel.get(HTMLID_SUBTITLE).clear();
		RootPanel.get(HTMLID_SUBTITLE).add(subtitle);
		GWT.log("[TestButtonPanel:2nd-con>NEXT:onClick()]  added " +
				"result subtitle(type)");

		// 説明変更
		final HTML resultDirection = 
				new HTML(DIRC_RESULT_PREFIX + 
						ClientUtils.getTypeWithLink(y, x, DIRC_RESULT_BODY) + 
						DIRC_RESULT_SUFFIX);
		RootPanel.get(HTMLID_DIRECTION).clear();
		RootPanel.get(HTMLID_DIRECTION).add(resultDirection);
		GWT.log("[TestButtonPanel:2nd-con>NEXT:onClick()]  added " +
				"result direction");

		// 本体パネル変更
		RootPanel.get(HTMLID_MAIN).clear();
		RootPanel.get(HTMLID_MAIN).add(new ResultPanel(x, y));
		GWT.log("[TestButtonPanel:2nd-con>NEXT:onClick()] added " +
				"result body");

		// ボタン変更（歳児以降用ボタンのみ）
		final Button restart = new Button(BUT_RETRY);
		restart.addClickHandler(new ClickHandler(){
			/**
			 * 結果画面の再実行ボタン押下時処理。
			 * 初期画面へもどす。
			 * @param ClickEvent クリックイベント（使用していない）
			 */
			@Override
			public void onClick(ClickEvent event) {
				long elapseRetry = System.currentTimeMillis();
				GWT.log("[TestButtonPanel:2nd-con>NEXT>RETRY:" +
						"onClick()] start");
				FourTypes fourTypes = new FourTypes();
				fourTypes.showTopPage();
				Window.scrollTo(0, 0);
				GWT.log("[TestButtonPanel:2nd-con>NEXT>RETRY:" +
						"onClick()] end: " + 
						(System.currentTimeMillis() - elapseRetry) + 
						"mSec.");
			} // end of onClick(): result retry
		}); // end of addClickHandler() result retry
		RootPanel.get(HTMLID_BUTTONS).clear();
		RootPanel.get(HTMLID_BUTTONS).add(restart);
		GWT.log("[TestButtonPanel:2nd-con>NEXT:onClick()] added " +
				"result button(retry)");

	}
	
	/**
	 * 結果をサーバへ送信する。
	 * @param x X座標
	 * @param y Y座標
	 */
	public void sendResult(int x, int y){
		//キャッシュへ結果を書き込む
		final StatisticsServiceAsync service =
				(StatisticsServiceAsync)GWT.create(
						StatisticsService.class);
		((ServiceDefTarget)service).setServiceEntryPoint(
				GWT.getHostPageBaseURL() + "fourtypes/stat");
		GWT.log("[TestButtonPanel:sendResult()] " +
				"prepared sending result to server");
		
		service.setResult(x, y, new Date(), new AsyncCallback<Void>(){
			/**
			 * 結果送信処理失敗時処理。
			 * 失敗したらそのままほったらかす。
			 * @param caught 発生した例外
			 */
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("[TestButtonPanel:sendResult()] exception occured", 
						caught);
			}
			/**
			 * 結果送信処理成功時処理。
			 * 成功してもサーバ側が何もしない。
			 * @param result 結果戻り値（void)
			 */
			@Override
			public void onSuccess(Void result) {
				GWT.log("[TestButtonPanel:2nd-con>NEXT:ASYNC:" +
						"onSuccess()] called");
			}
		});
	}
}
