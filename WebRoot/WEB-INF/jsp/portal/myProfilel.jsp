<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<div class="span12">
		<!-- BEGIN PAGE TITLE & BREADCRUMB-->
		<h3 class="page-title">
			个人信息
			<small>我的个人信息</small>
		</h3>

		<ul class="breadcrumb">
			<li>
				<i class="icon-home"></i>
				<a href="index.do">首页</a>
			</li>
		</ul>
	</div>

</div>

<!-- END PAGE HEADER-->
<div id="dashboard">
	<!-- BEGIN DASHBOARD STATS -->
	<div class="row-fluid">
		<div class="span12">
			<div class="span12">

				<div class="span3">

					<ul class="ver-inline-menu tabbable margin-bottom-10">
						<li class="active">
							<a data-toggle="tab" href="#tab_1-1"> <i class="icon-cog"></i> 个人信息 </a>
							<span class="after"></span>
						</li>
						<li class="">
							<a data-toggle="tab" href="#tab_2-2"><i class="icon-lock"></i> 修改密码</a>
						</li>
					</ul>

				</div>

				<div class="span9">
					<div class="tab-content">
						<div id="tab_1-1" class="tab-pane active">
							<div style="height: auto;" id="accordion1-1" class="accordion in collapse">
								<form action="#" id="fmProfilel">
									<label class="control-label">
										登录名
									</label>
									<input type="text" class="m-wrap span8" disabled value="${info.lgnNm}">
									<input type="hidden" id="lgnNm" name="lgnNm" value="${info.lgnNm}">

									<label class="control-label">
										用户名
									</label>
									<input type="text" class="m-wrap span8" name="usrNm" value="${info.usrNm}">

									<label class="control-label">
										邮箱地址
									</label>
									<div class="controls">
										<div class="input-icon left">
											<i class="icon-envelope"></i>
											<input class="wrap span8" type="text" name="email" value="${info.email}">
										</div>
									</div>
									<!-- 
									<label class="control-label">
										Interests
									</label>

									<input type="text" placeholder="Design, Web etc." class="m-wrap span8">

									<label class="control-label">
										Occupation
									</label>

									<input type="text" placeholder="Web Developer" class="m-wrap span8">

									<label class="control-label">
										Counrty
									</label>

									<div class="controls">

										<input type="text" class="span8 m-wrap" style="margin: 0 auto;" data-provide="typeahead" data-items="4"
											data-source="[&quot;Alabama&quot;,&quot;Alaska&quot;,&quot;Arizona&quot;,&quot;Arkansas&quot;,&quot;US&quot;,&quot;Colorado&quot;,&quot;Connecticut&quot;,&quot;Delaware&quot;,&quot;Florida&quot;,&quot;Georgia&quot;,&quot;Hawaii&quot;,&quot;Idaho&quot;,&quot;Illinois&quot;,&quot;Indiana&quot;,&quot;Iowa&quot;,&quot;Kansas&quot;,&quot;Kentucky&quot;,&quot;Louisiana&quot;,&quot;Maine&quot;,&quot;Maryland&quot;,&quot;Massachusetts&quot;,&quot;Michigan&quot;,&quot;Minnesota&quot;,&quot;Mississippi&quot;,&quot;Missouri&quot;,&quot;Montana&quot;,&quot;Nebraska&quot;,&quot;Nevada&quot;,&quot;New Hampshire&quot;,&quot;New Jersey&quot;,&quot;New Mexico&quot;,&quot;New York&quot;,&quot;North Dakota&quot;,&quot;North Carolina&quot;,&quot;Ohio&quot;,&quot;Oklahoma&quot;,&quot;Oregon&quot;,&quot;Pennsylvania&quot;,&quot;Rhode Island&quot;,&quot;South Carolina&quot;,&quot;South Dakota&quot;,&quot;Tennessee&quot;,&quot;Texas&quot;,&quot;Utah&quot;,&quot;Vermont&quot;,&quot;Virginia&quot;,&quot;Washington&quot;,&quot;West Virginia&quot;,&quot;Wisconsin&quot;,&quot;Wyoming&quot;]">

										<p class="help-block">
											<span class="muted">Start typing to auto complete!. E.g: US</span>
										</p>

									</div>

									<label class="control-label">
										About
									</label>

									<textarea class="span8 m-wrap" rows="3"></textarea>

									<label class="control-label">
										Website Url
									</label>

									<input type="text" placeholder="http://www.mywebsite.com" class="m-wrap span8">
 -->
									<div class="submit-btn">
										<a href="#" class="btn green profilelSave">保存</a>
									</div>
								</form>
							</div>
						</div>

						<div id="tab_2-2" class="tab-pane">
							<div style="height: auto;" id="accordion3-3" class="accordion collapse in">
								<form action="#" id="fmPassword">
									<label class="control-label">
										当前密码
									</label>
									<input type="password" class="m-wrap span8" id="opsd" name="opsd">
									<input type="hidden" value="${info.pswd}"   id="pswd" name="pswd">
									
									<label class="control-label">
										新密码
									</label>
									<input type="password" class="m-wrap span8" id="npsd" name="npsd">

									<label class="control-label">
										确认新密码
									</label>
									<input type="password" class="m-wrap span8" id="cpsd" name="cpsd">

									<div class="submit-btn">
										<a href="#" class="btn green passwordSave">保存</a>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>

				<!--end span9-->

			</div>
		</div>
	</div>
</div>

<script src="<c:url value="/media/fm/jquery.validate.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/md5.js"/>" type="text/javascript"></script>

<script>
	jQuery(document).ready(function()
	{
		$(".profilelSave").bind("click", function()
		{
			profilelSave();
		});

		$(".passwordSave").bind("click", function()
		{
			passwordSave();
		});
		
		$("#fmPassword").validate(
		{
			rules :
			{
				opsd :
				{
					required : true
				},
				npsd :
				{
					minlength : 6,
					required : true
				},
				cpsd :
				{
					equalTo : "#npsd",
					required : true
				}
			}
		});
	});

	function profilelSave()
	{
		$.post("account/profilelSave.do", $("#fmProfilel").serialize(), function(data)
		{
			App.alert(data);
		});
	}

	function passwordSave()
	{
		if ($("#fmPassword").valid() == false)
		{
			return;
		}

		var opsd = hex_md5($("#opsd").val());
		if ($("#pswd").val() != opsd)
		{
			App.alert("旧密码输入不正确！");
			return;
		}
		
		var npsd = hex_md5($("#npsd").val());
		var formParam =
		{
			"lgnNm" : $("#lgnNm").val(),
			"pswd" : npsd
		};
		
		$.post("account/passwordSave.do", formParam, function(data)
		{
			App.alert(data);
		});
	}
</script>

