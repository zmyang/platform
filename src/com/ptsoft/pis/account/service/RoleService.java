/**
 * 
 */
package com.ptsoft.pis.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.ptsoft.common.base.BaseService;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.pis.account.dao.SysRoleDao;
import com.ptsoft.pis.account.model.vo.SysRole;

/**
 * 角色管理业务类
 */
@Service
public class RoleService extends BaseService<SysRole, java.lang.Integer>
{
	@Autowired
	private SysRoleDao roleDao;

	@SuppressWarnings("rawtypes")
	protected EntityDao getEntityDao()
	{
		return roleDao;
	}

	@Override
	@Cacheable(cacheName = "sysRoleCache")
	public List<SysRole> findAll()
	{
		return this.roleDao.findAll();
	}

	@Override
	@TriggersRemove(cacheName = "sysRoleCache", removeAll = true)
	public void save(SysRole entity)
	{
		if (entity.getRlId() == 0)
		{
			roleDao.insert(entity);
		}
		else
		{
			roleDao.update(entity);
		}
	}

	/**
	 * 删除角色
	 * 
	 * @param role
	 */
	@TriggersRemove(cacheName = "sysRoleCache", removeAll = true)
	public void deleteRole(SysRole role)
	{
		roleDao.deleteById(role.getRlId());
	}

	public SysRole getRoleById(Integer roleId)
	{
		return (SysRole) this.roleDao.getById(roleId);
	}

	/**
	 * 把列表转换为下拉选项内容
	 * 
	 * @param list 数据源
	 */
	public String listToOptions(List<SysRole> list)
	{
		if (list == null)
			return "";

		StringBuilder builder = new StringBuilder();
		for (SysRole type : list)
		{
			// <option value="WY">Wyoming</option>
			builder.append("<option value=\"" + type.getRlId() + "\">" + type.getRlNm() + "</option>");
		}
		return builder.toString();
	}
}
