package com.myweb.util;

public class Criteria {
	
	private int pageNum;  //페이지 번호
	private int count;    //몇개의 데이터를 보여줄지 결정
	
	//기본 생성자
	public Criteria() {
		//최초 게시판에 진입할 때, 기본값 1번 페이지, 10개의 데이터로 세팅
		this.pageNum = 1;
		this.count = 10;
	}
	
	//초기화 생성자
	public Criteria(int pageNum, int count) {
		super();
		this.pageNum = pageNum;
		this.count = count;
	}

	/*DB에 정보를 얻어올 경우에 게시물 시작 포인트 지정*/
	public int getPageStart() {
		return ((pageNum - 1)*count)+1;
	}
	//게터, 셋터 설정
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCount() {
		return count;
	}
	
	public int getCount_oracle() {
		return (pageNum * count);
	}
	

	public void setCount(int count) {
		this.count = count;
	}
	
	

}
