/**
 *
 */
var PurchaseList = function () 
{
	var $table = App.initTable($("#tbList"));
	
	var selectedCategory = "";
	
	var dccd = "";
	
	//页面时间选择等控件加载
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
			maxDate: '2016-12-31'
		},
		function (start, end) 
		{
			$('#date-range span').html(start.toString('yyyy-MM-dd') + ' ~ ' + end.toString('yyyy-MM-dd'));
		});
		//初始化选中的日期
		$("#date-range span").html(Date.today().set({day:1}).toString('yyyy-MM-dd') + ' ~ ' + Date.today().toString('yyyy-MM-dd'));
				
		$('#datetimepicker1').datetimepicker({
			language: 'zh-CN',
			pickTime: false,
			todayBtn: true,
			autoclose: true,
			minView: '2',
			forceParse: false,
			format:"yyyy-mm-dd"
		});
	}
	
	//点击查询
	var handleDoSearch = function ()
	{
		$(".doSearch").bind("click", function()
		{
			handleSearch();
		});
	}
	
	//查询
	var handleSearch = function ()
	{
		App.blockUI($(".page-header-fixed"));
		var rangeTime = $("#rangeTime").text();
		var times = new Array();
		times = rangeTime.split(" ");

		var fmtm = times[0];
		var totm = times[2];

		var url = "purchase/psFormList.do?fmtm=" + fmtm +"&totm=" + totm + "&dccd=" + dccd;

		//get data
		$.get(url, function (data, status) 
		{
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
	
			App.unblockUI($(".page-header-fixed"));
		});
	}
	
	//编辑
	var handleEditPse = function ()
	{
		$(".editPse").bind("click", function ()
		{
			var fmNo = App.getTableSelection($table).fmNo;
			var sts = App.getTableSelection($table).sts;
			var $itemTable = App.initTable($("#tbItemList"));
		
			$("#hideStatus").val(sts);
		
			if(fmNo == undefined)
			{
				App.alert("请选择编辑项！");
				return;
			}
			
			$("#timu").html("单据信息：" + fmNo);
			$("#hidFormNo").val(fmNo);
			var url = "purchase/psFormDataListByFmno.do?fmno=" + fmNo;
			
			//get data
			$.get(url, function (data, status) 
			{ 
				$itemTable.bootstrapTable({data:data});
				$itemTable.bootstrapTable("load", data);
				$itemTable.bootstrapTable("hideLoading");
				$(".editActl").inputmask({alias: "decimal"});
				
				if(sts != 0)
				{
					$(".deleteItems").hide();
				}
				else
				{
					$(".deleteItems").show();
				}
		
				App.unblockUI($(".page-header-fixed"));
			});
			
			$("#infoPurchase").modal(
			{
				keyboard: true
			});
			
			if(sts != 0)
			{
				$(".editConfirm").hide();
			}
			else
			{
				$(".editConfirm").show();
			}
			
		});
	}
	
	//编辑确认
	var handleEditConfirm = function ()
	{
		$(".editConfirm").bind("click", function()
		{
			var fmNo = $("#hidFormNo").val();
			var editActl = "";
			var mtrlCd = "";
			var dt = "";

			/*if (confirm("确定确认采购单信息吗？\n一旦确认不再更改！") == false)
			{
				App.unblock();
				return;
			}*/
			
			$("#tbItemList").find("tr").each(function ()
			{
				editActl = $(this).find(".editActl").val();
				mtrlCd = $(this).find(".mtrlCdForEdit").text();
				if(editActl != undefined)
				{
					dt += mtrlCd + "," + editActl + ";";
				}
			});
			
			if(dt == "")
			{
				App.alert("没有物流信息！");
				return;
			}
			dt += fmNo;
			$.post("purchase/editConfirm.do", {data : dt}, function(data)
			{
				App.alert(data);
				handleSearch();
				
				$("#infoPurchase").modal("hide");
			});
		});
	}
	
	//编辑采购单删除物料
	var handleDeleteMtrl = function(mtrlCD)
	{
		App.block();
    	var data = "";
	    var mtrlCd = mtrlCD;
	    var fmNo = $("#hidFormNo").val();
	    
	    /*if(confirm("确定删除物料采购信息吗？") == false)
	    {
	    	App.unblock();
	    	return;
	    }*/
	    data += mtrlCd + "," + fmNo;
	    
	    $.post("purchase/delPsFormData.do", {data : data}, function(data)
	    {
	    
	    	var mtrlcd = "";
	    	$("#tbItemList").find("tr").each(function ()
			{
				mtrlcd = $(this).find(".mtrlCdForEdit").html();
				if(mtrlcd == mtrlCd)
				{
					$(this).closest("tr").remove();
				}
			});
	    	
	    	App.alert(data);
	    	App.unblock();
	    });
	}
	
	//删除
	var handleDelete = function ()
	{
		$(".doDelete").bind("click", function()
		{
			App.block();
    	
			var sts = App.getTableSelection($table).sts;
			var fmNo = App.getTableSelection($table).fmNo;
			
			if(fmNo == undefined)
			{
				App.alert("请选择删除项！");
				App.unblock();
				return;
			}
			
			if (sts == 0) 
			{
				/*if(confirm("确定删除采购单信息吗？") == false)
			   {
					App.unblock();
					return;
				}*/
				$.post("purchase/delPurchase.do", {fmNo:fmNo}, function(data)
				{
					App.alert(data);
					handleSearch();
				});
			}
			else
			{
				App.alert("订货单已确认不能删除！");
			}
			App.unblock();
		});
	}
	
	//自动采购modal
	var handleAutoPurchase = function ()
	{
		$(".doAutoPurchase").bind("click", function()
		{
			$("#autoPurchase").modal(
			{
				keyboard : true
			});
		});
	}
	
	//自动采购查询
	var handleAutoSearch = function ()
	{
		$(".autoDoSearch").bind("click", function()
		{
			App.blockUI($("#autoPseTbList"));
			
			var url = "purchase/psFormDataList.do?bizDt="+$("#autoTime").val();
			var $autoTable = App.initTable($("#autoPseTbList"));
			
			$.get(url, function (data, status)
			{
				$autoTable.bootstrapTable({data:data});
				//alert(JSON.stringify(data));
				//alert( "test"+ data[0].material.mtrlcd);
				$autoTable.bootstrapTable("load", data);
				$autoTable.bootstrapTable("hideLoading");
				
				App.unblockUI($("#autoPseTbList"));
			});
		});
	}
	
	//自动采购保存
	var handleAutoPseConfirm = function ()
	{
		$(".autoPseConfirm").bind("click", function()
		{
			var dt = "";
			var actlCnt= "";
			var mtrlCd = "";
			var rstCnt = "";

			$("#autoPseTbList").find("tr").each(function()
			{
				actlCnt = $(this).find(".actlCnt").val();
				rstCnt = $(this).find(".rstCnt").val();
				mtrlCd = $(this).find(".mtrlCd").val();
				if (actlCnt == 0)
				{
					return true;//continue
				}
				if (mtrlCd != undefined)
				{
					dt += "A,";
					dt += mtrlCd + ",";
					dt += $(this).find(".splrCd").val() + ",";
					dt += actlCnt+ ",";
					dt += rstCnt + ",";
					dt += $("#autoTime").val() + ";";
				}
			});
			
			if (dt == "")
			{
				return;
			}
			$.post("purchase/savePse.do", {data:dt}, function(data)
			{
				handleSearch();
				App.alert(data);
				
				$("#autoPurchase").modal("hide");
			});
		});
	}
	
	//手动采购modal
	var handleManualPurchase = function ()
	{
		$(".doManualPurchase").bind("click", function()
		{
			App.blockUI($(".page-header-fixed"));
		
			$("#manualPurchase").modal(
			{
				keyboard : true
			});
			
			App.unblockUI($(".page-header-fixed"));
		});
	}	
	
	//手动采购保存
	var handleManualPseConfirm = function ()
	{
		$(".manualPseConfirm").bind("click", function()
		{
			var dt = "";
			var mtrCd = "";
			var rqsCnt = "";
			var doSave = true;
			var $tableMtrl = $('#' + selectedCategory);
			
			$tableMtrl.find("tr").each(function()
			{
				rqsCnt = $(this).find(".rqsCnt").val();
				mtrCd = $(this).find(".mtrCd").text();
				if(rqsCnt == "")
				{
					doSave = false;
				}
				
				if (mtrCd != undefined && mtrCd != "")
				{
					dt += "M,";
					dt += mtrCd + ",";
					dt += $(this).find(".splCd").val() + ",";
					dt += rqsCnt + ",";
					dt += "0,";
					dt += " ;";
				}
			});
			
			if (dt == "")
			{
				return;
			}
			if(doSave)
			{
				$.post("purchase/savePse.do", {data:dt}, function(data)
				{
					App.alert(data);
					handleSearch();
					
					$("#manualPurchase").modal("hide");
				});
			}
			else
			{
				App.alert("有未完成的输入项，请检查！");
			}
		});
	}
	
	//加载物料类别 选项卡页面
	var handleLoadCategory = function ()
	{
		App.block();
		$.get("purchase/getCategories.do", function (data, status) 
		{
			var ctgrHmtl = "";
			var mtrlHtml = "";
			var ctgrcds = new Array();
			var k = 0;
			
			$.each(data, function(i, category)
			{
				ctgrcds[k] = category.ctgrcd;
				k += 1;
				
				if(i == 0)
				{
					ctgrHmtl += '<li class="active"><a href="#' + i + '">' + category.ctgrcd + " " + category.ctgrnm + '</a></li>';
					mtrlHtml += '<div style="display: block" id="' + i + '">';
					selectedCategory = category.ctgrcd;
				}
				else
				{
					ctgrHmtl += '<li><a href="#' + i + '">' + category.ctgrcd + " " + category.ctgrnm + '</a></li>';
					mtrlHtml += '<div style="display: none" id="' + i + '">';
				}
						mtrlHtml += '<table id="' + category.ctgrcd + '" class="table table-striped table-hover table-bordered">';
							mtrlHtml += '<thead>';
								mtrlHtml += '<tr>';
									mtrlHtml += '<th data-formatter="mtrlcdCtgrFormatter">编码</th>';								
									mtrlHtml += '<th data-field="mtrlnm">名称</th>';									
									mtrlHtml += '<th data-field="spc">规格</th>';
									mtrlHtml += '<th data-formatter="splrnmFormatter">供应商名称</th>';									
									mtrlHtml += '<th data-formatter="mailFormatter">联系方式</th>';								
									mtrlHtml += '<th data-formatter="pseNumFormatter">采购量</th>';									
									mtrlHtml += '<th data-field="orduntnm">配送单位</th>';								
								mtrlHtml += '</tr>';						
							mtrlHtml += '</thead>';	
						mtrlHtml += '</table>';
					mtrlHtml += '</div>';
			});
			
			$("#orderCategory").append(ctgrHmtl);
			$("#orderMaterial").append(mtrlHtml);
			
			for(var j = 0; j < ctgrcds.length; j++)
			{
				loadMtrls(ctgrcds[j]);
			}
			
			handleTabShow($("#orderCategory"), $("#orderMaterial"));
			
			App.unblock();
		});
	}
	
	//加载物料
	var loadMtrls = function (ctgrcd)
	{
		var $tableMtrl = $('#' + ctgrcd);
		var url = "purchase/getMaterialsByCtgrcd.do?ctgrcd=" + ctgrcd;
        //alert(url);
		//get data
		$.get(url, function (data, status) 
		{
			$tableMtrl.bootstrapTable({data:data});
			$tableMtrl.bootstrapTable("load", data);
			$tableMtrl.bootstrapTable("hideLoading");
			
			$(".rqsCnt").inputmask({alias: "decimal"});
			
			App.unblock();
		});
	}
	
	//选项卡操作
	function handleTabShow(tab, cont)
	{
	    var aLi = tab.find('li');
	    var aCont = cont.find('div');
	    
	    for(var i = 0; i < aLi.length; i++)
	    {
	        aLi[i].index = i;
	        aLi[i].onclick = function()
	        {
	        	for(var j = 0; j < aLi.length; j++)
	    	    {
	    	        aLi[j].className = '';
	    	        aCont[j].style.display = 'none';
	    	    }
	        	
	            this.className = 'active';
	            aCont[this.index].style.display = 'block';
	            
	            selectedCategory = $('#' + this.index).attr('id');
	        }
	    }
	}  
	
	//配送中心选择检查
	var handleCheckDccd = function ()
	{
		dccd = $("#currentDccd").val();
		if(dccd == "")
		{
			App.block();
			$("#selectDc").modal({keyboard : true});
		}
		else
		{
			handleSearch();
		}	
		
		$("#dcselect").change( function () 
		{
			dccd = $("#dcselect").val();
			if(dccd != "-1")
			{
				$.post("logistics/updateCurrentDc.do", {dcCode:dccd}, function (data) 
				{
					$("#selectDc").modal("hide");
					handleSearch();
				});
			}
		});
	}
	
	return {
		init: function ()
		{
			handleInit();
			
			handleDoSearch();
			
			handleSearch();
			
			handleEditPse();
			
			handleDelete();
			
			handleAutoPurchase();
			
			handleAutoSearch();
			
			handleAutoPseConfirm();
			
			handleManualPurchase();
			
			handleManualPseConfirm();
			
			handleEditConfirm();
			
			handleCheckDccd();
			
			handleLoadCategory();			
		},
		
		handleDeleteMtrl:function(mtrlCD)
        {
        	handleDeleteMtrl(mtrlCD);
        }
	}
	
}();
