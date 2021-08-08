package com.mari.jdbc.dao;

import com.mari.jdbc.dto.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MovieRespositoryImpl implements MovieRepository{

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Override
    public List<Movie> selectAll() {

        String sql = "SELECT * FROM MOVIE";

        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Movie.class));
    }

    @Override
    public int insertWithNamedJdbc(Movie movie) {
        String sql = "INSERT INTO MOVIE(TITLE, REGDATE) VALUES(:title , :regdate)";
        Map<String, Object> map = new HashMap<>();
        map.put("title", movie.getTitle());
        map.put("regdate", movie.getRegdate());
        return namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public int insert(Movie movie) {
        String sql = "INSERT INTO MOVIE(TITLE, REGDATE) VALUES(? , ?)";
        return jdbcTemplate.update(sql, movie.getTitle(),movie.getRegdate());
    }

    @Override
    public int insertWithSimpleInsertJdbc(Movie movie) {

        Map<String, Object> map = new HashMap<>();
        map.put("title", movie.getTitle());
        map.put("regdate", movie.getRegdate());

        return simpleJdbcInsert.withTableName("MOVIE").execute(map);
    }

    @Override
    public Long insertWithKey(Movie movie) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", movie.getTitle());
        map.put("regdate", movie.getRegdate());
        map.put("moddate" , movie.getModdate());

        simpleJdbcInsert.withTableName("MOVIE").usingGeneratedKeyColumns("mno");

        return simpleJdbcInsert.executeAndReturnKey(map).longValue();

    }

}
