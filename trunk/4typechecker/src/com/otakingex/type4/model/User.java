package com.otakingex.type4.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {

	private static final long serialVersionUID = 3522483200966423951L;
	
	private String name = "";
	private int age = 0;
	private boolean male = true;
	
	public String getName(){ return name; }
	public int getAge(){ return age; }
	public boolean isMale(){ return male; }
	
	public void setName(String name){ this.name = name; }
	public void setAge(int age){ this.age = age; }
	public void setMale(boolean male){ this.male = male; }
	
	@Override
	public String toString(){
		return "[[name=" + name + ":age=" + age + ":male=" + male +"]]";
	}
	

}
