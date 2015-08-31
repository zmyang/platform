/**
 * 
 */
package com.ptsoft.pis.system.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.ptsoft.common.annotation.ServiceLog;
import com.ptsoft.common.base.BaseService;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.common.util.StringUtil;
import com.ptsoft.pis.system.dao.SysDataTypeDao;
import com.ptsoft.pis.system.model.vo.SysDataType;

@Service
public class SysDataTypeService extends BaseService<SysDataType, String>
{
	@Autowired
	private SysDataTypeDao dataTypeDao;

	@SuppressWarnings("rawtypes")
	protected EntityDao getEntityDao()
	{
		return dataTypeDao;
	}

	/** 取全部 */
	@Cacheable(cacheName = "dataTypeCache")
	public List<SysDataType> findByType(int type)
	{
		return dataTypeDao.findByType(type);
	}

	/** 取可用+dicCode */
	@Cacheable(cacheName = "dataTypeCache")
	public List<SysDataType> findByType(int type, String dicCode)
	{
		return dataTypeDao.findByType(type, dicCode);
	}

	/**
	 * 保存
	 */
	@Override
	@ServiceLog(description = "保存字典数据")
	@TriggersRemove(cacheName = "dataTypeCache", removeAll = true)
	public void save(SysDataType item)
	{
		if (item.getDctcd() != null && item.getDctcd() != "")
		{
			dataTypeDao.update(item);
		}
		else
		{
			String dictCd = "T" + StringUtil.padLeft(String.valueOf(item.getTpid()), 3, "0") + new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
			item.setDctcd(dictCd);
			dataTypeDao.insert(item);
		}
		/*
		 * // 编码转换为大写 item.setTpcd(item.getTpcd().toUpperCase()); String msg = null; // 验证是否存在 int cout = this.dataTypeDao.getDataTypeCount(item.getTpid(), item.getTpcd(), item.getDctcd()); if (cout
		 * == 0) { if (item.getOperTag() == 0) { } else { } msg = "数据保存成功！"; } else { msg = "数据保存失败，编码已存在！"; }
		 */
		// return msg;
	}

	/**
	 * 把列表转换为下拉选项内容
	 * 
	 * @param list 数据源
	 * @param valueIsDctCd false：取dctCd字段, true：取Tpcd字段
	 */
	public String listToOptions(List<SysDataType> list, boolean valueIsDctCd)
	{
		if (list == null)
			return "";

		StringBuilder builder = new StringBuilder();
		builder.append("<option value=0 >-请选择-</option>");
		for (SysDataType type : list)
		{
			// <option value="WY">Wyoming</option>
			/*
			 * if (valueIsDctCd) { builder.append("<option value=\"" + type.getDctcd() + "\">" + type.getTpnm() + "</option>"); } else { builder.append("<option value=\"" + type.getTpcd() + "\">" +
			 * type.getTpnm() + "</option>"); }
			 */
			if (valueIsDctCd)
			{
				builder.append("<option value=\"" + type.getTpcd() + "\">" + type.getTpnm() + "</option>");

			}
			else
			{
				builder.append("<option value=\"" + type.getDctcd() + "\">" + type.getTpnm() + "</option>");
			}
		}
		return builder.toString();
	}

	@Cacheable(cacheName = "dataTypeCache")
	public List<SysDataType> getStoreType(Integer tpid, String stcd)
	{
		return this.dataTypeDao.getStoreType(tpid, stcd);
	}

	/**
	 * 把列表转换为checkbox
	 */
	public String listToCheckbox(List<SysDataType> list)
	{
		if (list == null)
			return "";
		StringBuilder builder = new StringBuilder();
		for (SysDataType dataType : list)
		{
			if (dataType.getChecked() == 1)
			{
				builder.append("<input type=\"checkbox\" name=\"sttp\" value=\"" + dataType.getDctcd() + "\" checked=\"checked\" />" + dataType.getTpnm() + "&emsp;");
			}
			else
			{
				builder.append("<input type=\"checkbox\" name=\"sttp\" value=\"" + dataType.getDctcd() + "\" />" + dataType.getTpnm() + "&emsp;");
			}
		}
		return builder.toString();
	}

	/**
	 * 获取市场信息组织成tab页 xml
	 * 
	 * @param itmcd
	 * @return
	 */
	public String listToTabXml(List<SysDataType> list)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version='1.0' encoding='utf-8'?>");
		builder.append("<tabbar><row>");
		if (list != null)
		{
			for (SysDataType item : list)
			{
				builder.append("<tab id=" + item.getTpcd() + ">" + item.getTpnm() + "</tab>");
			}
		}
		builder.append("</row></tabbar>");
		return builder.toString();
	}

	public String listToJson(List<SysDataType> list)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		if (list != null)
		{
			for (int i = 0; i < list.size(); i++)
			{
				builder.append("{id:\"" + list.get(i).getDctcd() + "\",");
				builder.append("label:\"" + list.get(i).getTpnm() + "\",");
				if (i == 0)
				{
					builder.append("active: true,");
				}
				if (i == list.size() - 1)
				{
					builder.append("width:\"100px\"}");
				}
				else
				{
					builder.append("width:\"100px\"},");
				}
			}
		}
		builder.append("]");
		return builder.toString();
	}

	public SysDataType findByDctcd(String dctcd) {
		return this.dataTypeDao.findByDctcd(dctcd);
	}
}
