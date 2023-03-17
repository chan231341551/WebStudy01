package kr.or.ddit.servlet01;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;

public class ImageList extends HttpServlet{

   private String imageFolder;
   
   @Override
   public void init(ServletConfig config) throws ServletException {
	   super.init(config);
	   imageFolder = config.getInitParameter("imageFolder");
	   System.out.printf("받은 파라미터 : %s\n",imageFolder);
   }
   
   public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

      StringBuffer html = new StringBuffer();
      File file = new File(imageFolder);
      String[] list =  file.list();
      
      html.append("<html>\n");
      html.append("<body>\n");
      html.append("<form action=\'imageStreaming.do\' method=\'get\' id=\'myForm\'>\n");
      html.append("<input type=\"submit\">");
      html.append("</form>");
      html.append("<select name='imgChoice' form='myForm'>\n");
      for(int i=0; i<list.length; i++) {
    	  html.append("<option value="+list[i]+">"+list[i]+"</option>\n");
      }
      html.append("</select>\n");
      html.append("<script>\n");            
      html.append("</script>\n");
      html.append("</body>\n");
      html.append("</html>\n");
      
      resp.getWriter().println(html);
      
   }

}