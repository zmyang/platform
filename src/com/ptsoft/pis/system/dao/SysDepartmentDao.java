package com.ptsoft.pis.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysDepartment;

@Repository
public class SysDepartmentDao extends BaseMybatisDao<SysDepartment, String>
{
	/**
	 * SQL前缀
	 * */
	public String getMybatisMapperPrefix()
	{
		return "SysDepartment";
	}

	@Override
	public List<SysDepartment> findAll()
	{
		return null;
	}
	
	public List<SysDepartment> getDepartments(String storeCode)
	{
		return this.getSqlSessionTemplate().selectList("SysDepartment_getByStore", storeCode);
	}
}
