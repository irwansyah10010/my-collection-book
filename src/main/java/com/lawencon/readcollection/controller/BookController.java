package com.lawencon.readcollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.readcollection.dto.BaseInsertResDto;
import com.lawencon.readcollection.dto.BaseResListDto;
import com.lawencon.readcollection.dto.BaseResSingleDto;
import com.lawencon.readcollection.dto.BaseUpdateAndDeleteResDto;
import com.lawencon.readcollection.dto.book.BookDeleteReqDto;
import com.lawencon.readcollection.dto.book.BookInsertReqDto;
import com.lawencon.readcollection.dto.book.BookListResDataDto;
import com.lawencon.readcollection.dto.book.BookSingleResDto;
import com.lawencon.readcollection.dto.book.BookUpdateReqDto;
import com.lawencon.readcollection.dto.book.BookUpdateStatusReqDto;
import com.lawencon.readcollection.dto.booktype.BookTypeDeleteReqDto;
import com.lawencon.readcollection.model.Book;
import com.lawencon.readcollection.service.BookService;

@RestController
@RequestMapping("books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<BaseResListDto<BookListResDataDto>> getAll(@RequestParam(value="search",required = false,defaultValue = "-") Object search){
        BaseResListDto<BookListResDataDto> baseResListDto = null;

        if(search.equals("-")){
            baseResListDto = bookService.getAll();
        }else{
            baseResListDto = bookService.getAll(search);
        }
        
        return new ResponseEntity<>(baseResListDto, HttpStatus.OK);
    }

    @GetMapping("{id}/id")
    public ResponseEntity<BaseResSingleDto<BookSingleResDto>> getById(@PathVariable("id") String id){
        BaseResSingleDto<BookSingleResDto> baseResSingleDto = bookService.getById(id);

        return new ResponseEntity<>(baseResSingleDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseInsertResDto> save(@RequestBody BookInsertReqDto bookInsertReqDto){
        BaseInsertResDto baseInsertResDto = bookService.save(bookInsertReqDto);

        return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<BaseUpdateAndDeleteResDto> update(@RequestBody BookUpdateReqDto bookUpdateReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = bookService.update(bookUpdateReqDto);

        return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    }

    @PutMapping("update-status")
    public ResponseEntity<BaseUpdateAndDeleteResDto> update(@RequestBody BookUpdateStatusReqDto bookUpdateStatusReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = bookService.updateStatus(bookUpdateStatusReqDto);

        return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BaseUpdateAndDeleteResDto> delete(@RequestBody BookDeleteReqDto bookDeleteReqDto){
        BaseUpdateAndDeleteResDto baseDeleteResDto = bookService.delete(bookDeleteReqDto);

        return new ResponseEntity<>(baseDeleteResDto, HttpStatus.OK);
    }
}
