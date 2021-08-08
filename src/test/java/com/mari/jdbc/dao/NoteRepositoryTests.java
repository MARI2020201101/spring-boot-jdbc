package com.mari.jdbc.dao;

import com.mari.jdbc.dto.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class NoteRepositoryTests {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void selectAllTest(){
        noteRepository.selectAll().stream().forEach(System.out::println);
    }

    @Test
    public void selectWithEmailTest(){
        System.out.println(noteRepository.selectByEmail("user1@aaa.com"));
    }

    @Test
    public void insertTest(){
        Long key = noteRepository.insert(Note.builder()
                .title("new note")
                .content("content..")
                .regDate(LocalDateTime.now())
                .modDate(LocalDateTime.now())
                .writerEmail("user1@aaa.com").build());

        System.out.println("generated Key Value : "+key);

    }

    @Test
    public void updateTest(){
        noteRepository.update(Note.builder().num(24L).title("update note..").content("nnn").build());
    }

    @Test
    public void deleteTest(){
        noteRepository.delete(1L);
    }
}
