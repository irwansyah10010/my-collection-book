package com.lawencon.readcollection.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.readcollection.constant.Message;
import com.lawencon.readcollection.dao.BookDao;
import com.lawencon.readcollection.dao.BookTypeDao;
import com.lawencon.readcollection.dto.BaseInsertResDto;
import com.lawencon.readcollection.dto.BaseResListDto;
import com.lawencon.readcollection.dto.BaseResSingleDto;
import com.lawencon.readcollection.dto.BaseUpdateResDto;
import com.lawencon.readcollection.dto.book.BookInsertReqDto;
import com.lawencon.readcollection.dto.book.BookUpdateReqDto;
import com.lawencon.readcollection.dto.book.BookUpdateStatusReqDto;
import com.lawencon.readcollection.model.Book;
import com.lawencon.readcollection.model.BookType;

@Service
public class BookService {
    
    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookTypeDao bookTypeDao;

    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto save(BookInsertReqDto bookInsertReqDto){
        BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

        BookType bookType = bookTypeDao.findById(BookType.class, bookInsertReqDto.getBookTypeId());

        if(bookType != null){
            Book book = new Book();
        
            book.setIssbn(bookInsertReqDto.getIssbn());
            book.setTitle(bookInsertReqDto.getTitle());
            book.setNumberOfPage(bookInsertReqDto.getNumberOfPage());
    
            book.setSynopsis(bookInsertReqDto.getSynopsis());
            book.setPrice(bookInsertReqDto.getPrice());
            book.setStatus("new");
            book.setPublisher(bookInsertReqDto.getPublisher());
            book.setAuthorName(bookInsertReqDto.getAuthorName());

            book.setBookType(bookType);

            Book bookInsert = bookDao.save(book);

            if(bookInsert != null){
                baseInsertResDto.setId(bookInsert.getId());
                baseInsertResDto.setMessage(Message.SUCCESS_SAVE.getMessage());
            }else{
                baseInsertResDto.setMessage(Message.FAILED_SAVE.getMessage());
            }
        }else{
            baseInsertResDto.setMessage(Message.FAILED_SAVE.getMessage());
        }

        return baseInsertResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateResDto update(BookUpdateReqDto bookUpdateReqDto){
        BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();

        Book book = bookDao.findById(Book.class, bookUpdateReqDto.getId());

        if(book != null){

            book.setTitle(bookUpdateReqDto.getTitle());
            book.setNumberOfPage(bookUpdateReqDto.getNumberOfPage());
    
            book.setSynopsis(bookUpdateReqDto.getSynopsis());
            book.setPrice(bookUpdateReqDto.getPrice());
            
            book.setPublisher(bookUpdateReqDto.getPublisher());
            book.setAuthorName(bookUpdateReqDto.getAuthorName());

            Book bookUpdate = bookDao.update(book);

            if(bookUpdate != null){
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

    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateResDto updateStatus(BookUpdateStatusReqDto bookUpdateStatusReqDto){
        BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();

        Book book = bookDao.findById(Book.class, bookUpdateStatusReqDto.getId());

        if(book != null){

            book.setStatus(bookUpdateStatusReqDto.getStatus());

            Book bookUpdate = bookDao.update(book);

            if(bookUpdate != null){
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

    public BaseResListDto<Book> getAll(){
        BaseResListDto<Book> baseResListDto = new BaseResListDto<>();

        List<Book> books = bookDao.getAll("tb_book", Book.class);

        baseResListDto.setData(books);

        return baseResListDto;
    }

    public BaseResSingleDto<Book> getById(String id){
        BaseResSingleDto<Book> baseResSingleDto = new BaseResSingleDto<>();

        Book book = bookDao.findById(Book.class, id);

        if(book != null){
            baseResSingleDto.setData(book);
        }

        return baseResSingleDto;
    }
}
