package com.javaex.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		//ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    
			// 2. Connection 얻어오기 (소켓통신 준비)
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		    // 3. SQL문 준비 / 바인딩 / 실행
			// 3-1. SQL문 준비
			String query ="insert into author values (seq_author.nextval, ?, ?)";
			//query문의 ;은 붙이지 않음
			//값이 계속 변경되는 부분은 ?로 표시
			
			// 3-2. 바인딩
			pstmt = conn.prepareStatement(query); //쿼리로 만들기
			
			pstmt.setString(1, "전재철");
			pstmt.setString(2, "서울시 도봉구");
			//insert into author values (seq_author.nextval, '황일영', '서울시 도봉구');
			
			// 3-3. 실행 : 지금은 insert
			//insert, update, delete
			int count = pstmt.executeUpdate(); //성공 횟수가 return		
			//select
			//ResultSet rs = pstmt.executeQuery();	
			
		    // 4.결과처리
			System.out.println(count + "건이 저장되었습니다.");
			
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
		    // 5. 자원정리
		    try {
		    	/*
		        if (rs != null) {
		            rs.close();
		        } 
		        */               
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
