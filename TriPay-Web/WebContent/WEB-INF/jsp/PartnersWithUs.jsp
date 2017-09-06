<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<sec:csrfMetaTags/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Partners with us</title>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>' type="image/png" />
<script
	src="<c:url value='/resources/js/jquery.min.js'/>"></script>

<link rel="stylesheet" href="<c:url value='/resources/css/css_style.css'/>" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/animate.min.css'/>">
<link href='<c:url value='/resources/css/font-family.min.css'/>'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>" type='text/css'>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>" type='text/css'>
<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>" type='text/css'>
<script
	src="<c:url value='/resources/js/bootstrap.js'/>"></script>

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

<body>
<div class="se-pre-con"></div>
<nav class="navbar navbar-default"
  style="background: #17bcc8; min-height: 80px; border-radius: 0; border-color: #17bcc8; margin-bottom: 0">
  <div class="container">
   <div class="navbar-header">
    <a class="navbar-brand" href="#"><img
     src="resources/images/toolbar_back1.png" alt=""></a>
   </div>
          </div><!-- /.container-fluid -->
        </nav>
        </nav><!-----------------end navbar---------------------->

     <div class="container">
       <div class="container" id="aboutus">
            <div class="row"> 
            <h2>Partner with us</h2>
             <hr style="width: 120px; float: left; border: solid; margin-top: 0; margin-left: 10px; color: #17bcc8;">
				<img src="resources/images/main/Partner.jpg" class="img-responsive"
					alt="">
				<div class="col-md-12">
					<!-- <h2>Partner with us</h2> -->
					<hr>
					<div class="container">
						<div class="col-md-5 col-md-offset-3" style="border: thick;">
							<center>
								<h4>GET IN TOUCH WITH US</h4>
								<hr>
							</center>
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="First name" />
								</div>
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Last name" />
								</div>
								<div class="form-group">
									<input type="email" class="form-control" placeholder="E-mail" />
								</div>
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Mobile No" />
								</div>
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="Select Location" />
								</div>
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="I'm interested in Partnering for" />
								</div>
								<div class="form-group">
									<textarea class="form-control" rows="4"
										placeholder="Please provide us some information on your interest"></textarea>
								</div>
								<center>
									<button type="button" class="btn btn-primary"
										style="width: 50%;">Submit</button>
								</center>
						</form>
						</div>
					</div>
				</div>
			</div>
			<!----end col-md-12------>
		</div>
		<!----end row----->
	</div>
	<!------end accolades-->
	</div>
	<!---------end About Us------------->









	<footer>
		<div class="help">
			<div class="container wow bounceIn" data-wow-duration="3s">
				<div class="row">
					<div class="col-sm-3">
						<img src="resources/images/main/help_1.png" alt="">
						<p>Help Line : 080 25535857/25505857 or care@vpayqwik.com </p>
					</div>

					<div class="col-sm-3">
						<img src="resources/images/main/help_2.png" alt="">
						<p>The security is our prime consern we ensure your money is
							secure</p>
					</div>

					<div class="col-sm-3">
						<img src="resources/images/main/help_3.png"
							style="padding-bottom: 13px;" alt="">
						<p>VPayQwik makes sure that your every transaction is 100% Safe</p>
					</div>

					<div class="col-sm-3">
						<img src="resources/images/main/help_4.png" alt="">
						<p>Spending is earning at VPayQwik We offer various deals to
							distribute happiness</p>
					</div>
				</div>
				<!---end row--->
			</div>
		</div>
		<!-----end help--->

		<div class="container">
			<div class="row">
				<!-- useful links -->
				<div class="col-sm-3 wow bounceInLeft" data-wow-duration="2s">
					<ul class="row footer-links">
						<p>
							<b>Mobile Recharges</b>
						</p>
						<a href="#">Airtel Recharge</a>|
						<a href="#">Vodafone Recharge</a>|
						<a href="#">Aircel Recharge</a>|
						<a href="#">Idea Recharge</a>|
						<a href="#">BSNL Recharge</a>|
						<a href="#">Reliance GSM</a>|
						<a href="#">Reliance CDMA</a>|
						<a href="#">Tata GSM</a>|
						<a href="#">Tata Docomo</a>|
						<a href="#">MTNL</a>|
						<a href="#">MTS</a>|
						<a href="#">T24 Mobile Recharge</a>
						</ul>
				</div>
				<div class="col-sm-3 wow bounceInLeft" data-wow-duration="2s">
					<ul class="row footer-links">
						<p>
							<b>DTH Recharges</b>
						</p>
						<a href="#">Airtel DTH Recharge</a>|
						<a href="#">Videocon D2H Recharge</a>|
						<a href="#">Tata Sky Recharge</a>|
						<a href="#">Reliance DTH Recharge</a>|
						<a href="#">Right Way</a>|
						<a href="#">Dish TV Recharge</a>|
						<a href="#">Sun Direct Recharge</a>
						</ul>
				</div>
				<div class="col-sm-3 wow bounceInRight" data-wow-duration="2s">
					<ul class="row footer-links">
						<p>
							<b>Datacard Recharges</b>
						</p>
						<a href="#">BSNL Datacard</a>|
						<a href="#">Reliance Netconnect</a>|
						<a href="#">Tata Photon</a>|
						<a href="#">Plus</a>|
						<a href="#">Tata Photon Whiz</a>|
						<a href="#">MTS Datacard</a>|
						<a href="#">MTS Blaze</a>|
						<a href="#">MTNL Datacard</a>
						</ul>
				</div>
				<div class="col-sm-3 wow bounceInRight" data-wow-duration="2s">
					<ul class="row footer-links">
						<p>
							<b>Landline Bill Payment</b>
						</p>
						<a href="#">BSNL Landline</a>|
						<a href="#">MTNL Landline Delhi</a>|
						<a href="#">Reliance</a>|
						<a href="#">Tata Docomo</a>
						</ul>
				</div>
			</div>
			<hr>
			<div class="row">
				<p>India's first complete payment application & website, VPayQwik
					is a quickest and safest way for Online Recharge, DTH or Datacard
					Recharge and make mobile or utility Bill Payments for Airtel,
					Aircel,BSNL, Docomo, Idea, MTNL, Vodafone or other operators.you do
					not need to rush to the market to make your DTH or mobile bill
					payments, just log-on to VPayQwik and experience the easiest &
					fastest method of recharges and payments.</p>
				<p>VPayQwik brings to you the various coupons, deals and offers
					to make your payment experience rich every day. Our online recharge
					and bill payment service give you reward points, which can be used
					to avail attractive and lucrative cash back and discount offers.
					Download VPayQwik from your App Store.</p>
				<p>VPayQwik also provides travel ticket service, through VPayQwik
					you can book Air tickes, Bus tickets, Hotels, Car Rental and
					Holiday Packages.Come and experience hassle-free, safe and fast
					ticketing services at VPayQwik.VPayQwik make sure that every
					transaction you do is risk free.</p>
			</div>
			<hr>
			<div class="row" id="footericons">
				<div class="col-sm-8  wow bounceInUp">
					<img src="resources/images/main/footer_1.png"
						class="img-responsive">
				</div>
				<!----end col-md-4--->

				<div class="col-sm-4  wow bounceInUp">
					<ul class="row footer-links">
						<a href="#"> <img src="resources/images/main/fb.gif"
							class="fb"></a>
						<a href="#"> <img src="resources/images/main/tw.gif"
							class="tw"></a>
						<a href="#"> <img src="resources/images/main/yu.gif"
							class="yu"></a>
						<a href="#"> <img src="resources/images/main/in.gif"
							class="in"></a>
					</ul>
				</div>
				<!----end col-md-4--->
				</center>

			</div>
			<!-----end row----->

		</div>
		<div class="menu" id="main">
		<div class="container">
			<div class="row" style="margin-top: 10px; margin-bottom: 10px;">
				<a href="Home">Home</a> <a href="AboutUs">About Us</a> <a
					href="PartnersWithUs">Partner with us</a> <a
					href="Terms&Conditions">Terms & Conditions</a> <a href="Grievance">Grievance
					policy</a>  <a href="faq">General
					Questions</a>
			</div>
			<!---row---->
		</div>
		<!----container------->
	</div>
	</footer>





</body>
</html>