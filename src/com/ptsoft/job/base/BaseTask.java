package com.ptsoft.job.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.ptsoft.common.Constants;
import com.ptsoft.pis.system.dao.SysServiceDao;
import com.ptsoft.pis.system.model.vo.SysService;

/**
 * Service对象的父类，只有继续此类才能被ServiceManager管理
 */
public abstract class BaseTask extends TimerTask
{
	public SysServiceDao serviceDao;

	private static Logger logger = Logger.getLogger("BaseTask");

	protected static SimpleDateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected static SimpleDateFormat fmtDate = new SimpleDateFormat("yyyyMMdd");
	protected static SimpleDateFormat fmtTime = new SimpleDateFormat("HHmmss");
	// 计时器
	private Timer timer = new Timer();
	// 配置对象
	private SysService sysService = null;

	/**
	 * 取得服务配置对象
	 */
	public SysService getSysService()
	{
		return sysService;
	}

	/**
	 * 设置服务配置对象
	 */
	public void setSysService(SysService sysService)
	{
		this.sysService = sysService;
	}

	/**
	 * 服务在启动时执行一次，进行系统初始化工作，可以由子类重写
	 */
	protected void initService()
	{
		// init
	}

	/**
	 * 启动
	 */
	public final void start()
	{
		// 初始化
		this.initService();

		// 如果没有开始时间
		if (this.sysService.getFirstTime() == null || this.sysService.getFirstTime() == "")
		{
			// 不需要重复执行，则立即启动该服务
			if (this.sysService.getDelayTime() == 0)
			{
				this.run();
			}
			else
			{
				this.startBySchedule();
			}
		}
		else
		{
			this.startBySchedule();
		}
	}

	/**
	 * 按计划执行
	 */
	private void startBySchedule()
	{
		Calendar first = Calendar.getInstance();
		int nowDate = Integer.parseInt(fmtDate.format(first.getTime()));
		int sdeDate = Integer.parseInt(fmtDate.format(first.getTime()));
		int nowTime = Integer.parseInt(fmtTime.format(first.getTime()));
		int sdeTime = Integer.parseInt(fmtTime.format(first.getTime()));

		// 计算第一次启动时间
		if (this.sysService.getFirstTime() != "")
		{
			sdeDate = Integer.parseInt(this.sysService.getFirstDate().replaceAll("-", ""));
			sdeTime = Integer.parseInt(this.sysService.getFirstTime().replaceAll(":", ""));

			// 小于等于今天
			if (sdeDate <= nowDate)
			{
				// 按指定时间执行
				String[] t = this.sysService.getFirstTime().split(":");
				first.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t[0]));
				first.set(Calendar.MINUTE, Integer.parseInt(t[1]));
				first.set(Calendar.SECOND, Integer.parseInt(t[2]));

				// 小于现在
				if (sdeTime < nowTime)
				{
					// 下一天的此时执行
					first.add(Calendar.DATE, 1);
					logger.info(this.sysService.getSvrKey() + " 条件 日期小、时间小 [下一天此时执行]");
				}
				else
				{
					logger.info(this.sysService.getSvrKey() + " 条件 日期小、时间大 [指定时间执行]");
				}
			}
			// 大于当前日期，按指定日期
			else
			{
				String[] d = this.sysService.getFirstDate().split("-");
				first.set(Calendar.YEAR, Integer.parseInt(d[0]));
				first.set(Calendar.MONTH, Integer.parseInt(d[1]) - 1);
				first.set(Calendar.DAY_OF_MONTH, Integer.parseInt(d[2]));

				String[] t = this.sysService.getFirstTime().split(":");
				first.set(Calendar.HOUR_OF_DAY, Integer.parseInt(t[0]));
				first.set(Calendar.MINUTE, Integer.parseInt(t[1]));
				first.set(Calendar.SECOND, Integer.parseInt(t[2]));
				logger.info(this.sysService.getSvrKey() + " 条件 日期大 [指定日期时间执行]");
			}
		}

		this.timer = new Timer();
		// 再次执行间隔，单位为秒
		if (this.sysService.getDelayTime() > 0)
		{
			this.timer.schedule(this, first.getTime(), this.sysService.getDelayTime() * 1000);
			logger.info(this.sysService.getSvrKey() + " 将在 " + fmtDateTime.format(first.getTime()) + " 执行，之后每隔 " + String.valueOf(this.sysService.getDelayTime()) + " 秒执行一次。");
		}
		else
		{
			this.timer.schedule(this, first.getTimeInMillis() - Calendar.getInstance().getTimeInMillis());
			logger.info(this.sysService.getSvrKey() + " 将在 " + fmtDateTime.format(first.getTime()) + " 执行，只执行一次。");
		}

		this.updateStatus(Constants.ServiceState.SLEEP);
	}

	/**
	 * 停止
	 */
	public final void stop()
	{
		this.endTheTask();

		this.updateStatus(Constants.ServiceState.STOPED);

		this.cancel();

		this.timer.cancel();

		logger.info(this.sysService.getSvrKey() + " 任务终止。");
	}

	/**
	 * 执行任务，子类通过重写executeTheTask方法完成任务
	 */
	public final void run()
	{
		this.updateStatus(Constants.ServiceState.RUNNING);

		this.executeTheTask();

		if (this.sysService.getDelayTime() > 0)
		{
			this.updateStatus(Constants.ServiceState.SLEEP);
		}
		else
		{
			this.stop();
		}

		logger.info(this.sysService.getSvrKey() + " 执行成功。");
	}

	private Constants.ServiceState _serviceState = Constants.ServiceState.WAIT;

	public Constants.ServiceState getServiceState()
	{
		return this._serviceState;
	}

	/**
	 * 更新服务状态
	 */
	public void updateStatus(Constants.ServiceState state)
	{
		this._serviceState = state;
		this.sysService.setStatus(state.getValue());

		this.serviceDao.update(this.sysService);
	}

	/**
	 * 执行任务
	 */
	protected abstract void executeTheTask();

	/**
	 * 任务完成
	 */
	protected abstract void endTheTask();
}
