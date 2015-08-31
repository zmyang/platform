var TimeTurnOver = function()
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
			minDate: '2012-01-01',
		},
		function (start, end) 
		{
			$('#date-range span').html(start.toString('yyyy-MM-dd') + ' ~ ' + end.toString('yyyy-MM-dd'));
		});
		//初始化选中的日期
		$("#date-range span").html(Date.today().set({day:1}).toString('yyyy-MM-dd') + ' ~ ' + Date.today().toString('yyyy-MM-dd'));
		
	}
	
	var handleClock = function ()
	{
		if (!jQuery().clockface) 
		{
            return;
        }

        $('#frmtm').clockface(
        {
            format: 'HH:mm',
            trigger: 'manual'
        });

        $('#frmtm_toggle').click(function (e) 
        {
            e.stopPropagation();
            $('#frmtm').clockface('toggle');
        });

        $('#totm').clockface({
            format: 'HH:mm',
            trigger: 'manual'
        });

        $('#totm_toggle').click(function (e) 
        {
            e.stopPropagation();
            $('#totm').clockface('toggle');
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
		var url = "sales/getTimeTurnOver.do?fmdt=" + fmdt +"&todt=" + todt + "&frmtm=" + $("#frmtm").val() + "&totm=" + $("#totm").val();

		//get data
		$.get(url, function (data, status) 
		{
			var $table = $("#tbList").bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
			
			handleSum();
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

		var totalTc = 0;
		$(".tc").each(function ()
		{
			if(!isNaN($(this).text()))
			{
				totalTc += parseInt($(this).text());
			}
		});
		
		var totalAvgAmnt = 0;
		$(".avgAmnt").each(function ()
		{
			if(!isNaN($(this).text()))
			{
				totalAvgAmnt += parseFloat($(this).text());
			}
		});
		totalAvgAmnt = currency(totalAvgAmnt);
		
		var totalGc = 0;
		$(".gc").each(function ()
		{
			if(!isNaN($(this).text()))
			{
				totalGc += parseInt($(this).text());
			}
		});
		
		var totalGcPrc = 0;
		$(".gcPrc").each(function ()
		{
			if(!isNaN($(this).text()))
			{
				totalGcPrc += parseFloat($(this).text());
			}
		});
		totalGcPrc = currency(totalGcPrc);
		
		var totalAmntPnt = 0;
		$(".amntPnt").each(function ()
		{
			var tmp = $(this).text().replace('％', '');
			if(!isNaN(tmp))
			{
				totalAmntPnt += parseFloat(tmp);
			}
		});
		totalAmntPnt = currency(Math.ceil(totalAmntPnt));
		
		var str = "";
		str += "<tr style='color: red;'>";
		str += "<td>合计</td>";
		str += "<td>" + totalAmnt + "</td>";
		str += "<td>" + totalTc + "</td>";
		str += "<td>" + totalAvgAmnt + "</td>";
		str += "<td>" + totalGc + "</td>";
		str += "<td>" + totalGcPrc + "</td>";
		str += "<td>" + totalAmntPnt + "％</td>";
		str += "</tr>";
		
		$("#tbList").append(str);
	}
	
	return {
		init : function ()
		{
			handleInit();
			handleClock();
			handleDoSearch();
			handleSearch();
		}
	};
}();