package com.questiontest.entity;

import java.util.List;

public class User implements Cloneable{
	private int id;
	private String username;
	private String sex;
	private int age;
	private String email;
	private String password;
	private int confire;
	private String randomcode;
	private List<Paper> papers;
	
	public User(int id, String username, String sex, int age, String email, String password, String randomcode,
			List<Paper> papers) {
		super();
		this.id = id;
		this.username = username;
		this.sex = sex;
		this.age = age;
		this.email = email;
		this.password = password;
		this.randomcode = randomcode;
		this.papers = papers;
	}

	public User() {}
	
	public String getRandomcode() {
		return randomcode;
	}
	public void setRandomcode(String randomcode) {
		this.randomcode = randomcode;
	}
	public int getConfire() {
		return confire;
	}
	public void setConfire(int confire) {
		this.confire = confire;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Paper> getPapers() {
		return papers;
	}
	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", sex=" + sex + ", age=" + age + ", email=" + email
				+ ", password=" + password + ", papers=" + papers + "]";
	}
	@Override
	public User clone()  {
		try {
			return (User)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
