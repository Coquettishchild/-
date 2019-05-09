package com.questiontest.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.questiontest.dao.UserDaoMapper;
import com.questiontest.entity.User;

@Repository
public class UserDaoService {
	@Resource
	private UserDaoMapper dao;
	/*
	 * 插入用户信息
	 */
	public int insertUser(User user) {
		try {
			dao.insertUser(user);
			return user.getId();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("插入用户信息失败");
			return -1;
		}
	}
	/*
	 * 查询用户名是否存在
	 */
	public boolean isextist(String username) {
		try {
			String str=dao.isextist(username);
			if(str!=null && !"".equals(str)) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("查询用户名是否存在失败");
			return false;
		}
	}
	/*
	 * 查询用户
	 */
	public User getUser(String username) {
		try {
			return dao.getUser(username);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("查询用户失败");
			return null;
		}
	}
	/*
	 * 修改密码
	 */
	public boolean updata(User user) {
		try {
			dao.updata(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("更新失败");
			return false;
		}
	}
	/*
	 * 验证邮箱
	 */
	public boolean confire(String randomcode) {
		try {
			dao.confire(randomcode);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("验证失败");
			return false;
		}
	}
}
