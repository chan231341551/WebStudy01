package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/05/getServerTime")
public class GetServerTimeControllerServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 요청 의 조건 : 헤더(Accept),파라미터(locale)
		String accept = req.getHeader("Accept"); 
		String locale = req.getParameter("locale"); 
		System.out.println("locale : "+locale);
		
		//파라미터 locale 에 따라 , 로케일 객체 변경.
		Locale clientLocale = req.getLocale();
		if(clientLocale != null && !locale.isEmpty()) {
			clientLocale = Locale.forLanguageTag(locale);
		}
		//2.
		Date now = new Date();
		String nowStr = String.format(clientLocale, "now : %tc", now);
		//3.
		System.out.println("nowStr : "+nowStr);
		req.setAttribute("now", nowStr);
		req.setAttribute("message", nowStr);
		resp.setHeader("Refresh", "1");
		
		
		//4.
		//헤더 accept 에 따라 path가 변경
		
		String viewName = null;
		if(accept.contains("json")) {
			viewName = "/jsonView.do";
		}
		else if(accept.contains("plain")) {
			viewName = "/WEB-INF/views/04/plainView.jsp";
		}
		else {
			resp.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}
		
		if(viewName==null && !resp.isCommitted()) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	}
}
	
		/*
		String path = null;
		int statusCode = HttpServletResponse.SC_OK; // 상태코드
		//4.view 선택
		if(accept.contains("plain")) {
			path = "/WEB-INF/views/04/plainView.jsp";
		} 
		else if(accept.contains("json")) {
			path = "/jsonView.do";
		} 
		else {
			statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE; // 상태코드
		}
		
		if(statusCode == HttpServletResponse.SC_OK) {
			//5.view 이동
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else {
			resp.sendError(statusCode,accept+"mime은 생성할수 없음");
		}
	}*/

	


	

