package com.lawencon.readcollection.dto.readbook;

import javax.validation.constraints.NotNull;

public class ReadBookInsertReqDto {

    @NotNull(message = "page of read is must required")
    private Integer pageOfRead;

    @NotNull(message = "book id is must required")
    private String bookId;

    private String status;

    public Integer getPageOfRead() {
        return pageOfRead;
    }

    public void setPageOfRead(Integer pageOfRead) {
        this.pageOfRead = pageOfRead;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
