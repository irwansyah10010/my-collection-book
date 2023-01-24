package com.lawencon.readcollection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.readcollection.dto.BaseInsertResDto;
import com.lawencon.readcollection.dto.BaseResListDto;
import com.lawencon.readcollection.dto.BaseResSingleDto;
import com.lawencon.readcollection.dto.readbook.ReadBookInsertReqDto;
import com.lawencon.readcollection.model.ReadBook;
import com.lawencon.readcollection.service.ReadBookService;

@RestController
@RequestMapping("read-books")
public class ReadBookController {
    
    @Autowired
    private ReadBookService readBookService;

    @GetMapping
    public ResponseEntity<BaseResListDto<ReadBook>> getAll(){
        BaseResListDto<ReadBook> baseResListDto = readBookService.getAll();

        return new ResponseEntity<>(baseResListDto, HttpStatus.OK);
    }

    @GetMapping("{id}/id")
    public ResponseEntity<BaseResSingleDto<ReadBook>> getById(@PathVariable("id") String id){
        BaseResSingleDto<ReadBook> baseResSingleDto = readBookService.getById(id);

        return new ResponseEntity<>(baseResSingleDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseInsertResDto> save(@RequestBody ReadBookInsertReqDto readBookInsertReqDto){
        BaseInsertResDto baseInsertResDto = readBookService.save(readBookInsertReqDto);

        return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    }

}
