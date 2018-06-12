package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.domain.User;
import com.blog.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;
	
	@Autowired
	private HttpServletRequest request;

	/**	
	 * 杩斿洖鐢ㄦ埛鍒楄〃
	 * @param pageNumber 椤电爜
	 * @return 杩斿洖鏌愪竴椤电殑鎵�鏈夌敤鎴蜂俊鎭�
	 */
	@ResponseBody
	@RequestMapping(value = "/allUsers/{pageNumber}", method = RequestMethod.GET)
	public List<User> allUserByPage(@PathVariable("pageNumber") String pageNumber){
		HttpSession session = request.getSession();
		Integer isAdmin = (Integer)session.getAttribute("isAdmin");
		if (isAdmin != 1) {
			return null;
		}
		return adminService.allUserByPage(Integer.parseInt(pageNumber));
	}
	
	/**
	 * 鎸夌収id鏌ユ壘鐢ㄦ埛淇℃伅
	 * @param userId
	 * @return 杩斿洖鐢ㄦ埛鐨勫叿浣撲俊鎭�
	 */
	@ResponseBody
	@RequestMapping(value = "/findUserByID/{userId}", method = RequestMethod.GET)
	public User findUser(@PathVariable("userId") Integer userId) {
		HttpSession session = request.getSession();
		Integer isAdmin = (Integer)session.getAttribute("isAdmin");
		if (isAdmin != 1) {
			return null;
		}
		return adminService.findUserById(userId);
	}

	/**
	 * 鍒犻櫎鐢ㄦ埛
	 * @param userId
	 * @return 鏁存暟绫诲瀷锛�404 浠ｈ〃闈炵鐞嗗憳鎿嶄綔锛�-1 浠ｈ〃鍒犻櫎澶辫触锛�200 浠ｈ〃鍒犻櫎鎴愬姛
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
	public int deleteUser(@PathVariable("userId") Integer userId) {
		HttpSession session = request.getSession();
		Integer isAdmin = (Integer)session.getAttribute("isAdmin");
		if (isAdmin != 1) {
			return 404;
		}
		// 杩欓噷瑕佷慨鏀逛竴涓嬶紝褰撳垹闄ょ敤鎴锋椂锛屼粬鍙戣〃杩囩殑 blog 閮借缃负鍒犻櫎鐘舵��
		// 璇勮涓嶅仛澶勭悊
		if (adminService.deleteUser(userId)) {
			return 200;
		} else {
			return -1;
		}
	}
}
