package com.lawencon.readcollection.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.readcollection.constant.Message;
import com.lawencon.readcollection.dao.BookDao;
import com.lawencon.readcollection.dao.ReadBookDao;
import com.lawencon.readcollection.dto.BaseInsertResDto;
import com.lawencon.readcollection.dto.BaseResListDto;
import com.lawencon.readcollection.dto.BaseResSingleDto;
import com.lawencon.readcollection.dto.readbook.ReadBookInsertReqDto;
import com.lawencon.readcollection.model.Book;
import com.lawencon.readcollection.model.ReadBook;

@Service
public class ReadBookService {
    
    @Autowired
    private BookDao bookDao;

    @Autowired
    private ReadBookDao readBookDao;

    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto save(ReadBookInsertReqDto readBookInsertReqDto){
        BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

        Book book = bookDao.findById(Book.class, readBookInsertReqDto.getBookId());

        if(book != null){
            ReadBook readBook = new ReadBook();
        
            readBook.setPageOfRead(readBookInsertReqDto.getPageOfRead());
            readBook.setDateOfRead(LocalDateTime.now());
            readBook.setBook(book);

            ReadBook readBookInsert = readBookDao.save(readBook);

            if(readBookInsert != null){
                // validasi
                book.setStatus(readBookInsertReqDto.getStatus());

                Book bookUpdateStatus = bookDao.update(book);

                if(bookUpdateStatus != null){
                    baseInsertResDto.setId(readBookInsert.getId());
                    baseInsertResDto.setMessage(Message.SUCCESS_SAVE.getMessage());
                }

            }else{
                baseInsertResDto.setMessage(Message.FAILED_SAVE.getMessage());
            }
        }else{
            baseInsertResDto.setMessage(Message.FAILED_SAVE.getMessage());
        }

        return baseInsertResDto;
    }

    public BaseResListDto<ReadBook> getAll(){
        BaseResListDto<ReadBook> baseResListDto = new BaseResListDto<>();

        String tableName = "tb_read_book";
        List<ReadBook> readBooks = readBookDao.getAll(tableName, ReadBook.class);
        Integer countOfReadBook = readBookDao.getCountOfData(tableName);

        baseResListDto.setData(readBooks);
        baseResListDto.setCountOfData(countOfReadBook);

        return baseResListDto;
    }

    public BaseResSingleDto<ReadBook> getById(String id){
        BaseResSingleDto<ReadBook> baseResSingleDto = new BaseResSingleDto<>();

        ReadBook readBook = readBookDao.findById(ReadBook.class, id);

        if(readBook != null){
            baseResSingleDto.setData(readBook);
        }

        return baseResSingleDto;
    }

}
