package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.domain.Comment;
import com.blog.domain.Tag;
import com.blog.domain.User;
import com.blog.service.UserService;
import com.blog.util.request.BlogWithTag;
import com.blog.util.request.NewBlog;
import com.blog.util.request.TagList;
import com.blog.util.response.BlogList;
import com.blog.util.response.UserSimpleData;

@Controller
public class UserController {

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	private HttpServletRequest request;
	

	/**
	 * test
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public int test() {
		HttpSession session = request.getSession();
		//Integer ownId = (Integer)session.getAttribute("userId");
		return 200;
	}
}