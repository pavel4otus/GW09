package ru.pavel2107.otus.hw09.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import ru.pavel2107.otus.hw09.domain.Book;
import ru.pavel2107.otus.hw09.service.BookService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    private BookService service;

    @Autowired
    public BookController( BookService service){
        this.service = service;
    }

    @RequestMapping( value = "/mvc/listBooks", method = RequestMethod.GET )
    public String listBooks( Model model){
        List<Book> list = service.findAll();
        model.addAttribute( "books", list);
        return "books";
    }

    @RequestMapping( value = "/mvc/deleteBook", method = RequestMethod.GET)
    public String delete( @RequestParam( value = "id") Long id){
        service.delete( id);
        return "redirect:listBooks";
    }


    @RequestMapping( value = "/mvc/updateBook", method = RequestMethod.GET)
    public String update( @RequestParam( value = "id") Long id, Model model){
        Book g =  id != 0 ? service.find( id): new Book();

        model.addAttribute( "book", g);
        return "updateBook";
    }

    @RequestMapping( value = "/mvc/updateBook", method = RequestMethod.POST)
    public String save(@Valid Book book , BindingResult bindingResult, SessionStatus status){
        if( bindingResult.hasErrors()){
            System.err.println( bindingResult.toString());
        } else {
            service.save( book);
        }
        return "redirect:listBooks";
    }


}
