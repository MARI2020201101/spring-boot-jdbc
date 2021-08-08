package com.mari.jdbc.dao;

import com.mari.jdbc.dto.Movie;

import java.util.List;

public interface MovieRepository {

    List<Movie> selectAll();
    int insertWithNamedJdbc(Movie movie);
    int insert(Movie movie);
    int insertWithSimpleInsertJdbc(Movie movie);
    Long insertWithKey(Movie movie);



}
