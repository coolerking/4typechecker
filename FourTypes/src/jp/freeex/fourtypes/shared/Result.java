package jp.freeex.fourtypes.shared;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

import com.google.appengine.api.datastore.Key;
/**
 * 結果クラス。
 * @author tasuku
 */
@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Result implements Serializable {

	/**
	 * シリアルバージョンUID
	 */
	private static final long serialVersionUID = -2532631366549728929L;

	/**
	 * 主キー
	 */
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key = null;

	/**
	 * X座標
	 */
	@Persistent
	private int x = 0;

	/**
	 * Y座標
	 */
	@Persistent
	private int y = 0;

	/**
	 * 評価日時
	 */
	@Persistent
	private Date evaluatedAt = new Date();
	
	/**
	 * 唯一のコンストラクタ。
	 * @param x X座標
	 * @param y Y座標
	 * @param evaluatedAt 評価自治じ
	 */
	public Result(int x, int y, Date evaluatedAt){
		this.x = x;
		this.y = y;
		this.evaluatedAt = evaluatedAt;
	}

	/**
	 * 主キーを取得する。
	 * @return 主キー
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * 主キーを格納する。
	 * @param key 主キー
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * X座標を取得する。
	 * @return X座標
	 */
	public int getX() {
		return x;
	}

	/**
	 * X座標を格納する。
	 * @param X座標
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Y座標を取得する。
	 * @return Y座標
	 */
	public int getY() {
		return y;
	}

	/**
	 * Y座標を格納する。
	 * @param Y座標
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 評価日時を取得する。
	 * @return 評価日時
	 */
	public Date getEvaluatedAt() {
		return evaluatedAt;
	}

	/**
	 * 評価日時を格納する。
	 * @param 評価日時
	 */
	public void setEvaluatedAt(Date evaluatedAt) {
		this.evaluatedAt = evaluatedAt;
	}
	
	/**
	 * 中身を表す文字列を取得する。
	 * @return 中身を表す文字列
	 */
	@Override
	public String toString(){
		return "(x, y)=(" + x + ", " + y + "): " + 
				(evaluatedAt==null?null:evaluatedAt.toString());
	}
}
