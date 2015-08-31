<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
	<!--<![endif]-->
	<!-- BEGIN HEAD -->
	<head>
		<title>${tit}</title>
		<meta content="width=device-width, initial-scale=1.0" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<!-- BEGIN GLOBAL MANDATORY STYLES -->
		<link href="<c:url value="/media/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/media/css/bootstrap-responsive.min.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/media/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/media/css/style-metro.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/media/css/style.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/media/css/style-responsive.css"/>" rel="stylesheet" type="text/css" />
		<link href="<c:url value="/media/css/theme/default.css"/>" rel="stylesheet" type="text/css" id="style_color" />
		<link href="<c:url value="/media/css/uniform.default.css"/>" rel="stylesheet" type="text/css" />
		<!-- END GLOBAL MANDATORY STYLES -->
		<link rel="shortcut icon" href="<c:url value="/media/image/favicon.ico"/>" />
	</head>
	<!-- END HEAD -->
	<!-- BEGIN BODY -->
	<body class="page-header-fixed">
		<!-- BEGIN HEADER -->
		<div class="header navbar navbar-inverse navbar-fixed-top">
			<!-- BEGIN TOP NAVIGATION BAR -->
			<div class="navbar-inner">
				<div class="container-fluid">
					<!-- BEGIN LOGO -->
					<a class="brand" href="<c:url value="/store/index.do"/>"> <img src="<c:url value="/media/image/logo.png"/>" alt="logo" /> </a>
					<!-- END LOGO -->
					<!-- BEGIN RESPONSIVE MENU TOGGLER -->
					<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse"> <img src="<c:url value="/media/image/menu-toggler.png"/>" alt="" /> </a>
					<!-- END RESPONSIVE MENU TOGGLER -->

					<!-- BEGIN TOP NAVIGATION MENU -->
					<ul class="nav pull-right">
						<!-- BEGIN NOTIFICATION DROPDOWN -->
						<li class="dropdown" id="header_notification_bar">
							<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-warning-sign"></i> <span class="badge">6</span> </a>
							<ul class="dropdown-menu extended notification">
								<li>
									<p>
										您有 14 条新通知
									</p>
								</li>
								<li>
									<a href="javascript:;"> <span class="label label-success"><i class="icon-plus"></i> </span> 新用户注册. <span class="time">刚才</span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="label label-important"><i class="icon-bolt"></i> </span> 2号POS机没有连接成功. <span class="time">15 mins</span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="label label-warning"><i class="icon-bell"></i> </span> 3号POS机没有响应. <span class="time">22 mins</span> </a>
								</li>

								<li>
									<a href="javascript:;"> <span class="label label-info"><i class="icon-bullhorn"></i> </span> 系统错误－发生未知错误. <span class="time">40 mins</span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="label label-important"><i class="icon-bolt"></i> </span> 数据库负载超过 68%. <span class="time">2 hrs</span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="label label-important"><i class="icon-bolt"></i> </span> 3号POS机已连接. <span class="time">5 hrs</span> </a>
								</li>
								<li class="external">
									<a href="javascript:;">查看更多通知 <i class="m-icon-swapright"></i> </a>
								</li>
							</ul>
						</li>
						<!-- END NOTIFICATION DROPDOWN -->

						<!-- BEGIN INBOX DROPDOWN -->
						<li class="dropdown" id="header_inbox_bar">
							<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-envelope"></i> <span class="badge">5</span> </a>
							<ul class="dropdown-menu extended inbox">
								<li>
									<p>
										您有 12 条新消息
									</p>
								</li>
								<li>
									<a href="inbox.html?a=view"> <span class="photo"> <img src="<c:url value="/media/image/avatar2.jpg"/>" alt="" /> </span> <span class="subject"> <span class="from">南京珠江路店</span> <span class="time">刚才</span> </span> <span class="message">Vivamus sed auctor nibh congue nibh. auctor nibh auctor nibh... </span> </a>
								</li>
								<li>
									<a href="inbox.html?a=view"> <span class="photo"> <img src="<c:url value="/media/image/avatar3.jpg"/>" alt="" /> </span> <span class="subject"> <span class="from">南京夫子庙店</span> <span class="time">16 mins</span> </span> <span class="message">Vivamus sed congue nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span> </a>
								</li>
								<li>
									<a href="inbox.html?a=view"> <span class="photo"> <img src="<c:url value="/media/image/avatar1.jpg"/>" alt="" /> </span> <span class="subject"> <span class="from">南京新街口店</span> <span class="time">2 hrs</span> </span> <span class="message">Vivamus sed nibh auctor nibh congue nibh. auctor nibh auctor nibh... </span> </a>
								</li>

								<li class="external">
									<a href="javascript:;">查看全部消息 <i class="m-icon-swapright"></i> </a>
								</li>
							</ul>
						</li>
						<!-- END INBOX DROPDOWN -->

						<!-- BEGIN TODO DROPDOWN -->
						<li class="dropdown" id="header_task_bar">
							<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <i class="icon-tasks"></i> <span class="badge">6</span> </a>
							<ul class="dropdown-menu extended tasks">
								<li>
									<p>
										您有 8 项未完成的任务
									</p>
								</li>
								<li>
									<a href="javascript:;"> <span class="task"> <span class="desc">现金管理</span> <span class="percent">30%</span> </span> <span class="progress progress-success "> <span style="width: 30%;" class="bar"></span> </span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="task"> <span class="desc">订货管理</span> <span class="percent">65%</span> </span> <span class="progress progress-danger progress-striped active"> <span style="width: 65%;" class="bar"></span> </span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="task"> <span class="desc">库存管理</span> <span class="percent">98%</span> </span> <span class="progress progress-success"> <span style="width: 98%;" class="bar"></span> </span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="task"> <span class="desc">新员工数据录入</span> <span class="percent">10%</span> </span> <span class="progress progress-warning progress-striped"> <span style="width: 10%;" class="bar"></span> </span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="task"> <span class="desc">工时统计</span> <span class="percent">58%</span> </span> <span class="progress progress-info"> <span style="width: 58%;" class="bar"></span> </span> </a>
								</li>
								<li>
									<a href="javascript:;"> <span class="task"> <span class="desc">员工排班</span> <span class="percent">85%</span> </span> <span class="progress progress-success"> <span style="width: 85%;" class="bar"></span> </span> </a>
								</li>
								<li class="external">
									<a href="javascript:;">查看所有任务 <i class="m-icon-swapright"></i> </a>
								</li>
							</ul>
						</li>
						<!-- END TODO DROPDOWN -->

						<!-- BEGIN USER LOGIN DROPDOWN -->
						<li class="dropdown user">
							<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown"> <img alt="" src="<c:url value="/media/image/avatar1_small.jpg"/>" /> <span class="username">${C_USR.usrNm}</span> <i class="icon-angle-down"></i> </a>
							<ul class="dropdown-menu">
								<li>
									<a href="javascript:App.loadPage('account/profilelEdit.do');"><i class="icon-user"></i> 账号信息</a>
								</li>
								<li>
									<a href="javascript:;"><i class="icon-calendar"></i> 我的日历</a>
								</li>
								<li>
									<a href="javascript:;"><i class="icon-envelope"></i> 收件箱(3)</a>
								</li>
								<li>
									<a href="javascript:;"><i class="icon-tasks"></i> 我的任务</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="index/doLogout.do"><i class="icon-key"></i> 退出</a>
								</li>
							</ul>
						</li>
						<!-- END USER LOGIN DROPDOWN -->
					</ul>
					<!-- END TOP NAVIGATION MENU -->
				</div>
			</div>
			<!-- END TOP NAVIGATION BAR -->
		</div>

		<!-- END HEADER -->

		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN SIDEBAR -->
			<div class="page-sidebar nav-collapse collapse">
				<!-- BEGIN SIDEBAR MENU -->
				<ul class="page-sidebar-menu">
					<li>
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
						<div class="sidebar-toggler hidden-phone"></div>
						<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					</li>
					<li>
						<!-- BEGIN RESPONSIVE QUICK SEARCH FORM -->
						<form class="sidebar-search">
							<div class="input-box">
								<a href="javascript:;" class="remove"></a>
								<input type="text" placeholder="Search..." />
								<input type="button" class="submit" value=" " />
							</div>
						</form>
						<!-- END RESPONSIVE QUICK SEARCH FORM -->
					</li>

					<li class="m start active ">
						<a href="javascript:;" url="portal/myPortal.do"> <i class="icon-home"></i> <span class="title">我的工作室</span> <span class="selected"></span> </a>
					</li>
					
					<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
					
					<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
					
				</ul>
				<!-- END SIDEBAR MENU -->
			</div>
			<!-- END SIDEBAR -->

			<!-- BEGIN PAGE -->
			<div class="page-content">

				<!-- BEGIN PAGE CONTAINER-->
				<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
				<div id="div_container" class="container-fluid">

				</div>
				<!-- +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ -->
				<!-- END PAGE CONTAINER-->

			</div>
			<!-- END PAGE -->
		</div>

		<!-- END CONTAINER -->

		<!-- BEGIN FOOTER -->
		<div class="footer">
			<div class="footer-inner">
				${cpy}
			</div>
			<div class="footer-tools">
				<span class="go-top"> <i class="icon-angle-up"></i> </span>
			</div>
		</div>
		<!-- END FOOTER -->

		<div id="div_Alert" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
			<div class="modal-header">
				<button data-dismiss="modal" class="close" type="button"></button>
				<h3 class="page-title">
					提示
				</h3>
			</div>
			<div class="modal-body">
				<p id="div_AlertMsg">
			</div>
			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn green">
					OK
				</button>
			</div>
		</div>

		<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
		<!-- BEGIN CORE PLUGINS -->
		<script src="<c:url value="/media/fm/jquery-1.10.1.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/jquery-migrate-1.2.1.min.js"/>" type="text/javascript"></script>
		<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
		<script src="<c:url value="/media/fm/jquery-ui-1.10.1.custom.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/bootstrap.min.js"/>" type="text/javascript"></script>
		<!--[if lt IE 9]>
	<script src="<c:url value="/media/fm/excanvas.min.js"/>"></script>
	<script src="<c:url value="/media/fm/respond.min.js"/>"></script>  
	<![endif]-->
		<script src="<c:url value="/media/fm/jquery.slimscroll.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/jquery.blockui.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/jquery.cookie.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/jquery.uniform.min.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/json2.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/math.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/highcharts.js"/>" type="text/javascript"></script>
		<script src="<c:url value="/media/fm/exporting.js"/>" type="text/javascript"></script> 
		<!--  
    <script src="<c:url value="/media/fm/date.js"/>" type="text/javascript" ></script>
    -->
		<script src="<c:url value="/media/fm/app.js"/>" type="text/javascript"></script>
		<!-- END CORE PLUGINS -->

		<!-- BEGIN PAGE LEVEL SCRIPTS -->
		<script src="<c:url value="/media/js/index.js"/>" type="text/javascript"></script>
		<!-- END PAGE LEVEL SCRIPTS -->

		<script>
	jQuery(document).ready(function()
	{
		App.init(); // initlayout and core plugins
		Index.init();
	});
</script>
		<!-- END JAVASCRIPTS -->
	</body>
	<!-- END BODY -->

</html>
