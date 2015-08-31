package com.ptsoft.pis.account.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.ptsoft.pis.account.dao.SysUserDao;
import com.ptsoft.pis.account.model.vo.SysUser;

/**
 * 实现SpringSecurity的UserDetailsService接口,获取用户Detail信息
 * 
 * @author fyy
 */
public class UserDetailServiceImpl implements UserDetailsService
{
	@Autowired
	private SysUserDao userDao;

	public UserDetails loadUserByUsername(String lgnNm) throws UsernameNotFoundException, DataAccessException
	{
		List<SysUser> user = userDao.getUserByName(lgnNm);
		if (user == null || user.size() == 0)
		{
			throw new UsernameNotFoundException("用户" + lgnNm + " 不存在");
		}

		// 获取用户所有的权限
		List<String> authorites = userDao.getUserAuthoritiesName(lgnNm);
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (String s : authorites)
		{
			authSet.add(new GrantedAuthorityImpl(s));
		}
		GrantedAuthority[] grantedAuths = authSet.toArray(new GrantedAuthority[authSet.size()]);

		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		// 构建用户对象，包括用户名、密码及权限
		String password = user.get(0).getPswd() == null ? "" : user.get(0).getPswd();

		User userdetail = new User(lgnNm, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);

		return userdetail;
	}

}
