package kr.or.ddit.servlet04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.ddit.servlet04.service.PropertiesService;
import kr.or.ddit.servlet04.service.PropertisServiceImpl;

@WebServlet("/03/props/propsView.do")
public class PropertiesControllerServlet2 extends HttpServlet{
	private PropertiesService service = new PropertisServiceImpl();
	
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
		   throws ServletException, IOException {
      String accept = req.getHeader("Accept"); // 헤더 요청
      System.out.println(accept);
      Object target = service.retrieveData(); // 모델 확보
      System.out.println(target);
      req.setAttribute("target", target); // 모델 공유
      
      String path = null;
      // view 선택
      if(accept.startsWith("*/*") || accept.toLowerCase().contains("html")) {
          path = "/WEB-INF/views/03/propsView.jsp";
      } 
      else if(accept.toLowerCase().contains("json")) {
    	  path = "/jsonView.do";
      } 
      else if(accept.toLowerCase().contains("xml")) {
    	  path = "/xmlView.do";
      } 
      // view 이동
      req.getRequestDispatcher(path).forward(req, resp);
   }

}