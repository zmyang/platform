package com.ptsoft.pis.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysDataType;

@Repository
public class SysDataTypeDao extends BaseMybatisDao<SysDataType, String>
{
	/**
	 * SQL前缀
	 * */
	public String getMybatisMapperPrefix()
	{
		return "SysDataType";
	}
	
	public List<SysDataType> findAll()
	{
		return null;
	}
	
	public List<SysDataType> findByType(int type)
	{
		return getSqlSessionTemplate().selectList("SysDataType_getByType", type);
	}
	
	public List<SysDataType> findByType(int type, String dicCode)
	{
		Map<String, String> map = new HashMap<String, String>();
		map.put("typeId", String.valueOf(type));
		map.put("dicCode", dicCode);
		
		return getSqlSessionTemplate().selectList("SysDataType_getAvailableByType", map);
	}
	
	public List<SysDataType> getStoreType(Integer tpid,String stcd)
	{	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tpid", tpid);
		map.put("stcd", stcd);
		return getSqlSessionTemplate().selectList("SysDataType_getStoreType", map);
	}
	
	public int getDataTypeCount(int tpid,String tpcd,String dctcd)
	{	
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tpid", tpid);
		map.put("tpcd", tpcd);
		map.put("dctcd", dctcd);
		return (Integer)getSqlSessionTemplate().selectOne("SysDataType_getDataTypeCount", map);
	}
	
	
	public List<SysDataType> getPriceSystemList(int tpid,String itmcd){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("tpid", tpid);
		map.put("itmcd", itmcd);
		return getSqlSessionTemplate().selectList("getPriceSystemList", map);
	}

	public SysDataType findByDctcd(String dctcd) {
		return (SysDataType) this.getSqlSessionTemplate().selectOne("SysDataType_findByDctcd", dctcd);
	}
}
