package com.javaex.book01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	
	//필드
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "webdb";
	String pw = "webdb";
	int count=0;
	
	//생성자
	
	//메소드 - getter/setter
	
	//메소드 일반
	public int bookInsert(BookVo bookVo) {
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
			query += " insert into book";
			query += " values (seq_book.nextval, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bookVo.title);
			pstmt.setString(2, bookVo.pubs);
			pstmt.setDate(3, bookVo.pub_date);
			pstmt.setInt(4, bookVo.authorId);
			
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
	
	public List<BookVo> getBookList(){
		List<BookVo> bookVoList = new ArrayList<BookVo>();
	
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select	bo.book_id,";
			query += " 			bo.title,";
			query += " 			bo.pubs,";
			query += " 			bo.pub_date,";
			query += " 			bo.author_id,";
			query += " 			au.author_name,";
			query += " 			au.author_desc";
			query += " from book bo left outer join author au ";
			query += " on bo.author_id = au.author_id ";
			query += " order by bo.book_id asc ";
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				Date pubDate = rs.getDate("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getNString("author_name");
				String authorDesc = rs.getNString("author_desc");
				
				BookVo vo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				
				bookVoList.add(vo);
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
		return bookVoList;
	}
	
	public int bookUpdate(BookVo bookVo) {
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
			query += " update book";
			query += " set title = ?,";
			query += " pubs = ?,";
			query += " pub_date = ?,";
			query += " author_id = ?";
			query += " where book_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bookVo.title);
			pstmt.setString(2, bookVo.pubs);
			pstmt.setDate(3, bookVo.pub_date);
			pstmt.setInt(4, bookVo.authorId);
			pstmt.setInt(5, bookVo.bookId);
			
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
	
	//삭제
	public int bookDelete(int bookId) {
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
			query += " delete book";
			query += " where book_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, bookId);
			
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
