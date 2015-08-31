package com.ptsoft.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptsoft.common.util.ResponseUtils;
import com.ptsoft.pis.system.model.vo.SysService;
import com.ptsoft.pis.system.service.SysServiceService;

@Controller("adminSysServiceController")
@RequestMapping("/admin/sysService/*")
public class SysServiceController
{
	@Autowired
	private SysServiceService svrService;

	/**
	 * 显示服务列表
	 */
	@RequestMapping("/serviceList.do")
	public String serviceList(HttpServletRequest request, HttpServletResponse response)
	{
		return "/admin/service/serviceList";
	}

	/**
	 * 显示服务列表
	 */
	@RequestMapping("/getServiceList.do")
	public void getServiceList(HttpServletRequest request, HttpServletResponse response)
	{
		List<SysService> list = this.svrService.findAll();

		ResponseUtils.renderJsons(response, list);
	}

	/**
	 * 启动指定的服务
	 */
	@RequestMapping("/startService.do")
	public void startService(HttpServletRequest request, HttpServletResponse response, String svrKey)
	{
		this.svrService.toStop(svrKey);

		this.svrService.toStart(svrKey);
	}

	/**
	 * 停止指定的服务
	 */
	@RequestMapping("/stopService.do")
	public void stopService(HttpServletRequest request, HttpServletResponse response, String svrKey)
	{
		this.svrService.toStop(svrKey);
	}
}
