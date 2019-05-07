package ru.pavel2107.otus.hw09;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.h2.tools.Console;

@SpringBootApplication
public class Hw07Application {


    public static void main(String[] args)  throws Exception {
        ApplicationContext context = SpringApplication.run(Hw07Application.class, args);

      //  Console.main();

    }

}
