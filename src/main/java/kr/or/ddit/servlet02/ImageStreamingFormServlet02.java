package kr.or.ddit.servlet02;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/imageForm.do")
public class ImageStreamingFormServlet02 extends HttpServlet {
	
	private ServletContext application;
	private String imageFolder;
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
		imageFolder = application.getInitParameter("imageFolder");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8"); // 출력스트림 개방 전에 선언한다.
		
		File folder = new File(imageFolder);
		File[] imageFiles = folder.listFiles((dir,name)->{
			String mime = application.getMimeType(name);
			return mime != null && mime.startsWith("image/");
		});
		
		String pattern = "<option>%s</option>\n";
		StringBuffer options = new StringBuffer();
		for(File tmp :imageFiles) {
			options.append(String.format(pattern, tmp.getName()));
		}
		String cPath = req.getContextPath();
		req.setAttribute("cPath", cPath);
		req.setAttribute("options", options);
		
		String viewName = "/WEB-INF/views/01/imageForm.jsp";
		req.getRequestDispatcher(viewName).forward(req, resp);
		/*String path = "/01/imageForm.tmpl";
		req.getRequestDispatcher(path).forward(req, resp);*/
		
		
		
	}
}
