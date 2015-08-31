package com.ptsoft.pis.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptsoft.common.base.BaseService;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.pis.system.dao.SysStoreGroupDao;
import com.ptsoft.pis.system.model.vo.SysStoreGroup;
import com.ptsoft.pis.system.model.vo.SysStoreGroupMap;

@Service
public class SysStoreGroupService extends BaseService<SysStoreGroup, String>
{	
	@Autowired
	private SysStoreGroupDao dao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected EntityDao getEntityDao()
	{
		return this.dao;
	}
	
	@Override
	public void save(SysStoreGroup entity)
	{
		SysStoreGroup tmp = this.dao.getById(String.valueOf(entity.getGrpId()));
		if (tmp == null)
		{
			this.dao.insert(entity);
		}
		else
		{
			this.dao.update(entity);
		}
	}

	public String getMaps(String grpid)
	{
		List<SysStoreGroupMap> list = this.dao.getDetails(grpid);
		String data = "";
		for (SysStoreGroupMap sysStoreGroupMap : list) {
			data += sysStoreGroupMap.getStcd() + ",";
		}
		return data;
	}
	
	public void saveMap(String grpid, String[] stCds)
	{	
		SysStoreGroupMap groupMap;
		this.dao.deleteDetails(grpid);
		
		for (String stCd : stCds) 
		{
			if(stCd.equals(""))
				continue;
			
			groupMap = new SysStoreGroupMap();
			groupMap.setGrpid(Integer.parseInt(grpid));
			groupMap.setStcd(stCd);
			
			this.dao.saveDetails(groupMap);
		}
	}
	
	public int  getDetailCount(String grpid){
		 return dao.getDetailCount(grpid);
	}
	
	public void deleteStoreGroup(String grpid){
			//删除该组下的门店
			this.dao.deleteDetails(grpid);
			this.dao.deleteById(grpid);
	}
}
