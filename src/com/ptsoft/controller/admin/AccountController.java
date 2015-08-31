package com.ptsoft.controller.admin;

import java.util.ArrayList;
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
import com.ptsoft.pis.account.model.vo.SysRole;
import com.ptsoft.pis.account.model.vo.SysUser;
import com.ptsoft.pis.account.service.PermissionService;
import com.ptsoft.pis.account.service.RoleService;
import com.ptsoft.pis.account.service.UserService;
import com.ptsoft.pis.system.model.vo.SysStore;
import com.ptsoft.pis.system.service.SysStoreService;
import com.ptsoft.pis.system.model.vo.MolRoleTree;

@Controller("AdminAccountController")
@RequestMapping("/admin/account/*")
public class AccountController
{
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SysStoreService storeService;
	@Autowired
	private PermissionService permissionService;

	/** 进入角色页面 */
	@RequestMapping("/rolePage.do")
	public String rolePage(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		model.addAttribute("sysTpSelect", PisConstants.SystemType.ToOptionString());
		model.addAttribute("statusSelect", PisConstants.Available.ToOptionString());

		return "admin/userInfo/role";
	}

	/** 角色列表 */
	@RequestMapping("/roleList.do")
	public void roleList(HttpServletRequest request, HttpServletResponse response)
	{
		List<SysRole> list = roleService.findAll();
		ResponseUtils.renderJsons(response, list);
	}

	/**
	 * 编辑角色
	 */
	@RequestMapping("/roleEdit.do")
	public void roleEdit(HttpServletRequest request, HttpServletResponse response)
	{
		String rlId = request.getParameter("rlId");
		ResponseUtils.renderJson(response, this.roleService.getById(Integer.parseInt(rlId)));
	}

	/** 保存角色 */
	@RequestMapping("/roleSave.do")
	public void roleSave(HttpServletRequest request, HttpServletResponse response, SysRole role)
	{
		String message = null;
		try
		{
			roleService.save(role);

			message = "角色保存成功！";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			message = "角色保存失败！";
		}

		ResponseUtils.renderText(response, message);
	}

	/**
	 * 进入权限管理页面
	 */
	@RequestMapping("/permissionPage.do")
	public String permissionPage(HttpServletResponse response, HttpServletRequest request, Model model, String rlId)
	{
		model.addAttribute("roleId", rlId);
		return "admin/userInfo/permission";
	}

	/**
	 * 角色的权限管理
	 */
	@RequestMapping("/getPermission.do")
	public void getPermission(HttpServletResponse response, String rlId)
	{
		List<MolRoleTree> list = permissionService.getRoleActionFunctionMap(rlId);
		ResponseUtils.renderJsons(response, list);
	}

	/**
	 * 角色权限管理保存
	 */
	@RequestMapping("/savePermission.do")
	public void savePermission(HttpServletResponse response, String roleId, String permission)
	{
		String[] mpids = permission.split(",");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < mpids.length; i++)
		{
			if (!mpids[i].equals(""))
			{
				list.add(mpids[i]);
			}
		}
		permissionService.savePermission(roleId, list);
	}

	/** 进入用户页面 */
	@RequestMapping("/userPage.do")
	public String userPage(HttpServletRequest request, HttpServletResponse response, Model model)
	{
		List<SysRole> roleList = roleService.findAll();
		List<SysStore> storeList = storeService.findAll();
		
		model.addAttribute("roleOption", PisUtils.list2Option(roleList, "getRlId", "getRlNm", "", false));
		model.addAttribute("storeSelect", PisUtils.list2Option(storeList, "getStCd", "getStNm", "", false));
		model.addAttribute("statusSelect", PisConstants.Available.ToOptionString());
		return "admin/userInfo/user";
	}

	/**
	 * 用户列表
	 */
	@RequestMapping("/userList.do")
	public void userList(HttpServletResponse response, HttpServletRequest request, int userType)
	{
		List<SysUser> list = this.userService.findAll(userType);
		ResponseUtils.renderJsons(response, list);
	}

	/**
	 * 显示编辑页面
	 */
	@RequestMapping("/userEdit.do")
	public void userEdit(HttpServletResponse response, HttpServletRequest request)
	{
		String usrId = request.getParameter("usrId");
		SysUser user = this.userService.getById(Integer.parseInt(usrId));
		ResponseUtils.renderJson(response, user);
	}

	/**
	 * 保存用户
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/userSave.do")
	public void userSave(HttpServletResponse response, HttpServletRequest request, SysUser user, int rlId)
	{
		String msg = "";

		SysRole role = new SysRole();
		role.setRlId(rlId);

		user.setRole(role);
		try
		{
			this.userService.save(user);
			msg = "用户保存成功！";
		}
		catch (Exception e)
		{
			msg = "用户保存失败！";
			e.printStackTrace();
		}
		ResponseUtils.renderText(response, msg);
	}

	/**
	 * 编辑个人信息
	 * 
	 * @param response
	 * @param request
	 */
	@RequestMapping("/profilelEdit.do")
	public String profilelEdit(HttpServletResponse response, HttpServletRequest request, Model model)
	{
		SysUser tUser = PisUtils.getCurrentUser(request);
		SysUser user = this.userService.getById(tUser.getUsrId());
		model.addAttribute("info", user);
		
		return "portal/myProfilel";
	}
	
	/**
	 * 保存个人信息
	 * */
	@RequestMapping("/profilelSave.do")
	public void profilelSave(HttpServletResponse response, SysUser user)
	{
		ResponseUtils.renderText(response, this.userService.profilelSave(user));
	}
	
	/**
	 * 保存个人密码
	 * */
	@RequestMapping("/passwordSave.do")
	public void passwordSave(HttpServletResponse response, SysUser user)
	{
		ResponseUtils.renderText(response, this.userService.passwordSave(user));
	}
}
