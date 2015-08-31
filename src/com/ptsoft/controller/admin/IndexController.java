package com.ptsoft.controller.admin;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptsoft.common.util.ExportUtil;
import com.ptsoft.common.util.ResponseUtils;
import com.ptsoft.pis.PisUtils;
import com.ptsoft.pis.system.service.MenuService;

@Controller("adminIndexController")
@RequestMapping("/admin/index/*")
public class IndexController
{
	@Autowired
	private MenuService menuService;
	
	/**
	 * 注销
	 * */
	@RequestMapping("/doLogout.do")
	public String doLogout(HttpServletRequest request, HttpServletResponse response)
	{
		return PisUtils.doLogout(request);
	}
	
	/** 
	 * 根据用户权限生成用户可见的菜单 
	 * */
	@RequestMapping("/showUserMenu.do")
	public void showUserMenu(HttpServletRequest request, HttpServletResponse response)
	{
		int roleId = PisUtils.getCurrentUser(request).getRole().getRlId();
		String menuInfo = this.menuService.getUserMenus(roleId);
		
		ResponseUtils.renderJson(response, menuInfo);
	}
	
	/**
	 * 导出Html Table 到Excel
	 * */
	@RequestMapping("/doExport.do")
	public void doExport(HttpServletRequest request, HttpServletResponse response, String columns, String cnts, String title)
	{
	    String url = ExportUtil.exportToExcel(columns, cnts, title, response);
	    ResponseUtils.renderText(response, url);
	}
}
