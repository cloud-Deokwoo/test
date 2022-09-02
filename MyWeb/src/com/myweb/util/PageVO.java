package com.myweb.util;

public class PageVO {
	
	//화면에 그려질 버튼의 개수를 계산하는 클래스 
	private int startPage; 	//시작번호
	private int endPage;    //끝번호
	private int total;		//전체 게시물 수

	private int pageNum;	//현재 조회하는 페이지
	
	private	boolean next;	//다음버튼 활성화 여부
	private boolean prev; 	//이전버튼 활성화 여부
	
	private Criteria cri; 	//페이징 기준
	
	//생성자가 기본생성자로 생성하지 못하고, total과 Criteria를 받아서 계산하도록 처리
	public PageVO(int total, Criteria cri) {
		//전달되는 매개변수에서 초기값을 저장
		this.total = total;
		this.cri = cri;
		this.pageNum = cri.getPageNum();
		
		//끝페이지 계산(페이지 번호)
		//조회하는 페이지 번호가 1 ~ 10인 경우 => 10
		//조회하는 페이지 번호가 11 ~ 20인 경우 => 20
		//사용하는 계산 메서드는 Math.ceil을 사용
		//계산식 = (int)Math.ceil(조회하는 페이지 번호 / (double)10) * 10
		this.endPage = (int)Math.ceil(cri.getPageNum()/(double)10) * 10;
		
		//시작페이지 계산
		this.startPage = endPage - 9;
		
		//realEnd : 페이지 번호가 endPage보다 적을 때 나타내는 진짜 끝번호
		int realEnd = (int)Math.ceil(this.total/(double)10);
		
		//마지막 페이지세팅으로 보여줘야 할 번호
		if(this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		//이전페이지 버튼 활성화 여부 
		//startPage => 1, 11, 21, 31, .... 
		//startPage가 1보다 크다면 true
		this.prev = startPage > 1;
		
		//다음페이지 버튼 활성화 여부
		this.next = realEnd > this.endPage;
		
	}

	//getter, setter 설정
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	
	
	
}
