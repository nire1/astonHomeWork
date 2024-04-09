package com.example.newhomework.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Book {
    private Long id;
    private String name;
    private Author author;
}
