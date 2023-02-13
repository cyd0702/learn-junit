package com.cyd.junitpjt.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyd.junitpjt.domain.Book;
import com.cyd.junitpjt.domain.BookRepository;
import com.cyd.junitpjt.util.MailSender;
import com.cyd.junitpjt.web.dto.BookResDto;
import com.cyd.junitpjt.web.dto.BookSaveReqDto;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final MailSender mailSender;

    // 1. 책 등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResDto 책등록(BookSaveReqDto dto){
        Book bookPS = bookRepository.save(dto.toEntity());
        if(bookPS != null){
            if(!mailSender.send()){
                throw new RuntimeException("메일 전송 실패!");
            }
        }
        return new BookResDto().toDto(bookPS);
    }

    // 2. 책 목록보기
    public List<BookResDto> 책목록보기(){
        return bookRepository.findAll().stream()
        .map(new BookResDto()::toDto)
        .collect(Collectors.toList());
    }

    // 3. 책 한건보기
    public BookResDto 책한건보기(Long id){
        Optional<Book> bookOP = bookRepository.findById(id);
        if(bookOP.isPresent()){
            return new BookResDto().toDto(bookOP.get());
        } else {
            throw new RuntimeException("해당아이디를 찾을수 없다");
        }
     
    }

    // 4. 책 삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책삭제하기(Long id){
        bookRepository.deleteById(id);
    }



    // 5. 책 수정
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책수정하기(Long id, BookSaveReqDto dto){
        Optional<Book> bookOP = bookRepository.findById(id);
        if(bookOP.isPresent()){
            Book bookPS = bookOP.get();
            bookPS.update(dto.getTitle(), dto.getAuthor());
        } else {
            throw new RuntimeException("해당아이디를 찾을수 없다");
        }
    } // 메서드 종료시 더티체킹(flush)으로 update 된다.

}
