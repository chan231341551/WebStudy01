<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>${header['user-agent']}</h4>
<h4>jQuery 와 Bootstrap 사용 가능한 페이지</h4>
<jsp:include page="${contentPage}"></jsp:include>
<jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>