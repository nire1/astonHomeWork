package com.example.newhomework.service.impl;

import com.example.newhomework.dao.impl.BookDaoImpl;
import com.example.newhomework.dto.BookDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;
import com.example.newhomework.entity.Licence;
import com.example.newhomework.mapper.impl.BookToBookDtoMapperImpl;
import com.example.newhomework.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
    public int create(Book book) throws IOException {
        return bookDao.create(book);
//        String name = req.getParameter("name");
//        long author_id = Long.parseLong(req.getParameter("author_id"));
//
//        Book book = new Book();
//        Author author = new Author();
//        author.setId(author_id);
//
//        book.setName(name);
//        book.setAuthor(author);
//        PrintWriter out = resp.getWriter();
//        if (bookDao.create(book) > 0) {
//            out.println("запись создалась");
//            resp.setStatus(HttpServletResponse.SC_CREATED);
//        } else {
//            out.println("запись не создалась");
//        }

    }

    @Override
    public int update(Book book) throws IOException {
        return bookDao.update(book);
//        Long id = Long.parseLong(req.getParameter("id"));
//        String name = req.getParameter("name");
//        long authorId = Long.parseLong(req.getParameter("author_id"));
//        Book book = new Book()
//                .setId(id)
//                .setName(name);
//        PrintWriter out = resp.getWriter();
//        if (bookDao.update(book, authorId) > 0) {
//            out.println("запись обновлена");
//        } else {
//            out.println("запись не обновлена");
//        }
    }

    @Override
    public int delete(Long id) throws IOException {
        return bookDao.delete(id);
//        long id = Long.parseLong(req.getParameter("id"));
//        PrintWriter out = resp.getWriter();
//        if (bookDao.delete(id) > 0) {
//            out.println("запись удалена");
//            resp.setStatus(HttpServletResponse.SC_OK);
//        } else {
//            out.println("запись не удалена");
//        }
    }


}
