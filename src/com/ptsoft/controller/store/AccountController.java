package com.ptsoft.controller.store;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptsoft.common.util.ResponseUtils;
import com.ptsoft.pis.PisUtils;
import com.ptsoft.pis.account.model.vo.SysUser;
import com.ptsoft.pis.account.service.UserService;

@Controller("StoreAccountController")
@RequestMapping("/store/account/*")
public class AccountController
{
	@Autowired
	private UserService userService;

	/**
	 * 编辑个人信息
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/profilelEdit.do")
	public String profilelEdit(HttpServletResponse response, HttpServletRequest request, Model model)
	{
		SysUser tUser = PisUtils.getCurrentUser(request);
		SysUser user = this.userService.getById(tUser.getUsrId());
		model.addAttribute("info", user);
		
		return "portal/myProfilel";
	}
	
	/**
	 * 保存个人信息
	 * */
	@RequestMapping("/profilelSave.do")
	public void profilelSave(HttpServletResponse response, SysUser user)
	{
		ResponseUtils.renderText(response, this.userService.profilelSave(user));
	}
	
	/**
	 * 保存个人密码
	 * */
	@RequestMapping("/passwordSave.do")
	public void passwordSave(HttpServletResponse response, SysUser user)
	{
		ResponseUtils.renderText(response, this.userService.passwordSave(user));
	}
}
