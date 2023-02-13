package com.cyd.junitpjt.web.dto;

import com.cyd.junitpjt.domain.Book;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookResDto {
    private Long id;
    private String title;
    private String author;
    
    public BookResDto toDto(Book bookPS){
        this.id = bookPS.getId();
        this.title = bookPS.getTitle();
        this.author = bookPS.getAuthor();
        return this;
    }
}
