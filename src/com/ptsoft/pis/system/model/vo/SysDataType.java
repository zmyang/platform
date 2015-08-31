package com.ptsoft.pis.system.model.vo;

import java.util.Date;

import com.ptsoft.common.base.BaseEntity;
import com.ptsoft.pis.PisConstants;

public class SysDataType extends BaseEntity
{
	private static final long serialVersionUID = -2589707966121375648L;
	/**
	 * 唯一识别号
	 */
	private String dctcd;
	/**
	 * 类别编码
	 */
	private String tpcd;
	/**
	 *名称 
	 */
	private String tpnm;
	/**
	 * 类型标识
	 */
	private int tpid;
	/**
	 * 数据关联标识
	 */
	private String jnid;
	/**
	 * 描述
	 */
	private String cmnts;
	/**
	 * 数据状态
	 */
	private int sts;
	/**
	 * 排序号
	 */
	private Double stno = 1d;
	/**
	 * 助记码
	 */
	private String mnmnccd;
	
	/**
	 * 数据创建时间
	 */
	private Date crttm;
	/**
	 * 数据最后更新时间
	 */
	private Date lstupdttm;

	

	private String def1;

	private String def2;

	private String def3;

	private String def4;

	private String def5;
	
	//判断是否选中
	private int checked;
	
	//页面标志
	private int operTag;
	
	//产品管理编辑和新增页面价格体系所用字段
	private String itmcd;
	
	private float prc;

	/**状态名称*/
	public String getStsName()
	{
		PisConstants.Available sts = PisConstants.Available.FromKey(this.sts);
		if (sts != null)
		{
			return sts.getText();
		}
		else
		{
			return "-";
		}
	}
	
	/**  
	 * 获取唯一识别号  
	 * @return dctcd 唯一识别号  
	 */
	public String getDctcd()
	{
		return dctcd;
	}

	/**  
	 * 设置唯一识别号  
	 * @param dctcd 唯一识别号  
	 */
	public void setDctcd(String dctcd)
	{
		this.dctcd = dctcd;
	}

	/**  
	 * 获取类别编码  
	 * @return tpcd 类别编码  
	 */
	public String getTpcd()
	{
		return tpcd;
	}

	/**  
	 * 设置类别编码  
	 * @param tpcd 类别编码  
	 */
	public void setTpcd(String tpcd)
	{
		this.tpcd = tpcd;
	}

	/**  
	 * 获取名称  
	 * @return tpnm 名称  
	 */
	public String getTpnm()
	{
		return tpnm;
	}

	/**  
	 * 设置名称  
	 * @param tpnm 名称  
	 */
	public void setTpnm(String tpnm)
	{
		this.tpnm = tpnm;
	}

	/**  
	 * 获取类型标识  
	 * @return tpid 类型标识  
	 */
	public int getTpid()
	{
		return tpid;
	}

	/**  
	 * 设置类型标识  
	 * @param tpid 类型标识  
	 */
	public void setTpid(int tpid)
	{
		this.tpid = tpid;
	}

	/**  
	 * 获取数据关联标识  
	 * @return jnid 数据关联标识  
	 */
	public String getJnid()
	{
		return jnid;
	}

	/**  
	 * 设置数据关联标识  
	 * @param jnid 数据关联标识  
	 */
	public void setJnid(String jnid)
	{
		this.jnid = jnid;
	}

	/**  
	 * 获取描述  
	 * @return cmnts 描述  
	 */
	public String getCmnts()
	{
		return cmnts;
	}

	/**  
	 * 设置描述  
	 * @param cmnts 描述  
	 */
	public void setCmnts(String cmnts)
	{
		this.cmnts = cmnts;
	}

	/**  
	 * 获取数据状态  
	 * @return sts 数据状态  
	 */
	public int getSts()
	{
		return sts;
	}

	/**  
	 * 设置数据状态  
	 * @param sts 数据状态  
	 */
	public void setSts(int sts)
	{
		this.sts = sts;
	}

	/**  
	 * 获取排序号  
	 * @return stno 排序号  
	 */
	public Double getStno()
	{
		return stno;
	}

	/**  
	 * 设置排序号  
	 * @param stno 排序号  
	 */
	public void setStno(Double stno)
	{
		this.stno = stno;
	}

	/**  
	 * 获取数据创建时间  
	 * @return crttm 数据创建时间  
	 */
	public Date getCrttm()
	{
		return crttm;
	}

	/**  
	 * 设置数据创建时间  
	 * @param crttm 数据创建时间  
	 */
	public void setCrttm(Date crttm)
	{
		this.crttm = crttm;
	}

	/**  
	 * 获取数据最后更新时间  
	 * @return lstupdttm 数据最后更新时间  
	 */
	public Date getLstupdttm()
	{
		return lstupdttm;
	}

	/**  
	 * 设置数据最后更新时间  
	 * @param lstupdttm 数据最后更新时间  
	 */
	public void setLstupdttm(Date lstupdttm)
	{
		this.lstupdttm = lstupdttm;
	}

	/**  
	 * 获取助记码  
	 * @return mnmnccd 助记码  
	 */
	public String getMnmnccd()
	{
		return mnmnccd;
	}

	/**  
	 * 设置助记码  
	 * @param mnmnccd 助记码  
	 */
	public void setMnmnccd(String mnmnccd)
	{
		this.mnmnccd = mnmnccd;
	}

	/**  
	 * 获取def1  
	 * @return def1 def1  
	 */
	public String getDef1()
	{
		return def1;
	}

	/**  
	 * 设置def1  
	 * @param def1 def1  
	 */
	public void setDef1(String def1)
	{
		this.def1 = def1;
	}

	/**  
	 * 获取def2  
	 * @return def2 def2  
	 */
	public String getDef2()
	{
		return def2;
	}

	/**  
	 * 设置def2  
	 * @param def2 def2  
	 */
	public void setDef2(String def2)
	{
		this.def2 = def2;
	}

	/**  
	 * 获取def3  
	 * @return def3 def3  
	 */
	public String getDef3()
	{
		return def3;
	}

	/**  
	 * 设置def3  
	 * @param def3 def3  
	 */
	public void setDef3(String def3)
	{
		this.def3 = def3;
	}

	/**  
	 * 获取def4  
	 * @return def4 def4  
	 */
	public String getDef4()
	{
		return def4;
	}

	/**  
	 * 设置def4  
	 * @param def4 def4  
	 */
	public void setDef4(String def4)
	{
		this.def4 = def4;
	}

	/**  
	 * 获取def5  
	 * @return def5 def5  
	 */
	public String getDef5()
	{
		return def5;
	}

	/**  
	 * 设置def5  
	 * @param def5 def5  
	 */
	public void setDef5(String def5)
	{
		this.def5 = def5;
	}

	/**  
	 * 获取checked  
	 * @return checked checked  
	 */
	public int getChecked()
	{
		return checked;
	}

	/**  
	 * 设置checked  
	 * @param checked checked  
	 */
	public void setChecked(int checked)
	{
		this.checked = checked;
	}

	/**  
	 * 获取operTag  
	 * @return operTag operTag  
	 */
	public int getOperTag()
	{
		return operTag;
	}

	/**  
	 * 设置operTag  
	 * @param operTag operTag  
	 */
	public void setOperTag(int operTag)
	{
		this.operTag = operTag;
	}

	/**  
	 * 获取itmcd  
	 * @return itmcd itmcd  
	 */
	public String getItmcd()
	{
		return itmcd;
	}

	/**  
	 * 设置itmcd  
	 * @param itmcd itmcd  
	 */
	public void setItmcd(String itmcd)
	{
		this.itmcd = itmcd;
	}

	/**  
	 * 获取prc  
	 * @return prc prc  
	 */
	public float getPrc()
	{
		return prc;
	}

	/**  
	 * 设置prc  
	 * @param prc prc  
	 */
	public void setPrc(float prc)
	{
		this.prc = prc;
	}
	
}