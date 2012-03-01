package com.otakingex.type4.control;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.otakingex.type4.ViewConstants;
import com.otakingex.type4.master.TestMaster;
import com.otakingex.type4.master.TestMasterManager;
import com.otakingex.type4.model.Answer;
import com.otakingex.type4.model.Count;
import com.otakingex.type4.model.Question;
import com.otakingex.type4.model.User;
import com.otakingex.type4.store.BigTableStoreManager;
import com.otakingex.type4.store.MailStoreManager;

public class WorkoutContext implements ViewConstants{
	
	private Logger log = Logger.getLogger(getClass().getName());
	
	private Map<String, String> params = null;
	
	public WorkoutContext(Map<String, String> params){
		this.params = params;
	}
	
	public User getUser(){
		User user = new User();
		user.setName(params.get(REQ_KEY_NAME));
		try{
			user.setAge(Integer.parseInt(params.get(REQ_KEY_AGE)));
		}catch(RuntimeException e){}
		user.setMale(REQ_VALUE_SEX_MALE.equals(params.get(REQ_KEY_SEX)));
		return user;
	}
	
	public String getSubTitle(){
		int kingSold = getKingOrSolderScore();
		if(kingSold<0){
			return SUBTITLE_TROPISM;
		}else{
			int schlCrft = getScholarOrCraftsmanScore();
			if(kingSold > schlCrft){
				return SUBTITLE_KINGSOLD;
			}else if(kingSold < schlCrft){
				return SUBTITLE_SCHLCRFT;
			}else{
				return SUBTITLE_UNKNOWN;
			}
		}
	}
	
	public String getTestKey(){
		return params.get(REQ_KEY_TESTKEY);
	}

	public String createTestKey(){
		StringBuffer key = new StringBuffer();
		TestMaster master = TestMasterManager.getInstance().getTestMaster();
		key.append(master.getClass().getName());
		key.append(TESTKEY_OUTER_SEP);
		String[][] test1Array = master.getTropismArray();
		int[] orders = Utils.createOrderArray(test1Array.length);
		for(int id=0; id<orders.length;id++){
			key.append(id);
			key.append(TESTKEY_INNER_SEP);
			key.append(orders[id]);
			key.append(TESTKEY_INNER_SEP);
			if(Utils.getRandomBoolean()){
				key.append(REQ_KEY_QUESTION_FWD_SUFFIX);
			}else{
				key.append(REQ_KEY_QUESTION_REW_SUFFIX);
			}
			if(id+1<orders.length) key.append(TESTKEY_MIDDLE_SEP);
		}
		
		String[][] test2Array = master.getKingSoldArray();
		orders = Utils.createOrderArray(test2Array.length);
		key.append(TESTKEY_OUTER_SEP);
		for(int id=0; id<orders.length;id++){
			key.append(id);
			key.append(TESTKEY_INNER_SEP);
			key.append(orders[id]);
			key.append(TESTKEY_INNER_SEP);
			if(Utils.getRandomBoolean()){
				key.append(REQ_KEY_QUESTION_FWD_SUFFIX);
			}else{
				key.append(REQ_KEY_QUESTION_REW_SUFFIX);
			}
			if(id+1<orders.length) key.append(TESTKEY_MIDDLE_SEP);
		}

		String[][] test3Array = master.getSchlCrftArray();
		orders = Utils.createOrderArray(test3Array.length);
		key.append(TESTKEY_OUTER_SEP);
		for(int id=0; id<orders.length;id++){
			key.append(id);
			key.append(TESTKEY_INNER_SEP);
			key.append(orders[id]);
			key.append(TESTKEY_INNER_SEP);
			if(Utils.getRandomBoolean()){
				key.append(REQ_KEY_QUESTION_FWD_SUFFIX);
			}else{
				key.append(REQ_KEY_QUESTION_REW_SUFFIX);
			}
			if(id+1<orders.length) key.append(TESTKEY_MIDDLE_SEP);
		}
		return key.toString();
	}

	public List<Question> getTropismQuestions(String testKey){
		List<Question> questions = new LinkedList<Question>();
//		if(getKingOrSolderScore()<0){
			questions.addAll(getTropismQuestionSet(testKey));
//		}
		return questions;
	}
	
	protected TreeSet<Question> getTropismQuestionSet(String testKey){
		TreeSet<Question> qList = new TreeSet<Question>();
		StringTokenizer st1 = new StringTokenizer(testKey, TESTKEY_OUTER_SEP);
		String className = st1.nextToken();
		String test1 = st1.nextToken();
		
		TestMaster master = TestMasterManager.getInstance().getTestMaster(className);
		String[][] test1Array = master.getTropismArray();

		StringTokenizer st2 = new StringTokenizer(test1, TESTKEY_MIDDLE_SEP);
		while(st2.hasMoreTokens()){
			String test = st2.nextToken();
			Question q = new Question();
			StringTokenizer st3 = new StringTokenizer(test, TESTKEY_INNER_SEP);
			q.setId(Integer.parseInt(st3.nextToken()));
			q.setOrder(Integer.parseInt(st3.nextToken()));
			q.setStatement(test1Array[q.getId()][0]);
			q.setForward(REQ_KEY_QUESTION_FWD_SUFFIX.equals(st3.nextToken()));
			List<String> answers = new LinkedList<String>();
			if(q.isForward()){
				answers.add(test1Array[q.getId()][1]);
				answers.add(LABEL_ANSWER_2);
				answers.add(LABEL_ANSWER_3);
				answers.add(LABEL_ANSWER_4);
				answers.add(test1Array[q.getId()][2]);
			}else{
				answers.add(test1Array[q.getId()][2]);
				answers.add(LABEL_ANSWER_2);
				answers.add(LABEL_ANSWER_3);
				answers.add(LABEL_ANSWER_4);
				answers.add(test1Array[q.getId()][1]);
			}
			q.setAnswers(answers);
			qList.add(q);
		}
		return qList;
	}
	
	public List<Question> getKingOrSolderQuestions(String testKey){
		List<Question> questions = new LinkedList<Question>();
//		if(getKingOrSolderScore()>=0){
			questions.addAll(getKingOrSolderQuestionSet(testKey));
//		}
		return questions;
	}
	
	protected TreeSet<Question> getKingOrSolderQuestionSet(String testKey){
		TreeSet<Question> qList = new TreeSet<Question>();
		StringTokenizer st1 = new StringTokenizer(testKey, TESTKEY_OUTER_SEP);
		String className = st1.nextToken();
		st1.nextToken();
		String test2 = st1.nextToken();
		
		TestMaster master = TestMasterManager.getInstance().getTestMaster(className);
		String[][] test2Array = master.getKingSoldArray();

		StringTokenizer st2 = new StringTokenizer(test2, TESTKEY_MIDDLE_SEP);
		while(st2.hasMoreTokens()){
			String test = st2.nextToken();
			Question q = new Question();
			StringTokenizer st3 = new StringTokenizer(test, TESTKEY_INNER_SEP);
			q.setId(Integer.parseInt(st3.nextToken()));
			q.setOrder(Integer.parseInt(st3.nextToken()));
			q.setStatement(test2Array[q.getId()][0]);
			q.setForward(REQ_KEY_QUESTION_FWD_SUFFIX.equals(st3.nextToken()));
			List<String> answers = new LinkedList<String>();
			if(q.isForward()){
				answers.add(test2Array[q.getId()][1]);
				answers.add(LABEL_ANSWER_2);
				answers.add(LABEL_ANSWER_3);
				answers.add(LABEL_ANSWER_4);
				answers.add(test2Array[q.getId()][2]);
			}else{
				answers.add(test2Array[q.getId()][2]);
				answers.add(LABEL_ANSWER_2);
				answers.add(LABEL_ANSWER_3);
				answers.add(LABEL_ANSWER_4);
				answers.add(test2Array[q.getId()][1]);
			}
			q.setAnswers(answers);
			qList.add(q);
		}
		return qList;
	}
	
	public List<Question> getScholarOrCraftsmanQuestions(String testKey){
		List<Question> questions = new LinkedList<Question>();
//		if(getKingOrSolderScore()>=0){
			questions.addAll(getScholarOrCraftsmanQuestionSet(testKey));
//		}
		return questions;
	}
	
	protected TreeSet<Question> getScholarOrCraftsmanQuestionSet(String testKey){
		TreeSet<Question> qList = new TreeSet<Question>();
		StringTokenizer st1 = new StringTokenizer(testKey, TESTKEY_OUTER_SEP);
		String className = st1.nextToken();
		st1.nextToken();
		st1.nextToken();
		String test3 = st1.nextToken();
		
		TestMaster master = TestMasterManager.getInstance().getTestMaster(className);
		String[][] test3Array = master.getSchlCrftArray();

		StringTokenizer st2 = new StringTokenizer(test3, TESTKEY_MIDDLE_SEP);
		while(st2.hasMoreTokens()){
			String test = st2.nextToken();
			Question q = new Question();
			StringTokenizer st3 = new StringTokenizer(test, TESTKEY_INNER_SEP);
			q.setId(Integer.parseInt(st3.nextToken()));
			q.setOrder(Integer.parseInt(st3.nextToken()));
			q.setStatement(test3Array[q.getId()][0]);
			q.setForward(REQ_KEY_QUESTION_FWD_SUFFIX.equals(st3.nextToken()));
			List<String> answers = new LinkedList<String>();
			if(q.isForward()){
				answers.add(test3Array[q.getId()][1]);
				answers.add(LABEL_ANSWER_2);
				answers.add(LABEL_ANSWER_3);
				answers.add(LABEL_ANSWER_4);
				answers.add(test3Array[q.getId()][2]);
			}else{
				answers.add(test3Array[q.getId()][2]);
				answers.add("↑");
				answers.add("どっちともいえない");
				answers.add("↓");
				answers.add(test3Array[q.getId()][1]);
			}
			q.setAnswers(answers);
			qList.add(q);
		}
		return qList;
	}
	
//	public String getTestType(){
//		return null;
//	}
	
	public int getKingOrSolderScore(){
		int kingSold = 0;
		int schlCrft = 0;
		Map<String, String> result = 
			Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST1);
		if(result==null||result.isEmpty()) return -1;
		Iterator<String> it = result.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key==null) continue;
			int value = Integer.parseInt(result.get(key));
			if(key.endsWith(REQ_KEY_QUESTION_FWD_SUFFIX)){
				if(value>0) kingSold += value;
				else if(value<0) schlCrft -= value;
			}else if(key.endsWith(REQ_KEY_QUESTION_REW_SUFFIX)){
				if(value<0) kingSold -= value;
				else if(value>0) schlCrft += value;
			}
		}
		return kingSold;
	}
	
	public int getScholarOrCraftsmanScore(){
		int kingSold = 0;
		int schlCrft = 0;
		Map<String, String> result = 
			Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST1);
		if(result==null||result.isEmpty()) return -1;
		Iterator<String> it = result.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key==null) continue;
			int value = Integer.parseInt(result.get(key));
			if(key.endsWith(REQ_KEY_QUESTION_FWD_SUFFIX)){
				if(value>0) kingSold += value;
				else if(value<0) schlCrft -= value;
			}else if(key.endsWith(REQ_KEY_QUESTION_REW_SUFFIX)){
				if(value<0) kingSold -= value;
				else if(value>0) schlCrft += value;
			}
		}
		return schlCrft;
	}
	
	public int getKingScore(){
		int king = 0;
		int solder = 0;
		Map<String, String> result = 
			Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST2);
		if(result==null||result.isEmpty()) return -1;
		Iterator<String> it = result.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key==null) continue;
			int value = Integer.parseInt(result.get(key));
			if(key.endsWith(REQ_KEY_QUESTION_FWD_SUFFIX)){
				if(value>0) king += value;
				else if(value<0) solder -= value;
			}else if(key.endsWith(REQ_KEY_QUESTION_REW_SUFFIX)){
				if(value<0) king -= value;
				else if(value>0) solder += value;
			}
		}
		return king;
	}
	
	public int getSolderScore(){
		int king = 0;
		int solder = 0;
		Map<String, String> result = 
			Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST2);
		if(result==null||result.isEmpty()) return -1;
		Iterator<String> it = result.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key==null) continue;
			int value = Integer.parseInt(result.get(key));
			if(key.endsWith(REQ_KEY_QUESTION_FWD_SUFFIX)){
				if(value>0) king += value;
				else if(value<0) solder -= value;
			}else if(key.endsWith(REQ_KEY_QUESTION_REW_SUFFIX)){
				if(value<0) king -= value;
				else if(value>0) solder += value;
			}
		}
		return solder;
	}
	
	public int getScholarScore(){
		int scholar = 0;
		int craftsman = 0;
		Map<String, String> result = 
			Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST3);
		if(result==null||result.isEmpty()) return -1;
		Iterator<String> it = result.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key==null) continue;
			int value = Integer.parseInt(result.get(key));
			if(key.endsWith(REQ_KEY_QUESTION_FWD_SUFFIX)){
				if(value>0) scholar += value;
				else if(value<0) craftsman -= value;
			}else if(key.endsWith(REQ_KEY_QUESTION_REW_SUFFIX)){
				if(value<0) scholar -= value;
				else if(value>0) craftsman += value;
			}
		}
		return scholar;
	}

	public int getCraftsmanScore(){
		int scholar = 0;
		int craftsman = 0;
		Map<String, String> result = 
			Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST3);
		if(result==null||result.isEmpty()) return -1;
		Iterator<String> it = result.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key==null) continue;
			int value = Integer.parseInt(result.get(key));
			if(key.endsWith(REQ_KEY_QUESTION_FWD_SUFFIX)){
				if(value>0) scholar += value;
				else if(value<0) craftsman -= value;
			}else if(key.endsWith(REQ_KEY_QUESTION_REW_SUFFIX)){
				if(value<0) scholar -= value;
				else if(value>0) craftsman += value;
			}
		}
		return craftsman;
	}

	public Map<String, String> getHiddenMap(){
		Map<String, String> result = new HashMap<String, String>();
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			if(key==null) continue;
			//TODO
			//if(key.equals(REQ_ATTRKEY_RESULT)) continue;
			String value = params.get(key);
			if(value==null) continue;
			result.put(key, value);
		}
		return result;
	}
	
	public String getResult(){
		int kingSold = getKingOrSolderScore();
		int schlCrft = getScholarOrCraftsmanScore();
		int king = getKingScore();
		int solder = getSolderScore();
		int scholar = getScholarScore();
		int craftsman = getCraftsmanScore();
		
		
		StringBuffer result = new StringBuffer();
		if(kingSold>schlCrft){
			if(king>solder){
				result.append(LABEL_RESULT_KING);
			}else if(king<solder){
				result.append(LABEL_RESULT_SOLD);
			}else{
				result.append(LABEL_RESULT_KINGSOLD);
			}
		}else if(kingSold<schlCrft){
			if(scholar>craftsman){
				result.append(LABEL_RESULT_SCHL);
			}else if(scholar<craftsman){
				result.append(LABEL_RESULT_CRFT);
			}else{
				result.append(LABEL_RESULT_SCHLCRFT);
			}
		}else{
			result.append(LABEL_RESULT_NOVECTOR);
			if(king>=0&&king>solder){
				result.append(LABEL_RESULT_AND_KING);
			}else if(king>=0&&king<solder){
				result.append(LABEL_RESULT_AND_SOLD);
			}else if(king>=0){
				result.append(LABEL_RESULT_KINGSOLD_UNKNOWN);
			}
			if(scholar>=0&&scholar>craftsman){
				result.append(LABEL_RESULT_AND_SCHL);
			}else if(scholar>=0&&scholar<craftsman){
				result.append(LABEL_RESULT_AND_CRFT);
			}else if(scholar>=0){
				result.append(LABEL_RESULT_SCHLCRFT_UNKNOWN);
			}
		}
		
		return result.toString();
	}

	public Count store(){
		Count c = null;
		try{
			BigTableStoreManager.getInstance().doStore(this);
			//MailStoreManager.getInstance().doStore(this);
			c = BigTableStoreManager.getInstance().getCount(this);
			return c;
		}catch(Exception e){
			log.log(Level.INFO, "格納処理中例外", e);
			return c;
		}
	}
	
	public List<Answer> getTropismAnswers(){
		String testKey = getTestKey();
		List<Answer> aList = new LinkedList<Answer>();
		Map<String, String> map = Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST1);
		if(map.isEmpty()) return aList;
		Iterator<Question> it = getTropismQuestionSet(testKey).iterator();
		while(it.hasNext()){
			Question q = it.next();
			Answer a = new Answer();
			a.setLabel(q.getLabel());
			a.setStatement(q.getStatement());
			String key = Utils.getRequestKey(REQ_KEY_QUESTION_TEST1, q.getId(), q.getOrder(), q.isForward());
			int value = -1; 
			try{
				value = Integer.parseInt(map.get(key));
				if(q.isForward()){
					if(value>0){
						a.setFwdScore(value);
						a.setRewScore(0);
					}else if(value<0){
						a.setFwdScore(0);
						a.setRewScore(value*(-1));
					}else{
						a.setFwdScore(0);
						a.setRewScore(0);
					}
				}else{
					if(value>0){
						a.setFwdScore(0);
						a.setRewScore(value);
					}else if(value<0){
						a.setFwdScore(value*(-1));
						a.setRewScore(0);
					}else{
						a.setFwdScore(0);
						a.setRewScore(0);
					}
				}
				aList.add(a);
			}catch(RuntimeException e){
				log.log(Level.INFO, "WorkoutContext#getTropismAnswers()内処理："+ map.get(key), e);
			}
		}
		return aList;
	}
	public List<Answer> getKingOrSolderAnswers(){
		String testKey = getTestKey();
		List<Answer> aList = new LinkedList<Answer>();
		Map<String, String> map = Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST2);
		if(map.isEmpty()) return aList;
		Iterator<Question> it = getKingOrSolderQuestionSet(testKey).iterator();
		while(it.hasNext()){
			Question q = it.next();
			Answer a = new Answer();
			a.setLabel(q.getLabel());
			a.setStatement(q.getStatement());
			String key = Utils.getRequestKey(REQ_KEY_QUESTION_TEST2, q.getId(), q.getOrder(), q.isForward());
			int value = 0;
			try{
				value = Integer.parseInt(map.get(key));
				if(q.isForward()){
					if(value>0){
						a.setFwdScore(value);
						a.setRewScore(0);
					}else if(value<0){
						a.setFwdScore(0);
						a.setRewScore(value*(-1));
					}else{
						a.setFwdScore(0);
						a.setRewScore(0);
					}
				}else{
					if(value>0){
						a.setFwdScore(0);
						a.setRewScore(value);
					}else if(value<0){
						a.setFwdScore(value*(-1));
						a.setRewScore(0);
					}else{
						a.setFwdScore(0);
						a.setRewScore(0);
					}
				}
				aList.add(a);
			}catch(RuntimeException e){
				log.log(Level.INFO, "WorkoutContext#getKingOrSolderAnswers()内処理：" + map.get(key), e);
			}
		}
		return aList;		
	}
	public List<Answer> getScholarOrCraftsmanAnswers(){
		String testKey = getTestKey();
		List<Answer> aList = new LinkedList<Answer>();
		Map<String, String> map = Utils.getContainsMap(params, REQ_KEY_QUESTION_PREFIX, REQ_KEY_QUESTION_TEST3);
		if(map.isEmpty()) return aList;
		Iterator<Question> it = getScholarOrCraftsmanQuestionSet(testKey).iterator();
		while(it.hasNext()){
			Question q = it.next();
			Answer a = new Answer();
			a.setLabel(q.getLabel());
			a.setStatement(q.getStatement());
			String key = Utils.getRequestKey(REQ_KEY_QUESTION_TEST3, q.getId(), q.getOrder(), q.isForward());
			int value = -1;
			try{
				value = Integer.parseInt(map.get(key));
				if(q.isForward()){
					if(value>0){
						a.setFwdScore(value);
						a.setRewScore(0);
					}else if(value<0){
						a.setFwdScore(0);
						a.setRewScore(value*(-1));
					}else{
						a.setFwdScore(0);
						a.setRewScore(0);
					}
				}else{
					if(value>0){
						a.setFwdScore(0);
						a.setRewScore(value);
					}else if(value<0){
						a.setFwdScore(value*(-1));
						a.setRewScore(0);
					}else{
						a.setFwdScore(0);
						a.setRewScore(0);
					}
				}
				aList.add(a);
			}catch(RuntimeException e){
				log.log(Level.INFO, "WorkoutContext#getScholarOrCraftsmanAnswers()内処理："+map.get(key), e);
			}
		}
		return aList;		
	}
	
	 public String getMessageBody(){
         StringBuffer buf = new StringBuffer();

         //Test key
         StringTokenizer st = new StringTokenizer(getTestKey(), TESTKEY_OUTER_SEP);
         buf.append("\"");
         buf.append(REQ_KEY_TESTKEY);
         buf.append("\",\"");
         buf.append(st.nextToken());
         buf.append("\"\n");

         // Date
         buf.append("\"EvaluatedAt\",");
         buf.append(Utils.now());
         buf.append("\n");

         // TestResult
         String result = Utils.omitTag(getResult());
         buf.append("\"");
         buf.append(REQ_ATTRKEY_RESULT);
         buf.append("\",\"");
         buf.append(result);
         buf.append("\"\n");

         // SummaryScore
         buf.append("\"SUM_");
         buf.append(REQ_KEY_QUESTION_TEST1);
         buf.append("_KINGSOLD\",");
         buf.append(getKingOrSolderScore());
         buf.append("\n");
         buf.append("\"SUM_");
         buf.append(REQ_KEY_QUESTION_TEST1);
         buf.append("_SCHLCRFT\",");
         buf.append(getScholarOrCraftsmanScore());
         buf.append("\n");
         int score = getKingScore();
         if(score>=0){
                 buf.append("\"SUM_");
                 buf.append(REQ_KEY_QUESTION_TEST2);
                 buf.append("_KING\",");
                 buf.append(score);
                 buf.append("\n");
         }
         score = getSolderScore();
         if(score>=0){
                 buf.append("\"SUM_");
                 buf.append(REQ_KEY_QUESTION_TEST2);
                 buf.append("_SOLD\",");
                 buf.append(score);
                 buf.append("\n");
         }
         score = getScholarScore();
         if(score>=0){
                 buf.append("\"SUM_");
                 buf.append(REQ_KEY_QUESTION_TEST3);
                 buf.append("_SCHL\",");
                 buf.append(score);
                 buf.append("\n");
         }
         score = getCraftsmanScore();
         if(score>=0){
                 buf.append("\"SUM_");
                 buf.append(REQ_KEY_QUESTION_TEST3);
                 buf.append("_CRFT\",");
                 buf.append(score);
                 buf.append("\n");
         }

         // Score Detail
         Iterator<String> it = params.keySet().iterator();
         Map<String, String> map = new TreeMap<String, String>();
         while(it.hasNext()){
                 String key = it.next();
                 boolean reverse = false;
                 if(key!=null && (REQ_KEY_QUESTION_PREFIX + REQ_KEY_QUESTION_SEP).equals(key)){
                         StringTokenizer st2 = new StringTokenizer(key, REQ_KEY_QUESTION_SEP);
                         st2.nextToken(); // Q
                         String testType = st2.nextToken();
                         st2.nextToken(); // order
                         String questionNo = st2.nextToken(); // id
                         if(REQ_KEY_QUESTION_REW_SUFFIX.equals(st2.nextToken())){
                                 reverse = true;
                         }
                         int value = Integer.parseInt(Utils.omitZero(params.get(key)));
                         value = reverse?(value*(-1)):value;
                         map.put(testType+questionNo, new Integer(value).toString());
                 }
         }
         it = map.keySet().iterator();
         while(it.hasNext()){
                 buf.append("\"");
                 String key = it.next();
                 buf.append(key);
                 buf.append("\",");
                 buf.append(map.get(key));
                 buf.append("\n");
         }

         return buf.toString();
	 }
}
