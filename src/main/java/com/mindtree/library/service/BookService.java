package com.mindtree.library.service;

import java.util.List;

import com.mindtree.library.entity.Book;


public interface BookService {
	public List<Book> viewBooks(int id);
	public Book addBook(int id,Book book);
	public Book updateBook(Book book1);
	public Book getId(int id);
	public void deleteId(int id) ;
	public List<Book> viewAll(int id);
}
