package com.lawencon.readcollection.dto.book;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class BookInsertReqDto {
    
    @NotNull(message = "isbn is must required")
    private String issbn;

    @NotNull(message = "title is must required")
    private String title;

    @NotNull(message = "number of page is must required")
    private Integer numberOfPage;
    
    private String synopsis;

    private BigDecimal price;

    private String publisher;

    private String authorName;

    @NotNull(message = "book type id is must required")
    private String bookTypeId;


    public String getIssbn() {
        return issbn;
    }

    public void setIssbn(String issbn) {
        this.issbn = issbn;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookTypeId() {
        return bookTypeId;
    }

    public void setBookTypeId(String bookTypeId) {
        this.bookTypeId = bookTypeId;
    }
}
