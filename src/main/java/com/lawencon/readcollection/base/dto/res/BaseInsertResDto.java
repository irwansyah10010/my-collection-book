package com.lawencon.readcollection.base.dto.res;

import java.util.Map;

public class BaseInsertResDto {
    private String id;
    private Map<String,String> errors;
    private String message;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
    
}
