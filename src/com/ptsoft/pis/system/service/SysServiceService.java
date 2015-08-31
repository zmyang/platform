/**
 * 
 */
package com.ptsoft.pis.system.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptsoft.common.Constants;
import com.ptsoft.common.base.BaseService;
import com.ptsoft.job.base.BaseTask;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.pis.system.dao.SysServiceDao;
import com.ptsoft.pis.system.model.vo.SysService;

@Service
public class SysServiceService extends BaseService<SysService, String>
{
	@Autowired
	private SysServiceDao serviceDao;
	
	@SuppressWarnings("rawtypes")
	protected EntityDao getEntityDao()
	{
		return serviceDao;
	}

	@Override
	public void save(SysService entity)
	{

	}
	
	
	
	
	//日志
	private static Logger logger = Logger.getLogger("ServiceManager"); 

	//数据源
	private HashMap<String, SysService> ServiceDs = null;
	//Service实例
	private HashMap<String, BaseTask> ServiceList = null;

	/**
	 * 初始化
	 * */
	@PostConstruct
	protected void initService()
	{
		this.ServiceDs = new HashMap<String, SysService>();
		this.ServiceList = new HashMap<String, BaseTask>();

		List<SysService> list = this.serviceDao.findAll();
		logger.info(this.getClass().getName() + " 服务数：" + list.size());
		
		for(SysService svs : list)
		{
			this.create(svs);
		}
		
		logger.info(this.getClass().getName() + " 初始化完成!");
	}
	
	/**
	 * 创建Service对象
	 * */
	protected boolean create(SysService sCls)
	{
		this.ServiceDs.put(sCls.getSvrKey(), sCls);
		
		try
		{
			Class<?> cls = Class.forName(sCls.getClsName());
			Object tmp = cls.newInstance();
			boolean flag = tmp instanceof BaseTask;  
			if (!flag)
			{
				tmp = null;
				logger.info(this.getClass().getName() + " CREATE " + sCls.getClsName() + "不是可识别的子类!");
				return false;
			}
			BaseTask svs = (BaseTask)cls.newInstance();
			svs.serviceDao = this.serviceDao;
			svs.setSysService(sCls);
			svs.updateStatus(Constants.ServiceState.WAIT);
			if (sCls.getAutoRun() == 1)
			{
				svs.start();
			}
			this.ServiceList.put(sCls.getSvrKey(), svs);
			return true;
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			return false;
		}
	}
	
	/**
	 * 启动某个Service
	 * @param key Service唯一标识
	 * */
	public void toStart(String key)
	{
		SysService sCls = this.serviceDao.getById(key);
		
		//没有注册该服务
		if (sCls == null)
		{
			logger.info(this.getClass().getName() + " start - 没有找到该标识：" + key);
			return;
		}
		
		//创建服务
		this.create(sCls);
		
		//该服务已经在执行
		if (this.ServiceDs.get(key).getStatus() == Constants.ServiceState.RUNNING.getValue())
		{
			return;
		}
		try
		{
			this.ServiceList.get(key).start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 启动某个Service
	 * @param key Service唯一标识
	 * */
	public void toStop(String key)
	{
		if (!this.ServiceDs.containsKey(key))
		{
			logger.info(this.getClass().getName() + " stop - 没有找到该标识：" + key);
			return;
		}
		if (!this.ServiceList.containsKey(key))
		{
			logger.info(this.getClass().getName() + " start - 没有找到该实例：" + key);
			return;
		}
		
		try
		{
			this.ServiceList.get(key).stop();
			this.ServiceList.put(key, null);
			this.ServiceList.remove(key);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
