package com.ptsoft.pis.account.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptsoft.common.Constants;
import com.ptsoft.pis.system.dao.SysActionFunctionMapDao;
import com.ptsoft.pis.system.dao.SysFunctionDao;
import com.ptsoft.pis.system.dao.SysPermissionDao;
import com.ptsoft.pis.system.model.vo.MolRoleTree;
import com.ptsoft.pis.system.model.vo.SysActionFunctionMap;
import com.ptsoft.pis.system.model.vo.SysFunction;
import com.ptsoft.pis.system.model.vo.SysPermission;

@Service
public class PermissionService
{

	@Autowired
	private SysActionFunctionMapDao sysActionFunctionMapDao;

	@Autowired
	private SysFunctionDao sysFunctionDao;

	@Autowired
	private SysPermissionDao sysPermissionDao;

	/**
	 * 查询页面及其按钮集合列表
	 * 
	 * @param rlId
	 * @return
	 */
	public List<MolRoleTree> getRoleActionFunctionMap(String rlId)
	{
		//加载Function列表
		List<SysFunction> funList = this.sysFunctionDao.getSysFunctions(rlId);
		HashMap<String, List<SysFunction>> funMap = new HashMap<String, List<SysFunction>>();
		funMap.put(Constants.MENU_ROOT, new ArrayList<SysFunction>());
		//处理Function列表
		for(SysFunction function : funList)
		{
			if (!funMap.containsKey(function.getPrntId()))
			{
				funMap.put(function.getPrntId(), new ArrayList<SysFunction>());
			}
			
			funMap.get(function.getPrntId()).add(function);
		}
		
		//加载ActionFunctionMap表
		List<SysActionFunctionMap> afList = this.sysActionFunctionMapDao.getRoleActionFunctionMap(rlId);
		HashMap<String, List<SysActionFunctionMap>> afMap = new HashMap<String, List<SysActionFunctionMap>>();
		//处理ActionFunctionMap表
		for(SysActionFunctionMap af : afList)
		{
			if (!afMap.containsKey(af.getFnctnid()))
			{
				afMap.put(af.getFnctnid(), new ArrayList<SysActionFunctionMap>());
			}
			
			afMap.get(af.getFnctnid()).add(af);
		}
		
		//生成树
		List<MolRoleTree> treeList = new ArrayList<MolRoleTree>();
		this.findSubFunction(treeList, funMap, afMap, Constants.MENU_ROOT);
		return treeList;
	}

	/**
	 * 查找下级功能
	 * 
	 * @param treeList 生成的结果树
	 * @param funMap 包含SysFunction的Map，直接传
	 * @param afMap 包含SysActionFunctionMap的Map，直接传
	 * @param currentRoot 当前正在处理的根节点
	 * */
	private List<MolRoleTree> findSubFunction(List<MolRoleTree> treeList, HashMap<String, List<SysFunction>> funMap, HashMap<String, List<SysActionFunctionMap>> afMap, String currentRoot)
	{
		Set<String> funIdsSet = funMap.keySet();
		for (String funId : funIdsSet)
		{
			if (!funId.equals(currentRoot))
			{
				continue;
			}
			
			List<SysFunction> funList = funMap.get(funId);
			for (SysFunction function : funList)
			{
				MolRoleTree roleTree = new MolRoleTree();
				roleTree.setFunctnid(function.getFnId());
				roleTree.setFunctnnm(function.getFnNm());
				roleTree.setActionFunctionList(afMap.get(function.getFnId()));
				
				roleTree.setChildren(findSubFunction(new ArrayList<MolRoleTree>(), funMap, afMap, function.getFnId()));
				
				treeList.add(roleTree);
			}
		}
		return treeList;
	}
	
	/**
	 * 保存角色权限
	 * 
	 * @param roleid
	 * @param list
	 */
	public void savePermission(String roleid, ArrayList<String> list)
	{
		this.sysPermissionDao.deletePermissionByRlId(roleid);
		if (list != null && list.size() > 0)
		{
			for (int i = 0; i < list.size(); i++)
			{
				SysPermission sysPermission = new SysPermission();
				sysPermission.setMpid(Integer.parseInt(list.get(i)));
				sysPermission.setRlid(Integer.parseInt(roleid));
				this.sysPermissionDao.insert(sysPermission);
			}
		}
	}
}
