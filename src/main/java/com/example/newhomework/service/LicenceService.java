package com.example.newhomework.service;

import com.example.newhomework.entity.Licence;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface LicenceService {
    int create(Licence licence) throws IOException, SQLException;
    List<Licence> getAll();
    int delete(String number) throws IOException, SQLException;
    int update(Licence licence) throws IOException, SQLException;

}
