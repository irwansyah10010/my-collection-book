package com.lawencon.readcollection.dto.book;

import javax.validation.constraints.NotNull;

public class BookUpdateStatusReqDto {
    
    @NotNull(message = "id is must required")
    private String id;

    @NotNull(message = "status is must required")
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    
    
}
