var Index = function () {
	var itemInfo = "";
	
	var getArrowStr = function (menu) 
	{
		if (menu.menus != null && menu.menus.length > 0)
		{
			return '<span class="arrow "></span>';
		}
		else
		{
			return '';
		}
    }
	
	var createMenu = function()
	{
		$.get("index/showUserMenu.do", function (data, status) 
		{
    		var menus = data.menus;
    		$(menus).each(function()
    		{
    			itemInfo = "";
    			//第一层
    			itemInfo += 
				'<li class="m ">' + 
				'	<a href="javascript:;" url="' + this.url + '"> <i class="' + this.icon + '"></i> <span class="title">' + this.name + '</span> ' + getArrowStr(this) + ' </a>';
				
    			if (this.menus.length > 0)
    			{
    				//第二层
    				itemInfo +=
    				'		<ul class="sub-menu" style="display: none;">';
    				
    				$(this.menus).each(function()
    				{
    					itemInfo +=
						'			<li>' +
						'				<a href="javascript:;" url="' + this.url + '">' + this.name + getArrowStr(this) + '</a>';
    					if (this.menus.length > 0)
            			{
            				//第三层
            				itemInfo +=
            				'				<ul class="sub-menu" style="display: none;">';
            				
            				$(this.menus).each(function()
            				{
            					itemInfo +=
    							'					<li>' +
    							'						<a href="javascript:;" url="' + this.url + '">' + this.name + getArrowStr(this) + '</a>';
    							itemInfo +=
            					'					</li>';
            				
            				});
            				itemInfo += 
            				'				</ul>';
            			}
    					
						itemInfo +=
    					'			</li>';
    				
    				});
    				itemInfo += 
    				'		</ul>';
    			}
    			
				itemInfo += 
				'</li>';
    			
    			$(".page-sidebar-menu").append(itemInfo);
    		});
    	});
	}
	
	var bindEndOfDayEvent = function()
	{
		//end of day
        $(".endOfDay").bind("click", function()
		{
			if (confirm("确定要结束营业吗 ?") == false)
			{
				return;
			}

			$.get("index/endOfDay.do", function(data, status)
			{
				if (data.STS == 0)
				{
					App.alert(data.MSG);
				} 
				else
				{
					App.alert("营业日期跳转完成，请重新登录！");
					window.location.href = "index/doLogout.do";
				}
			});
		});
	}
	
	var bindClickMenuEvent = function()
	{
		//menu item click, to load target page
        $(".m > a").live("click", function()
        {
        	if ($(this).parent().hasClass("start"))
        	{
        		var parent = $(this).parent().parent();
                parent.children('li.open').children('a').children('.arrow').removeClass('open');
                parent.children('li.open').children('.sub-menu').slideUp(1);
                parent.children('li.open').removeClass('open');
        		$(".page-sidebar-menu .active").removeClass("active");
        		
        		$(this).parent().addClass("active");
			}
        	App.loadPage($(this).attr("url"));
        });
        
        //set menu color
        $(".sub-menu > li > a").live("click", function()
        {
        	$(".page-sidebar-menu .active").removeClass("active");
        	$(".page-sidebar-menu .open").addClass("active");
        	$(this).parent().addClass("active");
        	
        	App.loadPage($(this).attr("url"));
        });
	}
	
	var showScreenInfo = function()
	{
		var s = ""; 
		s += "\n 网页可见区域宽：" + document.body.clientWidth; 
		s += "\n 网页可见区域高：" + document.body.clientHeight; 
		s += "\n 网页可见区域宽：" + document.body.offsetWidth+" (包括边线和滚动条的宽)"; 
		s += "\n 网页可见区域高：" + document.body.offsetHeight+" (包括边线的宽)"; 
		s += "\n 网页正文全文宽：" + document.body.scrollWidth; 
		s += "\n 网页正文全文高：" + document.body.scrollHeight; 
		s += "\n 网页被卷去的高(ff)：" + document.body.scrollTop; 
		s += "\n 网页被卷去的高(ie)：" + document.documentElement.scrollTop; 
		s += "\n 网页被卷去的左：" + document.body.scrollLeft; 
		s += "\n 网页正文部分上：" + window.screenTop; 
		s += "\n 网页正文部分左：" + window.screenLeft; 
		s += "\n 屏幕分辨率的高：" + window.screen.height; 
		s += "\n 屏幕分辨率的宽：" + window.screen.width; 
		s += "\n 屏幕可用工作区高度：" + window.screen.availHeight; 
		s += "\n 屏幕可用工作区宽度：" + window.screen.availWidth;

		s += "\n 你的屏幕设置是 " + window.screen.colorDepth +" 位彩色"; 
		s += "\n 你的屏幕设置 " + window.screen.deviceXDPI +" 像素/英寸"; 
		alert (s);
	}
	
    return {
        //main function to initiate the module
        init: function () 
        {
        	createMenu();
        	
        	//showScreenInfo();
        	
        	//bindEndOfDayEvent();
        	
        	bindClickMenuEvent();
        	
        	//load main page
        	App.loadPage("portal/myPortal.do");
        	
        	
        }
    };

}();