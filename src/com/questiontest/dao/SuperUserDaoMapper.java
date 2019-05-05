package com.questiontest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.questiontest.entity.Paper;
import com.questiontest.entity.SuperUser;
import com.questiontest.entity.User;

public interface SuperUserDaoMapper {
	//登录
		public SuperUser getUser(String username)throws Exception;
	//获取所有普通用户列表
		public List<User> getuserlist(@Param("begin") int begin,@Param("end") int end)throws Exception;
	//获取所有问卷列表
		public List<Paper> getpaperList(@Param("begin") int begin,@Param("end") int end) throws Exception;
	//删除用户
		public void deleteUser(int id) throws Exception;
	//特定查询一个用户
		public User getTheUser(int id) throws Exception;
}
