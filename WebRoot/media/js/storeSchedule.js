var StoreSchedule = function ()
{
	//班表table
	var $table = App.initTable($("#tbSchdlList"));
	//物料类别map
	var categoryMap;
	
	var handleInit = function ()
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
		$("#bgndt").val(Date.today().toString('yyyy-MM-dd'));
		
		$('#datetimepicker2').datetimepicker({
	        language: 'zh-CN',
	        pickTime: false,
	        todayBtn: true,
	        autoclose: true,
	        minView: '2',
	        forceParse: false,
	        format:"yyyy-mm-dd"
	    });
		$("#enddt").val(Date.today().toString('yyyy-MM-dd'));
		
		$('#datetimepicker3').datetimepicker({
	        language: 'zh-CN',
	        pickTime: false,
	        todayBtn: true,
	        autoclose: true,
	        minView: '2',
	        forceParse: false,
	        format:"yyyy-mm-dd"
	    });
		$("#orddt").val(Date.today().toString('yyyy-MM-dd'));
		
		$('#datetimepicker4').datetimepicker({
	        language: 'zh-CN',
	        pickTime: false,
	        todayBtn: true,
	        autoclose: true,
	        minView: '2',
	        forceParse: false,
	        format:"yyyy-mm-dd"
	    });
		$("#rcvdt").val(Date.today().toString('yyyy-MM-dd'));
		
		$("#addForm").validate(
		{
			rules :
			{
				schdlnm : "required",
				bgndt : "required",
				enddt : "required"
			}
		});
		
		$("#doCopy").hide();
	}
	
	var handleDoSearch = function ()
	{
		$(".doSearch").bind("click", function ()
		{
			handleSearch();
		});
	}
	
	var handleSearch = function ()
	{
		var url = "orderShift/getScheduleList.do?stcd=" + $("#stcd").val();
		
		$.get(url, function (data, status)
		{
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
		});
	}
	
	var handleAdd = function ()
	{
		$(".doAdd").bind("click", function ()
		{
			$("#formEdit").modal({keyboard: true});
		});
	}
	
	var handleSave = function ()
	{
		$(".doSave").bind("click", function ()
		{
			App.block();
			if($("#addForm").valid() == false)
			{
				App.unblock();
				return;
			}
			var params = $("#addForm").serialize();
			var url = "orderShift/saveSchdl.do";
			
			$.post(url, params, function (data)
			{
				App.alert(data);
				handleSearch();
				App.unblock();
				
				$("#formEdit").modal("hide");
			});
		});
	}
	
	var handleLoadCategories = function ()
	{
		//get data
		$.get("orderShift/getCategories.do", function (data, status) 
		{
			categoryMap = new HashMap();
			var trHtml = '';
			$.each(data, function(i, category) 
			{
				trHtml += '<option value="' + category.ctgrcd + '">' + category.ctgrcd + "　" + category.ctgrnm + '</option>';
				categoryMap.put(category.ctgrcd, category);
			});
			
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
	
	var handleCalendar = function (schdlid, bgndt, enddt)
	{
		if (!jQuery().fullCalendar) 
		{
	        return;
	    }
		
		var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();
        
        $("#schdlid").val(schdlid);
        
		var h = {};

		if ($('#calendar').width() <= 400) 
		{
			$('#calendar').addClass("mobile");
			h = {
				left : 'title',
				center : '',
				right : 'today'
			};
		}
		else 
		{
			$('#calendar').removeClass("mobile");
			if (App.isRTL()) 
			{
				h = {
					right : 'title',
					center : '',
					left : 'today'
				};
			} 
			else 
			{
				h = {
					left : 'title',
					center : '',
					right : 'today'
				};
			}
		}

		$('#calendar').fullCalendar('destroy'); // destroy the calendar
		$('#calendar').fullCalendar(
		{ 
			//re-initialize the calendar
			disableDragging : false,
			header : h,
			editable : false,
			dayClick : function(date, allDay, jsEvent, view) 
			{
				$("#dataid").val("0");
				$("#ctgrcd").val($("#orderCategories").val());
				$("#ctgrnm").val($("#orderCategories").find("option:selected").text());
				$("#orddt").val($.fullCalendar.formatDate(date, 'yyyy-MM-dd'));
				$("#rcvdt").val($.fullCalendar.formatDate(date, 'yyyy-MM-dd'));
				
				$("#schdlData").modal({keyboard: true});
			},
			events : function(visStart, visEnd, callback) 
			{
				visStart = $.fullCalendar.formatDate(visStart, 'yyyy-MM-dd');
				visEnd = $.fullCalendar.formatDate(visEnd, 'yyyy-MM-dd');
				$("#visStart").val(visStart);
		        $("#visEnd").val(visEnd);
				
				var url = "orderShift/getSchdlData.do?bgndt=" + visStart + "&enddt=" + visEnd +"&schdlid=" + schdlid + "&ctgrcd=" + $("#orderCategories").val();
				$("#calendar").fullCalendar('removeEvents'); //清空日历数据    
				
				$.get(url, function (data, status) 
				{ 
					var obj = data;
					for(var i = 0; i < obj.length; i++)
					{
					    var objs;
					    
					    if(obj[i].orddt >= visStart && obj[i].orddt <= visEnd)
					    {
					    	objs = new Object();
					    	objs.id = obj[i].dataid;
					    	objs.title = (i + 1) + "." + obj[i].ordtm + " 订" + obj[i].ctgrnm;
					    	objs.start = new Date(obj[i].orddt);
					    	objs.backgroundColor = App.getLayoutColorCode('red');
					    	$("#calendar").fullCalendar('renderEvent', objs, true);//往日历中添加数据
					    }
					    
					    if(obj[i].rcvdt >= visStart && obj[i].rcvdt <= visEnd)
					    {
					    	objs = new Object();
					    	objs.id = obj[i].dataid;
					    	objs.title = (i + 1) + "." + obj[i].rcvtm + " " + obj[i].ctgrnm + "收";
					    	objs.start = new Date(obj[i].rcvdt);
					    	objs.backgroundColor = App.getLayoutColorCode('green');
					    	$("#calendar").fullCalendar('renderEvent', objs, true);//往日历中添加数据
					    }
					}
				});
			},
			eventClick: function (calEvent, jsEvent, view)
			{
				handleEditData(calEvent.id);
			}
		});
		
	}
	
	var handleEditData = function (dataid)
	{
		$.get("orderShift/getDataById.do?dataid=" + dataid, function(data, status)
		{
			$("#dataid").val(data.dataid);
			$("#ctgrcd").val(data.ctgrcd);
			$("#ctgrnm").val(data.ctgrcd + " " + data.ctgrnm);
			$("#orddt").val(data.orddt);
			$("#ordtm").val(data.ordtm);
			$("#rcvdt").val(data.rcvdt);
			$("#rcvtm").val(data.rcvtm);
			
			$("#tileBiao").text("编辑订货信息");
			$("#schdlData").modal({keyboard: true});
		});
	}
	
	var handleSaveData = function ()
	{
		$(".doSaveData").bind("click", function ()
		{
			App.block();
			
			$("#ordwkd").val(new Date($("#orddt").val()).getDay());
			$("#rcvwkd").val(new Date($("#rcvdt").val()).getDay());
			
			var params = $("#addSchdlData").serialize();
			
			var url = "orderShift/saveSchdlData.do";
			//alert(params);
			
			$.post(url, params, function (data)
			{
				App.alert(data);
				handleSearch();
				App.unblock();
				
				$("#schdlData").modal("hide");
				handleCalendar($("#schdlid").val(), $("#visStart").val(), $("#visEnd").val());
			});
		});
	}
	
	var handleStroeScheduleClock = function ()
	{
		if (!jQuery().clockface) {
            return;
        }

        $('#ordtm').clockface({
            format: 'HH:mm',
            trigger: 'manual'
        });

        $('#ordtm_toggle').click(function (e) {
            e.stopPropagation();
            $('#ordtm').clockface('toggle');
        });

        $('#rcvtm').clockface({
            format: 'HH:mm',
            trigger: 'manual'
        });

        $('#rcvtm_toggle').click(function (e) {
            e.stopPropagation();
            $('#rcvtm').clockface('toggle');
        });
	}
	
	var handleCompareTime = function ()
	{
		$("#bgndt").live("input propertychange change", function()
		{
			if( $("#bgndt").val() > $("#enddt").val() )
			{
				App.alert("开始日期应小于结束日期");
				$("#bgndt").val($("#enddt").val());
			}
		});
		
		$("#enddt").live("input propertychange change", function()
		{
			if( $("#bgndt").val() > $("#enddt").val() )
			{
				App.alert("结束日期应大于开始日期");
				$("#enddt").val($("#bgndt").val());
			}
		});
		
		$("#orddt").live("input propertychange change", function()
		{
			if( $("#orddt").val() > $("#rcvdt").val() )
			{
				App.alert("订货日期应小于收货日期");
				$("#orddt").val($("#rcvdt").val());
			}
		});
		
		$("#rcvdt").live("input propertychange change", function()
		{
			if( $("#orddt").val() > $("#rcvdt").val() )
			{
				App.alert("收货日期应大于订货日期");
				$("#rcvdt").val($("#orddt").val());
			}
		});
	}
	
	var handleCategoryChange = function ()
	{
		$("#orderCategories").change( function () 
		{
			handleCalendar($("#schdlid").val(), $("#visStart").val(), $("#visEnd").val());
		});
	}
	
	var handleCopyClick = function ()
	{
		$(".doCopy").bind("click", function ()
		{
			var ctgrcd = $("#orderCategories").val();
			var opHtml = "";
			var j = 1;
			$.get("orderShift/getCategories.do", function (data, status) 
			{
				$.each(data, function(i, category) 
				{
					if(category.ctgrcd != ctgrcd)
					{
						/*if( j % 6 == 1 )
						{
							opHtml += "<div>";
						}*/
						opHtml += "<label class='checkbox inline' style='margin: 10px 0 10px 40px;'><input type='checkbox' value='" + category.ctgrcd + "' />" + category.ctgrcd + "　" + category.ctgrnm + "</label>";
						/*if( j % 6 == 0 )
						{
							opHtml += "</div>";
						}
						j++;*/
					}
				});
				$("#selection").html(opHtml);
			});
			
			$("#selectInfo").html("将复制物料类别 <font color='red'>" + $("#orderCategories").find("option:selected").text() + "</font> 的订货班次");
			$("#copyModal").modal({keyboard: true});
		});
	}
	
	var handleDoCopy = function ()
	{
		$(".doSaveCopy").bind("click", function ()
		{
			App.block();
			
			var checkDiv = $("#selection");
			var ctgrCds = "";
			var visStart = $("#visStart").val();
			var visEnd = $("#visEnd").val();
			var schdlid = $("#schdlid").val();
			
			for ( var i = 0; i < checkDiv.length; i++)
			{
				var checkboxlist = $(checkDiv[i]).find("input:checkbox:checked");
				for ( var j = 0; j < checkboxlist.length; j++)
				{
					ctgrCds += $(checkboxlist[j]).attr("value") + ",";
				}
			}
			//alert(ctgrCds);
			var url = "orderShift/doCopyData.do?ctgrcd=" + $("#orderCategories").val() + "&schdlid=" + schdlid + "&visStart=" + visStart + "&visEnd=" + visEnd;
			$.post(url, {ctgrCds:ctgrCds}, function (data)
			{
				App.alert(data);
				App.unblock();
				$("#copyModal").modal("hide");
				
				handleCalendar(schdlid, visStart, visEnd);
			});
		});
	}
	
	return {
		init: function ()
		{
			handleInit();
			handleDoSearch();
			handleSearch();
			handleAdd();
			handleSave();
			handleSaveData();
			handleLoadCategories();
			handleStroeScheduleClock();
			handleCompareTime();
			handleCategoryChange();
			handleCopyClick();
			handleDoCopy();
		},
		
		handleCalendar: function(schdlid, bgndt, enddt)
		{
			handleCalendar(schdlid, bgndt, enddt);
		}
	};
}();