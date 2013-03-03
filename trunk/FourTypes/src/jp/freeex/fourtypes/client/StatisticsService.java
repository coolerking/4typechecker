package jp.freeex.fourtypes.client;

import java.util.Date;
import java.util.List;

import jp.freeex.fourtypes.shared.Summary;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("stat")
public interface StatisticsService extends RemoteService {
	void setResult(int x, int y, Date evaluatedAt);
	Summary getSummary();
	List<long[]> getResults();

}
