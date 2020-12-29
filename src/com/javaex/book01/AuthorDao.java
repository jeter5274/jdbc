package com.javaex.book01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	//필드
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "webdb";
	String pw = "webdb";
	int count=0;
	
	//생성자
	
	//메소드 getter/setter
	
	//메소드 일반
	public int authorInsert(AuthorVo authorVo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
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
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		  
		    // 5. 자원정리
		    try {
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

		return count;
	}
	
	public List<AuthorVo> getAuthorList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<AuthorVo> authorVoList = new ArrayList<AuthorVo>();
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select	author_id, ";
			query += " 			author_name,";
			query += " 			author_desc";
			query += " from author";
			query += " order by author_id desc";
			
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
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		  
		    // 5. 자원정리
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
		return authorVoList;
	}
	
	public int authorUpdate(AuthorVo authorVo) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
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
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		  
		    // 5. 자원정리
		    try {
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

		return count;
	}
	
	public int authorDelete(int authorId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete author";
			query += " where author_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, authorId);
			
			count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println("[DAO] " +count+ "건 삭제");
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		  
		    // 5. 자원정리
		    try {
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

		return count;
	}
}
