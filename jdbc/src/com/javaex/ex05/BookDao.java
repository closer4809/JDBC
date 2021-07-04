/* 메소드 파라미터를 Vo사용 */

package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@3.36.114.215:1521:xe";
	private String id = "webdb2";
	private String pw = "webdb2";

	
	
	// 생성자

	// 메소드-gs

	// 메소드-일반
	// DB연결
	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 자원정리
	private void close() {
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

	//  삭제하기
	public int bookDelete(int bookId) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);

			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.close();

		return count;
	}

	// 작가 수정하기
	public int bookUpdate(BookVo bookVo) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " update book ";
			query += " set    title = ?, ";
			query += "        pubs = ?, ";
			query += "        pub_date = ?, ";
			query += "        author_id = ? ";
			query += " where  book_id = ? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			pstmt.setInt(5, bookVo.getBookId());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 4.결과처리
		System.out.println(count + "건 수정");

		close();

		return count;
	}

	// 작가 등록하기
	public int bookInsert(BookVo bookVo) {

		int count = -1;

		getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " insert into book ";
			query += " values(seq_book_id.nextval, ?, ?, ?, ?) ";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		close();

		return count; // 성공갯수 리턴
	}

	// 작가 리스트 가져오기
	public List<BookVo> getBookList() {
		// DB값을 가져와서 ArrayList로 전달

		// 리스트 생성
		List<BookVo> bookList = new ArrayList<BookVo>();

		
		getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select  bo.book_id, ";
			query += "         bo.title, ";
			query += "         bo.pubs, ";
			query += "         bo.pub_date, ";
			query += "         bo.author_id, ";
			query += "         au.author_name, ";
			query += "         au.author_desc ";
			query += " from    book bo, author au ";
			query += " where  au.author_id = bo.author_id ";
			
			
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				
				
				BookVo bookVo = new BookVo(authorId, authorName, authorDesc, bookId, title, pubs, pubDate );

				bookList.add(bookVo);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		close();

		return bookList;

	}

}
