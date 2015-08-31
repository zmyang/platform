<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/daterangepicker.css"/>" />

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		系统报表
		<small>盘点报表</small>
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
			<a href="#">盘点报表</a>
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
			<form action="#" class="form-horizontal" id="1158698488">
				<div class="date" id="datetimepicker1">
					<label class="control-label">
						查询日期：
					</label>
					<input data-format="yyyy-MM-dd" type="text" id="timeDate" class="timeExcel" />
					<span class="add-on"> <i data-time-icon="icon-time" data-date-icon="icon-calendar" class="icon-calendar"> </i> </span>
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
				<i class="icon-globe"></i><span class="titleExcel">盘点报表</span>
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
						<th data-formatter="stsFormatter">状态</th>
						<th data-field="CHKCNT" data-formatter="chkCntFormatter">盘点数量</th>
						<th data-formatter="moneyFormatter">金额</th>
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
				<strong>合计：￥</strong>
				<span class="moneySum"></span>
			</button>
		</div>
	</div>
</div>

<script src="<c:url value="/media/fm/daterangepicker.js"/>"	type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-datetimepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-table.js"/>"	type="text/javascript"></script>

<script type="text/javascript">

//拨出单列表
var $table = App.initTable($("#tbList"));
//配送中心
var dccd;

$().ready(function() 
{
	$('#datetimepicker1').datetimepicker({
        language: 'zh-CN',
        pickTime: false,
        todayBtn: true,
        autoclose: true,
        minView: '2',
        forceParse: false,
        format:"yyyy-mm-dd"
    });
    $('#datetimepicker1').datetimepicker('setEndDate', Date.today());
    
	$("#timeDate").val(Date.today().toString('yyyy-MM-dd'));
	
	//点击查询
	$(".doSearch").bind("click", function()
	{
		doSearch();
	});
	
	checkDcCode();
});

//查询
function doSearch()
{
	
	App.blockUI($(".table"));
	var bizdt = $("#timeDate").val();
	var url = "report/geChkList.do?bizdt=" + bizdt + "&dccd=" + dccd;

	//get data
	$.get(url, function (data, status) 
	{
		$table.bootstrapTable("load", data);
		$table.bootstrapTable("hideLoading");
		
		getMoneySum();
	});
	
	App.unblockUI($(".table"));
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

function stsFormatter(value, row, index)
{
	if(row.sts == 0)
	{
		return '<span>未确认</span>';
	}
	else
	{
		return '<span>已确认</span>';
	}
}

function chkCntFormatter(value, row, index)
{
	var chkCnt = row.CHKCNT == undefined ? 0 : row.CHKCNT;
	return '<span>' + currency(chkCnt) + '</span>';
}

function moneyFormatter(value, row, index)
{
	var saleprc = row.SALEPRC == undefined ? 0 : row.SALEPRC;
	var chkCnt = row.CHKCNT == undefined ? 0 : row.CHKCNT;
	return '<span class="money">' + currency(ptMul(saleprc, chkCnt)) + '</span>';
}

</script>