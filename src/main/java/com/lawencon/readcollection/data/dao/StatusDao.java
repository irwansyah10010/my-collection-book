package com.lawencon.readcollection.data.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class StatusDao extends BaseDao{

    public List<Map<String, Object>> findAll(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT id, status_code, status_name ")
        .append("tb_status ");

        List<Map<String, Object>> result = new ArrayList<>();
        try {
            List<?> resultList = getEM()
                .createNativeQuery(sql.toString())
                .getResultList();

            for (Object obj : resultList) {
                Object[] o = (Object[]) obj;
                
                Map<String, Object> map = new HashMap<>();
                map.put("id", o[0]);
                map.put("statusCode", o[1]);
                map.put("statusName", o[2]);
                result.add(map);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    // public List<Map<String, Object>> findAll(Integer page, Integer data){
    //     StringBuilder sql = new StringBuilder();
    //     sql.append("SELECT id, status_code, status_name ")
    //     .append("tb_status ")
    //     .append("LIMIT :data OFFSET :offset ");

    //     List<Map<String, Object>> result = new ArrayList<>();
    //     try {
    //         List<?> resultList = getEM()
    //             .createNativeQuery(sql.toString())
    //             .setParameter("data", data)
    //             .setParameter("offset", (page - 1) * data)
    //             .getResultList();

    //         for (Object obj : resultList) {
    //             Object[] o = (Object[]) obj;
                
    //             Map<String, Object> map = new HashMap<>();
    //             map.put("id", o[0]);
    //             map.put("statusCode", o[1]);
    //             map.put("statusName", o[2]);
    //             result.add(map);
    //         }
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }

    //     return result;
    // }

}
