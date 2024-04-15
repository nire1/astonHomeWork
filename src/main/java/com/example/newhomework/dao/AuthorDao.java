package com.example.newhomework.dao;

import com.example.newhomework.entity.Author;

import java.sql.SQLException;

public interface AuthorDao {
    void create(Author author) throws SQLException;
    Author getById(long id);
    int delete(long id) throws SQLException;
    int update(Author author) throws SQLException;
    boolean existById(Long id);
}
