package com.questiontest.dao;

import java.util.List;

import com.questiontest.entity.Answer;
import com.questiontest.entity.ChoiceAnswer;
import com.questiontest.entity.QuestionAnswer;
import com.questiontest.entity.Useranswer;

public interface AnswerDaoMapper {	
	//添加答案   制作人
	public void addAnswer(Answer answer)throws Exception;
	//删除答案
	public void deleteAnswer(int aid)throws Exception;
	//修改答案
	public void updateAnswer(Answer answer) throws Exception;
	//查询选择的答案
	public ChoiceAnswer choiceAnswer(int id) throws Exception;
	//查询简答的答案
	public QuestionAnswer questionAnswer(int id) throws Exception;
//用户填写
	//插入答案  aid:问题id
	public void insertAnswer(Useranswer useranwer)throws Exception;
	
}
