package com.zhang.entity;

import java.util.List;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import com.zhang.utils.ListSlideView;



/**
 * ²ËÆ×ÁÐ±í
 * */

@Table(name="MenuTable")
public class MenuEntity {

	public MenuEntity() { }
	
	@Column(name="id" ,isId=true)
	public int id;
	
	@Column(name="care")
	public boolean care=false;
	
	public boolean getCare() {
		return care;
	}

	public void setCare(boolean care) {
		this.care = care;
	}

	//²Ëid
	@Column(name="mId")
	public String mId;
	//²ËÃû
	@Column(name="title")
	public String title;
	//
	@Column(name="tags")
	public String tags;
	
	@Column(name="imtro")
	public String imtro;
	
	@Column(name="ingredients")
	public String ingredients;
	
	@Column(name="burden")
	public String burden;
	
	@Column(name="albums")
	public String albums;
	
	public ListSlideView slideView;
	
	public List<StepsEntity> steps;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getImtro() {
		return imtro;
	}

	public void setImtro(String imtro) {
		this.imtro = imtro;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getBurden() {
		return burden;
	}

	public void setBurden(String burden) {
		this.burden = burden;
	}

	public String getAlbums() {
		return albums;
	}

	public void setAlbums(String albums) {
		this.albums = albums;
	}

	public List<StepsEntity> getSteps() {
		return steps;
	}

	public void setSteps(List<StepsEntity> steps) {
		this.steps = steps;
	}
	
	
}
