package com.example.newhomework.service;

import com.example.newhomework.dto.BookDto;
import com.example.newhomework.entity.Book;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    int create(Book book) throws IOException;

    int update(Book book) throws IOException;

    int delete(Long id) throws IOException;
}
