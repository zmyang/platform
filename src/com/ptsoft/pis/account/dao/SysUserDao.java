package com.ptsoft.pis.account.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.account.model.vo.SysUser;

@Repository
public class SysUserDao extends BaseMybatisDao<SysUser, java.lang.Integer>
{
	/**
	 * SQL前缀
	 * */
	public String getMybatisMapperPrefix()
	{
		return "SysUser";
	}
	
	@SuppressWarnings("rawtypes")
	public Page findByPageRequest(PageRequest pageRequest)
	{
		return pageQuery("getUserList", "getUserListCount", pageRequest);
	}

	public List<String> getUserAuthoritiesName(String userName)
	{
		return this.getSqlSessionTemplate().selectList("getUserAuthoritiesName", userName);
	}

	public List<SysUser> getUserByName(String userName)
	{
		return this.getSqlSessionTemplate().selectList("getUserByName", userName);
	}

	public int getUserCount()
	{
		Object obj = this.getSqlSessionTemplate().selectOne("getUserListCount");
		return Integer.parseInt(obj.toString());
	}
	
	@Override
	public List<SysUser> findAll()
	{
		return this.getSqlSessionTemplate().selectList("findAllUser");
	}
	
	public List<SysUser> findAll(int userType)
	{
		return this.getSqlSessionTemplate().selectList("findAllUserByType", userType);
	}
	
	public SysUser getUserLogin(String lgnNm,String pswd)
	{
		Map<String, String> para =new HashMap<String, String>();
		para.put("lgnNm", lgnNm);
		para.put("pswd", pswd);
		return (SysUser)this.getSqlSessionTemplate().selectOne("getUserLogin", para);
	}
	
	
	public void profilelSave(SysUser user)
	{
		this.getSqlSessionTemplate().update("SysUser_profilelSave", user);
	}
	
	public void passwordSave(SysUser user)
	{
		this.getSqlSessionTemplate().update("SysUser_passwordSave", user);
	}
}
