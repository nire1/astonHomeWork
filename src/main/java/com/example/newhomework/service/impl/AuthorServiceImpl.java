package com.example.newhomework.service.impl;

import com.example.newhomework.dao.impl.AuthorDaoImpl;
import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.mapper.impl.AuthorToAuthorDtoMapperImpl;
import com.example.newhomework.service.AuthorService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDaoImpl authorDao = new AuthorDaoImpl();
    private final AuthorToAuthorDtoMapperImpl mapper = new AuthorToAuthorDtoMapperImpl();

    public AuthorServiceImpl() throws SQLException {
    }

    @Override
    public void create(Author author) throws IOException, SQLException {
         authorDao.create(author);
    }

    @Override
    public AuthorDto getById(long id) {

        if (authorDao.existById(id)) {
            Author author = authorDao.getById(id);
            return mapper.map(author);
        } else throw new NoSuchElementException("Не найден");
    }

    @Override
    public int delete(Long id) throws IOException, SQLException {
        if (authorDao.existById(id)) {
            return authorDao.delete(id);
        } else throw new NoSuchElementException("Не найден");
    }

    @Override
    public int update(Author author) throws IOException, SQLException {

        if (authorDao.existById(author.getId())) {
            return authorDao.update(author);
        } else throw new NoSuchElementException("Не найден");

    }
}
