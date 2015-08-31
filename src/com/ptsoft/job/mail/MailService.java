package com.ptsoft.job.mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Logger;

import com.ptsoft.job.base.BaseTask;
import com.ptsoft.job.config.DbHelper;

public class MailService extends BaseTask
{
	private static Logger LogUtil = Logger.getLogger("MailService");

	protected static String MAIL_SERVER = "smtp.exmail.qq.com";
	protected static String MAIL_FROM = "system@partnersoft.cn";
	protected static String MAIL_USER = "system@partnersoft.cn";
	protected static String MAIL_PASS = "sys1qaz2wsx";
	protected static String ROOT_PATH = "E:\\jboss-5.0.0.GA\\server\\default\\deploy\\RSC.war\\uploads\\";

	protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected List<MailInfo> mailList = null;

	/**
	 * 服务在启动时执行一次，进行系统初始化工作
	 * */
	protected void initService()
	{
		try
		{
			InputStream in = MailService.class.getResourceAsStream("MailConfig.properties");

			Properties props = new Properties();
			props.load(in);

			MAIL_SERVER = props.getProperty("MAIL_SERVER");
			MAIL_FROM = props.getProperty("MAIL_FROM");
			MAIL_USER = props.getProperty("MAIL_USER");
			MAIL_PASS = props.getProperty("MAIL_PASS");
			ROOT_PATH = props.getProperty("ROOT_PATH");
		} catch (Exception e)
		{
			LogUtil.error(e.getMessage());
		}
	}

	/**
	 * 执行任务
	 */
	protected void executeTheTask()
	{
		this.mailList = new ArrayList<MailInfo>();

		this.loadMailInfo();

		for (MailInfo mi : this.mailList)
		{
			try
			{
				this.sendByDomain(mi);
				this.updateSuccessStatus(mi);
			} catch (Exception e)
			{
				this.traceErrorMsg(e);
				this.updateFaultStatus(mi, e.getMessage());
			}
		}
	}

	/**
	 * 加载邮件信息
	 * */
	protected void loadMailInfo()
	{
		String sql = "Select * from OS_SENDMAILINFO Where SendStatus=0";
		ResultSet rs = null;
		try
		{
			rs = DbHelper.getInstance().getJdbc(DbHelper.JDBC_HQ).loadData(sql);
			while (rs.next())
			{
				MailInfo mi = new MailInfo();
				mi.setId(Long.parseLong(rs.getString("ID")));
				mi.setCreateDate(rs.getString("CreateDate"));
				mi.setSendDate(rs.getString("SendDate"));
				mi.setBc(rs.getString("BC"));
				mi.setCc(rs.getString("CC"));
				mi.setFilePath(rs.getString("FilePath"));
				mi.setMailAdressTitle(rs.getString("MailAdressTitle"));
				mi.setMailTo(rs.getString("MailTo"));
				mi.setMailType(Integer.parseInt(rs.getString("MailType")));
				// mi.setMessage(rs.getString("Message").replace("‘",
				// "'").replace("；", ";"));
				mi.setMessage(new String(rs.getBlob("Message").getBytes((long) 1, (int) rs.getBlob("Message").length())).replace("‘", "'").replace("；", ";"));
				mi.setSendStatus(Integer.parseInt(rs.getString("SendStatus")));
				mi.setSendUser(rs.getString("SendUser"));
				mi.setSendUserMail(rs.getString("SendUserMail"));
				mi.setSubject(rs.getString("Subject"));
				mi.setSendErrorException(rs.getString("SendErrorException"));

				this.mailList.add(mi);
			}
		} catch (Exception e)
		{
			LogUtil.error(e.getMessage());
		} finally
		{
			DbHelper.getInstance().getJdbc(DbHelper.JDBC_HQ).closeResultSet(rs);
		}
	}

	/**
	 * 发送邮件
	 * */
	protected void sendByDomain(MailInfo mi) throws Exception
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", MAIL_SERVER);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(props);

		Address from = new InternetAddress(MAIL_FROM, mi.getMailAdressTitle());

		BodyPart mbpContent = new MimeBodyPart();
		mbpContent.setContent(mi.getMessage(), "text/html;charset=gb2312");
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbpContent);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(from);
		for (String ads : mi.getMailTo().split(";"))
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(ads));
		msg.setSubject(mi.getSubject());
		msg.setSentDate(new Date());

		if (mi.getFilePath() != null && !mi.getFilePath().equals(""))
		{
			String files[] = mi.getFilePath().split("\n");
			for (String fileName : files)
			{
				String path = getFilePath(fileName);
				FileDataSource fds = new FileDataSource(path);
				MimeBodyPart mbpFile = new MimeBodyPart();
				mbpFile.setDataHandler(new DataHandler(fds));
				mbpFile.setFileName(new String(renameFile(fds.getName()).getBytes(), "ISO-8859-1"));
				mp.addBodyPart(mbpFile);
			}
		}
		msg.setContent(mp);

		LogUtil.info("------MailService Send Begin " + mi.getSubject());
		Transport transport = session.getTransport("smtp");
		transport.connect(MAIL_SERVER, MAIL_USER, MAIL_PASS);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		LogUtil.info("------MailService Send   End " + mi.getSubject());
	}

	/**
	 * 修改文件名
	 * */
	protected String renameFile(String fileName)
	{
		if (fileName.contains("_"))
		{
			fileName = fileName.substring(fileName.indexOf("_") + 1, fileName.length());
			return fileName;
		}
		return fileName;
	}

	/**
	 * 计算文件路径
	 * */
	protected String getFilePath(String fileName) throws Exception
	{
		String fileDir = "";
		String dirSpacer = "/";
		fileDir = fileDir.replace("\\", dirSpacer);
		if (!fileDir.equals("") && !fileDir.substring(fileDir.length() - 1).equals(dirSpacer))
		{
			fileDir += dirSpacer;
		}
		fileDir = ROOT_PATH + fileDir;
		File dir = new File(fileDir);
		if (!dir.exists())
		{
			throw new FileNotFoundException(fileDir + " 没有指定的路径。");
		}
		dir = null;

		String fullPath = fileDir + fileName;
		dir = new File(fullPath);
		if (!dir.exists())
		{
			throw new FileNotFoundException(fullPath + " 没有指定的文件。");
		}
		return fullPath;
	}

	/**
	 * 更新邮件发送成功状态
	 * */
	protected void updateSuccessStatus(MailInfo mi)
	{
		String sql = "Update OS_SENDMAILINFO Set SendDate='" + dateFormat.format(new Date()) + "', SendStatus=1 Where ID=" + mi.getId();
		DbHelper.getInstance().getJdbc(DbHelper.JDBC_HQ).executeSql(sql);
	}

	/**
	 * 更新邮件发送失败状态
	 * */
	protected void updateFaultStatus(MailInfo mi, String exceptionMsg)
	{
		String sql = "Update OS_SENDMAILINFO Set SendDate='" + dateFormat.format(new Date()) + "', SendStatus=2,SendErrorException='" + exceptionMsg + "' Where ID=" + mi.getId();
		DbHelper.getInstance().getJdbc(DbHelper.JDBC_HQ).executeSql(sql);
	}

	/**
	 * 解析错误信息
	 * */
	protected void traceErrorMsg(Exception mex)
	{
		Exception ex = mex;
		do
		{
			if (ex instanceof FileNotFoundException)
			{
				LogUtil.info(ex.getMessage());
				return;
			} else if (ex instanceof SendFailedException)
			{
				SendFailedException sfex = (SendFailedException) ex;
				Address[] invalid = sfex.getInvalidAddresses();
				if (invalid != null)
				{
					LogUtil.info("    ** Invalid Addresses");
					if (invalid != null)
					{
						for (int i = 0; i < invalid.length; i++)
						{
							LogUtil.info("         " + invalid[i]);
						}
					}
				}
				Address[] validUnsent = sfex.getValidUnsentAddresses();
				if (validUnsent != null)
				{
					LogUtil.info("    ** ValidUnsent Addresses");
					if (validUnsent != null)
					{
						for (int i = 0; i < validUnsent.length; i++)
						{
							LogUtil.info("         " + validUnsent[i]);
						}
					}
				}
				Address[] validSent = sfex.getValidSentAddresses();
				if (validSent != null)
				{
					LogUtil.info("    ** ValidSent Addresses");
					if (validSent != null)
					{
						for (int i = 0; i < validSent.length; i++)
						{
							LogUtil.info("         " + validSent[i]);
						}
					}
				}
			} else if (ex instanceof MessagingException)
			{
				ex = ((MessagingException) ex).getNextException();
			} else
			{
				ex = null;
			}
		} while (ex != null);
	}

	/**
	 * 任务完成
	 * */
	protected void endTheTask()
	{

	}
}
