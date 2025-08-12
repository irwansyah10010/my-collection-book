package com.lawencon.readcollection.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.readcollection.data.model.Book;

@Repository
public class BookDao extends BaseDao{
    
    @SuppressWarnings("unchecked")
    public List<Book> getByBookTypeId(String bookTypeId){
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM tb_book WHERE book_type_id = :bookTypeId");

        List<Book> lists = getEM().createNativeQuery(sql.toString(),Book.class)
        .setParameter("bookTypeId", bookTypeId)
        .getResultList();

        return lists;
    }
}
