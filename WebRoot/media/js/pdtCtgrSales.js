var PdtCtgrSales = function ()
{
	var handleInit = function ()
	{
		$("#date-range").daterangepicker(
		{
			opens: (App.isRTL() ? 'left' : 'right'),
			format: 'yyyy-MM-dd',
		    separator: ' ~ ',
		    startDate: Date.today().set({day:1}),
			endDate: Date.today(),
		},
		function (start, end) 
		{
			$('#date-range span').html(start.toString('yyyy-MM-dd') + ' ~ ' + end.toString('yyyy-MM-dd'));
		});
		//初始化选中的日期
		$("#date-range span").html(Date.today().set({day:1}).toString('yyyy-MM-dd') + ' ~ ' + Date.today().toString('yyyy-MM-dd'));
	}
	
	var handleSearch = function ()
	{
		App.blockUI($(".page-header-fixed"));
		var rangeTime = $("#rangeTime").text();
		var times = new Array();
		times = rangeTime.split(" ");
		
		var fmdt = times[0];
		var todt = times[2];
		var url = "sales/getPdtCtgrSalesRpt.do?fmdt=" + fmdt +"&todt=" + todt;

		//get data
		$.get(url, function (data, status) 
		{
			var $table = $("#tbList").bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
			
			App.unblockUI($(".page-header-fixed"));
		});
	}
	
	var handleDoSearch = function ()
	{
		$(".doSearch").bind("click", function ()
		{
			handleSearch();
		});
	}
	
	return {
		init : function ()
		{
			handleInit();
			handleSearch();
			handleDoSearch();
		}
	};
}();