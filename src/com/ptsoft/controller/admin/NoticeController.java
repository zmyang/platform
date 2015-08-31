package com.ptsoft.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ptsoft.common.util.ResponseUtils;
import com.ptsoft.pis.system.model.vo.SysBulletinBorad;
import com.ptsoft.pis.system.service.SysBulletinBoradService;

@Controller
@RequestMapping("/admin/notice/*")
public class NoticeController {
	@Autowired
	private SysBulletinBoradService bulletinBoradService;

	/** 进入公告页面 */
	@RequestMapping("/noticePage.do")
	public String notice(HttpServletRequest request,
			HttpServletResponse response) {
		return "admin/notice/notice";
	}

	/** 公告列表 */
	@RequestMapping("/noticeList.do")
	public void noticeList(HttpServletRequest request,
			HttpServletResponse response) {
		List<SysBulletinBorad> list = bulletinBoradService.findAll();
		ResponseUtils.renderJsons(response, list);
	}

	/** 删除公告 */
	@RequestMapping("/deleteNotice.do")
	public void deleteNotice(HttpServletRequest request,
			HttpServletResponse response) {
		String bdid = request.getParameter("bdid");
		String message = null;
		try {
			SysBulletinBorad bulletinBorad = new SysBulletinBorad();
			bulletinBorad.setBdid(Integer.valueOf(bdid));
			bulletinBoradService.deletebulletinBorad(bulletinBorad);
			message = "1";
		} catch (Exception e) {
			message = "2";
		}
		ResponseUtils.renderText(response, message);
	}

	/** 保存公告 */
	@RequestMapping("/saveNotice.do")
	public void saveNotice(HttpServletRequest request,HttpServletResponse response) {
		SysBulletinBorad sb = new SysBulletinBorad();
		SysBulletinBorad copy = new SysBulletinBorad();

		String bdid = request.getParameter("bdid");
		sb.setBdid(Integer.parseInt(bdid));
		String sbjct = request.getParameter("sbjct");
		sb.setSbjct(sbjct);
		String pblshdt = request.getParameter("pblshdt");
		sb.setPblshdt(pblshdt);
		String cntnt = request.getParameter("cntnt");
		sb.setCntnt(cntnt);
		String cmnts = request.getParameter("cmnts");
		sb.setCmnts(cmnts);
		String message = null;
		try {
			if (sb.getBdid() != 0) {
				copy.setBdid(sb.getBdid());
				copy = bulletinBoradService.getSysBulletinBoradByBdid(sb
						.getBdid());
			}
			new DozerBeanMapper().map(sb, copy);
			bulletinBoradService.save(copy);
			message = "1";
		} catch (Exception e) {
			message = "2";
		}
		ResponseUtils.renderText(response, message);
	}

	/**
	 * 显示编辑页面
	 */
	@RequestMapping("/edit.do")
	public void edit(HttpServletRequest request, HttpServletResponse response) {
		String bdid = request.getParameter("bdid");
		ResponseUtils.renderJson(response,
				bulletinBoradService.getById(Integer.parseInt(bdid)));
	}
}
