package com.questiontest.entity;

public class Useranswer {
	private int id;
	private String context;
	private int aid;
	public String getContext() {
		return context;
	}
	
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Useranswer [context=" + context + "]";
	}
	
}
