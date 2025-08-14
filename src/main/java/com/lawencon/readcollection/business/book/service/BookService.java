package com.lawencon.readcollection.business.book.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.readcollection.base.constant.Message;
import com.lawencon.readcollection.base.dto.res.BaseInsertResDto;
import com.lawencon.readcollection.base.dto.res.BaseResListDto;
import com.lawencon.readcollection.base.dto.res.BaseResSingleDto;
import com.lawencon.readcollection.base.dto.res.BaseUpdateAndDeleteResDto;
import com.lawencon.readcollection.business.book.dto.BookDeleteReqDto;
import com.lawencon.readcollection.business.book.dto.BookInsertReqDto;
import com.lawencon.readcollection.business.book.dto.BookSingleResDto;
import com.lawencon.readcollection.business.book.dto.BookTypeInsertReqDto;
import com.lawencon.readcollection.business.book.dto.BookUpdateReqDto;
import com.lawencon.readcollection.business.book.dto.BookUpdateStatusReqDto;
import com.lawencon.readcollection.business.booktype.dto.BookTypeInsertBookReqDto;
import com.lawencon.readcollection.data.dao.BookDao;
import com.lawencon.readcollection.data.dao.BookTypeBookDao;
import com.lawencon.readcollection.data.dao.BookTypeDao;
import com.lawencon.readcollection.data.dao.ReadBookDao;
import com.lawencon.readcollection.data.dao.StatusDao;
import com.lawencon.readcollection.data.model.Book;
import com.lawencon.readcollection.data.model.BookType;
import com.lawencon.readcollection.data.model.BookTypeBook;
import com.lawencon.readcollection.data.model.ReadBook;
import com.lawencon.readcollection.data.model.Status;

@Service
public class BookService {
    
    @Autowired
    private StatusDao statusDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookTypeDao bookTypeDao;

    @Autowired
    private BookTypeBookDao bookTypeBookDao;
    
    public BaseResListDto<Map<String, Object>> getAll(Integer page, Integer limit){
        List<Map<String,Object>> all = bookDao.findAll(page, limit);

        BaseResListDto<Map<String,Object>> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(all);
        baseResListDto.setCountOfData(bookDao.count(Book.class));
        baseResListDto.setLimit(limit);
        baseResListDto.setPage(page);

        return baseResListDto;
    }

    public BaseResSingleDto<?> getByIssbn(String issbn){
        Map<String, Object> byIssbn = bookDao.findByIssbn(issbn);

        BaseResSingleDto<Map<String,Object>> baseResSingleRes = new BaseResSingleDto<>();
        baseResSingleRes.setData(byIssbn);

        return baseResSingleRes;
    }

    public BaseResListDto<Map<String, Object>> getAll(Integer page, Integer limit, String search){
        List<Map<String,Object>> all = bookDao.findAll(page, limit, search);

        BaseResListDto<Map<String,Object>> baseResListDto = new BaseResListDto<>();
        baseResListDto.setData(all);
        baseResListDto.setCountOfData(statusDao.count(Status.class));
        baseResListDto.setLimit(limit);
        baseResListDto.setPage(page);

        return baseResListDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto add(BookInsertReqDto bookInsertReqDto){
        BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

        // save book
        Book book = new Book();
        Status status = statusDao.findByPK(Status.class,"N");
    
        book.setIssbn(bookInsertReqDto.getIssbn());
        book.setTitle(bookInsertReqDto.getTitle());
        book.setNumberOfPage(bookInsertReqDto.getNumberOfPage());
        book.setDescription(bookInsertReqDto.getDescription());
        book.setPrice(bookInsertReqDto.getPrice());
        book.setStatus(status);
        book.setPublisher(bookInsertReqDto.getPublisher());
        book.setAuthorName(bookInsertReqDto.getAuthorName());
        book.setReleaseDate(System.currentTimeMillis());  // dummy
        Book bookInsert = bookDao.save(book); // save book

        if(bookInsert != null){

            // get book type
            for (BookTypeInsertBookReqDto bookType : bookInsertReqDto.getBookTypes()) {
                BookType bt = bookTypeDao.findByPK(BookType.class,bookType.getBookTypeCode());

                // book type and book
                BookTypeBook bookTypeBook = new BookTypeBook();
                bookTypeBook.setBook(bookInsert);
                bookTypeBook.setBookType(bt);

                BookTypeBook bookTypeBookSave = bookTypeBookDao.save(bookTypeBook);

                if(bookTypeBookSave == null){
                    throw new RuntimeException("Failed to add book type");
                }
            }

            //
            baseInsertResDto.setId(bookInsert.getIssbn());
            baseInsertResDto.setMessage(Message.SUCCESS_SAVE.getMessage());
        }else{
            throw new RuntimeException("Failed to save book");
        }
        
        return baseInsertResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseInsertResDto addBookTypResDto(BookTypeInsertReqDto bookTypeInsertReqDto){
        BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

        // init book and book type
        Book book = bookDao.findByPK(Book.class, bookTypeInsertReqDto.getIssbn());
        BookType bookType = bookTypeDao.findByPK(BookType.class, bookTypeInsertReqDto.getBookTypeCode());

        if(book != null || bookType != null){
            // set book type and book
            BookTypeBook bookTypeBook = new BookTypeBook();
            bookTypeBook.setBook(book);
            bookTypeBook.setBookType(bookType);

            BookTypeBook bookTypeBookSave = bookTypeBookDao.save(bookTypeBook);

            if(bookTypeBookSave == null){
                throw new RuntimeException("Failed to add book type");
            }else{
                //
                baseInsertResDto.setId(bookTypeBookSave.getId());
                baseInsertResDto.setMessage(Message.SUCCESS_SAVE.getMessage());
            }
        }else{
            baseInsertResDto.setMessage("Failed to add");
        }
        
        return baseInsertResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateAndDeleteResDto update(BookUpdateReqDto bookUpdateReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = new BaseUpdateAndDeleteResDto();

        Book book = bookDao.findByUpdate(Book.class, bookUpdateReqDto.getIssbn());

        if(book != null){

            if(bookUpdateReqDto.getTitle() != null){
                book.setTitle(bookUpdateReqDto.getTitle());
            }

            if(bookUpdateReqDto.getNumberOfPage() != null){
                book.setNumberOfPage(bookUpdateReqDto.getNumberOfPage());
            }

            if(bookUpdateReqDto.getDescription() != null){
                book.setDescription(bookUpdateReqDto.getDescription());
            }

            if(bookUpdateReqDto.getPrice() != null){
                book.setPrice(bookUpdateReqDto.getPrice());
            }

            if(bookUpdateReqDto.getPublisher() != null){
                book.setPublisher(bookUpdateReqDto.getPublisher());
            }

            if(bookUpdateReqDto.getAuthorName() != null){
                book.setAuthorName(bookUpdateReqDto.getAuthorName());
            }

            //
            baseUpdateResDto.setMessage(Message.SUCCESS_UPDATE.getMessage());
        }else{
            baseUpdateResDto.setMessage(Message.FAILED_UPDATE.getMessage());
        }

        return baseUpdateResDto;
    }

    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateAndDeleteResDto updateStatus(BookUpdateStatusReqDto bookUpdateStatusReqDto){
        BaseUpdateAndDeleteResDto baseUpdateResDto = new BaseUpdateAndDeleteResDto();

        Book book = bookDao.findByUpdate(Book.class, bookUpdateStatusReqDto.getId());

        if(book != null){

            if(!book.getStatus().getStatusCode().equals(bookUpdateStatusReqDto.getStatusCode())){
                Status status = statusDao.findByPK(Status.class, bookUpdateStatusReqDto.getStatusCode());
    
                book.setStatus(status);
    
                baseUpdateResDto.setMessage(Message.SUCCESS_UPDATE.getMessage());
            }else{
                baseUpdateResDto.setMessage("Status code is equal");    
            }
        }else{
            baseUpdateResDto.setMessage(Message.FAILED_UPDATE.getMessage());
        }

        return baseUpdateResDto;
    }

    // min read and book type
    @Transactional(rollbackOn = Exception.class)
    public BaseUpdateAndDeleteResDto delete(BookDeleteReqDto bookDeleteReqDto){
        BaseUpdateAndDeleteResDto baseDeleteResDto = new BaseUpdateAndDeleteResDto();

        BookType book = bookDao.findByPK(BookType.class, bookDeleteReqDto.getIssbn());

        if(book != null){

            // delete read_book by issbn
            Boolean isDeleteReadBook = true;
            if(isDeleteReadBook){
                // delete book_type_book by issbn
                Boolean isDeleteBookTypeBook = true;
                if(isDeleteBookTypeBook){
                    // delete book by issbn
                    Boolean isDeleteBook = bookTypeDao.delete(Book.class, "issbn", bookDeleteReqDto.getIssbn());

                    if(isDeleteBook){
                        baseDeleteResDto.setMessage(Message.SUCCESS_UPDATE.getMessage()+"(data relation a book, deleted)");    
                    }else{
                        throw new RuntimeException("book failed delete");      
                    }
                }else{
                   throw new RuntimeException("book type failed delete"); 
                }
                
            }else{
                throw new RuntimeException("Read book failed");
            }

        }else{
            baseDeleteResDto.setMessage("No book available");
        }

        return baseDeleteResDto;
    }


    
}
