package com.questiontest.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.questiontest.entity.User;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	private UserDaoService userdao;
	@Autowired
	private PaperDaoService paperdao;
	@Autowired
	private QuestionDaoService questiondao;
	@Autowired
	private AnswerDaoService answerdao;
	public int insertUser(User user) {
		return userdao.insertUser(user);
	}
	public boolean isesist(String username) {
		return userdao.isextist(username);
	}
	
}
