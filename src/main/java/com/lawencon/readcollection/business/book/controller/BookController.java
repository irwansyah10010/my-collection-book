package com.lawencon.readcollection.business.book.controller;

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

import com.lawencon.readcollection.base.dto.req.BaseInsertResDto;
import com.lawencon.readcollection.base.dto.req.BaseResListDto;
import com.lawencon.readcollection.base.dto.req.BaseResSingleDto;
import com.lawencon.readcollection.base.dto.req.BaseUpdateAndDeleteResDto;
import com.lawencon.readcollection.business.book.dto.BookDeleteReqDto;
import com.lawencon.readcollection.business.book.dto.BookInsertReqDto;
import com.lawencon.readcollection.business.book.dto.BookListResDataDto;
import com.lawencon.readcollection.business.book.dto.BookSingleResDto;
import com.lawencon.readcollection.business.book.dto.BookUpdateReqDto;
import com.lawencon.readcollection.business.book.dto.BookUpdateStatusReqDto;
import com.lawencon.readcollection.business.book.service.BookService;
import com.lawencon.readcollection.business.booktype.dto.BookTypeDeleteReqDto;
import com.lawencon.readcollection.data.model.Book;

@RestController
@RequestMapping("books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<BaseResListDto<?>> getAll(@RequestParam(value="search",required = false,defaultValue = "-") String search, Integer page, Integer limit){
        BaseResListDto<?> baseResListDto = null;

        if(search.equals("-")){
            baseResListDto = bookService.getAll(page, limit);
        }else{
            baseResListDto = bookService.getAll(page, limit, search);
        }
        
        return new ResponseEntity<>(baseResListDto, HttpStatus.OK);
    }

    //
    // @GetMapping("{id}/id")
    // public ResponseEntity<BaseResSingleDto<BookSingleResDto>> getById(@PathVariable("id") String id){
    //     BaseResSingleDto<BookSingleResDto> baseResSingleDto = bookService.getById(id);

    //     return new ResponseEntity<>(baseResSingleDto, HttpStatus.OK);
    // }

    // @PostMapping
    // public ResponseEntity<BaseInsertResDto> save(@RequestBody BookInsertReqDto bookInsertReqDto){
    //     BaseInsertResDto baseInsertResDto = bookService.save(bookInsertReqDto);

    //     return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    // }


    // @PutMapping
    // public ResponseEntity<BaseUpdateAndDeleteResDto> update(@RequestBody BookUpdateReqDto bookUpdateReqDto){
    //     BaseUpdateAndDeleteResDto baseUpdateResDto = bookService.update(bookUpdateReqDto);

    //     return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    // }

    // @PutMapping("update-status")
    // public ResponseEntity<BaseUpdateAndDeleteResDto> update(@RequestBody BookUpdateStatusReqDto bookUpdateStatusReqDto){
    //     BaseUpdateAndDeleteResDto baseUpdateResDto = bookService.updateStatus(bookUpdateStatusReqDto);

    //     return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    // }

    // @DeleteMapping
    // public ResponseEntity<BaseUpdateAndDeleteResDto> delete(@RequestBody BookDeleteReqDto bookDeleteReqDto){
    //     BaseUpdateAndDeleteResDto baseDeleteResDto = bookService.delete(bookDeleteReqDto);

    //     return new ResponseEntity<>(baseDeleteResDto, HttpStatus.OK);
    // }
}
