<%@page import="kr.or.ddit.servlet02.NowServlet"%>
<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="kr.or.ddit.servlet01.DescriptionServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/resourceIdentify.jsp</title>
</head>
<body>
	<h4>자원의 종류와 식별방법</h4>
	<pre>
		: 자원의 위치와 경로 표기 방법에 따라 분류
		
		1. File system resource : d:/contents/images/cat1.jpg
		<%
			String realPath = "d:/contents/images/cat1.jpg"; // 물리경로
			File file = new File(realPath);
		%>
		파일의 크기 : <%=file.length() %>
		2. Class path resource : /kr/or/ddit/images/cat2.png  <!-- 논리경로 -->
		<%
			String qualifiedName = "../images/cat2.png";
			realPath = NowServlet.class.getResource(qualifiedName).getFile(); // DescriptionServlet.class = 해당 클래스에 접근
			System.out.print(DescriptionServlet.class);
			//realPath = DescriptionServlet.class.getClassLoader().getResource("kr/or/ddit/images/cat2.png").getFile(); // 클래스패스가 고정되어있어서 /로 시작하지않음
 			File classPathResource = new File(realPath);
		%>
		실제 경로 : <%=realPath %>
		파일의 크기 : <%=classPathResource.length() %>
		3. Web resource : https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.7-ladc.gif
		
		http://localhost/WebStudy01/resources/js/jquery-3.6.1.min.js
		<% 
			//String resourceURL = "http://localhost/WebStudy01/resources/js/jquery-3.6.1.min.js";
			String resourceURL = "https://www.google.com/logos/doodles/2022/seasonal-holidays-2022-6753651837109831.3-law.gif";
		
			URL url = new URL(resourceURL);
			URLConnection conn = url.openConnection();
			String resourcePath = url.getPath();
			int lastIdx = resourcePath.lastIndexOf("/");
			String fileName = resourcePath.substring(lastIdx+1); // 마지막/이후 의 경로
			String folderPath = "d:/contents/images";
			File downloadFile = new File(folderPath,fileName);
			InputStream is = conn.getInputStream();
			Files.copy(is, Paths.get(downloadFile.getPath()), StandardCopyOption.REPLACE_EXISTING); // StandardCopyOption.REPLACE_EXISTING = 저장하는 위치에 똑같은 파일있으면 바꿔라
		%>
		<%=resourcePath %>
		
		*** 웹자원에 대한 식별성 : URI
		URI(Uniform Resource Identifier) 범용자원식별
		
		URL(Uniform Resource Location) 범용자원위치
		URN(Uniform Resource Name) 범용자원이름 (단점 : 중복이되면 곤란 , 모든이름이 등록되어있는 자원이 필요)
		URC(Uniform Resource Content) 범용자원조건
		
		URL 구조
		protocol(scheme)://IP(Domain):port/content/depth1...depthN/resourceName
		
		DomainName 
		3 level www.naver.com (GlobalTopLevel) : GTLD
		4 level www.naver.co.kr (NationalTopLevel) : NTL
		
		URL 표기 방식
		절대경로(**) : 최상위 루트부터 전체 경로 표현 - 생략가능한 요소가 존재
		client side : /WebStudy01/resources/images/cat1.jpg
					: context path 부터 시작됨.
		server side : /resources/images/cat1.jpg
					: context path 이후의 경로 표기.
		상대경로() : 기준점(브라우저의 현재 주소)을 중심으로 한 경로 표현
	</pre>
<%
	//InputStream is2 = application.getResourceAsStream("/resources/images/cat1.jpg");
	String realPath1 = application.getRealPath("/resources/images/cat1.jpg");
	String realPath2 = application.getRealPath(request.getContextPath()+"/resources/images/cat1.jpg");
	
	//request.getRequestDispatcher("/WEB-INF/views/depth1/text.jsp").forward(request, response);
	//response.sendRedirect(request.getContextPath()+"/member/memberForm.do");
%>
	<img src="<%=request.getContextPath() %>/resources/images/cat1.jpg">
	<img src="../resources/images/cat1.jpg"><br/>
	<img src="cat1.jpg">
	<%-- 서버 사이드 방식으로 접근한 파일의 크기 : <%=is2.available() %> --%>
	realPath1 : <%=realPath1 %> <br/>
	realPath2 : <%=realPath2 %>
</body>
</html>