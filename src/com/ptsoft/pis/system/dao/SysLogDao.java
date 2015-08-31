package com.ptsoft.pis.system.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysLog;

@Repository
public class SysLogDao extends BaseMybatisDao<SysLog, java.lang.Integer>
{
	/**
	 * SQL前缀
	 * */
	public String getMybatisMapperPrefix()
	{
		return "SysLog";
	}
	
	public List<SysLog> getLogs(SysLog log)
	{
		return this.getSqlSessionTemplate().selectList("getLogs", log);
	}
	
	public List<SysLog> findAll()
	{
		return null;
	}
}
