package com.example.newhomework.service;

import com.example.newhomework.dto.BookDto;
import com.example.newhomework.entity.Book;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BookService {
    List<BookDto> getAll();

    int create(Book book) throws IOException, SQLException;

    int update(Book book) throws IOException, SQLException;

    int delete(Long id) throws IOException, SQLException;
}
