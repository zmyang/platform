package com.ptsoft.pis.system.model.vo;

import com.ptsoft.common.base.BaseEntity;

public class SysActionFunctionMap extends BaseEntity{
	
	private static final long serialVersionUID = -1098499115234719585L;
	private int mpid;
	private String fnctnid;
	private String actnid;
	private String actnlbl;
	private int checkid; // 判断该角色是否拥有按钮权限 1代表有 0代表无

	public int getMpid() {
		return mpid;
	}

	public void setMpid(int mpid) {
		this.mpid = mpid;
	}

	public String getFnctnid() {
		return fnctnid;
	}

	public void setFnctnid(String fnctnid) {
		this.fnctnid = fnctnid;
	}

	public String getActnid() {
		return actnid;
	}

	public void setActnid(String actnid) {
		this.actnid = actnid;
	}

	public String getActnlbl() {
		return actnlbl;
	}

	public void setActnlbl(String actnlbl) {
		this.actnlbl = actnlbl;
	}

	public int getCheckid() {
		return checkid;
	}

	public void setCheckid(int checkid) {
		this.checkid = checkid;
	}
	
}
