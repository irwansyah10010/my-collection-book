package com.lawencon.readcollection.data.dao;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.lawencon.readcollection.data.model.ReadBook;

@Repository
public class ReadBookDao extends BaseDao{

    public List<Map<String, Object>> findAll(Integer page, Integer data){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tb.issbn, tb.title, tb.description, ")
        .append("CONCAT(tb.publisher,'-', tb.author_name) AS publisher, ")
        .append("tbc.book_types, tbrc.number_of_read, ts.status_name ")
        .append("FROM tb_book tb ")
        .append("INNER JOIN tb_status ts on ts.status_code = tb.status_code ")
        .append("INNER JOIN ( ")
            .append("SELECT tb.issbn, COUNT(trb.id) AS number_of_read ")
            .append("FROM tb_book tb ")
            .append("LEFT JOIN tb_read_book trb ON tb.issbn = trb.issbn ")
            .append("GROUP BY tb.issbn ")
        .append(") tbrc ON tb.issbn = tbrc.issbn ")
        .append("LEFT JOIN ( ")
            .append("SELECT issbn, GROUP_CONCAT(book_type_name ORDER BY book_type_name SEPARATOR ', ') AS book_types ")
            .append("FROM ( ")
                .append("SELECT tb.issbn, book_type_name ")
                .append("FROM tb_book tb ")
                .append("RIGHT JOIN tb_book_type_book tbd ON tb.issbn = tbd.issbn ")
                .append("INNER JOIN tb_book_type tbt ON tbt.book_type_code = tbd.book_type_code ")
            .append(") tbg GROUP BY issbn ")
        .append(") tbc ON tb.issbn = tbc.issbn ");

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
                map.put("description", o[2]);
                map.put("publisher", o[3]);
                map.put("bookTypes", o[4]);
                map.put("numberOfRead", o[5]);
                map.put("status", o[6]);
                result.add(map);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public List<Map<String, Object>> findAll(Integer page, Integer data, String search, String status){
        StringBuilder sql = new StringBuilder();

        StringBuilder conditionSql = new StringBuilder();
        
        if(!search.isEmpty())
            conditionSql.append("AND (tb.title LIKE CONCAT(:search,'%') ").append("OR tb.issbn LIKE CONCAT(:search,'%')) ");
        

        if (!status.isEmpty())
            conditionSql.append("AND tb.status_code = :status ");
        
        sql.append("SELECT tb.issbn, tb.title, tb.description, ")
        .append("CONCAT(tb.publisher,'-', tb.author_name) AS publisher, ")
        .append("tbc.book_types, tbrc.number_of_read, ts.status_name ")
        .append("FROM tb_book tb ")
        .append("INNER JOIN tb_status ts on ts.status_code = tb.status_code ")
        .append("INNER JOIN ( ")
            .append("SELECT tb.issbn, COUNT(trb.id) AS number_of_read ")
            .append("FROM tb_book tb ")
            .append("LEFT JOIN tb_read_book trb ON tb.issbn = trb.issbn ")
            .append("GROUP BY tb.issbn ")
        .append(") tbrc ON tb.issbn = tbrc.issbn ")
        .append("LEFT JOIN ( ")
            .append("SELECT issbn, GROUP_CONCAT(book_type_name ORDER BY book_type_name SEPARATOR ', ') AS book_types ")
            .append("FROM ( ")
                .append("SELECT tb.issbn, book_type_name ")
                .append("FROM tb_book tb ")
                .append("RIGHT JOIN tb_book_type_book tbd ON tb.issbn = tbd.issbn ")
                .append("INNER JOIN tb_book_type tbt ON tbt.book_type_code = tbd.book_type_code ")
            .append(") tbg GROUP BY issbn ")
        .append(") tbc ON tb.issbn = tbc.issbn ")
        .append("WHERE 1=1 ")
        .append(conditionSql.toString());
        
        List<Map<String, Object>> result = new LinkedList<>();
        try {
            Query setParameter = getEM()
                            .createNativeQuery(sql.toString())
                            .setFirstResult((page - 1) * data)   // offset
                            .setMaxResults(data);

            if(!search.isEmpty())
                setParameter.setParameter("search", search);
            

            if (!status.isEmpty())
                setParameter.setParameter("status", status);

            List<?> resultList = setParameter
                .getResultList();

            for (Object obj : resultList) {
                Object[] o = (Object[]) obj;
                
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("issbn", o[0]);
                map.put("title", o[1]);
                map.put("description", o[2]);
                map.put("publisher", o[3]);
                map.put("bookTypes", o[4]);
                map.put("numberOfRead", o[5]);
                map.put("status", o[6]);
                result.add(map);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }
    
    public List<Map<String, Object>> findAllByIssbn(String issbn, Integer page, Integer data){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tb.issbn, tb.title, trb.date_of_read, trb.page_of_read, trb.note ")
        .append("FROM tb_book tb ")
        .append("LEFT JOIN tb_read_book trb ON tb.issbn = trb.issbn ")
        .append("WHERE tb.issbn = :issbn ")
        .append("ORDER BY trb.date_of_read ");

        List<Map<String, Object>> result = new LinkedList<>();
        try {
            List<?> resultList = getEM()
                .createNativeQuery(sql.toString())
                .setParameter("issbn", issbn)
                .setFirstResult((page - 1) * data)   // offset
                .setMaxResults(data)
                .getResultList();

            for (Object obj : resultList) {
                Object[] o = (Object[]) obj;
                
                Map<String, Object> map = new LinkedHashMap<>();
                map.put("issbn", o[0]);
                map.put("title", o[1]);
                map.put("dateOfRead", o[2]);
                map.put("pageOfRead", o[3]);
                map.put("note", o[4]);
                result.add(map);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public Map<String, Object> findLastByIssbn(String issbn){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tb.issbn, tb.title, trb.date_of_read, trb.page_of_read, trb.note ")
        .append("FROM tb_book tb ")
        .append("LEFT JOIN tb_read_book trb ON tb.issbn = trb.issbn ")
        .append("WHERE tb.issbn = :issbn ")
        .append("ORDER BY trb.date_of_read DESC ");

        Map<String, Object> result = new LinkedHashMap<>();
        try {
            Object obj = getEM()
                .createNativeQuery(sql.toString())
                .setParameter("issbn", issbn)
                .getResultList().get(0);

            
            Object[] o = (Object[]) obj;
            
            
            result.put("issbn", o[0]);
            result.put("title", o[1]);
            result.put("dateOfRead", o[2]);
            result.put("pageOfRead", (Integer) o[3]);
            result.put("note", o[4]);
            
            
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public boolean isExistReadBook(String issbn){
        return count(ReadBook.class, Map.of("issbn", issbn)) > 0;
    }
}
