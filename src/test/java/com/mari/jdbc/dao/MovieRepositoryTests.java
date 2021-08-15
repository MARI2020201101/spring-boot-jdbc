package com.mari.jdbc.dao;

import com.mari.jdbc.dto.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class MovieRepositoryTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void selectAllTest(){
        movieRepository.selectAll().stream().forEach(System.out::println);
    }

    @Test
    public void insertTest(){
       movieRepository.insert(
               Movie.builder().title("new movie").regdate(LocalDateTime.now()).build());
    }

    @Test
    public void insertWithNamedParameterTest(){
        movieRepository.insertWithNamedJdbc(
                Movie.builder().title("new movie2").regdate(LocalDateTime.now()).build());
    }

    public void insertWithSimpleInsertJdbcTest(){
        movieRepository.insertWithSimpleInsertJdbc(
                Movie.builder().title("new movie3").regdate(LocalDateTime.now()).build());
    }

    @Test
    public void insertWithKeyTest(){
        Long key = movieRepository.insertWithKey(
                Movie.builder().title("new movie5")
                        .regdate(LocalDateTime.now())
                        .moddate(LocalDateTime.now()).build());
    System.out.println("returned key  : " +key);

    }
}
