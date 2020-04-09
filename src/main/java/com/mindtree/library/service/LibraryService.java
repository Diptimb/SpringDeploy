package com.mindtree.library.service;

import java.util.List;

import com.mindtree.library.entity.Library;


public interface LibraryService {
	public Library addLibrary(Library library) throws NameNotCorrectException, NameAlraedyExistsException ;
	public List<Library> viewAll();
	public Library getId(int id) ;
	public void deleteId(int id);
}
