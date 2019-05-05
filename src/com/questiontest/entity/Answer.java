package com.questiontest.entity;

public class Answer {
	private int id;
	private String content;
	private int qid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Answer() {}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Answer [id=" + id + ", content=" + content + ", qid=" + qid + "]";
	}

}
