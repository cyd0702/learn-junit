package com.cyd.junitpjt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cyd.junitpjt.domain.BookRepository;
import com.cyd.junitpjt.util.MailSenderStub;
import com.cyd.junitpjt.web.dto.BookResDto;
import com.cyd.junitpjt.web.dto.BookSaveReqDto;

@DataJpaTest
public class BookServiceTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void 책등록하기_테스트(){
        //given
        BookSaveReqDto dto = new BookSaveReqDto();
        dto.setTitle("junit");
        dto.setAuthor("cyd");

        //stub
        MailSenderStub mailSenderStub = new MailSenderStub();

        //when
        BookService bookService = new BookService(bookRepository, mailSenderStub);
        BookResDto bookResDto = bookService.책등록(dto);

        //then
       assertEquals(dto.getTitle(), bookResDto.getTitle());
       assertEquals(dto.getAuthor(), bookResDto.getAuthor());

    }
}
