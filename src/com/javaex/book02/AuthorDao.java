package com.javaex.book02;

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
	private int count=0;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//생성자
	
	//메소드 getter/setter
	
	//메소드 일반
	
	private void getConnection() {
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
	}
	
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
	
	public int authorInsert(AuthorVo authorVo) {

		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into author";
			query += " values (seq_author.nextval, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, authorVo.authorName);
			pstmt.setString(2, authorVo.authorDesc);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[DAO] " +count+ "건 등록");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		  
		//자원정리
		close();

		return count;
	}
	
	public List<AuthorVo> getAuthorList(){

		List<AuthorVo> authorVoList = new ArrayList<AuthorVo>();
		
		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select	author_id, ";
			query += " 			author_name,";
			query += " 			author_desc";
			query += " from author";
			query += " order by author_id asc";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				
				authorVoList.add(vo);
			}
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		//자원정리
		close();
		
		return authorVoList;
	}
	
	public int authorUpdate(AuthorVo authorVo) {

		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update author";
			query += " set author_name = ?,";
			query += " author_desc = ?";
			query += " where author_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, authorVo.authorName);
			pstmt.setString(2, authorVo.authorDesc);
			pstmt.setInt(3, authorVo.authorId);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[DAO] " +count+ "건 수정");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		//자원정리
		close();
		
		return count;
	}
	
	public int authorDelete(int authorId) {

		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete author";
			query += " where author_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, authorId);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[DAO] " +count+ "건 삭제");
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		//자원정리
		close();

		return count;
	}
}
