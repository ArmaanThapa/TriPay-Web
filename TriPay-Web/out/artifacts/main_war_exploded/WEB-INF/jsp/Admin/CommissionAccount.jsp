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
<title>VPayQwik | Commission Report</title>

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

<!-- <script -->
<%-- 	src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script> --%>
<script	src="${pageContext.request.contextPath}/resources/admin/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Admin/jquery.twbsPagination.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/Admin/jquery.twbsPagination.min.js"></script>


	<script type="text/javascript">
// 	var flag=0;
// 	function searchIt(){
// 		console.log("inside search it");
// 		var num=document.getElementById("mobile").value;
// 		alert(num);
// 		$.ajax({
// 			type:"POST",
// 			url:"${pageContext.request.contextPath}/Admin/UserListOnSearch?search="+num,
// 			dataType:"json",
// 			success:function(data){
// 				var trHTML='';
// 				$(".testingg").empty();
// 				$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+i+'</td><td>UserName:' + data.jsonArray[i].username + '<br>Mobileno.:'+ data.jsonArray[i].contactNo+'<br>email id:'+ data.jsonArray[i].email+'</td>'+'<td>user type:'+ data.jsonArray[i].kycNonkyc+'<br>DOB:'+data.jsonArray[i].dob+'<br>Gender:'+data.jsonArray[i].gender+'<br>Authority:'+data.jsonArray[i].authority+'</td>'+'<td>Balance:'+data.jsonArray[i].balance+'<br>points:'+data.jsonArray[i].points+'</td>'+'<td>'+data.jsonArray[i].dateOfAccountCreation+'</td></tr>';});
// 				$('#editedtable').append(trHTML);
// 				console.log("search completed wit display");
// 			}
				
// 			});
		
// 	}	
// 		$(function () {
//         var obj = $('#paginationn').twbsPagination({
//             visiblePages: 10,
//             onPageClick: function (event, page) {
//                 console.info(page);
//             }
//         });
//     });
	
	 function fetchMe(value){
		//  var tag = $(this);
		//  var paging=document.getElementById('kick2').innerHTML;
		var paging=value;
		
		console.log(paging);
		 // paging=tag.;
		console.log("inside function");
		
	$.ajax({
	type:"POST",
	url:"${pageContext.request.contextPath}/Admin/CommissionReportInJSON",
	data:{page:paging,size:'20'},
	dataType:"json",
	success:function(data){
	var trHTML='';
		if(trHTML==''){
			
		$(".testingg").empty();
		$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>' + data.jsonArray[i].dateOfTransaction + '</td>'+'<td>'+ data.jsonArray[i].transactionRefNo+'</td>'+'<td>'+data.jsonArray[i].description+'</td>'+'<td>'+data.jsonArray[i].commission+'</td><td>'+data.jsonArray[i].status+'</td></tr>';});
		  $('#editedtable').append(trHTML);
		 
		 

		}
		else
		{
			$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>' + data.jsonArray[i].dateOfTransaction + '</td>'+'<td>'+ data.jsonArray[i].transactionRefNo+'</td>'+'<td>'+data.jsonArray[i].description+'</td>'+'<td>'+data.jsonArray[i].commission+'</td><td>'+data.jsonArray[i].status+'</td></tr>';});
			  $('#editedtable').append(trHTML);
		}
		 
	}

	});
			 }
	 $(document).ready(function() {
		 var paging='1';
		 var size='';
		 console.log("under ready...");
		 $.ajax({
				type:"POST",
				url:"${pageContext.request.contextPath}/Admin/CommissionReportInJSON",
				data:{page:paging,size:'20'},
			dataType:"json",
			success:function(data){
				var trHTML='';
					if(trHTML==''){
					$(".testingg").empty();
					$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>' + data.jsonArray[i].dateOfTransaction + '</td>'+'<td>'+ data.jsonArray[i].transactionRefNo+'</td>'+'<td>'+data.jsonArray[i].description+'</td>'+'<td>'+data.jsonArray[i].commission+'</td><td>'+data.jsonArray[i].status+'</td></tr>';});
					  $('#editedtable').append(trHTML);
					 
					 
				
					}
					else
					{
						$(data.jsonArray).each(function(i,item){trHTML += '<tr class="testingg"><td>'+(i+1)+'</td><td>' + data.jsonArray[i].dateOfTransaction + '</td>'+'<td>'+ data.jsonArray[i].transactionRefNo+'</td>'+'<td>'+data.jsonArray[i].description+'</td>'+'<td>'+data.jsonArray[i].commission+'</td><td>'+data.jsonArray[i].status+'</td></tr>';});
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

</head>
<body class="nav-md">
	<div class="se-pre-con"></div>
	<div class="container body">
		<div class="main_container">
			<jsp:include page="/WEB-INF/jsp/Admin/LeftMenu.jsp" />
			<jsp:include page="/WEB-INF/jsp/Admin/TopNavigation.jsp" />

			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>Commission Account Transactions</h3>
						</div>

					</div>
					<div class="clearfix"></div>

					<div class="row">

						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="x_panel">
								<div class="x_title">
									<!-- <h2>Button Example <small>Users</small></h2> -->
									
									</ul>
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<p class="text-muted font-13 m-b-30">
									<form action="<c:url value="/Admin/CommissionAccount"/>" method="post" class="form form-inline">
										From <input type="text" id="fromDate" name="startDate" class="form-control" readonly/>
										To <input type="text" id="toDate" name="endDate" class="form-control" readonly/>
										<sec:csrfInput/>
										<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-filter"></span></button>
									</form>
									<h4><c:out value="${msg}" escapeXml="true"/></h4>
									</p>
									<table id="editedtable"
										class="table table-striped table-bordered date_sorted">
										<thead>
											<tr>
												<th>S.No</th>
												<th>Transaction Date</th>
												<th>Transaction Ref No</th>
												<th>Transaction Type</th>
												<th>Total Commission</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody>
											
										</tbody>
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
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/jquery.dataTables.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.bootstrap.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.buttons.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.bootstrap.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/jszip.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/pdfmake.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/vfs_fonts.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.html5.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/buttons.print.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.fixedHeader.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.keyTable.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.responsive.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/responsive.bootstrap.min.js"></script> --%>
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/datatables/dataTables.scroller.min.js"></script> --%>
<!--     <script -->
<!--             src="//cdn.datatables.net/plug-ins/1.10.11/sorting/date-dd-MMM-yyyy.js"></script> -->

    <!-- pace -->
<!--     <script -->
<%--             src="${pageContext.request.contextPath}/resources/admin/js/pace/pace.min.js"></script> --%>

</body>
</html>
