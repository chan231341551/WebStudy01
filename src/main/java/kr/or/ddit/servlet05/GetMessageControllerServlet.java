package kr.or.ddit.servlet05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/04/getGreetingMessage")
public class GetMessageControllerServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//1. 요청 분석(line,header,body)
		String accept = req.getHeader("Accept");
		String locale = req.getParameter("locale");
		
		Locale clientLocale = null;
		if(locale != null) {
//			ko, en : language tag , locale code
			clientLocale = Locale.forLanguageTag(locale);
		}
		else {
			clientLocale = req.getLocale(); // Accept-language header 로 결정됨.
		}
		String name = req.getParameter("name");
		if(name == null || name.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		System.out.println(name);
		//2. 모델확보
		Object message = null;
		Object keyList = null;
		try {
			//message = List<String>..
			keyList =  retrieveMessageList(clientLocale,name);
			message = retrieveMessage(clientLocale, name);
			System.out.println("keyList : "+ keyList);
		}catch (MissingResourceException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		
		}
		System.out.println(message);
		//3. 모델공유
		//Map<String, Object> target = Collections.singletonMap("message", message);
		req.setAttribute("message", message);
		req.setAttribute("keyList", keyList);
		
		String path = null;
		int statusCode = HttpServletResponse.SC_OK; // 상태코드
		//4.view 선택
		if(accept.contains("plain")) {
			path = "/WEB-INF/views/04/plainView.jsp";
		} 
		else if(accept.contains("json")) {
			path = "/jsonView.do";
		} 
		else if(accept.contains("xml")) {
			path = "/xmlView.do";
		}
		else {
			statusCode = HttpServletResponse.SC_NOT_ACCEPTABLE; // 상태코드
		}
		
		if(statusCode == HttpServletResponse.SC_OK) {
			//5.view 이동
			req.getRequestDispatcher(path).forward(req, resp);
		}
		else {
			resp.sendError(statusCode);
		}
	}

	private Object retrieveMessage(Locale clientLocale, String name) {
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, clientLocale);
		return bundle.getString(name);
	}
	
	private List<String> retrieveMessageList(Locale clientLocale, String name) {
		String baseName = "kr.or.ddit.props.Message";
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, clientLocale);
		
		Enumeration enums = bundle.getKeys();
		
		List<String> tempVOList = new ArrayList<String>();
		
		while(enums.hasMoreElements()) {
			TempVO tempVO = new TempVO();
			
			String key = (String)enums.nextElement();
			String value = bundle.getString(key);
			
			System.out.println("key : " + key + ", value : " + value);
			
			tempVO.setKey(key);
			tempVO.setValue(value);
			
			tempVOList.add(key);
		}
		
		return tempVOList;
	}
}
