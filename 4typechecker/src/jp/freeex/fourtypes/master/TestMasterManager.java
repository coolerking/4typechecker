package jp.freeex.fourtypes.master;

public class TestMasterManager {
	
	public static final String SYSPROP_KEY_CURRENTMASTER = "type4.currentmaster";
	
	private static TestMasterManager manager = new TestMasterManager();
	
	private TestMaster master = null;
	
	private TestMasterManager(){
		String className = System.getProperty(SYSPROP_KEY_CURRENTMASTER);
		try {
			master = (TestMaster)Class.forName(className).newInstance();
		} catch (Exception e) {
			master = new TestMasterSampleImpl();
		}
	}
	
	public static final synchronized TestMasterManager getInstance(){
		return manager;
	}
	
	public TestMaster getTestMaster(){
		return master;
	}

	public TestMaster getTestMaster(String className){
		try {
			return (TestMaster)Class.forName(className).newInstance();
		} catch (Exception e) {
			return null;
		}
	}
}
