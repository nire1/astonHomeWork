package com.example.newhomework.dao;

import com.example.newhomework.entity.Licence;

import java.util.List;

public interface LicenceDao {
    int create(Licence licence);
    List<Licence> getAll();
    int delete(String number);
    int update(Licence licence);

}
