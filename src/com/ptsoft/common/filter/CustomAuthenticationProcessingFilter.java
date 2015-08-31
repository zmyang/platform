/**
 * 
 */
package com.ptsoft.common.filter;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.Authentication;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

import com.ptsoft.common.Constants;
import com.ptsoft.common.util.SpringSecurityUtils;
import com.ptsoft.pis.account.model.vo.SysUser;
import com.ptsoft.pis.account.service.UserService;

/**
 * 自定义认证处理过虑器 作用：为了实现登录后的处理，比如设置session中的变量
 * 
 */
public class CustomAuthenticationProcessingFilter extends AuthenticationProcessingFilter
{
	@Autowired
	private UserService userService;

	/**
	 * 登录成功后的处理
	 */
	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException
	{
		String loginName = SpringSecurityUtils.getCurrentUserName();

		SysUser user = userService.getUserByName(loginName).get(0);

		HttpSession session = request.getSession();
		session.setAttribute(Constants.SA_USER, user);
		// String menuXml =
		// userService.getMenuXml(user.getId(),request.getContextPath());
		// session.setAttribute("menuXml", menuXml);
		super.onSuccessfulAuthentication(request, response, authResult);
	}
}
