package com.javaex.book02;

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
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";
	int count=0;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//생성자
	
	//메소드 - getter/setter
	
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
	
	public int bookInsert(BookVo bookVo) {
		
		//DB접속
		getConnection();
		
		try {
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
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		
		//자원정리
		close();
		
		return count;
	}
	
	public List<BookVo> getBookList(){
		List<BookVo> bookVoList = new ArrayList<BookVo>();
	
		//DB접속
		getConnection();
		
		try {
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
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		//자원정리
		close();
		
		return bookVoList;
	}
	
	public int bookUpdate(BookVo bookVo) {
		
		//DB접속
		getConnection();
		
		try {
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
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 

		//자원정리
		close();
		
		return count;
	}
	
	//삭제
	public int bookDelete(int bookId) {

		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete book";
			query += " where book_id = ?";
			
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, bookId);
			
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
	
	
	public List<BookVo> searchBook(String search){
		List<BookVo> bookVoList = new ArrayList<BookVo>();
	
		//DB접속
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select	bo.book_id,";
			query += " 			bo.title,";
			query += " 			bo.pubs,";
			query += " 			bo.pub_date,";
			query += " 			au.author_name";
			query += " from book bo left outer join author au ";
			query += " on bo.author_id = au.author_id ";
			query += " where bo.title like ?";
			query += " or bo.pubs like ?";
			query += " or au.author_name like ?";
			query += " order by bo.book_id asc ";
			
			pstmt = conn.prepareStatement(query);
			
			search = "%"+search;
			search += "%";
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			
			rs = pstmt.executeQuery();

			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				Date pubDate = rs.getDate("pub_date");
				String authorName = rs.getNString("author_name");
				
				BookVo vo = new BookVo(bookId, title, pubs, pubDate, authorName);
				
				bookVoList.add(vo);
			}
			
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} 
		//자원정리
		close();
		
		return bookVoList;
	}
}
