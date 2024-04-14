package com.example.newhomework.service.impl;

import com.example.newhomework.dao.impl.BookDaoImpl;
import com.example.newhomework.dto.BookDto;
import com.example.newhomework.entity.Book;
import com.example.newhomework.mapper.impl.BookToBookDtoMapperImpl;
import com.example.newhomework.service.BookService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


public class BookServiceImpl implements BookService {
    private final BookDaoImpl bookDao = new BookDaoImpl();
    private final BookToBookDtoMapperImpl mapper = new BookToBookDtoMapperImpl();

    @Override
    public List<BookDto> getAll() {
        List<Book> bookList = bookDao.getAll();
        return bookList
                .stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public int create(Book book) throws IOException, SQLException {
        return bookDao.create(book);
    }

    @Override
    public int update(Book book) throws IOException, SQLException {
        if (bookDao.existById(book.getId())) {
            return bookDao.update(book);
        } else throw new NoSuchElementException("Не найден");
    }

    @Override
    public int delete(Long id) throws IOException, SQLException {
        if (bookDao.existById(id)) {
            return bookDao.delete(id);
        } else throw new NoSuchElementException("Не найден");
    }


}
