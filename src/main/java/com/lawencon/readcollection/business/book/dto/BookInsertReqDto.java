package com.lawencon.readcollection.business.book.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.lawencon.readcollection.business.booktype.dto.BookTypeInsertBookReqDto;

public class BookInsertReqDto {
    
    @NotNull(message = "Issbn is must required")
    @NotBlank(message = "issbn isn't only whitespace")
    private String issbn;

    @NotNull(message = "title is must required")
    @NotBlank(message = "title isn't only whitespace")
    private String title;

    private String publisher;

    private String authorName;

    @NotNull(message = "number of page is must required")
    private Integer numberOfPage;
    
    private String description;

    private BigDecimal price;

    private LocalDate releaseDate;

    private List<BookTypeInsertBookReqDto> bookTypes;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookTypeInsertBookReqDto> getBookTypes() {
        return bookTypes;
    }

    public void setBookTypes(List<BookTypeInsertBookReqDto> bookType) {
        this.bookTypes = bookType;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

}
