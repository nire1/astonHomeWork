package com.example.newhomework.servlet;

import com.example.newhomework.entity.Licence;
import com.example.newhomework.service.impl.LicenceServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/licence")
public class LicenceServlet extends HttpServlet {
    private LicenceServiceImpl licenceService;
    private Gson gson;

    @Override
    public void init() {
        licenceService = new LicenceServiceImpl();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Licence> licenceList = licenceService.getAll();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(licenceList));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String number = req.getParameter("number");
        Licence licence = new Licence()
                .setNumber(number);
        PrintWriter out = resp.getWriter();
        try {
            licenceService.create(licence);
            out.println("запись успешна");
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String number = req.getParameter("number");
        Licence licence = new Licence()
                .setId(id)
                .setNumber(number);
        PrintWriter out = resp.getWriter();
        try {
            licenceService.update(licence);
            out.println("запись обовлена");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String number = req.getParameter("number");
        PrintWriter out = resp.getWriter();
        try {
            licenceService.delete(number);
            out.println("запись удалена");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }


}
