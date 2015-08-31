/**
 * 
 */
package com.ptsoft.pis.account.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.ptsoft.common.annotation.ServiceLog;
import com.ptsoft.common.base.BaseService;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.pis.PisConstants;
import com.ptsoft.pis.account.dao.SysRoleDao;
import com.ptsoft.pis.account.dao.SysUserDao;
import com.ptsoft.pis.account.model.vo.SysRole;
import com.ptsoft.pis.account.model.vo.SysUser;

/**
 * 用户管理业务类
 * 
 * @author
 */
@Service
@SuppressWarnings("unused")
public class UserService extends BaseService<SysUser, java.lang.Integer>
{
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private SysRoleDao roleDao;
	
	private static final Logger logger = Logger.getLogger(UserService.class);

	@SuppressWarnings("rawtypes")
	public EntityDao getEntityDao()
	{
		return this.userDao;
	}

	@Override
	@Cacheable(cacheName = "sysUserCache")
	public List<SysUser> findAll()
	{
		return this.userDao.findAll();
	}

	@Cacheable(cacheName = "sysUserCache")
	public List<SysUser> findAll(int userType)
	{
		return this.userDao.findAll(userType);
	}
	
	/**
	 * 保存用户
	 * 
	 * @param user
	 * @param checkedRoleIds
	 */
	@Override
	@TriggersRemove(cacheName = "sysUserCache", removeAll = true)
	public void save(SysUser user)
	{
		if (user.getUsrId() == 0)
		{
			userDao.insert(user);
		}
		else
		{
			// 更新User表
			userDao.update(user);
		}
		
	}
	
	/**
	 * 删除用户
	 * 
	 * @param user 用户对象
	 */
	@TriggersRemove(cacheName = "sysUserCache", removeAll = true)
	public void deleteUser(SysUser user)
	{
		userDao.deleteById(user.getUsrId());
	}

	/**
	 * 检查用户列表
	 */
	@SuppressWarnings("rawtypes")
	public Page findByPageRequest(PageRequest pr)
	{
		return userDao.findByPageRequest(pr);
	}

	/**
	 * 登录名是否唯一
	 * 
	 * @param loginName
	 *            新登录名
	 * @param oldLoginName
	 *            原始登录名
	 * @return 是否唯一
	 */
	public boolean isNameUnique(String loginName, String oldLoginName)
	{
		if (loginName == null || loginName.equals(oldLoginName))
		{
			return true;
		}
		List<SysUser> users = this.userDao.getUserByName(loginName);
		return users.size() == 0;
	}

	/**
	 * 检查用户记录数
	 */
	public int getUserCount()
	{
		return userDao.getUserCount();
	}

	/**
	 * 根据登录名查用户列表
	 */
	public List<SysUser> getUserByName(String loginName)
	{
		return userDao.getUserByName(loginName);
	}

	/**
	 * 用户登录
	 * 
	 * @param lgnNm
	 * @param pswd
	 * @return
	 */
	@ServiceLog(description = "用户认证")
	public SysUser getUserLogin(String lgnNm, String pswd)
	{
		return userDao.getUserLogin(lgnNm, pswd);
	}
	
	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	@TriggersRemove(cacheName = "sysUserCache", removeAll = true)
	public String profilelSave(SysUser user)
	{
		try
		{
			this.userDao.profilelSave(user);
			return "个人信息保存成功！";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "人个信息保存失败！";
		}
	}
	
	/**
	 * 保存用户
	 * 
	 * @param user
	 */
	@ServiceLog(description = "修改密码")
	@TriggersRemove(cacheName = "sysUserCache", removeAll = true)
	public String passwordSave(SysUser user)
	{
		try
		{
			this.userDao.passwordSave(user);
			return "个人信息保存成功！";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return "人个信息保存失败！";
		}
	}
	
}
