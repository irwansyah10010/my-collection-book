package com.lawencon.readcollection.dto.book;

import java.util.List;

import com.lawencon.readcollection.dto.readbook.ReadBookResDataDto;

public class BookResDataDto {
 
    private String id;
    private String title;
    private Integer numberOfPage;
    private String status;

    private List<ReadBookResDataDto> listRedBooks;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getNumberOfPage() {
        return numberOfPage;
    }
    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public List<ReadBookResDataDto> getListRedBooks() {
        return listRedBooks;
    }
    public void setListRedBooks(List<ReadBookResDataDto> listRedBooks) {
        this.listRedBooks = listRedBooks;
    }

        


}
