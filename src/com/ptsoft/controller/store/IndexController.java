package com.ptsoft.controller.store;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptsoft.common.util.ExportUtil;
import com.ptsoft.common.util.ResponseUtils;
import com.ptsoft.pis.PisUtils;
import com.ptsoft.pis.system.service.MenuService;

@Controller("storeIndexController")
@RequestMapping("/store/index/*")
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
	
	/** 
	 * 结束营业 
	 * */
	@RequestMapping("/endOfDay.do")
	public void endOfDay(HttpServletRequest request, HttpServletResponse response)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("STS", "0");
		map.put("MSG", "生成新营业日期失败！");
		
		
		ResponseUtils.renderJson(response, map);
	}
}
