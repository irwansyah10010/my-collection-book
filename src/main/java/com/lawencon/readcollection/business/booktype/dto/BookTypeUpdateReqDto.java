package com.lawencon.readcollection.business.booktype.dto;

import javax.validation.constraints.NotNull;

public class BookTypeUpdateReqDto {
    
    @NotNull(message = "book code is must required")
    private String bookTypeCode;

    private String bookTypeName;

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

}
