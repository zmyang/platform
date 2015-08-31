package com.ptsoft.common.base;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

public interface EntityDao<E, PK extends Serializable>
{
	/** 唯一查 */
	public E getById(PK id) throws DataAccessException;

	/** 根据id删 */
	public void deleteById(PK id) throws DataAccessException;

	/** 插入数据 */
	public void insert(E entity) throws DataAccessException;

	/** 更新数据 */
	public void update(E entity) throws DataAccessException;

	/** 是否唯一 */
	public boolean isUnique(E entity, String uniquePropertyNames) throws DataAccessException;

	/** 全部查 */
	public List<E> findAll() throws DataAccessException;

}
