package com.mari.jdbc.dao;

import com.mari.jdbc.dto.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NoteRepositoryImpl implements NoteRepository{

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NoteRepositoryImpl(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("NOTE")
                .usingGeneratedKeyColumns("num");
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<Note> selectAll() {
        String sql = "SELECT * FROM NOTE";
        return jdbcTemplate.query(sql,this::select);
    }

    @Override
    public Note selectByEmail(String writerEmail) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM NOTE WHERE WRITER_EMAIL = ?");

        return jdbcTemplate.queryForObject(sql.toString(), this::select, writerEmail);
    }

    @Override
    public Long insert(Note note) {
        Map<String, Object> map = new HashMap<>();
        map.put("title" , note.getTitle());
        map.put("content", note.getContent());
        map.put("regdate" , note.getRegDate());
        map.put("moddate" , note.getModDate());
        map.put("writer_email", note.getWriterEmail());
        return simpleJdbcInsert.executeAndReturnKey(map).longValue();
    }

    @Override
    public void update(Note note) {
        String sql = "UPDATE NOTE SET TITLE = :title , CONTENT = :content WHERE NUM = :num";
        Map<String,Object> map = new HashMap<>();
        map.put("title",note.getTitle());
        map.put("content" , note.getContent());
        map.put("num", note.getNum());
        namedParameterJdbcTemplate.update(sql,map);

    }

    @Override
    public void delete(Long num) {
        String sql = "DELETE FROM NOTE WHERE NUM = ?";
        jdbcTemplate.update(sql,num);
    }


}
