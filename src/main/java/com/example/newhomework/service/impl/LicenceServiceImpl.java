package com.example.newhomework.service.impl;


import com.example.newhomework.dao.impl.LicenceDaoImpl;
import com.example.newhomework.entity.Licence;
import com.example.newhomework.service.LicenceService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;


public class LicenceServiceImpl implements LicenceService {
    private final LicenceDaoImpl licenceDao = new LicenceDaoImpl();

    @Override
    public int create(Licence licence) throws IOException, SQLException {
        return licenceDao.create(licence);
    }

    @Override
    public List<Licence> getAll() {
        return licenceDao.getAll();
    }

    @Override
    public int delete(String number) throws IOException, SQLException {
        if (licenceDao.existByNumber(number)) {
            return licenceDao.delete(number);
        } else throw new NoSuchElementException("не найден");
    }

    @Override
    public int update(Licence licence) throws IOException, SQLException {

        if (licenceDao.existById(licence.getId())) {
            return licenceDao.update(licence);
        } else throw new NoSuchElementException("Не найден");

    }
}
