var PayTp = function()
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
		
		$("#date-range span").change(function()
		{
			handleDoSearch();
		});
	}
	
	var handlePmntTp = function ()
	{
		$.get("sales/getPmntTp.do", function (data, status)
		{
			var str = "<th data-field='bizDt'>日期</th>";
			var field = "";
			$.each(data, function(i, value)
			{
				if(i == 0)
				{
					field = "actlCash";
				}
				else
				{
					if(i < 10)
					{
						field = "actlPmnt0" + i;
					}
					else
					{
						field = "actlPmnt" + i;
					}
				}
				
				
				str += "<th data-field='" + field + "'>" + data[i].tpnm + "</th>";
			});
			$("#tb-title").html(str);
		});
	}
	
	var handleDoSearch = function ()
	{
		App.blockUI($(".page-header-fixed"));
		var rangeTime = $("#rangeTime").text();
		var times = new Array();
		times = rangeTime.split(" ");
		
		var fmdt = times[0];
		var todt = times[2];
		var url = "sales/getPayTpRpt.do?fmdt=" + fmdt +"&todt=" + todt;

		//get data
		$.get(url, function (data, status) 
		{
			//alert(data.length);
			var $table = $("#tbList").bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
		});
		
		App.unblockUI($(".page-header-fixed"));
	}
	
	var handleSearch = function ()
	{
		$(".doSearch").bind("click", function ()
		{
			handleDoSearch();
		});
	}
	
	return {
		init : function ()
		{
			handleInit();
			handlePmntTp();
			handleDoSearch();
			handleSearch();
		}
	};
}();