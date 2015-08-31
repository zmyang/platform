/**
 * 
 */
package com.ptsoft.pis.account.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.account.model.vo.SysRole;

/**
 * 角色操作DAO类
 */
@Repository
public class SysRoleDao extends BaseMybatisDao<SysRole, java.lang.Integer>
{
	@Override
	protected String getMybatisMapperPrefix()
	{
		return "SysRole";
	}

	@Override
	public List<SysRole> findAll()
	{
		return getSqlSessionTemplate().selectList("findAllRole");
	}
	
}
