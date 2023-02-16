package com.cyd.junitpjt.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.event.spi.PreUpdateEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cyd.junitpjt.service.BookService;
import com.cyd.junitpjt.web.dto.request.BookSaveReqDto;
import com.cyd.junitpjt.web.dto.response.BookResDto;
import com.cyd.junitpjt.web.dto.response.CMResDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BookService bookService;
    
    // 1. 책등록
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveReqDto bookSaveReqDto, BindingResult bindingResult){
       
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError fe : bindingResult.getFieldErrors()){
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            System.out.println("=============================");
            System.out.println(errorMap.toString());
            System.out.println("========================");

            throw new RuntimeException(errorMap.toString()); // global exception 으로 보낸다
        }

        BookResDto bookResDto = bookService.책등록(bookSaveReqDto);
        //CMResDto<?> cmResDto = CMResDto.builder().code(1).msg("글저장성공").body(bookResDto).build();
        return new ResponseEntity<>(CMResDto.builder().code(1).msg("글저장성공").body(bookResDto).build(), HttpStatus.CREATED); // 201 = insert
    }

    // 2. 책 목록보기
    public ResponseEntity<?> getBookList(){
        return null;
    }

    // 3. 책 한건보기
    public ResponseEntity<?> getbookOne(){
        return null;
    }

    // 4. 책 삭제하기
    public ResponseEntity<?> deleteBook(){
        return null;
    }

    // 5. 책 수정하기
    public ResponseEntity<?> updateBook(){
        return null;
    }
}
