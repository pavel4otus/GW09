package ru.pavel2107.otus.hw09.repository.datajpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.pavel2107.otus.hw09.domain.Genre;


public interface GenreRepository extends CrudRepository<Genre, Long> {

}

