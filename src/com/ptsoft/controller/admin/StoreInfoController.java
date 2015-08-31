package com.ptsoft.controller.admin;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptsoft.common.util.ResponseUtils;
import com.ptsoft.pis.PisConstants;
import com.ptsoft.pis.PisUtils;
import com.ptsoft.pis.system.model.vo.SysStore;
import com.ptsoft.pis.system.model.vo.SysStoreGroup;
import com.ptsoft.pis.system.model.vo.SysStoreOther;
import com.ptsoft.pis.system.service.SysStoreGroupService;
import com.ptsoft.pis.system.service.SysStoreOtherService;
import com.ptsoft.pis.system.service.SysStoreService;
import com.ptsoft.pis.system.service.SysDataTypeService;

@Controller
/**
 * 门店信息
 */
@RequestMapping("/admin/shop/*")
public class StoreInfoController
{
	@Autowired
	private SysStoreService storeService;
	
	@Autowired
	private SysStoreOtherService otherService;
	
	@Autowired
	private SysDataTypeService dtService;
	
	@Autowired
	private SysStoreGroupService groupService;
	
	/** 进入 门店管理页面 */
	@RequestMapping("/toStorePage.do")
	public String toStorePage(HttpServletRequest request, HttpServletResponse response)
	{
		return "admin/storeManage/storeManage";
	}

	/** 门店管理列表列表 */
	@RequestMapping("/storeDataList.do")
	public void storeList(HttpServletRequest request, HttpServletResponse response, String serachParm)
	{
		if (serachParm == null || serachParm.equals(""))
		{
			ResponseUtils.renderJsons(response, this.storeService.findAll());
		}
		else 
		{
			ResponseUtils.renderJsons(response, this.storeService.searchItems(serachParm));
		}
	}

	/**
	 * 显示编辑页面
	 */
	@RequestMapping("/storeEdit.do")
	public String storeEdit(HttpServletRequest request, HttpServletResponse response, Model model, String stCd)
	{
		SysStore store = null;
		SysStoreOther other = null;
		if (!stCd.equals(""))
		{
			store = this.storeService.getById(stCd);
			other = this.otherService.getById(stCd);
		}
		if (store == null)
		{
			store = new SysStore();
		}
		if (other == null)
		{
			other = new SysStoreOther();
			other.setStCd(store.getStCd());
		}
		
		List<PisConstants.Status> yesnos = Arrays.asList(PisConstants.Status.values());
		List<PisConstants.Available> availables = Arrays.asList(PisConstants.Available.values());
		
		model.addAttribute("store", store);
		model.addAttribute("other", other);
		
		model.addAttribute("sBrand", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.Brand.getKey()), "getDctcd", "getTpnm", store.getBrndCd(), false));
		model.addAttribute("sMarket", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.Market.getKey()), "getDctcd", "getTpnm", store.getMktCd(), false));
		model.addAttribute("sZone", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.Zone.getKey()), "getDctcd", "getTpnm", store.getZnCd(), false));
		model.addAttribute("sCompany", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.Company.getKey()), "getDctcd", "getTpnm", store.getCmpnCd(), false));
		model.addAttribute("sStoreType", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.StoreType.getKey()), "getDctcd", "getTpnm", store.getStTpCd(), false));
		model.addAttribute("sCity", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.City.getKey()), "getDctcd", "getTpnm", store.getCityCd(), false));
		model.addAttribute("sPriceType", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.PriceSystem.getKey()), "getDctcd", "getTpnm", store.getNewPrcTp(), false));
		model.addAttribute("sPosType", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.PosType.getKey()), "getDctcd", "getTpnm", other.getPosCd(), false));
		model.addAttribute("sAtndnType", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.AtndnType.getKey()), "getDctcd", "getTpnm", other.getAtndnCd(), false));
		model.addAttribute("sDistribution", PisUtils.list2Option(this.dtService.findByType(PisConstants.DataType.Distribution.getKey()), "getDctcd", "getTpnm", other.getDcCd(), false));
		model.addAttribute("sIs24H",  PisUtils.list2Option(yesnos, "getKey", "getText", String.valueOf(store.getIs24h()), false));
		model.addAttribute("sSts",  PisUtils.list2Option(availables, "getKey", "getText", String.valueOf(store.getSts()), false));
		
		return "admin/storeManage/storeEditor";
	}
	
	/** 保存门店信息 */
	@RequestMapping("/storeSave.do")
	public void storeSave(HttpServletRequest request, HttpServletResponse response, SysStore store)
	{
		String msg = this.storeService.saveSysStore(store);
		ResponseUtils.renderText(response, msg);
	}
	
	/** 保存门店信息 */
	@RequestMapping("/otherSave.do")
	public void otherSave(HttpServletRequest request, HttpServletResponse response, SysStoreOther other)
	{
		String msg = this.otherService.saveSysStoreOther(other);
		ResponseUtils.renderText(response, msg);
	}

	
	
	
	
	
	
	
	
	
	/**
	 * 自定义组
	 */
	@RequestMapping("/customizePage.do")
	public String customize(HttpServletRequest request, HttpServletResponse response)
	{
		return "admin/storeManage/customize";
	}
	
	/**
	 * 自定义组列表
	 */
	@RequestMapping("/customizeList.do")
	public void customizeList(HttpServletRequest request, HttpServletResponse response)
	{
		List<SysStoreGroup> list = groupService.findAll();
		ResponseUtils.renderJsons(response, list);
	}
	
	/**
	 * 编辑-根据ID获取自定义组信息
	 */
	@RequestMapping("/customizeEdit.do")
	public void customizeEdit(HttpServletRequest request, HttpServletResponse response, String grpId)
	{
		ResponseUtils.renderJson(response, this.groupService.getById(grpId));
	}

	/**
	 * 保存自定义组信息
	 */
	@RequestMapping("/saveCustomize.do")
	public void saveCustomize(HttpServletRequest request, HttpServletResponse response, SysStoreGroup group)
	{
		String message = null;
		try
		{
			this.groupService.save(group);
			message = "数据保存成功！";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			message = "数据保存失败！";
		}
		ResponseUtils.renderText(response, message);
	}
	
	/**
	 * 自定义组设置-根据组ID获取门店列表
	 */
	@RequestMapping("/getStores.do")
	public void getStores(HttpServletRequest request, HttpServletResponse response, Model model, String grpid)
	{
		ResponseUtils.renderText(response, PisUtils.list2Option(this.storeService.findAll(), "getStCd", "getStNm", this.groupService.getMaps(grpid), false));
	}
	
	@RequestMapping("/saveStoreMap.do")
	public void saveStoreMap(HttpServletRequest request, HttpServletResponse response, String grpid, String stCds)
	{
		String msg = "";
		try 
		{
			this.groupService.saveMap(grpid, stCds.split(","));
			msg = "自定义组门店选择保存成功！";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			msg = "自定义组门店选择保存失败！";
		}
		ResponseUtils.renderText(response, msg);
	}

}
