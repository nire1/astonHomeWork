package com.example.newhomework.service;

import com.example.newhomework.dto.BookDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    void create(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void update(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
