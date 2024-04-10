package com.example.newhomework.mapper.impl;

import com.example.newhomework.dto.BookDto;
import com.example.newhomework.entity.Book;
import com.example.newhomework.mapper.BookToBookDtoMapper;

public class BookToBookDtoMapperImpl implements BookToBookDtoMapper {
    @Override
    public BookDto map(Book book) {
        return new BookDto()
                .setName(book.getName())
                .setAuthor(book.getAuthor().getName());
    }
}
