<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!-- Meta, title, CSS, favicons, etc. -->
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<sec:csrfMetaTags/>
	<title>VPayQwik | Home</title>

	<!-- Bootstrap core CSS -->
	<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
		  type="image/png" />

	<link
			href="${pageContext.request.contextPath}/resources/admin/css/bootstrap.min.css"
			rel="stylesheet">
	<link
			href="${pageContext.request.contextPath}/resources/admin/fonts/css/font-awesome.min.css"
			rel="stylesheet">
	<link
			href="${pageContext.request.contextPath}/resources/admin/css/animate.min.css"
			rel="stylesheet">

	<!-- Custom styling plus plugins -->
	<link
			href="${pageContext.request.contextPath}/resources/admin/css/custom.css"
			rel="stylesheet">
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath}/resources/admin/css/maps/jquery-jvectormap-2.0.3.css" />
	<link
			href="${pageContext.request.contextPath}/resources/admin/css/icheck/flat/green.css"
			rel="stylesheet" />
	<link
			href="${pageContext.request.contextPath}/resources/admin/css/floatexamples.css"
			rel="stylesheet" type="text/css" />

	<script
			src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script>
	<script
			src="${pageContext.request.contextPath}/resources/admin/js/nprogress.js"></script>
	<style>
		.no-js #loader {
			display: none;
		}

		.js #loader {
			display: block;
			position: absolute;
			left: 100px;
			top: 0;
		}

		.se-pre-con {
			position: fixed;
			left: 0px;
			top: 0px;
			width: 100%;
			height: 100%;
			z-index: 9999;
			background: url(/images/pq_large.gif) center no-repeat #fff;
		}
	</style>
	<script src="<c:url value='/resources/js/modernizr.js'/>"></script>

	<script type="text/javascript">
		$(window).load(function() {
			$(".se-pre-con").fadeOut("slow");
		});
	</script>

</head>

<body class="nav-md">
<div class="se-pre-con"></div>
<div class="container body">
	<div class="main_container">

		<jsp:include page="/WEB-INF/jsp/Admin/LeftMenu.jsp"/>
		<jsp:include page="/WEB-INF/jsp/Admin/TopNavigation.jsp"/>

		<!-- page content -->
		<div class="right_col" role="main">

			<!-- top tiles -->
			<div class="row tile_count">
				<div
						class="animated flipInY col-md-3 col-sm-5 col-xs-4 tile_stats_count">
					<div class="left"></div>
					<div class="right">
							<span class="count_top"><i class="fa fa-clock-o"></i>
								Merchant Payable</span>
						<div class="count"><c:out value="${mpayable}" default="" escapeXml="true"/></div>
						<!-- <span class="count_bottom"><i class="green"><i
                                class="fa fa-sort-asc"></i>3% </i> From last Week</span> -->
					</div>
				</div>
				<div
						class="animated flipInY col-md-3 col-sm-5 col-xs-4 tile_stats_count">
					<div class="left"></div>
					<div class="right">
							<span class="count_top"><i class="fa fa-clock-o"></i>
								Total Users</span>
						<div class="count"><c:out value="${onlineUser}" default="" escapeXml="true"/></div>
						<!-- <span class="count_bottom"><i class="green"><i
                                class="fa fa-sort-asc"></i>3% </i> From last Week</span> -->
					</div>
				</div>
				<div
						class="animated flipInY col-md-3 col-sm-5 col-xs-4 tile_stats_count">
					<div class="left"></div>
					<div class="right">
							<span class="count_top"><i class="fa fa-clock-o"></i>
								Total Commission</span>
						<div class="count"><c:out value="${tcommission}" default="" escapeXml="true"/></div>
						<!-- <span class="count_bottom"><i class="green"><i
                                class="fa fa-sort-asc"></i>3% </i> From last Week</span> -->
					</div>
				</div>
				<div
						class="animated flipInY col-md-3 col-sm-5 col-xs-4 tile_stats_count">
					<div class="left"></div>
					<div class="right">
							<span class="count_top"><i class="fa fa-user"></i> Total
								Transactions</span>
						<div class="count green"><c:out value="${totalTrans}" default="" escapeXml="true"/></div>
						<span class="count_bottom"><i class="green"> </i></span>
					</div>
				</div>

				<div
						class="animated flipInY col-md-3 col-sm-5 col-xs-4 tile_stats_count">
					<div class="left"></div>
					<div class="right">
							<span class="count_top"><i class="fa fa-user"></i>
								Total Load Money(EBS) </span>
						<div class="count green"><c:out value="${lme}" default="" escapeXml="true"/> &nbsp;</div>
						<span class="count_bottom"><i class="green"> </i></span>
					</div>
				</div>

				<div
						class="animated flipInY col-md-3 col-sm-5 col-xs-4 tile_stats_count">
					<div class="left"></div>
					<div class="right">
							<span class="count_top"><i class="fa fa-user"></i>
								Total Load Money(V-NET)</span>
						<div class="count green"><c:out value="${lmv}" default="" escapeXml="true"/> &nbsp;</div>
						<span class="count_bottom"><i class="green"> </i></span>
					</div>
				</div>
				<div
						class="animated flipInY col-md-3 col-sm-5 col-xs-4 tile_stats_count">
					<div class="left"></div>
					<div class="right">
							<span class="count_top"><i class="fa fa-user"></i>
								Payable Amount</span>
						<div class="count green"><c:out value="${tpay}" default="" escapeXml="true"/> &nbsp;</div>
						<span class="count_bottom"><i class="green"> </i></span>
					</div>
				</div>
				<div
						class="animated flipInY col-md-3 col-sm-5 col-xs-4 tile_stats_count">
					<div class="left"></div>
					<div class="right">
							<span class="count_top"><i class="fa fa-user"></i>
								Pool Account</span>
						<div class="count green"><c:out value="${pool}" default="" escapeXml="true"/> &nbsp;</div>
						<span class="count_bottom"><i class="green"> </i></span>
					</div>
				</div>
			</div>
			<!-- /top tiles -->

			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<div class="dashboard_graph">

						<div class="row x_title">
							<div class="col-md-6">
								<h3>
									VPayQwik Transaction's <small> <!-- Graph title sub-title -->
								</small>
								</h3>
							</div>

						</div>

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div id="placeholder33" style="height: 260px; display: none"
								 class="demo-placeholder"></div>
							<div style="width: 100%;">
								<div id="canvas_dahs" class="demo-placeholder"
									 style="width: 100%; height: 270px;"></div>
							</div>
						</div>
						<%-- <div class="col-md-3 col-sm-3 col-xs-12 bg-white">
                            <div class="x_title">
                                <h2>Top Campaign Performance</h2>
                                <div class="clearfix"></div>
                            </div>

                            <div class="col-md-12 col-sm-12 col-xs-6">
                                <div>
                                    <p>Total Male : <c:out value="${totalMale}" default="" escapeXml="true"/></p>
                                    <div class="">
                                        <div class="progress progress_sm" style="width: 76%;">
                                            <div class="progress-bar bg-green" role="progressbar"
                                                data-transitiongoal="80"></div>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <p>Total Female : <c:out value="${totalFemale}" default="" escapeXml="true"/></p>
                                    <div class="">
                                        <div class="progress progress_sm" style="width: 76%;">
                                            <div class="progress-bar bg-green" role="progressbar"
                                                data-transitiongoal="60"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> --%>
						<div class="clearfix"></div>
					</div>
				</div>
			</div>
			<br />
			<!-- end of weather widget -->
		</div>
	</div>
</div>

<!-- footer content -->

<jsp:include page="/WEB-INF/jsp/Admin/Footer.jsp"/>
<div id="custom_notifications" class="custom-notifications dsp_none">
	<ul class="list-unstyled notifications clearfix"
		data-tabbed_notifications="notif-group">
	</ul>
	<div class="clearfix"></div>
	<div id="notif-group" class="tabbed_notifications"></div>
</div>
<script
		src="${pageContext.request.contextPath}/resources/admin/js/bootstrap.min.js"></script>

<!-- gauge js -->
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/gauge/gauge.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/gauge/gauge_demo.js"></script>
<!-- bootstrap progress js -->
<script
		src="${pageContext.request.contextPath}/resources/admin/js/progressbar/bootstrap-progressbar.min.js"></script>
<script
		src="${pageContext.request.contextPath}/resources/admin/js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script
		src="${pageContext.request.contextPath}/resources/admin/js/icheck/icheck.min.js"></script>
<!-- daterangepicker -->
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/moment/moment.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/datepicker/daterangepicker.js"></script>
<!-- chart js -->
<script
		src="${pageContext.request.contextPath}/resources/admin/js/chartjs/chart.min.js"></script>

<script
		src="${pageContext.request.contextPath}/resources/admin/js/custom.js"></script>

<!-- flot js -->
<!--[if lte IE 8]><script type="text/javascript" src="js/excanvas.min.js"></script><![endif]-->
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/jquery.flot.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/jquery.flot.pie.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/jquery.flot.orderBars.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/jquery.flot.time.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/date.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/jquery.flot.spline.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/jquery.flot.stack.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/curvedLines.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/flot/jquery.flot.resize.js"></script>
<script>
	$(document)
			.ready(
					function() {
						// [17, 74, 6, 39, 20, 85, 7]
						//[82, 23, 66, 9, 99, 6, 2]
						console.log("Hello");
						console.log("<c:out value="${aa}" default="" escapeXml="true"/>");
						console.log("<c:out value="${bb}" default="" escapeXml="true"/>");
						console.log("<c:out value="${cc}" default="" escapeXml="true"/>");
						console.log("<c:out value="${dd}" default="" escapeXml="true"/>");
						console.log("<c:out value="${ee}" default="" escapeXml="true"/>");
						console.log("<c:out value="${ff}" default="" escapeXml="true"/>");
						console.log("<c:out value="${gg}" default="" escapeXml="true"/>");
						var data1 = [
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>"),
								"<c:out value="${gDto.aa}" escapeXml="true" default=""/>" ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 1),
								"<c:out value="${gDto.bb}" escapeXml="true" default=""/>" ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 2),
								"<c:out value="${gDto.cc}" escapeXml="true" default=""/>" ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 3),
								"<c:out value="${gDto.dd}" escapeXml="true" default=""/>" ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 4),
								"<c:out value="${gDto.ee}" escapeXml="true" default=""/>" ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 5),
								"<c:out value="${gDto.ff}" escapeXml="true" default=""/>" ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 6),
								"<c:out value="${gDto.gg}" escapeXml="true" default=""/>" ] ];

						var data2 = [
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>"), 00 ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 1), 00 ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 2), 00 ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 3), 00 ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 4), 00 ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 5), 00 ],
							[
								gd("<c:out value="${gDto.year}" default="" escapeXml="true"/>", "<c:out value="${gDto.month}" default="" escapeXml="true"/>",
										"<c:out value="${gDto.day}" escapeXml="true" default=""/>" - 6), 00 ] ];

						$("#canvas_dahs").length
						&& $
								.plot(
										$("#canvas_dahs"),
										[ data1, data2 ],
										{
											series : {
												lines : {
													show : false,
													fill : true
												},
												splines : {
													show : true,
													tension : 0.4,
													lineWidth : 1,
													fill : 0.4
												},
												points : {
													radius : 0,
													show : true
												},
												shadowSize : 2
											},
											grid : {
												verticalLines : true,
												hoverable : true,
												clickable : true,
												tickColor : "#d5d5d5",
												borderWidth : 1,
												color : '#fff'
											},
											colors : [ "#0182c4",
												"rgba(3, 88, 106, 0.38)" ],
											xaxis : {
												tickColor : "rgba(51, 51, 51, 0.06)",
												mode : "time",
												tickSize : [ 1,
													"day" ],
												//tickLength: 10,
												axisLabel : "Date",
												axisLabelUseCanvas : true,
												axisLabelFontSizePixels : 12,
												axisLabelFontFamily : 'Verdana, Arial',
												axisLabelPadding : 10
												//mode: "time", timeformat: "%m/%d/%y", minTickSize: [1, "day"]
											},
											yaxis : {
												ticks : 8,
												tickColor : "rgba(51, 51, 51, 0.06)",
											},
											tooltip : false
										});

						function gd(year, month, day) {
							return new Date(year, month - 1, day).getTime();
						}
					});
</script>

<!-- worldmap -->
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/maps/jquery-jvectormap-2.0.3.min.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/maps/gdp-data.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/maps/jquery-jvectormap-world-mill-en.js"></script>
<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/admin/js/maps/jquery-jvectormap-us-aea-en.js"></script>
<!-- pace -->
<script src="js/pace/pace.min.js"></script>
<script>
	$(function() {
		$('#world-map-gdp').vectorMap({
			map : 'world_mill_en',
			backgroundColor : 'transparent',
			zoomOnScroll : false,
			series : {
				regions : [ {
					values : gdpData,
					scale : [ '#E6F2F0', '#149B7E' ],
					normalizeFunction : 'polynomial'
				} ]
			},
			onRegionTipShow : function(e, el, code) {
				el.html(el.html() + ' (GDP - ' + gdpData[code] + ')');
			}
		});
	});
</script>
<!-- skycons -->
<script src="/resources/js/skycons/skycons.min.js"></script>
<script>
	var icons = new Skycons({
		"color" : "#73879C"
	}), list = [ "clear-day", "clear-night", "partly-cloudy-day",
		"partly-cloudy-night", "cloudy", "rain", "sleet", "snow",
		"wind", "fog" ], i;

	for (i = list.length; i--;)
		icons.set(list[i], list[i]);

	icons.play();
</script>

<!-- dashbord linegraph -->
<script>
	Chart.defaults.global.legend = {
		enabled : false
	};

	var data = {
		labels : [ "Symbian", "Blackberry", "Other", "Android", "IOS" ],
		datasets : [ {
			data : [ 15, 20, 30, 10, 30 ],
			backgroundColor : [ "#BDC3C7", "#9B59B6", "#455C73", "#26B99A",
				"#3498DB" ],
			hoverBackgroundColor : [ "#CFD4D8", "#B370CF", "#34495E",
				"#36CAAB", "#49A9EA" ]

		} ]
	};

	var canvasDoughnut = new Chart(document.getElementById("canvas1"), {
		type : 'doughnut',
		tooltipFillColor : "rgba(51, 51, 51, 0.55)",
		data : data
	});
</script>
<!-- /dashbord linegraph -->
<!-- datepicker -->
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						var cb = function(start, end, label) {
							console.log(start.toISOString(), end
									.toISOString(), label);
							$('#reportrange span').html(
									start.format('MMMM D, YYYY') + ' - '
									+ end.format('MMMM D, YYYY'));
							//alert("Callback has fired: [" + start.format('MMMM D, YYYY') + " to " + end.format('MMMM D, YYYY') + ", label = " + label + "]");
						}

						var optionSet1 = {
							startDate : moment().subtract(29, 'days'),
							endDate : moment(),
							minDate : '01/01/2012',
							maxDate : '12/31/2015',
							dateLimit : {
								days : 60
							},
							showDropdowns : true,
							showWeekNumbers : true,
							timePicker : false,
							timePickerIncrement : 1,
							timePicker12Hour : true,
							ranges : {
								'Today' : [ moment(), moment() ],
								'Yesterday' : [
									moment().subtract(1, 'days'),
									moment().subtract(1, 'days') ],
								'Last 7 Days' : [
									moment().subtract(6, 'days'),
									moment() ],
								'Last 30 Days' : [
									moment().subtract(29, 'days'),
									moment() ],
								'This Month' : [ moment().startOf('month'),
									moment().endOf('month') ],
								'Last Month' : [
									moment().subtract(1, 'month')
											.startOf('month'),
									moment().subtract(1, 'month')
											.endOf('month') ]
							},
							opens : 'left',
							buttonClasses : [ 'btn btn-default' ],
							applyClass : 'btn-small btn-primary',
							cancelClass : 'btn-small',
							format : 'MM/DD/YYYY',
							separator : ' to ',
							locale : {
								applyLabel : 'Submit',
								cancelLabel : 'Clear',
								fromLabel : 'From',
								toLabel : 'To',
								customRangeLabel : 'Custom',
								daysOfWeek : [ 'Su', 'Mo', 'Tu', 'We',
									'Th', 'Fr', 'Sa' ],
								monthNames : [ 'January', 'February',
									'March', 'April', 'May', 'June',
									'July', 'August', 'September',
									'October', 'November', 'December' ],
								firstDay : 1
							}
						};
						$('#reportrange span').html(
								moment().subtract(29, 'days').format(
										'MMMM D, YYYY')
								+ ' - '
								+ moment().format('MMMM D, YYYY'));
						$('#reportrange').daterangepicker(optionSet1, cb);
						$('#reportrange').on('show.daterangepicker',
								function() {
									console.log("show event fired");
								});
						$('#reportrange').on('hide.daterangepicker',
								function() {
									console.log("hide event fired");
								});
						$('#reportrange')
								.on(
										'apply.daterangepicker',
										function(ev, picker) {
											console
													.log("apply event fired, start/end dates are "
															+ picker.startDate
																	.format('MMMM D, YYYY')
															+ " to "
															+ picker.endDate
																	.format('MMMM D, YYYY'));
										});
						$('#reportrange').on('cancel.daterangepicker',
								function(ev, picker) {
									console.log("cancel event fired");
								});
						$('#options1').click(
								function() {
									$('#reportrange').data(
											'daterangepicker').setOptions(
											optionSet1, cb);
								});
						$('#options2').click(
								function() {
									$('#reportrange').data(
											'daterangepicker').setOptions(
											optionSet2, cb);
								});
						$('#destroy').click(
								function() {
									$('#reportrange').data(
											'daterangepicker').remove();
								});
					});
</script>
<script>
	NProgress.done();
</script>
<!-- /datepicker -->
<!-- /footer content -->
</body>

</html>
