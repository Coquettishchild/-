package com.questiontest.entity;

import java.util.List;

public class Result {
	private List<Object> answers;
	private String  pname;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public List<Object> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Object> answers) {
		this.answers = answers;
	}
	
	
}
