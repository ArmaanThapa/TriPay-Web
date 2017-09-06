<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false"%>
<%@ page import="org.springframework.web.util.HtmlUtils"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<!-- <link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/styles.css">
<link rel="stylesheet" href="/resources/css/travel_css.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	    <script src="https://code.jquery.com/jquery-3.1.0.min.js"></script> -->
	    
	    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.1/animate.min.css">
<link href='https://fonts.googleapis.com/css?family=Ubuntu' rel='stylesheet' type='text/css'>
<!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">-->
<link rel="stylesheet" href="/resources/css/styles.css">
<link rel="stylesheet" href="/resources/css/travel_css.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

   <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
 <link rel="stylesheet" href="<c:url value="/resources/css/travel_css.css"/>">
 <link rel="stylesheet" href="<c:url value="/resources/css/css_style.css"/>">
    <script type="text/javascript"
	src="<c:url value="/resources/js/userdetails.js"/>"></script> 
     <script type="text/javascript"
	src="<c:url value="/resources/js/header.js" />"></script>
       
    <script src="https://code.jquery.com/jquery-3.1.0.min.js"></script>
    <!--<p id=s"+checkboxId+">"+checkboxId+" is selected</p>-->
<body>

	<jsp:include page="/WEB-INF/jsp/User/Header.jsp" />
	<jsp:include page="/WEB-INF/jsp/User/Menu.jsp" />



	
	<div class="background"></div>
	<!---blue box---->

	<div class="container">
		<input type="hidden" class="string optional" id="user_boisrindpoints"
			value="${boisrindpoints}" />
	</div>

	<div class="container" id="bus">
       <c:forEach items="${busalltail}" var="u">
       <div class="container" >
                <div class="col-md-4">
                    <i class="fa fa-bus fa-2x img-responsive" style="/*margin-left: 20px;*/ font-size: 3em; color: gray; float: left; margin-bottom: 30px; margin-top: 8px; margin-right: 30px;"></i>
                    <h4 style="margin-top: 8px;"><b>${u.displayName}</b><br></small></h4>
                     <img src=<c:url value="/resources/images/Amenities.png"/> style="width: 40%; margin-left: 10px;">
               
                </div>	<hr class="line1">

                <div class="col-md-3 col-sm-4 col-xs-12 spt">
                   <h4 style="float:left;"><b>${u.arrivalTime} <br></h4>
                   <h4>${u.duration}</b><br><img src=<c:url value="/resources/images/time.png"/>  style="width: 19%;" class="time"></h4><br>
                                    
                    <div class="bottom-align-text"><select class="form-control2" id="user_boisrindpointsbusdeatil${u.i}">
					<option value="${u.landmark}##${u.pointId}">${u.landmark}</option>

				</select></div>                    
                </div>  <hr class="line">
                
                <div class="col-md-2 col-sm-3 col-xs-6 spt">
                    <img src=<c:url value="/resources/images/chair.png"/> style="width: 19%;" class="chair"><h4><b>${u.fares}<br><small>${u.availableSeats}seats</small></b></h4>
                </div>	<hr class="line">
                
                <div class="col-md-2 col-sm-3 col-xs-6 spt">
                    <i class="fa fa-inr fa-2x rups"></i><h3 style="margin-left: 20px;"> <b> ${u.fares}</b></h3>
                </div>	<hr class="line">
                
                <div class="col-md-2 col-sm-2 col-xs-6" style="margin-top: 10px;">
                   <button type="button" class="btn btn-warning" style="background: red; color: white; border-radius: 0;"
						onclick="val('${u.tripid}','${u.porvidr}'
					,'${u.travels}','${u.citysourceoneway}','${u.citydestoneway}',' ${u.datepickeronewaydeparure}','${u.i}','${u.cancellationPolicy}'
					 ,'${u.convenienceFee}','${u.partialCancellationAllowed}','${u.busType}','${u.departureTime}','user_boisrindpointsbusdeatil${u.i}')">View
						Seats</button>
                </div>   
                
              </div>
              <hr>
		</c:forEach>     
       </div><!---end container-->
	<input type="hidden" value="${Triptype}" id="triptypeofuser">
</body>

 <div class="container"
	style="background: rgb(224, 224, 224); display: none; "
	id="seat">
	<div class="travel"></div>
	<h4><b>Choose Seats by Clicking</b></h4>
	<div class="col-lg-3 text-center" style=" padding: 20px;">
	  <img href='<c:url value="/resources/images/steering-wheel.png"/>' style="width: 30px; margin-left: 135px;"><hr style="margin-bottom: -10px;">
		<input type="checkbox" style="visibility: hidden; color: red;"
			checked="checked" />
		<div
			style="background: red; width: 13px; float: left; margin-right: -13px;">
			
		</div>
		<div id="seatsdiv"></div>
	</div>
	<div class="col-lg-3" style="font-weight: 600;">
		<h5 ><b  style="font-weight: 600;">SEAT LEGEND<b></h5>
           	 <div class="Ladies"></div><p>Selected</p>
              <div class="Available"></div><p>Available</p>
            <div class="Unavailable"></div>  <p>Unavailable</p>          
             <h4>Tip:Click for select it, Click again to d-select it</h4> 
			<br>
	
	   <span>Seat(s)No :</span>
		<p id="seatsno"></p>
		<span>Amounts :</span>
		<p id="busamount"></p>
		<span>Service Tax :</span>
		<p id="servicetax"></p>
		<span>Service Charge :</span>
		<p id="servicecharge"></p>
		<span>Total Amount :</span>
		<p id="totalamount"></p>
	</div>
	
	<!-- 
	<div class="col-lg-2">
		
	</div>
	<div class="col-lg-2">
		
		<input type="text" placeholder="Passenger Name:" id="psname"><br>
		
		<br> <input type="text" placeholder="Age:" id="age">
		<br><br>
		<input type="text" id="mobileno" placeholder="Mobile No:"><br>
		<br> <input type="text" id="email" placeholder="Email Id:"><br><br>
		
		<input type="text"placeholder="ID Number :" id="idnumber"><br> <br> 
			<input type="text" placeholder="ID Card Issued By:"id="idcardissuedby">
		<br> <br>
		<button type="button"  style="background: red; color: white; border-radius: 0;" class="btn btn-warning" id="bookbus"
			onclick=getbusseatandamount()>Proceed</button>
	</div>
	
	
<div class="col-lg-2 "><br>
		<select class="form-control" id="gender">

			<option value="M">Mr</option>
			<option value="F">Ms</option>
		</select><br>
		<br> <select class="form-control" id="mrs">
			<option value="Mr">Male</option>
			<option value="F">Female</option>
		</select>
		<br><br> <select class="form-control" id="idcard">
			<option value="DRIVING_LICENCE">DRIVING_LICENCE</option>
			<option selected="selected" value="PAN_CARD">PAN_CARD</option>
			<option value="PASSPORT">PASSPORT</option>
			<option value="RATION_CARD">RATION_CARD</option>
			<option value="VOTER_CARD">VOTER_CARD</option>
			<option value="AADHAR">AADHAR</option>
		</select><br>
		
	</div>
 -->	
 
           <div class="col-sm-4" id="sts">
				<p class="num">passenger info</p>
           
            <div class="col-sm-12 book-tbl3" id="bossss" style="display:none;">                       
            </div>
           
           <div class="form_seat" style="display:none; margin-top: 10px;">
           <form>  <br>
           <div class="group_1">
           	 <select class="form-control" id="gender">	
				<option value="M">Mr</option>
				<option value="F">Ms</option>
			</select>  
		   </div><br>
		   <div class="group_1">
		   <select class="form-control" id="mrs">
			<option value="Mr">Male</option>
			<option value="F">Female</option>
		</select>
		</div><br>
		
		<div class="group_1">
		 <select class="form-control" id="idcard">
			<option value="DRIVING_LICENCE">DRIVING_LICENCE</option>
			<option selected="selected" value="PAN_CARD">PAN_CARD</option>
			<option value="PASSPORT">PASSPORT</option>
			<option value="RATION_CARD">RATION_CARD</option>
			<option value="VOTER_CARD">VOTER_CARD</option>
			<option value="AADHAR">AADHAR</option>
		</select>
</div>  <br>
		
		<!-- <input type="text" placeholder="Passenger Name:" id="psname"><br> -->
		<div class="group_1">      
                          <input type="text" id="psname" required>
                          <span class="highlight"></span>
                          <span class="bar"></span>
                          <label class="bel">Passenger Name</label>
                        </div>
                        
                        <div class="group_1">      
                          <input type="text" id="age" required>
                          <span class="highlight"></span>
                          <span class="bar"></span>
                          <label class="bel">Age</label>
                        </div>
		
		<div class="group_1">      
                          <input type="text" id="mobileno" required>
                          <span class="highlight"></span>
                          <span class="bar"></span>
                          <label class="bel">Mobile No</label>
                        </div>
		
		
		<div class="group_1">      
                          <input type="text" id="email" required>
                          <span class="highlight"></span>
                          <span class="bar"></span>
                          <label class="bel">Email Id</label>
                        </div>
		
		<div class="group_1">      
                          <input type="text" id="idnumber" required>
                          <span class="highlight"></span>
                          <span class="bar"></span>
                          <label class="bel">ID Number</label>
                        </div>
                        
		<div class="group_1">      
                          <input type="text" id="idcardissuedby" required>
                          <span class="highlight"></span>
                          <span class="bar"></span>
                          <label class="bel">ID Card Issued By:</label>
                        </div>
		
		
		<div class="group_1">  
		   <button type="button"  style="background: red; color: white; border-radius: 0;" class="btn btn-warning" id="bookbus"
			onclick=getbusseatandamount()>Proceed</button> 
                        </div>
		
		
		
		<!-- <br> <input type="text" placeholder="Age:" id="age">
		<br><br>
		<input type="text" id="mobileno" placeholder="Mobile No:"><br>
		<br> <input type="text" id="email" placeholder="Email Id:"><br><br>
		
		<input type="text"placeholder="ID Number :" id="idnumber"><br> <br> 
			<input type="text" placeholder="ID Card Issued By:"id="idcardissuedby">
		<br> <br> -->
		<!-- <button type="button"  style="background: red; color: white; border-radius: 0;" class="btn btn-warning" id="bookbus"
			onclick=getbusseatandamount()>Proceed</button>         -->                               
                    </form><hr></div><br><br>
            <%-- <div class="bottom-align-text">
                <center><button type="button"  style="background: red; color: white; border-radius: 0;" class="btn btn-warning" id="bookbus"
			onclick=getbusseatandamount()>Proceed</button></center>        
            </div>  --%>
           
           <!----end input text--->
         </div>  
                    
       </div><!---end container-->
 
</div>
<!-- <script>
$(function(){
	$(window).scroll(function(){
		var windowScrollPosTop = $(window).scrollTop();
		$(".status").html(windowScrollPosTop);
	});
	
	 $('.seats').click(function(){
	    var checkboxId = $(this).attr("id");
		if (this.checked){
    		 
			
		} else{
				
   	 	}
	});	 
});
</script> -->
<script>

var a3="";
var c3="";
var d3="";
var e3="";
var f3="";

function val(x,x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12)
{
	//alert(x);
	a3=x7;
	b3=x8;
	c3=x9;
	d3=x10;
	e3=x11;
	
	f3=x12;
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/Api/v1/User/Website/en/ThirdParty/Travel/Buses/getcheckbusbookdetial",
		

		data : JSON.stringify({
			"tripid":""+x+"","porvidr":""+x1+"","travels":""+x2+"","sourceId":""+ x3+"",
			"destinationId":"" + x4+"","journeyDate":"" + x5+"",
		}),
		
		success : function(response) 
		{
			console.log("::::::::::::Response   "+response)
			
			$("#seat").toggle();
			
			var x = new Array();
			x = response.split("&&");
			
			
			var second = new Array();
			second = x[1].split("!!");
			
			
			var splitata = new Array();
			splitata = x[0].split("**");
			
			var splitatamain = new Array();
			splitatamain = second[0].split("~~");
			
			var Uperberthandlowerbert = new Array();
			Uperberthandlowerbert = second[1].split("^^");
			
			
			var seatsnumber = new Array();
			seatsnumber = splitata[0].split("##");
			
			var seatsamount = new Array();
			seatsamount = splitata[1].split("@@");
			
			var servicetax = new Array();
			servicetax = splitatamain[0].split("$$");
			
			var operatorservicecharge = new Array();
			operatorservicecharge = splitatamain[1].split("%%");
			
			// for booked seat
			
			
			
			
			
			var i=0;
			var c="";
		   
			 for(i=1;i<operatorservicecharge.length;i++)
			 {
			 
		if(i%4==0)
			{
			 var bookorunbook=Uperberthandlowerbert[i];
			 console.log(bookorunbook);
			 var n = bookorunbook.localeCompare("book");
		     if(n===0)
		     {
		    	 var cb = "<div class='book-tbl' >&nbsp&nbsp <input  id='"+i+"'class='seats'  type='checkbox'   onclick=busblock('"+seatsamount[i]+"','"+seatsnumber[i]+"','"+servicetax[i]+"','"+operatorservicecharge[i]+"','"+i+"')><label for='"+i+"' class='seat'></label></div><br>";
			        c+=cb;
		     }
		     else
		    	 {
		    	 var cb = "<div class='book-tbl' >&nbsp&nbsp <input  id='"+i+"'class='seats'  type='checkbox'   onclick=busblock('"+seatsamount[i]+"','"+seatsnumber[i]+"','"+servicetax[i]+"','"+operatorservicecharge[i]+"','"+i+"') disabled><label for='"+i+"' style='background: grey !important;color: #fff;' class='seat'></label></div><br>";
			        c+=cb;
		    	 }
			
			} 
		else
			{
		if(i%2==0)
			{
			var bookorunbook=Uperberthandlowerbert[i];
			 console.log(bookorunbook);
			 var n = bookorunbook.localeCompare("book");
		     if(n===0)
		     {
			 var cb = "<div class='book-tbl' >&nbsp&nbsp<input  id='"+i+"'class='seats'  type='checkbox'   onclick=busblock('"+seatsamount[i]+"','"+seatsnumber[i]+"','"+servicetax[i]+"','"+operatorservicecharge[i]+"','"+i+"')><label for='"+i+"' class='seat'></label></div>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
		        c+=cb;
		     }
		     else
		    	 {
		    	 var cb = "<div class='book-tbl' >&nbsp&nbsp<input  id='"+i+"'class='seats'  type='checkbox'   onclick=busblock('"+seatsamount[i]+"','"+seatsnumber[i]+"','"+servicetax[i]+"','"+operatorservicecharge[i]+"','"+i+"') disabled><label for='"+i+"' style='background: grey !important;color: #fff;' class='seat'></label></div>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp";
			        c+=cb;
		    	 }
			}
		else
			{
			var bookorunbook=Uperberthandlowerbert[i];
			 console.log(bookorunbook);
			 var n = bookorunbook.localeCompare("book");
		     if(n===0)
		     {
			 var cb = "<div class='book-tbl' >&nbsp&nbsp<input  id='"+i+"'class='seats'  type='checkbox'   onclick=busblock('"+seatsamount[i]+"','"+seatsnumber[i]+"','"+servicetax[i]+"','"+operatorservicecharge[i]+"','"+i+"')><label for='"+i+"' class='seat'></label></div>";
		        c+=cb;
		     }
		        else
		        	{
		        	 var cb = "<div class='book-tbl' >&nbsp&nbsp<input  id='"+i+"' class='seats'  type='checkbox'   onclick=busblock('"+seatsamount[i]+"','"+seatsnumber[i]+"','"+servicetax[i]+"','"+operatorservicecharge[i]+"','"+i+"')  disabled><label for='"+i+"' style='background:grey !important;color: #fff;' class='seat'></label></div>";
				        c+=cb;
		        	}
			}
			}
				 
				}
			 document.getElementById('seatsdiv').innerHTML=c;
			
			
		}
	
	});

}
/* for(var i = array.length - 1; i >= 0; i--) {
    if(array[i] === number) {
       array.splice(i, 1);
    }
} */


function sumArray(array) {
	  for (
	    var
	      index = 0,              // The iterator
	      length = array.length,  // Cache the array length
	      sum = 0;                // The total amount
	      index < length;         // The "for"-loop condition
	      sum += parseInt(array[index++]) // Add number on each iteration
	  );
	  return sum;
	}
	

var busno = [];
var busamounttotal = new Array();

var busamounttotalfreshonlyfair = new Array();
function busblock(x1,x2,x3,x4,x5)
{
	
	//alert(x5);
	
		var lfckv = document.getElementById(x5).checked;
		var amount=parseInt(x1)+parseInt(x3)+parseInt(x4);
				if(lfckv)
		{
			$("#bossss").append("<div id=s"+x5+">"+$(".form_seat").html()+"</div>");
			 $("#bossss").show();
			busamounttotalfreshonlyfair.push(x1);
			busno.push(x2);
			busamounttotal.push(amount);
			
			document.getElementById("seatsno").innerHTML = busno;
			document.getElementById("busamount").innerHTML = busamounttotalfreshonlyfair;
			document.getElementById("totalamount").innerHTML = sumArray(busamounttotal);
			document.getElementById("servicecharge").innerHTML=x3;
			document.getElementById("servicetax").innerHTML=x4;
		}
		else
		{
			$("#s"+x5).hide();
			for(var i = busno.length - 1; i >= 0; i--) {
			    if(busno[i] === x2) {
			    	busno.splice(i, 1);
			    }
			}
			var d=document.getElementById("totalamount").innerHTML;
			var amount=parseInt(x1)+parseInt(x3)+parseInt(x4);
			var c=parseInt(d)-amount;
			for(var j = busamounttotal.length - 1; j >= 0; j--)
			{
				
			    if(busamounttotal[j] === amount) {
			    	busamounttotal.splice(j, 1);
			    }
			}
			
			document.getElementById("seatsno").innerHTML = busno;
			document.getElementById("busamount").innerHTML = x1;
			document.getElementById("totalamount").innerHTML = c;
			document.getElementById("servicecharge").innerHTML=x3;
			document.getElementById("servicetax").innerHTML=x4;
			
			
		}
	
	
	
	
}


function getbusseatandamount()
{
	var a=document.getElementById("seatsno").innerHTML;
	var b=document.getElementById("busamount").innerHTML;
	var c=document.getElementById("totalamount").innerHTML;
	var d=document.getElementById(f3).value;
	var e=document.getElementById("servicecharge").innerHTML;
	var f=document.getElementById("servicetax").innerHTML;
	
	
	
	var g=document.getElementById("gender").value;
	var h=document.getElementById("psname").value;
	var i=document.getElementById("mrs").value;
	var j=document.getElementById("age").value;
	var k=document.getElementById("mobileno").value;
	var l=document.getElementById("email").value;
	var m=document.getElementById("idcard").value;
	var n=document.getElementById("idcardissuedby").value;
	var o=document.getElementById("idnumber").value;
    var p=busno.length;
  //  document.getElementById("bookbus").disabled = true;
//alert(p);
  if(p==1)
	{
	
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/Api/v1/en/Website/en/ThirdParty/Travel/Buses/Busbookingandgetticket",
			
			data : JSON.stringify({
				"seatNos" : "" +a+"","fares" : "" +b+"",
				"user_boisrindpointsbusdeatil" : "" + d
				+"", "serviceCharge" : "" +e+"","serviceTax" : "" + f+"","genders" : "" + g+"","names" : "" + h+"", "titles" : "" + i+"","ages" : "" + j+"","mobileNo" : "" + k+"","emailId" : "" + l
				+"","idCardType" : "" + m+"","idCardIssuedBy" : "" + n+"","idCardNo" : "" + o+"","noofSeats" : "" + p+"","cancellationPolicy" : "" + 
				a3+"","convenienceFee" : "" + b3+"","partialCancellationAllowed" : "" + c3+"","busTypeName" : "" + d3+"","departureTime" : "" + e3
			}),
			success : function(response) 
			{
				alert("Booking SuccessFull");
			document.getElementById("bookbus").disabled = false;

				//document.getElementById("detailticket").innerHTML=response;
				var cur = new Array();
	   			cur = response.split("@@");
	   			document.getElementById("mobileticket").innerHTML=cur[0];
	   			 document.getElementById("emailticket").innerHTML=cur[1];
	   			document.getElementById("noticket").innerHTML=cur[2];
	   			document.getElementById("boardingticket").innerHTML=cur[4];
	   			document.getElementById("bookingdateticket").innerHTML=cur[18];

	   			document.getElementById("sourceticket").innerHTML=cur[6];
	   			document.getElementById("destticket").innerHTML=cur[7];

	   			document.getElementById("passnameticket").innerHTML=cur[12];

	   			document.getElementById("travellerticket").innerHTML=cur[8];
	   			document.getElementById("journeydateticket").innerHTML=cur[10];

	   			document.getElementById("departureticket").innerHTML=cur[19];

	   			document.getElementById("seatticket").innerHTML=cur[13];

	   			document.getElementById("amountticket").innerHTML=cur[14];

	   			/* document.getElementById("boardingticket").innerHTML=cur[13];

	   			document.getElementById("boardingticket").innerHTML=cur[14];
	   			document.getElementById("boardingticket").innerHTML=cur[15];
	   			document.getElementById("boardingticket").innerHTML=cur[16];

	   			 */
	   			alert("ticket Modal")
	   			
				$("#ticket").modal('show');
				
			}
		
		});
	}
else 
	  {
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/Api/v1/en/Website/en/ThirdParty/Travel/Buses/MultipleBusBook",
			
			data : JSON.stringify({
				"seatNos" : "" +a+"","fares" : "" +busamounttotalfreshonlyfair+""/* ,"totalamount" : "" +c+"" */,"user_boisrindpointsbusdeatil" : "" + d
				+"", "serviceCharge" : "" +e+"","serviceTax" : "" + f+"","genders" : "" + g+"","names" : "" + h+"", "titles" : "" + i+"","ages" : "" + j+"","mobileNo" : "" + k+"","emailId" : "" + l
				+"","idCardType" : "" + m+"","idCardIssuedBy" : "" + n+"","idCardNo" : "" + o+"","noofSeats" : "" + p+"","cancellationPolicy" : "" + 
				a3+"","convenienceFee" : "" + b3+"","partialCancellationAllowed" : "" + c3+"","busTypeName" : "" + d3+"","departureTime" : "" + e3
			}),
			success : function(response) 
			{
			document.getElementById("bookbus").disabled = false;

				//document.getElementById("detailticket").innerHTML=response;
				var cur = new Array();
	   			cur = response.split("@@");
	   			document.getElementById("mobileticket").innerHTML=cur[0];
	   			 document.getElementById("emailticket").innerHTML=cur[1];
	   			document.getElementById("noticket").innerHTML=cur[2];
	   			document.getElementById("boardingticket").innerHTML=cur[4];
	   			document.getElementById("bookingdateticket").innerHTML=cur[18];

	   			document.getElementById("sourceticket").innerHTML=cur[6];
	   			document.getElementById("destticket").innerHTML=cur[7];

	   			document.getElementById("passnameticket").innerHTML=cur[12];

	   			document.getElementById("travellerticket").innerHTML=cur[8];
	   			document.getElementById("journeydateticket").innerHTML=cur[10];

	   			document.getElementById("departureticket").innerHTML=cur[19];

	   			document.getElementById("seatticket").innerHTML=cur[13];

	   			document.getElementById("amountticket").innerHTML=cur[14];

	   			/* document.getElementById("boardingticket").innerHTML=cur[13];

	   			document.getElementById("boardingticket").innerHTML=cur[14];
	   			document.getElementById("boardingticket").innerHTML=cur[15];
	   			document.getElementById("boardingticket").innerHTML=cur[16];

	   			 */
	   			
	   			
				$("#ticket").modal('show');
				
			}
		
		});
	  }

}

function closerefres()
{
	//var c="#"+x;
	$("#ticket").modal('hide');
	$("body").removeClass('modal-open');
	$(".modal-backdrop").remove();
	window.location = "../../Home";
}
</script>



<!-- Modal -->
<div class="modal fade" id="ticket" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" onclick="closerefres()">&times;</button>
				<h4 class="modal-title">Details</h4>
			</div>
			<div class="modal-body">
				<table class="table">
					<tbody>
						<tr>
							<td>Booked on:</td>
							<td id="bookingdateticket"></td>
						</tr>
						<tr>
							<td>Mobile:</td>
							<td id="mobileticket">9611411087</td>
						</tr>
						<tr>
							<td>Id:</td>
							<td id="emailticket"></td>
						</tr>
						<tr>
							<td>Booking Reference NO:</td>
							<td id="noticket"></td>
						</tr>
						<!-- <tr>
							<td>PNR number:</td>
							<td id="pnrticket"></td>
						</tr> -->
						<!-- <tr>
							<td>Ticket No.</td>
							<td id="ticketnoticket"></td>
						</tr> -->
						<tr>
							<td>Boarding:</td>
							<td id="boardingticket"></td>
						</tr>
						<tr>
							<td>Date of Journey:</td>
							<td id="journeydateticket"></td>
						</tr>
					</tbody>
				</table>
				<div style="background: rgba(167, 167, 167, 1.00); padding: 10px;">
					<table class="table">
						<tbody>
							<tr>
								<p>
									<b>Bus Details</b>
								</p>
							</tr>
							<tr>
								<th>From</th>
								<th>To</th>
								<th>Bus</th>
								<th>Date</th>
								<th>Dep.</th>
							</tr>
							<tr>
								<td id="sourceticket"></td>
								<td id="destticket">Bangalore</td>

								<td id="travellerticket">Reshma Tourist Non A/C Sleeper
									(2+1)</td>
								<td id="journeydateticket">13 Sep 2016</td>
								<td id="departureticket">10:30 PM</td>
							</tr>
						</tbody>
					</table>
				</div>

				<hr>
				<table class="table">
					<tbody>
						<tr>
							<p>
								<b>Passenger(s) Details</b>
							</p>
						</tr>
						<tr>
							<th>Passanger Name</th>
							<th>Seat No</th>
							<!-- <th>Charge Description</th> -->
							<th>Amount</th>
						</tr>
						<tr>



							<!-- <td id="chargeticket"></td> -->
							<td id="passnameticket"></td>
							<td id="seatticket"></td>
							<td id="amountticket"></td>


						</tr>
					</tbody>
				</table>

			</div>
		</div>
	</div>
</div>
</html>

