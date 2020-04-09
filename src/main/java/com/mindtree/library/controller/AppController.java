package com.mindtree.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mindtree.library.entity.Book;
import com.mindtree.library.entity.Library;
import com.mindtree.library.service.BookService;
import com.mindtree.library.service.LibraryService;
import com.mindtree.library.service.NameAlraedyExistsException;
import com.mindtree.library.service.NameNotCorrectException;

@Controller
public class AppController {
	@Autowired 
	private LibraryService libraryService;
	@Autowired
	private BookService bookService;
@RequestMapping(value = "/")
public String viewLibrary(Model model) {
	List<Library> library=libraryService.viewAll();
	model.addAttribute("library", library);
	return "index";
}
@RequestMapping("/addLibrary")
public String showAddLibraryPage(Model model) {
   Library library=new Library();
  model.addAttribute("library",library);
    return "addLibrary";
}
@RequestMapping(value = "/save", method = RequestMethod.POST)
public String saveLibarary(@ModelAttribute("library") Library library, Model model) {
    try {
		try {
			libraryService.addLibrary(library);
		} catch (NameAlraedyExistsException e) {
			String error=e.getLocalizedMessage();
			model.addAttribute("error", error);
			return "NameException";
		}
		
	} catch (NameNotCorrectException e) {
		String error=e.getLocalizedMessage();
		model.addAttribute("error", error);
		return "NameException";
	}
    return "redirect:/";
}
@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
public String showEditLibrayPage(@PathVariable(name = "id") int id,Model model) {
	//System.out.println(id);
    List<Book> book= bookService.viewBooks(id);
    model.addAttribute("book", book);
    model.addAttribute("id", id);
    return "updateLibrary";
}
@RequestMapping(value = "/add/{id}")
public String saveBook(@PathVariable(name = "id") int id,Model model) {
  Book book=new Book();
  model.addAttribute("book", book);
  model.addAttribute("id",id);
  return "addBook";
}
@RequestMapping(value = "/addBook/{id}",method = RequestMethod.POST)
public String saveBooks(@PathVariable(name = "id") int id,@ModelAttribute("book") Book book) {
	System.out.println(id);
	bookService.addBook(id, book);
	    return "redirect:/";
}

@RequestMapping(value = "/deleteId")
public String deleteLibrary(@ModelAttribute("index") String index) {
		  int id=Integer.parseInt(index);
		  libraryService.deleteId(id);
	return "redirect:/";
	 
}
@RequestMapping(value = "/delete")
public ModelAndView deleteLibraryForm() {
	
	List<Library> library=libraryService.viewAll();
	ModelAndView mav = new ModelAndView("delete");
	mav.addObject("library", library);
	return mav;
}

@RequestMapping("/update/{id}")
public ModelAndView showEditLibraryPage(@PathVariable(name = "id") int id) {
    ModelAndView mav = new ModelAndView("edit");
    Library lib=libraryService.getId(id);
    mav.addObject("lib", lib);
     
    return mav;
}
@RequestMapping("/edit/{id}")
public ModelAndView showEditBookPage(@PathVariable(name = "id") int id) {
    ModelAndView mav = new ModelAndView("editBook");
    Book book=bookService.getId(id);
    mav.addObject("book", book);
    return mav;
}
@RequestMapping(value = "/add",method = RequestMethod.POST)
public String editBooks(@ModelAttribute("book") Book book) {
	//System.out.println(id);
	Book books=bookService.updateBook(book);
		/* int id=books.getLibrary().getId() */
	    return "redirect:/";
}
@RequestMapping(value = "/deleteFromBook/{id}")
public ModelAndView deleteBookView(@PathVariable(name = "id") int id) {
	//System.out.println(id);
	List<Book> books=bookService.viewAll(id);
	ModelAndView mav = new ModelAndView("deleteBook");
	mav.addObject("books",books);
	return mav;
}

@RequestMapping("/deleteBook")
public String deleteBook(@ModelAttribute("index") String index) {
	System.out.println(index);
	  int id=Integer.parseInt(index);
   bookService.deleteId(id);
    return "redirect:/";       
}
}
