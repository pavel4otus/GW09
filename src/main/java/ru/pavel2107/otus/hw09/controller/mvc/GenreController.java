package ru.pavel2107.otus.hw09.controller.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import ru.pavel2107.otus.hw09.domain.Genre;
import ru.pavel2107.otus.hw09.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class GenreController {

    private GenreService service;

    @Autowired
    public GenreController( GenreService service){
        this.service = service;
    }

    @RequestMapping( value = "/mvc/listGenres", method = RequestMethod.GET )
    public String listGenres( Model model){
        List<Genre> list = service.findAll();
        model.addAttribute( "genres", list);
        return "genres";
    }

    @RequestMapping( value = "/mvc/deleteGenre", method = RequestMethod.GET)
    public String delete( @RequestParam( value = "id") Long id){
        service.delete( id);
        return "redirect:listGenres";
    }


    @RequestMapping( value = "/mvc/updateGenre", method = RequestMethod.GET)
    public String update( @RequestParam( value = "id") Long id, Model model){
        Genre g =  id != 0 ? service.find( id): new Genre();

        model.addAttribute( "genre", g);
        return "updateGenre";
    }

    @RequestMapping( value = "/mvc/updateGenre", method = RequestMethod.POST)
    public String save( @Valid Genre genre, BindingResult bindingResult, SessionStatus status){
        if( bindingResult.hasErrors()){
            System.err.println( bindingResult.toString());
        } else {
            service.save( genre);
        }
        return "redirect:listGenres";
    }
}
