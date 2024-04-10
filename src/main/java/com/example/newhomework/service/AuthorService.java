package com.example.newhomework.service;

import com.example.newhomework.dto.AuthorDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthorService {
    void create(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    AuthorDto getById(long id);
    void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    void update(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
