package ru.pavel2107.otus.hw09.controller.mvc;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.pavel2107.otus.hw09.repository.datajpa.AuthorRepository;
import ru.pavel2107.otus.hw09.service.AuthorService;
import ru.pavel2107.otus.hw09.service.AuthorServiceImpl;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@DisplayName( "Контроллер авторов")
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {

    @Autowired private MockMvc mockMvc;

    @Test
    public void listAuthors() throws Exception{
        mockMvc
                .perform( get( "/mvc/listAuthors"))
                .andDo( print())
                .andExpect( view().name( "authors"))
                .andExpect( model().attributeExists( "authors"))
                .andExpect( status().isOk());

    }
}