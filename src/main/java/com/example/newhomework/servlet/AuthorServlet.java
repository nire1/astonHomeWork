package com.example.newhomework.servlet;

import com.example.newhomework.dao.AuthorDao;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Licence;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
    private AuthorDao authorDao;
    private Gson gson;
    @Override
    public void init() throws ServletException {
        authorDao = new AuthorDao();
        gson = new Gson();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(
                req.getParameter("id"));
        Author author = authorDao.getById(id);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(author));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        long licenceId = Long.parseLong(
                req.getParameter("licence_id"));
        Author author = new Author();
        author.setName(name);
        author.setLicence(new Licence().setId(licenceId));
        authorDao.create(author);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(
                req.getParameter("id"));
        String name = req.getParameter("name");
        long licence_id = Long.parseLong(
                req.getParameter("licence_id"));
        Author updatedAuthor = new Author()
                .setId(id)
                .setName(name)
                .setLicence(new Licence()
                        .setId(licence_id));
        authorDao.update(updatedAuthor);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(
                req.getParameter("id"));
        authorDao.delete(id);
        resp.setStatus(HttpServletResponse.SC_OK);
    }


}
