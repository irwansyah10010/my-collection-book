package com.lawencon.readcollection.business.booktype.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BookTypeInsertBookReqDto {
    
    @NotNull(message = "book type code is must required")
    @NotBlank(message = "book type code isn't only whitespace")
    private String bookTypeCode;

    public String getBookTypeCode() {
        return bookTypeCode;
    }

    public void setBookTypeCode(String bookTypeCode) {
        this.bookTypeCode = bookTypeCode;
    }

    
}
