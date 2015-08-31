package com.ptsoft.pis.system.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.ptsoft.common.base.BaseEntity;

public class MolRoleTree extends BaseEntity
{
	private static final long serialVersionUID = 6382016506221264336L;
	private String functnid;
	private String functnnm;
	private List<SysActionFunctionMap> actionFunctionList = new ArrayList<SysActionFunctionMap>();
	private List<MolRoleTree> children;
	private String state = "open";

	public String getFunctnid()
	{
		return functnid;
	}

	public void setFunctnid(String functnid)
	{
		this.functnid = functnid;
	}

	public String getFunctnnm()
	{
		return functnnm;
	}

	public void setFunctnnm(String functnnm)
	{
		this.functnnm = functnnm;
	}

	public List<SysActionFunctionMap> getActionFunctionList()
	{
		return actionFunctionList;
	}

	public void setActionFunctionList(List<SysActionFunctionMap> actionFunctionList)
	{
		this.actionFunctionList = actionFunctionList;
	}

	public List<MolRoleTree> getChildren()
	{
		return children;
	}

	public void setChildren(List<MolRoleTree> children)
	{
		this.children = children;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

}
