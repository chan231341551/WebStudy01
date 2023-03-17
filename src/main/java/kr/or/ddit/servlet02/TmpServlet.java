package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.tmpl")
public class TmpServlet extends HttpServlet {
	
	private ServletContext application;
	
	@Override
	public void init() throws ServletException {
		super.init();
		application = getServletContext();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("TmpServlet 왔다");
		StringBuffer tmplSrc = readTemplate(req,resp);
		
		if(tmplSrc == null) {
			System.err.println("템플릿 소스를 읽을수 없음.");
			return;
		}
		/*Map<String, Object> scope = new HashMap<>();
		scope.put("now", new Date());*/
		
		String html = evaluateVariables(tmplSrc,req);
		//String html = (String) req.getAttribute("cPath");
		PrintWriter out = resp.getWriter();
		out.println(html);
		
	}

	private String evaluateVariables(StringBuffer tmplSrc, HttpServletRequest scope) {
		
		System.out.println("evaluateVariables 왔다");
		String evalPattern = "#([a-zA-Z0-9_]+)#";
		Pattern regex = Pattern.compile(evalPattern);
		Matcher matcher = regex.matcher(tmplSrc);
		StringBuffer finalHtml = new StringBuffer();
		while(matcher.find()) {
			String varName = matcher.group(1); // now를 가지고있음
			Object value = scope.getAttribute(varName); // now를 꺼냄
			matcher.appendReplacement(finalHtml, Objects.toString(value,"")); // null문자가 공백으로 치환됨
		}
		matcher.appendTail(finalHtml);
		return finalHtml.toString();
	}

	private StringBuffer readTemplate(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
	
		System.out.println("readTemplate 왔다");
		resp.setContentType("text/html;charset=UTF-8");
		String tmplPath = req.getServletPath();
		String realPath = application.getRealPath(tmplPath);
		File tmplFile = new File(realPath);
		
		if(!tmplFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,tmplPath+"을 찾을수 없음.");
			return null;
		}
		
		FileReader reader = new FileReader(tmplFile);
		BufferedReader br = new BufferedReader(reader);
		String temp = null;
		StringBuffer tmplSrc = new StringBuffer();
		while((temp = br.readLine()) != null) {
			tmplSrc.append(temp+"\n");
		}
		return tmplSrc;
	}
}
