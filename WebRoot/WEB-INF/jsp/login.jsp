<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<title>${tit}</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	
	<link href="<c:url value="/media/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/media/css/bootstrap-responsive.min.css"/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/media/css/font-awesome.min.css"/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/media/css/style-metro.css"/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/media/css/style.css"/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/media/css/style-responsive.css"/>" rel="stylesheet" type="text/css"/>
	<link href="<c:url value="/media/css/theme/default.css"/>" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="<c:url value="/media/css/uniform.default.css"/>" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES -->
	<link href="<c:url value="/media/css/login.css"/>" rel="stylesheet" type="text/css"/>
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="<c:url value="/media/image/favicon.ico"/>" />
</head>
<!-- END HEAD -->

<!-- BEGIN BODY -->
<body>
	<!-- BEGIN Login -->
	<div class="login">
	<!-- BEGIN LOGO -->
	<div class="logo">
		<img src="<c:url value="/media/image/logo-big.png"/>" alt="" />
	</div>
	<!-- END LOGO -->

	<!-- BEGIN LOGIN -->
	<div class="content">
		<!-- BEGIN LOGIN FORM -->
		<form class="form-vertical login-form" action="doLogin.do">
			<h3 class="form-title">登录您的账号</h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span>请输入您的用户名和密码。</span>
			</div>

			<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">用户</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="登录名" id="uNm" name="username"/>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<input id="password" class="m-wrap placeholder-no-fix" type="password" placeholder="密码" name="password"/>
					</div>
				</div>
			</div>

			<div class="form-actions">
				<label class="checkbox" style="display:none">
				<input type="checkbox" name="remember" value="1"/> 记住密码
				</label>
				<button type="submit" class="btn green pull-right">
				登　 录 <i class="m-icon-swapright m-icon-white"></i>
				</button>            
			</div>

			<div class="forget-password" style="display:none">
				<h4>忘了您的密码 ?</h4>
				<p>
					没关系, 点击 <a href="javascript:;" class="" id="forget-password">这里</a>
					重新设置密码.
				</p>
			</div>

			<div class="create-account" style="display:none">
				<p>
					还没有账号 ?&nbsp; 
					<a href="javascript:;" id="register-btn" class="">创建账号</a>
				</p>
			</div>
		</form>
		<!-- END LOGIN FORM -->        

		<!-- BEGIN FORGOT PASSWORD FORM -->
		<form class="form-vertical forget-form" action="index.html" style="display:none">
			<h3 class="">忘记密码 ?</h3>
			<p>输入您的 e-mail 地址来重设密码.</p>
			<div class="control-group">
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-envelope"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email" />
					</div>
				</div>
			</div>

			<div class="form-actions">
				<button type="button" id="back-btn" class="btn">
				<i class="m-icon-swapleft"></i> 返回
				</button>
				<button type="submit" class="btn green pull-right">
				Submit <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</form>
		<!-- END FORGOT PASSWORD FORM -->

		<!-- BEGIN REGISTRATION FORM -->
		<form class="form-vertical register-form" action="index.html"  style="display:none">
			<h3 class="">创建账号</h3>
			<p>在下面输入您的账号信息:</p>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="用户名" name="username"/>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i>
						<input class="m-wrap placeholder-no-fix" type="password" id="register_password" placeholder="密码" name="password"/>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">再次输入密码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-ok"></i>
						<input class="m-wrap placeholder-no-fix" type="password" placeholder="再次输入密码" name="rpassword"/>
					</div>
				</div>
			</div>

			<div class="control-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">Email</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-envelope"></i>
						<input class="m-wrap placeholder-no-fix" type="text" placeholder="Email" name="email"/>
					</div>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<label class="checkbox">
					<input type="checkbox" name="tnc"/> 我同意 <a href="#">服务条款</a> 和 <a href="#">相关规定</a>
					</label>  
					<div id="register_tnc_error"></div>
				</div>
			</div>

			<div class="form-actions">
				<button id="register-back-btn" type="button" class="btn">
				<i class="m-icon-swapleft"></i>  返回
				</button>
				<button type="submit" id="register-submit-btn" class="btn green pull-right">
				创建 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</form>
		<!-- END REGISTRATION FORM -->
	</div>
	<!-- END LOGIN -->

	<!-- BEGIN COPYRIGHT -->
	<div class="copyright">
		${cpy}
	</div>
	<!-- END COPYRIGHT -->
	</div>
	<!-- END Login -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->
	<script src="<c:url value="/media/fm/jquery-1.10.1.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/media/fm/jquery-migrate-1.2.1.min.js"/>" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="<c:url value="/media/fm/jquery-ui-1.10.1.custom.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/media/fm/jquery.form.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/media/fm/bootstrap.min.js"/>" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="<c:url value="/media/fm/excanvas.min.js"/>"></script>
	<script src="<c:url value="/media/fm/respond.min.js"/>"></script>  
	<![endif]-->   
	<script src="<c:url value="/media/fm/jquery.slimscroll.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/media/fm/jquery.blockui.min.js"/>" type="text/javascript"></script>  
	<script src="<c:url value="/media/fm/jquery.cookie.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/media/fm/jquery.uniform.min.js"/>" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="<c:url value="/media/fm/jquery.validate.min.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/media/fm/md5.js"/>"                 type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="<c:url value="/media/fm/app.js"/>" type="text/javascript"></script>
	<script src="<c:url value="/media/js/login.js"/>" type="text/javascript"></script>      
	<!-- END PAGE LEVEL SCRIPTS --> 
	
	<script>
		jQuery(document).ready(function() {     
		  App.init();
		  Login.init();
		  
		  $("#uNm").focus();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>