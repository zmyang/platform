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
        <!-- BEGIN PAGE TITLE & BREADCRUMB-->
        <h3 class="page-title">今天的工作 <small>每日工作任务一览</small></h3>

        <ul class="breadcrumb">
            <li>
                <i class="icon-home"></i>
                <a href="javascript:;">首页</a>
            </li>
        </ul>
    </div>

</div>

<!-- END PAGE HEADER-->
<div id="dashboard">
    <!-- BEGIN DASHBOARD STATS -->
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

    <!-- END DASHBOARD STATS -->

    <div class="clearfix"></div>

    <div class="row-fluid">

        <div class="span6">

            <!-- BEGIN PORTLET-->

            <div class="portlet solid bordered light-grey">

                <div class="portlet-title">

                    <div class="caption"><i class="icon-bar-chart"></i>销售数据</div>

                    <div class="tools">

                        <div class="btn-group pull-right" data-toggle="buttons-radio">

                            <a href="" class="btn mini">日</a>
                            <a href="" class="btn mini">周</a>
                            <a href="" class="btn mini active">月</a>

                        </div>

                    </div>

                </div>

                <div class="portlet-body">

                    <div id="site_statistics_loading">

                        <img src="../media/image/loading.gif" alt="loading" />

                    </div>

                    <div id="site_statistics_content" class="hide">

                        <div id="site_statistics" class="chart"></div>

                    </div>

                </div>

            </div>

            <!-- END PORTLET-->

        </div>

        <div class="span6">

            <!-- BEGIN PORTLET-->

            <div class="portlet solid light-grey bordered">

                <div class="portlet-title">

                    <div class="caption"><i class="icon-bullhorn"></i>完成比例</div>

                    <div class="tools">

                        <div class="btn-group pull-right" data-toggle="buttons-radio">

                            <a href="" class="btn blue mini active">月</a>

                            <a href="" class="btn blue mini">年</a>

                        </div>

                    </div>

                </div>

                <div class="portlet-body">

                    <div id="site_activities_loading">

                        <img src="../media/image/loading.gif" alt="loading" />

                    </div>

                    <div id="site_activities_content" class="hide">

                        <div id="site_activities" class="chart"></div>

                    </div>

                </div>

            </div>

            <!-- END PORTLET-->

        </div>


    </div>

    <div class="clearfix"></div>

    <div class="row-fluid">

        <div class="span6">

            <div class="portlet box purple">

                <div class="portlet-title">

                    <div class="caption"><i class="icon-calendar"></i>关键任务</div>

                    <div class="actions">

                        <a href="javascript:;" class="btn yellow easy-pie-chart-reload"><i class="icon-repeat"></i>Reload</a>

                    </div>

                </div>

                <div class="portlet-body">

                    <div class="row-fluid">

                        <div class="span4">

                            <div class="easy-pie-chart">

                                <div class="number transactions" data-percent="55"><span>+55</span>%</div>

                                <a class="title" href="#">现金管理 <i class="m-icon-swapright"></i></a>

                            </div>

                        </div>

                        <div class="margin-bottom-10 visible-phone"></div>

                        <div class="span4">

                            <div class="easy-pie-chart">

                                <div class="number visits" data-percent="85"><span>+85</span>%</div>

                                <a class="title" href="#">库存管理 <i class="m-icon-swapright"></i></a>

                            </div>

                        </div>

                        <div class="margin-bottom-10 visible-phone"></div>

                        <div class="span4">

                            <div class="easy-pie-chart">

                                <div class="number bounce" data-percent="46"><span>-46</span>%</div>

                                <a class="title" href="#">人事管理 <i class="m-icon-swapright"></i></a>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

        <div class="span6">

            <div class="portlet box blue">

                <div class="portlet-title">

                    <div class="caption"><i class="icon-calendar"></i>辅助任务</div>

                    <div class="tools">

                        <a href="" class="collapse"></a>

                        <a href="#portlet-config" data-toggle="modal" class="config"></a>

                        <a href="" class="reload"></a>

                        <a href="" class="remove"></a>

                    </div>

                </div>

                <div class="portlet-body">

                    <div class="row-fluid">

                        <div class="span4">

                            <div class="sparkline-chart">

                                <div class="number" id="sparkline_bar"></div>

                                <a class="title" href="#">数据同步 <i class="m-icon-swapright"></i></a>

                            </div>

                        </div>

                        <div class="margin-bottom-10 visible-phone"></div>

                        <div class="span4">

                            <div class="sparkline-chart">

                                <div class="number" id="sparkline_bar2"></div>

                                <a class="title" href="#">合同到期 <i class="m-icon-swapright"></i></a>

                            </div>

                        </div>

                        <div class="margin-bottom-10 visible-phone"></div>

                        <div class="span4">

                            <div class="sparkline-chart">

                                <div class="number" id="sparkline_line"></div>

                                <a class="title" href="#">设备检查 <i class="m-icon-swapright"></i></a>

                            </div>

                        </div>

                    </div>

                </div>

            </div>

        </div>

    </div>

    <div class="clearfix"></div>

    <div class="row-fluid">

        <div class="span6">

            <!-- BEGIN PORTLET-->

            <div class="portlet box blue calendar">

                <div class="portlet-title">

                    <div class="caption"><i class="icon-calendar"></i>订货班表</div>

                </div>

                <div class="portlet-body light-grey">

                    <div id="calendar">
                    </div>

                </div>

            </div>

            <!-- END PORTLET-->

        </div>

        <div class="span6">

            <!-- BEGIN PORTLET-->

               <div class="portlet">

                   <div class="portlet-title line">

                       <div class="caption"><i class="icon-comments"></i>信息沟通</div>

                       <div class="tools">

                           <a href="" class="collapse"></a>

                           <a href="#portlet-config" data-toggle="modal" class="config"></a>

                           <a href="" class="reload"></a>

                           <a href="" class="remove"></a>

                       </div>

                   </div>

                   <div class="portlet-body" id="chats">

                       <div class="scroller" data-height="435px" data-always-visible="1" data-rail-visible1="1">

                           <ul class="chats">

                               <li class="in">

                                   <img class="avatar" alt="" src="../media/image/avatar1.jpg" />

                                   <div class="message">

                                       <span class="arrow"></span>

                                       <a href="#" class="name">南京新街口店</a>

                                       <span class="datetime">at Jul 25, 2012 11:09</span>

                                       <span class="body">-
                                       </span>

                                   </div>

                               </li>

                               <li class="out">

                                   <img class="avatar" alt="" src="../media/image/avatar2.jpg" />

                                   <div class="message">

                                       <span class="arrow"></span>

                                       <a href="#" class="name">南京珠江路店</a>

                                       <span class="datetime">at Jul 25, 2012 11:09</span>

                                       <span class="body">-
                                       </span>

                                   </div>

                               </li>

                               <li class="in">

                                   <img class="avatar" alt="" src="../media/image/avatar1.jpg" />

                                   <div class="message">

                                       <span class="arrow"></span>

                                       <a href="#" class="name">南京新街口店</a>

                                       <span class="datetime">at Jul 25, 2012 11:09</span>

                                       <span class="body">-
                                       </span>

                                   </div>

                               </li>

                               <li class="out">

                                   <img class="avatar" alt="" src="../media/image/avatar3.jpg" />

                                   <div class="message">

                                       <span class="arrow"></span>

                                       <a href="#" class="name">南京夫子庙店</a>

                                       <span class="datetime">at Jul 25, 2012 11:09</span>

                                       <span class="body">-
                                       </span>

                                   </div>

                               </li>

                               <li class="in">

                                   <img class="avatar" alt="" src="../media/image/avatar3.jpg" />

                                   <div class="message">

                                       <span class="arrow"></span>

                                       <a href="#" class="name">南京夫子庙店</a>

                                       <span class="datetime">at Jul 25, 2012 11:09</span>

                                       <span class="body">-
                                       </span>

                                   </div>

                               </li>

                               <li class="out">

                                   <img class="avatar" alt="" src="../media/image/avatar1.jpg" />

                                   <div class="message">

                                       <span class="arrow"></span>

                                       <a href="#" class="name">南京新街口店</a>

                                       <span class="datetime">at Jul 25, 2012 11:09</span>

                                       <span class="body">-

                                       </span>

                                   </div>

                               </li>

                               <li class="in">

                                   <img class="avatar" alt="" src="../media/image/avatar3.jpg" />

                                   <div class="message">

                                       <span class="arrow"></span>

                                       <a href="#" class="name">南京夫子庙店</a>

                                       <span class="datetime">at Jul 25, 2012 11:09</span>

                                       <span class="body">-
                                       </span>

                                   </div>

                               </li>
                           </ul>

                       </div>

                       <div class="chat-form">

                           <div class="input-cont">

                               <input class="m-wrap" type="text" placeholder="Type a message here..." />

                           </div>

                           <div class="btn-cont">

                               <span class="arrow"></span>

                               <a href="" class="btn blue icn-only"><i class="icon-ok icon-white"></i></a>

                           </div>

                       </div>

                   </div>

               </div>

               <!-- END PORTLET-->

        </div>

    </div>

</div>

<script src="<c:url value="/media/fm/jquery.vmap.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.russia.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.world.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.europe.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.germany.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.usa.js"/>" type="text/javascript"></script>
<script src="<c:url value="/media/fm/jquery.vmap.sampledata.js"/>" type="text/javascript"></script>
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
    	Portal.initCalendar(); // init index page's custom scripts
    	Portal.initCharts(); // init index page's custom scripts
    	Portal.initMiniCharts();
    });
</script>

