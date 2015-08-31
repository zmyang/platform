package com.ptsoft.pis.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptsoft.common.Constants;
import com.ptsoft.pis.system.dao.SysActionDao;
import com.ptsoft.pis.system.dao.SysFunctionDao;
import com.ptsoft.pis.system.model.vo.SysAction;
import com.ptsoft.pis.system.model.vo.SysFunction;

/**
 * 菜单业务类
 */
@Service
public class MenuService
{
	@Autowired
	private SysActionDao actionDao;

	@Autowired
	private SysFunctionDao functionDao;

	//保存每个功能的子功能
	private Map<String, List<SysFunction>> subFunctionMap = null;

	/**
	 * 获取用户所拥有的权限菜单列表
	 * 
	 * @param rlId 角色ID
	 * @return 用户菜单
	 */
	public String getUserMenus(int rlId)
	{
		List<SysFunction> list = functionDao.getUserFunctions(rlId);
		this.subFunctionMap = new HashMap<String, List<SysFunction>>();
		if (list != null)
		{
			for (SysFunction fun : list)
			{
				if (this.subFunctionMap.get(fun.getPrntId()) == null)
				{
					this.subFunctionMap.put(fun.getPrntId(), new ArrayList<SysFunction>());
				}
				this.subFunctionMap.get(fun.getPrntId()).add(fun);
			}
		}

		StringBuilder builder = new StringBuilder();
		builder.append("{\"menus\":[");
		this.getSubMenus(builder, Constants.MENU_ROOT);
		builder.append("]}");
		String json = builder.toString();
		return json;
	}

	/**
	 * 计算子菜单
	 */
	private void getSubMenus(StringBuilder builder, String prntID)
	{
		List<SysFunction> list = this.subFunctionMap.get(prntID);
		if (list == null)
		{
			return;
		}
		
		int size = list.size();
		for (int i = 0; i < size; i++)
		{
			SysFunction fun = list.get(i);
			builder.append("{\"name\":\"" + fun.getFnNm() + "\",");
			builder.append("\"icon\":\"" + fun.getIcn() + "\",");
			builder.append("\"url\":\"" + (fun.getPage() == null ? "" : fun.getPage()) + "\",");
			builder.append("\"menus\":[");
			getSubMenus(builder, fun.getFnId());
			builder.append("]}");
			if (i != size - 1)
			{
				builder.append(",");
			}
		}
	}

	/**
	 * 根据用户在某个页面的权限，生成功能按钮
	 */
	public String getUserActions(int rlId, String functionID)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("rlId", String.valueOf(rlId));
		map.put("functionID", functionID.trim());

		List<SysAction> list = this.actionDao.getUserActions(map);

		StringBuffer builder = new StringBuffer("<?xml version=\"1.0\"?><toolbar>");
		if (list != null)
		{
			for (SysAction act : list)
			{
				if (!act.getActnid().equals("btnView"))
				{
					builder.append("<item id=\"" + act.getActnid() + "\" type=\"button\" text=\"" + act.getActnlbl() + "\" img=\"" + act.getIcn() + "\" />");
				}
			}
		}
		builder.append("</toolbar>");
		return builder.toString();
	}

}
