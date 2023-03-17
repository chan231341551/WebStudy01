package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;


@WebServlet("/member/memberList.do")
public class MemberListControllerServlet extends HttpServlet {
	
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		2.
		List<MemberVO> memberList = service.retrieveMemberList();
		
//		3.
		req.setAttribute("memberList", memberList);
		
//		4.
		String viewName = "/WEB-INF/views/member/memberList.jsp";
		
//		5.
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			System.out.println("viewName : "+viewName);
			resp.sendRedirect(req.getContextPath()+viewName);
		}
		else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
	
	}
}
