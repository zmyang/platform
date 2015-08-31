<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/daterangepicker.css"/>" />

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		系统设置
		<small>公告管理</small>
	</h3>

	<ul class="breadcrumb">
		<li>
			<i class="icon-home"></i>
			<a href="index.do">我的工作室</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">系统设置</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">公告管理</a>
		</li>
	</ul>
</div>

<!-- 列表开始 -->
<div class="row-fluid">
	<div class="span12 portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i>公告列表
			</div>
			<div class="actions">
				<button class="btn red doSearch">
					查询
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<a data-toggle="modal" href="#formEdit" class="btn green"><i class="icon-plus"></i> 新增</a>
			</div>
		</div>

		<div class="portlet-body flip-scroll">
			<table id="tbList" class="table table-striped table-bordered table-hover table-full-width" id="sample_2">
				<thead>
					<tr>
						<th data-field="bdid">
							序号
						</th>
						<th data-field="pblshdt">
							日期
						</th>
						<th data-field="sbjct">
							公告主题
						</th>
						<th data-formatter="operateFormatter" data-events="operateEvents">
							操作
						</th>
					</tr>
				</thead>
			</table>


			<div id="formEdit" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
				<div class="modal-header">
					<button data-dismiss="modal" class="close" type="button"></button>
					<h3 class="page-title" id="tileBiao">

					</h3>
				</div>
				<div class="modal-body" style="text-align: center;">
					<form action="#" class="form-horizontal">
						<input type="hidden" value="0" id="bdid">
						<div class="control-group">
							<label class="col-sm-2 control-label">
								主题
								<span class="required">*</span>
							</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="" id="sbjct" />
							</div>
						</div>
						<div class="control-group date" id="datetimepicker1">
							<label class="control-label">
								日期
								<span class="required">*</span>
							</label>
							<span class="required" style="visibility: hidden;">*</span>
							<input data-format="yyyy-MM-dd" name="dateTime" style="width: 200px;" type="text" id="dateTime" />
							<span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" class="icon-calendar"> </i> </span>
						</div>
						<div class="control-group">
							<label class="control-label">
								内容
								<span class="required">*</span>
							</label>
							<div class="controls">
								<textarea class="form-control" id="cntnt" rows="3"></textarea>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">
								备注
								<span class="required">*</span>
							</label>
							<div class="controls">
								<textarea class="form-control" id="cmnts" rows="3"></textarea>
							</div>
						</div>
					</form>
				</div>

				<div class="modal-footer">
					<button type="button" data-dismiss="modal" class="btn">
						取消
					</button>
					<button type="button" onclick="saveNotice()" class="confirmReceive btn green">
						确定
					</button>
				</div>
			</div>


		</div>

	</div>
</div>

<script src="<c:url value="/media/fm/daterangepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-datetimepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-table.js"/>" type="text/javascript"></script>
<script type="text/javascript">
	function operateFormatter(value, row, index)
	{
		return '<a class="view" onclick="editShow(' + row.bdid + ')" href="javascript:void(0)">编辑</a>||<a class="del" onclick="deleteNotice(' + row.bdid + ')" href="javascript:void(0)">删除</a>';
	}

	$().ready(function()
	{
		//绑定日期选择控件
		$('#datetimepicker1').datetimepicker(
		{
			language : 'zh-CN',
			pickTime : false,
			todayBtn : true,
			autoclose : true,
			minView : '2',
			forceParse : false,
			format : "yyyy-mm-dd"
		});
		$("#dateTime").val(Date.today().toString('yyyy-MM-dd'));

		//点击查询
		$(".doSearch").bind("click", function()
		{
			doSearch();
		});
		
		doSearch();
	});

	function doSearch()
	{
		App.blockUI($(".page-header-fixed"));

		var url = "notice/noticeList.do";

		//get data
		$.get(url, function(data, status)
		{
			var $table = $("#tbList").bootstrapTable(
			{
				data : data
			});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");

			App.unblockUI($(".page-header-fixed"));
		});
	}

	function editShow(bdid)
	{
		var url = "notice/edit.do?bdid=" + bdid;
		$.get(url, function(data, status)
		{
			$("#bdid").val(data.bdid);
			$("#sbjct").val(data.sbjct);
			$("#dateTime").val(data.pblshdt);
			$("#cntnt").val(data.cntnt);
			$("#cmnts").val(data.cmnts);
		});
		$("#formEdit").modal(
		{
			keyboard : true
		});
	}
	function deleteNotice(bdid)
	{
		if (confirm("确定要删除吗 ?") == false)
		{
			return;
		}
		var url = "notice/deleteNotice.do?bdid=" + bdid;

		//get data
		$.get(url, function(data, status)
		{
			if (data == 1)
			{
				alert("删除成功");
				doSearch();
			} else
			{
				alert("删除失败");
			}
		});
	}

	function saveNotice()
	{
		var formParam =
		{
			"bdid" : $("#bdid").val(),
			"sbjct" : $("#sbjct").val(),
			"pblshdt" : $("#dateTime").val(),
			"cntnt" : $("#cntnt").val(),
			"cmnts" : $("#cmnts").val()
		};

		$.post("notice/saveNotice.do", formParam, function(data)
		{
			if (data == 1)
			{
				alert("保存成功");
				doSearch();
				$("#formEdit").modal("hide");

			} else
			{
				alert("保存失败");
			}
		});
	}
</script>
