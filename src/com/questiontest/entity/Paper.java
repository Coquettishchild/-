package com.questiontest.entity;

import java.util.List;

public class Paper {
	private int id;
	private String name;
	private String data;
	private List<Questions> questions;
	private int uid;
	private int answercount;
	
	public int getAnswercount() {
		return answercount;
	}
	public void setAnswercount(int answercount) {
		this.answercount = answercount;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questions> list) {
		this.questions = list;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	

	@Override
	public String toString() {
		return "Paper [name=" + name + ", data=" + data + ", questions=" + questions + ", uid=" + uid + "]";
	}
	
	
}
