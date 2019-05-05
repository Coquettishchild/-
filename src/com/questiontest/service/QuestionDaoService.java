package com.questiontest.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questiontest.dao.QuestionDaoMapper;
import com.questiontest.entity.Answer;
import com.questiontest.entity.Questions;

@Repository
public class QuestionDaoService {
	@Resource
	private QuestionDaoMapper dao;
	@Resource
	private AnswerDaoService adao;

	/*
	 * 添加问题
	 */
	@Transactional
	public boolean addQuestion(Questions q) {
		try {
			dao.addQuestion(q);
			if (q.getAnswers() != null) {
				for (Answer an : q.getAnswers()) {
					if (!an.getContent().equals("")) {
						an.setQid(q.getId());
						adao.addAnswer(an);
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("添加问题失败");
			return false;
		}
	}

	/*
	 * 修改问题
	 */
	@Transactional
	public boolean updateQuestion(Questions q) {
		try {
			dao.updateQuestion(q);
			List<Answer> list = q.getAnswers();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getId() != -1 && !list.get(i).getContent().equals("")) {
						adao.updateAnswer(list.get(i));
					} else if(list.get(i).getId()==-1 && !list.get(i).getContent().equals("")){
						adao.addAnswer(list.get(i));
					}else if(list.get(i).getId() != -1) {
						adao.deleteAnswer(list.get(i).getId());
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("修改答案失败");
			return false;
		}
	}

	/*
	 * 删除问题
	 */
	public boolean deleteQuestion(int qid) {
		try {
			dao.deleteQuestion(qid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("删除问题");
			return false;
		}
	}
}
