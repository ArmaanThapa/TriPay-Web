<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <sec:csrfMetaTags />
    <title>${user.firstName} | Transactions Report</title>

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
    <link
            href="${pageContext.request.contextPath}/resources/admin/css/icheck/flat/green.css"
            rel="stylesheet">

    <link
            href="${pageContext.request.contextPath}/resources/admin/js/datatables/jquery.dataTables.min.css"
            rel="stylesheet" type="text/css" />
    <link
            href="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.bootstrap.min.css"
            rel="stylesheet" type="text/css" />
    <link
            href="${pageContext.request.contextPath}/resources/admin/js/datatables/fixedHeader.bootstrap.min.css"
            rel="stylesheet" type="text/css" />
    <link
            href="${pageContext.request.contextPath}/resources/admin/js/datatables/responsive.bootstrap.min.css"
            rel="stylesheet" type="text/css" />
    <link
            href="${pageContext.request.contextPath}/resources/admin/js/datatables/scroller.bootstrap.min.css"
            rel="stylesheet" type="text/css" />

    <script
            src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script>
            <script	src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Admin/jquery.twbsPagination.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Admin/jquery.twbsPagination.min.js"></script>
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
    <link rel="stylesheet" href="<c:url value="/resources/css/datepicker.css"/>">
    <script src="<c:url value="/resources/js/datepicker.js"/>"></script>
    <script>
        $(function() {
            $( "#toDate" ).datepicker({
                format:"yyyy-mm-dd"
            });
            $( "#fromDate" ).datepicker({
                format:"yyyy-mm-dd"
            });
        });
    </script>

    <script type="text/javascript">
        $(window).load(function() {
            $(".se-pre-con").fadeOut("slow");
        });
    </script>
<script type="text/javascript">
function fetchMe(value){
		//  var tag = $(this);
		//  var paging=document.getElementById('kick2').innerHTML;
		var paging=value;
		
		console.log(paging);
		 // paging=tag.;
		console.log("inside function");
		
	$.ajax({
	type:"POST",
	url:"${pageContext.request.contextPath}/Merchant/MerchantTransactionInJSON",
	data:{page:paging,size:'20'},
	dataType:"json",
	success:function(data){
		console.log(data);
	var trHTML='';
		
		$(".testingg").empty();
		
		$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>' + data.jsonArray[i].date + '</td><td>'+ data.jsonArray[i].contactNo+'<br>'+ data.jsonArray[i].email+'</td>'+'<td>'+ data.jsonArray[i].transactionRefNo+'</td><td>'+data.jsonArray[i].amount+'</td><td>'+data.jsonArray[i].status+'</td></tr>';});
		  $('#editedtable').append(trHTML);
		 
		 

		}
		
		 
	

	});
			 }
	 $(document).ready(function() {
		 var paging='0';
		 var size='';
		 console.log("under ready...");
		 $.ajax({
				type:"POST",
				url:"${pageContext.request.contextPath}/Merchant/MerchantTransactionInJSON",
				data:{page:paging,size:'20'},
			dataType:"json",
			success:function(data){
				var trHTML='';
					
					$(".testingg").empty();

					$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>' + data.jsonArray[i].date + '</td><td>'+ data.jsonArray[i].contactNo+'<br>'+ data.jsonArray[i].email+'</td>'+'<td>'+ data.jsonArray[i].transactionRefNo+'</td><td>'+data.jsonArray[i].amount+'</td><td>'+data.jsonArray[i].status+'</td></tr>';});
					  $('#editedtable').append(trHTML);
					 
					 
					
					
					 $(function () {
							console.log("inside funt...");
						 $('#paginationn').twbsPagination({
							 totalPages: data.totalPages,
							 visiblePages: 10,
				         onPageClick: function (event, page) {
				        	 fetchMe(page);
						
				         }
						 });
						});
					 
			}
		 });
		
		
	 });
	 
	 
	
	
</script>	
</head>
<body class="nav-md">
<div class="se-pre-con"></div>
<div class="container body">
    <div class="main_container">
        <jsp:include page="/WEB-INF/jsp/Merchant/LeftMenu.jsp" />
        <jsp:include page="/WEB-INF/jsp/Merchant/TopNavigation.jsp" />



        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">
                <div class="page-title">
                    <div class="title_left">
                        <h3>VPayQwik Users</h3>
                    </div>

                </div>
                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">
                            <div class="x_title">
                                <!--  <h2>Button Example <small>Users</small></h2> -->
                                <ul class="nav navbar-right panel_toolbox">
                                </ul>
                                <div class="clearfix"></div>
                            </div>
                            <div class="x_content">
                                <p class="text-muted font-13 m-b-30">
                                    <!--                       The Buttons extension for DataTables provides a common set of options, API methods and styling to display buttons on a page that will interact with a DataTable. The core library provides the based framework upon which plug-ins can built.
-->
<%-- <form action="<c:url value="/Merchant/TransactionFiltered"/>" method="post" class="form form-inline"> --%>
<!--                                 From <input type="text" id="fromDate" name="startDate" class="form-control" readonly/>  -->
<!--                                  To <input type="text" id="toDate" name="endDate" class="form-control" readonly/>  -->
<%--                                 <sec:csrfInput/> --%>
<!--                                 <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-filter"></span></button>  -->
<!--                             </form>  -->
                                </p>
                                <table id="editedtable"
                                       class="table table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th>S.No</th>
                                        <th>Transaction Date</th>
                                        <th>User Details</th>
                                        <th>Transaction ID</th>
                                        <th>Amount</th>
                                        <th>Status</th>
                                    </tr>
                                    </thead>
                                   

                                </table>
                            </div>
                        </div>
                    </div>
<nav>
							<ul class="pagination" id="paginationn">
							  </ul>
							  </nav>
                </div>
            </div>


            <jsp:include page="/WEB-INF/jsp/Admin/Footer.jsp" />
        </div>
        <!-- /page content -->
    </div>

</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix"
        data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
</div>

<script
        src="${pageContext.request.contextPath}/resources/admin/js/bootstrap.min.js"></script>

<!-- bootstrap progress js -->
<script
        src="${pageContext.request.contextPath}/resources/admin/js/progressbar/bootstrap-progressbar.min.js"></script>
<script
        src="${pageContext.request.contextPath}/resources/admin/js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script
        src="${pageContext.request.contextPath}/resources/admin/js/icheck/icheck.min.js"></script>

<script
        src="${pageContext.request.contextPath}/resources/admin/js/custom.js"></script>


<!-- Datatables -->
<!-- <script src="js/datatables/js/jquery.dataTables.js"></script>
<script src="js/datatables/tools/js/dataTables.tableTools.js"></script> -->

<!-- Datatables-->
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/jquery.dataTables.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.bootstrap.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.buttons.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.bootstrap.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/jszip.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/pdfmake.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/vfs_fonts.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.html5.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.print.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.fixedHeader.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.keyTable.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.responsive.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/responsive.bootstrap.min.js"></script> --%>
<!-- <script -->
<%--         src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.scroller.min.js"></script> --%>


<!-- pace -->
<script
        src="${pageContext.request.contextPath}/resources/admin/js/pace/pace.min.js"></script>
<script>
    var handleDataTableButtons = function() {
        "use strict";
        0 !== $("#datatable-buttons").length
        && $("#datatable-buttons").DataTable({
            dom : "Bfrtip",
            buttons : [ {
                extend : "copy",
                className : "btn-sm"
            }, {
                extend : "csv",
                className : "btn-sm"
            }, {
                extend : "excel",
                className : "btn-sm"
            }, {
                extend : "pdf",
                className : "btn-sm"
            }, {
                extend : "print",
                className : "btn-sm"
            } ],
            responsive : !0
        })
    }, TableManageButtons = function() {
        "use strict";
        return {
            init : function() {
                handleDataTableButtons()
            }
        }
    }();
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $('#datatable').dataTable();
        $('#datatable-keytable').DataTable({
            keys : true
        });
        $('#datatable-responsive').DataTable();
        $('#datatable-scroller').DataTable({
            ajax : "js/datatables/json/scroller-demo.json",
            deferRender : true,
            scrollY : 380,
            scrollCollapse : true,
            scroller : true
        });
        var table = $('#datatable-fixed-header').DataTable({
            fixedHeader : true
        });
    });
    TableManageButtons.init();
</script>
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


</body>
</html>
