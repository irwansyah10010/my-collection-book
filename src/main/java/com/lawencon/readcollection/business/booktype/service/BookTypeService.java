package com.lawencon.readcollection.business.booktype.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.server.ResponseStatusException;

import com.lawencon.readcollection.base.constant.Message;
import com.lawencon.readcollection.base.dto.res.BaseTransactionResDto;
import com.lawencon.readcollection.base.dto.res.BaseResListDto;
import com.lawencon.readcollection.base.dto.res.BaseTransactionResDto;
import com.lawencon.readcollection.base.dto.validation.ValidationRuntimeException;
import com.lawencon.readcollection.business.booktype.dto.BookTypeDeleteReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeInsertReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeUpdateReqDto;
import com.lawencon.readcollection.data.dao.BookTypeDao;
import com.lawencon.readcollection.data.model.BookType;

@Service
public class BookTypeService {
    
    @Autowired
    private BookTypeDao bookTypeDao;
    
    /*
     * get all data book type
     */
    public BaseResListDto<BookType> getAll(){
        List<BookType> all = bookTypeDao.findAll();

        BaseResListDto<BookType> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(all);
        baseResListDto.setCountOfData(bookTypeDao.count(BookType.class));

        return baseResListDto;
    }

    /**
     * add new book type
     * 
     * validation - request forbidden (400)
     * - book type code is require: v
     * - book type name is require: v
     * - book type name is not blank: v
     
     * - book type code has been available: v
     * 
     * @param bookTypeReqDto
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public BaseTransactionResDto save(BookTypeInsertReqDto bookTypeReqDto){
        BaseTransactionResDto baseInsertResDto = new BaseTransactionResDto();


        String bookTypeCode = bookTypeReqDto.getBookTypeCode();

        // check duplicate data
        if(!bookTypeDao.isExistByBookTypeCode(bookTypeCode)){
            BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(bookTypeReqDto, "bookTypeReqDto");
            bindingResult.rejectValue("bookTypeCode", "duplicate", "Book type code already exists");

            throw new ValidationRuntimeException(bindingResult);
        }

        BookType bookType = new BookType();
        bookType.setBookTypeCode(bookTypeCode);
        bookType.setBookTypeName(bookTypeReqDto.getBookTypeName());

        BookType bookTypeInsert = bookTypeDao.save(bookType);

        // check 
        if(bookTypeInsert != null){
            baseInsertResDto.setId(bookTypeInsert.getBookTypeCode());
            baseInsertResDto.setMessage(Message.SUCCESS_SAVE.getMessage());
        } else{
            throw new RuntimeException("Failed to save data book type");
        } 

        return baseInsertResDto;
    }

    /**
     * update book type
     * 
     * validation - request forbidden (400)
     * - book type code is require: v
     * - book type name is require: v
     * - book type name is not blank: v
     *
     * - book type isn't available: v
     * - request book type isn't change: v
     * 
     * @param bookTypeReqDto
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public BaseTransactionResDto update(BookTypeUpdateReqDto bookTypeUpdateReqDto){
        BaseTransactionResDto baseUpdateResDto = new BaseTransactionResDto();

        if(bookTypeDao.isChangeByAllRequest(bookTypeUpdateReqDto.getBookTypeCode(), bookTypeUpdateReqDto.getBookTypeName()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request book type isn't change");

        BookType bookType = bookTypeDao.findByUpdate(BookType.class, bookTypeUpdateReqDto.getBookTypeCode());

        if(bookType != null){

            bookType.setBookTypeName(bookTypeUpdateReqDto.getBookTypeName());
            
            baseUpdateResDto.setMessage(Message.SUCCESS_UPDATE.getMessage());
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book type isn't available");
        }

        return baseUpdateResDto;
    }

    /**
     * delete book type
     * 
     * validation - request forbidden (400)
     * - book type isn't available: v
     * - book type still available on book: v
     * 
     * @param bookTypeReqDto
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public BaseTransactionResDto delete(BookTypeDeleteReqDto bookTypeDeleteReqDto){
        BaseTransactionResDto baseUpdateResDto = new BaseTransactionResDto();

        String bookTypeCode = bookTypeDeleteReqDto.getBookTypeCode();

        // check data type isn't available
        if(!bookTypeDao.isExistByBookTypeCode(bookTypeCode))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book type isn't available");
        
        // check book
        if(bookTypeDao.isExistOfBookTypeFK(bookTypeCode))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book type still available on book");

        // delete book type
        Boolean isDelete = bookTypeDao.delete(BookType.class, "book_type_code", bookTypeCode);
        
        if(isDelete){
            baseUpdateResDto.setMessage(Message.SUCCESS_DELETE.getMessage());
        }else{
            throw new RuntimeException("Failed to delete data book type");
        }
        
        return baseUpdateResDto;
    }

}
