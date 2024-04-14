package com.example.newhomework.util;

import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Licence;

import java.util.ArrayList;

public class DataUtils {
    public static Author getPushkinPersisted(){
        return new Author()
                .setId(1)
                .setName("Pushkin")
                .setLicence(new Licence())
                .setBookList(new ArrayList<>());
    }
}
