package com.ptsoft.pis.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysStore;

@Repository
public class SysStoreDao extends BaseMybatisDao<SysStore, String>
{
	@Override
	protected String getMybatisMapperPrefix()
	{
		return "SysStore";
	}

	@Override
	public List<SysStore> findAll()
	{
		return getSqlSessionTemplate().selectList("SysStore_findAll");
	}
	
	public List<SysStore> searchItems(String parm)
	{
		return getSqlSessionTemplate().selectList("SysStore_searchItems", "%" + parm + "%");
	}
	
	public int getStoreByStcd(String stcd)
	{
		return (Integer)getSqlSessionTemplate().selectOne("SysStore_getStoreByStcd", stcd);
	}
	
	public int getStoreByStnm(String stnm,String stcd)
	{
		Map<String, String> map=new HashMap<String, String>();
		map.put("stnm", stnm);
		map.put("stcd", stcd);
		return (Integer)getSqlSessionTemplate().selectOne("SysStore_getStoreByStnm", map);
	}

	public List<SysStore> findByBrndcd(String brndcd) 
	{
		return this.getSqlSessionTemplate().selectList("SysStore_findByBrndcd", brndcd);
	}

	public List<SysStore> getStListByStcd(String stcd) 
	{
		return this.getSqlSessionTemplate().selectList("SysStore_findAll", stcd);
	}

}
