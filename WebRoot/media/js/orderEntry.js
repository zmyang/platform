var OrderEntry = function () {
	
	var selectedOrderType = "";
	var selectedDeptCode = "";
	var selectedDeptName = "";
	var selectedCategory = "";
	
	var handleWizard = function (){
		if (!jQuery().bootstrapWizard) {
            return;
        }
        
        var form = $('#submit_form');
        
        var displayConfirm = function() {
            $('.display-value', form).each(function(){
                var input = $('[name="'+$(this).attr("data-display")+'"]', form);
                if (input.is(":text") || input.is("textarea")) {
                    $(this).html(input.val());
                } else if (input.is("select")) {
                    $(this).html(input.find('option:selected').text());
                } else if (input.is(":radio") && input.is(":checked")) {
                    $(this).html(input.attr("data-title"));
                } else if ($(this).attr("data-display") == 'card_expiry') {
                    $(this).html($('[name="card_expiry_mm"]', form).val() + '/' + $('[name="card_expiry_yyyy"]', form).val());
                } else if ($(this).attr("data-display") == 'payment') {
                    var payment = [];
                    $('[name="payment[]"]').each(function(){
                        payment.push($(this).attr('data-title'));
                    });
                    $(this).html(payment.join("<br>"));
                }
            });
        }
        
        // default form wizard
        $('#form_wizard_1').bootstrapWizard(
        {
            'nextSelector': '.button-next',
            'previousSelector': '.button-previous',
            onTabClick: function (tab, navigation, index) 
            {
                return false;
            },
            onNext: function (tab, navigation, index) 
            {
                if (form.valid() == false) {
                    return false;
                }

                var total = navigation.find('li').length;
                var current = index + 1;
                // set wizard title
                $('.step-title', $('#form_wizard_1')).text('第 ' + (index + 1) + '步（共' + total + '步） ');
                // set done steps
                jQuery('li', $('#form_wizard_1')).removeClass("done");
                var li_list = navigation.find('li');
                for (var i = 0; i < index; i++) {
                    jQuery(li_list[i]).addClass("done");
                }
                
                if (current == 3)
                {
                	handleLoadDepartmentOrderStatus();
                	
                	handleLoadMaterial();
                	handleLoadMaterialCategory();
                	
                	handleLoadCategory();
                	handleTabShow($("#orderCategory"), $("#orderMaterial"));
                }
                if (current == 4)
                {
                	handleOrderPerview();
                }
                
                if (current == 1) 
                {
                    $('#form_wizard_1').find('.button-previous').hide();
                } 
                else 
                {
                    $('#form_wizard_1').find('.button-previous').show();
                }

                if (current >= total) 
                {
                    $('#form_wizard_1').find('.button-next').hide();
                    $('#form_wizard_1').find('.button-submit').show();
                    displayConfirm();
                } 
                else 
                {
                    $('#form_wizard_1').find('.button-next').show();
                    $('#form_wizard_1').find('.button-submit').hide();
                }
                App.scrollTo($('.page-title'));
            },
            onPrevious: function (tab, navigation, index) 
            {
                var total = navigation.find('li').length;
                var current = index + 1;
                // set wizard title
                $('.step-title', $('#form_wizard_1')).text('第 ' + (index + 1) + '步（共' + total + '步） ');
                // set done steps
                jQuery('li', $('#form_wizard_1')).removeClass("done");
                var li_list = navigation.find('li');
                for (var i = 0; i < index; i++) {
                    jQuery(li_list[i]).addClass("done");
                }

                if (current == 1) 
                {
                    $('#form_wizard_1').find('.button-previous').hide();
                } 
                else 
                {
                    $('#form_wizard_1').find('.button-previous').show();
                }

                if (current >= total) 
                {
                    $('#form_wizard_1').find('.button-next').hide();
                    $('#form_wizard_1').find('.button-submit').show();
                } 
                else 
                {
                    $('#form_wizard_1').find('.button-next').show();
                    $('#form_wizard_1').find('.button-submit').hide();
                }

                App.scrollTo($('.page-title'));
            },
            onTabShow: function (tab, navigation, index) 
            {
                var total = navigation.find('li').length;
                var current = index + 1;
                var $percent = (current / total) * 100;
                $('#form_wizard_1').find('.bar').css({
                    width: $percent + '%'
                });
            }
        });

        $('#form_wizard_1').find('.button-previous').hide();
        $('#form_wizard_1 .button-submit').click(function () {
            alert('处理完成! ');
        }).hide();
	}
	
	var handlePulsate = function () 
	{
        if (!jQuery().pulsate) {
            return;
        }

        if (App.isIE8() == true) {
            return; // pulsate plugin does not support IE8 and below
        }
        
        if (jQuery().pulsate) 
        {
        	//default selected ordertype
        	jQuery('.ordertype').each(function()
	    	{
        		$(this).pulsate({color: "#bf1c56"});
				return false;
	    	});
            
            jQuery('.ordertype').click(function () 
            {
            	jQuery('.ordertype').each(function()
            	{
            		$(this).pulsate("destroy");
            	});
            	
                $(this).pulsate({
                    color: "#bf1c56",
                });
            });
        }
    }
	
	var handleSelectOrderType = function ()
	{
		//default selected ordertype
		jQuery('.ordertype').each(function()
    	{
			selectedOrderType = $(this).find(".number").attr("data");
			$(".selectedOrdertype").text("("+$(this).find(".number").text()+")");
			return false;
    	});
		
		jQuery('.ordertype').click(function () 
        {
			selectedOrderType = $(this).find(".number").attr("data");
			$(".selectedOrdertype").text("("+$(this).find(".number").text()+")");
        });
	}
	
	var handleDepartmentOrder = function()
	{
		$(".reloadDepartment").click(function () 
		{
			handleLoadDepartmentOrderStatus();
		});
	}
	
	var handleLoadDepartmentOrderStatus = function()
	{
		var url = "order/departmentOrderStatus.do?formType=" + selectedOrderType;
		$.get(url, function (data, status) 
		{
			var $table = $("#tbDeptOrderStatus").bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
    	});
	}
	
	//显示部门订货明细
	var handleLoadDepartmentOrderItems = function(deptCode, deptName)
	{
		selectedDeptCode = deptCode;
		selectedDeptName = deptName;
		
        if (selectedOrderType == "1" || selectedOrderType == "2")
        {
        	hadleShowDepartmentOrderList();
        }
        else
        {
        	hadleShowDepartmentOrderView();
        }
	}
	
	//手工单个增加项目的模式
	var hadleShowDepartmentOrderView = function()
	{
		App.block();
		
		//title
        $("#deptOrderView>.modal-header>.page-title").text(selectedDeptName);
        
        //get data
        var url = "order/deptOrderDetails.do?formType=" + selectedOrderType + "&deptCode=" + selectedDeptCode;
        
		//get data
		$.get(url, function (data, status) 
		{
			var $table = $("#tbView1").bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
			
			$("#orderMaterials").val("");
			
			App.unblock();
			
			handleEditMaterial();
    	});
		
      	//popup
		$("#deptOrderView").modal({keyboard: true});
	}
	
	//显示全部项目的模式
	var hadleShowDepartmentOrderList = function()
	{
		//title
        $("#deptOrderList>.modal-header>.page-title").text(selectedDeptName);
        
        //popup
		$("#deptOrderList").modal({keyboard: true});
	}
	
	//添加物料
	var handleEditDepartmentOrder = function()
	{
		$(".addOrderItem").bind("click", function ()
		{
			var selMaterial = $("#orderMaterials").val();
			if (selMaterial == "") return;
			
			handleEditOrderItem(selMaterial);
		});
	}
	
	//选择不同的物料
	var handleEditOrderItem = function (selMaterial)
	{
		if ($("#tbView1").find("tr").length == 2)
		{
			$("#tbView1").find(".no-records-found").remove();
		}
		var material = materialMap.get(selMaterial);
		var exists = false;
		$("#tbView1 .mtrlCd").each(function()
		{
			if ($(this).text() == material.MTRLCD)
			{
				exists = true;
				return false;
			}
		});
		if (exists == true)
		{
			App.alert("已存在该项！");
			return;
		}
		var tr = ['<tr>', 
		          	'<td><span class="mtrlCd">' + material.MTRLCD + '</span></td>',
					'<td>' + material.MTRLNM + '</td>',
					'<td>' + material.SPC + '</td>',
					'<td><input class="ordCnt m-wrap" style="width:50px;"></td>',
					'<td>' + material.ORDUNT + '</td>',
					'<td><span class="chkScl">' + material.CHKSCL + '</span></td>',
					'<td><span class="chkCnt"></span></td>',
					'<td>' + material.CHKUNT + '</td>',
					'<td><input class="cmnts m-wrap small"></td>',
					'<td><a class="deleteOrderItem" href="javascript:void(0)">删除</a></td>',
		          '</tr>'].join("");
		//alert(tr);
		$("#tbView1").append(tr);
		
		handleEditMaterial();
	}
	
	//订货量计算
	var handleEditMaterial = function()
	{
		$(".ordCnt").inputmask({alias: "decimal"});
		$(".ordCnt").bind("input propertychange change", function(e)
		{
			var chkScl = $(this).closest("tr").find(".chkScl").text();
			var ordCnt = $(this).val();
			var tmp = Number(chkScl) * Number(ordCnt);
			//alert("chkScl = " + chkScl + "    ordCnt = " + ordCnt);
			$(this).closest("tr").find(".chkCnt").text(tmp);
		});
		
		$(".deleteOrderItem").bind("click", function(e) 
		{
			e.preventDefault();
			$(this).closest("tr").remove();
		});
	}
	
	var materialMap = null;
	var categoryMap = null;
	//加载订货的物料列表
	var handleLoadMaterial = function()
	{
		App.block();
		//get data
        var url = "order/orderMaterials.do?formType=" + selectedOrderType;
		//get data
		$.get(url, function (data, status) 
		{
			materialMap = new HashMap();
			var trHtml = '<option value=""></option>';
			$.each(data, function(i, material) 
			{
				trHtml += '<option value="' + material.MTRLCD + '">' + material.MTRLCD + " " + material.MTRLNM + " " + material.MNMNCCD + '</option>';
				materialMap.put(material.MTRLCD, material);
			});
			$("#orderMaterials").html(trHtml);
			
			var $chosen = $("#orderMaterials").next();
			if ($chosen.hasClass("chzn-container"))
			{
				$("#orderMaterials").removeClass("chzn-done");
				$chosen.remove();
			}
			
			$("#orderMaterials").chosen();
			
			App.unblock();
    	});
	}
	
	//选中的物料发生变化
	var handleMaterialChose = function ()
	{
		$("#orderMaterials").change(function () 
		{
			handleEditOrderItem($(this).val());
        });
	}
	
	//加载订货类别
	var handleLoadMaterialCategory = function()
	{
		App.block();

		//get data
		$.get("order/getCategories.do", function (data, status) 
		{
			categoryMap = new HashMap();
			var trHtml = '<option value=""></option>';
			$.each(data, function(i, category) 
			{
				trHtml += '<option value="' + category.ctgrcd + '">' + category.ctgrcd + " " + category.ctgrnm + " " + category.mnmnccd + '</option>';
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
			
			App.unblock();
    	});
	}
	
	//加载物料类别选项卡
	var handleLoadCategory = function ()
	{
		App.block();
		$.get("order/getCategories.do", function (data, status) 
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
									mtrlHtml += '<th data-field="MTRLCD" data-formatter="MtrlcdFormatter">编码</th>';								
									mtrlHtml += '<th data-field="MTRLNM">名称</th>';									
									mtrlHtml += '<th data-field="SPC">规格</th>';									
									mtrlHtml += '<th data-field="ORDCNT" data-formatter="OrdCntFormatter">订货数量</th>';									
									mtrlHtml += '<th data-field="ORDUNT">订货单位</th>';								
									mtrlHtml += '<th data-field="CHKSCL" data-formatter="ChkSclFormatter">换算率</th>';									
									mtrlHtml += '<th data-field="CHKCNT" data-formatter="ChkCntFormatter">盘点数量</th>';								
									mtrlHtml += '<th data-field="CHKUNT">盘点单位</th>';						
									mtrlHtml += '<th data-field="CMNTS" data-formatter="CmntsFormatter">备注</th>';							
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
		var $table = $('#' + ctgrcd);
		var url = "order/loadMaterial.do?formType=" + selectedOrderType + "&deptCode=" + selectedDeptCode + "&category=" + ctgrcd;
		//var url = "order/deptOrderDetails.do?formType=" + selectedOrderType + "&deptCode=" + selectedDeptCode + "&category=" + ctgrcd;
        
		//get data
		$.get(url, function (data, status) 
		{
			$table.bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
			
			App.unblock();
		
			handleEditMaterial();
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

	//选中的类别发生变化
	var handleCategoryChose = function ()
	{
		$("#orderCategories").change(function () 
		{
			handleEditCategoryItem($(this).val());
        });
	}
	
	//物料类别选择发生变化
	var handleEditCategoryItem = function(categoryCode)
	{
        //get data
        var url = "order/deptOrderDetails.do?formType=" + selectedOrderType + "&deptCode=" + selectedDeptCode + "&category=" + categoryCode;
        
		//get data
		$.get(url, function (data, status) 
		{
			var $table = $("#tbView2").bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
			
			App.unblock();
			
			handleEditMaterial();
    	});
	}
	
	//保存订单内容(单个选择)
	var handleSaveOrderItem = function ()
	{
		$(".saveOrderItem").bind("click", function()
		{
			var dt = "";
			var mtrlCd = "";
			var ordCnt = "";
			var chkCnt = "";
			var cmnts  = "";
			var doSave = true;
			$("#tbView1").find("tr").each(function()
			{
				mtrlCd = $(this).find(".mtrlCd").text();
				ordCnt = $(this).find(".ordCnt").val();
				chkCnt = $(this).find(".chkCnt").text();
				cmnts  = $(this).find(".cmnts").val();
				if (mtrlCd != "")
				{
					if (ordCnt == "")
					{
						doSave = false;
					}
					//alert("mtrlCd=" + mtrlCd + "         ordCnt=" + ordCnt);
					dt += mtrlCd + "_" + ordCnt + "_" + chkCnt + "_" + cmnts + ";";
				}
			});
			if (dt == "")
			{
				dt = 
				{
					deptCode:selectedDeptCode,
					formType:selectedOrderType
				};
				App.block();
				$.post("order/clearDeptOrderItems.do", dt, function(data)
				{
					App.alert(data);
					
					handleLoadDepartmentOrderStatus();
					$("#deptOrderView").modal("hide");
					App.unblock();
				});
			}
			else
			{
				dt = 
				{
					data:dt, 
					deptCode:selectedDeptCode,
					formType:selectedOrderType
				};
				if (doSave)
				{
					App.block();
					$.post("order/saveDeptOrderItems.do", dt, function(data)
					{
						App.alert(data);
						
						handleLoadDepartmentOrderStatus();
						$("#deptOrderView").modal("hide");
						App.unblock();
					});
				}
				else
				{
					App.alert("有未完成的输入项，请检查！");
				}
			}
		})
	}
	
	//保存订单内容(批量选择)
	var handleSaveOrderItems = function ()
	{
		$(".saveOrderItems").bind("click", function()
		{
			var dt = "";
			var mtrlCd = "";
			var ordCnt = "";
			var chkCnt = "";
			var cmnts  = "";
			var doSave = true;
			var $table = $('#' + selectedCategory);
			
			$table.find("tr").each(function()
			{
				mtrlCd = $(this).find(".mtrlCd").text();
				ordCnt = $(this).find(".ordCnt").val();
				chkCnt = $(this).find(".chkCnt").text();
				cmnts  = $(this).find(".cmnts").val();
				if (mtrlCd != "")
				{
					if (ordCnt == "")
					{
						doSave = false;
					}
					//alert("mtrlCd=" + mtrlCd + "         ordCnt=" + ordCnt);
					dt += mtrlCd + "_" + ordCnt + "_" + chkCnt + "_" + cmnts + ";";
				}
			});
			if (doSave)
			{
				App.block();
				
				dt = 
				{
					data:dt, 
					deptCode:selectedDeptCode,
					formType:selectedOrderType
				};
				
				$.post("order/margeDeptOrderItems.do", dt, function(data)
				{
					App.alert(data);
					
					handleLoadDepartmentOrderStatus();
					
					App.unblock();
				});
			}
			else
			{
				App.alert("有未完成的输入项，请检查！");
			}
		})
	}
	
	//预览订单内容
	var handleOrderPerview = function()
	{
		var url = "order/orderPerview.do?formType=" + selectedOrderType;
		$.get(url, function (data, status) 
		{
			var $table = $("#tbOrderView").bootstrapTable({data:data});
			$table.bootstrapTable("load", data);
			$table.bootstrapTable("hideLoading");
    	});
	}
	
	//创建订单
	var handleCreateOrder = function()
	{
		$(".createOrder").bind("click", function()
		{
			var lth = $("#tbOrderView").find("tr:last").children('td').eq(1);
			if (lth.html() == undefined)
			{
				App.alert("没有订货内容，请检查！");
				return;
			}
			if (confirm("确定将现有部门订货数据生成订单 ?确定后将无法修改。") == false) 
            {
                return;
            }
			
			var url = "order/createOrder.do?formType=" + selectedOrderType;
			$.get(url, function (data, status) 
			{
				App.alert(data);
	    	});
		});
	}
	
    return {
        //main function to initiate the module
        init: function () 
        {
        	handlePulsate();
        	
        	handleWizard();
            
        	handleSelectOrderType();
        	
        	handleDepartmentOrder();
        	
        	handleEditDepartmentOrder();
        	
        	handleSaveOrderItem();
        	
        	handleSaveOrderItems();
        	
        	handleCreateOrder();
        	
        	handleMaterialChose();
        	
        	handleCategoryChose();
        },
        
        handleLoadDepartmentOrderItems:function(deptCode, deptName)
        {
        	handleLoadDepartmentOrderItems(deptCode, deptName);
        }
    };

}();