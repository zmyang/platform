package com.ptsoft.job.config;

import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

class Single
{
}

public class DbHelper
{
	private static DbHelper instance;

	private HashMap<String, Jdbc> jdbcList = null;

	public DbHelper(Single s)
	{
		this.jdbcList = new HashMap<String, Jdbc>();
	}

	public static DbHelper getInstance()
	{
		if (instance == null)
		{
			instance = new DbHelper(new Single());
			instance.parseConfig();
		}
		return instance;
	}

	private String url;

	private void parseConfig()
	{
		try
		{
			this.url = DbHelper.class.getResource("SysConfig.xml").toString();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(this.url);

			this.parseJdbc(document);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static final String JDBC_HQ = "mis";
	public static final String JDBC_MID = "mid";

	/**
	 * 根据配置文件，创建数据库连接配置
	 * */
	private void parseJdbc(Document document)
	{
		NodeList list = document.getElementsByTagName("jdbc");
		if (list.getLength() == 0)
			return;
		Element jdbcElement = (Element) list.item(0);

		list = jdbcElement.getElementsByTagName(JDBC_HQ);
		if (list.getLength() > 0)
		{
			Element element = (Element) list.item(0);
			this.jdbcList.put(JDBC_HQ, new Jdbc(element));
		}

		list = jdbcElement.getElementsByTagName(JDBC_MID);
		if (list.getLength() > 0)
		{
			Element element = (Element) list.item(0);
			this.jdbcList.put(JDBC_MID, new Jdbc(element));
		}
	}

	/** 取得数据库连接配置 */
	public Jdbc getJdbc(String key)
	{
		return this.jdbcList.get(key);
	}
}
