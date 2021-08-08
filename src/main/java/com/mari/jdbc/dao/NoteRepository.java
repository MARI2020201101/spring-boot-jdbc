package com.mari.jdbc.dao;

import com.mari.jdbc.dto.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface NoteRepository {

    List<Note> selectAll();
    Note selectByEmail(String writerEmail);
    Long insert(Note note);
    void update(Note note);
    void delete(Long num);

    default Note select(ResultSet rs, int i) throws SQLException {
        Note note = new Note();
        note.setNum(rs.getLong("num"));
        note.setTitle(rs.getString("title"));
        note.setContent(rs.getString("content"));
        note.setRegDate(rs.getTimestamp("regdate").toLocalDateTime());
        note.setModDate(rs.getTimestamp("moddate").toLocalDateTime());
        note.setWriterEmail(rs.getString("writer_email"));
        return note;
    }

 }
