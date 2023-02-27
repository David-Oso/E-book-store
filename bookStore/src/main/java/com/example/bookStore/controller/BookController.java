package com.example.bookStore.controller;

import com.example.bookStore.entity.Book;
import com.example.bookStore.entity.MyBookList;
import com.example.bookStore.services.BookService;
import com.example.bookStore.services.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MyBookListService myBookListService;
    @GetMapping("/")
    public String home(){
        return "home";
    }
    @GetMapping("book_register")
    public String bookRegister(){
        return "bookRegister";
    }
    @GetMapping("available_books")
    public ModelAndView getAllBook(){
        List<Book> list = bookService.getAllBook();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("bookList");
//        modelAndView.addObject("book", list);
        return new ModelAndView("bookList", "book", list);
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book){
        bookService.save(book);
        return "redirect:/available_books";
    }
    @GetMapping("/my-books")
    public String getMyBooks(Model model){
        List<MyBookList> list = myBookListService.getAllMyBooks();
        model.addAttribute("book", list);
    return "myBooks";
    }
    @RequestMapping("/myList/{id}")
    public String getMyList(@PathVariable("id") Long id){
        Book book = bookService.getBookById(id);
        MyBookList myBookList = new MyBookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
        myBookListService.saveMyBook(myBookList);
        return "redirect:/my-books";
    }
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deletebBok(@PathVariable("id") Long id){
        bookService.deleteById(id);
        return "redirect:/available_books";
    }
}
