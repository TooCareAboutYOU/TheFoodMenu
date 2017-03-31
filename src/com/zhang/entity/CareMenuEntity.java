package com.zhang.entity;

import org.xutils.db.annotation.Column;


//@Table(name="CareMenuEntity")
public class CareMenuEntity {

	
//	@Column(name="id" ,isId=true)
	private String id;
	
//	@Column(name="Care_MenuID")
	private String care_menuID;
	
//	@Column(name="Care_MneuTitle")
	private String care_MneuTitle;
	
	/*@Column(name="Care_MneuAlbums")*/
	private String care_MneuAlbums;
	
	@Column(name="Care_MneuTags")
	private String care_MneuTags;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCare_menuID() {
		return care_menuID;
	}

	public void setCare_menuID(String care_menuID) {
		this.care_menuID = care_menuID;
	}

	public String getCare_MneuTitle() {
		return care_MneuTitle;
	}

	public void setCare_MneuTitle(String care_MneuTitle) {
		this.care_MneuTitle = care_MneuTitle;
	}

	/*public String getCare_MneuAlbums() {
		return care_MneuAlbums;
	}

	public void setCare_MneuAlbums(String care_MneuAlbums) {
		this.care_MneuAlbums = care_MneuAlbums;
	}*/

	public String getCare_MneuTags() {
		return care_MneuTags;
	}

	public void setCare_MneuTags(String care_MneuTags) {
		this.care_MneuTags = care_MneuTags;
	}
	
	
}
