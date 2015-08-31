package com.ptsoft.job.db;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

import com.ptsoft.job.base.BaseTask;

/**
 * 自动备份数据库
 */
public class DbBackup extends BaseTask
{
	private static Logger LogUtil = Logger.getLogger("DbBackup");

	@Override
	protected void executeTheTask()
	{
		try
		{
			String path = DbBackup.class.getResource ("").getFile();
			Process ps = Runtime.getRuntime().exec(path + "DbBackup.bat");
			InputStream in = ps.getInputStream();
			int c;
			while ((c = in.read()) != -1)
			{
				System.out.print(c);// 如果你不需要看输出，这行可以注销掉
			}
			in.close();
			ps.waitFor();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		LogUtil.info("DbBackup Complete");
	}

	@Override
	protected void endTheTask()
	{

	}

}
