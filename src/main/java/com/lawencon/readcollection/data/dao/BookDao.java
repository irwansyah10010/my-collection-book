package com.lawencon.readcollection.data.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.lawencon.readcollection.data.model.Book;
import com.lawencon.readcollection.data.model.BookTypeBook;
import com.lawencon.readcollection.util.DatetimeUtil;

@Repository
public class BookDao extends BaseDao{

    
    public Map<String, Object> findByIssbn(String issbn){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tb.issbn, tb.title, ts.status_name, tbcg.book_types, tb.number_of_page, tb.author_name, tb.publisher, tb.release_date ")
        .append("FROM tb_book tb ")
        .append("INNER JOIN tb_status ts ON tb.status_code = ts.status_code ")
        .append("LEFT JOIN ( ")
            .append("SELECT issbn, GROUP_CONCAT(book_type_name ORDER BY book_type_name SEPARATOR ', ') AS book_types ")
            .append("FROM ( ")
                .append("SELECT tb.issbn, book_type_name ")
                .append("FROM tb_book tb ")
                .append("RIGHT JOIN tb_book_type_book tbd ON tb.issbn = tbd.issbn ")
                .append("INNER JOIN tb_book_type tbt ON tbt.book_type_code = tbd.book_type_code ")
            .append(") tbc GROUP BY issbn ")
        .append(") tbcg ON tb.issbn = tbcg.issbn ")
        .append("WHERE tb.issbn=:issbn ");

        Map<String, Object> result = new LinkedHashMap<>();
        try {
            Object singleResult = getEM()
                .createNativeQuery(sql.toString())
                .setParameter("issbn", issbn)
                .getSingleResult();;

            
            Object[] o = (Object[]) singleResult;
            
            result.put("issbn", o[0]);
            result.put("title", o[1]);
            result.put("statusName", o[2]);
            result.put("category", o[3]);
            result.put("page", o[4]);
            result.put("authorName", o[5]);
            result.put("publisher", o[6]);
            result.put("releaseDate", DatetimeUtil.epochMillisToLocalDate(((BigInteger) o[7]).longValue()));

        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }


    
    public List<Map<String, Object>> findAll(Integer page, Integer data){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tb.issbn, tb.title, ts.status_name, tbcg.book_types, tb.number_of_page ")
        .append("FROM tb_book tb ")
        .append("INNER JOIN tb_status ts ON tb.status_code = ts.status_code ")
        .append("LEFT JOIN ( ")
            .append("SELECT issbn, GROUP_CONCAT(book_type_name ORDER BY book_type_name SEPARATOR ', ') AS book_types ")
            .append("FROM ( ")
                .append("SELECT tb.issbn, book_type_name ")
                .append("FROM tb_book tb ")
                .append("RIGHT JOIN tb_book_type_book tbd ON tb.issbn = tbd.issbn ")
                .append("INNER JOIN tb_book_type tbt ON tbt.book_type_code = tbd.book_type_code ")
            .append(") tbc GROUP BY issbn ")
        .append(") tbcg ON tb.issbn = tbcg.issbn ");

        List<Map<String, Object>> result = new LinkedList<>();
        try {
            List<?> resultList = getEM()
                .createNativeQuery(sql.toString())
                .setFirstResult((page - 1) * data)   // offset
                .setMaxResults(data)  
                .getResultList();

            for (Object obj : resultList) {
                Object[] o = (Object[]) obj;
                
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("issbn", o[0]);
                map.put("title", o[1]);
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

    
    public List<Map<String, Object>> findAll(Integer page, Integer data, String search){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tb.issbn, tb.title, ts.status_name, tbcg.book_types, tb.number_of_page ")
        .append("FROM tb_book tb ")
        .append("INNER JOIN tb_status ts ON tb.status_code = ts.status_code ")
        .append("LEFT JOIN ( ")
            .append("SELECT issbn, GROUP_CONCAT(book_type_name ORDER BY book_type_name SEPARATOR ', ') AS book_types ")
            .append("FROM ( ")
                .append("SELECT tb.issbn, book_type_name ")
                .append("FROM tb_book tb ")
                .append("RIGHT JOIN tb_book_type_book tbd ON tb.issbn = tbd.issbn ")
                .append("INNER JOIN tb_book_type tbt ON tbt.book_type_code = tbd.book_type_code ")
            .append(") tbc GROUP BY issbn ")
        .append(") tbcg ON tb.issbn = tbcg.issbn ")
        .append("WHERE issbn like CONCAT(:search,'%') ")
        .append("OR title like CONCAT(:search,'%') ");

        List<Map<String, Object>> result = new LinkedList<>();
        try {
            List<?> resultList = getEM()
                .createNativeQuery(sql.toString())
                .setParameter("search", search)
                .setFirstResult((page - 1) * data)   // offset
                .setMaxResults(data)
                .getResultList();

            for (Object obj : resultList) {
                Object[] o = (Object[]) obj;
                
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("issbn", o[0]);
                map.put("title", o[1]);
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

    public boolean isExistBook(String issbn){
        return count(Book.class, Map.of("issbn", issbn)) > 0;
    }


    public Boolean isChangeByAllRequest(String issbn, String title, Integer numberOfPage, String description, BigDecimal price, String publisher, String authorName, LocalDate releaseDate){
        return count(Book.class,
            Map.of(
                "issbn", issbn,
                "title", title,
                "number_of_page", numberOfPage,
                "description", description,
                "price", price,
                "publisher",publisher,
                "author_name",authorName,
                "release_date", DatetimeUtil.localDateToEpochMilli(releaseDate)
            )
        ) > 0;
    }


    public Boolean isExistOfBookFK(String issbn){
        return count(BookTypeBook.class,
            Map.of(
                "issbn", issbn
            )
        ) > 0;
    }
}
