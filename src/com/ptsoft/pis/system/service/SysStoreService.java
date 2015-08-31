package com.ptsoft.pis.system.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptsoft.common.base.BaseService;
import com.ptsoft.common.base.EntityDao;
import com.ptsoft.pis.system.dao.SysStoreDao;
import com.ptsoft.pis.system.dao.SysStoreOtherDao;
import com.ptsoft.pis.system.model.vo.SysStore;
import com.ptsoft.pis.system.model.vo.SysStoreOther;

@Service
public class SysStoreService extends BaseService<SysStore, String>
{
	@Autowired
	private SysStoreDao storeDao;
	
	@Autowired
	private SysStoreOtherDao otherDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected EntityDao getEntityDao()
	{
		return this.storeDao;
	}

	@Override
	public List<SysStore> findAll()
	{
		return storeDao.findAll();
	}
	
	public List<SysStore> searchItems(String parm)
	{
		return storeDao.searchItems(parm);
	}
	
	public String saveSysStore(SysStore entity)
	{
		String msg = null;
		int cotStcd = this.storeDao.getStoreByStcd(entity.getStCd());

		try
		{
			if (cotStcd > 0)
			{
				this.storeDao.update(entity);
				msg = "门店信息更新成功！";
			}
			else
			{
				//新增门店基础数据
				this.storeDao.insert(entity);
				
				//新增门店其它数据
				SysStoreOther other = new SysStoreOther();
				other.setStCd(entity.getStCd());
				this.otherDao.insert(other);
				
				msg = "门店信息保存成功！";
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			msg = "门店信息保存失败！";
		}
		return msg;
	}
	
	private Map<String, List<SysStore>> storeMarketMap = null;

	public String getStoreTree(List<SysStore> list)
	{
		this.storeMarketMap = new LinkedHashMap<String, List<SysStore>>();

		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version='1.0' encoding='utf-8'?>");
		builder.append("<tree id=\"0\">");
		if (list != null)
		{
			for (SysStore store : list)
			{
				//storeMarketMap
				if (store.getMktNm() == null)
					continue;
				if (this.storeMarketMap.get(store.getMktNm()) == null)
				{
					this.storeMarketMap.put(store.getMktNm(), new ArrayList<SysStore>());
				}
				this.storeMarketMap.get(store.getMktNm()).add(store);

				//storeXxxMap
			}
		}
		//生成根节点
		this.genRoot(builder);

		builder.append("</tree>");
		String xml = builder.toString();
		return xml;
	}

	private void genRoot(StringBuilder builder)
	{
		builder.append("<item text=\"全国\" id=\"root\" open=\"1\" im0=\"leaf.gif\" im1=\"folderOpen.gif\" im2=\"folderClosed.gif\">");
		builder.append("<userdata name=\"type\">ROOT</userdata>");
		builder.append("<userdata name=\"data\">root</userdata>");
		this.genMarket(builder);
		builder.append("</item>");
	}

	private void genMarket(StringBuilder builder)
	{
		for (String market : storeMarketMap.keySet())
		{
			builder.append("<item text=\"" + market + "\" id=\"" + market + "\" close=\"1\">");
			builder.append("<userdata name=\"type\">MARKET</userdata>");
			builder.append("<userdata name=\"data\">" + market + "</userdata>");
			//this.genXxx(builder);
			builder.append("</item>");
		}
	}

	public String getStoreList(List<SysStore> list, String type, String data)
	{
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version='1.0' encoding='utf-8'?>");
		builder.append("<rows>");

		if (type.equals("ROOT")) //root表示全国
		{
			for (SysStore item : list)
			{
				this.getStoreRow(builder, item);
			}
		}
		else if (type.equals("MARKET"))
		{
			for (SysStore item : this.storeMarketMap.get(data))
			{
				this.getStoreRow(builder, item);
			}
		}
		builder.append("</rows>");
		String xml = builder.toString();
		return xml;
	}

	private void getStoreRow(StringBuilder builder, SysStore item)
	{
		builder.append("<row id=\"" + item.getStCd() + "\">");
		builder.append("<cell>" + item.getStCd() + "</cell>");
		builder.append("<cell>" + StringUtils.trimToEmpty(item.getStNm()) + "</cell>");
		builder.append("</row>");
	}

	@Override
	public void save(SysStore entity)
	{

	}

	public List<SysStore> findByBrndcd(String brndcd) 
	{
		return this.storeDao.findByBrndcd(brndcd);
	}

	public List<SysStore> getStListByStcd(String stcd)
	{
		return this.storeDao.getStListByStcd(stcd);
	}

}
