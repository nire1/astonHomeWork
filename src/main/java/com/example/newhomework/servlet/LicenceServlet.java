package com.example.newhomework.servlet;

import com.example.newhomework.dao.LicenceDao;
import com.example.newhomework.entity.Licence;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/licence")
public class LicenceServlet extends HttpServlet {
    private LicenceDao licenceDao;
    private Gson gson;
    @Override
    public void init() throws ServletException {
        licenceDao = new LicenceDao();
        gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Licence> licenceList = licenceDao.getAll();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(gson.toJson(licenceList));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        Licence licence = new Licence()
                .setNumber(number);
        licenceDao.create(licence);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String number = req.getParameter("number");
        Licence licence = new Licence()
                .setId(id)
                .setNumber(number);
        licenceDao.update(licence);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String number = req.getParameter("number");
        licenceDao.delete(number);
    }


}
