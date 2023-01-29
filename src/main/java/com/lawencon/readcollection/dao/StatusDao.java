package com.lawencon.readcollection.dao;

import org.springframework.stereotype.Repository;

import com.lawencon.readcollection.model.Status;

@Repository
public class StatusDao extends BaseDao{

    public Status getByStatusCode(String statusCode){
        String sql = "SELECT id, status_code, status_name FROM tb_status WHERE status_code = :statusCode";

        Object obj = null;

        try {
            obj = getEM().createNativeQuery(sql,Status.class)
                    .setParameter("statusCode", statusCode)
                    .getSingleResult(); 
        } catch (Exception e) {
            
        }
        
        Status status = null;
        if(obj != null){            
            status = (Status) obj;
        }

        return status;
    }
}
