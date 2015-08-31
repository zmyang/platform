package com.ptsoft.pis.system.model.vo;

import com.ptsoft.common.base.BaseEntity;

public class SysFunction extends BaseEntity
{
	private static final long serialVersionUID = 7498016035878848131L;

	/**页面标识*/
	private String fnId;
	
	/**页面名称*/
	private String fnNm;
	
	/**所属模块*/
	private int mdlId;
	
	/**页面地址*/
	private String page;
	
	/**页面图标*/
	private String icn;
	
	/**页面类型-层级*/
	private int pgTp;
	
	/**系统类型-区分不同的功能树*/
	private int sysTp;
	
	/**父页面标识-fnId*/
	private String prntId;
	
	/**排序号*/
	private Double stNo;
	
	/**状态*/
	private int sts;
	
	
	/**  
	 * 获取页面标识  
	 * @return fnId 页面标识  
	 */
	public String getFnId()
	{
		return fnId;
	}
	/**  
	 * 设置页面标识  
	 * @param fnId 页面标识  
	 */
	public void setFnId(String fnId)
	{
		this.fnId = fnId;
	}
	/**  
	 * 获取页面名称  
	 * @return fnNm 页面名称  
	 */
	public String getFnNm()
	{
		return fnNm;
	}
	/**  
	 * 设置页面名称  
	 * @param fnNm 页面名称  
	 */
	public void setFnNm(String fnNm)
	{
		this.fnNm = fnNm;
	}
	/**  
	 * 获取所属模块  
	 * @return mdlId 所属模块  
	 */
	public int getMdlId()
	{
		return mdlId;
	}
	/**  
	 * 设置所属模块  
	 * @param mdlId 所属模块  
	 */
	public void setMdlId(int mdlId)
	{
		this.mdlId = mdlId;
	}
	/**  
	 * 获取页面地址  
	 * @return page 页面地址  
	 */
	public String getPage()
	{
		return page;
	}
	/**  
	 * 设置页面地址  
	 * @param page 页面地址  
	 */
	public void setPage(String page)
	{
		this.page = page;
	}
	/**  
	 * 获取页面图标  
	 * @return icn 页面图标  
	 */
	public String getIcn()
	{
		return icn;
	}
	/**  
	 * 设置页面图标  
	 * @param icn 页面图标  
	 */
	public void setIcn(String icn)
	{
		this.icn = icn;
	}
	/**  
	 * 获取页面类型-层级  
	 * @return pgTp 页面类型-层级  
	 */
	public int getPgTp()
	{
		return pgTp;
	}
	/**  
	 * 设置页面类型-层级  
	 * @param pgTp 页面类型-层级  
	 */
	public void setPgTp(int pgTp)
	{
		this.pgTp = pgTp;
	}
	/**  
	 * 获取系统类型-区分不同的功能树  
	 * @return sysTp 系统类型-区分不同的功能树  
	 */
	public int getSysTp()
	{
		return sysTp;
	}
	/**  
	 * 设置系统类型-区分不同的功能树  
	 * @param sysTp 系统类型-区分不同的功能树  
	 */
	public void setSysTp(int sysTp)
	{
		this.sysTp = sysTp;
	}
	/**  
	 * 获取父页面标识-fnId  
	 * @return prntId 父页面标识-fnId  
	 */
	public String getPrntId()
	{
		return prntId;
	}
	/**  
	 * 设置父页面标识-fnId  
	 * @param prntId 父页面标识-fnId  
	 */
	public void setPrntId(String prntId)
	{
		this.prntId = prntId;
	}
	/**  
	 * 获取排序号  
	 * @return stNo 排序号  
	 */
	public Double getStNo()
	{
		return stNo;
	}
	/**  
	 * 设置排序号  
	 * @param stNo 排序号  
	 */
	public void setStNo(Double stNo)
	{
		this.stNo = stNo;
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
}