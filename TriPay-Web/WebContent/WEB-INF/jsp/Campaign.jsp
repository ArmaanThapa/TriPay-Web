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
    <title>Campaign</title>


    <link rel="icon" href='<c:url value="/resources/images/favicon.png"/>' type="image/png" />
    <script
            src="<c:url value='/resources/js/jquery.js'/>"></script>





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
</nav><!-----------------end navbar---------------------->



<div class="container">
    <div class="container" id="aboutus">
        <div class="row">
            <h2>Terms &amp; Conditions</h2>
            <hr style="width: 120px; float: left; border: solid; margin-top: 0; margin-left: 10px; color: #17bcc8;">
            <img src="resources/images/main/T&c_2.jpg" class="img-responsive"
                 alt="">
            <div class="col-md-12">
                <!-- <h2>Terms &amp; Conditions:</h2> -->
                <hr>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> This Contest is subject to the rules and conditions determined by the Official application VPayQwik and relevant laws, and is not offered or valid to the activities outside the VPayQwik Mobile Application. </p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Employees of VPayQwik or VPayQwik, their associate agencies, agents, Payers, advertising and sales promotion agencies and members of the immediate families may participate in this campaign but will not be eligible to be selected as one of the winners.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> This Campaign will run for limited time period, and one lucky winner will be selected on last day.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> The Campaign will run for limited period (from given starting date).</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Each participant must download the application from play store or register at http://VPayQwik.com/ and sign up.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Participant must like VPayQwik Facebook page.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Participant must share the screenshot of their profile page of mobile application or web version of VPayQwik account at VPayQwik Facebook page.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Participant must perform one transaction of recharge, top up, bill payment, fund transfer or shopping (of any amount).</p>
                <hr>
                <h4><b>Conditions for Winners:</b></h4>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> The winner will be chosen through the lucky draw between the customers who has done at least one transaction. This Campaign will run for 11 days, and one lucky winner will be selected on last day.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> VPayQwik reserves the right to use the participant contact along with his/her photo for its promotion which may include promotion in media too. </p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> VPayQwik reserves the right to disqualify and remove any Participants from the Campaign without prior notification or breach of terms and conditions of their account with VPayQwik.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> The Prize shall neither be transferable nor exchangeable for cash or any items. VPayQwik reserves the absolute right to substitute the prize with that of similar value at any time with or without prior notice. </p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Prize is non-transferable (i.e. winner cannot transfer his prize to another person).</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> The decision of VPayQwik shall be final and conclusive. No appeal and/or correspondence will be entertained.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> The winner will be notified via phone number/mobile number and email provided by the participant and any other means of communication as determined by VPayQwik. This includes publishing on the web portal as well as http://www.facebook.com/VPayQwik page.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> VPayQwik will not be held liable in the event the winner does not receive this notification email for any reason whatsoever, failing which he/she will be disqualified and the next winner will be selected.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> The prize is accepted entirely at the risk of the winner, and VPayQwik excludes all warranties or representations whatsoever in connection with any prize to the extent permitted by law. VPayQwik does not assume any responsibility for the prizes offered under this campaign. </p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> By participating in the Campaign, Participants have expressly consented for their particulars to be posted in any method or medium that VPayQwik sees fit for the purpose of announcing the winners.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> Acceptance of the prizes constitutes the winnersí unconditional agreement to and permission to VPayQwik and its agencies to use the winner’a names, winner and his/her friends photograph and/or likeness for purposes of advertising, editorial, promotional, Marketing, trade and/or other purposes without further compensation and notice, unless prohibited by law. VPayQwik fully reserves its rights to such forms of publicity and publication.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> VPayQwik reserves the right to cancel, terminate or suspend this Campaign, in whole or in part at any time its sole and absolute discretion without notice and without liability or compensation to any participant or any other party. In the event of such cancellation, termination or suspension of this Campaign or if any Participant account is suspended or terminated by Online Portal, VPayQwik may at its sole and absolute discretion choose not to award the prize in respect of this Campaign or choose not to select any winner from all the qualified entries received prior to such cancellation, termination or suspension of this Campaign.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> VPayQwik reserves the rights to at any time add to, delete, vary or amend the Campaign, in whole or in part at any time and from time to time and at is sole discretion without notice and without liability to any users of this application or any other party. </p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> VPayQwik reserves the right to at any time add to, delete, vary or amend the Terms and Conditions herein or change or modify any aspect of this Campaign, in whole or in part at any time and from time to time and at its sole discretion without notice and without liability to any Participant or any other party.</p>
                <p><i class="fa fa-arrow-circle-right" aria-hidden="true"></i> VPayQwik and its affiliates (including its employees, consultants and agents) shall not be liable for any delays, interruptions, loss or damage whatsoever (indirect or consequential) incurred or suffered by any Participant or winner.</p>
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
                        <b>DataCard Recharges</b>
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