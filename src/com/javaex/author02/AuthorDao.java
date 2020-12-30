package com.javaex.author02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//생성자
	
	//메소드 getter/setter
	
	//메소드 일반
	
	//DB접속
	private void getConnection() {
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			System.out.println("[접속성공]");
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	//자원정리
	private void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	//작가 저장 기능
	public int authorInsert(AuthorVo authorVo) {
		
		int count = 0;
		
		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into author";
			query += " values (seq_author.nextval, ?, ?)";
			//System.out.println(query); //테스트
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, authorVo.authorName);
			pstmt.setString(2, authorVo.authorDesc);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[dao] " +count+ "건 저장");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		//자원정리
		close();

		return count;
	}
	
	
	//작가 수정
	public int authorUpdate(AuthorVo authorVo) {
		
		int count = 0;
		
		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update author";
			query += " set author_name = ?,";
			query += " author_desc = ?";
			query += " where author_id = ?";
			//System.out.println(query); //테스트

			pstmt = conn.prepareStatement(query); 

			pstmt.setString(1, authorVo.authorName);	
			pstmt.setString(2, authorVo.authorDesc);	
			pstmt.setInt(3, authorVo.authorId);	
			
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[dao] " +count+ "건 수정");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//자원정리
		close();

		return count;
		
	}
	//작가 삭제하기
	public int authorDelete(int authorId) {
		
		int count = 0;
		
		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete author";
			query += " where author_id = ?";
			//System.out.println(query); //테스트
			
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[dao] " +count+ "건 삭제");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
		
		//자원정리
		close();

		return count;
		
	}
	
	//작가 리스트 가져오기
	public List<AuthorVo> getAuthorList() {
		
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select	author_id, ";
			query += " 			author_name,";
			query += " 			author_desc";
			query += " from author";
			//System.out.println(query); //테스트
			
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			//rs에 있는 데이터를 List<AuthorVo>로 구성해야 한다.
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				
				authorList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} 
	
		//자원정리
		close();
		
		return authorList;
	}
	

}
