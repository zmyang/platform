package com.ptsoft.pis.system.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysAction;

@Repository
public class SysActionDao extends BaseMybatisDao<SysAction, java.lang.Integer>
{
	/**
	 * SQL前缀
	 * */
	public String getMybatisMapperPrefix()
	{
		return "SysAction";
	}
	
	public List<SysAction> getUserActions(Map<String, String> map)
	{
		return this.getSqlSessionTemplate().selectList("getUserActions", map);
	}

	public List<SysAction> findAll()
	{
		return this.getSqlSessionTemplate().selectList("getFunctionList", null);
	}
}
