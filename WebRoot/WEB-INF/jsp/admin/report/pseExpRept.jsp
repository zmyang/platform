<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/daterangepicker.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/chosen.css"/>" />

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		系统报表
		<small>采购汇总报表</small>
	</h3>

	<ul class="breadcrumb">
		<li>
			<i class="icon-home"></i>
			<a href="index.html">我的工作室</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">系统报表</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">采购汇总报表</a>
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
			<div class="actions">
				<button class="btn red doSearch">
					查询
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<a class="btn red doExport" data-toggle="tbList" data-title="titleExcel" data-time="timeExcel">
					导出
					<i class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>
		<div class="portlet-body form">
			<form action="#" class="form-horizontal">
				<div class="control-group input-append" style="float: left;">
					<label class="control-label">
						日期范围：
					</label>
					<div class="controls">
						<div id="date-range" class="btn">
							<i class="icon-calendar"></i>&nbsp;<span id="rangeTime" class="timeExcel"></span> 
							<b class="caret"></b>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">
						选择物料：
					</label>
					<div class="controls">
						<select id="orderMaterials" class="chosen" data-placeholder="选择物料" style="width: 300px;">
						</select>
					</div>
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
				<i class="icon-globe"></i><span class="titleExcel">采购汇总报表</span>
			</div>
		</div>

		<div class="portlet-body flip-scroll">
			<input type="hidden" id="currentDccd" value='${currentDc.dctcd}' />
			<table id="tbList" class="table table-striped table-bordered table-hover table-full-width" data-click-to-select="true" data-single-select="true">
				<thead>
					<tr>
						<th data-field="MTRLCD">物料编号</th>
						<th data-field="MTRLNM">物料名称</th>
						<th data-field="ORDUNT">单位</th>
						<th data-field="SPC">规格</th>
						<th data-field="SPLRNM">供应商</th>
						<th data-field="FMNO">采购单号</th>
						<th data-field="STNGCNT">数量</th>
						<th data-formatter="moneyFormatter">金额</th>
					</tr>
				</thead>
			</table>
			
			<div id="selectDc" style="width: 500px; left: 45%;" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
				<div class="modal-header">
					<h3 id="timu" class="page-title"> 选择配送中心</h3>
					<input id="hidFormNo" type="hidden" />
				</div>
				<div class="modal-body form-horizontal">
					<div class="control-group">
						<label class="control-label">
							请选择
							<span class="required">*</span>
						</label>
						<div class="controls">
							<select id="dcselect" name="dcselect">
								${sDistribution }
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="portlet-footer sumMoney">
			<button class="btn green">
				<strong>合计：￥</strong>
				<span class="moneySum"></span>
			</button>
		</div>
	</div>
</div>

<script src="<c:url value="/media/fm/daterangepicker.js"/>"	type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-datetimepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-table.js"/>"	type="text/javascript"></script>
<script src="<c:url value="/media/fm/chosen.jquery.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/hashMap.js"/>" type="text/javascript"></script>

<script type="text/javascript">

//拨出单列表
var $table = App.initTable($("#tbList"));
//配送中心
var dccd;
//物料Map
var materialMap;

$().ready(function() 
{
	$("#date-range").daterangepicker(
	{
		opens: (App.isRTL() ? 'left' : 'right'),
		format: 'yyyy-MM-dd',
        separator: ' ~ ',
	    startDate: Date.today().set({day:1}),
		endDate: Date.today(),
		minDate: '2012-01-01',
		maxDate: Date.today()
	},
	function (start, end) 
	{
		$('#date-range span').html(start.toString('yyyy-MM-dd') + ' ~ ' + end.toString('yyyy-MM-dd'));
	});
	//初始化选中的日期
	$("#date-range span").html(Date.today().set({day:1}).toString('yyyy-MM-dd') + ' ~ ' + Date.today().toString('yyyy-MM-dd'));
	
	//点击查询
	$(".doSearch").bind("click", function()
	{
		doSearch();
	});
	
	$("#orderMaterials").change( function () 
	{
		doSearch();
	});
	
	checkDcCode();
	handleLoadMaterials();
});

//查询
function doSearch()
{
	
	App.blockUI($(".table"));
	var rangeTime = $("#rangeTime").text();
	var times = new Array();
	times = rangeTime.split(" ");
	
	var fmtm = times[0];
	var totm = times[2];
	var mtrlcd = $("#orderMaterials").val();
	
	var url = "report/getPseExpList.do?dccd=" + dccd + "&fmtm=" + fmtm + "&totm=" + totm + "&mtrlcd=" + mtrlcd;

	//get data
	$.get(url, function (data, status) 
	{
		$table.bootstrapTable("load", data);
		$table.bootstrapTable("hideLoading");
		
		getMoneySum();
	});
	
	App.unblockUI($(".table"));
}

function handleLoadMaterials()
{
	var ctgrcd = $("#orderCategories").val();
	if(ctgrcd == null)
	{
		ctgrcd = "";
	}
	
	//get data
	$.get("report/getMaterials.do?ctgrcd=", function (data, status) 
	{
		materialMap = new HashMap();
		var trHtml = '<option value=""></option>';
		$.each(data, function(i, material) 
		{
			trHtml += '<option value="' + material.mtrlcd + '">' + material.mtrlcd + " " + material.mtrlnm + '</option>';
			materialMap.put(material.mtrlcd, material);
		});
		trHtml += '<option value="">全部</option>';
		materialMap.put("", "");
		$("#orderMaterials").html(trHtml);
		
		var $chosen = $("#orderMaterials").next();
		if ($chosen.hasClass("chzn-container"))
		{
			$("#orderMaterials").removeClass("chzn-done");
			$chosen.remove();
		}
		$("#orderMaterials").chosen();
			
    });
}

//配送中心选择
function checkDcCode()
{
	dccd = $("#currentDccd").val();
	if(dccd == "")
	{
		App.block();
		$("#selectDc").modal({keyboard : true});
	}
	else
	{
		doSearch();
	}	
	
	$("#dcselect").change( function () 
	{
		dccd = $("#dcselect").val();
		$("#currentDcnm").val($("#selectDc").find("option:selected").text());
		if(dccd != "-1")
		{
			$.post("logistics/updateCurrentDc.do", {dcCode:dccd}, function (data) 
			{
				$("#selectDc").modal("hide");
				App.unblock();
				doSearch();
			});
		}
	});
}

function getMoneySum()
{
	var moneySum = 0;
	$table.find("tr").each( function ()
	{
		moneySum += Number($(this).find(".money").text());
	});
	$(".moneySum").text(currency(moneySum));
}

function moneyFormatter(value, row, index)
{
	var saleprc = row.SALEPRC == undefined ? 0 : row.SALEPRC;
	var stngCnt = row.STNGCNT == undefined ? 0 : row.STNGCNT;
	return '<span class="money">' + currency(ptMul(saleprc, stngCnt)) + '</span>';
}

</script>