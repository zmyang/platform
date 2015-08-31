package com.ptsoft.pis.system.model.vo;

import com.ptsoft.common.base.BaseEntity;

public class SysAction extends BaseEntity
{
	private static final long serialVersionUID = -405465225454854742L;

	private String actnid;

	private String actnlbl;

	private String atcnky;

	private String tltp;

	private String icn;

	private Double stno;

	private String def1;

	private String def2;

	private String def3;

	private String def4;

	private String def5;

	public String getActnid()
	{
		return actnid;
	}

	public void setActnid(String actnid)
	{
		this.actnid = actnid;
	}

	public String getActnlbl()
	{
		return actnlbl;
	}

	public void setActnlbl(String actnlbl)
	{
		this.actnlbl = actnlbl;
	}

	public String getAtcnky()
	{
		return atcnky;
	}

	public void setAtcnky(String atcnky)
	{
		this.atcnky = atcnky;
	}

	public String getTltp()
	{
		return tltp;
	}

	public void setTltp(String tltp)
	{
		this.tltp = tltp;
	}

	public String getIcn()
	{
		return icn;
	}

	public void setIcn(String icn)
	{
		this.icn = icn;
	}

	public Double getStno()
	{
		return stno;
	}

	public void setStno(Double stno)
	{
		this.stno = stno;
	}

	public String getDef1()
	{
		return def1;
	}

	public void setDef1(String def1)
	{
		this.def1 = def1;
	}

	public String getDef2()
	{
		return def2;
	}

	public void setDef2(String def2)
	{
		this.def2 = def2;
	}

	public String getDef3()
	{
		return def3;
	}

	public void setDef3(String def3)
	{
		this.def3 = def3;
	}

	public String getDef4()
	{
		return def4;
	}

	public void setDef4(String def4)
	{
		this.def4 = def4;
	}

	public String getDef5()
	{
		return def5;
	}

	public void setDef5(String def5)
	{
		this.def5 = def5;
	}
}