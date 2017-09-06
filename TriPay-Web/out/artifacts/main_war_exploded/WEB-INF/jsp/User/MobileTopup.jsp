<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<sec:csrfMetaTags/>
<title>VPayQwik | Mobile Topup</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>">

	<link href='<c:url value='/resources/css/font-family.css'/>'
          rel='stylesheet' type='text/css'>

    <link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>
<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" type='text/css'>
<script
	src="<c:url value='/resources/js/jquery.js'/>"></script>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/header.js"/>"></script>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="<c:url value="/resources/js/topup.js"/>" ></script>
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

<body style="overflow-x: hidden;">
	<div class="se-pre-con"></div>
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->


	<div class="background"></div>

	<div class="container" id="box">
        <a data-toggle="modal" data-target="#order_confirmation"></a>
        <div class="row">

			<div class="col-md-4" id="Prepaid">
				<!---form---->

				<div class="box hidden-xs"></div>

				<ul class="nav nav-pills">

					<li id="PrepaidSubMenu" class="active"><a data-toggle="pill"
						href="#PrepaidFadeIn"> <i class="fa fa-mobile"></i> <span>Prepaid</span></a></li>
					<li id="PostpaidSubMenu"><a data-toggle="pill"
						href="#PostpaidFadeIn"> <i class="fa fa-mobile"></i> <span>Postpaid</span></a></li>
					<li id="DataCardSubMenu"><a data-toggle="pill"
						href="#DataCardFadeIn"><i class="fa fa-rocket"></i> <span>DataCard</span></a></li>
				</ul>

				<div class="tab-content">
                    <span id="error_pre_topup" class="label label-danger"></span>
                    <span id="success_pre_topup" class="label label-success"></span>

					<br><br><div id="PrepaidFadeIn" class="tab-pane fade in active">

						<form action="#" method="post">

							<div class="group_1">
								<input type="text" name="mobileNo" id="pre_mobile"
									 required> <span
									class="highlight"></span><span class="bar"></span>
								<p id="error_pre_mobile" class="error"></p>
								<label>Prepaid Mobile No</label>
								<a data-toggle="modal" data-target="#order_confirmation"></a>
							</div>

							<div class="group_1">
							<select name="serviceProvider" id="pre_operator"
									class="form-control" style="width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none; border-radius: 0;">
									<option value="#">Select Operator</option>
									<option value="VACP">Aircel</option>
									<option value="VATP">Airtel</option>
									<option value="VBVP">BSNL - Special Tariff</option>
									<option value="VBGP">BSNL</option>
									<option value="VIDP">Idea</option>
									<option value="VMSP">MTNL - Special Tariff</option>
									<option value="VMMP">MTNL</option>
									<option value="VMTP">MTS</option>
									<option value="VRGP">Reliance</option>
									<option value="VTVP">T24 Mobile - Special Tariff</option>
									<option value="VTMP">T24 Mobile</option>
									<option value="VTCP">Tata Docomo CDMA</option>
									<option value="VTSP">Tata Docomo GSM - Special Tariff</option>
									<option value="VTGP">Tata Docomo GSM</option>
									<option value="VUSP">Telenor - Special Tariff</option>
									<option value="VUGP">Telenor</option>
									<option value="VVSP">Videocon - Special Tariff</option>
									<option value="VVGP">Videocon</option>
									<option value="VVFP">Vodafone</option>
								</select>
								<p id="error_pre_operator" class="error"></p>
							</div>

							<div class="group_1">
								<select name="serviceProvider" id="pre_circle"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Area</option>
									<option value="AP">Andhra Pradesh</option>
									<option value="AS">Assam</option>
									<option value="BR">Bihar and Jharkhand</option>
									<option value="CH">Chennai</option>
									<option value="DL">Delhi</option>
									<option value="GJ">Gujarat</option>
									<option value="HR">Haryana</option>
									<option value="HP">Himachal Pradesh</option>
									<option value="JK">Jammu and Kashmir</option>
									<option value="KN">Karnataka</option>
									<option value="KL">Kerala</option>
									<option value="KO">Kolkata</option>
									<option value="MP">Madhya Pradesh/Chattisgarh</option>
									<option value="MH">Maharashtra</option>
									<option value="MU">Mumbai</option>
									<option value="NE">North East</option>
									<option value="OR">Orissa</option>
									<option value="PB">Punjab</option>
									<option value="RJ">Rajasthan</option>
									<option value="TN">Tamil Nadu</option>
									<option value="UW">Uttar Pradesh(W)/Uttranchal</option>
									<option value="UE">Uttar Pradesh(E)</option>
									<option value="WB">West Bengal</option>
								</select>
								<div id="plan_link" style="text-align: -webkit-right; margin-left: 200px;"></div>
								<p id="error_pre_circle" class="error"></p>

							</div>
							
							<div class="group_1">
								<input type="number" id="pre_amount" name="amount" class="numeric" min="10"
									 required> <span
									class="highlight"></span> <span class="bar"></span> <label>Enter
									Amount</label>
									<p id="error_pre_amount" class="error"></p>
							</div>

							<button type="button" class="btn" id="pre_submit" 
								style="width: 80%; background: #ff0000; color: #FFFFFF;">Pay</button>
						</form>
					</div><!-- end prepaid -->
                    <span id="error_post_topup"  class="label label-danger"></span>
                    <span id="success_post_topup"  class="label label-success"></span>
					<div id="PostpaidFadeIn" class="tab-pane fade">
						<form method="post"	action="#">

							<div class="group_1">
								<input type="text" name="mobileNo" 	id="post_mobile" required> <span class="highlight"></span><span class="bar"></span>
								<p id="error_post_mobile" class="error"></p>
								<label>Postpaid Mobile No</label>
							</div>

							<div class="group_1">
								<select name="serviceProvider" id="post_operator"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Operator</option>
									<option value="VACC">Aircel</option>
									<option value="VATC">Airtel</option>
									<option value="VBGC">BSNL</option>
									<option value="VIDC">Idea</option>
									<option value="VMTC">MTS</option>
									<option value="VRGC">Reliance</option>
									<option value="VTDC">Tata Docomo</option>
									<option value="VVFC">Vodafone</option>
								</select>
								<p id="error_post_operator" class="error"></p>
							</div>
							<div class="group_1">
								<select name="serviceProvider" id="post_circle"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="">Select Area</option>
									<option value="AP">Andhra Pradesh</option>
									<option value="AS">Assam</option>
									<option value="BR">Bihar and Jharkhand</option>
									<option value="CH">Chennai</option>
									<option value="DL">Delhi</option>
									<option value="GJ">Gujarat</option>
									<option value="HR">Haryana</option>
									<option value="HP">Himachal Pradesh</option>
									<option value="JK">Jammu and Kashmir</option>
									<option value="KN">Karnataka</option>
									<option value="KL">Kerala</option>
									<option value="KO">Kolkata</option>
									<option value="MP">Madhya Pradesh/Chattisgarh</option>
									<option value="MH">Maharashtra</option>
									<option value="MU">Mumbai</option>
									<option value="NE">North East</option>
									<option value="OR">Orissa</option>
									<option value="PB">Punjab</option>
									<option value="RJ">Rajasthan</option>
									<option value="TN">Tamil Nadu</option>
									<option value="UW">Uttar Pradesh(W)/Uttranchal</option>
									<option value="UE">Uttar Pradesh(E)</option>
									<option value="WB">West Bengal</option>
								</select>
								<p id="error_post_circle" class="error"></p>
							</div>

							<div class="group_1">
								<input type="number" id="post_amount" name="amount" class="numeric" min="10"
									 required> <span
									class="highlight"></span> <label>Enter Amount</label>
								<p id="error_post_amount" class="error"></p>
							</div>
							<button type="button" id="post_submit" class="btn"
								style="width: 80%; background: #ff0000;  color: #FFFFFF;">Pay</button>
						</form>
					</div>



                    <span id="error_dc_topup" class="label label-danger"></span>
                    <span class="success_dc_topup" class="label label-success"></span>

					<div id="DataCardFadeIn" class="tab-pane fade">

						<form action="#" method="post">
							
					        <div class="group_1">
                                <input type="text" name="mobileNo" 	id="dc_mobile" required> <span class="highlight"></span><span class="bar"></span>
                                <p id="error_dc_mobile" class="error"></p>
                                <label>Mobile No</label>
                            </div>



                            <div class="group_1">
								<select name="serviceProvider" id="dc_operator"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Operator</option>
									<option value="VACP">Aircel</option>
									<option value="VATP">Airtel</option>
									<option value="VBVP">BSNL - Special Tariff</option>
									<option value="VBGP">BSNL</option>
									<option value="VIDP">Idea</option>
									<option value="VMSP">MTNL - Special Tariff</option>
									<option value="VMMP">MTNL</option>
									<option value="VMTP">MTS</option>
									<option value="VRGP">Reliance</option>
									<option value="VTVP">T24 Mobile - Special Tariff</option>
									<option value="VTMP">T24 Mobile</option>
									<option value="VTCP">Tata Docomo CDMA</option>
									<option value="VTSP">Tata Docomo GSM - Special Tariff</option>
									<option value="VTGP">Tata Docomo GSM</option>
									<option value="VUSP">Telenor - Special Tariff</option>
									<option value="VUGP">Telenor</option>
									<option value="VVSP">Videocon - Special Tariff</option>
									<option value="VVGP">Videocon</option>
									<option value="VVFP">Vodafone</option>
								</select>
								<p id="error_dc_operator" class="error"></p>
							</div>

							<div class="group_1">
								<select name="serviceProvider" id="dc_circle"
									class="form-control" style="border-radius: 0; width: 86%; border: transparent;border-bottom: gray;border-style: solid;border-width: 1.8px; font-family: 'Ubuntu', sans-serif; padding-left: 0; height: 55px; margin-top: -19px; font-weight: bold; color: #928F8F; box-shadow: none;">
									<option value="#">Select Area</option>
									<option value="AP">Andhra Pradesh</option>
									<option value="AS">Assam</option>
									<option value="BR">Bihar and Jharkhand</option>
									<option value="CH">Chennai</option>
									<option value="DL">Delhi</option>
									<option value="GJ">Gujarat</option>
									<option value="HR">Haryana</option>
									<option value="HP">Himachal Pradesh</option>
									<option value="JK">Jammu and Kashmir</option>
									<option value="KN">Karnataka</option>
									<option value="KL">Kerala</option>
									<option value="KO">Kolkata</option>
									<option value="MP">Madhya Pradesh/Chattisgarh</option>
									<option value="MH">Maharashtra</option>
									<option value="MU">Mumbai</option>
									<option value="NE">North East</option>
									<option value="OR">Orissa</option>
									<option value="PB">Punjab</option>
									<option value="RJ">Rajasthan</option>
									<option value="TN">Tamil Nadu</option>
									<option value="UW">Uttar Pradesh(W)/Uttranchal</option>
									<option value="UE">Uttar Pradesh(E)</option>
									<option value="WB">West Bengal</option>
				
								</select>
								<div id="plan_link_dc" style="margin-right: 150px;font-size: 12px;"></div>
								<p id="error_dc_circle" class="error"></p>
							</div>

							<div class="group_1">
								<input type="number" name="amount"  id="dc_amount" min="10" class="numeric" required> <span
									class="highlight"></span> <span class="bar"></span> <label>Enter
									Amount</label>
								<p id="error_dc_amount" class="error"></p>
							</div>


							<button type="button" class="btn" id="dc_submit"
								style="width: 80%; background: #ff0000;  color: #FFFFFF;">Pay</button>
						</form>
					</div>
				</div>

			</div>
			<!----end col-md-4-->


			<div class="col-md-8 hidden-xs">
				<div class="slider" id="slider"
					style="margin-right: -15px; margin-left: -15px;">
					<div class="carousel slide hidden-xs" data-ride="carousel"
						id="mycarousel">
						<ol class="carousel-indicators">
							<li class="active" data-slide-to="0" data-target="#mycarousel"></li>
							<li data-slide-to="1" data-target="#mycarousel"></li>
						</ol>

						<div class="carousel-inner">

							<div class="item active" id="slide1">

								<img src='<c:url value="/resources/images/slider_5.jpg"/>' />
							</div>
							<!---end item---->

							<div class="item">
								<img src='<c:url value="/resources/images/slider_6.jpg"/>'>
							</div>

							<!---end item---->
						</div>
						<!--end carousel inner------>

					</div>

					<!---end caeousel slider---->
				</div>
				<!---end slider----->
			</div>
		</div>
		<!-----end col-md-8-->
	</div>
	<!---end row-->
	<jsp:include page="/WEB-INF/jsp/User/Footer.jsp" />
	<div id="order_confirmation" class="modal fade" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title">Order Confirmation</h5>
				</div>
				<div class="modal-body">
						<table class="table table-condensed">
							<tr>
						<td><b>Topup </b> </td> <td id="o_topup"> </td>
						</tr>
                            <tr>
                                <td><b>Mobile </b> </td> <td id="o_mobile"> </td>
                            </tr>

                            <tr>
								<td><b>Operator </b> </td> <td id="o_operator"> </td>
							</tr>
							<tr>
								<td><b>Area </b> </td> <td id="o_area"></td>
							</tr>
							<tr>
								<td><b>Amount</b> </td> <td id="o_amount"></td>
							</tr>


						</table>
					<button type="button" class="btn btn-block btn-info" id="confirm_order">Confirm</button>
				</div>
			</div>
		</div>
	</div>
	<div id="browse_plans_modal" role="dialog" class="modal fade">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h2><b>Choose Plan</b></h2>
				</div>
				<div class="modal-body" id="browse_plans_body">
 			<div class="row">
 			<div class="col-md-12">
 			<ul class="nav nav-tabs">
    			<li class="active"><a data-toggle="tab" href="#3g" class="size">3G</a></li>
    			<li><a data-toggle="tab" href="#2g" class="size">2G</a></li>
 	  			<li><a data-toggle="tab" href="#special" class="size">Special</a></li>
 	  			<li><a data-toggle="tab" href="#regular" class="size">Regular</a></li>
  			</ul>
  			</div>
			</div>
          <div class="tab-content">
    			<div id="3g" class="tab-pane fade in active">
    				<table id="3g_plans" class="table table-hover">
    				</table>
    			</div>
    			<div id="2g" class="tab-pane fade">
    				<table id="2g_plans" class="table table-hover">
    				</table>
    			</div>
    			<div id="special" class="tab-pane fade">
    				<table id="special_plans" class="table table-hover">
    				</table>
    			</div>
    			<div id="regular" class="tab-pane fade">
    			    <table id="regular_plans" class="table table-hover">
    				</table>
    			</div>
    			

  </div>							
							
											
				</div>
			</div>
		</div>
	</div>

    // modal  for data card plans

    <div id="browse_dc_plans_modal" role="dialog" class="modal fade">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h2><b>Choose Plan</b></h2>
                </div>
                <div class="modal-body" id="browse_dc_plans_body">
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="nav nav-tabs">
                                <li class="active"><a data-toggle="tab" href="#3g_dc" class="size">3G</a></li>
                                <li><a data-toggle="tab" href="#2g_dc" class="size">2G</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="tab-content">
                        <div id="3g_dc" class="tab-pane fade in active">
                            <table id="3g_dc_plans" class="table table-hover">
                            </table>
                        </div>
                        <div id="2g_dc" class="tab-pane fade">
                            <table id="2g_dc_plans" class="table table-hover">
                            </table>
                        </div>


                    </div>


                </div>
            </div>
        </div>
    </div>

	<script src="http://code.jquery.com/jquery-2.2.1.min.js"></script>
	<script type="text/javascript"
		src='<c:url value="/resources/js/wow.js"/>' />
	<script>
		new WOW().init();
	</script>
	<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>
	
	<!-- Latest compiled and minified JavaScript -->
</body>
</html>
