package kr.or.ddit.servlet06;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

@WebServlet(value = "/bts",loadOnStartup=1)
public class BTSServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext application = config.getServletContext();
		application.setAttribute("btsMembers", getBtsMemberList());
	
	}
	
	public Map<String, String[]> getBtsMemberList() {
		Map<String, String[]> members = new LinkedHashMap<>();
		int idx = 1;
		members.put("B00"+idx++,new String[] {"RM","/WEB-INF/views/bts/rm.jsp"});
		members.put("B00"+idx++,new String[] {"진","/WEB-INF/views/bts/jin.jsp"});
		members.put("B00"+idx++,new String[] {"슈가","/WEB-INF/views/bts/suga.jsp"});
		members.put("B00"+idx++,new String[] {"제이홉","/WEB-INF/views/bts/jhop.jsp"});
		members.put("B00"+idx++,new String[] {"지민","/WEB-INF/views/bts/jimin.jsp"});
		members.put("B00"+idx++,new String[] {"뷔","/WEB-INF/views/bts/bui.jsp"});
		members.put("B00"+idx++,new String[] {"정국","/WEB-INF/views/bts/jungkuk.jsp"});
		return members;
	}
	
	public String[] getMemberContent(String code) {
		
		String[] content = getBtsMemberList().get(code);
		return content;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		2.모델확보
		Map<String, String[]> bts = getBtsMemberList();
		System.out.println("bts : "+ bts);
		
		
//		3.모델공유
		req.setAttribute("bts", bts);
		
//		4.뷰 선택
		String viewName = "";
		viewName = "/jsonView.do";
//		5.뷰 이동
		req.getRequestDispatcher(viewName).forward(req, resp);
	}
}
