package com.questiontest.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Redict {
	public static void redict(HttpServletRequest request,HttpServletResponse response) {
		String basepath = request.getScheme()+"://"+request.getServerName()
		+"://"+request.getServerPort()+request.getContextPath();
		if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
			response.setHeader("REDIRECT", "REDIRECT");
			response.setHeader("CONTENTPATH", basepath+"/index.html");
			 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}else {
			try {
				response.sendRedirect("../index.html");
			} catch (IOException e) {
				System.err.println("非ajax请求");
				e.printStackTrace();
			}
		}
	}
}
