package com.example.newhomework.util;

import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;
import com.example.newhomework.entity.Licence;

import java.util.ArrayList;
import java.util.List;


public class DataUtils {
    public static Author getPushkinPersisted(){
        List<Book>bookList = new ArrayList<>();
        Book book = new Book()
                .setId(1L)
                .setName("Книга");
        Book book2 = new Book()
                .setId(2L)
                .setName("Книга2");
        bookList.add(book);
        bookList.add(book2);
        Licence licence = new Licence()
                .setId(1).setNumber("111");

        return new Author()
                .setId(1)
                .setName("Pushkin")
                .setLicence(licence)
                .setBookList(bookList);
    }
}
