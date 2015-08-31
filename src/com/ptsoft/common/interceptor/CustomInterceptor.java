package com.ptsoft.common.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ptsoft.common.Constants;
import com.ptsoft.pis.PisUtils;
import com.ptsoft.pis.account.model.vo.SysUser;

/**
 * session失效拦截器
 * 
 * @author Kingdom
 * 
 */
public class CustomInterceptor implements HandlerInterceptor
{
	private static final String[] NoFilters = new String[] { "index.html", "login.jsp", "doLogin.do", Constants.LOGIN_URL, Constants.LOGOUT_URL };

	/**
	 * SpringMVC 中的Interceptor 是链式的调用的，在一个应用中或者说是在一个请求中可以同时存在多个Interceptor 。
	 * 每个Interceptor 的调用会依据它的声明顺序依次执行，而且最先执行的都是Interceptor
	 * 中的preHandle方法，所以可以在这个方法中进行一些前置初始化操作或者是对当前请求的一个预处理
	 * ，也可以在这个方法中进行一些判断来决定请求是否要继续进行下去。 该方法的返回值是布尔值Boolean 类型的，当它返回为false
	 * 时，表示请求结束，后续的Interceptor 和Controller 都不会再执行； 当返回值为true
	 * 时就会继续调用下一个Interceptor 的preHandle方法，如果已经是最后一个Interceptor
	 * 的时候就会是调用当前请求的Controller 方法。
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		SysUser user = PisUtils.getCurrentUser(request);
		if (user == null)
		{
			System.out.println("*****" + request.getRequestURI());
		}
		else
		{
			System.out.println("****" + user.getLgnNm() + "*" + request.getRequestURI());
		}
		
		
		String uri[] = request.getRequestURI().split("/");
		if (uri.length < 3)
		{
			return false;
		}
		
		String ctrl = uri[2];
		String page = uri[uri.length-1];
		
		boolean beFilter = true;
		for (String s : NoFilters)
		{
			s = s.replace("/", "");
			if (page.equals(s))
			{
				beFilter = false;
				break;
			}
		}
		
		if (beFilter)
		{
			if (user == null // 需要用户验证的系统功能
					|| (user != null && user.getStCd() != null && !"".equals(user.getStCd()) && !ctrl.equals("store")) 	// 门店用户，企图访问总部系统
					|| (user != null && (user.getStCd() == null || "".equals(user.getStCd())) && ctrl.equals("store")) 	// 总部用户，企图访问门店系统
			)
			{
				PrintWriter out = response.getWriter();
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\">");
				builder.append("window.top.location.href=\"");
				builder.append(request.getContextPath() + Constants.LOGIN_URL);
				builder.append("\";</script>");
				out.print(builder.toString());
				out.close();
				return false;
			}
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception
	{

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception
	{

	}

}
