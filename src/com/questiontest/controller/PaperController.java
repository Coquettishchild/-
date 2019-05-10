package com.questiontest.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.questiontest.entity.Paper;
import com.questiontest.entity.ResponseMessage;
import com.questiontest.entity.ResponseObject;
import com.questiontest.entity.User;
import com.questiontest.service.PaperDaoService;

@Controller
public class PaperController {
	@Autowired
	private PaperDaoService service;

	/*
	 * 添加问卷
	 */
	@RequestMapping(value = "addpaper.action", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage addPaper(Paper paper, HttpServletRequest request) {
		paper.setData(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		User user = (User) request.getSession().getAttribute("user");
		paper.setUid(user.getId());
		ResponseMessage message = new ResponseMessage();
		int flag = service.addPaper(paper);
		request.getSession().removeAttribute("addpaperid");
		request.getSession().setAttribute("addpaperid", paper.getId());
		System.out.println(paper);
		;
		if (flag != -1) {
			message.setFlag(true);
			message.setMessage("创建成功");
		} else {
			message.setFlag(false);
			message.setMessage("创建失败");
		}
		return message;
	}

	/*
	 * 查询单个问卷的所有信息
	 */
	@RequestMapping(value = "quertonepaper.action", method = RequestMethod.POST)
	public @ResponseBody ResponseObject getOnePaper(HttpServletRequest request) {
		int id = (int) request.getSession().getAttribute("addpaperid");
		ResponseObject response = new ResponseObject();
		Paper paper = service.queryOnePaper(id);
		if (paper != null) {
			response.setFlag(true);
			response.setObj(paper);
		} else {
			response.setFlag(false);
			response.setObj(null);
		}
		return response;
	}
	
	/*
	 * 查询一张问卷
	 */
	@RequestMapping(value = "answerpaper.action", method = RequestMethod.POST)
	public @ResponseBody ResponseObject answerpaper(int id, HttpServletRequest request) {
		ResponseObject response = new ResponseObject();
		Paper paper = service.queryOnePaper(id);
		if (paper != null) {
			response.setFlag(true);
			response.setObj(paper);
		} else {
			response.setFlag(false);
			response.setObj(null);
		}
		return response;
	}
	
	/*
	 * 查询问卷列表
	 */
	@RequestMapping(value = "getpaperList.action", method = RequestMethod.POST)
	public @ResponseBody ResponseObject getpaperList(int index, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Paper> list = service.getUserList(user.getId(), index);
		ResponseObject response = new ResponseObject();
		if (list != null) {
			response.setFlag(true);
			response.setObj(list);
		} else {
			response.setObj(null);
			response.setFlag(false);
		}
		return response;
	}

	/*
	 * 普通用户删除问卷
	 */
	@RequestMapping(value = "deletePaper.action", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage deletePaper(@Param("id") String id) {
		int realid = Integer.parseInt(id);
		boolean flag = service.deletePaper(realid);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if (flag) {
			message.setMessage("删除成功");
		} else {
			message.setMessage("删除失败");
		}
		return message;
	}
	
	/*
	 * 修改问卷
	 */
	@RequestMapping(value = "changepaper.action", method = RequestMethod.POST)
	public @ResponseBody ResponseMessage changePaper(int id, HttpServletRequest request) {
		ResponseMessage message = new ResponseMessage();
		request.getSession().removeAttribute("addpaperid");
		request.getSession().setAttribute("addpaperid", id);
		message.setFlag(true);
		message.setMessage("创建成功");
		return message;
	}
}
