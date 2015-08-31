package com.ptsoft.pis.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptsoft.common.base.BaseService;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.pis.system.dao.SysBulletinBoradDao;
import com.ptsoft.pis.system.model.vo.SysBulletinBorad;

@Service
public class SysBulletinBoradService extends BaseService<SysBulletinBorad, Integer>
{
	@Autowired
	private SysBulletinBoradDao bulletinBoradDao;
	
	@SuppressWarnings("rawtypes")
	protected EntityDao getEntityDao() {
		return bulletinBoradDao;
	}

	@Override
	public void save(SysBulletinBorad entity) {
		if (entity.getBdid() == 0)
		{
			bulletinBoradDao.insert(entity);
		}
		else
		{
			// 更新User表
			bulletinBoradDao.update(entity);
		}
	}
	
	public List<SysBulletinBorad> getSysBulletinBoradList(){
		return bulletinBoradDao.findAll();
	}
	
	public SysBulletinBorad getSysBulletinBoradByBdid(int bdid){
		return bulletinBoradDao.getById(bdid);
	}
	
	
	public void deletebulletinBorad(SysBulletinBorad bulletinBorad){
		bulletinBoradDao.deleteById(bulletinBorad.getBdid());
	}

	
}
