package com.lawencon.readcollection.dto.booktype;

import javax.validation.constraints.NotNull;

public class BookTypeInsertBookReqDto {
    
    @NotNull(message = "code is must required")
    private String bookTypeCode;

    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    
}
