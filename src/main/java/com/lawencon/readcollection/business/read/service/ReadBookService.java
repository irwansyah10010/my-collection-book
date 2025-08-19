package com.lawencon.readcollection.business.read.service;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.server.ResponseStatusException;

import com.lawencon.readcollection.base.constant.Message;
import com.lawencon.readcollection.base.dto.res.BaseTransactionResDto;
import com.lawencon.readcollection.base.dto.res.BaseResListDto;
import com.lawencon.readcollection.base.dto.validation.ValidationRuntimeException;
import com.lawencon.readcollection.business.read.dto.ReadBookInsertReqDto;
import com.lawencon.readcollection.data.dao.BookDao;
import com.lawencon.readcollection.data.dao.ReadBookDao;
import com.lawencon.readcollection.data.dao.StatusDao;
import com.lawencon.readcollection.data.model.Book;
import com.lawencon.readcollection.data.model.ReadBook;
import com.lawencon.readcollection.data.model.Status;

@Service
public class ReadBookService {
    
    @Autowired
    private StatusDao statusDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ReadBookDao readBookDao;

    public BaseResListDto<Map<String, Object>> getAll(Integer page, Integer limit){
        List<Map<String,Object>> all = readBookDao.findAll(page, limit);

        BaseResListDto<Map<String,Object>> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(all);
        baseResListDto.setCountOfData(bookDao.count(Book.class));
        baseResListDto.setLimit(limit);
        baseResListDto.setPage(page);

        return baseResListDto;
    }

    /**
     * 
     * get all read
     * 
     * validation - request forbidden (400)
     * - Character does not allow: v
     * 
     * @param page
     * @param limit
     * @param search
     * @param status
     * @return
     */
    public BaseResListDto<Map<String, Object>> getAll(Integer page, Integer limit, String search, String status){

        if (!search.isEmpty() && !search.matches("^[a-zA-Z0-9 ]*$"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Search contains invalid characters");

        if (!status.isEmpty() && !status.matches("^[a-zA-Z0-9 ]*$"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Status contains invalid characters");

        List<Map<String,Object>> all = readBookDao.findAll(page, limit, search, status);

        BaseResListDto<Map<String,Object>> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(all);
        baseResListDto.setCountOfData(bookDao.count(Book.class));
        baseResListDto.setLimit(limit);
        baseResListDto.setPage(page);

        return baseResListDto;
    }

    /**
     * 
     * @param issbn
     * @param page
     * @param limit
     * @return
     */
    public BaseResListDto<Map<String, Object>> getAllByIssbn(String issbn, Integer page, Integer limit){
        List<Map<String,Object>> all = readBookDao.findAllByIssbn(issbn, page, limit);

        BaseResListDto<Map<String,Object>> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(all);
        baseResListDto.setCountOfData(bookDao.count(Book.class));
        baseResListDto.setLimit(limit);
        baseResListDto.setPage(page);

        return baseResListDto;
    }

    /**
     * 
     * validation - request forbidden (400)
     * - Issbn is must required: v
     * - page of read is must required: v
     * 
     * - data book isnt available: v
     * - page of read isn't change: v
     * - Status code and data read book isn't match -> (data > 0 == ! 'R' || data = 0 ==  ! 'N' ): v
     * - Page of read out of reach: v
     * 
     * - reading complete if page of read to equal max book: v
     * - reading read if page of read to unless max book: v
     
     * - book has been complete(max book) and is read false: v
     * 
     * @param readBookInsertReqDto
     * @return
     */
    @Transactional(rollbackOn = Exception.class)
    public BaseTransactionResDto readingByPage(ReadBookInsertReqDto readBookInsertReqDto){
        BaseTransactionResDto baseInsertResDto = new BaseTransactionResDto();

        String issbn = readBookInsertReqDto.getIssbn();
        Integer pageOfRead = readBookInsertReqDto.getPageOfRead();
        String note = readBookInsertReqDto.getNote();
        Boolean isReread = readBookInsertReqDto.getIsReread();
        isReread = (isReread != null) ?isReread:false;

        // check issbn is not exist
        if(!bookDao.isExistBook(issbn)){
            BeanPropertyBindingResult bindingResult =
                new BeanPropertyBindingResult(readBookInsertReqDto, "readBookInsertReqDto");
            bindingResult.rejectValue("issbn", "empty", "Issbn isn't available");

            throw new ValidationRuntimeException(bindingResult);
        }

        Book book = bookDao.findByPK(Book.class, issbn);
        String statusCodeBook = book.getStatus().getStatusCode();
        Integer numberOfPageBook = book.getNumberOfPage();

        /*  no re read book validation */
        if(!isReread){
            // validation complete read
            if(statusCodeBook.equals("C"))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book has been complete");

        }else{
            statusCodeBook = "R";
            book.setStatus(statusDao.findByPK(Status.class, statusCodeBook));
        }
        
        /*  global validation */
        // if page equal to number of page
        if(pageOfRead.equals(numberOfPageBook))
            book.setStatus(statusDao.findByPK(Status.class, "C"));
        
        
        // read book isnt exist
        if(!readBookDao.isExistReadBook(issbn))
            book.setStatus(statusDao.findByPK(Status.class, "R"));
        

        // check data read book and status book not match (data > 0 == ! 'R' || data = 0 ==  ! 'N' ) // note
        if(readBookDao.isExistReadBook(issbn) && !statusCodeBook.equals("R") ||
            !readBookDao.isExistReadBook(issbn) && !statusCodeBook.equals("N"))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status code and data read book isn't match");

        // validation page 
        if(pageOfRead > numberOfPageBook && pageOfRead < 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page of read out of reach");
        

        // check if last data is same
        Map<String,Object> lastByIssbn = readBookDao.findLastByIssbn(issbn);
        if(pageOfRead.equals(lastByIssbn.get("pageOfRead")))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "page of read isn't change");

        ReadBook readBook = new ReadBook();
        readBook.setPageOfRead(pageOfRead);
        readBook.setDateOfRead(System.currentTimeMillis());
        readBook.setBook(book);
        readBook.setNote(note != null?note:"No comment");

        ReadBook readBookInsert = readBookDao.save(readBook);

        if(readBookInsert != null){            

            Book bookUpdate = bookDao.update(book);

            if(bookUpdate != null){
                baseInsertResDto.setId(readBookInsert.getId());
                baseInsertResDto.setMessage("Thank you for reading");
            }else
                throw new RuntimeException("Failed to save");
        }else
            throw new RuntimeException("Failed to save");
        
        return baseInsertResDto;
    }

}
