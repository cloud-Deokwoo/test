package com.myweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
 * 다른 페이지에 접근할 수 있게 만들어보자... 
 * 네비게이트 바에서 "Memeber"를 클릭했을 때에 /member/member.jsp 로 접근할 수 있게
 * 만들어 주세요. index.do로 접근시에도 무조건 Home에 접근 가능해야 함. 
 */


@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		
		if(command.equals("/member.do")) {
//		if(session != null) {
//			if(session.getAttribute("user_id") == null) { //로그인X
//				res.sendRedirect("/MyWeb/user/user_login.jsp");
//				return;
//			}
//		}
			RequestDispatcher dp =request.getRequestDispatcher("member/member.jsp");
			dp.forward(request, response);
			
		}else {
			RequestDispatcher dp =request.getRequestDispatcher("index.jsp");
			dp.forward(request, response);
		}
		
		
		
		
	}


}
