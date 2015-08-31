<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="/media/css/daterangepicker.css"/>" />

<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
	<h3 class="page-title">
		基础数据
		<small>监测点管理</small>
	</h3>

	<ul class="breadcrumb">
		<li>
			<i class="icon-home"></i>
			<a href="index.do">我的工作室</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">基础数据</a>
			<span class="icon-angle-right"></span>
		</li>
		<li>
			<a href="#">监测点管理</a>
		</li>
	</ul>
</div>

<!-- END PAGE HEADER-->
<!-- 列表开始 -->
<div class="row-fluid">
	<div class="span12 portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i><span class="titleExcel">监测点列表</span>
			</div>
			<div class="actions">
				<button class="btn red doSearch">
					刷新
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<button class="btn red doAddNew">
					新增
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<button class="btn red doEdit">
					编辑
					<i class="m-icon-swapright m-icon-white"></i>
				</button>
				<a class="btn red doExport">
					导出
					<i class="m-icon-swapright m-icon-white"></i>
				</a>
			</div>
		</div>

		<div class="portlet-body flip-scroll">
			<form action="#" class="form-horizontal">
				
			</form>
			<table id="tbList" class="table table-striped table-bordered table-hover table-full-width" data-click-to-select="true" data-single-select="true">
				<thead>
					<tr>
						<!--<th data-checkbox="true">
							
						</th>
						--><th data-field="sttnId" data-visible="false">
							监测点ID
						</th>
						<th data-field="sttnnm">
							监测点名称
						</th>
						<th data-field="sttntp">
							监测项目
						</th>
						<th data-field="sttnattr">
							地址
						</th>
						<th>
							所属公司
						</th>
						<th data-field="sttnct">
							联系人
						</th>
						<th data-field="mobile">
							电话
						</th>
						<th data-field="sts">
							状态
						</th>
					</tr>
				</thead>
			</table>
		</div>

		<div id="formEdit" class="modal hide fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
			<div class="modal-header">
				<button data-dismiss="modal" class="close" type="button"></button>
				<h3 class="page-title" id="tileBiao">
					监测点信息
				</h3>
			</div>
			<div class="modal-body">
				<form action="#" id="fmUserEdit" class="form-horizontal">
					<input type="hidden" value="0" id="usrId">
					<input type="hidden" value="0" id="opsd">
					<div class="control-group">
						<label class="control-label">
							监测点名称
							<span class="required">*</span>
						</label>
						<div class="controls">
							<input type="text" class="form-control" id="lgnNm" name="lgnNm" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							监测项目
							<span class="required">*</span>
						</label>
						<div class="controls">
							<input type="text" class="form-control" id="usrNm" name="usrNm" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							地址
							<span class="required">*</span>
						</label>
						<div class="controls">
							<input type="password" class="form-control" id="pswd" name="pswd" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							所属公司
							<span class="required">*</span>
						</label>
						<div class="controls">
							<input type="password" class="form-control" id="pswdOK" name="pswdOK" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							联系人
							<span class="required">*</span>
						</label>
						<div class="controls">
							<input type="text" class="form-control" id="email" name="email" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							电话
							<span class="required">*</span>
						</label>
						<div class="controls">
							<select id="rlId">
								${roleOption}
							</select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">
							状态
							<span class="required">*</span>
						</label>
						<div class="controls">
							<select id="sts">
								${statusSelect}
							</select>
						</div>
					</div>
					<div id="divStore" class="control-group">
						<label class="control-label">
							关联门店
							<span class="required">*</span>
						</label>
						<div class="controls">
							<select id="stCd" name="stCd">
								${storeSelect}
							</select>
						</div>
					</div>
				</form>
			</div>

			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn red">
					取消
				</button>
				<button type="button" onclick="doSave()" class="btn green">
					保存
				</button>
			</div>
		</div>
	</div>
</div>

<script src="<c:url value="/media/fm/daterangepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-datetimepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.uniform.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/bootstrap-table.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.validate.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.validate.messages_cn.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/md5.js"/>" type="text/javascript"></script>

<script type="text/javascript">
	var $table = App.initTable($("#tbList"));

	$().ready(function()
	{
		//setRadioButton();

		doSearch();

		//点击查询
		$(".doSearch").bind("click", function()
		{
			doSearch();
		});

		$(".doAddNew").bind("click", function()
		{
			doAddNew();
		});

		$(".doEdit").bind("click", function()
		{
			doEdit();
		});

		$("#fmUserEdit").validate(
		{
			rules :
			{
				lgnNm :
				{
					minlength : 5,
					required : true
				},
				usrNm :
				{
					required : true
				},
				pswd :
				{
					minlength : 6,
					required : true
				},
				pswdOK :
				{
					equalTo : "#pswd",
					required : true
				},
				email :
				{
					email : true
				},
				stCd :
				{
					required : true
				}
			}
		});

	});

	function setRadioButton()
	{
		var test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle, .star)");
		if (test.size() > 0)
		{
			test.each(function()
			{
				if ($(this).parents(".checker").size() == 0)
				{
					$(this).show();
					$(this).uniform();
				}
			});

			test.bind("change", function()
			{
				doSearch();
			});
		}
	}

	function roleFormatter(value, row, index)
	{
		return '<span>' + row.role.rlNm + '</span>';
	}

	function doSearch()
	{
		/** 
		App.block();

		var selectedType = $("#userType input:radio:checked").val();
		$.get("account/userList.do?userType=" + selectedType, function(data, status)
		{
			$table.bootstrapTable("load", data);

			App.unblock();
		});
		*/
	}

	function setDivStore()
	{
		var selectedType = $("#userType input:radio:checked").val();
		if (selectedType == 1)
		{
			$("#divStore").hide();
		} else
		{
			$("#divStore").show();
		}
	}

	function doAddNew()
	{
		setDivStore();

		$("#sts").val(1);
		$("#usrId").val(0);
		$("#lgnNm").val("");
		$("#usrNm").val("");
		$("#pswd").val("");
		$("#pswdOK").val("");
		$("#opsd").val("");
		$("#email").val("");
		$("#rlId").val("");
		$("#stCd").val("");

		$("#formEdit").modal(
		{
			keyboard : true
		});
	}

	function doEdit()
	{
		var usrId = App.getTableSelection($table).usrId;
		if (usrId == undefined)
		{
			return;
		}
		setDivStore();
		var url = "account/userEdit.do?usrId=" + usrId;
		$.get(url, function(data, status)
		{
			$("#sts").val(data.sts);
			$("#usrId").val(data.usrId);
			$("#lgnNm").val(data.lgnNm);
			$("#usrNm").val(data.usrNm);
			$("#pswd").val(data.pswd);
			$("#pswdOK").val(data.pswd);
			$("#opsd").val(data.pswd);
			$("#email").val(data.email);
			$("#rlId").val(data.role.rlId);
			$("#stCd").val(data.stCd);
		});

		$("#formEdit").modal(
		{
			keyboard : true
		});
	}

	function doSave()
	{
		if ($("#fmUserEdit").valid() == false)
		{
			return;
		}

		var xpsd = "";
		if ($("#pswd").val() != $("#opsd").val())
		{
			xpsd = hex_md5($("#pswd").val());
		} else
		{
			xpsd = $("#pswd").val();
		}

		var stCode = $("#stCd").val();
		var selectedType = $("#userType input:radio:checked").val();
		if (selectedType == 1)
		{
			stCode = null;
		}
		var formParam =
		{
			"sts" : $("#sts").val(),
			"usrId" : $("#usrId").val(),
			"lgnNm" : $("#lgnNm").val(),
			"usrNm" : $("#usrNm").val(),
			"pswd" : xpsd,
			"email" : $("#email").val(),
			"rlId" : $("#rlId").val(),
			"stCd" : stCode
		};

		$.post("account/userSave.do", formParam, function(data)
		{
			App.alert(data);

			doSearch();

			$("#formEdit").modal("hide");
		});
	}
</script>
