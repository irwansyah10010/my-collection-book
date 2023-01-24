package com.lawencon.readcollection.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.readcollection.constant.Message;
import com.lawencon.readcollection.dao.BookTypeDao;
import com.lawencon.readcollection.dto.BaseInsertResDto;
import com.lawencon.readcollection.dto.BaseResListDto;
import com.lawencon.readcollection.dto.BaseResSingleDto;
import com.lawencon.readcollection.dto.BaseUpdateResDto;
import com.lawencon.readcollection.dto.booktype.BookTypeInsertReqDto;
import com.lawencon.readcollection.dto.booktype.BookTypeUpdateReqDto;
import com.lawencon.readcollection.model.BookType;

@Service
public class BookTypeService {
    
    @Autowired
    private BookTypeDao bookTypeDao;

    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto save(BookTypeInsertReqDto bookTypeReqDto){
        BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

        BookType bookType = new BookType();
        
        bookType.setBookTypeCode(bookTypeReqDto.getBookTypeCode());
        bookType.setBookTypeName(bookTypeReqDto.getBookTypeName());

        BookType bookTypeInsert = bookTypeDao.save(bookType);

        if(bookTypeInsert != null){
            baseInsertResDto.setId(bookTypeInsert.getId());
            baseInsertResDto.setMessage(Message.SUCCESS_SAVE.getMessage());
        }else{
            baseInsertResDto.setMessage(Message.FAILED_SAVE.getMessage());
        } 

        return baseInsertResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateResDto update(BookTypeUpdateReqDto bookTypeUpdateReqDto){
        BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();

        BookType bookType = bookTypeDao.findById(BookType.class, bookTypeUpdateReqDto.getId());

        if(bookType != null){

            bookType.setBookTypeName(bookTypeUpdateReqDto.getBookTypeName());

            BookType bookTypeUpdate = bookTypeDao.update(bookType);

            if(bookTypeUpdate != null){
                baseUpdateResDto.setVersion(0);
                baseUpdateResDto.setMessage(Message.SUCCESS_UPDATE.getMessage());
            }else{
                baseUpdateResDto.setMessage(Message.FAILED_UPDATE.getMessage());
            }
        }else{
            baseUpdateResDto.setMessage(Message.FAILED_UPDATE.getMessage());
        }

        return baseUpdateResDto;
    }

    public BaseResListDto<BookType> getAll(){
        BaseResListDto<BookType> baseResListDto = new BaseResListDto<>();

        List<BookType> bookTypes = bookTypeDao.getAll("tb_book_type", BookType.class);

        baseResListDto.setData(bookTypes);

        return baseResListDto;
    }

    public BaseResSingleDto<BookType> getById(String id){
        BaseResSingleDto<BookType> baseResSingleDto = new BaseResSingleDto<>();

        BookType bookType = bookTypeDao.findById(BookType.class, id);

        if(bookType != null){
            baseResSingleDto.setData(bookType);
        }

        return baseResSingleDto;
    }
    
}
