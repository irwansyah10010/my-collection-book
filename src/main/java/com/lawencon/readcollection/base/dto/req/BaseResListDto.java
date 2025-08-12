package com.lawencon.readcollection.base.dto.req;

import java.util.List;

public class BaseResListDto<T> {
    
    private List<T> data;
    private Integer page;
    private Integer countPerPage;
    private Integer countOfData;
    
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }
    public Integer getCountOfData() {
        return countOfData;
    }
    public void setCountOfData(Integer countOfData) {
        this.countOfData = countOfData;
    }
    public Integer getPage() {
        return page;
    }
    public void setPage(Integer page) {
        this.page = page;
    }
    public Integer getCountPerPage() {
        return countPerPage;
    }
    public void setCountPerPage(Integer countPerPage) {
        this.countPerPage = countPerPage;
    }

    
}
