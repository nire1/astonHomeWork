package com.example.newhomework.dao;

import com.example.newhomework.entity.Author;

public interface AuthorDao {
    int create(Author author);
    Author getById(long id);
    int delete(long id);
    int update(Author author);
    boolean existById(Long id);
}
