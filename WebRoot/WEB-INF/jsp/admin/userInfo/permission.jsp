<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/media/easyui-1.3.2/themes/metro/easyui.css"/>" />
<style>
	input[type="checkbox"]
	{
		margin-left:10px;
		margin-right:5px;
	}
</style>

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		用户信息
		<small>角色管理</small>
	</h3>

	<ul class="breadcrumb">
		<li>
			<i class="icon-home"></i>
			<a href="index.do">我的工作室</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">用户信息</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">角色授权</a>
		</li>
	</ul>
</div>

<!-- END PAGE HEADER-->

<!-- 列表开始 -->
<div class="row-fluid">
	<div class="span12 portlet box yellow">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i>角色授权
			</div>
			<div class="actions">
				<button class="btn red doSearch">
					刷新
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<button class="btn red doSave">
					保存
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<button class="btn red doBack">
					返回
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</div>

		<div class="portlet-body">
			<input type="hidden" id="rlId" name="rlId" value="${roleId}" />

			<table id="permissonList"></table>
		</div>
	</div>
</div>


<script src="<c:url value="/media/easyui-1.3.2/jquery.easyui.min.js"/>" type="text/javascript"></script>

<script type="text/javascript">
	$().ready(function()
	{
		//点击查询
		$(".doSearch").bind("click", function()
		{
			doSearch();
		});

		$(".doBack").bind("click", function()
		{
			backToList();
		});

		$(".doSave").bind("click", function()
		{
			doSave();
		});

		doSearch();
	});

	function doSearch()
	{
		$('#permissonList').treegrid(
		{
			width : '100%',
			fitColumns : false,
			url : "account/getPermission.do?rlId=" + $("#rlId").val(),
			idField : 'functnid',
			treeField : 'functnnm',
			columns : [ [
			{
				field : 'functnnm',
				width : 260,
				title : '模块名称'
			},
			{
				field : 'actionFunctionList',
				title : '操作按钮',
				align : 'left',
				rowspan : 2,
				formatter : function(value, rec)
				{
					var box = "<input type='checkbox' name=" + rec.functnid + " onclick='checkAll(event)'/>全选  <span class='btndiv' id="+rec.functnid+">";
					var actionlist = rec.actionFunctionList;
					if (actionlist.length > 0)
					{
						for ( var i = 0; i < actionlist.length; i++)
						{
							if (actionlist[i].checkid == 1) //已勾权限
							{
								box = box + "<input type='checkbox' id="+actionlist[i].mpid+" checked='checked'/>" + actionlist[i].actnlbl + " ";
							} 
							else
							{
								box = box + "<input type='checkbox' id="+actionlist[i].mpid+" />" + actionlist[i].actnlbl + " ";
							}
						}
					}
					box = box + "</span>";
					return box;
				}
			}
			]],
			onBeforeLoad: function(row, param)
			{
				App.block();
			},
			onLoadSuccess: function(row, data)
			{
				App.unblock();
			}
		});
	}

	function backToList()
	{
		App.loadPage("account/rolePage.do");
	}

	//全选 
	function checkAll(event)
	{
		var functionid = $(event.srcElement ? event.srcElement : event.target).attr("name");
		var checked = $(event.srcElement ? event.srcElement : event.target).attr("checked");
		if (checked)
		{
			$("#" + functionid).find(":checkbox").attr("checked", "checked");
		} else
		{
			$("#" + functionid).find(":checkbox").removeAttr("checked");
		}
	}

	//保存
	function doSave()
	{
		App.block();
		
		var functionDiv = $(".btndiv");
		var permission = "";
		for ( var i = 0; i < functionDiv.length; i++)
		{
			var functionid = $(functionDiv[i]).attr("id");
			var checkboxlist = $(functionDiv[i]).find("input:checkbox:checked");
			for ( var j = 0; j < checkboxlist.length; j++)
			{
				permission += $(checkboxlist[j]).attr("id") + ",";
			}
		}
		$.ajax(
		{
			type : 'post',
			url : "account/savePermission.do",
			data :
			{
				roleId : $("#rlId").val(),
				permission : permission
			},
			success : function(result)
			{
				App.unblock();
				App.alert("角色权限分配成功！");
			},
			error : function(response)
			{
				App.unblock();
				App.alert("角色权限分配失败！");
			}
		});

	}
</script>
