package com.lawencon.readcollection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_book_type")
public class BookType {
    
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "book_type_code",length = 5,nullable = false, unique = true)
    private String bookTypeCode;

    @Column(name = "book_type_name",length = 20,nullable = false)
    private String bookTypeName;
}
