<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<style type="text/css">
	table *{
		border: solid 1px;
	}
	.red{
		background-color: red;
	}
	.green{
		background-color: green;
	}
</style>
</head>
<body>
1. JSTL 과 EL 2단부터 9단까지 구구단 출력
	<c:set var="minDan" value="${empty param.min ? 2 : param.min}"></c:set>
	<c:set var="maxDan" value="${not empty param.max ? param.max : 9}"></c:set>
<form>
	<select name="min">
		<c:forEach var="dan1" begin="2" end="9" >
			<option value="${dan1}" ${dan1 eq minDan ? "selected" : "" }>${dan1}단</option>
		</c:forEach>
	</select>
	<select name="max">
		<c:forEach var="dan2" begin="2" end="9" >
			<option value="${dan2}" ${dan2 eq maxDan ? "selected" : "" }>${dan2}단</option>
		</c:forEach>
	</select>
	<button type="submit">SUBMIT</button>
</form>

<table>
	<c:forEach var="i" begin="1" end="9" step="1" varStatus="vs">
	<c:choose>
		<c:when test="${vs.count eq 3 }">
			<c:set var="clzAttr" value="red"></c:set>
		</c:when>
		<c:when test="${vs.last }">
			<c:set var="clzAttr" value="green"></c:set>
		</c:when>
		<c:otherwise>
			<c:set var="clzAttr" value="normal"></c:set>
		</c:otherwise>
	</c:choose>
		<tr class="${clzAttr}">
		<c:forEach var="j" begin="${minDan }" end="${maxDan }" step="1">
			<td>
				${j} * ${i} = ${j*i}
			</td>
		</c:forEach>
		</tr>
	</c:forEach>
</table>
<script type="text/javascript">
	/* $("[name=min]").val("${minDan}")
	$("[name=max]").val("${maxDan}") */
</script>
</body>
</html>