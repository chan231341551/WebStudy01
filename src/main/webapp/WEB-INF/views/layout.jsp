<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="10kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" flush="false"></jsp:include> 
</head>
<body>
<div class="container">
	<jsp:include page='${contentPage}'></jsp:include>
</div>
<jsp:include page="/includee/postScript.jsp" flush="false"></jsp:include>
</body>
</html>