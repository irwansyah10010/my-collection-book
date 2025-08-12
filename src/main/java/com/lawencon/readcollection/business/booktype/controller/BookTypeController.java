package com.lawencon.readcollection.business.booktype.controller;

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
import com.lawencon.readcollection.business.booktype.dto.BookTypeDeleteReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeInsertReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeResDataDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeUpdateReqDto;
import com.lawencon.readcollection.business.booktype.service.BookTypeService;
import com.lawencon.readcollection.data.model.BookType;

@RestController
@RequestMapping("book-types")
public class BookTypeController {
    
    @Autowired
    private BookTypeService bookTypeService;

    @GetMapping
    public ResponseEntity<BaseResListDto<BookTypeResDataDto>> getAll(@RequestParam(value="search",required = false,defaultValue = "-") Object search){
        BaseResListDto<BookTypeResDataDto> baseResListDto = null;

        if(search.equals("-")){
            baseResListDto = bookTypeService.getAll();
        }else{
            baseResListDto = bookTypeService.getAll(search);
        }

        return new ResponseEntity<>(baseResListDto, HttpStatus.OK);
    }

    @GetMapping("{id}/id")
    public ResponseEntity<BaseResSingleDto<BookTypeResDataDto>> getById(@PathVariable("id") String id){
        BaseResSingleDto<BookTypeResDataDto> baseResSingleDto = bookTypeService.getById(id);

        return new ResponseEntity<>(baseResSingleDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BaseInsertResDto> save(@RequestBody BookTypeInsertReqDto bookTypeInsertReqDto){
        BaseInsertResDto baseInsertResDto = bookTypeService.save(bookTypeInsertReqDto);

        return new ResponseEntity<>(baseInsertResDto, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<BaseUpdateAndDeleteResDto> update(@RequestBody BookTypeUpdateReqDto bookTypeUpdateReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = bookTypeService.update(bookTypeUpdateReqDto);

        return new ResponseEntity<>(baseUpdateResDto, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<BaseUpdateAndDeleteResDto> delete(@RequestBody BookTypeDeleteReqDto bookTypeDeleteReqDto){
        BaseUpdateAndDeleteResDto baseDeleteResDto = bookTypeService.delete(bookTypeDeleteReqDto);

        return new ResponseEntity<>(baseDeleteResDto, HttpStatus.OK);
    }

    @GetMapping("{bookTypeCode}/code")
    public ResponseEntity<BaseResSingleDto<BookType>> getBybookTypeCode(@PathVariable("bookTypeCode") String bookTypeCode){
        BaseResSingleDto<BookType> baseResSingleDto = bookTypeService.getByBookTypeCode(bookTypeCode);

        return new ResponseEntity<BaseResSingleDto<BookType>>(baseResSingleDto, HttpStatus.OK);
    }
}
