/* get all sorce*/
function busnameget() {
 // $( "#datepicker-1" ).datepicker();
 
 //alert("sddd");
  var a = document.getElementById("user_id").value;

  var cur = new Array();
  cur = a.split("$");

  var x = new Array();
  x = cur[0].split("#");

  var x1 = new Array();
  x1 = cur[1].split("@");

 
  var i;

  /*document.getElementById("citysource").innerHTML = "";
  for (i = 0; i < x.length; i++) {
   var option = document.createElement("option");
   option.text = x1[i];
   option.value = x[i];
   var select = document.getElementById("citysource");
   if (i == 0) {
    option.text = "Select Any City";
    option.value = "#";
    select.add(option);

   }
   select.add(option);

  }*/

  
  var j;

  document.getElementById("citysourceoneway").innerHTML = "";
  for (j = 0; j < x.length; j++) {
   var option = document.createElement("option");
   option.text = x1[j];
   option.value = x1[j]+"@@"+x[j];
   var select = document.getElementById("citysourceoneway");
   if (j == 0) {
    option.text = "Select Any City";
    option.value = "#";
    select.add(option);

   }
   select.add(option);

  }

 }


/* get all destination*/

function setdestination(currentid,currentval) 
{
 
 
 var b = document.getElementById(currentval).value;

  
  var a = document.getElementById("user_id").value;
  
  var cur = new Array();
  cur = a.split("$");

  var x = new Array();
  x = cur[0].split("#");

  var x1 = new Array();
  x1 = cur[1].split("@");

  
  var i;
  //alert(x[0]+"ddddd"+b);
  document.getElementById(currentid).innerHTML = "";
  for (i = 0; i < x.length; i++) {
   if (x[i].includes(b)) {

   } else {
    var option = document.createElement("option");
    option.text = x1[i];
    option.value = x[i]+"@@"+x1[i];
    var select = document.getElementById(currentid);
    if (i == 0) {
     option.text = "Select Any City";
     option.value = "#";
     select.add(option);

    }
    select.add(option);
   }

  }
 }
 

/ get all AvailableBuses One Way /
 
 
 function AvailableBusesget()
 {
  
  var dateoneway = document.getElementById("datepickeronewaydeparure").value;
  var citydest = document.getElementById("citydestoneway").value;
  var citysource = document.getElementById("citysourceoneway").value;
  
  $.ajax({
   type : "GET",
   url : "/user/AvailableBuses",
   success : function(response) {
  alert(response);
  document.getElementById("allbusdetail").innerHTML = response;
   }
  });
 
 }
 
 / get all AvailableBuses Round Way /
 
 
 function AvailableBusesgetroundway()
 {
  
  var date = document.getElementById("datepickeronewaydeparure").value;
  var citydest = document.getElementById("citydestoneway").value;
  var citysource = document.getElementById("citysourceoneway").value;
  var csrfParameter = $("meta[name='_csrf_parameter']").attr("content");
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var hash_key="hash";
	var default_hash="123456";
	var headers = {};
	headers[hash_key] = default_hash;
	headers[csrfHeader] = csrfToken;
  
  $.ajax({
   type : "POST",
   header :{
    "key":"s",
    
   },
   contentType : "application/json",
   url : "/Api/v1/User/Website/en/ThirdParty/Travel/Buses/AvailableBuses",
   dataType : 'json',
   data : JSON.stringify({

    "sourceId" : "sd",
    "destinationId" : "sd",
    "journeyDate" : "sd",
    "tripType" : "single",
    "consumerSecret" : "1231",

    "returnDate" : "sd",
    "userType" : "5",
    "operatorId" : "sd",
    "busType" : "sd",
    "busTimings" : "sd",
    "consumerKey" : "sd"
    
   }),
   success : function(response) 
   {
    var div = document.getElementById("allbusdetail");

    div.innerHTML = response;
    
   }
  });
 
 }
 
 
//-------------------------------------------- Hotel JS
 
 /*function hotelsrcget(destinationId,cityName) {
  
  
  alert("sddd");

  $.ajax({
   type : "POST",
   header :{
    
   },
   contentType : "application/json",
   url : "/v1/User/Website/ThirdParty/Travel/Buses/City",
   dataType : 'json',
   data : JSON.stringify({
    "destinationId" : "" +destinationId+ "",
    "cityName" : "" + cityName + "",
    
    
   }),
   success : function(response) 
   {
   
   var a = document.getElementById("sourceid").value;

   var cur = new Array();
   cur = a.split("$");

   var x = new Array();
   x = cur[0].split("#");

   var x1 = new Array();
   x1 = cur[1].split("@");

  
   var i;

   document.getElementById("hotelsource").innerHTML = "";
   for (i = 0; i < x.length; i++) {
    var option = document.createElement("option");
    option.text = x1[i];
    option.value = x[i];
    var select = document.getElementById("hotelsource");
    if (i == 0) {
     option.text = "Select Any City";
     option.value = "#";
     select.add(option);

    }
    select.add(option);

   }

   
   }
  }
  
  

*/