package com.questiontest.controller;

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
import com.questiontest.entity.SuperUser;
import com.questiontest.entity.User;
import com.questiontest.service.PaperDaoService;
import com.questiontest.service.SuperDaoService;

@Controller
public class SuperUserController {
	@Autowired
	private SuperDaoService service;
	@Autowired
	private PaperDaoService pservice;
	
	/*
	 * 超级用户登录
	 */
	@RequestMapping(value="superlogin.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage superlogin(SuperUser user,HttpServletRequest request) {
		SuperUser user2 = service.getUser(user.getUsername());
		ResponseMessage message = new ResponseMessage();
		if(user2==null) {
			message.setFlag(false);
			message.setMessage("用户名不存在");
		}else if(!user.getUserpassword().equals(user2.getUserpassword())){
			message.setFlag(false);
			message.setMessage("密码错误");
		}else {
			request.getSession().setAttribute("supuser", user);
			message.setFlag(true);
			message.setMessage("登陆成功");
		}
		return message;
	}
	
	/*
	 * 获取超级用户信息
	 */
	@RequestMapping(value="getsuperuser.action",method = RequestMethod.POST)
	public @ResponseBody ResponseObject getUser(HttpServletRequest request) {
		Object user = request.getSession().getAttribute("supuser");
		ResponseObject obj = new ResponseObject();
		obj.setFlag(true);
		obj.setObj(user);
		return obj;
	}
	
	/*
	 *获取用户列表 
	 */
	@RequestMapping(value="getuserlist.action",method=RequestMethod.POST)
	public @ResponseBody ResponseObject getuserlist(int index) {
		List<User> list=service.getuserlist(index);
		ResponseObject obj = new ResponseObject();
		if(list!=null) {
			obj.setFlag(true);
			obj.setObj(list);
		}else {
			obj.setFlag(false);
			obj.setObj(null);
		}
		return obj;
	}
	
	/*
	 * 问卷列表
	 */
	@RequestMapping(value="superpaperlist.action",method = RequestMethod.POST)
	public @ResponseBody ResponseObject getpaperlist(int index) {
		List<Paper> list=service.getpaperList(index);
		ResponseObject obj = new ResponseObject();
		if(list!=null) {
			obj.setFlag(true);
			obj.setObj(list);
		}else {
			obj.setFlag(false);
			obj.setObj(null);
		}
		return obj;
	}
	
	/*
	 * 删除用户
	 */
	@RequestMapping(value="deleteuser.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage deleteUser(int id) {
		ResponseMessage message = new ResponseMessage();
		boolean flag = service.deleteUser(id);
		message.setFlag(flag);
		if(flag) {
			message.setMessage("删除成功");
		}else {
			message.setMessage("删除失败");
		}
		return message;
	}
	
	/*
	 * 删除问卷
	 */
	@RequestMapping(value="superdeletePaper.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage deletePaper(@Param("id") String id) {
		int realid =Integer.parseInt(id);
		boolean flag=pservice.deletePaper(realid);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if(flag) {
			message.setMessage("删除成功");
		}else {
			message.setMessage("删除失败");
		}
		return message;
	}
	
	/*
	 * 获取普通用户信息
	 */
	@RequestMapping(value="gettheuser.action",method = RequestMethod.POST)
	public @ResponseBody ResponseObject getTheUser(int id) {
		User user = service.getTheUser(id);
		ResponseObject obj = new ResponseObject();
		if(user==null) {
			obj.setFlag(false);
			obj.setObj(null);
		}else {
			obj.setFlag(true);
			obj.setObj(user);
		}
		return obj;
	}
}
