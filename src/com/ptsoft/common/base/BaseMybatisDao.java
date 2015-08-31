package com.ptsoft.common.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import cn.org.rapid_framework.page.Page;
import cn.org.rapid_framework.page.PageRequest;

public abstract class BaseMybatisDao<E, PK extends Serializable> extends SqlSessionDaoSupport implements EntityDao<E, PK>
{
	protected final Log log = LogFactory.getLog(getClass());

	/** 用于子类覆盖,在insert,update之前调用 */
	protected void prepareObjectForSaveOrUpdate(E o)
	{

	}

	/** 取得SQL语句的前缀 */
	protected abstract String getMybatisMapperPrefix();

	/** 默认Map标识 */
	protected String getFindByIdStatement()
	{
		return getMybatisMapperPrefix() + "_getById";
	}

	/** 默认Map标识 */
	protected String getDeleteStatement()
	{
		return getMybatisMapperPrefix() + "_delete";
	}

	/** 默认Map标识 */
	protected String getInsertStatement()
	{
		return getMybatisMapperPrefix() + "_insert";
	}

	/** 默认Map标识 */
	protected String getUpdateStatement()
	{
		return getMybatisMapperPrefix() + "_update";
	}

	/** 新增对象 */
	public void insert(E entity)
	{
		prepareObjectForSaveOrUpdate(entity);
		getSqlSessionTemplate().insert(getInsertStatement(), entity);
	}

	/** 删除对象 */
	public void deleteById(PK id)
	{
		getSqlSessionTemplate().delete(getDeleteStatement(), id);
	}

	/** 更新对象 */
	public void update(E entity)
	{
		prepareObjectForSaveOrUpdate(entity);
		getSqlSessionTemplate().update(getUpdateStatement(), entity);
	}

	/** 根据主键查对象 */
	@SuppressWarnings("unchecked")
	public E getById(PK primaryKey)
	{
		Object object = getSqlSessionTemplate().selectOne(getFindByIdStatement(), primaryKey);
		return (E) object;
	}

	/** 查所有对象 */
	public abstract List<E> findAll();

	/** 判断是否唯一 */
	public boolean isUnique(E entity, String uniquePropertyNames)
	{
		throw new UnsupportedOperationException();
	}

	public String getCountStatementForPaging(String statementName)
	{
		System.out.println("statementName=============" + statementName);
		return statementName + ".count";
	}

	@SuppressWarnings("all")
	protected Page pageQuery(String statementName, PageRequest pageRequest)
	{
		return pageQuery(statementName, getCountStatementForPaging(statementName), pageRequest);
	}

	@SuppressWarnings("all")
	public Page pageQuery(String statementName, String countStatementName, PageRequest pageRequest)
	{
		@SuppressWarnings("deprecation")
		Number totalCount = (Number) getSqlSessionTemplate().selectOne(countStatementName, pageRequest.getFilters());
		if (totalCount == null || totalCount.longValue() <= 0)
		{
			return new Page(pageRequest, 0);
		}
		Page page = new Page(pageRequest, totalCount.intValue());

		// 其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用.
		// 与getSqlMapClientTemplate().queryForList(statementName,
		// parameterObject)配合使用
		Map filters = new HashMap();
		filters.put("offset", page.getFirstResult());
		filters.put("pageSize", page.getPageSize());
		filters.put("lastRows", page.getFirstResult() + page.getPageSize());
		filters.put("sortColumns", pageRequest.getSortColumns());

		filters.putAll((Map) pageRequest.getFilters());
		List list = getSqlSessionTemplate().selectList(statementName, filters, new RowBounds(page.getFirstResult(), page.getPageSize()));
		page.setResult(list);
		return page;
	}

	public static interface SqlSessionCallback
	{
		public Object doInSession(SqlSession session);

	}
}
