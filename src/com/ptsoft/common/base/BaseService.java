package com.ptsoft.common.base;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Transactional
public abstract class BaseService<E, PK extends Serializable>
{
	protected Log log = LogFactory.getLog(getClass());

	@SuppressWarnings("rawtypes")
	protected abstract EntityDao getEntityDao();

	/** 
	 * 保存对象 
	 * 
	 * @param entity 对象实体
	 * */
	public abstract void save(E entity);
	
	/**
	 * 根据主键取得某个对象实体
	 * 
	 * @param id 主键
	 * @return 对象实体
	 */
	public E getById(PK id)
	{
		return (E) getEntityDao().getById(id);
	}

	/***
	 * 返回所有对象实体
	 * 
	 * @return 所有对象实体
	 */
	public List<E> findAll()
	{
		return getEntityDao().findAll();
	}

	/**
	 * 插入对象到数据库
	 * 
	 * @param entity 对象实体
	 */
	public void insert(E entity)
	{
		getEntityDao().insert(entity);
	}

	/**
	 * 根据主键删除指定对象
	 * 
	 * @param id 主键
	 */
	public void removeById(PK id)
	{
		getEntityDao().deleteById(id);
	}

	/**
	 * 更新对象
	 * 
	 * @param entity 对象
	 */
	public void update(E entity)
	{
		getEntityDao().update(entity);
	}

	/***
	 * 
	 * @param entity
	 * @param uniquePropertyNames
	 * @return
	 */
	public boolean isUnique(E entity, String uniquePropertyNames)
	{
		return getEntityDao().isUnique(entity, uniquePropertyNames);
	}
}
