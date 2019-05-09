package com.questiontest.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.questiontest.entity.ResponseMessage;
import com.questiontest.entity.ResponseObject;
import com.questiontest.entity.User;
import com.questiontest.service.Service;
import com.questiontest.service.UserDaoService;
import com.questiontest.util.SendMail;

@Controller
public class UserController {
	@Autowired
	private  UserDaoService service;
	
	@RequestMapping(value = "login.action",method = RequestMethod.POST)
	public  @ResponseBody ResponseMessage login(@Param(value = "username")String username,@Param(value = "password")String password,HttpServletRequest request) {
		User user =service.getUser(username);
		ResponseMessage message = new ResponseMessage();
		if(user!=null&&user.getPassword().equals(password)) {
			message.setFlag(true);
			message.setMessage("登陆成功");
			request.getSession().setAttribute("user", user);
		}else if(user==null){
			message.setFlag(false);
			message.setMessage("用户名不存在");
		}else if(user.getPassword()==null||!user.getPassword().equals(password)){
			message.setMessage("密码错误");
			message.setFlag(false);
		}
		return message;
	}
	
	@RequestMapping(value = "zhuce.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage reginster(@Param(value="user") User user,HttpServletRequest request) {
		int flag=service.insertUser(user);
		ResponseMessage message = new ResponseMessage();
		if(flag==-1) {
			message.setFlag(false);
			message.setMessage("注册失败");
		}else {
			message.setFlag(true);
			message.setMessage("注册成功");
			user.setId(flag);
			request.getSession().setAttribute("user", user);
		}
		return message;
	}
	
	@RequestMapping(value="isexist.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage isexist(@Param(value="username") String username) {
		boolean flag =service.isextist(username);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(!flag);
		if(flag) {
			message.setMessage("用户名存在");
		}else {
			message.setMessage("可以注册");
		}
		return message;
	}
	
	@RequestMapping(value="updata.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage updata(@Param("user") User user,HttpServletRequest request) {
		User user2 = (User) request.getSession().getAttribute("user");
		user.setId(user2.getId());
		user.setUsername(user2.getUsername());
		user.setPassword(user2.getPassword());
		System.err.println(user);
		boolean flag=service.updata(user);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if(flag) {
			request.getSession().removeAttribute("user");
			request.getSession().setAttribute("user", user);
			message.setMessage("更新成功");
		}else {
			message.setMessage("更新失败");
		}
		return message;
	}
	
	@RequestMapping(value="getuser.action",method = RequestMethod.POST)
	public @ResponseBody ResponseObject getUser(HttpServletRequest request) {
		Object user = request.getSession().getAttribute("user");
		ResponseObject obj = new ResponseObject();
		obj.setFlag(true);
		obj.setObj(user);
		return obj;
	}
	
	@RequestMapping(value="dropuser.action",method =RequestMethod.POST)
	public @ResponseBody ResponseMessage dropUser(HttpServletRequest request) {
		ResponseMessage message = new ResponseMessage();
		request.getSession().removeAttribute("user");
		message.setFlag(true);
		message.setMessage("登出成功");
		return message;
	}
	
	@RequestMapping(value="updatepassword.action",method = RequestMethod.POST)
	public @ResponseBody ResponseMessage updatapassword(String password,HttpServletRequest request) {
		ResponseMessage message = new ResponseMessage();
		User user = (User) request.getSession().getAttribute("user");
		user.setPassword(password);
		boolean flag=service.updata(user);
		message.setFlag(flag);
		if(flag) {
			request.getSession().removeAttribute("user");
			request.getSession().setAttribute("user", user);
			message.setMessage("更改成功");
		}else {
			message.setMessage("更改失败");
		}
		return message;
	}
	
	@RequestMapping(value="confireemail.action",method=RequestMethod.POST)
	public @ResponseBody ResponseMessage confireEmail(HttpServletRequest request) {
		String email = ((User)request.getSession().getAttribute("user")).getEmail();
		String url = "<a href='http://localhost:8080/QuestionTest/secendhtml/confire.html?email="+email+"'>点击验证</a>";
		SendMail.sendVerfileEmail(email,url);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(true);
		message.setMessage("我们已经向您的邮箱发送了验证消息，请及时处理");
		return message;
	}
	
	@RequestMapping(value="confire.action",method=RequestMethod.POST)
	public @ResponseBody ResponseMessage confire(String email) {
		boolean flag =service.confire(email);
		ResponseMessage message = new ResponseMessage();
		message.setFlag(flag);
		if(flag) {
			message.setMessage("验证成功");
		}else {
			message.setMessage("验证失败");
		}
		return message;
	}
	
	@RequestMapping(value="forget.action",method =RequestMethod.POST)
	public @ResponseBody ResponseMessage forget(String username) {
		ResponseMessage message = new ResponseMessage();
		User user=service.getUser(username);
		if(user!=null) {
			int confire = user.getConfire();
			String email= user.getEmail();
			String password="abc123";
			if(confire==1) {
				message.setFlag(true);
				user.setPassword(password);
				service.updata(user);
				SendMail.sendVerfileEmail(email, password);
				message.setMessage("我们已经重置了您的密码，并发到您的邮箱上了");
			}else {
				message.setFlag(false);
				message.setMessage("您没有绑定邮箱，无法为您找回密码");
			}
		}else {
			message.setFlag(true);
			message.setMessage("用户名不存在");
		}
		return message;
	}
}
