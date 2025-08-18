package com.lawencon.readcollection.business.read.dto;

import javax.validation.constraints.NotNull;

public class ReadBookInsertReqDto {

    @NotNull(message = "page of read is must required")
    private Integer pageOfRead;

    @NotNull(message = "issbn is must required")
    private String issbn;

    private String note;

    private Boolean isReread;

    public Integer getPageOfRead() {
        return pageOfRead;
    }

    public void setPageOfRead(Integer pageOfRead) {
        this.pageOfRead = pageOfRead;
    }

    public String getIssbn() {
        return issbn;
    }

    public void setIssbn(String issbn) {
        this.issbn = issbn;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getIsReread() {
        return isReread;
    }

    public void setIsReread(Boolean isReread) {
        this.isReread = isReread;
    }
    
    
    
}
