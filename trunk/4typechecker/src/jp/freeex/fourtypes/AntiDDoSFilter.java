package jp.freeex.fourtypes;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import jp.freeex.fourtypes.antiddos.FrequencyManager;
/**
 * <p>対DDoS攻撃用ServletFilter</p>
 * <p>リクエスト送信元のIPアドレス別にアクセス回数をmemcacheで管理し、
 * 一定ルールを超えた場合、所定のページヘfowardする。</p>
 * @author harahara-development
 */
public class AntiDDoSFilter implements Filter, ViewConstants {
	
	/**
	 * ロガー
	 */
	private static Logger log = 
			Logger.getLogger(AntiDDoSFilter.class.getName());
	
	/**
	 * FilterConfigをインスタンス
	 */
	private FilterConfig config = null;

	/**
	 * 初期処理。FilterConfigインスタンスをインスタンス変数へ格納する。
	 * @param FilterConfig FilterConfigインスタンス
	 */
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	/**
	 * フィルタ処理。
	 * memcache上のIPアドレスアクセス数を比較し違反した場合、
	 * 所定のページヘフォワーディングする。
	 * @param ServletRequest req リクエスト
	 * @param ServletResponse resp レスポンス
	 * @param FilterChain フィルタチェーン
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		String ip = req.getRemoteAddr();
		FrequencyManager freqManager = FrequencyManager.getInstance();
		boolean result = freqManager.isHeavyAccess(ip);
		if(result){
			log.log(Level.INFO, "[doFilter] ip=" + ip +" is heavy user");
			ServletContext context = config.getServletContext();
			RequestDispatcher rd = context.getRequestDispatcher(JSP_HEAVYUSER);
			try {
				rd.forward(req, resp);
				log.info("[doFilter] fowarding done");
			} catch (Exception e) {
				log.log(Level.INFO, 
						"[doFilter] exception during forwarding", e);
			}
		}
		// 処理が終わったので、後続のフィルタ/サーブレットへ
		chain.doFilter(req,  resp);
	}

	/**
	 * 終了処理。
	 * インスタンス変数をクリアする。
	 */
	@Override
	public void destroy() {
		config = null;
	}
}
