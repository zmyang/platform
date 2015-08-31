package com.ptsoft.pis.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptsoft.common.base.BaseService;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.pis.system.dao.SysDepartmentDao;
import com.ptsoft.pis.system.model.vo.SysDepartment;

@Service
public class SysDepartmentService extends BaseService<SysDepartment, String>
{
	@Autowired
	private SysDepartmentDao departmentDao;
	
	@SuppressWarnings("rawtypes")
	protected EntityDao getEntityDao()
	{
		return departmentDao;
	}

	@Override
	public void save(SysDepartment entity)
	{
		
	}
	
	/**
	 * 取门店相关的部门
	 * */
	public List<SysDepartment> getDepartments(String storeCode)
	{
		return this.departmentDao.getDepartments(storeCode);
	}
}
