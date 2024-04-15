package com.example.newhomework.service;

import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.entity.Author;
import java.io.IOException;
import java.sql.SQLException;

public interface AuthorService {
    void create(Author author) throws IOException, SQLException;
    AuthorDto getById(long id);
    int delete(Long id) throws IOException, SQLException;
    int update(Author author) throws IOException, SQLException;
}
