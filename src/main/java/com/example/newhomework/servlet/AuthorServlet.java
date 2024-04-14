package com.example.newhomework.servlet;


import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Licence;
import com.example.newhomework.service.impl.AuthorServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.NoSuchElementException;

@WebServlet("/author")
public class AuthorServlet extends HttpServlet {
    private Gson gson;
    private AuthorServiceImpl authorService;
    @SneakyThrows
    @Override
    public void init() {
        gson = new Gson();
        authorService = new AuthorServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(
                req.getParameter("id"));
       try {
           AuthorDto authorDto = authorService.getById(id);
           resp.setContentType("application/json");
           resp.setCharacterEncoding("UTF-8");
           resp.getWriter().write(gson.toJson(authorDto));
       }catch (NoSuchElementException e){
           resp.getWriter().println("Не найден");
       }



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

            try {
                authorService.create(author);
                out.println("Запись успешна");
            } catch (SQLException e) {
                out.println(e.getMessage());
            }


//        if (authorService.create(author)>0){
//            out.println("Запись успешна");
//        } else {
//            out.println("Запись не создалась");
//        }

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
        try {
            authorService.update(updatedAuthor);
            out.println("Запись успешна");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
//        if (authorService.update(updatedAuthor) > 0) {
//            out.println("Запись успешна");
//        } else {
//            out.println("Запись не создалась");
//        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(
                req.getParameter("id"));

        PrintWriter out = resp.getWriter();
        try {
            authorService.delete(id);
            out.println("Запись успешна");
//            if (authorService.delete(id) > 0) {
//                resp.setStatus(200);
//                out.println("Запись успешна");
//
//            } else {
//                resp.setStatus(500);
//                out.println("id не найден");
//            }
        } catch (Exception e) {
            out.println(e.getMessage());

        }


    }


}
