package com.lawencon.readcollection.base.dto.res;

public class BaseResSingleDto<T> {
    
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }    
}
