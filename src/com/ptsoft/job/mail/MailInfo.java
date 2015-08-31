package com.ptsoft.job.mail;

/**
 * @author qiujunqi Mail info 是用于存储发送邮件信息的实体
 */
public class MailInfo
{
	private long id;// 主键
	private int mailType;// 邮件类型
	private String sendUser;// 发件人用户名
	private String sendUserMail;// 发件人邮箱
	private String mailAdressTitle;// 邮件发件人中的描述信息
	private String cc;// cc收件人，逗号分割
	private String bc;// bc收件人，逗号分割
	private String mailTo;// 收件人，逗号分割
	private String filePath;// 附件地址，逗号分割
	private String subject;// 邮件主题
	private String message;// 邮件正文
	private String createDate;// 创建时间
	private String sendDate;// 邮件发送时间
	private int sendStatus;// 邮件发送状态
	private String sendErrorException;// 邮件发送失败异常信息

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getMailType()
	{
		return mailType;
	}

	public void setMailType(int mailType)
	{
		this.mailType = mailType;
	}

	public String getSendUser()
	{
		return sendUser;
	}

	public void setSendUser(String sendUser)
	{
		this.sendUser = sendUser;
	}

	public String getSendUserMail()
	{
		return sendUserMail;
	}

	public void setSendUserMail(String sendUserMail)
	{
		this.sendUserMail = sendUserMail;
	}

	public String getMailAdressTitle()
	{
		return mailAdressTitle;
	}

	public void setMailAdressTitle(String mailAdressTitle)
	{
		this.mailAdressTitle = mailAdressTitle;
	}

	public String getCc()
	{
		return cc;
	}

	public void setCc(String cc)
	{
		this.cc = cc;
	}

	public String getBc()
	{
		return bc;
	}

	public void setBc(String bc)
	{
		this.bc = bc;
	}

	public String getMailTo()
	{
		return mailTo;
	}

	public void setMailTo(String mailTo)
	{
		this.mailTo = mailTo;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public void setFilePath(String filePath)
	{
		this.filePath = filePath;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getCreateDate()
	{
		return createDate;
	}

	public void setCreateDate(String createDate)
	{
		this.createDate = createDate;
	}

	public String getSendDate()
	{
		return sendDate;
	}

	public void setSendDate(String sendDate)
	{
		this.sendDate = sendDate;
	}

	public int getSendStatus()
	{
		return sendStatus;
	}

	public void setSendStatus(int sendStatus)
	{
		this.sendStatus = sendStatus;
	}

	public String getSendErrorException()
	{
		return sendErrorException;
	}

	public void setSendErrorException(String sendErrorException)
	{
		this.sendErrorException = sendErrorException;
	}
}
