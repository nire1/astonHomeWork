package com.example.newhomework.servlet;

import com.example.newhomework.dto.AuthorDto;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Licence;
import com.example.newhomework.service.impl.AuthorServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthorServletTest {

    @Mock
    AuthorServiceImpl authorService;
    @Mock
    HttpServletRequest request;
    @Mock
    Gson gson;
    @Mock
    HttpServletResponse response;
    @InjectMocks
    private AuthorServlet servlet;

    @BeforeEach
    void setup() throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    @SneakyThrows
    void getById() {
        when(request.getParameter("id")).thenReturn("1");
        long id = 1L;
        AuthorDto authorDto = new AuthorDto()
                .setName("ПУШКИН")
                .setLicence_number("na1231")
                .setCountOfBook(3);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(authorService.getById(id)).thenReturn(authorDto);
        servlet.doGet(request,response);

        String authorJsonString = stringWriter.getBuffer().toString().trim();
        AuthorDto fetchedAuthor = new Gson().fromJson(authorJsonString, AuthorDto.class);
        assertEquals(fetchedAuthor, authorDto);



    }

    @Test
    void create() throws IOException {


        when(request.getParameter("name")).thenReturn("Пушкин");
        when(request.getParameter("licence_id")).thenReturn("1");
        Author author = new Author();
        author.setName("Пушкин");
        author.setLicence(new Licence().setId(1));

        servlet.doPost(request, response);
        verify(authorService).create(author);
        Assertions.assertThat(request.getParameter("name")).isEqualTo("Пушкин");




    }

    @SneakyThrows
    @Test
    void update() {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("НовоеИмя");
        Author author = new Author()
                .setId(1)
                .setName("НовоеИмя");
        servlet.doPut(request, response);
        verify(authorService).update(author);


    }

    @Test
    @SneakyThrows
    void delete() {
        when(request.getParameter("id")).thenReturn("1");
        Long id = 1L;
        servlet.doDelete(request, response);
        verify(authorService).delete(id);
    }


}