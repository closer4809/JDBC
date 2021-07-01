package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorDeleteApp {

	public static void main(String[] args) {
		//insert into author VALUES(sequ_author_id.nextval, '김영하', '알쓸신잡');
		
				// 0. import java.sql.*;
				Connection conn = null;
				PreparedStatement pstmt = null;
				//ResultSet rs = null; 지금은 리턴값이 없어서 쓰지않음.

				try {
				    // 1. JDBC 드라이버 (Oracle) 로딩
					Class.forName("oracle.jdbc.driver.OracleDriver");

				    // 2. Connection 얻어오기
					String url = "jdbc:oracle:thin:@3.36.114.215:1521:xe";
					conn = DriverManager.getConnection(url, "webdb", "manager");

					
				    // 3. SQL문 준비 / 바인딩 / 실행
				    String query = "";				//**쿼리문 문자열로 만들기 --> ? 주의**
				
				    query += " delete from author ";  //query = query + "insert into author"
				    query += " where author_id = ? "; //앞뒤로 공백넣어주기"
				
					
				    
					pstmt = conn.prepareStatement(query);	
					pstmt.setString(1, "1");   			
					
					
					int count = pstmt.executeUpdate();		//쿼리문 실행 --> 리턴값으로 성공여부 판단
					
					
				    // 4.결과처리
					System.out.println(count + "건이 저장되었습니다.");

					
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
	
