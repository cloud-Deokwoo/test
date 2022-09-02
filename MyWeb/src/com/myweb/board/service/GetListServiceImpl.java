package com.myweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;
import com.myweb.util.Criteria;
import com.myweb.util.PageVO;

public class GetListServiceImpl implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		//DAO객체 생성
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardVO> list = dao.getList(); //목록조회 결과를 list형태로 받는 메서드
		//다음 화면에서 사용하기 위해서... request객체 강제 저장
		request.setAttribute("list", list);
		*/
		
		//페이징처리
		BoardDAO dao = BoardDAO.getInstance();
		
		Criteria cri = new Criteria();  //기본값 : pageNum =1, count=10
		
		if(request.getParameter("pageNum")!=null) {
			
			String pageNum = request.getParameter("pageNum");
			cri.setPageNum(Integer.parseInt(pageNum));
		}
		
		if(request.getParameter("cnt")!=null) {
			
			String cnt = request.getParameter("cnt");
			cri.setPageNum(Integer.parseInt(cnt));
		}
		
		ArrayList<BoardVO> list = dao.getList(cri); //기존 getList()메소드 변경
		
		request.setAttribute("list", list);
		
		//화면에 보여질 페이지 버튼 계산 처리
		int total = dao.getTotal();
		
		PageVO vo = new PageVO(total, cri);
		
		request.setAttribute("pageVO", vo);

	}

}
