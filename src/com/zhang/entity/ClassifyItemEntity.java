package com.zhang.entity;



/**
 * 二级分类标签列表
 * */
/*@Table(name="ClassifyItemEntity")*/
public class ClassifyItemEntity {

	
//	@Column(name="id" ,isId=true)
	private String id;
	
	/*@Column(name="ChildName")*/
	private String name;
	
//	@Column(name="ChildparentId")
	private String parentId;
	
	public ClassifyItemEntity() {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	
	
	
}
