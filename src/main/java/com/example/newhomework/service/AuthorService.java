package com.example.newhomework.service;

import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.entity.Author;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthorService {
    int create(Author author) throws IOException;
    AuthorDto getById(long id);
    int delete(Long id) throws IOException;
    int update(Author author) throws IOException;
}
