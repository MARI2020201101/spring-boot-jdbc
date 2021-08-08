package com.mari.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {

    private Long num;
    private LocalDateTime modDate;
    private LocalDateTime regDate;
    private String title;
    private String content;
    private String writerEmail;
}

