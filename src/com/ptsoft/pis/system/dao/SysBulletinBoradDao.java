package com.ptsoft.pis.system.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ptsoft.common.base.BaseMybatisDao;
import com.ptsoft.pis.system.model.vo.SysBulletinBorad;

@Repository
public class SysBulletinBoradDao  extends BaseMybatisDao<SysBulletinBorad, java.lang.Integer>{

	@Override
	protected String getMybatisMapperPrefix() {
		return "SysBulletinBorad";
	}

	@Override
	public List<SysBulletinBorad> findAll() {
		return getSqlSessionTemplate().selectList("getBulletinBoradList",null);
	}

}
