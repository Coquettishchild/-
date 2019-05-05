package com.questiontest.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.questiontest.dao.AnswerDaoMapper;
import com.questiontest.dao.PaperDaoMapper;
import com.questiontest.entity.Answer;
import com.questiontest.entity.ChoiceAnswer;
import com.questiontest.entity.Paper;
import com.questiontest.entity.QuestionAnswer;
import com.questiontest.entity.Questions;
import com.questiontest.entity.Result;
import com.questiontest.entity.Useranswer;

@Repository
public class AnswerDaoService {
	@Resource
	private AnswerDaoMapper dao;
	@Resource
	private PaperDaoMapper pdao;

	/*
	 * 作者插入答案
	 * 
	 */
	public boolean addAnswer(Answer answer) {
		try {
			dao.addAnswer(answer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("作者插入答案失败");
			;
			return false;
		}
	}

	/*
	 * 用户插入答案
	 * 
	 */
	@Transactional
	public boolean insertAnswer(List<Useranswer> list) {
		try {
			for (int i = 0; i < list.size(); i++) {
				dao.insertAnswer(list.get(i));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("用户插入答案失败");
			return false;
		}
	}

	/*
	 * 作者删除答案
	 */
	public boolean deleteAnswer(int id) {
		try {
			dao.deleteAnswer(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("删除答案失败");
			return false;
		}
	}

	/*
	 * 作者更新答案
	 */
	public boolean updateAnswer(Answer answer) {
		try {
			dao.updateAnswer(answer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("更新答案失败");
			return false;
		}
	}

	/*
	 * 查询问题的答案
	 */
	@Transactional
	public Result getAnswer(int pid) {
		System.err.println("begin");
		Paper paper = null;
		Result re = new Result();
		try {
			paper = pdao.queryOnePaper(pid);
			re.setPname(paper.getName());
			List<Object> list = new ArrayList<Object>();
			for (int i = 0; i < paper.getQuestions().size(); i++) {
				Questions q = paper.getQuestions().get(i);
				if (q.getQtype() == 1) {
					try {
						ChoiceAnswer ch = dao.choiceAnswer(q.getId());
						ch.setName(q.getName());
						list.add(ch);
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println("查询选择问题失败");
					}
				} else if (q.getQtype() == 2) {
					try {
//						List<String> qlist= dao.questionAnswer(q.getId());
						QuestionAnswer qa =  dao.questionAnswer(q.getId());
//						qa.setAnswers(qlist);
						qa.setName(q.getName());
						list.add(qa);
					} catch (Exception e) {
						e.printStackTrace();
						System.err.println("查询简答问题失败");
					}
				}
			}
			re.setAnswers(list);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
