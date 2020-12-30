package com.javaex.book01;

import java.sql.Date;
import java.util.List;

import com.javaex.book02.AuthorDao;
import com.javaex.book02.AuthorVo;

public class BookVo /*extends AuthorVo*/{ //잘 못된 의미

	//필드
	public int bookId, authorId;
	public String title, pubs, authorName, authorDesc;	//authorName, authorDesc 추가
	public Date pub_date;
	
	//생성자
	public BookVo() {}
	
	//Insert에서 사용
	public BookVo(String title, String pubs, String pub_date, int authorId) {
		this.authorId = authorId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = java.sql.Date.valueOf(pub_date);
	}
	/*
	public BookVo(String title, String pubs, String pub_date, int authorId) {
		this.authorId = authorId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = java.sql.Date.valueOf(pub_date);

		//쿼리문으로 불러오는 것이기 때문에 생성자에서 만들 필요가 없음
		//작가정보를 가져옴
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> authorVoList = authorDao.getAuthorList();
		super.authorName = authorVoList.get(authorId-1).authorName;
		super.authorDesc = authorVoList.get(authorId-1).authorDesc;
	}
	*/
	
	//BookVo.searchBook에서 사용
	public BookVo(int bookId, String title, String pubs, Date pub_date, String authorName) {
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorName = authorName;
	}
	
	//Update에서 사용
	public BookVo(int bookId, String title, String pubs, String pub_date, int authorId) {
		this.bookId = bookId;
		this.authorId = authorId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = java.sql.Date.valueOf(pub_date);
	}
	
	//BookVo.bookInsert에서 사용
	public BookVo(int bookId, String title, String pubs,Date pub_date, int authorId, String authorName, String authorDesc) {
		this.bookId = bookId;
		this.authorId = authorId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.authorId = authorId;
		this.authorName = authorName;
		this.authorDesc = authorDesc;
	}
	
	//메소드 - getter/setter
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public Date getPub_date() {
		return pub_date;
	}
	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorDesc() {
		return authorDesc;
	}
	public void setAuthorDesc(String authorDesc) {
		this.authorDesc = authorDesc;
	}

	//메소드 - 일반
	@Override
	public String toString() {
		return "BookVo [bookId=" + bookId + ", authorId=" + authorId + ", title=" + title + ", pubs=" + pubs
				+ ", pub_date=" + pub_date + "]";
	}
}
