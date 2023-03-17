package kr.or.ddit.servlet01;
import java.io.*;
import javax.servlet.http.*;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;


public class ImageStreamingServlet extends HttpServlet{
	
	private String imageFolder;
	private ServletContext application;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
//		imageFolder = config.getInitParameter("imageFolder");
		application = getServletContext();
		imageFolder = application.getInitParameter("imageFolder");
		System.out.printf("받은 파라미터 : %s\n",imageFolder);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		// 가장 먼저 생성되고 가장 오래 유지됨  범위가 가장 넓음
	    ServletContext application = getServletContext(); 
	    
			    
		String imageName = req.getParameter("imgChoice");
		
		if(imageName == null || imageName.isEmpty()) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String mimeType = application.getMimeType(imageName);
		resp.setContentType(mimeType);
		
		File imageFile = new File(imageFolder, imageName);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		Cookie imageCookie = new Cookie("imageCookie",imageName);
		imageCookie.setDomain("localhost");
		imageCookie.setPath(req.getContextPath());
		int maxAge = 0;
		
		if(StringUtils.isNoneBlank(imageName)) {
			maxAge = 60*60*24*3;
		}
		imageCookie.setMaxAge(maxAge);
		resp.addCookie(imageCookie);
		
		FileInputStream fis = null; 
		OutputStream os = null; 
		try {
			
			
			fis = new FileInputStream(imageFile);
			os = resp.getOutputStream();
			int tmp = -1;
			while((tmp=fis.read()) != -1){
				os.write(tmp);
			}
		}finally {
			if(fis != null) {
				fis.close();
			}
			if(os != null) {
				os.close();	
			}
		}

		
	}
}