package com.example.newhomework.dao;

import com.example.newhomework.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    int create(Book book) throws SQLException;
    List<Book> getAll();
    int delete(long id);
    int update(Book book) throws SQLException;
    boolean existById(Long id) throws SQLException;
}
