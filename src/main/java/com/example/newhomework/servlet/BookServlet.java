package com.example.newhomework.servlet;

import com.example.newhomework.HelloServlet;
import com.example.newhomework.dao.BookDao;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/books/*")
public class BookServlet extends HttpServlet {
    private BookDao bookDao;
    private Gson gson;
    @Override
    public void init() throws ServletException {

        bookDao = new BookDao();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = bookDao.getAll();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(books));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        Long author_id = Long.parseLong(req.getParameter("author_id"));

        Book book = new Book();
        Author author = new Author();
        author.setId(author_id);

        book.setName(name);
        book.setAuthor(author);
        bookDao.create(book);
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        long authorId = Long.parseLong(req.getParameter("author_id"));
        Book book = new Book()
                .setId(id)
                .setName(name);

        bookDao.update(book,authorId);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        bookDao.delete(id);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
