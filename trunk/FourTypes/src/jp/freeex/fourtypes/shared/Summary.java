package jp.freeex.fourtypes.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;
/**
 * サマリを表すクラス。
 * 通信に使用する。
 * @author tasuku
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Summary implements Serializable {
	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -6765745000382873457L;
	/**
	 * キー値
	 */
	public static final String DBKEY_SUMMARY = "summaryPK";
	
	/**
	 * ID
	 */
	@PrimaryKey
	@Persistent
	private String id = DBKEY_SUMMARY;
	/**
	 * 合計
	 */
	@Persistent
	private int total = 0;
	/**
	 * 注目型件数
	 */
	@Persistent
	private int king = 0;
	/**
	 * 司令型件数
	 */
	@Persistent
	private int solder = 0;
	@Persistent
	/**
	 * 法則型件数
	 */
	private int scholar = 0;
	@Persistent
	/**
	 * 理想型件数
	 */
	private int craftsman = 0;
	/**
	 * IDを取得する
	 * @return ID
	 */
	public String getId(){
		return id;
	}
	/**
	 * IDを格納する
	 * @param id ID
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 合計を取得する。
	 * @return 合計
	 */
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * 注目型件数を取得する。
	 * @return 注目型件数
	 */
	public int getKing() {
		return king;
	}
	/**
	 * 注目型件数を格納する。
	 * @param 注目型件数
	 */
	public void setKing(int king) {
		this.king = king;
	}
	/**
	 * 司令型件数を取得する。
	 * @return 司令型件数
	 */
	public int getSolder() {
		return solder;
	}
	/**
	 * 司令型件数を格納する。
	 * @param 司令型件数
	 */
	public void setSolder(int solder) {
		this.solder = solder;
	}
	/**
	 * 法則型件数を取得する。
	 * @return 法則型件数
	 */
	public int getScholar() {
		return scholar;
	}
	/**
	 * 法則型件数を格納する。
	 * @param 法則型件数
	 */
	public void setScholar(int scholar) {
		this.scholar = scholar;
	}
	/**
	 * 理想型件数を取得する。
	 * @return 理想型件数
	 */
	public int getCraftsman() {
		return craftsman;
	}
	/**
	 * 理想型件数を格納する。
	 * @param 理想型件数
	 */
	public void setCraftsman(int craftsman) {
		this.craftsman = craftsman;
	}
	
	/**
	 * 各フィールド値をでセミコロン区切った文字列に変換する
	 * @return String 変換後文字列
	 */
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append(total).append(";").append(king).append(";").append(solder);
		buf.append(";").append(scholar).append(";").append(craftsman);
		return buf.toString();
	}
}
