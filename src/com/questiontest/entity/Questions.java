package com.questiontest.entity;

import java.util.List;

public class Questions {
	private int id;
	private String name;
	private List<Answer> answers;
	// 1 选择 2 判断 3 简答
	private int qtype;
	private int pid;
	public Questions() {}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public int getQtype() {
		return qtype;
	}
	public void setQtype(int qtype) {
		this.qtype = qtype;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "Questions [id=" + id + ", name=" + name + ", answers=" + answers + ", qtype=" + qtype + ", pid=" + pid
				+ "]";
	}
	
}
