package com.example.newhomework.servlet;


import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.service.impl.AuthorServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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

        authorService.create(req,resp);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        authorService.update(req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        authorService.delete(req, resp);

    }


}
