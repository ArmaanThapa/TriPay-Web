<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<sec:csrfMetaTags/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>About Us</title>
<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
	<script src="<c:url value='/resources/js/jquery.min.js'/>"></script>
	
<link rel="stylesheet"
	href="<c:url value='/resources/css/css_style.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/animate.min.css'/>">
<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'/>


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
		style="min-height: 80px; margin-bottom: 0">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#"><img
					src="resources/images/vijayalogo.png" alt="logo" style="width: 230px; margin-top: 8px;" ></a>
			</div>	 	
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div class="line" style="height: 4px; background: #17bcc8;"></div>
	<!-----------------end navbar---------------------->
	<div class="container">
		<div class="container" id="aboutus">
			<div class="row">
				<h2>About Us</h2>
				<hr
					style="width: 120px; float: left; border: solid; margin-top: 0; margin-left: 10px; color: #17bcc8;">
				<img src="resources/images/main/aboutus.jpg" class="img-responsive"
					alt="">
				<div class="col-md-12">
					<hr>
					<p>VPayQwik is one of India's best mobile commerce platform.
						Started to offer quick mobile recharge and utility bill payments,
						VPayQwik has pioneered as a full online marketplace with range of
						products to pick from. VPayQwik strives to provide fully-secure
						platform to the consumers for instant fund transfer across India.
						We have developed a platform which we use ourselves, and love it
						absolutely! With VPayQwik, users are now able to recharge all
						prepaid or postpaid mobile bills across India, make bill payments
						to landline & electricity providers, purchase tickets for bus,
						airline, and movie easily and shop for latest products right on
						their mobile phones</p>
					<p>Just launching a customized platform isn't sufficient, we
						rigorously endeavor to deliver credible, evolving, expedient and
						handy product. That's why we adhere to improve on customer's
						feedbacks and reflect the same in design. Plain yet tech-savvy,
						our website and mobile app will guide you in your every necessity.
						Be it lagging behind in the traffic logjam or waiting in a long
						queue, payments are always weary and cumbersome. With amazing
						mobile of app of VPayQwik, you will be able to pay or transfer
						amount anytime from your mobile phone. That too without any
						hassle!</p>
					<p>Not only that with our fastest checkout options, we offer
						multiple payment transactions which are fully safe and secure. By
						choosing VPayQwik, you will be bestowed upon with exclusive
						discounts, exciting coupons and attractive cashback offers while
						shopping on our partner sites. Check out our website to know more
						about partner sites. VPayQwik is totally safe. In addition to
						payments or transactions, VPayQwik is one-stop destination to shop
						for retail, fashion, electronics and other products online.
						Everything you are looking for is available right with VPayQwik,
						shopping online was never so easy.</p>
					<p>It's nothing short than a joy ride for our customers, which
						is irresistible yet easy on wallets. Download our mobile app from
						Google Play Store today. Get going with the best mobile commerce,
						exciting offers and exclusive coupons are just a click away.</p>
				</div>
				<!----end col-md-12------>
			</div>
			<!----end row----->
			<hr>
		</div>
		<!------end accolades-->

<%--		<div class="row">
			<div class="Accolades">
				<h1>Accolades</h1>
				<img src="resources/images/main/mobile.jpg" class="img-responsive"
					alt="chart">
			</div>
		</div>--%>
		<!---end row-->

	</div>
	<!---------end About Us------------->






	</div>



	<footer>
		<div class="help">
			<div class="container wow bounceIn" data-wow-duration="3s">
				<div class="row">
					<div class="col-sm-3">
						<img src="resources/images/main/help_1.png" alt="">
						<p>Help Line : 080 25535857/25505857 or care@vpayqwik.com</p>
					</div>

					<div class="col-sm-3">
						<img src="resources/images/main/help_2.png" alt="">
						<p>The security is our prime consern we ensure your money is
							secure</p>
					</div>

					<div class="col-sm-3">
						<img src="resources/images/main/help_3.png"
							style="padding-bottom: 13px;" alt="">
						<p>VPayQwik makes sure that your every transaction is 100%
							Safe</p>
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
				<p>India's first complete payment application & website,
					VPayQwik is a quickest and safest way for Online Recharge, DTH or
					Datacard Recharge and make mobile or utility Bill Payments for
					Airtel, Aircel,BSNL, Docomo, Idea, MTNL, Vodafone or other
					operators.you do not need to rush to the market to make your DTH or
					mobile bill payments, just log-on to VPayQwik and experience the
					easiest & fastest method of recharges and payments.</p>
				<p>VPayQwik brings to you the various coupons, deals and offers
					to make your payment experience rich every day. Our online recharge
					and bill payment service give you reward points, which can be used
					to avail attractive and lucrative cash back and discount offers.
					Download VPayQwik from your App Store.</p>
				<p>VPayQwik also provides travel ticket service, through
					VPayQwik you can book Air tickes, Bus tickets, Hotels, Car Rental
					and Holiday Packages.Come and experience hassle-free, safe and fast
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
						policy</a>
				</div>
				<!---row---->
			</div>
			<!----container------->
		</div>
	</footer>

</body>
</html>