package jp.freeex.fourtypes.client.widget;

import java.io.UnsupportedEncodingException;
import java.util.List;

import jp.freeex.fourtypes.client.ClientUtils;
import jp.freeex.fourtypes.client.StatisticsService;
import jp.freeex.fourtypes.client.StatisticsServiceAsync;
import jp.freeex.fourtypes.shared.Summary;
import jp.freeex.fourtypes.shared.Utils;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ResultPanel extends VerticalPanel {
	
	private ResultPanel resultPanel = null;
	
	public ResultPanel(final int x, final int y){
		super();
		long elapse = System.currentTimeMillis();
		GWT.log("[ResultPanel:constructor] start");


		// 匿名インナークラス受渡し用変数へ格納する
		this.resultPanel = this;

		final Canvas canvas = Canvas.createIfSupported();
		//final Context2d ctx = null;
		if(canvas==null){
			GWT.log("[ResultPanel:constructor] canvas not support");
			Label sorry = new Label();
			sorry.setText("HTML5非対応ブラウザのため、グラフ表示できませんでした。" +
					"\nあなたの座標(x, y)=(" + x + ", " + y + 
					")");
			this.add(sorry);
			GWT.log("[ResultPanel:constructor] added sorry label");
		}else{
			canvas.setCoordinateSpaceWidth(400);
			canvas.setCoordinateSpaceHeight(400);
			Context2d ctx = canvas.getContext2d();
			//createRader(ctx, firstScore, secondScore);
			ResultPanel.drawGraphBase(ctx);
			ResultPanel.writePoint(ctx, x, y, 5, 
					Utils.getTypeColor(y, x));
			this.add(canvas);
			GWT.log("[ResultPanel:constructor] added rader canvas");
		}

		// 非同期で統計情報を表示する
		GWT.log("[ResultPanel:constructor] prepare requesting summary " +
				"to server");
		final StatisticsServiceAsync service =
				(StatisticsServiceAsync)GWT.create(
						StatisticsService.class);
		((ServiceDefTarget)service).setServiceEntryPoint(
				GWT.getHostPageBaseURL() + "fourtypes/statistics");
		GWT.log("[ResultPanel:constructor] start requesting summary to server");
		// サーバへサマリ情報を要求する
		service.getSummary(new AsyncCallback<Summary>(){
			/**
			 * サマリ取得失敗時の処理。
			 * 何もしない
			 * @param caught 発生した例外
			 */
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("[ResultPanel:constructor>Async:onFailure()] called",
						caught);
			}

			/**
			 * サマリ結果を受領した時呼び出される処理。
			 * 結果を元に結果パネルへ情報追加する
			 * @param result サマリを表す文字列
			 */
			@Override
			public void onSuccess(Summary sum) {
				final long elapseSum = System.currentTimeMillis();
				GWT.log("[ResultPanel:constructor>Async:SUMMARY:onSuccess()]" +
						" start");
				int total = sum.getTotal();
				int king = sum.getKing();
				int sold = sum.getSolder();
				int scho = sum.getScholar();
				int crft = sum.getCraftsman();
				try{
					final Image sumGraph = new Image(
								ClientUtils.getSummaryChart(
										total, king, sold, scho, crft));
					sumGraph.addLoadHandler(new LoadHandler(){
						@Override
						public void onLoad(LoadEvent event) {
							GWT.log("[ResultPanel:constructor>Async:SUMMARY:" +
									"onSuccess():Image:onLoad()] start");
							HTML space = new HTML(ClientUtils.getNewLine() + 
									ClientUtils.getNewLine());
							resultPanel.add(space);
							resultPanel.add(sumGraph);
							GWT.log("[ResultPanel:constructor>Async:SUMMARY" +
									"onSuccess():Image:onLoad()] end: " + 
									(System.currentTimeMillis() - elapseSum) + 
									"mSec.");
						}
					});

				}catch(UnsupportedEncodingException e){
					GWT.log("[ResultPanel:constructor>Async:SUMMARY:" +
							"onSuccess()] unsupported utf-8", e);
				}
				GWT.log("[ResultPanel:constructor>Async:SUMMARY:onSuccess" +
						"()] end" + 
						(System.currentTimeMillis() - elapseSum) + "mSec.");
			} // end of onSuccess(): ASYNC getSummary()
		}); // end of ASYNC getSummary()
		
		if(canvas!=null){
			service.getResults(new AsyncCallback<List<long[]>>(){

				@Override
				public void onFailure(Throwable caught) {
					GWT.log("[ResultPanel:constructor>Async:RESULTS:" +
							"onFailure()] called", caught);
				}

				@Override
				public void onSuccess(List<long[]> result) {
					long elapse = System.currentTimeMillis();
					GWT.log("[ResultPanel:constructor>Async:RESULTS:" +
							"onFailure()] start: "+result);
					Context2d ctx = canvas.getContext2d();
					ResultPanel.writeGrayPoints(ctx, result);
					ResultPanel.writePoint(ctx, x, y, 5, 
							Utils.getTypeColor(y, x));
					GWT.log("[ResultPanel:constructor>Async:RESULTS:" +
							"onFailure()] end: " + 
							(System.currentTimeMillis() - elapse) + "mSec.");
				}
			});
		}
		GWT.log("[ResultPanel:constructor] end:" + 
				(System.currentTimeMillis() - elapse) + "mSec.");
	}

	
	public static void drawGraphBase(Context2d ctx){
		GWT.log("[Resultpanel:drawGraphBase()] start");
		ctx.setStrokeStyle(Utils.COLOR_BLACK);
		ctx.fillRect(0, 0, 400, 400);
		ctx.setStrokeStyle(Utils.COLOR_GRAY);
		ctx.setGlobalAlpha(0.7D);
		ctx.strokeRect(0, 0,50, 50);
		ctx.strokeRect(0, 50,50, 50);
		ctx.strokeRect(0, 100,50, 50);
		ctx.strokeRect(0, 150,50, 50);
		ctx.strokeRect(0, 200,50, 50);
		ctx.strokeRect(0, 250,50, 50);
		ctx.strokeRect(0, 300,50, 50);
		ctx.strokeRect(0, 350,50, 50);
		ctx.strokeRect(50, 0,50, 50);
		ctx.strokeRect(50, 50,50, 50);
		ctx.strokeRect(50, 100,50, 50);
		ctx.strokeRect(50, 150,50, 50);
		ctx.strokeRect(50, 200,50, 50);
		ctx.strokeRect(50, 250,50, 50);
		ctx.strokeRect(50, 300,50, 50);
		ctx.strokeRect(50, 350,50, 50);
		ctx.strokeRect(100, 0,50, 50);
		ctx.strokeRect(100, 50,50, 50);
		ctx.strokeRect(100, 100,50, 50);
		ctx.strokeRect(100, 150,50, 50);
		ctx.strokeRect(100, 200,50, 50);
		ctx.strokeRect(100, 250,50, 50);
		ctx.strokeRect(100, 300,50, 50);
		ctx.strokeRect(100, 350,50, 50);
		ctx.strokeRect(150, 0,50, 50);
		ctx.strokeRect(150, 50,50, 50);
		ctx.strokeRect(150, 100,50, 50);
		ctx.strokeRect(150, 150,50, 50);
		ctx.strokeRect(150, 200,50, 50);
		ctx.strokeRect(150, 250,50, 50);
		ctx.strokeRect(150, 300,50, 50);
		ctx.strokeRect(150, 350,50, 50);
		ctx.strokeRect(200, 0,50, 50);
		ctx.strokeRect(200, 50,50, 50);
		ctx.strokeRect(200, 100,50, 50);
		ctx.strokeRect(200, 150,50, 50);
		ctx.strokeRect(200, 200,50, 50);
		ctx.strokeRect(200, 250,50, 50);
		ctx.strokeRect(200, 300,50, 50);
		ctx.strokeRect(200, 350,50, 50);
		ctx.strokeRect(250, 0,50, 50);
		ctx.strokeRect(250, 50,50, 50);
		ctx.strokeRect(250, 100,50, 50);
		ctx.strokeRect(250, 150,50, 50);
		ctx.strokeRect(250, 200,50, 50);
		ctx.strokeRect(250, 250,50, 50);
		ctx.strokeRect(250, 300,50, 50);
		ctx.strokeRect(250, 350,50, 50);
		ctx.strokeRect(300, 0,50, 50);
		ctx.strokeRect(300, 50,50, 50);
		ctx.strokeRect(300, 100,50, 50);
		ctx.strokeRect(300, 150,50, 50);
		ctx.strokeRect(300, 200,50, 50);
		ctx.strokeRect(300, 250,50, 50);
		ctx.strokeRect(300, 300,50, 50);
		ctx.strokeRect(300, 350,50, 50);
		ctx.strokeRect(350, 0,50, 50);
		ctx.strokeRect(350, 50,50, 50);
		ctx.strokeRect(350, 100,50, 50);
		ctx.strokeRect(350, 150,50, 50);
		ctx.strokeRect(350, 200,50, 50);
		ctx.strokeRect(350, 250,50, 50);
		ctx.strokeRect(350, 300,50, 50);
		ctx.strokeRect(350, 350,50, 50);

		
		ctx.beginPath();
		ctx.setGlobalAlpha(1D);
		ctx.setStrokeStyle(Utils.COLOR_WHITE);
		ctx.moveTo(200,0);
		ctx.lineTo(200, 400);
		ctx.moveTo(0, 200);
		ctx.lineTo(400, 200);
		ctx.stroke();
		ctx.setGlobalAlpha(1D);

		ctx.beginPath();
		ctx.setFont(Utils.FONTTYPE_TYPE);
		ctx.setStrokeStyle(Utils.COLOR_WHITE);
		ctx.strokeText(Utils.TYPE_SOLD, 343,  18);
		ctx.strokeText(Utils.TYPE_KING,   0,  18);
		ctx.strokeText(Utils.TYPE_CRFT,   0, 395);
		ctx.strokeText(Utils.TYPE_SCHO, 343, 395);
		ctx.stroke();
		ctx.beginPath();
		ctx.setFont(Utils.FONTTYPE_NUM);
		ctx.setStrokeStyle(Utils.COLOR_WHITE);
		ctx.strokeText("-24",   0, 200);
		ctx.strokeText( "40", 200,   8);
		ctx.strokeText("-40", 200, 400);
		ctx.strokeText( "24", 390, 200);
		ctx.strokeText(  "0", 200, 200);
		ctx.stroke();
	}

	private static void writeGrayPoints(
			Context2d ctx, List<long[]> coordinates){
		int pos=0;
		for(long[] coordinate: coordinates){
			ResultPanel.writePoint(
					ctx, 
					(int)coordinate[0], (int)coordinate[1], 
					3, ResultPanel.getGray(pos++));
		}
	}
	
	private static String getGray(int pos){
		int point = 255 - pos;
		if(point<0) point=0;
		StringBuffer color = new StringBuffer();
		color.append("rgb(");
		color.append(point);
		color.append(",");
		color.append(point);
		color.append(",");
		color.append(point);
		color.append(")");
		return color.toString();
	}

	private static void writePoint(
			Context2d ctx, int x, int y, int radius, 
			String color){
		int xCoord = 200 + (x * 8);
		int yCoord = 200 - (y * 5);
		ctx.beginPath();
		ctx.setFillStyle(color);
		ctx.arc(xCoord, yCoord, radius, 0, Math.PI * 2, true);
		ctx.fill();
	}
}
