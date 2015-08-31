package com.ptsoft.pis.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysStoreOther;

@Repository
public class SysStoreOtherDao extends BaseMybatisDao<SysStoreOther, String>
{
	@Override
	protected String getMybatisMapperPrefix()
	{
		return "SysStoreOther";
	}

	@Override
	public List<SysStoreOther> findAll()
	{
		return null;
	}
	
}
