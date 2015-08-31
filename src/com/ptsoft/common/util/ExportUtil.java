package com.ptsoft.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

public class ExportUtil
{

	private static final String ExportReportFilePath = "report/";

	@SuppressWarnings("all")
	public static String ExportToExcel(LinkedHashMap<String, String> title, ArrayList dataList)
	{

		String reportFullFile = "";
		try
		{
			// 生成excel时间文件名
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String reportFileName = formatter.format(new Date());
			// 生成Excel文件及路径
			reportFullFile = ExportReportFilePath + reportFileName + ".xls";
			String reportFilePath = SysConfig.getRootPath() + "/" + reportFullFile;
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
			HSSFSheet sheet = hssfWorkbook.createSheet("报表");
			// 设置表头样式
			HSSFCellStyle headStyle = hssfWorkbook.createCellStyle();
			Font headerFont = hssfWorkbook.createFont();
			headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			headStyle.setBorderBottom((short) 1);
			headStyle.setBorderLeft((short) 1);
			headStyle.setBorderRight((short) 1);
			headStyle.setBorderTop((short) 1);
			headStyle.setFont(headerFont);

			HSSFCellStyle bodyStyle = hssfWorkbook.createCellStyle();
			bodyStyle.setBorderBottom((short) 1);
			bodyStyle.setBorderLeft((short) 1);
			bodyStyle.setBorderRight((short) 1);
			bodyStyle.setBorderTop((short) 1);

			// 第一行标题栏
			HSSFRow firstrow = sheet.createRow(0);
			// 第二行表列名
			// HSSFRow columnrow = sheet.createRow(1);

			// 循环迭代LinkedHashMap取出键值插入一二行
			Iterator titleIter = title.keySet().iterator();
			int columTag = 0; // 从第一列开始
			while (titleIter.hasNext())
			{
				String columnKey = (String) titleIter.next();
				// 在一行内循环
				HSSFCell ti = firstrow.createCell(columTag);
				ti.setCellValue(title.get(columnKey).toString());
				ti.setCellStyle(headStyle);
				// 在一行内循环
				// HSSFCell column = columnrow.createCell(columTag);
				// column.setCellValue(columnKey);
				// column.setCellStyle(headStyle);
				columTag = columTag + 1;
			}
			for (int i = 0; i < dataList.size(); i++)
			{
				// 创建一行
				HSSFRow row = sheet.createRow(i + 1);
				// 得到要插入的每一条记录
				@SuppressWarnings("unchecked")
				LinkedHashMap<String, String> data = (LinkedHashMap<String, String>) dataList.get(i);
				Iterator j = data.entrySet().iterator();
				int col = 0;
				while (j.hasNext())
				{
					// 只遍历一次,速度快
					Map.Entry e = (Map.Entry) j.next();
					if (title.containsKey(e.getKey()))
					{
						// 在一行内循环
						HSSFCell xh = row.createCell(col);
						xh.setCellValue(e.getValue().toString());
						xh.setCellStyle(bodyStyle);
						col = col + 1;
					}
				}
			}
			// 创建文件输出流，准备输出电子表格
			OutputStream out = new FileOutputStream(reportFilePath);
			hssfWorkbook.write(out);
			out.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return SysConfig.get_sms_url() + reportFullFile;
	}

	public static void deleteExcel(String reportFilePath)
	{
		File excel = new File(reportFilePath);
		if (excel.isFile() && excel.exists())
		{
			excel.delete(); // 把Excel文件删除
		}
	}
	
	public static String exportToExcel(String columns, String cnts, String title, HttpServletResponse response)
	{
		List<String> columnsList = Arrays.asList(columns.split(","));
		List<List<String>> cntsLists = new ArrayList<List<String>>();
		String[] cnt = cnts.split(";");
		List<String> tempList = new ArrayList<String>();
		for (int i = 0; i < cnt.length; i++)
		{
			tempList = Arrays.asList(cnt[i].split(","));
			cntsLists.add(tempList);
		}

		String url = "";
		try
		{
			String filePath = SysConfig.getRootPath() + "/exportExcels/";
			String fileName = title.replaceAll("：", "")+ "_" + new Date().getTime() + ".xls";
			//fileName = java.net.URLEncoder.encode(fileName, "utf-8");
			File file = new File(filePath);
			if (!file.exists() && !file.isDirectory())
			{
				file.mkdir();
			}
			file = new File(filePath + fileName);
			if (!file.exists())
			{
				file.createNewFile();
			}

			WritableWorkbook workbook = Workbook.createWorkbook(file);
			createExcel(workbook, columnsList, cntsLists, title);

			url = SysConfig.get_sms_url() + File.separator + "exportExcels" + File.separator + fileName;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return url;
	}

	private static void createExcel(WritableWorkbook workbook, List<String> columnsList, List<List<String>> cntList, String title)
	{
		try
		{
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			sheet.getSettings().setShowGridLines(true);
			sheet.getSettings().setProtected(false);
			Label lable = new Label(0, 0, title);
			lable.setCellFormat(getTitleFormat());
			sheet.addCell(lable);
			sheet.mergeCells(0, 0, columnsList.size() - 1, 0);

			for (int i = 0; i < columnsList.size(); i++)
			{
				sheet.setColumnView(i, 30);
			}

			Label titleLabel = null;
			for (int i = 0; i < columnsList.size(); i++)
			{
				titleLabel = new Label(i, 1, columnsList.get(i), getColumnsFormat());
				sheet.addCell(titleLabel);
			}

			Label cntLabel = null;
			for (int i = 0, k = 1; i < cntList.size(); i++, k++)
			{
				List<String> tempList = cntList.get(i);
				for (int j = 0; j < tempList.size(); j++)
				{
					cntLabel = new Label(j, k + 1, tempList.get(j), getCntsFormat());
					sheet.addCell(cntLabel);
				}
			}
			workbook.write();
			workbook.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static CellFormat getTitleFormat() throws Exception
	{
		//设置字体   
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 15, WritableFont.BOLD);

		//创建单元格FORMAT   
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf.setLocked(true);
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		return wcf;
	}

	public static WritableCellFormat getColumnsFormat() throws Exception
	{
		//设置字体   
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);

		//创建单元格FORMAT   
		WritableCellFormat wcf = new WritableCellFormat(wf);
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf.setLocked(true);
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		wcf.setBackground(Colour.GREY_25_PERCENT);
		return wcf;
	}

	private static CellFormat getCntsFormat() throws Exception
	{
		WritableCellFormat wcf = new WritableCellFormat();
		wcf.setAlignment(Alignment.CENTRE);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		return wcf;
	}
}
