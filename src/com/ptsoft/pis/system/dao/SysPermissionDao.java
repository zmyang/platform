package com.ptsoft.pis.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysPermission;
@Repository
public class SysPermissionDao extends BaseMybatisDao<SysPermission, java.lang.Integer>{

	@Override
	protected String getMybatisMapperPrefix() {
		return "SysPermission";
	}
	@Override
	public List<SysPermission> findAll() {
		return null;
	}
	//根据角色id删除权限
	public void deletePermissionByRlId(String rlId){
		this.getSqlSessionTemplate().delete("deletePermissionByRlId", rlId);
	}
}
