package com.javaex.ex05;

import java.util.List;



public class BookApp {

	//메소드 일반
	public static void main(String[] args) {
		
		// 북 리스트
		BookDao bookDao = new BookDao();
		List<BookVo> bookList; 
		
		//아서 리스트
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;
		
		
		//리스트출력     0x777
		//DB에서 데이터 가져오기
		bookList = bookDao.getBookList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		
		authorList = authorDao.getAuthorList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(bookList);
		
		
		
		//책 등록
		BookVo iBookVo = new BookVo("차라투라투스는 이렇게 말했다", "울림", "2000-01-01", 6);
		int iCount = bookDao.bookInsert(iBookVo);
		if(iCount>0) {
			System.out.println("[등록되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
		}
		
		//작가등록
		AuthorVo iAuthorVo = new AuthorVo("황일영", "하이미디어");
		int iCount2 = authorDao.authorInsert(iAuthorVo);
		if(iCount2>0) {
			System.out.println("[등록되었습니다.]");
		}else {
			System.out.println("[관리자에게 문의하세요(" + iCount + ")]");
		}
		
		
		
		//리스트출력     0x777
		//DB에서 데이터 가져오기
		bookList = bookDao.getBookList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(bookList);
		
		
		//책 삭제
		int dCount = bookDao.bookDelete(7);
		
		//작가 삭
		int dCount1 = authorDao.authorDelete(7);
		//리스출력
		//DB에서 가져오기
		bookList = bookDao.getBookList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(bookList);
		
	
		//책 수정  iCount 사용은 생략했음
		BookVo uBookVo = new BookVo("국부론", "울림", "2020-01-01", 1, 1);
		int uCount = bookDao.bookUpdate(uBookVo);
		
		//작가수
		AuthorVo uAuthorVo = new AuthorVo(3, "김일영", "강남하이디미어");
		int uCount2 = authorDao.authorUpdate(uAuthorVo);
		
		//리스출력
		//DB에서 가져오기
		bookList = bookDao.getBookList();
		//리스트를 for문으로 출력 --> 메소드로 정의
		printList(bookList);
		
		
		
	
	
	
	
	
		
	
	
	
	
	
	}
	
	
	//리스트 출력 메소드
	public static void printList(List<BookVo> bookList) {
		
		for(int i=0; i<bookList.size(); i++) {
			
			
			BookVo bookVo = bookList.get(i);
			System.out.println(bookVo.getBookId() + "," + bookVo.getTitle() + "," + bookVo.getPubs() + "," + bookVo.getPubDate() + ","+ bookVo.getAuthorId() + "," + bookVo.getAuthorName()+ ","+ bookVo.getAuthorDesc());
			

		}
		
		System.out.println("=================================");
		System.out.println("");
		
	}
	
	
	
	
	
	

}
