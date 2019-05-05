package com.questiontest.dao;

import com.questiontest.entity.Questions;

public interface QuestionDaoMapper {
	//添加问题 
		public void addQuestion(Questions q)throws Exception;
	//修改问题
		public void updateQuestion(Questions q)throws Exception;
	//删除问题
		public void deleteQuestion(int  qid)throws Exception;
}
