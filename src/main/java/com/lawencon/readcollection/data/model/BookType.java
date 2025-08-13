package com.lawencon.readcollection.data.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_book_type")
public class BookType {

    @Id
    @Column(name = "book_type_code",length = 5,nullable = false, unique = true)
    private String bookTypeCode;

    @Column(name = "book_type_name",length = 20,nullable = false)
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
