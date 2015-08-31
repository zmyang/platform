<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		门店信息
		<small>门店管理</small>
	</h3>

	<ul class="breadcrumb">
		<li>
			<i class="icon-home"></i>
			<a href="index.do">我的工作室</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">门店信息</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">门店管理</a>
		</li>
	</ul>
</div>

<!-- END PAGE HEADER-->

<div class="row-fluid">
	<div class="portlet box blue tabbable">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-reorder"></i>
				<span class="hidden-480">门店信息</span>
			</div>
		</div>

		<div class="portlet-body form">
			<div class="tabbable portlet-tabs">
				<ul class="nav nav-tabs">
					<li class="">
						<a href="#portlet_tab2" class="storeOther" data-toggle="tab">其他信息</a>
					</li>
					<li class="active">
						<a href="#portlet_tab1" class="storeInfo" data-toggle="tab">基本信息</a>
					</li>
				</ul>

				<div class="tab-content">
					<div class="tab-pane active" id="portlet_tab1">
						<!-- BEGIN FORM-->
						<form action="#" id="fmStoreInfo" class="form-horizontal">
							<h3 class="form-section">
								基本信息
							</h3>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											门店编码
											<span class="required">*</span>
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" id="stCd" name="stCd" value="${store.stCd}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											门店名称
											<span class="required">*</span>
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="stNm" value="${store.stNm}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											品牌
											<span class="required">*</span>
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="brndCd">
												${sBrand}
											</select>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											类型
											<span class="required">*</span>
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="stTpCd">
												${sStoreType}
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											市场
											<span class="required">*</span>
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="mktCd">
												${sMarket}
											</select>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											城市
											<span class="required">*</span>
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="cityCd">
												${sCity}
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											区县
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="dstrct" value="${store.dstrct}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											商圈
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="znCd">
												${sZone}
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											电子邮箱
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="email" value="${store.email}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											联系电话
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="tel" value="${store.tel}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											地址
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="adrs" value="${store.adrs}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											邮编
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="post" value="${store.post}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											状态
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="sts">
												${sSts}
											</select>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											
										</label>
										<div class="controls">
											
										</div>
									</div>
								</div>
							</div>

							<h3 class="form-section">
								营运信息
							</h3>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											法人公司
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="cmpnCd">
												${sCompany}
											</select>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											24小时营业
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="is24h">
												${sIs24H}
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											营运总经理
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="oprtnGm" value="${store.oprtnGm}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											营运总监
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="oprtnDrct" value="${store.oprtnDrct}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											营运经理
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="oprtnMgr" value="${store.oprtnMgr}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											区域经理
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="am" value="${store.am}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											门店经理
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="stMgr" value="${store.stMgr}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											营业面积
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="oprtar" value="${store.oprtar}">
										</div>
									</div>
								</div>
							</div>

							<div class="form-actions">
								<a class="btn blue saveStore"> <i class="icon-ok"></i> 保存 </a>
								<a class="btn cancelSave"> 取消 </a>
							</div>
						</form>
						<!-- END FORM-->
					</div>

					<div class="tab-pane" id="portlet_tab2">
						<form action="#" id="fmStoreOther" class="form-horizontal">
							<input type="hidden" id="otCd" name="stCd" value="${store.stCd}">
							<h3 class="form-section">
								其他信息
							</h3>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											收银系统类型
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="posCd">
												${sPosType}
											</select>
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											考勤系统类型
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="atndnCd">
												${sAtndnType}
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											租赁面积
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="rntSz" value="${other.rntSz}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											营运面积
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="oprtnAr" value="${other.oprtnAr}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											楼层数
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="flr" value="${other.flr}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											桌位数
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="stQnt" value="${other.stQnt}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											开业日期
											<span class="required">*</span>
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="opnDt" value="${other.opnDt}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											关店日期
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="clsDt" value="${other.clsDt}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											开店时间
											<span class="required">*</span>
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="opnTm" value="${other.opnTm}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											打烊时间
											<span class="required">*</span>
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="clsTm" value="${other.clsTm}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											收银机数量
											<span class="required">*</span>
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="posCnt" value="${other.posCnt}">
										</div>
									</div>
								</div>
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											最大交班次数
											<span class="required">*</span>
										</label>
										<div class="controls">
											<input type="text" class="m-wrap span12" name="shftCnt" value="${other.shftCnt}">
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span6 ">
									<div class="control-group">
										<label class="control-label">
											配送中心
										</label>
										<div class="controls">
											<select class="m-wrap span12" name="dcCd">
												${sDistribution}
											</select>
										</div>
									</div>
								</div>
							</div>
							<div class="form-actions">
								<a class="btn blue saveOther"> <i class="icon-ok"></i> 保存 </a>
								<a class="btn cancelSave"> 取消 </a>
							</div>
						</form>
						<!-- END FORM-->
					</div>

				</div>

			</div>

		</div>

	</div>

</div>

<script src="<c:url value="/media/fm/bootstrap-table.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.validate.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.validate.messages_cn.js"/>" type="text/javascript"></script>

<script type="text/javascript">
	var $table = App.initTable($("#tbList"));

	$().ready(function()
	{
		setStCd();

		$(".saveStore").bind("click", function()
		{
			saveStore();
		});

		$(".saveOther").bind("click", function()
		{
			saveOther();
		});

		$(".cancelSave").bind("click", function()
		{
			backToList();
		});

		$(".storeOther").bind("click", function()
		{
			$("#otCd").val($("#stCd").val());
		});

		$("#fmStoreInfo").validate(
		{
			rules :
			{
				stCd :
				{
					required : true
				},
				stNm :
				{
					required : true
				},
				email :
				{
					email : true
				},
				oprtar :
				{
					number : true
				}
			}
		});

		$("#fmStoreOther").validate(
		{
			rules :
			{
				rntSz :
				{
					number : true
				},
				opnDt :
				{
					required : true
				},
				opnTm :
				{
					required : true
				},
				clsTm :
				{
					required : true
				},
				oprtnAr :
				{
					number : true
				},
				flr :
				{
					digits : true
				},
				stQnt :
				{
					digits : true
				},
				posCnt :
				{
					required : true,
					digits : true
				},
				shftCnt :
				{
					required : true,
					digits : true
				}
			}
		});
	});

	function setStCd()
	{
		if ($("#stCd").val() != "")
		{
			$("#stCd").attr("disabled", true);
		}
	}

	function saveStore()
	{
		if ($("#fmStoreInfo").valid() == false)
		{
			return;
		}

		App.block();
		//serialize只序列化可用的
		$("#stCd").attr("disabled", false);
		$.post("shop/storeSave.do", $("#fmStoreInfo").serialize(), function(data)
		{
			App.alert(data);

			App.unblock();

			//重新设置
			setStCd();
		});
	}

	function saveOther()
	{
		if ($("#fmStoreOther").valid() == false)
		{
			return;
		}
		
		App.block();
		$.post("shop/otherSave.do", $("#fmStoreOther").serialize(), function(data)
		{
			App.alert(data);

			App.unblock();
		});
	}

	function backToList()
	{
		App.loadPage("shop/toStorePage.do");
	}
</script>
