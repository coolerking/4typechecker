package jp.freeex.fourtypes.shared;

import java.io.Serializable;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Summary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6765745000382873457L;
	public static final String DBKEY_SUMMARY = "summaryPK";
	
	@PrimaryKey
	@Persistent
	private String id = DBKEY_SUMMARY;
	@Persistent
	private int total = 0;
	@Persistent
	private int king = 0;
	@Persistent
	private int solder = 0;
	@Persistent
	private int scholar = 0;
	@Persistent
	private int craftsman = 0;
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getKing() {
		return king;
	}
	public void setKing(int king) {
		this.king = king;
	}
	public int getSolder() {
		return solder;
	}
	public void setSolder(int solder) {
		this.solder = solder;
	}
	public int getScholar() {
		return scholar;
	}
	public void setScholar(int scholar) {
		this.scholar = scholar;
	}
	public int getCraftsman() {
		return craftsman;
	}
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
