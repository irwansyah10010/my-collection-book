package com.lawencon.readcollection.data.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.readcollection.data.model.BookType;

@Repository
public class BookTypeDao extends BaseDao{

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
}
