package com.ptsoft.pis.account.model.vo;

import com.ptsoft.common.base.BaseEntity;
import com.ptsoft.pis.PisConstants;

/**
 * 角色
 * */
public class SysRole extends BaseEntity
{
	private static final long serialVersionUID = 3767819007561368304L;

	/**角色ID*/
	private int rlId;
	
	/**角色名称*/
	private String rlNm;
	
	/**状态*/
	private int sts;
	
	/**备注*/
	private String cmnts;

	/**角色类型，区分总部/门店角色*/
	private int sysTp;
	
	public SysRole()
	{
		
	}
	
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

	/**类型名称*/
	public String getTypeName()
	{
		PisConstants.SystemType type = PisConstants.SystemType.FromKey(this.sysTp);
		if (type != null)
		{
			return type.getText();
		}
		else
		{
			return "-";
		}
	}
	
	/**  
	 * 获取角色ID  
	 * @return rlId 角色ID  
	 */
	public int getRlId()
	{
		return rlId;
	}

	/**  
	 * 设置角色ID  
	 * @param rlId 角色ID  
	 */
	public void setRlId(int rlId)
	{
		this.rlId = rlId;
	}

	/**  
	 * 获取角色名称  
	 * @return rlNm 角色名称  
	 */
	public String getRlNm()
	{
		return rlNm;
	}

	/**  
	 * 设置角色名称  
	 * @param rlNm 角色名称  
	 */
	public void setRlNm(String rlNm)
	{
		this.rlNm = rlNm;
	}

	/**  
	 * 获取状态  
	 * @return sts 状态  
	 */
	public int getSts()
	{
		return sts;
	}

	/**  
	 * 设置状态  
	 * @param sts 状态  
	 */
	public void setSts(int sts)
	{
		this.sts = sts;
	}
	
	/**  
	 * 获取备注  
	 * @return cmnts 备注  
	 */
	public String getCmnts()
	{
		return cmnts;
	}

	/**  
	 * 设置备注  
	 * @param cmnts 备注  
	 */
	public void setCmnts(String cmnts)
	{
		this.cmnts = cmnts;
	}

	/**  
	 * 获取角色类型，区分总部门店角色  
	 * @return sysTp 角色类型，区分总部门店角色  
	 */
	public int getSysTp()
	{
		return sysTp;
	}

	/**  
	 * 设置角色类型，区分总部门店角色  
	 * @param sysTp 角色类型，区分总部门店角色  
	 */
	public void setSysTp(int sysTp)
	{
		this.sysTp = sysTp;
	}

}