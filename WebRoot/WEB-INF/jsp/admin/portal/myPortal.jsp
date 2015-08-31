<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>


<link href="<c:url value="/media/css/jquery.gritter.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/media/css/daterangepicker.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/media/css/fullcalendar.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/media/css/jqvmap.css"/>" rel="stylesheet" type="text/css" media="screen" />
<link href="<c:url value="/media/css/jquery.easy-pie-chart.css"/>" rel="stylesheet" type="text/css" media="screen" />

    
<!-- BEGIN PAGE HEADER-->
<div class="row-fluid">
    <div class="span12">
        <!-- BEGIN PAGE TITLE & BREADCRUMB
        <h3 class="page-title">今天的工作 <small>每日工作任务一览</small></h3>
		
        <ul class="breadcrumb">
            <li>
                <i class="icon-home"></i>
                <a href="javascript:;">首页</a>
            </li>
        </ul>
        -->
    </div>

</div>

<!-- END PAGE HEADER-->
<div id="dashboard">
    <!-- BEGIN DASHBOARD STATS 
    <div class="row-fluid">
        <div class="responsive span3" data-tablet="span6" data-desktop="span3">
            <div class="dashboard-stat green">
                <div class="visual">
                    <i class="icon-shopping-cart"></i>
                </div>
                <div class="details">
                    <div class="number">-</div>
                    <div class="desc">交易次数</div>
                </div>
                <a class="more" href="#">查看更多 <i class="m-icon-swapright m-icon-white"></i></a>
            </div>
        </div>

        <div class="responsive span3" data-tablet="span6" data-desktop="span3">
            <div class="dashboard-stat blue">
                <div class="visual">
                    <i class="icon-comments"></i>
                </div>
                <div class="details">
                    <div class="number">-</div>
                    <div class="desc">顾客数量</div>
                </div>
                <a class="more" href="#">查看更多 <i class="m-icon-swapright m-icon-white"></i></a>
            </div>
        </div>

        <div class="responsive span3" data-tablet="span6  fix-offset" data-desktop="span3">
            <div class="dashboard-stat yellow">
                <div class="visual">
                    <i class="icon-bar-chart"></i>
                </div>
                <div class="details">
                    <div class="number">-</div>
                    <div class="desc">销售金额</div>
                </div>
                <a class="more" href="#">查看更多 <i class="m-icon-swapright m-icon-white"></i></a>
            </div>
        </div>

        <div class="responsive span3" data-tablet="span6" data-desktop="span3">
            <div class="dashboard-stat purple">
                <div class="visual">
                    <i class="icon-globe"></i>
                </div>
                <div class="details">
                    <div class="number">-</div>
                    <div class="desc">预估达成</div>
                </div>
                <a class="more" href="#">查看更多 <i class="m-icon-swapright m-icon-white"></i></a>
            </div>
        </div>

    </div>
	-->
    <!-- Portlet Start -->
    <div class="portlet box yellow">
	    <div class="portlet-title">
	        <div class="caption"><i class="icon-reorder"></i>查询条件</div>
	        <div class="tools">
	            <a href="javascript:;" class="collapse"></a>
	        </div>
	        <div class="actions">
	            <button class="btn red">查询 <i class="m-icon-swapright m-icon-white"></i></button>
	        </div>
	    </div>
        <div class="portlet-body form">
			<form action="#" class="form" id="1518486128">
			<div class="row-fluid">
				<div class="span3">
					<div class="control-group">
						<label class="control-label span3">企业</label>
						<select class="form-control">
						  <option>公司1</option>
						  <option>公司2</option>
						  <option>公司3</option>
						  <option>公司4</option>
						  <option>公司5</option>
						</select>
					</div>
				</div>
				<div class="span3">
					<div class="control-group">
						<label class="control-label span3">站点</label>
						<select class="form-control">
						  <option>1号站</option>
						  <option>2号站</option>
						  <option>3号站</option>
						  <option>4号站</option>
						  <option>5号站</option>
						</select>
					</div>
				</div>
				<div class="span3">
					<div class="control-group">
						<label class="control-label span3">项目</label>
						<select class="form-control">
						  <option>大气</option>
						  <option>水质</option>
						  <option>噪声</option>
						</select>
					</div>
				</div>
				<div class="span3">
					<div class="control-group">
						<label class="control-label span3">因子</label>
						<select class="form-control">
						  <option>温度</option>
						  <option>湿度</option>
						  <option>气压</option>
						  <option>PH</option>
						  <option>风力</option>
						</select>
					</div>
				</div>
			</div>
			</form>
		</div>
    </div>
    <!-- Portlet End -->
    <!-- row-fluid start -->
    <div class="row-fluid">
    	<div class="portlet solid light-grey bordered">

          <div class="portlet-title">

              <div class="caption"><i class="icon-bullhorn"></i>大气监测</div>

              <!--<div class="tools">

                  <div class="btn-group pull-right" data-toggle="buttons-radio">

                      <a href="" class="btn blue mini active">月</a>

                      <a href="" class="btn blue mini">年</a>

                  </div>

              </div>

          --></div>

          <div class="portlet-body">

          	<div id="hcChart" class="chart"></div>

          </div>
       </div>
    </div>
    <!-- row-fluid end -->
    
	<div class="portlet box blue">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-globe"></i><span class="titleExcel">设备列表</span>
			</div>
			<div class="actions">
				<button class="btn red doSearch">
					查询
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
			</div>
		</div>

		<div class="portlet-body form">
			<form action="#" class="form">
			<div class="row-fluid">
				<div class="span3">
					<div class="control-group">
						<label class="control-label span3">企业</label>
						<select class="form-control">
						  <option>公司1</option>
						  <option>公司2</option>
						  <option>公司3</option>
						  <option>公司4</option>
						  <option>公司5</option>
						</select>
					</div>
				</div>
				<div class="span3">
					<div class="control-group">
						<label class="control-label span3">站点</label>
						<select class="form-control">
						  <option>1号站</option>
						  <option>2号站</option>
						  <option>3号站</option>
						  <option>4号站</option>
						  <option>5号站</option>
						</select>
					</div>
				</div>
			</div>
			</form>
			<table id="tbList" class="table table-striped table-bordered table-hover table-full-width" data-click-to-select="true" data-single-select="true">
				<thead>
					<tr>
						<th data-field="eqid">
							设备编号
						</th>
						<th data-field="eqnm">
							设备名称
						</th>
						<th data-field="eqstt">
							所属站点
						</th>
						<th data-field="eqst">
							运行状态
						</th>
					</tr>
				</thead>
				<tbody>
				  <tr><td>10001</td><td>蓝盾仪器</td><td>1号站</td><td>正常运行</td></tr>
				  <tr><td>10002</td><td>德州仪器</td><td>2号站</td><td>电源异常</td></tr>
				  <tr><td>10003</td><td>温度计</td><td>3号站</td><td>维护中</td></tr>
				  <tr><td>10004</td><td>气压计</td><td>4号站</td><td>校零</td></tr>
				  <tr><td>10004</td><td>ph值检测仪</td><td>5号站</td><td>校满</td></tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- END DASHBOARD STATS -->

<!--<script src="<c:url value="/media/fm/jquery.vmap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.russia.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.world.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.europe.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.germany.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.usa.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.sampledata.js"/>" type="text/javascript"></script>
-->
<script src="<c:url value="/media/fm/jquery.flot.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.flot.resize.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.pulsate.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/date.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/daterangepicker.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.gritter.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/fullcalendar.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.easy-pie-chart.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.sparkline.min.js"/>" type="text/javascript"></script>

   

<script src="<c:url value="/media/js/portal.js"/>" type="text/javascript"></script>

<script>
    jQuery(document).ready(function () 
    {
    	Portal.init();
    	
    	Portal.initHChart();
    	
    });
</script>

