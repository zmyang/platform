package com.ptsoft.common.util;

public class ParamUtil
{
	private int posStart;// 从第N个开始计算

	private int pageNum;// 第N页
	private int count;// 每页加载数据

	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	public String getDirection()
	{
		return direction;
	}

	public void setDirection(String direction)
	{
		this.direction = direction;
	}

	private String orderBy;
	private String direction;// Asc ,Desc

	public int getPosStart()
	{
		return posStart;
	}

	public void setPosStart(int posStart)
	{
		this.posStart = posStart;
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}
}
