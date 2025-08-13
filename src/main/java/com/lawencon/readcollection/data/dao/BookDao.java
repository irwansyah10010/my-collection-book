package com.lawencon.readcollection.data.dao;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lawencon.readcollection.data.model.Book;

@Repository
public class BookDao extends BaseDao{

    // min book type
    // subquery column or join book type detail
    public List<Map<String, Object>> findAll(Integer page, Integer data){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tb.isbn, tb.book_name, ts.status_name, tbt.book_type_name, tb.number_of_page ")
        .append("FROM tb_book tb ")
        .append("INNER JOIN tb_status ts ON tb.status_id = ts.status_code ")
        .append("LEFT JOIN tb_book_detail tbd ON tb.isbn = tbd.isbn ")
        .append("INNER JOIN tb_book_type tbt ON tbt.book_type_code = tbd.book_type_code ")
        .append("LIMIT :data OFFSET :offset ");

        List<Map<String, Object>> result = new LinkedList<>();
        try {
            List<?> resultList = getEM()
                .createNativeQuery(sql.toString())
                .setParameter("data", data)
                .setParameter("offset", (page - 1) * data)
                .getResultList();

            for (Object obj : resultList) {
                Object[] o = (Object[]) obj;
                
                Map<String, Object> map = new HashMap<>();
                map.put("isbn", o[0]);
                map.put("bookName", o[1]);
                map.put("statusName", o[2]);
                map.put("category", o[3]);
                map.put("page", o[4]);
                result.add(map);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }


    // min book type
    // subquery column or join book type detail
    public List<Map<String, Object>> findAll(Integer page, Integer data, String search){

        /*
         * SELECT book_type_name 
         * FROM tb_book tb 
         * RIGHT JOIN tb_book_detail tbd ON tb.isbn = tbd.isbn
         * INNER JOIN tb_book_type tbt ON tbt.book_type_code = tbd..book_type_code
         * WHERE tb.isbn = ?
         */

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tb.isbn, tb.book_name, ts.status_name, tb.status_name as book_type_name, tb.number_of_page ")
        .append("FROM tb_book tb ")
        .append("INNER JOIN tb_status ts ON tb.status_id = ts.status_code ")
        .append("LEFT JOIN tb_book_detail tbd ON tb.isbn = tbd.isbn ")
        .append("INNER JOIN tb_book_type tbt ON tbt.book_type_code = tbd.book_type_code ")
        .append("WHERE isbn like :search||'%' ")
        .append("OR book_name like :search||'%' ")
        .append("OR book_type_name like '%'||:search||'%' ")
        .append("LIMIT :data OFFSET :offset ");

        List<Map<String, Object>> result = new LinkedList<>();
        try {
            List<?> resultList = getEM()
                .createNativeQuery(sql.toString())
                .setParameter("data", data)
                .setParameter("offset", (page - 1) * data)
                .setParameter("search", search)
                .getResultList();

            for (Object obj : resultList) {
                Object[] o = (Object[]) obj;
                
                Map<String, Object> map = new HashMap<>();
                map.put("isbn", o[0]);
                map.put("bookName", o[1]);
                map.put("statusName", o[2]);
                map.put("category", o[3]);
                map.put("page", o[4]);
                result.add(map);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

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
