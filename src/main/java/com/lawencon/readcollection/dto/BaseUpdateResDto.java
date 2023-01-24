package com.lawencon.readcollection.dto;

public class BaseUpdateResDto {
    public Integer version;
    public String message;


    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
