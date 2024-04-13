package com.example.newhomework.servlet;


import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Licence;
import com.example.newhomework.service.impl.AuthorServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
    private Gson gson;
    private AuthorServiceImpl authorService;
    @Override
    public void init() {
        gson = new Gson();
        authorService = new AuthorServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(
                req.getParameter("id"));
       AuthorDto authorDto = authorService.getById(id);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(authorDto));


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String name = req.getParameter("name");
        long licenceId = Long.parseLong(
                req.getParameter("licence_id"));
        Author author = new Author();
        author.setName(name);
        author.setLicence(new Licence().setId(licenceId));
        PrintWriter out = resp.getWriter();
        if (authorService.create(author)>0){
            out.println("Запись успешна");
        } else {
            out.println("Запись не создалась");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(
                req.getParameter("id"));
        String name = req.getParameter("name");

        Author updatedAuthor = new Author()
                .setId(id)
                .setName(name);

        PrintWriter out = resp.getWriter();
        if (authorService.update(updatedAuthor) > 0) {
            out.println("Запись успешна");
        } else {
            out.println("Запись не создалась");
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(
                req.getParameter("id"));

        PrintWriter out = resp.getWriter();
        if (authorService.delete(id) > 0) {
            resp.setStatus(200);
            out.println("Запись успешна");

        } else {
            resp.setStatus(500);
            out.println("id не найден");
        }



    }


}
