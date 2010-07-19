package com.otakingex.type4.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class PersonalSummary {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key key = null;
	@Persistent
	private String name = null;
	@Persistent
	private int age = -1;
	@Persistent
	private boolean male = true;
	@Persistent
	private int kingOrSolderScore = -1;
	@Persistent
	private int scholarOrCraftsmanScore = -1;
	@Persistent
	private int kingScore = -1;
	@Persistent
	private int solderScore = -1;
	@Persistent
	private int scholarScore = -1;
	@Persistent
	private int craftsmanScore = -1;
	@Persistent
	private Date evaluatedAt = new Date();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isMale() {
		return male;
	}
	public void setMale(boolean male) {
		this.male = male;
	}
	public int getKingOrSolderScore() {
		return kingOrSolderScore;
	}
	public void setKingOrSolderScore(int kingOrSolderScore) {
		this.kingOrSolderScore = kingOrSolderScore;
	}
	public int getScholarOrCraftsmanScore() {
		return scholarOrCraftsmanScore;
	}
	public void setScholarOrCraftsmanScore(int scholarOrCraftsmanScore) {
		this.scholarOrCraftsmanScore = scholarOrCraftsmanScore;
	}
	public int getKingScore() {
		return kingScore;
	}
	public void setKingScore(int kingScore) {
		this.kingScore = kingScore;
	}
	public int getSolderScore() {
		return solderScore;
	}
	public void setSolderScore(int solderScore) {
		this.solderScore = solderScore;
	}
	public int getScholarScore() {
		return scholarScore;
	}
	public void setScholarScore(int scholarScore) {
		this.scholarScore = scholarScore;
	}
	public int getCraftsmanScore() {
		return craftsmanScore;
	}
	public void setCraftsmanScore(int craftsmanScore) {
		this.craftsmanScore = craftsmanScore;
	}
	public Date getEvaluatedAt() {
		return evaluatedAt;
	}
	public void setEvaluatedAt(Date evaluatedAt) {
		this.evaluatedAt = evaluatedAt;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public Key getKey() {
		return key;
	}
	
}
