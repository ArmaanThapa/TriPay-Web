<!DOCTYPE html>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicontripays, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>TriPay | Blocked Users</title>

<!-- Bootstrap core CSS -->
<link rel="icon" href='<c:url value="/resources/images/favicontripay.png"/>'
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
<script	src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Admin/jquery.twbsPagination.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Admin/jquery.twbsPagination.min.js"></script>
<script type="text/javascript" >

function fetchMe(value){
	//  var tag = $(this);
	//  var paging=document.getElementById('kick2').innerHTML;
	var paging=(value-1);
	
	console.log(paging);
	 // paging=tag.;
	console.log("inside function");
	
$.ajax({
type:"POST",
url:"${pageContext.request.contextPath}/Admin/BlockedUsers",
data:{page:paging,size:'20'},
dataType:"json",
success:function(data){
var trHTML='';
	if(trHTML==''){
	$(".testingg").empty();
	
	 $(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>UserName:' + data.jsonArray[i].username + '<br>Mobileno.:<a href="${pageContext.request.contextPath}/Admin/User'+data.jsonArray[i].contactNo+'">'+ data.jsonArray[i].contactNo+'</a>'+'<br>Email id:'+ data.jsonArray[i].email+'</td>'+'<td>'+data.jsonArray[i].authority+'</td><td>Balance:'+data.jsonArray[i].balance+'<br>points:'+data.jsonArray[i].points+'</td></tr>'});
	  $('#editedtable').append(trHTML);
	 
	 

	}
	else
	{ $(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>UserName:' + data.jsonArray[i].username + '<br>Mobileno.:<a href="${pageContext.request.contextPath}/Admin/User'+data.jsonArray[i].contactNo+'">'+ data.jsonArray[i].contactNo+'</a>'+'<br>Email id:'+ data.jsonArray[i].email+'</td>'+'<td>'+data.jsonArray[i].authority+'</td><td>Balance:'+data.jsonArray[i].balance+'<br>points:'+data.jsonArray[i].points+'</td></tr>'});
	  $('#editedtable').append(trHTML);
	}
	 
}

});
		 }
$(document).ready(function() {
	 var paging='0';
	 var size='20';
	 console.log("under ready...");
	 $.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/Admin/BlockedUsers",
			data:{page:paging,size:'20'},
		dataType:"json",
		success:function(data){
			var trHTML='';
				if(trHTML==''){
				$(".testingg").empty();
				$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>UserName:' + data.jsonArray[i].username + '<br>Mobileno.:<a href="${pageContext.request.contextPath}/Admin/User'+data.jsonArray[i].contactNo+'">'+ data.jsonArray[i].contactNo+'</a>'+'<br>Email id:'+ data.jsonArray[i].email+'</td>'+'<td>'+data.jsonArray[i].authority+'</td><td>Balance:'+data.jsonArray[i].balance+'<br>points:'+data.jsonArray[i].points+'</td></tr>'});
				  $('#editedtable').append(trHTML);
				 
				 
			
				}
				else
				{
					$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>UserName:' + data.jsonArray[i].username + '<br>Mobileno.:<a href="${pageContext.request.contextPath}/Admin/User'+data.jsonArray[i].contactNo+'">'+ data.jsonArray[i].contactNo+'</a>'+'<br>Email id:'+ data.jsonArray[i].email+'</td>'+'<td>'+data.jsonArray[i].authority+'</td><td>Balance:'+data.jsonArray[i].balance+'<br>points:'+data.jsonArray[i].points+'</td></tr>'});
					  $('#editedtable').append(trHTML);
					 
				}
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

				<jsp:include page="/WEB-INF/jsp/Admin/LeftMenu.jsp"/>
				<jsp:include page="/WEB-INF/jsp/Admin/TopNavigation.jsp"/>
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Blocked Users</h3>
						</div>
					</div>
					<div class="clearfix"></div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<ul class="nav navbar-right panel_toolbox">
										<li><a href="#"><i class="fa fa-chevron-up"></i></a></li>
										<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown" role="button" aria-expanded="false"><i
												class="fa fa-wrench"></i></a> <!-- <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Settings 1</a>
                        </li>
                        <li><a href="#">Settings 2</a>
                        </li>
                      </ul> --></li>
										<li><a href="#"><i class="fa fa-close"></i></a></li>
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<p class="text-muted font-13 m-b-30">
										<!--  DataTables has most features enabled by default, so all you need to do to use it with your own tables is to call the construction function: <code>$().DataTable();</code> -->
									</p>
									<table id="editedtable"
										class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>S.No</th>
												<th>User Account Details</th>
												<th>Authority</th>
												<th>Account</th>
												<!-- <th>Expense</th> -->
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


               <jsp:include page="/WEB-INF/jsp/Admin/Footer.jsp"/>
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
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/jquery.dataTables.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.bootstrap.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.buttons.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.bootstrap.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/jszip.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/pdfmake.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/vfs_fonts.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.html5.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.print.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.fixedHeader.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.keyTable.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.responsive.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/responsive.bootstrap.min.js"></script> --%>
<!-- 	<script -->
<%-- 		src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.scroller.min.js"></script> --%>


	<!-- pace -->
	<script
		src="${pageContext.request.contextPath}/resources/admin/js/pace/pace.min.js"></script>
		
<div class="container" align="center">

</div>

</body>

</html>
