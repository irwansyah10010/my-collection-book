package com.lawencon.readcollection.dto.readbook;

import java.time.LocalDateTime;

public class ReadBookResDataDto {
    private String id;   
    private Integer pageOfRead;
    private LocalDateTime dateOfLastRead;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPageOfRead() {
        return pageOfRead;
    }

    public void setPageOfRead(Integer pageOfRead) {
        this.pageOfRead = pageOfRead;
    }

    public LocalDateTime getDateOfLastRead() {
        return dateOfLastRead;
    }

    public void setDateOfLastRead(LocalDateTime dateOfLastRead) {
        this.dateOfLastRead = dateOfLastRead;
    }


    
}
