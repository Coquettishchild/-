package com.questiontest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.questiontest.entity.Questions;
import com.questiontest.entity.ResponseMessage;
import com.questiontest.service.QuestionDaoService;

@Controller
public class QuestionController {
	
	@Autowired
	private QuestionDaoService service;
	
	@RequestMapping(value="addQuestion.action",method=RequestMethod.POST)
	public @ResponseBody ResponseMessage addQuestion(@RequestBody Questions question,HttpServletRequest request) {
		int id = (int) request.getSession().getAttribute("addpaperid");
		question.setPid(id);
		request.getSession().removeAttribute("questionid");
		request.getSession().setAttribute("questionid", question.getId());
		System.out.println(question);
		boolean flag=service.addQuestion(question);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if(flag) {
			message.setMessage("添加成功");
		}else {
			message.setMessage("添加失败");
		}
		return message;
	}
	
	@RequestMapping(value="updataQuestion.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage updataQuestion(@RequestBody Questions question) {
		boolean flag =service.updateQuestion(question);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if(flag) {
			message.setMessage("更新成功");
		}else {
			message.setMessage("更新失败");
		}
		return message;
	}
	
	@RequestMapping(value="deleteQuestion.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage deleteQuestion(int id) {
		boolean flag=service.deleteQuestion(id);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if(flag) {
			message.setMessage("删除成功");
		}else {
			message.setMessage("删除失败");
		}
		return message;
	}
	
}
