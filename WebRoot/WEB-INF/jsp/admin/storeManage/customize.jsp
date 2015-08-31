<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/multi-select-metro.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/chosen.css"/>" />

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		门店信息
		<small>自定义组</small>
	</h3>

	<ul class="breadcrumb">
		<li>
			<i class="icon-home"></i>
			<a href="index.do">我的工作室</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">门店信息</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">自定义组</a>
		</li>
	</ul>
</div>

<!-- END PAGE HEADER-->
<!-- 列表开始 -->
<div class="row-fluid">
	<div class="span12 portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i>自定义组
			</div>
			<div class="actions">
				<button class="btn red doSearch">
					刷新
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<button class="btn red doAddNew">
					新增
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<button class="btn red doEdit">
					编辑
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<button class="btn red doSetting">
					设置
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</div>

		<div class="portlet-body flip-scroll">
			<table id="tbList" class="table table-striped table-bordered table-hover table-full-width" data-click-to-select="true" data-single-select="true">
				<thead>
					<tr>
						<th data-checkbox="true"></th>
						<th data-field="grpId" data-visible="false">
							序号
						</th>
						<th data-field="grpNm">
							名称
						</th>
						<th data-field="cmnts">
							备注
						</th>
					</tr>
				</thead>
			</table>

			<div id="formEdit" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3 class="page-title" id="tileBiao">
						自定义组
					</h3>
				</div>
				<form action="#" id="fmDataEdit" class="form-horizontal">
					<div class="modal-body">
						<input type="hidden" name="grpId" id="grpId"/>

						<div class="control-group">
							<label class="control-label">
								名称
								<span class="required">*</span>
							</label>
							<div class="controls">
								<input type="text" class="form-control" id="grpNm" name="grpNm" />
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">
								描述
							</label>
							<div class="controls">
								<textarea class="form-control" id="cmnts" name="cmnts" rows="3"></textarea>
							</div>
						</div>
					</div>

					<div class="modal-footer">
						<button type="button" data-dismiss="modal" class="btn red">
							取消
						</button>
						<button type="button" class="btn green doSave">
							保存
						</button>
					</div>
				</form>
			</div>
			
			<div id="settings" class="modal hide fade" tabindex="-1" style="width: 600px;" data-backdrop="static" data-keyboard="false">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3 class="page-title" id="tileBiao">
						自定义组—门店
					</h3>
				</div>
				<div class="modal-body">
					<input type="hidden" name="grpid" id="grpid"/>
					<div class="control-group">
						<label class="control-label">
						</label>
						<div class="controls" id="selection">
							<select multiple="multiple" id="stcd" name="stcd">
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn red">
						取消
					</button>
					<button type="button" class="btn green doSettings">
						保存
					</button>
				</div>
			</div>
		</div>
	</div>
</div>


<script src="<c:url value="/media/fm/bootstrap-table.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.validate.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.validate.messages_cn.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.multi-select.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/chosen.jquery.min.js"/>" type="text/javascript"></script>

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
		
		$(".doSave").bind("click", function()
		{
			doSave();
		});
		
		$(".doSetting").bind("click", function()
		{
			doSetting();
		});
		
		$(".doSettings").bind("click", function()
		{
			doSettings();
		});
		
		$("#fmDataEdit").validate(
		{
			rules :
			{
				grpNm :
				{
					required : true
				}
			}
		});
	});

	function doSearch()
	{
		App.block();
		$.get("shop/customizeList.do", function(data, status)
		{
			$table.bootstrapTable("load", data);

			App.unblock();
		});
	}
	
	function doAddNew()
	{
		$("#grpId").val(0);
		$("#grpNm").val("");
		$("#cmnts").text("");
		
		$("#formEdit").modal(
		{
			keyboard : true
		});
	}
	
	function doEdit()
	{
		var grpId = App.getTableSelection($table).grpId;
		if (grpId == undefined)
		{
			return;
		}
		$.get("shop/customizeEdit.do?grpId=" + grpId, function(data, status)
		{
			$("#grpId").val(data.grpId);
			$("#grpNm").val(data.grpNm);
			$("#cmnts").text(data.cmnts);
		});
		
		$("#formEdit").modal(
		{
			keyboard : true
		});
	}

	function doSave()
	{
		if ($("#fmDataEdit").valid() == false)
		{
			return;
		}
		
		var formParam = $("#fmDataEdit").serialize();
		
		$.post("shop/saveCustomize.do", formParam, function(data)
		{
			App.alert(data);

			doSearch();

			$("#formEdit").modal("hide");
		});
	}
	
	function doSetting()
	{
		var grpId = App.getTableSelection($table).grpId;
		if (grpId == undefined)
		{
			App.alert("请选择组！");
			return;
		}
		
		//清空selection 重新加载
		$("#selection").empty();
		$.get("shop/getStores.do?grpid=" + grpId, function(data, status)
		{
			$("#grpid").val(grpId);
			
			var str = "<select multiple='multiple' id='stcd' name='stcd'>";
			str += data + "</select>";
			
			$("#selection").append(str);
			$("#stcd").multiSelect();
		});
		
		$("#settings").modal(
		{
			keyboard : true
		});
	}
	
	function doSettings()
	{
		var grpid = $("#grpid").val();
		if (grpid == undefined || grpid == "")
		{
			App.alert("未选择自定义组！");
			return;
		}
		var stCds = $("#stcd").val();
		if (stCds == null)
		{
			stCds = "";
		}
		else
		{
			stCds = stCds.join(",");
		}
		
		var data = 
		{
			grpid : grpid,
			stCds : stCds
		};
		
		App.block();
		$.post("shop/saveStoreMap.do", data, function(data) 
		{
			App.alert(data);

			$("#settings").modal("hide");
			doSearch();
			App.unblock();
		});
	}
</script>
