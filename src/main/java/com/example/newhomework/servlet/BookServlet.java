package com.example.newhomework.servlet;

import com.example.newhomework.dto.BookDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;
import com.example.newhomework.service.impl.BookServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@WebServlet(urlPatterns = "/books/*")
public class BookServlet extends HttpServlet {
    private Gson gson;
    private BookServiceImpl bookService;

    @Override
    public void init() {

        bookService = new BookServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            List<BookDto> books = bookService.getAll();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(200);
            resp.getWriter().write(gson.toJson(books));
        } catch (NoSuchElementException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("Не найден");
        }
    }

        @Override
        protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws IOException {
            String name = req.getParameter("name");
            long author_id = Long.parseLong(req.getParameter("author_id"));

            Book book = new Book();
            Author author = new Author();
            author.setId(author_id);

            book.setName(name);
            book.setAuthor(author);
            PrintWriter out = resp.getWriter();
            try {
                bookService.create(book);
                resp.setStatus(HttpServletResponse.SC_OK);
                out.println("запись создалась");
            } catch (SQLException e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.println(e.getMessage());
            }

        }

        @Override
        protected void doPut (HttpServletRequest req, HttpServletResponse resp) throws IOException {
            Long id = Long.parseLong(req.getParameter("id"));
            String name = req.getParameter("name");
            long authorId = Long.parseLong(req.getParameter("author_id"));
            Book book = new Book()
                    .setId(id)
                    .setName(name)
                    .setAuthor(new Author()
                            .setId(authorId));

            PrintWriter out = resp.getWriter();
            try {
                bookService.update(book);
                resp.setStatus(HttpServletResponse.SC_OK);
                out.println("запись обновлена");
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.println(e.getMessage());
            }
        }

        @Override
        protected void doDelete (HttpServletRequest req, HttpServletResponse resp) throws IOException {
            long id = Long.parseLong(req.getParameter("id"));
            PrintWriter out = resp.getWriter();
            try {
                bookService.delete(id);
                resp.setStatus(HttpServletResponse.SC_OK);
                out.println("запись удалена");
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.println(e.getMessage());
            }
        }
    }
