package com.lawencon.readcollection.business.booktype.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.readcollection.base.constant.Message;
import com.lawencon.readcollection.base.dto.res.BaseInsertResDto;
import com.lawencon.readcollection.base.dto.res.BaseResListDto;
import com.lawencon.readcollection.base.dto.res.BaseUpdateAndDeleteResDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeDeleteReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeInsertReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeUpdateReqDto;
import com.lawencon.readcollection.data.dao.BookTypeDao;
import com.lawencon.readcollection.data.model.BookType;
import com.lawencon.readcollection.data.model.Status;

@Service
public class BookTypeService {
    
    @Autowired
    private BookTypeDao bookTypeDao;

    public BaseResListDto<BookType> getAll(){
        List<BookType> all = bookTypeDao.findAll();

        BaseResListDto<BookType> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(all);
        baseResListDto.setCountOfData(bookTypeDao.count(BookType.class));

        return baseResListDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto save(BookTypeInsertReqDto bookTypeReqDto){
        BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

        BookType bookType = new BookType();
        
        bookType.setBookTypeCode(bookTypeReqDto.getBookTypeCode());
        bookType.setBookTypeName(bookTypeReqDto.getBookTypeName());

        BookType bookTypeInsert = bookTypeDao.save(bookType);

        if(bookTypeInsert != null){
            baseInsertResDto.setId(bookTypeInsert.getBookTypeCode());
            baseInsertResDto.setMessage(Message.SUCCESS_SAVE.getMessage());
        }else{
            throw new RuntimeException("Failed to save");
        } 

        return baseInsertResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateAndDeleteResDto update(BookTypeUpdateReqDto bookTypeUpdateReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = new BaseUpdateAndDeleteResDto();

        BookType bookType = bookTypeDao.findByPK(BookType.class, bookTypeUpdateReqDto.getBookTypeCode());

        if(bookType != null){
            bookType.setBookTypeName(bookTypeUpdateReqDto.getBookTypeName());

            BookType bookTypeUpdate = bookTypeDao.update(bookType);

            if(bookTypeUpdate != null){
                baseUpdateResDto.setMessage(Message.SUCCESS_UPDATE.getMessage());
            }else{
                throw new RuntimeException("Failed to save");
            }
        }else{
            baseUpdateResDto.setMessage("Book type isn't available");
        }

        return baseUpdateResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateAndDeleteResDto delete(BookTypeDeleteReqDto bookTypeDeleteReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = new BaseUpdateAndDeleteResDto();

        // delete book

        // delete book type
        Boolean isDelete = bookTypeDao.delete(BookType.class, "book_type_code", bookTypeDeleteReqDto.getBookTypeCode());
        
        if(isDelete){
            baseUpdateResDto.setMessage(Message.SUCCESS_DELETE.getMessage());
        }else{
            baseUpdateResDto.setMessage(Message.FAILED_DELETE.getMessage());
        }
        
        return baseUpdateResDto;
    }

}
