package jp.freeex.fourtypes.client;

import java.util.Date;
import java.util.List;

import jp.freeex.fourtypes.shared.Summary;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StatisticsServiceAsync {
	void setResult(int x, int y, Date evaluatedAt, AsyncCallback<Void> callback);
	void getSummary(AsyncCallback<Summary> callback);
	void getResults(AsyncCallback<List<long[]>> callback);

}
