package com.questiontest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.questiontest.dao.SuperUserDaoMapper;
import com.questiontest.entity.Paper;
import com.questiontest.entity.SuperUser;
import com.questiontest.entity.User;

@Repository
public class SuperDaoService {
	@Autowired
	private SuperUserDaoMapper dao;

	/*
	 * 超级用户登录
	 */
	public SuperUser getUser(String username) {
		SuperUser user = null;
		try {
			user = dao.getUser(username);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取用户失败");
		}
		return user;
	}

	/*
	 * 获取普通用户列表
	 */
	public List<User> getuserlist(int index) {
		int begin, end = 10;
		begin = index * end - end;
		try {
			List<User> list = dao.getuserlist(begin, end);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("查询列表失败");
			return null;
		}
	}

	/*
	 * 获取问卷列表
	 */
	public List<Paper> getpaperList(int index) {
		int begin, end = 10;
		begin = index * end - end;
		try {
			List<Paper> list = dao.getpaperList(begin, end);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("查询列表失败");
			return null;
		}
	}

	/*
	 * 删除普通用户
	 */
	public boolean deleteUser(int id) {
		try {
			dao.deleteUser(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("删除用户失败");
			return false;
		}
	}
	/*
	 * 特定查询一个用户
	 */
	public User getTheUser(int id) {
		try {
			User user = dao.getTheUser(id);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("获取单个用户失败");
			return null;
		}
	}

}
