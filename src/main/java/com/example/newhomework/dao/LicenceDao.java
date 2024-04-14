package com.example.newhomework.dao;

import com.example.newhomework.entity.Licence;

import java.sql.SQLException;
import java.util.List;

public interface LicenceDao {
    int create(Licence licence) throws SQLException;
    List<Licence> getAll();
    int delete(String number) throws SQLException;
    int update(Licence licence) throws SQLException;
    boolean existByNumber(String number);
    boolean existById(long id);
}
