package jp.freeex.fourtypes.master;

import java.io.Serializable;

public interface TestMaster extends Serializable {
	public String[][] getTropismArray();
	public String[][] getKingSoldArray();
	public String[][] getSchlCrftArray();

}
