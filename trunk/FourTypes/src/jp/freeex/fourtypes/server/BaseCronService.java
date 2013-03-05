package jp.freeex.fourtypes.server;

import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.freeex.fourtypes.shared.Const;
/**
 * cronサービスの基底クラス。
 * @author tasuku
 */
public abstract class BaseCronService extends HttpServlet implements Const{

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = 8132957214483025646L;
	
	/**
	 * ロガー
	 */
	private static Logger log = 
			Logger.getLogger(BaseCronService.class.getName());

	/**
	 * cron処理を実装する。
	 */
	public abstract void execute();
	
	/**
	 * IPアドレスが国内かどうか判別する
	 * @param req リクエスト
	 * @return 真：国内or0.1.0.1、偽：それ以外（海外）
	 */
	private boolean checkIpAddress(HttpServletRequest req){
		String ip = req.getRemoteAddr();
		return ServerUtils.isJapaneseIP(ip);
	}
	
	/**
	 * GETメソッド受領時処理。IPアドレスチェック後execute()を呼び出す。
	 * @param req リクエスト
	 * @param resp レスポンス
	 */
	@Override
	public final void doGet(HttpServletRequest req, HttpServletResponse resp){
		if(checkIpAddress(req)){
			execute();
		}else{
			log.info("[BaseCronService#doGet()] ignore access/out of range :" + 
					req.getRemoteAddr());
		}
	}
	
	/**
	 * POSTメソッド受領時処理。IPアドレスチェック後execute()を呼び出す。
	 * @param req リクエスト
	 * @param resp レスポンス
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		if(checkIpAddress(req)){
			execute();
		}else{
			log.info("[BaseCronService#doPost()] ignore access/out of range :" + 
					req.getRemoteAddr());
		}
	}
}
