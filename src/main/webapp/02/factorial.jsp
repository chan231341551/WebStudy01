<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// _JSPService(request, response)
	String numParam = request.getParameter("number");
	
	if(numParam != null && !numParam.matches("\\d{1,2}")){ // * = 0번 이상 반복  , + = 1번이상 반복 , {1,2} = 2의 자리까지
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h4>팩토리얼 연산 처리</h4>
 number = 10;
 <form>
 	<input type="number" name="number" value="<%=Objects.toString(numParam,"") %>" onchange="this.form.submit()" /> 
 </form>
<%
	if(numParam != null){
 	int number = Integer.parseInt(numParam);
 	String pattern = "%d! = %d";
 	int result = fact(number);
 	String expr = String.format(pattern, number, result);
%>
<%= expr %>
<%
	}
%>
<%!
 	int fact(int number){
		if(number<0){
		 	throw new IllegalArgumentException("음수는 연산 불가");
	 	}
	 	else if(number <= 1){
			 return number;
	 	}
		 else{
			 return fact(number-1)*number;
	 	}
 	}
 
 %>
 
</body>
</html>