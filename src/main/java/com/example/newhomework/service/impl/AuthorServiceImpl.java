package com.example.newhomework.service.impl;

import com.example.newhomework.dao.impl.AuthorDaoImpl;
import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.mapper.impl.AuthorToAuthorDtoMapperImpl;
import com.example.newhomework.service.AuthorService;

import java.io.IOException;

public class AuthorServiceImpl implements AuthorService {
    private final AuthorDaoImpl authorDao = new AuthorDaoImpl();
    private final AuthorToAuthorDtoMapperImpl mapper = new AuthorToAuthorDtoMapperImpl();

    @Override
    public int create(Author author) throws IOException {
        return authorDao.create(author);
//        String name = req.getParameter("name");
//        long licenceId = Long.parseLong(
//                req.getParameter("licence_id"));
//        Author author = new Author();
//        author.setName(name);
//        author.setLicence(new Licence().setId(licenceId));
//        PrintWriter out = resp.getWriter();
//        if (authorDao.create(author) > 0) {
//            out.println("Запись успешна");
//        } else {
//            out.println("Запись не создалась");
//        }

    }

    @Override
    public AuthorDto getById(long id) {

        if (authorDao.existById(id)) {
            Author author = authorDao.getById(id);
            return mapper.map(author);
        }
        else return new AuthorDto();
    }

    @Override
    public int delete(Long id) throws IOException {
        return authorDao.delete(id);
//        long id = Long.parseLong(
//                req.getParameter("id"));
//
//        PrintWriter out = resp.getWriter();
//        if (authorDao.delete(id) > 0) {
//            out.println("Запись успешна");
//        } else {
//            out.println("Запись не создалась");
//        }

    }

    @Override
    public int update(Author author) throws IOException {
        return authorDao.update(author);
//        long id = Long.parseLong(
//                req.getParameter("id"));
//        String name = req.getParameter("name");
//
//        Author updatedAuthor = new Author()
//                .setId(id)
//                .setName(name);
//
//        PrintWriter out = resp.getWriter();
//        if (authorDao.update(updatedAuthor) > 0) {
//            out.println("Запись успешна");
//        } else {
//            out.println("Запись не создалась");
//        }

    }
}
