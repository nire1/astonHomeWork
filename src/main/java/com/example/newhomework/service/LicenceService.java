package com.example.newhomework.service;

import com.example.newhomework.entity.Licence;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface LicenceService {
    int create(Licence licence) throws IOException;
    List<Licence> getAll();
    int delete(String number) throws IOException;
    int update(Licence licence) throws IOException;

}
