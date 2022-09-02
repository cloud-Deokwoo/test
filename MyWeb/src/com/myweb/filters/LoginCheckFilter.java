package com.myweb.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * 다음 필터를 member디렉터리에 있는 페이지에 접근시 사용자 인증을 확인하는 필터로
 * 작성해 보세요!!(로그인한 사용자만 접근 가능... : 리다이렉트 경로는 login페이지로)
 * 추가) 인코딩 설정을 변경 -> utf-8로 접근하도록 설정하는 인코딩을 추가 작업해보세요.
 */
@WebFilter("/member.do")
public class LoginCheckFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession(); //세션 생성
		HttpServletResponse res = (HttpServletResponse)response;
		System.out.println("로그인 체크 필터 동작!!!");
		if(session != null) {
			if(session.getAttribute("user_id") == null) { //로그인X
				res.sendRedirect("/MyWeb/user/user_login.jsp");
				return;
			}
		}
		
		
		chain.doFilter(request, response);
		
	}

}
