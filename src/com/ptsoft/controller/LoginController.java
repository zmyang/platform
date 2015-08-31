package com.ptsoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptsoft.common.Constants;
import com.ptsoft.common.util.ResponseUtils;
import com.ptsoft.pis.account.model.vo.SysUser;
import com.ptsoft.pis.account.service.UserService;
import com.ptsoft.pis.system.service.SysStoreService;

/**
 * 登录系统，验证用户，成功后跳转到相应页面
 */
@Controller
public class LoginController
{
	@Autowired
	private UserService userService;
	@Autowired
	private SysStoreService storeService;
	
	/**
	 * 验证用户登录
	 */
	@RequestMapping("/doLogin.do")
	public void doLogin(HttpServletResponse response, HttpServletRequest request, Model model, String username, String password)
	{
		SysUser sysUser = userService.getUserLogin(username, password);
		String msg = "";
		String url = "";
		if (sysUser != null)
		{
			request.getSession().setAttribute(Constants.SA_USER, sysUser);
			url = this.toPage(request);
		}
		else
		{
			msg = "登录失败，请重试！";
		}
		
		String str = "{\"msg\":\"" + msg + "\",\"url\":\"" + url + "\"}";  
		ResponseUtils.renderJson(response, str);
	}
	
	/**
	 * 跳转到指定页面
	 * */
	private String toPage(HttpServletRequest request)
	{
		SysUser sysUser = (SysUser) request.getSession().getAttribute(Constants.SA_USER);
		if (sysUser.getStCd() != null && !"".equals(sysUser.getStCd()))	//门店用户
		{
			request.getSession().setAttribute(Constants.SA_STORE, storeService.getById(sysUser.getStCd()));
			return "store/index.do";
		}
		else							//总部用户
		{
			request.getSession().setAttribute(Constants.SA_STORE, null);
			request.getSession().setAttribute(Constants.SA_BIZDT, null);
			return "admin/index.do";
		}
	}
}
