package com.nkl.admin.domain;

import com.nkl.common.domain.BaseDomain;

public class Clazz extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 7499859893243068691L;
	private int clazz_id; // 
	private String clazz_name; // 
	private String note; // 

	private String ids;
	private String random;

	public void setClazz_id(int clazz_id){
		this.clazz_id=clazz_id;
	}

	public int getClazz_id(){
		return clazz_id;
	}

	public void setClazz_name(String clazz_name){
		this.clazz_name=clazz_name;
	}

	public String getClazz_name(){
		return clazz_name;
	}

	public void setNote(String note){
		this.note=note;
	}

	public String getNote(){
		return note;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

}
