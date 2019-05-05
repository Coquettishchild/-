package com.questiontest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.questiontest.entity.Answer;
import com.questiontest.entity.ResponseMessage;
import com.questiontest.entity.ResponseObject;
import com.questiontest.entity.Result;
import com.questiontest.entity.Useranswer;
import com.questiontest.service.AnswerDaoService;

@Controller
public class AnswerController {
	@Autowired
	private AnswerDaoService service;

	@RequestMapping(value = "addAnswer.action", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage addAnswer(Answer answer) {
		boolean flag = service.addAnswer(answer);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if (flag) {
			message.setMessage("添加成功");
		} else {
			message.setMessage("添加失败");
		}
		return message;
	}

	// 用户插入答案
	@RequestMapping(value = "addUserAnswer.action", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage addUserAnswer(@RequestBody List<Useranswer> list) {
		ResponseMessage message = new ResponseMessage();
		boolean flag = service.insertAnswer(list);
		message.setFlag(flag);
		if (flag) {
			message.setMessage("提交成功");
		} else {
			message.setMessage("提交失败");
		}
		return message;
	}

	@RequestMapping(value = "deleteAnswer.action", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage deleteAnswer(int id) {
		boolean flag = service.deleteAnswer(id);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if (flag) {
			message.setMessage("删除成功");
		} else {
			message.setMessage("删除失败");
		}
		return message;
	}
	
	@RequestMapping(value = "getresult.action", method = RequestMethod.POST)
	public @ResponseBody ResponseObject getResult(int id) {
		Result re = service.getAnswer(id);
		ResponseObject obj = new ResponseObject();
		obj.setFlag(true);
		obj.setObj(re);
		return obj;
	}
}
