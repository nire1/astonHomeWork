package com.example.newhomework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Author {
    private long id;
    private String name;
    private Licence licence;
    private List<Book> bookList;

}
