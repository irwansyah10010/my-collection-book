package com.lawencon.readcollection.business.booktype.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.readcollection.base.dto.res.BaseInsertResDto;
import com.lawencon.readcollection.base.dto.res.BaseResListDto;
import com.lawencon.readcollection.base.dto.res.BaseUpdateAndDeleteResDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeDeleteReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeInsertReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeUpdateReqDto;
import com.lawencon.readcollection.business.booktype.service.BookTypeService;
import com.lawencon.readcollection.data.model.BookType;

@RestController
@RequestMapping("book-types")
public class BookTypeController {
    
    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping
    public ResponseEntity<BaseResListDto<?>> getAll(){
        BaseResListDto<BookType> all = bookTypeService.getAll();

        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseInsertResDto> save(@Valid @RequestBody BookTypeInsertReqDto bookTypeInsertReqDto){
        BaseInsertResDto baseInsertResDto = bookTypeService.save(bookTypeInsertReqDto);

        return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<BaseUpdateAndDeleteResDto> update(@Valid @RequestBody BookTypeUpdateReqDto bookTypeUpdateReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = bookTypeService.update(bookTypeUpdateReqDto);

        return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BaseUpdateAndDeleteResDto> delete(@RequestBody BookTypeDeleteReqDto bookTypeDeleteReqDto){
        BaseUpdateAndDeleteResDto baseUpdateAndDeleteResDto = bookTypeService.delete(bookTypeDeleteReqDto);

        return new ResponseEntity<>(baseUpdateAndDeleteResDto, HttpStatus.OK);
    }

}
