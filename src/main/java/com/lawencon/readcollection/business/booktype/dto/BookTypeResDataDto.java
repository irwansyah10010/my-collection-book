package com.lawencon.readcollection.business.booktype.dto;

import java.util.List;

import com.lawencon.readcollection.data.model.Book;

public class BookTypeResDataDto {
    private String id;
    private String bookTypeCode;
    private String bookTypeName;

    private List<Book> books;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    public String getBookTypeName() {
        return bookTypeName;
    }

    public void setBookTypeName(String bookTypeName) {
        this.bookTypeName = bookTypeName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }    
    
}
