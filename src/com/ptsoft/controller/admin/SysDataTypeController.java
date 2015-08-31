package com.ptsoft.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptsoft.common.util.ResponseUtils;
import com.ptsoft.pis.PisConstants;
import com.ptsoft.pis.system.model.vo.SysDataType;
import com.ptsoft.pis.system.service.SysDataTypeService;

@Controller
@RequestMapping("/admin/sysDataType/*")
public class SysDataTypeController
{
	@Autowired
	private SysDataTypeService dataTypeService;

	/**
	 * 进入字典表界面
	 * @param type 字典数据类型
	 * */
	@RequestMapping("/toPage.do")
	public String toPage(HttpServletRequest request, HttpServletResponse response, Model model, int type)
	{
		model.addAttribute("typeID", type);
		model.addAttribute("typeName", PisConstants.DataType.FromKey(type).getText());
		model.addAttribute("statusSelect", PisConstants.Available.ToOptionString());
		
		return "admin/dataType/dataTypeList";
	}
	
	/**
	 * 字典数据列表 
	 * */
	@RequestMapping("/dataList.do")
	public void dataList(HttpServletRequest request, HttpServletResponse response, int type)
	{
		List<SysDataType> list = this.dataTypeService.findByType(type);
		ResponseUtils.renderJsons(response, list);
	}

	/**
	 * 显示编辑页面
	 */
	@RequestMapping("/editData.do")
	public void editData(HttpServletRequest request, HttpServletResponse response, String dicCode)
	{
		SysDataType dataType = this.dataTypeService.getById(dicCode);
		ResponseUtils.renderJson(response, dataType);
	}
	
	/** 保存公告 */
	@RequestMapping("/saveData.do")
	public void saveData(HttpServletRequest request, HttpServletResponse response, SysDataType dataType)
	{
		String message = null;
		try
		{
			this.dataTypeService.save(dataType);
			message = "数据保存成功！";
		}
		catch (Exception e)
		{
			message = "数据保存失败！";
			e.printStackTrace();
		}
		ResponseUtils.renderText(response, message);
	}

	
}
