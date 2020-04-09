package com.mindtree.library.service.BookServiceImplement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mindtree.library.entity.Book;
import com.mindtree.library.entity.Library;
import com.mindtree.library.repository.BookRepository;
import com.mindtree.library.repository.LibraryRepository;
import com.mindtree.library.service.BookService;

public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepo;
	@Autowired
	private LibraryRepository libRepo;
	public List<Book> viewBooks(int id){
	    List<Book> books=new ArrayList<Book>();
		List<Book> book= bookRepo.findAll();
		for(Book b:book) {
			if(b.getLibrary()==null)
				continue;
			else if(b.getLibrary().getId()==id ) {
				books.add(b);
			}
			
		}
		return books;
	}
	public Book addBook(int id,Book book) {
		Library lib=libRepo.findById(id).get();
		book.setLibrary(lib);
	    return bookRepo.save(book);
	}
	public Book updateBook(Book book) {
		Book book2=bookRepo.findById(book.getId()).get();
		book2.setBookName(book.getBookName());
	    return bookRepo.save(book2);
	}
	public Book getId(int id) {
		Book book=bookRepo.findById(id).get();
		return book;
	}
	public void deleteId(int id) {
		bookRepo.deleteById(id);
	}
	public List<Book> viewAll(int id){
		List<Book> book= bookRepo.findAll();
		List<Book> books=new ArrayList<Book>();
		for(Book b:book) {
			if(b.getLibrary()==null)
				continue;
			else if(b.getLibrary().getId()==id)
				books.add(b);
		}
		return books;
	}
}
