/**
 * 
 */
package com.ptsoft.pis.system.model.vo;

import com.ptsoft.common.base.BaseEntity;

/**
 * 系统日志
 * 
 * @author fuyiyong
 *
 */
public class SysLog extends BaseEntity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3114554672938403197L;

	/**直增长ID*/
	private int lgid;
	/**操作日期*/
	private String bzDt;
	/**操作人*/
	private String oprtr;
	/**客户端IP*/
	private String ip;
	/**浏览器名称*/
	private String browser;
	/**执行的动作*/
	private String actnId;
	/**执行的方法*/
	private String fnctnId;
	/**操作的数据*/
	private String dataId;
	/**操作内容*/
	private String cntnt;
	/**操作结果*/
	private String rst;
	/**操作状态 0-失败 1-成功'*/
	private int sts;
	/**时间戳*/
	private String lgTm;
	
	/**  
	 * 获取直增长ID  
	 * @return lgid 直增长ID  
	 */
	public int getLgid()
	{
		return lgid;
	}
	/**  
	 * 设置直增长ID  
	 * @param lgid 直增长ID  
	 */
	public void setLgid(int lgid)
	{
		this.lgid = lgid;
	}
	/**  
	 * 获取操作日期  
	 * @return bzDt 操作日期  
	 */
	public String getBzDt()
	{
		return bzDt;
	}
	/**  
	 * 设置操作日期  
	 * @param bzDt 操作日期  
	 */
	public void setBzDt(String bzDt)
	{
		this.bzDt = bzDt;
	}
	/**  
	 * 获取操作人  
	 * @return oprtr 操作人  
	 */
	public String getOprtr()
	{
		return oprtr;
	}
	/**  
	 * 设置操作人  
	 * @param oprtr 操作人  
	 */
	public void setOprtr(String oprtr)
	{
		this.oprtr = oprtr;
	}
	/**  
	 * 获取客户端IP  
	 * @return ip 客户端IP  
	 */
	public String getIp()
	{
		return ip;
	}
	/**  
	 * 设置客户端IP  
	 * @param ip 客户端IP  
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	/**  
	 * 获取浏览器名称  
	 * @return browser 浏览器名称  
	 */
	public String getBrowser()
	{
		return browser;
	}
	/**  
	 * 设置浏览器名称  
	 * @param browser 浏览器名称  
	 */
	public void setBrowser(String browser)
	{
		this.browser = browser;
	}
	/**  
	 * 获取执行的动作  
	 * @return actnId 执行的动作  
	 */
	public String getActnId()
	{
		return actnId;
	}
	/**  
	 * 设置执行的动作  
	 * @param actnId 执行的动作  
	 */
	public void setActnId(String actnId)
	{
		this.actnId = actnId;
	}
	/**  
	 * 获取执行的方法  
	 * @return fnctnId 执行的方法  
	 */
	public String getFnctnId()
	{
		return fnctnId;
	}
	/**  
	 * 设置执行的方法  
	 * @param fnctnId 执行的方法  
	 */
	public void setFnctnId(String fnctnId)
	{
		this.fnctnId = fnctnId;
	}
	/**  
	 * 获取操作的数据  
	 * @return dataId 操作的数据  
	 */
	public String getDataId()
	{
		return dataId;
	}
	/**  
	 * 设置操作的数据  
	 * @param dataId 操作的数据  
	 */
	public void setDataId(String dataId)
	{
		this.dataId = dataId;
	}
	/**  
	 * 获取操作内容  
	 * @return cntnt 操作内容  
	 */
	public String getCntnt()
	{
		return cntnt;
	}
	/**  
	 * 设置操作内容  
	 * @param cntnt 操作内容  
	 */
	public void setCntnt(String cntnt)
	{
		this.cntnt = cntnt;
	}
	/**  
	 * 获取操作结果  
	 * @return rst 操作结果  
	 */
	public String getRst()
	{
		return rst;
	}
	/**  
	 * 设置操作结果  
	 * @param rst 操作结果  
	 */
	public void setRst(String rst)
	{
		this.rst = rst;
	}
	/**  
	 * 获取操作状态0-失败1-成功'  
	 * @return sts 操作状态0-失败1-成功'  
	 */
	public int getSts()
	{
		return sts;
	}
	/**  
	 * 设置操作状态0-失败1-成功'  
	 * @param sts 操作状态0-失败1-成功'  
	 */
	public void setSts(int sts)
	{
		this.sts = sts;
	}
	/**  
	 * 获取时间戳  
	 * @return lgtm 时间戳  
	 */
	public String getLgTm()
	{
		return lgTm;
	}
	/**  
	 * 设置时间戳  
	 * @param lgtm 时间戳  
	 */
	public void setLgTm(String lgtm)
	{
		this.lgTm = lgtm;
	}
}
