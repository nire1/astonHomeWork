package com.example.newhomework.dao;

import com.example.newhomework.entity.Book;

import java.util.List;

public interface BookDao {
    int create(Book book);
    List<Book> getAll();
    int delete(long id);
    int update(Book book);
}
