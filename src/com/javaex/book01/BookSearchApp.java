package com.javaex.book01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSearchApp {

	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		List<BookVo> bookVoList = new ArrayList<BookVo>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("=======책 검색=======");
		System.out.print("검색어 : ");
		String search = sc.nextLine();
		
		bookVoList = bookDao.searchBook(search);
		
		System.out.println("===============검색 결과===============");
		for(int i=0;i<bookVoList.size(); i++) {
			System.out.println(bookVoList.get(i).bookId+ ". " +bookVoList.get(i).title+ ", " +bookVoList.get(i).pubs+ ", " +bookVoList.get(i).pub_date+ ", " +bookVoList.get(i).authorName);
		}
		
	}

}
