package com.myweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.Criteria;
import com.myweb.util.JdbcUtil;

public class BoardDAO {
	
	//싱글톤 패턴으로 생성 및 기본 설정 생성... 
	
	//1. 스스로 객체를 멤버변수로 선언하고 1개 제한
	private static BoardDAO instance = new BoardDAO();
	
	//2. 외부에서 객체를 생성할 수 없더록 처리, 커넥션 풀에서 DB연동을 위한 작업
	private BoardDAO() {
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			System.out.println("커넥션 풀링 에러 발생");
			e.printStackTrace();
		}
	}
	
	//3. 외부에서 객체를 요구할 때에 getter메서드만 써서 반환
	public static BoardDAO getInstance() {
		return instance;
	}
	
	//---------- 중복되는 코드를 멤버변수로 선언 -----------------
	private DataSource ds;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	//---------- 이하 메서드 구현 부분 ------------------------
	
	public void regist(String writer, String title, String content) {
		
		String sql = "insert into board (writer, title, content) "
				+ "values(?,?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
	}
	
	//게시물 목록 조회 메서드
//	public ArrayList<BoardVO> getList(){
//		ArrayList<BoardVO> list = new ArrayList<>();
//		
//		//DB와 통신 결과(아래의 SQL을 이용한)를 BoardVO객체에 저장
//		//ArrayList에 추가하여 최종 결과를 반환값으로 처리... 
//		//sql = "select * from board order by num desc";
//		String sql = "select * from board order by num desc";
//		
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				//rs.getString("컬럼명"), rs.getInt("컬럼명"), rs.getTimeStamp()
//				//각각의 데이터 타입에 따라서 읽어들여 vo객체에 담아서 list에 저장
//				int num = rs.getInt("num");
//				String writer = rs.getString("writer");
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				Timestamp regdate = rs.getTimestamp("regdate");
//				int hit = rs.getInt("hit");
//				
//				BoardVO vo = new BoardVO(num, writer, title, content, regdate, hit);
//				
//				//생성된 vo를 list에 추가
//				list.add(vo);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(conn, pstmt, rs);
//		}
//		
//		
//		return list;
//	}
	
	//페이징 게시물 목록 처리 메서드
	public ArrayList<BoardVO> getList(Criteria cri){
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql = "select * from " + 
				"(select rownum as rnum, B1.* from board B1 "
				+ "where rownum <= ? order by num desc) where rnum >= ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cri.getCount_oracle());
			pstmt.setInt(2, cri.getPageStart());
			System.out.println(cri.getPageStart());
			System.out.println(cri.getCount_oracle());
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				
				BoardVO vo = new BoardVO(num, writer, title, content, regdate, hit);
				
				//리스트에 추가
				list.add(vo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		return list;
	}
	
	//getTotal()메서드 작성 : 총게시물 수를 반환
	public int getTotal() {
		int result = 0;
		
		String sql = "select count(*) as total from board";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("total");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		return result;
	}
	
	
	//getContent() 메서드 작성
	public BoardVO getContent(String num1) {
		BoardVO vo = null;
		
		String sql = "select * from board where num= ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num1);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp regdate = rs.getTimestamp("regdate");
				int hit = rs.getInt("hit");
				
				vo = new BoardVO(num, writer, title, content, regdate, hit);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	//update() 메서드 생성하기
	public void update(String num, String title, String content) {
		
		String sql = "update board set title=?,content=? where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
	}
	
	//delete() 메서드 생성
	public void delete(String num) {
		
		String sql = "delete from board where num = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
	}
	
	//조회수 업데이트
	public void upHit(String num) {
		
		String sql = "update board set hit=hit+1 where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
	}

}
