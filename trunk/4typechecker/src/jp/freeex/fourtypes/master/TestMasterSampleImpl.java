package jp.freeex.fourtypes.master;

public class TestMasterSampleImpl implements TestMaster {

	private static final long serialVersionUID = -2706887852985362493L;

	private static final String[][] TROPISMARRAY = {
		{"１＋１は？", "2", "いつも２とは限らない"},
		{"神さまは", "信じる", "人間の妄想にすぎない"}
	};
	private static final String[][] KINGSOLDARRAY = {
		{"あなたは王様？", "そう", "でない"},
		{"きちっと", "しないと", "しろ"}
	};
	private static final String[][] SCHLCRFTARRAY = {
		{"掃除", "きらい", "大好きだ"},
		{"年がら年中", "仕事", "苦労ばかり"}
	};
	
	@Override
	public String[][] getTropismArray() {
		return TROPISMARRAY;
	}

	@Override
	public String[][] getKingSoldArray() {
		return KINGSOLDARRAY;
	}

	@Override
	public String[][] getSchlCrftArray() {
		return SCHLCRFTARRAY;
	}



}
