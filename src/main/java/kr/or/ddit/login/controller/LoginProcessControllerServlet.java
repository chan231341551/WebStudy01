package kr.or.ddit.login.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.vo.MemberVO;


@WebServlet("/login/loginProcess.do")
public class LoginProcessControllerServlet extends HttpServlet{
	/**
	 * 1. 검증에 통과하지 못했을 경우, 다시 로그인 폼으로 이동함.
	 * 2. 인증에 통과하지 못했을 경우, 다시 로그인 폼으로 이동함.
	 *  - 비밀번호 오류 상태를 가정하고, 메시지 전달.
	 * 	- 이전에 입력받은 아이디의 상태를 유지함.
	 * 3. 인증 완료시 웰컴 페이지 이동함.
	 * 
	 * 
	 */
	private boolean authenticate(MemberVO member) {
		
		return member.getMemId().equals(member.getMemPass());
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		1.
		HttpSession session = req.getSession();
		if(session.isNew()) { // 세션 검증
			resp.sendError(400,"로그인 폼이 없는데 어떻게 로그인 합니까? 예?");
		}
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		String saveId = req.getParameter("saveId");
		
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		member.setMemPass(memPass);
		
		boolean valid = validate(member);	
		
		
		String viewName = null;
		if(valid) { // 검증 통과
//			2.
			if(authenticate(member)) { // 인증 통과
				
				Cookie saveIdCookie = new Cookie("saveId", member.getMemId());
				saveIdCookie.setDomain("localhost");
				saveIdCookie.setPath(req.getContextPath());
				int maxAge = 0;
				
				if(StringUtils.isNotBlank(saveId)) {
					maxAge = 60*60*24*5;
				}
				
				saveIdCookie.setMaxAge(maxAge);
				resp.addCookie(saveIdCookie);
				
				session.setAttribute("authMember", member);
//				3.
				viewName = "redirect:/";
			}
			else { // 인증 실패
//				3.
				session.setAttribute("validId", memId);
				session.setAttribute("message", "비밀번호 오류");
				viewName = "redirect:/login/loginForm.jsp";
			}
		}
		else { // 검증 실패
//			3.
			session.setAttribute("message", "아이디나 비밀번호 누락");
			viewName = "redirect:/login/loginForm.jsp";
		}
		
//		5.
		if(viewName.startsWith("redirect:")) { /*?*/
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath()+viewName);
		}
		else {
			req.getRequestDispatcher(viewName).forward(req, resp);
		}
 	}

	private boolean validate(MemberVO member) {
		
		boolean valid = true;
		
		if(StringUtils.isBlank(member.getMemId())) { /*?*/
			valid = false;
		}
		if(StringUtils.isBlank(member.getMemPass())) { /*?*/
			valid = false;
		}
		
		return valid;
	}
}
