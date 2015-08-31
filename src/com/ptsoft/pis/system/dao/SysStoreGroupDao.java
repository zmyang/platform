package com.ptsoft.pis.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysStoreGroup;
import com.ptsoft.pis.system.model.vo.SysStoreGroupMap;

@Repository
public class SysStoreGroupDao extends BaseMybatisDao<SysStoreGroup, String>
{
	@Override
	protected String getMybatisMapperPrefix()
	{
		return "SysStoreGroup";
	}

	@Override
	public List<SysStoreGroup> findAll()
	{
		return getSqlSessionTemplate().selectList("SysStoreGroup_findAll");
	}

	public List<SysStoreGroupMap> getDetails(String grpid)
	{
		return getSqlSessionTemplate().selectList("getDetails", grpid);
	}
	
	public int  getDetailCount(String grpid){
		 return Integer.parseInt(getSqlSessionTemplate().selectOne("getDetailCount", grpid).toString());
	}
	
	/**删除map表*/
	public void deleteDetails(String grpid)
	{
		getSqlSessionTemplate().delete("deleteDetails", grpid);
	}
	
	/**保存map表*/
	public void saveDetails(SysStoreGroupMap groupMap)
	{
		getSqlSessionTemplate().insert("saveDetails", groupMap);
	}
}
