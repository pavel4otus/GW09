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
import ru.pavel2107.otus.hw09.domain.Comment;
import ru.pavel2107.otus.hw09.service.BookService;
import ru.pavel2107.otus.hw09.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    private CommentService service;
    private BookService bookService;

    @Autowired
    public CommentController( CommentService service, BookService bookService){
        this.service = service;
        this.bookService = bookService;
    }

    @RequestMapping( value = "/mvc/listComments", method = RequestMethod.GET )
    public String listComments(@RequestParam( value = "id") Long bookId, Model model){
        List<Comment> list = service.findAll( bookId);
        model.addAttribute( "comments", list);
        Comment c = new Comment();
        c.setBook( bookService.find( bookId));
        model.addAttribute( "newComment", c);

        return "comments";
    }

    @RequestMapping( value = "/mvc/addComment", method = RequestMethod.POST)
    public String save( @RequestParam( value="book.id") Long bookId,
                        @RequestParam( value = "name") String name,
                        @RequestParam( value ="comment")  String newComment){
        Comment comment = new Comment();
        comment.setName( name);
        comment.setComment( newComment);
        comment.setDateTime( LocalDateTime.now());
        comment.setBook( bookService.find( bookId));
        service.save( comment);

        return "redirect:listComments?id=" + bookId;
    }


}
