package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import static java.util.Calendar.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/10/calendar.do")
public class CalenderControllerServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String yearParam = req.getParameter("year");
		String monthParam = req.getParameter("month");
		String languageParam = req.getParameter("language");
		
		Locale clientLocale = req.getLocale();
		if(languageParam != null && !languageParam.isEmpty()) {
			clientLocale = Locale.forLanguageTag(languageParam);
			req.setAttribute("languageParam", languageParam);
		}
		
		Calendar calendar = Calendar.getInstance();
		
		try {
			if(yearParam != null && monthParam != null) {
				int year = Integer.parseInt(yearParam);
				int month = Integer.parseInt(monthParam);	
				calendar.set(YEAR,year);
				calendar.set(MONTH,month);
			}
			req.setAttribute("calendar", new CalendarWrapper(calendar,clientLocale));
			
			String viewName = "/WEB-INF/views/calendar.jsp";
			req.getRequestDispatcher(viewName).forward(req, resp);
			
		}catch (NumberFormatException e) {
			resp.sendError(400,e.getMessage());
			return;
		}
		
//		if(year != null && !year.matches("\\d{4}")) {
//			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//			return;
//		}
		
		
	}
	
}
