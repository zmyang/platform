<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/daterangepicker.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/jquery.treegrid.css"/>" />

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
			<a href="#">角色管理</a>
		</li>
	</ul>
</div>

<!-- END PAGE HEADER-->

<!-- 列表开始 -->
<div class="row-fluid">
	<div class="span12 portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i><span class="titleExcel">角色列表</span>
			</div>
			<div class="actions">
				<button class="btn red doSearch"> 刷新 <i class="m-icon-swapright m-icon-white"></i> </button>
				<button class="btn red doAddNew"> 新增 <i class="m-icon-swapright m-icon-white"></i> </button>
				<button class="btn red doEdit"  > 编辑 <i class="m-icon-swapright m-icon-white"></i> </button>
				<button class="btn red doAuthority"> 授权 <i class="m-icon-swapright m-icon-white"></i> </button>
				<a class="btn red doExport"> 导出 <i class="m-icon-swapright m-icon-white"></i> </a>
			</div>
		</div>

		<div class="portlet-body flip-scroll">
			<table id="tbList" class="table table-striped table-bordered table-hover table-full-width" data-click-to-select="true" data-single-select="true">
				<thead>
					<tr>
						<th data-checkbox="true"></th>
						<th data-field="rlId" data-visible="false">角色编码</th>
						<th data-field="rlNm">角色名称</th>
						<th data-field="stsName">状态</th>
						<th data-field="typeName">类型</th>
						<th data-field="cmnts">备注</th>
					</tr>
				</thead>
			</table>

			<div id="formEdit" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3 class="page-title" id="tileBiao">
						角色信息
					</h3>
				</div>
				<div class="modal-body" style="text-align: center;">
					<form id="fmRoleEdit" action="#" class="form-horizontal">
						<input type="hidden" value="0" id="rlId">
						<div class="control-group">
							<label class="control-label">角色名称<span class="required">*</span></label>
							<div class="controls">
								<input type="text" class="large m-wrap" id="rlNm" name="rlNm">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">角色类型<span class="required">*</span></label>
							<div class="controls">
								<select class="large m-wrap" id="sysTp">
									${sysTpSelect}
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">状态<span class="required">*</span></label>
							<div class="controls">
								<select class="large m-wrap" id="sts">
									${statusSelect}
								</select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">描述<span class="required">*</span></label>
							<div class="controls">
								<textarea class="large m-wrap" id="cmnts" rows="6"></textarea>
							</div>
						</div>
					</form>
				</div>

				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn red">取消</button>
					<button type="button" onclick="doSave()" class="btn green">保存</button>
				</div>
			</div>


			<div id="roleChoonse" class="modal hide fade flip-scroll" tabindex="-1" data-backdrop="static" data-keyboard="false">
				<input type="hidden" id="rlId" name="rlId" value="${roleId}" />

				<table class="table table-striped table-bordered">
					<tr>
						<td valign="top">
							<table id="permissonList" title="权限管理" class="tree">
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="buttons" align="center">
							<input type="submit" value="保 存" class="button" data-dismiss="modal" />
						</td>
					</tr>
				</table>
			</div>
		</div>

	</div>
</div>

<script src="<c:url value="/media/fm/daterangepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-datetimepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-table.js"/>"	type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.validate.min.js"/>"	type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.validate.messages_cn.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/treegrid.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.treegrid.bootstrap3.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.treeview.js"/>"	type="text/javascript"></script>

<script type="text/javascript">

	var $table = App.initTable($("#tbList"));

	$().ready(function() 
	{
		doSearch();
		
		//点击查询
		$(".doSearch").bind("click", function() 
		{
			doSearch();
		});
		
		$(".doAddNew").bind("click", function() 
		{
			doAddNew();
		});
		
		$(".doEdit").bind("click", function() 
		{
			doEdit();
		});
		
		$(".doAuthority").bind("click", function() 
		{
			doAuthority();
		});
		
		$("#fmRoleEdit").validate
		({
			rules: 
			{
				rlNm: 
				{
                    minlength: 2,
                    required: true
                }
            }
		});
	});

	function doSearch() 
	{
		App.block();
		$.get("account/roleList.do", function(data, status) 
		{
			$table.bootstrapTable("load", data);
			
			App.unblock();
		});
	}
	
	function doAddNew()
	{
		$("#sts").val(1);
		$("#rlId").val(0);
		$("#rlNm").val("");
		$("#cmnts").val("");
		
		$("#formEdit").modal({keyboard : true});
	}
	
	function doEdit() 
	{
		var rlId = App.getTableSelection($table).rlId;
		if (rlId == undefined) return;
		
		var url = "account/roleEdit.do?rlId=" + rlId;
		$.get(url, function(data, status) 
		{
			$("#sts").val(data.sts);
			$("#rlId").val(data.rlId);
			$("#rlNm").val(data.rlNm);
			$("#cmnts").val(data.cmnts);
			$("#sysTp").val(data.sysTp);
		});
		
		$("#formEdit").modal({keyboard : true});
	}
	
	function doSave() 
	{
		if ($("#fmRoleEdit").valid() == false)
		{
			return;
		}
		
		var formParam = 
		{
			"sts" : $("#sts").val(),
			"rlId" : $("#rlId").val(),
			"rlNm" : $("#rlNm").val(),
			"cmnts" : $("#cmnts").val(),
			"sysTp" : $("#sysTp").val()
		};

		$.post("account/roleSave.do", formParam, function(data) 
		{
			App.alert(data);
			
			doSearch();
			$("#formEdit").modal("hide");
		});
	}
	
	function doAuthority() 
	{
		var rlId = App.getTableSelection($table).rlId;
		if (rlId == undefined) return;
		
		App.loadPage("account/permissionPage.do?rlId=" + rlId);
	}

	
</script>
