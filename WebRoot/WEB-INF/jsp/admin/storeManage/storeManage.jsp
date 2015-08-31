<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/daterangepicker.css"/>" />

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		门店信息
		<small>门店管理</small>
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
			<a href="#">门店管理</a>
		</li>
	</ul>
</div>

<!-- END PAGE HEADER-->
<div class="row-fluid">
	<div class="span12 portlet box yellow">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>查询条件
			</div>
			<div class="tools">
				<a href="javascript:;" class="collapse"></a>
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
				<a class="btn red doExport">
					导出
					<i class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
		<div class="portlet-body form">
			<form action="#" class="form-horizontal" id="1158698488">
				<div class="control-group">
					<label class="control-label">
						模糊检索：
					</label>
					<input type="text" id="serachParm" />
				</div>
			</form>
		</div>
	</div>
</div>

<!-- 列表开始 -->
<div class="row-fluid">
	<div class="span12 portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i><span class="titleExcel">门店列表</span>
			</div>
		</div>

		<div class="portlet-body flip-scroll">
			<table id="tbList" class="table table-striped table-bordered table-hover table-full-width" data-click-to-select="true" data-single-select="true">
				<thead>
					<tr>
						<th data-checkbox="true"></th>
						<th data-field="stCd">
							门店编码
						</th>
						<th data-field="stNm">
							门店名称
						</th>
						<th data-field="brndNm">
							品牌
						</th>
						<th data-field="mktNm">
							市场
						</th>
						<th data-field="cityNm">
							城市
						</th>
						<th data-field="stTpNm">
							类型
						</th>
						<th data-field="tel">
							电话
						</th>
						<th data-field="email">
							邮件
						</th>
						<th data-field="adrs">
							地址
						</th>
					</tr>
				</thead>
			</table>

		</div>

	</div>
</div>

<script src="<c:url value="/media/fm/bootstrap-table.js"/>" type="text/javascript"></script>
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
		
		doSearch();
	});
	
	function doSearch()
	{
		App.block();
		var url = "shop/storeDataList.do?serachParm=" + $("#serachParm").val();
		$.get(url, function(data, status)
		{
			$table.bootstrapTable("load", data);

			App.unblock();
		});
	}
	
	function doAddNew()
	{
		App.loadPage("shop/storeEdit.do?stCd=");
	}

	function doEdit()
	{
		var stCd = App.getTableSelection($table).stCd;
		if (stCd == undefined)
		{
			return;
		}
		App.loadPage("shop/storeEdit.do?stCd="+stCd);
	}
	
</script>
