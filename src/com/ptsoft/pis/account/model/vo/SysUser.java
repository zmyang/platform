package com.ptsoft.pis.account.model.vo;

import com.ptsoft.common.base.BaseEntity;
import com.ptsoft.pis.PisConstants;

public class SysUser extends BaseEntity
{
	private static final long serialVersionUID = 560087008677615668L;
	
	private int usrId;
	//关联的角色
	private SysRole role;
	private String lgnNm;
	private String usrNm;
	private String pswd;
	private String email;
	private int sts = 0;
	private String stCd;
	
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
	 * 取得用户关联的门店编码
	 * */
	public String getStCd()
	{
		return stCd;
	}

	/**
	 * 设置用户关联的门店编码
	 * */
	public void setStCd(String stCd)
	{
		this.stCd = stCd;
	}

	public int getUsrId()
	{
		return usrId;
	}

	public void setUsrId(int usrId)
	{
		this.usrId = usrId;
	}

	public String getLgnNm()
	{
		return lgnNm;
	}

	public void setLgnNm(String lgnNm)
	{
		this.lgnNm = lgnNm;
	}

	public String getUsrNm()
	{
		return usrNm;
	}

	public void setUsrNm(String usrNm)
	{
		this.usrNm = usrNm;
	}

	public String getPswd()
	{
		return pswd;
	}

	public void setPswd(String pswd)
	{
		this.pswd = pswd;
	}

	public int getSts()
	{
		return sts;
	}

	public void setSts(int sts)
	{
		this.sts = sts;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	/**  
	 * 获取role  
	 * @return role role  
	 */
	public SysRole getRole()
	{
		return role;
	}

	/**  
	 * 设置role  
	 * @param role role  
	 */
	public void setRole(SysRole role)
	{
		this.role = role;
	}
}