package com.lawencon.readcollection.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lawencon.readcollection.data.model.BookType;
import com.lawencon.readcollection.data.model.BookTypeBook;

@Repository
public class BookTypeDao extends BaseDao{

    @SuppressWarnings("unchecked")
    public List<BookType> findAll(){
        String sql = "SELECT * FROM tb_book_type";

        List<BookType> result = new ArrayList<>();

        try {
            result = getEM()
            .createNativeQuery(sql.toString(), BookType.class)
            .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Boolean isExistByBookTypeCode(String bookTypeCode){
        return count(BookType.class, Map.of("book_type_code", bookTypeCode)) > 0;
    }

    public Boolean isChangeByAllRequest(String bookTypeCode, String bookTypeName){
        return count(BookType.class,
            Map.of(
                "book_type_code", bookTypeCode,
                "book_type_name", bookTypeName
            )
        ) > 0;
    }

    // book type book mine
    public Boolean isExistOfBookTypeFK(String bookCodeType){
        return count(BookTypeBook.class,
            Map.of(
                "book_type_code", bookCodeType
            )
        ) > 0;
    }
}
