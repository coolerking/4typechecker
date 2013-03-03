package jp.freeex.fourtypes.shared;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2600508589498986225L;

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key = null;

	private int x = 0;

	private int y = 0;

	private Date evaluatedAt = new Date();
	
	public Result(int x, int y, Date evaluatedAt){
		this.x = x;
		this.y = y;
		this.evaluatedAt = evaluatedAt;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Date getEvaluatedAt() {
		return evaluatedAt;
	}

	public void setEvaluatedAt(Date evaluatedAt) {
		this.evaluatedAt = evaluatedAt;
	}
	
	@Override
	public String toString(){
		return "(x, y)=(" + x + ", " + y + "): " + 
				(evaluatedAt==null?null:evaluatedAt.toString());
	}
	
}
