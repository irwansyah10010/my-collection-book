package com.lawencon.readcollection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tb_book")
public class Book {
    
    @Id
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Column(name = "issbn",nullable = false,length = 15)
    private String issbn;

    @Column(name = "title",nullable = false,length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "book_type_id")
    private BookType bookType;

    @Column(name = "publisher",nullable = false,length = 20)
    private String publisher;

    @Column(name = "author_name",nullable = false,length = 20)
    private String authorName;
}
