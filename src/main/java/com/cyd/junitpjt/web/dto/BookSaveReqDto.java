package com.cyd.junitpjt.web.dto;

import com.cyd.junitpjt.domain.Book;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookSaveReqDto {
    private String title;
    private String author;

    public Book toEntity(){
        return Book.builder()
            .title(title)
            .author(author)
            .build();
    }
}
