package com.example.newhomework.mapper.impl;
import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.mapper.AuthorToAuthorDtoMapper;



public class AuthorToAuthorDtoMapperImpl implements AuthorToAuthorDtoMapper {
    @Override
    public AuthorDto map(Author author) {

        return new AuthorDto()
                .setName(author.getName())
                .setLicence_number(author.getLicence().getNumber())
                .setCountOfBook(author.getBookList().size());

    }
}
