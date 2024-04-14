package com.example.newhomework.service.impl;

import com.example.newhomework.dao.impl.AuthorDaoImpl;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Licence;
import com.example.newhomework.util.DataUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @Mock
    AuthorDaoImpl authorDao;

    @InjectMocks
    AuthorServiceImpl authorService;


    @Test
    @SneakyThrows
    @DisplayName("Test save author")
    public void saveAuthor(){
        Author author = new Author()
                .setId(1)
                .setName("Pushkin")
                .setLicence(new Licence().setId(6))
                .setBookList(new ArrayList<>());
        BDDMockito.given(authorDao.create(any(Author.class)));
        int returnedInt = authorService.create(author);
        Assertions.assertEquals(returnedInt,0);
    }

}