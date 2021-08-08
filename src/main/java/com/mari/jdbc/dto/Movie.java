package com.mari.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Movie {

    private Long mno;
    private LocalDateTime moddate;
    private LocalDateTime regdate;
    private String title;
}
