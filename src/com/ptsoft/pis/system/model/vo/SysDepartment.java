package com.ptsoft.pis.system.model.vo;

import java.sql.Timestamp;

/**门店部门*/
public class SysDepartment
{
	/**流水码*/
	private String dctcd;
	/**部门编码*/
	private String tpcd;
	/**部门名称*/
	private String tpnm;
	/**数据类型-固定为16*/
	private int tpid;
	/**数据关系-固定为0*/
	private String jnid;
	/**描述*/
	private String cmnts;
	/**数据状态*/
	private int sts;
	/**排序号*/
	private float stno;
	/**创建时间*/
	private Timestamp crttm;
	/**更新时间*/
	private Timestamp lstupdttm;
	/**助记码*/
	private String mnmnccd;
	/**门店主键*/
	private String def1;
	/**门店编码*/
	private String def2;
	
	private String def3;
	private String def4;
	private String def5;

	public String getDctcd()
	{
		return dctcd;
	}

	public void setDctcd(String dctcd)
	{
		this.dctcd = dctcd;
	}

	public String getTpcd()
	{
		return tpcd;
	}

	public void setTpcd(String tpcd)
	{
		this.tpcd = tpcd;
	}

	public String getTpnm()
	{
		return tpnm;
	}

	public void setTpnm(String tpnm)
	{
		this.tpnm = tpnm;
	}

	public int getTpid()
	{
		return tpid;
	}

	public void setTpid(int tpid)
	{
		this.tpid = tpid;
	}

	public String getJnid()
	{
		return jnid;
	}

	public void setJnid(String jnid)
	{
		this.jnid = jnid;
	}

	public String getCmnts()
	{
		return cmnts;
	}

	public void setCmnts(String cmnts)
	{
		this.cmnts = cmnts;
	}

	public int getSts()
	{
		return sts;
	}

	public void setSts(int sts)
	{
		this.sts = sts;
	}

	public float getStno()
	{
		return stno;
	}

	public void setStno(float stno)
	{
		this.stno = stno;
	}

	public Timestamp getCrttm()
	{
		return crttm;
	}

	public void setCrttm(Timestamp crttm)
	{
		this.crttm = crttm;
	}

	public Timestamp getLstupdttm()
	{
		return lstupdttm;
	}

	public void setLstupdttm(Timestamp lstupdttm)
	{
		this.lstupdttm = lstupdttm;
	}

	public String getMnmnccd()
	{
		return mnmnccd;
	}

	public void setMnmnccd(String mnmnccd)
	{
		this.mnmnccd = mnmnccd;
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
