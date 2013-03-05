package jp.freeex.fourtypes.client.widget;

import java.util.Arrays;
import java.util.List;

import jp.freeex.fourtypes.client.ClientUtils;
import jp.freeex.fourtypes.client.StatisticsService;
import jp.freeex.fourtypes.client.StatisticsServiceAsync;
import jp.freeex.fourtypes.shared.Const;
import jp.freeex.fourtypes.shared.Summary;
import jp.freeex.fourtypes.shared.Utils;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * 結果パネルクラス。
 * @author tasuku
 */
public class ResultPanel extends VerticalPanel implements Const{
	/**
	 * 結果パネルインスタンス
	 */
	private ResultPanel resultPanel = null;
	/**
	 * 統計サービスインスタンス。
	 */
	private final StatisticsServiceAsync service = 
			(StatisticsServiceAsync)GWT.create(StatisticsService.class);
	
	/**
	 * 結果パネルを構成し、再実行ボタンのアクションを定義する。
	 * @param x X座標
	 * @param y Y座標
	 */
	public ResultPanel(final int x, final int y){
		super();
		long elapse = System.currentTimeMillis();
		GWT.log("[ResultPanel:constructor] start");

		((ServiceDefTarget)service).setServiceEntryPoint(
				GWT.getHostPageBaseURL() + URL_SERVICE);

		// 匿名インナークラス受渡し用変数へ格納する
		this.resultPanel = this;

		final Canvas canvas = Canvas.createIfSupported();
		//final Context2d ctx = null;
		if(canvas==null){
			GWT.log("[ResultPanel:constructor] canvas not support");
			// テーブルで代替
			FlexTable resultTbl = new FlexTable();
			if(y>0) resultTbl.setText(0,  0, TBL_2ND_EXT + TBL_COORD_X);
			else resultTbl.setText(0,  1, TBL_2ND_EXT + TBL_COORD_X);
			resultTbl.setText(0,  1, TBL_1ST + TBL_COORD_Y);

			resultTbl.setText(1, 0, Integer.toString(x));
			resultTbl.setText(1, 1, Integer.toString(y));
			resultTbl.setBorderWidth(1);
			resultTbl.setStyleName("center");

			this.add(resultTbl);
			GWT.log("[ResultPanel:constructor] added table");
		}else{
//		if(canvas!=null){
			canvas.setCoordinateSpaceWidth(400);
			canvas.setCoordinateSpaceHeight(400);
			Context2d ctx = canvas.getContext2d();
			// グラフベースを描画
			ResultPanel.drawGraphBase(ctx);
			// 結果を描画
			ResultPanel.writePoint(ctx, x, y, 5, 
					Utils.getTypeColor(y, x));
			// ツールチップでスコア詳細をだす
			canvas.setTitle(ClientUtils.getTooltipMsg(x, y));
			this.add(canvas);
			GWT.log("[ResultPanel:constructor] added rader canvas");
		}

		// 非同期で統計情報を表示する
		GWT.log("[ResultPanel:constructor] prepare requesting summary " +
				"to server");

		
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
		
		// 統計情報が取得できた場合は表示
		drawSummary();
		GWT.log("[ResultPanel:constructor] end:" + 
				(System.currentTimeMillis() - elapse) + "mSec.");
	}

	/**
	 * 結果グラフの枠線を描画する。
	 * @param ctx コンテキスト
	 */
	public static void drawGraphBase(Context2d ctx){
		GWT.log("[Resultpanel:drawGraphBase()] start");
		ctx.setStrokeStyle(COLOR_BLACK);
		ctx.fillRect(0, 0, 400, 400);
		ctx.setStrokeStyle(COLOR_GRAY);
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
		ctx.setStrokeStyle(COLOR_WHITE);
		ctx.moveTo(200,0);
		ctx.lineTo(200, 400);
		ctx.moveTo(0, 200);
		ctx.lineTo(400, 200);
		ctx.stroke();
		ctx.setGlobalAlpha(1D);

		ctx.beginPath();
		ctx.setFont(FONTTYPE_TYPE);
		ctx.setStrokeStyle(COLOR_WHITE);
		ctx.strokeText(TYPE_SOLD, 343,  18);
		ctx.strokeText(TYPE_KING,   0,  18);
		ctx.strokeText(TYPE_CRFT,   0, 395);
		ctx.strokeText(TYPE_SCHO, 343, 395);
		ctx.stroke();
		ctx.beginPath();
		ctx.setFont(FONTTYPE_NUM);
		ctx.setStrokeStyle(COLOR_WHITE);
		ctx.strokeText("-24",   0, 200);
		ctx.strokeText( "40", 200,   8);
		ctx.strokeText("-40", 200, 400);
		ctx.strokeText( "24", 390, 200);
		ctx.strokeText(  "0", 200, 200);
		ctx.stroke();
	}

	/**
	 * 過去の履歴を結果グラフへ書き込む。
	 * @param ctx コンテキスト
	 * @param coordinates 結果(long[])が格納されたリスト
	 */
	private static void writeGrayPoints(
			Context2d ctx, List<long[]> coordinates){
		int pos=0;
		for(long[] coordinate: coordinates){
			GWT.log("ResultPanel#writeGrayPoint()] pos=" + pos +": (x,y)=(" + 
					coordinate[0] +", " + coordinate[1] + ")");
			ResultPanel.writePoint(
					ctx, 
					(int)coordinate[0], (int)coordinate[1], 
					3, ResultPanel.getGray(pos++));
		}
	}
	
	/**
	 * グレーの色合いを文字列で取得する。
	 * @param pos 位置
	 * @return グレーを表す文字列
	 */
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
		GWT.log("[ResultPanel#getGray()] :" + color.toString());
		return color.toString();
	}

	/**
	 * 点を結果グラフへ書き込む
	 * @param ctx コンテキスト
	 * @param x X座標
	 * @param y Y座標
	 * @param radius 半径
	 * @param color 色
	 */
	private static void writePoint(
			Context2d ctx, int x, int y, int radius, 
			String color){
		int xCoord = 200 + (x * 8);
		int yCoord = 200 - (y * 5);
		ctx.beginPath();
		ctx.setFillStyle(color);
		ctx.arc(xCoord, yCoord, radius, 0, Math.PI * 2, true);
		ctx.fill();
		GWT.log("[ResultPanel#writePoint] (x, y)=(" + xCoord + ", " + 
				yCoord + ", rad=" + radius + ", color=" + color);
	}
	
	/**
	 * サマリを書き込む
	 */
	private void drawSummary(){
		GWT.log("[ResultPanel#drawSummary()] start");
		// サーバへサマリ情報を要求する
		service.getSummary(new AsyncCallback<Summary>(){
			/**
			 * サマリ取得失敗時の処理。
			 * 何もしない
			 * @param caught 発生した例外
			 */
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("[ResultPanel#drawSummary()>Async:onFailure()] called",
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
				GWT.log("[ResultPanel#drawSummary()>Async:SUMMARY:onSuccess()]" +
						" start: summary=" + sum);
				if(sum.getTotal()<=0){
					GWT.log("[ResultPanel#drawSummary()>Async:SUMMARY:" +
							"onSuccess()] no summary ignore");
					return;
				}
				
				List<Summary> sums = Arrays.asList(sum);

				CellTable<Summary> statTbl = new CellTable<Summary>();
				TextColumn<Summary> kingCol = new TextColumn<Summary>(){
					@Override
					public String getValue(Summary sum) {
						return Integer.toString(sum.getKing());
					}
				};
				statTbl.addColumn(kingCol, TYPE_KING);
				TextColumn<Summary> soldCol = new TextColumn<Summary>(){
					@Override
					public String getValue(Summary sum) {
						return Integer.toString(sum.getSolder());
					}
				};
				statTbl.addColumn(soldCol, TYPE_SOLD);
				TextColumn<Summary> schoCol = new TextColumn<Summary>(){
					@Override
					public String getValue(Summary sum) {
						return Integer.toString(sum.getScholar());
					}
				};
				statTbl.addColumn(schoCol, TYPE_SCHO);
				TextColumn<Summary> crftCol = new TextColumn<Summary>(){
					@Override
					public String getValue(Summary sum) {
						return Integer.toString(sum.getCraftsman());
					}
				};
				statTbl.addColumn(crftCol, TYPE_CRFT);
				TextColumn<Summary> othrCol = new TextColumn<Summary>(){
					@Override
					public String getValue(Summary sum) {
						return Integer.toString(sum.getTotal()-sum.getKing() - 
								sum.getSolder() - sum.getScholar() - 
								sum.getCraftsman());
					}
				};
				statTbl.addColumn(othrCol, SUM_OTHERS);
				TextColumn<Summary> totlCol = new TextColumn<Summary>(){
					@Override
					public String getValue(Summary sum) {
						return Integer.toString(sum.getTotal());
					}
				};
				statTbl.addColumn(totlCol, SUM_TOTAL);
				statTbl.setRowCount(sums.size(), true);
				statTbl.setRowData(0, sums);
				resultPanel.add(new HTML(ClientUtils.getNewLine()));
				resultPanel.add(new HTML(ClientUtils.getNewLine()));
				resultPanel.setTitle(TTP_SUMMARY);
				resultPanel.add(statTbl);

				GWT.log("[ResultPanel:constructor>Async:SUMMARY:onSuccess" +
						"()] end" + 
						(System.currentTimeMillis() - elapseSum) + "mSec.");
			}
		});
	}
}
