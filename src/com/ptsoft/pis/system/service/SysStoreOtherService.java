package com.ptsoft.pis.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptsoft.common.base.BaseService;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.pis.system.dao.SysStoreOtherDao;
import com.ptsoft.pis.system.model.vo.SysStoreOther;

@Service
public class SysStoreOtherService extends BaseService<SysStoreOther, String>
{
	@Autowired
	private SysStoreOtherDao otherDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected EntityDao getEntityDao()
	{
		return this.otherDao;
	}

	@Override
	public void save(SysStoreOther entity)
	{
		
	}
	
	public String saveSysStoreOther(SysStoreOther entity)
	{
		String msg = null;
		try
		{
			this.otherDao.update(entity);
			msg = "更新数据成功！";
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			msg = "更新数据失败！";
		}
		return msg;
	}
}
