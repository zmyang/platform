var ReturnEntry = function () {
	
	var selectedOrderType = "";
	var selectedDeptCode = "";
	
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
                
                if (current == 2)
                {
                	handleLoadMaterial();
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
	
	var handleDoSearch = function()
	{
		$("#doSearch").bind("click", function()
		{
			doSearch();
		});
	}
	
	var doSearch = function()
	{
		App.blockUI($('.page-header-fixed'));
			var dates = $("#date-range span").html().split('~');
			var url = "stock/returnFormList.do?beginDate=" + $.trim(dates[0]) + "&endDate=" + $.trim(dates[1]);

			//get data
			$.get(url, function (data, status) 
			{
				var $table = $("#tbOrderView").bootstrapTable({data:data});
				$table.bootstrapTable("load", data);
				$table.bootstrapTable("hideLoading");
				
				App.unblockUI($(".page-header-fixed"));
	    	});
	}
	
	var handleEditDepartmentOrder = function()
	{
		$(".addOrderItem").bind("click", function ()
		{
			var selMaterial = $("#orderMaterials").val();
			if (selMaterial == "") return;
			
			handleEditOrderItem(selMaterial);
		});
	}
	
	var handleEditOrderItem = function (selMaterial)
	{
		if ($("#tbView").find("tr").length == 2)
		{
			$("#tbView").find(".no-records-found").remove();
		}
		var material = materialMap.get(selMaterial);
		var exists = false;
		$(".mtrlCd").each(function()
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
					'<td><span class="chkScl">' + material.MTRLNM + '</span></td>',
					'<td>' + material.SPC + '</td>',
					'<td><input class="ordCnt m-wrap" style="width:50px;"></td>',
					'<td>' + material.ORDUNT + '</td>',
					'<td><a class="deleteOrderItem" href="javascript:void(0)">删除</a></td>',
		          '</tr>'].join("");
		//alert(tr);
		$("#tbView").append(tr);
		
		handleEditMaterial();
	}
	
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
	var handleLoadMaterial = function()
	{
		//get data
        var url = "order/orderMaterials.do?formType=0";
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
			
			$(".chosen").chosen();
			$('.chosen').change(function () 
			{
				handleEditOrderItem($(this).val());
            });
    	});
	}
	
	var handleSaveOrderItem = function ()
	{
		$(".saveOrderItem").bind("click", function()
		{
			var dt = "";
			var mtrlCd = "";
			var ordCnt = "";
			var chkCnt = "";
			var doSave = true;
			$("#tbView").find("tr").each(function()
			{
				mtrlCd = $(this).find(".mtrlCd").text();
				ordCnt = $(this).find(".ordCnt").val();
				if (mtrlCd != "")
				{
					if (ordCnt == "")
					{
						doSave = false;
					}
					//alert("mtrlCd=" + mtrlCd + "         ordCnt=" + ordCnt);
					dt += mtrlCd + "_" + ordCnt + ";";
				}
			});
			
			dt = 
			{
				data:dt, 
				fmTp:selectedOrderType,//16,17,18
			};
			if (doSave)
			{
				if (confirm("确定要生成退货单吗 ?") == false) 
	            {
	                return;
	            }
				$.post("stock/saveReturnItems.do", dt, function(data)
				{
					App.alert(data);
					
					App.loadPage("stock/returnGoods.do");
					
					App.unblock();;
				});
			}
			else
			{
				App.alert("有未完成的输入项，请检查！");
			}
		})
	}
	
    return {
        //main function to initiate the module
        init: function () 
        {
        	handlePulsate();
        	
        	handleWizard();
            
        	handleSelectOrderType();
        	
        	handleDoSearch();
        	
        	handleEditDepartmentOrder();
        	
        	handleSaveOrderItem();
        }
    };

}();