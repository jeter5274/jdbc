package com.javaex.author01;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorList;
		
		//int count = authorDao.authorInsert("이문열", "경북영양");
		//System.out.println(count+ " 건 등록되었습니다." );

		//작가 등록
		
		AuthorVo authorVo01 = new AuthorVo("이문열", "경북 영양");
		authorDao.authorInsert(authorVo01);
		
		AuthorVo authorVo02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(authorVo02);
		
		AuthorVo authorVo03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(authorVo03);
		
		
		//리스트 전체 출력
		authorList = authorDao.getAuthorList(); //리스트를 불러옴
		
		System.out.println("=========작가 리스트=========");
		for(int i=0; i<authorList.size(); i++) {
			System.out.println(authorList.get(i).authorId+ ". " +authorList.get(i).authorName+ ", " +authorList.get(i).authorDesc);
		}
		
		//작가 삭제
		authorDao.authorDelete(3);
		
		//리스트 전체 출력
		authorList = authorDao.getAuthorList(); // 리스트를 불러옴

		System.out.println("=========작가 리스트=========");
		for(int i=0; i<authorList.size(); i++){
			System.out.println(authorList.get(i).authorId+ ". " +authorList.get(i).authorName+ ", " +authorList.get(i).authorDesc);
		}
		
		//수정
		AuthorVo authorVo04 = new AuthorVo(2, "김경리", "제주도");
		authorDao.authorUpdate(authorVo04);
		
		//리스트 전체 출력
		authorList = authorDao.getAuthorList(); // 리스트를 불러옴

		System.out.println("=========작가 리스트=========");
		for (int i = 0; i < authorList.size(); i++) {
			System.out.println(authorList.get(i).authorId + ". " + authorList.get(i).authorName + ", " + authorList.get(i).authorDesc);
		}
	}

}
