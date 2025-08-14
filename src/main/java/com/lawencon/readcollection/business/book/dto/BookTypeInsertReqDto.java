package com.lawencon.readcollection.business.book.dto;

import javax.validation.constraints.NotNull;

public class BookTypeInsertReqDto {
    @NotNull(message = "book type code is must required")
    private String bookTypeCode;

    @NotNull(message = "issbn is must required")
    private String issbn;

    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    public String getIssbn() {
        return issbn;
    }

    public void setIssbn(String issbn) {
        this.issbn = issbn;
    }
    
}
