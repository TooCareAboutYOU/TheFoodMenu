package com.zhang.entity;

import java.util.List;



/**
 * 一级分类标签列表
 * */
/*@Table(name="ClassifyEntity")*/
public class ClassifyEntity {

	/*@Column(name="id" ,isId=true)*/
	private int id;
	
	/*@Column(name="Pclassfiyname")*/
	private String name;
	
	/*@Column(name="PparentId")*/
	private String parentId;
	
	
	private List<ClassifyItemEntity> classList;
	
	public ClassifyEntity() {
		this.name = name;
		this.parentId = parentId;
		this.classList = classList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<ClassifyItemEntity> getClassList() {
		return classList;
	}
	public void setClassList(List<ClassifyItemEntity> classList) {
		this.classList = classList;
	}
	
	
	
	
}
