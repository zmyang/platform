<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<div class="span12">
		<h3 class="page-title">
			服务管理
			<small>管理服务启动、停止</small>
		</h3>
		<ul class="breadcrumb">
			<li>
				<i class="icon-home"></i>
				<a href="index.do">我的工作室</a>
				<span class="icon-angle-right"></span>
			</li>
			<li>
				<a href="#">基础数据</a>
				<span class="icon-angle-right"></span>
			</li>
			<li>
				<a href="#">服务管理</a>
			</li>
		</ul>
	</div>
</div>
<!-- END PAGE HEADER-->

<div class="row-fluid">
	<div class="span12 portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i>服务列表
			</div>
			<div class="actions">
				<!-- 
				<button class="btn red doSearch">
					查询
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				 -->
			</div>
		</div>

		<div class="portlet-body flip-scroll">
			<table id="tbList" class="table table-striped table-bordered table-advance table-hover table-condensed">
				<thead>
					<tr>
						<th data-field="svrKey">
							<i class="icon-barcode"></i> 服务标识
						</th>
						<th data-field="clsName">
							<i class="icon-android"></i> 执行对象
						</th>
						<th data-field="firstDate">
							<i class="icon-calendar"></i> 首次执行日期
						</th>
						<th data-field="firstTime">
							<i class="icon-time"></i> 首次执行时间
						</th>
						<th data-field="delayTime">
							<i class="icon-bolt"></i> 执行间隔(秒)
						</th>
						<th data-field="autoRun" data-formatter="autoRunFormatter">
							<i class="icon-check"></i> 自动执行
						</th>
						<th data-field="memo">
							<i class="icon-book"></i> 描述
						</th>
						<th data-field="status" data-formatter="statusFormatter">
							<i class="icon-heart"></i> 状态
						</th>
						<th data-field="statusName" data-formatter="operateFormatter" data-events="operateEvents">
							<i class="icon-magic"></i> 操作
						</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>

<script src="<c:url value="/media/fm/bootstrap-table.js"/>" type="text/javascript"></script>

<script type="text/javascript">

function autoRunFormatter(value, row, index)
{
	if (row.autoRun == 1)
	{
		return '<i class="icon-ok-sign" title="是"></i>';
	}
	else
	{
		return '<i class="icon-minus-sign" title="否 "></i>';
	}
}

function statusFormatter(value, row, index)
{
	if (row.status == 0)
	{
		return '<span class="btn">未创建</span>';
	}
	else if (row.status == 1)
	{
		return '<span class="btn">未运行</span>';
	}
	else if (row.status == 2)
	{
		return '<span class="btn yellow">待运行</span>';
	}
	else if (row.status == 3)
	{
		return '<span class="btn green">运行中</span>';
	}
	else if (row.status == 4)
	{
		return '<span class="btn red">已停止</span>';
	}
}

//生成操作列
function operateFormatter(value, row, index) 
{
	var rst = '';
	if (row.status == 3)
	{
		rst += '<span class="start btn yellow-stripe">重启</span>';
	}
	else
	{
		rst += '<span class="start btn green-stripe">启动</span>';
	}
	rst += '<span class="stop btn red-stripe">停止</span>';
	return rst;
}

//查看单据详情
window.operateEvents = 
{
    'click .start': function (e, value, row, index) 
    {
    	if (confirm("确定要启动该服务吗？") == false)
	    {
	    	return;
	    }
	    
	    App.blockUI($(".page-header-fixed"));
	    $.get("sysService/startService.do?svrKey="+row.svrKey, function (data, status) 
   		{
   			var $table = $("#tbView").bootstrapTable({data:data});
   			$table.bootstrapTable("load", data);
   			$table.bootstrapTable("hideLoading");
   			
   			App.unblockUI($(".page-header-fixed"));
   			
   			loadServiceList();
       	});
    },
    
	'click .stop': function (e, value, row, index) 
	{
	    if (confirm("确定要停止该服务吗？") == false)
	    {
	    	return;
	    }
	    
	    App.blockUI($(".page-header-fixed"));
	    $.get("sysService/stopService.do?svrKey="+row.svrKey, function (data, status) 
   		{
   			var $table = $("#tbView").bootstrapTable({data:data});
   			$table.bootstrapTable("load", data);
   			$table.bootstrapTable("hideLoading");
   			
   			App.unblockUI($(".page-header-fixed"));
   			
   			loadServiceList();
       	});
	}
};

function loadServiceList()
{
	App.blockUI($(".page-header-fixed"));
	
	//get data
	$.get("sysService/getServiceList.do", function (data, status) 
	{
		var $table = $("#tbList").bootstrapTable({data:data});
		$table.bootstrapTable("load", data);
		$table.bootstrapTable("hideLoading");
		
		App.unblockUI($(".page-header-fixed"));
	});
}

$().ready(function() 
{
	loadServiceList();
});
</script>
