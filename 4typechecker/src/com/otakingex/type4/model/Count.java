package com.otakingex.type4.model;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdentityType;


import com.otakingex.type4.ViewConstants;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Count implements Serializable {

	private static final long serialVersionUID = 2449036045734092964L;
	@PrimaryKey
	@Persistent
	private String id = ViewConstants.PRIMARYKEY_ID;
	@Persistent
	private int total = 0;
	@Persistent	
	private int kingOrSolder = 0;
	@Persistent
	private int scholarOrCraftsman = 0;
	@Persistent
	private int king = 0;
	@Persistent
	private int solder = 0;
	@Persistent
	private int scholar = 0;
	@Persistent
	private int craftsman = 0;
	@Persistent
	private Date createdAt = new Date();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getKingOrSolder() {
		return kingOrSolder;
	}
	public void setKingOrSolder(int kingOrSolder) {
		this.kingOrSolder = kingOrSolder;
	}
	public int getScholarOrCraftsman() {
		return scholarOrCraftsman;
	}
	public void setScholarOrCraftsman(int scholarOrCraftsman) {
		this.scholarOrCraftsman = scholarOrCraftsman;
	}
	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
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
	public Date getCreatedAt(){
		return createdAt;
	}
	@Override
	public String toString(){
		return "[total=" + total + ":king=" + king + ":solder=" + 
		solder + ":scholar=" + scholar + ":craftsman=" + craftsman + "]";
	}
}
