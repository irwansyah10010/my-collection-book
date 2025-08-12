package com.lawencon.readcollection.data.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.readcollection.data.model.ReadBook;

@Repository
public class ReadBookDao extends BaseDao{
    
    @SuppressWarnings("unchecked")
    public List<ReadBook> getByBookId(String bookId){
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM tb_read_book WHERE book_id = :bookId");

        List<ReadBook> lists = getEM().createNativeQuery(sql.toString(),ReadBook.class)
        .setParameter("bookId", bookId)
        .getResultList();

        return lists;
    }
}
