package jp.freeex.fourtypes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jp.freeex.fourtypes.control.IPUtils;

public class IPBlockFilter implements Filter {

	/**
	 * ロガー
	 */
	private static Logger log = 
			Logger.getLogger(AntiDDoSFilter.class.getName());

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		String ip = req.getRemoteAddr();
		if(IPUtils.isJapaneseIP(ip)){
			log.log(Level.INFO, "ip=[" + ip + "] is Japanese domain");
			chain.doFilter(req, resp);
		}else{
			log.log(Level.INFO, "ip=[" + ip + "] is not Japanese domain");
			resp.setContentType("text/html; charset=Shift_JIS");
			PrintWriter pw = resp.getWriter();
			pw.println("<html><body>");
			pw.println("<h1>Sorry, this service is for Japanese domain</h1>");
			pw.println("</body></html>");
			pw.close();
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}
}
