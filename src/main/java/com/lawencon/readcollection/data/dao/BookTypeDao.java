package com.lawencon.readcollection.data.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.readcollection.data.model.BookType;

@Repository
public class BookTypeDao extends BaseDao{

    public BookType findByBookTypeCode(String bookTypeCode){
        String sql = "SELECT id, book_type_code, book_type_name FROM tb_book_type WHERE book_type_code = :bookTypeCode";

        Object obj = null;

        try {
            obj = getEM().createNativeQuery(sql,BookType.class)
                    .setParameter("bookTypeCode", bookTypeCode)
                    .getSingleResult(); 
        } catch (Exception e) {
            
        }
        
        BookType bookType = null;
        if(obj != null){            
            bookType = (BookType) obj;
        }

        return bookType;
    }
}
