package com.example.newhomework.service.impl;


import com.example.newhomework.dao.impl.LicenceDaoImpl;
import com.example.newhomework.entity.Licence;
import com.example.newhomework.service.LicenceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class LicenceServiceImpl implements LicenceService {
    private final LicenceDaoImpl licenceDao = new LicenceDaoImpl();

    @Override
    public int create(Licence licence) throws IOException {
        return licenceDao.create(licence);
//        String number = req.getParameter("number");
//        Licence licence = new Licence()
//                .setNumber(number);
//        PrintWriter out = resp.getWriter();
//        if (licenceDao.create(licence) > 0) {
//            out.println("запись успешна");
//        } else {
//            out.println("запись не создалась");
//        }
    }

    @Override
    public List<Licence> getAll() {
        return licenceDao.getAll();
    }

    @Override
    public int delete(String number) throws IOException {
        return licenceDao.delete(number);
//        String number = req.getParameter("number");
//        PrintWriter out = resp.getWriter();
//        if (licenceDao.delete(number) > 0) {
//            out.println("запись удалена");
//        } else {
//            out.println("запись не удалена");
//        }
    }

    @Override
    public int update(Licence licence) throws IOException {
        return licenceDao.update(licence);
//        long id = Long.parseLong(req.getParameter("id"));
//        String number = req.getParameter("number");
//        Licence licence = new Licence()
//                .setId(id)
//                .setNumber(number);
//        PrintWriter out = resp.getWriter();
//        if (licenceDao.update(licence) > 0) {
//            out.println("запись удалена");
//        } else {
//            out.println("запись не удалена");
//        }
    }
}
