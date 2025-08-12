package com.lawencon.readcollection.base.dto.req;

public class BaseResSingleDto<T> {
    
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }    
}
