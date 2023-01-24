package com.lawencon.readcollection.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDao {

    @PersistenceContext
	private EntityManager entityManager;
	
	EntityManager getEM() {
		return entityManager;
	}
    
    public <T> T save(T entity){
        getEM().persist(entity);


        return entity;
    }

    public <T> T update(T entity){
        T entityUpdate = getEM().merge(entity);

        getEM().flush();

        return entityUpdate;
    }

    public <T> T findById(Class<T> clazz,String id){
        T entityFind = null;

        try {
            entityFind = getEM().find(clazz, id);
                        
        } catch (Exception e) {
            
        }

        if(entityFind != null){
            getEM().detach(entityFind);
        }

        return entityFind;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(String tablename,Class<T> clazz){
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM ")
        .append(tablename);

        List<T> lists = getEM().createNativeQuery(sql.toString(),clazz).getResultList();

        return lists;
    }
}
