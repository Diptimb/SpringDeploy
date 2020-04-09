package com.mindtree.library.service.LibServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.library.entity.Book;
import com.mindtree.library.entity.Library;
import com.mindtree.library.repository.BookRepository;
import com.mindtree.library.repository.LibraryRepository;
import com.mindtree.library.service.LibraryService;
import com.mindtree.library.service.NameAlraedyExistsException;
import com.mindtree.library.service.NameNotCorrectException;

@Service
public class LibraryServiceImpl implements LibraryService{

	
	@Autowired
	private LibraryRepository libraryRepo;
	@Autowired
	private BookRepository bookRepo;
	
	public Library addLibrary(Library library) throws NameNotCorrectException, NameAlraedyExistsException {
		List<Library> library1=libraryRepo.findAll();
		String name=library.getName();
		for(int i=0;i<name.length();i++) {
			char ch=name.charAt(i);
			if(!((ch>='a'&& ch<='z')||(ch>='A'&& ch<='B')||ch==' '))
				throw new NameNotCorrectException("Name not Correct");
		}
		for(int i=0;i<library1.size();i++) {
			if(library1.get(i).getName().equalsIgnoreCase(library.getName())==true)
				throw new NameAlraedyExistsException("Name already Exists");
				
		}
		return libraryRepo.save(library);
	}
	public List<Library> viewAll(){
		List<Library> library=libraryRepo.findAll();
		return library;
	}
	
	public Library getId(int id) {
		return libraryRepo.findById(id).get();
	}
	public void deleteId(int id) {
		List<Book> books=bookRepo.findAll();
		for(Book b:books) {
			if(b.getLibrary()==null)
				continue;
			else if(b.getLibrary().getId()==id) {
				b.setLibrary(null);
				bookRepo.save(b);
			}
		}
		libraryRepo.deleteById(id);
	}
}
