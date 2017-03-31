package com.zhang.entity;



/**
 * ≤Ω÷Ë¡–±Ì
 * */

/*@Table(name="StepsTable")*/
public class StepsEntity {

	public StepsEntity() { }
	
	/*@Column(name="id" ,isId=true)*/
	public int id;
	
	/*@Column(name="img")*/
	public String img;
	
	/*@Column(name="step")*/
	public String step;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	
	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public String getStep() {
		return step;
	}


	public void setStep(String step) {
		this.step = step;
	}
	
	
	
}
