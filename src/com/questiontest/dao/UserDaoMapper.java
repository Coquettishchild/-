package com.questiontest.dao;

import com.questiontest.entity.User;

public interface UserDaoMapper {
	//注册
		//插入用户信息
		public int insertUser(User user) throws Exception;
		//查询用户名是否存在
		public String isextist(String username)throws Exception;
	//登录
		//查询密码
		public User getUser(String username)throws Exception;
		//修改密码
		public void updata(User user)throws Exception;
		//修改confire
		public void confire(String randomcode)throws Exception;
		//邮箱修改，从新验证
		public void updatacon(User user) throws Exception; 
}
