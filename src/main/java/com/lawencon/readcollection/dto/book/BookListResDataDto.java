package com.lawencon.readcollection.dto.book;

import java.util.List;

import com.lawencon.readcollection.model.ReadBook;


public class BookListResDataDto {
 
    private String id;
    private String title;
    private Integer numberOfPage;
    private String status;

    private List<ReadBook> ReadBooks;

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
    public List<ReadBook> getReadBooks() {
        return ReadBooks;
    }
    public void setReadBooks(List<ReadBook> readBooks) {
        ReadBooks = readBooks;
    }
}
