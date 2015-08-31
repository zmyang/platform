package com.ptsoft.pis.system.model.vo;

/**
 * 自定义组
 * */
public class SysStoreGroup 
{
	/**自定义组标识*/
	private int grpId;

	/**自定义组名称*/
	private String grpNm;

	/**备注*/
	private String cmnts;

	/**  
	 * 获取自定义组标识  
	 * @return grpId 自定义组标识  
	 */
	public int getGrpId()
	{
		return grpId;
	}

	/**  
	 * 设置自定义组标识  
	 * @param grpId 自定义组标识  
	 */
	public void setGrpId(int grpId)
	{
		this.grpId = grpId;
	}

	/**  
	 * 获取自定义组名称  
	 * @return grpNm 自定义组名称  
	 */
	public String getGrpNm()
	{
		return grpNm;
	}

	/**  
	 * 设置自定义组名称  
	 * @param grpNm 自定义组名称  
	 */
	public void setGrpNm(String grpNm)
	{
		this.grpNm = grpNm;
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
}