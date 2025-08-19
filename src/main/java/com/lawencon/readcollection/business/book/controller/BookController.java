package com.lawencon.readcollection.business.book.controller;

import javax.validation.Valid;

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

import com.lawencon.readcollection.base.dto.res.BaseTransactionResDto;
import com.lawencon.readcollection.base.dto.res.BaseResListDto;
import com.lawencon.readcollection.base.dto.res.BaseResSingleDto;
import com.lawencon.readcollection.business.book.dto.BookDeleteReqDto;
import com.lawencon.readcollection.business.book.dto.BookInsertReqDto;
import com.lawencon.readcollection.business.book.dto.BookTypeInsertReqDto;
import com.lawencon.readcollection.business.book.dto.BookUpdateReqDto;
import com.lawencon.readcollection.business.book.dto.BookUpdateStatusReqDto;
import com.lawencon.readcollection.business.book.service.BookService;

@RestController
@RequestMapping("books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<BaseResListDto<?>> getAll(@RequestParam(value="search",required = false,defaultValue = "") String search, Integer page, Integer limit){
        BaseResListDto<?> baseResListDto = null;

        if(search.isEmpty()){
            baseResListDto = bookService.getAll(page, limit);
        }else{
            baseResListDto = bookService.getAll(page, limit, search);
        }
        
        return new ResponseEntity<>(baseResListDto, HttpStatus.OK);
    }

    @GetMapping("/{issbn}/id")
    public ResponseEntity<BaseResSingleDto<?>> getById(@PathVariable("issbn") String issbn){
        BaseResSingleDto<?> byIssbn = bookService.getByIssbn(issbn);

        return new ResponseEntity<>(byIssbn, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseTransactionResDto> add(@Valid @RequestBody BookInsertReqDto bookInsertReqDto){

        BaseTransactionResDto baseInsertResDto = bookService.add(bookInsertReqDto);

        return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    }

    @PostMapping("/add-book-type")
    public ResponseEntity<BaseTransactionResDto> addBookType(@Valid @RequestBody BookTypeInsertReqDto bookInsertReqDto){

        BaseTransactionResDto baseInsertResDto = bookService.addBookType(bookInsertReqDto);

        return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BaseTransactionResDto> update(@Valid @RequestBody BookUpdateReqDto bookUpdateReqDto){
        BaseTransactionResDto baseUpdateResDto = bookService.update(bookUpdateReqDto);

        return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    }

    @PutMapping("/update-status")
    public ResponseEntity<BaseTransactionResDto> updateStatus(@Valid @RequestBody BookUpdateStatusReqDto bookUpdateStatusReqDto){
        BaseTransactionResDto baseUpdateResDto = bookService.updateStatus(bookUpdateStatusReqDto);

        return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BaseTransactionResDto> delete(@RequestBody BookDeleteReqDto bookDeleteReqDto){
        BaseTransactionResDto baseDeleteResDto = bookService.delete(bookDeleteReqDto);

        return new ResponseEntity<>(baseDeleteResDto, HttpStatus.OK);
    }
}
