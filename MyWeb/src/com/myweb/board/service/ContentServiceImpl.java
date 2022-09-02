package com.myweb.board.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class ContentServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 1. dao에 getContent(num) 메서드 생성하고, 호출
		 * 2. getContent()메서드에서는  num을 가지고, 게시글에 대한 정보를 조회해서
		 *   vo에 담는 코드를 작성
		 * 3. 메서드의 리턴 타입 BoardVO
		 * 4. 화면 전송을 위한 리턴값은 vo라는 이름으로 강제 저장
		 */
		
		String num = request.getParameter("num");
		String pageNum = request.getParameter("pageNum");
		
		BoardDAO dao = BoardDAO.getInstance();
		
		//2. 쿠키는 서버로 전송될 때 자동으로 request에 담겨서 전달됨. 쿠키검사
		Cookie[] arr = request.getCookies();
		
		boolean bool = true;   //기본적으로 글을 읽으면 hit값을 증가
		for(Cookie c : arr) {
			if(c.getName().equals("hitNum"+num)) { //쿠키 이름이 게시글 번호 쿠키인 확인
				bool = false;
				break;
			}
		}
		
		//조회수 업데이트
		if(bool) { //bool이 true이면 클릭한 적이 없다는 의미...  
			dao.upHit(num); //hit값 증가 메서드
		}
				
		BoardVO vo = dao.getContent(num);
		
		request.setAttribute("vo", vo);
		request.setAttribute("pageNum", pageNum);
		//조회수 처리하기 
		//1. 쿠키를 생성하여 거기에 hit값 처리를 담당함(why? 중복 증가 방지를 위해서)
		Cookie hitCoo = new Cookie("hitNum"+num, num); //num게시글 번호 
		hitCoo.setMaxAge(30);
		response.addCookie(hitCoo);

	}

}
