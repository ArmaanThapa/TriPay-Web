<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<sec:csrfMetaTags/>
<title>Email Verified</title>
</head>
<body style="background:#efefef;">
		
        <header style="padding: 20px; padding-left: 30px;"> <img src='<c:url value='/resources/images/vijaya pay logo.png'/>' alt=""></header>

    	<div class="img" style=" text-align: center; margin-top: 5%;">
            <img src="<c:url value='/resources/images/en.png'/>" width="300">
            <h1 style="font-size: 50pt; font-family:'Gill Sans', 'Gill Sans MT', 'Myriad Pro', 'DejaVu Sans Condensed', Helvetica, Arial, sans-serif; color: gray">Email Verified</h1>
       		
            <a href="<c:url value='/'/>" class="btn btn" style="padding: 10px; text-decoration:none; background: #17bcc8; color: white; border: none;">Go to Home Page</a>
        	
        </div>
</body>
</html>