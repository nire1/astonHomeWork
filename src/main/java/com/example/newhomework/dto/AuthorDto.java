package com.example.newhomework.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthorDto {
    private String name;
    private String licence_number;
    private int countOfBook;
}
