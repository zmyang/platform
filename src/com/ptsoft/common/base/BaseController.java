package com.ptsoft.common.base;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

/**
 * 常用的Controller方法
 * 
 * @author Partnersoft
 * 
 */
public abstract class BaseController<E>
{
	/** 显示列表数据 */
	public abstract void list(HttpServletResponse response);

	/** 显示编辑页面 */
	public abstract String edit(Model model, String Id);

	/** 显示新增页面 */
	public abstract String create(Model model);

	/** 保存数据 */
	public abstract void save(HttpServletResponse response, E item) throws Exception;
}
