<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body onLoad="document.pay.submit()">
<h3>Please Wait while we're redirecting you to Payment Gateway...</h3>

	<form method="post" id="pay" action="https://secure.ebs.in/pg/ma/payment/request">
		<input type="hidden" class="form-control" name="channel" value= "${fn:escapeXml(loadmoney.channel)}"  />
		<input type="hidden" class="form-control" name="account_id" value="${fn:escapeXml(loadmoney.accountId)}"  />
		<input type="hidden" class="form-control" name="reference_no" value="${fn:escapeXml(loadmoney.referenceNo)}"  />
		<input type="hidden" class="form-control" name="amount" value="${fn:escapeXml(loadmoney.amount)}" />

		<input type="hidden" class="form-control" name="mode" value="${fn:escapeXml(loadmoney.mode)}" />
		<input type="hidden" class="form-control" name="currency" value="${fn:escapeXml(loadmoney.currency)}" />
		<input type="hidden" class="form-control" name="description" value="${fn:escapeXml(loadmoney.description)}"/>
		<input type="hidden" class="form-control" name="return_url" value="${fn:escapeXml(loadmoney.returnUrl)}" />
		<input type="hidden" class="form-control" name="name" value="${fn:escapeXml(loadmoney.name)}"/>
		<input type="hidden" class="form-control" name="address" value="${fn:escapeXml(loadmoney.address)}"  />
		<input type="hidden" class="form-control" name="city" value="${fn:escapeXml(loadmoney.city)}"/>
		<input type="hidden" class="form-control" name="state" value="${fn:escapeXml(loadmoney.state)}"/>
		<input type="hidden" class="form-control" name="country" value="${fn:escapeXml(loadmoney.country)}"/>
		<input type="hidden" class="form-control" name="postal_code" value="${fn:escapeXml(loadmoney.postalCode)}"/>
		<input type="hidden" class="form-control" name="phone" value="${fn:escapeXml(loadmoney.phone)}" />
		<input type="hidden" class="form-control" name="email" value="${fn:escapeXml(loadmoney.email)}" />
		<input type="hidden" class="form-control" name="ship_name" value="${fn:escapeXml(loadmoney.shipName)}" />
		<input type="hidden" class="form-control" name="ship_address" value="${fn:escapeXml(loadmoney.shipAddress)}"/>
		<input type="hidden" class="form-control" name="ship_city" value="${fn:escapeXml(loadmoney.shipCity)}" />
		<input type="hidden" class="form-control" name="ship_state" value="${fn:escapeXml(loadmoney.shipState)}"/>
		<input type="hidden" class="form-control" name="ship_country" value="${fn:escapeXml(loadmoney.shipCountry)}" />
		<input type="hidden" class="form-control" name="ship_postal_code" value="${fn:escapeXml(loadmoney.shipPostalCode)}"/>
		<input type="hidden" class="form-control" name="ship_phone" value="${fn:escapeXml(loadmoney.shipPhone)}"/>
		<input type="hidden" class="form-control" name="secure_hash" value="${fn:escapeXml(loadmoney.secureHash)}" />
 	</form>
  	<script>document.getElementById('pay').submit();</script> 
</body>
</html>
 --%>
 
<%@ page import="java.io.*,java.util.*,com.ccavenue.security.*"%>
<html>
<head>
<title>Sub-merchant checkout page</title>
<script
 src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
 <%
  String merchantId = "123108";
  /*8080 Local*/
  //String accessCode = "F94007DF1640D69A";     // Put in the Access Code provided by CCAVENUES
  //String workingKey = "9B20FA8217806BC860462424176142DD";    // Put in the Working Key provided by CCAVENUES            
  /*80 Production*/
  String accessCode = "AVDV00EB87AD17VDDA";
  String workingKey = "612606343A883D9197BA54AF0197E3D9";
  System.out.println("REQUEST :: " + request.getParameterNames().toString());
  Enumeration enumeration = request.getParameterNames();
  String ccaRequest = "", pname = "", pvalue = "";
  while (enumeration.hasMoreElements()) {
   pname = "" + enumeration.nextElement();
   pvalue = request.getParameter(pname);
   ccaRequest = ccaRequest + pname + "=" + pvalue + "&amount=10";
   String encRequest = "67ea826f8b9b343371cf1811ac2a8ddcaf824b3481d9afbb4db14dbf1d9a5ddab95e85b4838de0cb551dd10f1db1423f23f70384f9a877f71273d7cafe1e8b5832bfd8cde9e0c3169435dfdfeb7920be005b633d95ed78552a02291c0877faf615d79e5861d1eed95a998fe0a0b2161dce6bd8aa8c03833dfcc7ed9dfc95022ca6b1984ecc38df28d9aec25594cf281c827716c0201c1d95e5b3fd270b7777249dad535d02b40adf102d19843706c9b2f39849cf79e76006003f06dad14b61ac414dd3f15e3d387b5f6d60402cda2e75ecdbf19c7b2a7dd72c9c24c4a23a0f4feb602a75638c70b8be195693cdd86223ed707f318e5b574e99d2c40d4b6c196940c3f44cbddc5999fc99586718adfd978196642185419f8600b7672310d9ab2ded3e3a33ef66ea65081783e316f18b5570d709e278740eed0458ecf9771445ba8c222c245edf479916b8aa29a66789faaef93168a98ebfacb137c824644f79d9d086bfc2a2285c76a670710240e9856a42b5f9dad85744085754e21496b0fdd2715b4c0eb810cd620a5a8765cb793020e5778c5c7929e0de06f991a95972ede036fce8258e72edc94ebb98f63b068d8ddb20172b0a86f63c4fbd19dfa58e05fc76734ba6a637883ad7f4b6cfffec8b72170426db9c0e71fad8587934d1bb981be77030532351f635be669bc909bb124176d7b00dc0fcb46971ae4929a440f2a81b568714516818254e01c016375f29c80244e0be6760d24979fce36ac55ce03d5fd3a6137d3c4e8b9013269a9e6a229434a0268b9368f4f12b15017d60f47aeb27aca1f9987e7bb56c699d4743efe4f64a5ee6ee50e16708e3552cc4e54dec3cc9a87c520d5bab6dafaaa1ece2f57f96c83873d836f4636e5efabc65263f2ce4bddeeb801a7e65a8419407f2787ad3c1c0005006d2375e69e6c1bb4b4efd7c03b8bc4350087eb6f203bc02027208e61a88263d96f5329573692d40f18dd1882409f229050c7bc9b4798b8c82a7b4feb8225f63edfbdf3c036c34c5e85a9d257d870c49484ba82bd1cf43785a1493e99d51911ae6686fa47242f06453f96ea7f58569f53a2ccb2c01b948fece13c114dd733053ccf6a560d2eb5ecf56ca3f4550e2332d007221ad6f70364aa5d5e8e77895394338b3b7b4e52e70a9b3f4fe8b7eb3aad1d0fab96e729c21447da1a879cb";
  }
  System.out.println("REQUEST CCA :: " + ccaRequest);
  AesCryptUtil aesUtil = new AesCryptUtil(workingKey);
  String encRequest = aesUtil.encrypt(ccaRequest);
 %>
 <center>
 
 <%-- src="https://secure.ccavenue.com/transaction/transaction.do?command=initiateTransaction&merchant_id=<%=merchantId%>&encRequest=<%=encRequest%>&access_code=<%=accessCode%>"> --%>
  <br> <br>
  <!-- width required mininmum 482px -->
  <iframe width="482" height="500" scrolling="No" frameborder="0"
   id="paymentFrame"
   src="https://test.ccavenue.com/transaction/transaction.do?command=initiateTransaction&merchant_id=<%=merchantId%>&encRequest=<%=encRequest%>&access_code=<%=accessCode%>">
  </iframe>
 </center>
 <script type="text/javascript">
  $(document).ready(
    function() {
     $('iframe#paymentFrame').load(
       function() {
        window.addEventListener('message', function(e) {
         $("#paymentFrame").css("height",
           e.data['newHeight'] + 'px');
        }, false);
       });
    });
 </script>
</body>
</html>	