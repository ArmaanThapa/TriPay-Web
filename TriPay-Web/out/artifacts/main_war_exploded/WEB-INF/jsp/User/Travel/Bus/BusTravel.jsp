<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html >
<head>
<script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<sec:csrfMetaTags />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>VPayQwik | Redeem Coupon</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/font-awesome.min.css"/>">
<link href='<c:url value='/resources/css/font-family.css'/>'
	rel='stylesheet' type='text/css'>

<link rel="stylesheet"
	href="<c:url value='/resources/css/font-awesome.min.css'/>"
	type='text/css'>

<link rel="stylesheet"
	href="<c:url value='/resources/css/travel_css.css'/>" type='text/css'>

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap-theme.min.css'/>"
	type='text/css'>

<link href="<c:url value="/resources/css/css_style.css"/>"
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>"
	type='text/css'>
<script src="<c:url value='/resources/js/jquery.js'/>"></script>
<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>

<script src="/resources/js/Travel/travel.js"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/userdetails.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/header.js" />"></script>

<script type="text/javascript"
	src="<c:url value="/resources/js/aj.js" />"></script>

<link rel="icon" href='<c:url value="/resources/images/favicon.png"/>'
	type="image/png" />
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
<script>
 $(document).ready(function(){
  var date_input=$('input[name="date"]');
  var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
  date_input.datepicker({
   format: 'dd/mm/yyyy',
   container: container,
   todayHighlight: true,
   autoclose: true,
  })
 })
</script>

 
<link href='<c:url value="/resources/css/css_style.css"/>'
	rel='stylesheet' type='text/css'>

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


<script>
	function AvailableBusesgetdetailonewaybus() {

		$("#searchbusloaing").show();
		var returndate = document
				.getElementById("datepickerreturnewaydeparure").value;
		var date = document.getElementById("datepickeronewaydeparure").value;
		var citydest = document.getElementById("citydestoneway").value;
		var citysource = document.getElementById("citysourceoneway").value;

		$
				.ajax({
					type : "POST",
					contentType : "application/json",
					url : "/Api/v1/User/Website/en/ThirdParty/Travel/Buses/Getbusdeatail",
					data : JSON.stringify({
						"datepickeronewaydeparure" : "" + date + "",
						"citysourceoneway" : "" + citysource + "",
						"citydestoneway" : "" + citydest + "",
						"returnDate" : "" + returndate + "",

					}),
					//data : "date="+date+"&sourceId="+citysource +"&destinationId=" +citydest ,
					success : function(response) {
						alert("Hruda.........................................");
						/* alert(response); */
						window.location = "Bus/Allbusshowdetail";
					}
				});

	}

	function showone(busreturn) {
		$("#" + busreturn).hide();
	}
</script>



<body onload="busnameget()">


	<input type="hidden" class="string optional" id="user_id"
		value="${cityandid}" />
	<input type="hidden" class="string optional" id="user_city" value="" />
	<input type="hidden" class="string optional" id="sourceid"
		value="${citylist}" />
	<input type="hidden" class="string optional" id="user_city" value="" />

	<input type="hidden" class="string optional" id="user_cityflight"
		value="" />

	<input type="hidden" class="string optional" id="user_citycab" value="" />


	<div class="se-pre-con"></div>
	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />

	<!-----------------end navbar---------------------->

	<!------------- end main-------------------->

	<div class="background"></div>
	<!---blue box---->


<div class="container" id="bus">
          <div class="row"> 
          	 <div class="tab-content" id="bill_payment">
			 
                 	<div class="col-sm-2 col-sm-offset-1">    
                       <div class="group city">
                       <img src=<c:url value="/resources/images/cityscape.png"/> alt="">      
                       <select name="citysourceoneway" id="citysourceoneway" onchange="setdestination('citydestoneway','citysourceoneway')"	class=" foo">
							<option value="#">Select City</option>
					   </select>                                                    
                       </div>
                    </div>
                    <div class="col-sm-2">
                       <div class="group city">      
   	                     <img src=<c:url value="/resources/images/cityscape.png"/> alt="">
                          <select name="citydestoneway" id="citydestoneway" class=" foo">
								<option value="#">Select City</option>
						   </select>
                      </div>
                    </div>
                    <div class="col-sm-2">
                      <div class="group_2 city">      
                        <input class=" foo"	name="date" id="datepickeronewaydeparure" type="text" placeholder="Onward Date" required/>
                        <span class="highlight"></span>
                        <img src=<c:url value="/resources/images/calendar.png"/> alt="">
                        <span class="bar"></span>
                      </div>
                    </div>   
                    <div class="col-sm-2">
                      <div class="group_2 city">      
                        <input class=" foo" name="date" id="datepickerreturnewaydeparure" type="text" placeholder="Return Date *" required/>
                        <span class="highlight"></span>
                        <img src=<c:url value="/resources/images/calendar.png"/> alt="">
                        <span class="bar"></span>
                      </div>
                    </div>
                    <div class="col-sm-2">
                      <button type="submit" class="btn" style="width: 80%; background: #ff0000; color: #FFFFFF; border-radius: 0; height: 45px;" onclick="AvailableBusesgetdetailonewaybus()">SEARCH</button>
                    </div>                
                 </form>
            </div>            
          </div><!---end row-->
     </div>
       

 <!-- <div class="col-sm-3">
								<div class="form-group form-group-lg form-group-icon-left">
									<i class="fa fa-map-marker input-icon"></i> <label>From</label>
									<select name="citysourceoneway" id="citysourceoneway"
										onchange="setdestination('citydestoneway','citysourceoneway')"
										class="form-control">
										<option value="#">Select City</option>

									</select>
								</div>
							</div>
						 <div class="col-sm-3">
									<i class="fa fa-map-marker input-icon"></i> <label>To</label> <select
										name="citydestoneway" id="citydestoneway" class="form-control">
										<option value="#">Select City</option>

									</select>
								</div>
							</div>
						</div>
						 <div class="col-sm-3">
									<div class="form-group form-group-lg form-group-icon-left">
										<i class="fa fa-calendar input-icon input-icon-highlight"></i>
										<label>Departing</label> <input class="form-control"
											name="date" id="datepickeronewaydeparure" type="text" />
									</div>
								</div>

 <div class="col-sm-3">
						
									<div class="form-group form-group-lg form-group-icon-left">
										<i class="fa fa-calendar input-icon input-icon-highlight"></i>
										<label>Return</label> <input class="form-control" name="date"
											id="datepickerreturnewaydeparure" type="text" />
									</div>
								</div>

							</div>
						</div>


						<div class="alert" id="searchbusloaing" style="display: none;">
							<center>
						<img src="/resources/images/s.gif" class="img-circle"
							style="margin-top: 15px;" width="40" height="40">
					</center>
						</div>
						<button class="btn btn-primary btn-lg" type="button"
							onclick="AvailableBusesgetdetailonewaybus()">Search
							Buses</button>

						</form>
				</div>


			</div>
		</div>
		end tabble
	</div>
 -->	<!-------------end bus---------------->



	<!----end container-->
	<script src="<c:url value='/resources/js/bootstrap.js'/>"></script>


</body>
</html>
