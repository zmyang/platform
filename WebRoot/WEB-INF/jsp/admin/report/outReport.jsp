<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/daterangepicker.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/chosen.css"/>" />

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		系统报表
		<small>出库报表</small>
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
			<a href="#">出库报表</a>
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
				<a class="btn red doExport">
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
						<div id="date-range" class="btn" style="width: 275px">
							<i class="icon-calendar"></i>&nbsp;<span id="rangeTime" class="timeExcel"></span> 
							<b class="caret"></b>
						</div>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">
						物料类别：
					</label>
					<div class="controls">
						<select id="orderCategories" class="chosen" data-placeholder="选择物料类别" tabindex="1" style="width: 300px;">
						</select>
					</div>
				</div>
			</form>
			<form action="#" class="form-horizontal" id="1158698488">
				<div class="control-group input-append" style="float: left;">
					<label class="control-label">
						选择品牌：
					</label>
					<div class="controls">
						<select id="orderBrand" style="width: 300px;">
							${sBrand }
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">
						选择门店：
					</label>
					<div class="controls">
						<select id="orderStore" class="chosen" data-placeholder="选择门店" tabindex="1" style="width: 300px;">
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
				<i class="icon-globe"></i><span class="titleExcel">出库报表</span>
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
						<th data-field="STNM">门店</th>
						<th data-field="BRANDNM">品牌</th>
						<th data-field="FMNO">出库单号</th>
						<th data-field="BIZDT">日期</th>
						<th data-field="CHKCNT">数量</th>
						<th data-formatter="actlAmntFormatter">成本金额</th>
						<th data-formatter="saleAmntFormatter">销售金额</th>
					</tr>
				</thead>
			</table>
			
			<div id="selectDc" style="width: 500px; left: 45%;"
				class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
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
				<strong>成本合计：￥</strong>
				<span class="actlAmntSum"></span>
			</button>
			<button class="btn green">
				<strong>销售合计：￥</strong>
				<span class="saleAmntSum"></span>
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

//出库列表
var $table = App.initTable($("#tbList"));
//配送中心
var dccd;
//物料类别map
var categoryMap;
//门店Map
var storeMap;

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
	
	$("#orderCategories").change( function()
	{
		doSearch();
	});
	
	$("#orderBrand").change( function ()
	{
		handleLoadStore();
	});
	
	$("#orderStore").change( function()
	{
		doSearch();
	});
	
	checkDcCode();
	handleLoadMaterialCategory();
	handleLoadStore();
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
	var ctgrcd = $("#orderCategories").val();
	var stcd = $("#orderStore").val();
	var brndcd = $("#orderBrand").val();
	
	if(brndcd == "-1")
	{
		brndcd = "";
	}
	var url = "report/getOutReptList.do?dccd=" + dccd + "&ctgrcd=" + ctgrcd + "&stcd=" + stcd + "&brndcd=" + brndcd + "&fmtm=" + fmtm + "&totm=" + totm;

	//get data
	$.get(url, function (data, status) 
	{
		$table.bootstrapTable("load", data);
		$table.bootstrapTable("hideLoading");
		
		getMoneySum();
	});
	
	App.unblockUI($(".table"));
}

//加载物料类别
function handleLoadMaterialCategory()
{
	//get data
	$.get("report/getCategories.do", function (data, status) 
	{
		categoryMap = new HashMap();
		var trHtml = '<option value=""></option>';
		$.each(data, function(i, category) 
		{
			trHtml += '<option value="' + category.ctgrcd + '">' + category.ctgrcd + " " + category.ctgrnm + " " + category.mnmnccd + '</option>';
			categoryMap.put(category.ctgrcd, category);
		});
		trHtml += '<option value="">全部</option>';
		categoryMap.put("", "");
		$("#orderCategories").html(trHtml);
		
		var $chosen = $("#orderCategories").next();
		if ($chosen.hasClass("chzn-container"))
		{
			$("#orderCategories").removeClass("chzn-done");
			$chosen.remove();
		}
		$("#orderCategories").chosen();
			
    });
}

//加载门店
function handleLoadStore()
{
	var brndcd = $("#orderBrand").val();
	if(brndcd == "-1")
	{
		brndcd = "";
	}
	//get data
	$.get("report/getStores.do?brndcd=" + brndcd, function (data, status) 
	{
		storeMap = new HashMap();
		var trHtml = '<option value=""></option>';
		$.each(data, function(i, store) 
		{
			trHtml += '<option value="' + store.stCd + '">' + store.stCd + "　" + store.stNm + '</option>';
			storeMap.put(store.stCd, store);
		});
		if(brndcd == "")
		{
			trHtml += '<option value="">全部</option>';
			storeMap.put("", "");
		}
		$("#orderStore").html(trHtml);
		
		var $chosen = $("#orderStore").next();
		if ($chosen.hasClass("chzn-container"))
		{
			$("#orderStore").removeClass("chzn-done");
			$chosen.remove();
		}
		$("#orderStore").chosen();
			
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
	var actlAmntSum = 0;
	var saleAmntSum = 0;
	$table.find("tr").each( function ()
	{
		actlAmntSum += Number($(this).find(".actlAmnt").text());
		saleAmntSum += Number($(this).find(".saleAmnt").text());
	});
	$(".actlAmntSum").text(currency(actlAmntSum));
	$(".saleAmntSum").text(currency(saleAmntSum));
}

function actlAmntFormatter(value, row, index)
{
	var actlprc = row.ACTLPRC == undefined ? 0 : row.ACTLPRC;
	var chkCnt = row.CHKCNT == undefined ? 0 : row.CHKCNT;
	return '<span class="actlAmnt">' + currency(ptMul(actlprc, chkCnt)) + '</span>';
}

function saleAmntFormatter(value, row, index)
{
	var saleprc = row.SALEPRC == undefined ? 0 : row.SALEPRC;
	var chkCnt = row.CHKCNT == undefined ? 0 : row.CHKCNT;
	return '<span class="saleAmnt">' + currency(ptMul(saleprc, chkCnt)) + '</span>';
}

</script>