package ru.pavel2107.otus.hw09.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import ru.pavel2107.otus.hw09.domain.Author;
import ru.pavel2107.otus.hw09.service.AuthorService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AuthorController {

    private AuthorService service;

    @Autowired
    public AuthorController( AuthorService service){
        this.service = service;
    }

    @RequestMapping( value = "/mvc/listAuthors", method = RequestMethod.GET )
    public String listAuthors( Model model){
        List<Author> list = service.findAll();
        model.addAttribute( "authors", list);
        return "authors";
    }

    @RequestMapping( value = "/mvc/deleteAuthor", method = RequestMethod.GET)
    public String delete( @RequestParam( value = "id") Long id){
        service.delete( id);
        return "redirect:listAuthors";
    }


    @RequestMapping( value = "/mvc/updateAuthor", method = RequestMethod.GET)
    public String update( @RequestParam( value = "id") Long id, Model model){
        Author g =  id != 0 ? service.find( id): new Author();

        model.addAttribute( "author", g);
        return "updateAuthor";
    }

    @RequestMapping( value = "/mvc/updateAuthor", method = RequestMethod.POST)
    public String save(@Valid Author author, BindingResult bindingResult, SessionStatus status){
       if( bindingResult.hasErrors()){
           System.err.println( bindingResult.toString());
       } else {
            service.save( author);
       }
       return "redirect:listAuthors";
    }
}
