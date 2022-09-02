package com.myweb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.service.ContentServiceImpl;
import com.myweb.board.service.DeleteServiceImpl;
import com.myweb.board.service.GetListServiceImpl;
import com.myweb.board.service.IBoardService;
import com.myweb.board.service.RegisterServiceImpl;
import com.myweb.board.service.UpdateServiceImpl;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
		// .board로 끝나는 요청은 이 컨트롤러로 들어오게 처리
		// 1. get, post 요청은 하나의 메서드로 연결 -> doAction()으로 
		// 2. 컨텍스트 path를 제거 /board/list.board 요청으로 들어오면 
	    //    board_list화면(view)으로 이동할 수 있게 처리
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 컨트롤러로 들어온 명령을 구분하는 처리 구간
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();//요청주소 중 서버주소(http[s]://호스트)를 뺀 URI값
		String path = request.getContextPath();
		String command = uri.substring(path.length());
		
		//command에 다른 동작 구현... 
		System.out.println(command);
		
		
		IBoardService service = null;  //서비스 로직에서 사용할 빈객체
		
		if (command.equals("/board/list.board")) {  //글목록
			//게시글 목록을 가지고 화면으로 이동
			service = new GetListServiceImpl(); //테이블에 있는 모든 레코드 읽어오기
			service.execute(request, response);
			//아직 구현X ... 나중에 구현하여 forward로 결과값 전달 처리 예정
			
			//Forward를 통해서 저장된 list값을 목록페이지로 전달...
			RequestDispatcher dp = request.getRequestDispatcher("board_list.jsp");
			dp.forward(request, response);
			
		}else if(command.equals("/board/write.board")) {
			//글작성 페이지(VIEW)로 이동
			response.sendRedirect("board_write.jsp");
		}else if(command.equals("/board/register.board")) {
			//글등록을 위한 작업 시작... 
			//서비스 객체 생성
			service = new RegisterServiceImpl();
			service.execute(request, response);
			
			//글등록한 후에 글목록으로 이동 처리
			response.sendRedirect("list.board");  //다시 컨트롤러로 이동
		}else if(command.equals("/board/content.board")) { //상세보기 화면 요청
			//상세보기 화면 처리를 위한 정보를 얻어야 한다.... 
			service = new ContentServiceImpl();
			service.execute(request, response);
			
			RequestDispatcher dp = request.getRequestDispatcher("board_content.jsp");
			dp.forward(request, response);
		}else if(command.equals("/board/modify.board")) { //수정 화면 요청
			service = new ContentServiceImpl();
			service.execute(request, response);
			
			RequestDispatcher dp = request.getRequestDispatcher("board_modify.jsp");
			dp.forward(request, response);
			
		}else if(command.equals("/board/update.board")) {
			/*
			 * 1. updateServiceImpl 생성
			 * 2. 서비스 영역에서 num, title, content을 받아서 update()메서드를 실행
			 * 3. DAO의 update()에서는 update구분으로 데이터를 수정
			 * 4. 페이지 이름을 상세보기화면으로 연결(단, 필요한 값을 전달해야 합니다.)
			 * 
			 * sql = "update board set title=?,content=? where num=?";
			 * 
			 */
			service = new UpdateServiceImpl();
			service.execute(request, response);
			
			
			//4번
			String num = request.getParameter("num");
			response.sendRedirect("/MyWeb/board/content.board?num="+num);
		}else if(command.equals("/board/delete.board")) {
			/*
			 * 1. DeleteServiceImpl을 생성합니다.
			 * 2. 서비스 영역에서는 num을 받아서 delete() 메서드를 실행
			 * 3. DAO의 delete()에서는 delete구문으로 삭제
			 * 4. 페이지 이동은 목록으로 이동
			 */
			
			service = new DeleteServiceImpl();
			service.execute(request, response);
			
			response.sendRedirect("list.board");
		}
	}

}
