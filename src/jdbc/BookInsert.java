package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			
			
			
			String query = "insert into book values (seq_book.nextval, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(query);
			
			/*
			//insert into book values (seq_book.nextval, '우리들의 일그러진 영웅', '다림', '1998-02-22', 1);
			pstmt.setString(1, "우리들의 일그러진 영웅");
			pstmt.setString(2, "다림");
			pstmt.setDate(3, java.sql.Date.valueOf("1998-02-22"));
			pstmt.setInt(4, 1);
			*/
			
			/*
			//insert into book values (seq_book.nextval, '삼국지', '민음사', '2002-03-01', 1);
			pstmt.setString(1, "삼국지");
			pstmt.setString(2, "민음사");
			pstmt.setDate(3, java.sql.Date.valueOf("2002-03-01"));
			pstmt.setInt(4, 1);
			*/
			
			/*
			//insert into book values (seq_book.nextval, '토지', '마로니에북스', '2012-08-15', 2);
			pstmt.setString(1, "토지");
			pstmt.setString(2, "마로니에북스");
			pstmt.setDate(3, java.sql.Date.valueOf("2012-08-15"));
			pstmt.setInt(4, 2);
			*/
			
			/*
			//insert into book values (seq_book.nextval, '유시민의 글쓰기 특강', '생각의길', '2015-04-01', 3);
			pstmt.setString(1, "유시민의 글쓰기 특강");
			pstmt.setString(2, "생각의길");
			pstmt.setDate(3, java.sql.Date.valueOf("2015-04-01"));
			pstmt.setInt(4, 3);
			*/
			
			/*
			//insert into book values (seq_book.nextval, '패션왕', '중앙북스(books)', '2012-02-22', 4);
			pstmt.setString(1, "패션왕");
			pstmt.setString(2, "중앙북스(books)");
			pstmt.setDate(3, java.sql.Date.valueOf("2012-02-22"));
			pstmt.setInt(4, 4);
			*/
			
			/*
			//insert into book values (seq_book.nextval, '순정만화', '재미주의', '2011-08-03', 5);
			pstmt.setString(1, "순정만화");
			pstmt.setString(2, "재미주의");
			pstmt.setDate(3, java.sql.Date.valueOf("2011-08-03"));
			pstmt.setInt(4, 5);
			*/
			
			/*
			//insert into book values (seq_book.nextval, '오직두사람', '문학동네', '2017-05-04', 6);
			pstmt.setString(1, "오직두사람");
			pstmt.setString(2, "문학동네");
			pstmt.setDate(3, java.sql.Date.valueOf("2017-05-04"));
			pstmt.setInt(4, 6);
			*/
			
			//insert into book values (seq_book.nextval, '26년', '재미주의', '2012-02-04', 5);
			pstmt.setString(1, "26년");
			pstmt.setString(2, "재미주의");
			pstmt.setDate(3, java.sql.Date.valueOf("2012-02-04"));
			pstmt.setInt(4, 5);
			
			int count = pstmt.executeUpdate();
			
			// 4.결과처리
			System.out.println(count+ "건이 실행되었습니다.");
			
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


	}

}
