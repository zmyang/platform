package com.ptsoft.pis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.ui.Model;

import com.ptsoft.common.Constants;
import com.ptsoft.pis.account.model.vo.SysUser;
import com.ptsoft.pis.system.model.vo.SysStore;
import com.ptsoft.pis.system.model.vo.SysDataType;
import com.ptsoft.pis.system.service.SysDataTypeService;

import java.lang.reflect.InvocationTargetException;

public class PisUtils
{
	/**
	 * 取得当前登录系统的用户
	 */
	public static SysUser getCurrentUser(HttpServletRequest request)
	{
		return (SysUser) request.getSession().getAttribute(Constants.SA_USER);
	}

	/**
	 * 取得当前登录系统的门店
	 */
	public static SysStore getCurrentStore(HttpServletRequest request)
	{
		return (SysStore) request.getSession().getAttribute(Constants.SA_STORE);
	}

	/**
	 * 检查当前用户是否选择了配送中心
	 * */
	public static void checkCurrentDc(SysDataTypeService dtService, HttpServletRequest request, Model model)
	{
		SysDataType currentDc = PisUtils.getCurrentDc(request);
		if(currentDc == null)
		{
			model.addAttribute("currentDc", new SysDataType());
		}
		else
		{
			model.addAttribute("currentDc", currentDc);
		}
		model.addAttribute("sDistribution", PisUtils.list2Option(dtService.findByType(PisConstants.DataType.Distribution.getKey()), "getDctcd", "getTpnm", "", true));
	}
	
	/**
	 * 取得当前选择的配送中心
	 * @param request
	 * @return
	 */
	public static SysDataType getCurrentDc(HttpServletRequest request)
	{
		return (SysDataType) request.getSession().getAttribute(Constants.SA_DCCD);
	}
	
	/**
	 * 注销用户，清空Session
	 * */
	public static String doLogout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.setAttribute(Constants.SA_USER, null);
		session.setAttribute(Constants.SA_STORE, null);
		session.setAttribute(Constants.SA_BIZDT, null);
		session.setAttribute(Constants.SA_DCCD, null);

		return "redirect:/login.do";
	}
	
	/**
	 * 把列表数据转换为界面显示的下拉框数据源
	 * 
	 * @param list 列表数据源
	 * @param valField 下拉框数据源的“值”
	 * @param txtField 下拉框数据源的“文字”
	 * @param selectedVal 选中的项目
	 * @param addNull 是否增加“请选择”空白选项
	 * 
	 * @return 下拉框数据源
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String list2Option(List list, String valField, String txtField, String selectedVal, boolean addNull)
	{
		selectedVal = selectedVal == null ? "" : selectedVal;
		StringBuilder optionStr = new StringBuilder();
		String[] values = selectedVal.split(",");
		
		if (addNull)
		{
			optionStr.append("<option value=\"-1\">-请选择-</option>");
		}
		try
		{
			for (Object item : list)
			{
				Class cls = item.getClass();
				String vfield = cls.getDeclaredMethod(valField).invoke(item).toString();
				String tfield = cls.getDeclaredMethod(txtField).invoke(item).toString();
				if (ArrayUtils.contains(values, vfield))
				{
					optionStr.append("<option value=\"" + vfield + "\" selected=\"selected\">" + tfield + "</option>");
				}
				else
				{
					optionStr.append("<option value=\"" + vfield + "\">" + tfield + "</option>");
				}
			}
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return optionStr.toString();
	}
}
