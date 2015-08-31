package com.ptsoft.common.util;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SysConfig
{
	private static String sms_url = "";
	private static String salesData_path = "";
	
	/**
	 * 获取配置的当前Web系统地址
	 * @return
	 */
	public static String get_sms_url()
	{
		if (sms_url.equals(""))
		{
			getSysConfigInfo();
		}
		if (!sms_url.endsWith("/"))
		{
			sms_url = sms_url + "/";
		}
		return sms_url;
	}

	/**
	 * 获取配置的销售数据文件存放位置
	 * @return
	 */
	public static String get_salesData_path()
	{
		if (salesData_path.equals(""))
		{
			getSysConfigInfo();
		}
		if (!salesData_path.endsWith("\\"))
		{
			salesData_path = salesData_path + "\\";
		}
		return salesData_path;
	}
	
	/**
	 * 获取Web容器下导出模板文件的实际路径
	 * @return
	 * @throws IOException
	 */
	public static String getExportTemplateFilePath() throws IOException
	{
		return getRootPath() + "template/template.xls";
	}

	/**
	 * 获取Web容器的实际路径
	 * @return
	 */
	public static String getRootPath()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String path = request.getSession().getServletContext().getRealPath("/");
		return path;
	}
	
	/**
	 * 分析SysConfig文件
	 */
	private static void getSysConfigInfo()
	{
		try
		{
			String url = Thread.currentThread().getContextClassLoader().getResource("SysConfig.xml").toString();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(url);

			Element root = doc.getDocumentElement();

			NodeList nodelist = root.getChildNodes();

			if (nodelist != null)
			{
				for (int i = 0; i < nodelist.getLength(); i++)
				{
					Node node = nodelist.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE)
					{
						if (node.getNodeName().equals("sms_url"))
						{
							sms_url = node.getTextContent().toString().trim();
						}
						else if (node.getNodeName().equals("salesData_path"))
						{
							salesData_path = node.getTextContent().toString().trim();
						}
					}
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
