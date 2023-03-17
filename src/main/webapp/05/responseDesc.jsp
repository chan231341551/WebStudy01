<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<pre>
		Http의 response packaging
		1. Response Line : Status Code(응답상태코드, XXX)
			100~ : ...ing... ( ConnectLess(비연결) - StateLess(비상태) 단점 : 상태를 저장할수없음, 실시간 양방향 통신이 불가능-> 그래서 세션,쿠키를 이용함  )
			200~ : OK
			300~ : 최종 처리하기 위해 클라이언트의 추가 액션이 필요함. (response body가 없음, server에서 알아서 설정)
				304(cache data관련) : Not Modified (수정된게없음 최신임)
<!-- 			(location header에 새로운 위치를 알려줌 , 새로운 위치로 다시 추가 액션이 필요함) -->
				301 / 302 / 307 : Moved + Location response header와 함께 사용 (redirect request)
				<%
// 					request.getRequestDispatcher("/04/messageView.jsp").forward(request, response); // 서버 내에서 이동
// 					String location = request.getContextPath()+"/04/messageView.jsp";
// 					response.sendRedirect(location); // 클라이언트로 부터 새로운 요청이 발생.
				%>
			400~ : clien side error -> Fail 
				400 : <%=HttpServletResponse.SC_BAD_REQUEST %>, 클라이언트 전송 데이터(파라미터)검증시 활용. <!-- 필수 파라미터가 전달되지 않았을때 --> 
<!-- 			Authentication -> 신원 확인 하는 과정 , Authorization _> 이미 신원 확인을 거친 사람에게 권한을 주어졌는지 확인 ( 보안처리에 활용하는 상태코드 ) -->
				401 / 403 : 인증(Authentication)과 인가(Authorization) 기반의 접근 제어에 활용 
					<%=HttpServletResponse.SC_UNAUTHORIZED %>, <%=HttpServletResponse.SC_FORBIDDEN %>
				404 : <%=HttpServletResponse.SC_NOT_FOUND %>
				405 : <%=HttpServletResponse.SC_METHOD_NOT_ALLOWED %>, 현재 요청의 메소드에 대한 콜백 메소드가 재정의되지 않았을때.
				406 / 415 : content-type(MIME) 과 관련된 상태코드
					<%=HttpServletResponse.SC_NOT_ACCEPTABLE %> : Accept request 헤더에 설정된 MIME 데이터를 만들어낼수 없을떄  
						ex)accept:application/json
						   content-type : application/json(XXX)
						   
					<%=HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE %> : Content-type request 헤더르 해석할 수 없을떄.
						ex)content-type:application/json --> unmarshalling(XXX)
			500~ : server side error -> Fail, 500(Internal Server Error)
		2. Response Header : meta data
			Content(body) 에 대한 부가정보 설정 : Content-* , Content-Type(MIME), Content-Length(size)
													  Content-Disposition(content name, 첨부여부)
			<%
// 				response.setHeader("Content-Dispostion", "inline[attatchement];filename=\"파일명\"");
			%>
            Auto Request : 주기적으로 갱신되는 자원에 대한 자동 요청
            Cache control : 자원에 대한 캐싱 설정 
            Location 기반의 이동구조(Redirection).
		3. Response Body(Content body, message body) : 
<%-- 		response.getWriter(), response getOutPutSteam(), <%= %>, out --%>
	
	</pre>
</body>
</html>