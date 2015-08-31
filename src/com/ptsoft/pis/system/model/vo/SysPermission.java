package com.ptsoft.pis.system.model.vo;

import com.ptsoft.common.base.BaseEntity;

public class SysPermission extends BaseEntity {

	private static final long serialVersionUID = 3611144850914398100L;

	private int rlid;
	private int mpid;

	public int getRlid() {
		return rlid;
	}

	public void setRlid(int rlid) {
		this.rlid = rlid;
	}

	public int getMpid() {
		return mpid;
	}

	public void setMpid(int mpid) {
		this.mpid = mpid;
	}

}
