package com.example.newhomework.service;

import com.example.newhomework.entity.Licence;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface LicenceService {
    void create(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    List<Licence> getAll();
    void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException;
    void update(HttpServletRequest req, HttpServletResponse resp) throws IOException;

}
