package com.ptsoft.pis.system.model.vo;

/**记录部署到系统中的服务*/
public class SysService
{
	private String svrKey = "";
	private String clsName = "";
	private String firstDate = "";
	private String firstTime = "";
	private int delayTime = 0;
	private int autoRun = 0;
	private int status = 0;
	private String memo = "";
	
	public SysService()
	{
		super();
	}
	
	public SysService(String key, String clsName)
	{
		super();
		this.svrKey = key;
		this.clsName = clsName;
	}

	/**设置标识*/
	public String getSvrKey()
	{
		return svrKey;
	}
	
	/**取得标识*/
	public void setSvrKey(String key)
	{
		this.svrKey = key;
	}

	/**设置类路径*/
	public String getClsName()
	{
		return clsName;
	}

	/**取得类路径*/
	public void setClsName(String clsName)
	{
		this.clsName = clsName;
	}

	/**取得第一次开始日期，如果为空代表当天执行*/
	public String getFirstDate()
	{
		return firstDate;
	}

	/**设置第一次开始日期，如果为空代表当天执行*/
	public void setFirstDate(String firstDate)
	{
		this.firstDate = firstDate;
	}

	/**取得第一次开始时间，如果为空代表立即执行*/
	public String getFirstTime()
	{
		return firstTime;
	}
	
	/**设置第一次开始时间，如果为空代表立即执行*/
	public void setFirstTime(String firstTime)
	{
		this.firstTime = firstTime;
	}
	
	/**取得再次执行的间隔时间(秒)，如果为0则只执行一次*/
	public int getDelayTime()
	{
		return delayTime;
	}

	/**设置再次执行的间隔时间(秒)，如果为0则只执行一次*/
	public void setDelayTime(int delayTime)
	{
		this.delayTime = delayTime;
	}

	/**取得服务所处在的状态*/
	public int getStatus()
	{
		return status;
	}

	/**设置服务所处在的状态*/
	public void setStatus(int status)
	{
		this.status = status;
	}

	/**取得服务是否自动启动*/
	public int getAutoRun()
	{
		return autoRun;
	}

	/**设置服务是否自动启动*/
	public void setAutoRun(int autoRun)
	{
		this.autoRun = autoRun;
	}

	/**取得服务描述*/
	public String getMemo()
	{
		return memo;
	}

	/**设置服务描述*/
	public void setMemo(String memo)
	{
		this.memo = memo;
	}

	
	
}
