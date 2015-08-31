var SavingDetail = function()
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
	
	var handleDoSearch = function ()
	{
		App.blockUI($(".page-header-fixed"));
		var rangeTime = $("#rangeTime").text();
		var times = new Array();
		times = rangeTime.split(" ");
		
		var fmdt = times[0];
		var todt = times[2];
		var url = "sales/getSavingDetail.do?fmdt=" + fmdt +"&todt=" + todt;

		//get data
		$.get(url, function (data, status) 
		{
			var $table = $("#tbList").bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
			
			handleSum();
			App.unblockUI($(".page-header-fixed"));
		});
		
	}
	
	var handleSearch = function ()
	{
		$(".doSearch").bind("click", function ()
		{
			handleDoSearch();
		});
	}
	
	var handleSum = function ()
	{
		var totalAmnt = 0;
		$(".amnt").each(function ()
		{
			if(!isNaN($(this).text()))
			{
				totalAmnt += parseFloat($(this).text());
			}
		});
		totalAmnt = currency(totalAmnt);
		
		var str = "";
		str += "<tr style='color: red'>";
		str += "<td colspan='3' style='text-align: right;'>合计</td>";
		str += "<td>" + totalAmnt + "</td>";
		str += "<td colspan='3'></td>";
		str += "</tr>";
		
		$("#tbList").append(str);
	}
	
	return {
		init : function ()
		{
			handleInit();
			handleDoSearch();
			handleSearch();
		}
	};
}();