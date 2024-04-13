package com.example.newhomework.servlet;

import com.example.newhomework.dto.BookDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;
import com.example.newhomework.service.impl.BookServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/books/*")
public class BookServlet extends HttpServlet {
    private Gson gson;
    private BookServiceImpl bookService;
    @Override
    public void init()  {

        bookService = new BookServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<BookDto> books = bookService.getAll();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(books));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        long author_id = Long.parseLong(req.getParameter("author_id"));

        Book book = new Book();
        Author author = new Author();
        author.setId(author_id);

        book.setName(name);
        book.setAuthor(author);
        PrintWriter out = resp.getWriter();
        if (bookService.create(book) > 0) {
            out.println("запись создалась");
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("запись не создалась");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        long authorId = Long.parseLong(req.getParameter("author_id"));
        Book book = new Book()
                .setId(id)
                .setName(name)
                .setAuthor(new Author()
                        .setId(authorId));

        PrintWriter out = resp.getWriter();
        if (bookService.update(book) > 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            out.println("запись обновлена");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("запись не обновлена");
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        PrintWriter out = resp.getWriter();
        if (bookService.delete(id) > 0) {
            resp.setStatus(HttpServletResponse.SC_OK);
            out.println("запись удалена");
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("запись не удалена");
        }
    }
}
