package com.ptsoft.pis.system.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysActionFunctionMap;

@Repository
public class SysActionFunctionMapDao extends BaseMybatisDao<SysActionFunctionMapDao, java.lang.Integer>
{
	@Override
	protected String getMybatisMapperPrefix()
	{
		return "SysActionFunctionMap";
	}

	@Override
	public List<SysActionFunctionMapDao> findAll()
	{
		return null;
	}

	public List<SysActionFunctionMap> getRoleActionFunctionMap(String rlId)
	{
		return this.getSqlSessionTemplate().selectList("getRoleActionFunctionMap", rlId);
	}

}
