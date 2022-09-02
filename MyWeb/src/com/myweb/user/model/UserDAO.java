package com.myweb.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.myweb.util.JdbcUtil;

public class UserDAO {
	
	/*
	 * DAO는 단수 DB연동을 담당하는 클래스 
	 * 여러개 생성하도록 일반클래스 만들면, 메모리 과부하가 올 수 있습니다. 
	 * 싱글톤 패턴을 적용해서 객체를 1개로 제한
	 */
	
	private DataSource ds;
	private Context ct;
	
	//1. 스스로 객체를 멤버변수로 선언하고 1개로 제한
	private static UserDAO instance = new UserDAO();
	
	//2. 외부에서 객체를 생성할 수 없도록 생성자 private
	private UserDAO() {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			ct = new InitialContext(); //이니셜 Context를 생성
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/oracle");  //이니셜 Context내에서 "java:comp/env/jdbc/oracle"을 검색
		} catch (Exception e) {
			System.out.println("드라이버 호출시 에러 발생");
		}
	}
	
	//3. 외부에서 객체를 요구할 때 getter메서드만 써서 반환하게 처리
	public static UserDAO getInstance() {
		return instance;
	}
	
	//--------- 중복되는 코드를 멤버변수로 선언 -----------------
//	//DB연동을 위한 변수
//	private String url = "jdbc:oracle:thin:@192.168.100.29:1521/XEPDB1";
//	private String user = "myjsp";
//	private String password = "myjsp";
	
	//DB연동을 위한 객체 
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//DB연동을 위한 메서드를 구현
	
	//회원 확인 메서드 
	public int IdConfirm(String id) {
		int result = 0;
		
			String sql = "select * from users where id = ?";
			
			try {
//				conn = DriverManager.getConnection(url, user, password);
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) result = 1;
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) conn.close();
					if (pstmt != null) pstmt.close();
					if (rs != null) rs.close();						
				} catch (Exception e) {}
			}
		
		return result;
	}
	
	//회원가입 메서드
	public int join(UserVO vo) {
		int result = 0;
		
		String sql = "insert into users (id,pw,name,email,address)"
				+ "values(?,?,?,?,?)";
		
		try {
//			conn = DriverManager.getConnection(url, user, password);
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) conn.close();
			} catch (Exception e2) {}
		}
		
		
		
		return result;
	}

	
	//로그인 메서드 : 회원 정보(id,pw) 있는지 여부 확인
	public int login(String id, String pw) {
		int result = 0;
		
		String sql = "select * from users where id = ? and pw = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//회원 정보 얻어오는 메서드
	public UserVO getUserInfo(String id1) {
		UserVO vo = null;
		
		String sql = "select * from users where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id1);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				//문자열 데이터는 getString(컬럼명), 날짜형 데이터 getTimeStamp(컬럼)
				String id = rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				Timestamp regdate = rs.getTimestamp("regdate");
				
				vo = new UserVO(id, null, name, email, address, regdate);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	//패스워드 변경 메서드
	public int changePassword(String id, String pw) {
		int result = 0;
		
		String sql = "update users set pw = ? where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	//회원정보 수정 메서드(update())
	public int update(UserVO vo) {
		int result = 0;
		
		String sql = "update users set name=?,email=?,address=? "
				+ "where id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getId());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	
	//회원 탈퇴(삭제)
	public int delete(String id) {
		int result = 0;
		
		String sql = "delete from users where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	
	
	

}
