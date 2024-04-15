package com.example.newhomework.servlet;

import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Licence;
import com.example.newhomework.service.impl.AuthorServiceImpl;
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


import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.NoSuchElementException;


import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthorServletTest {

    @Mock
    AuthorServiceImpl authorService;
    @Mock
    HttpServletRequest request;
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
    void createAuthorAndExpectOk() {
        when(request.getParameter("name")).thenReturn("Пушкин");
        when(request.getParameter("licence_id")).thenReturn("1");
        Author author = new Author();
        author.setName("Пушкин");
        author.setLicence(new Licence().setId(1));

        servlet.doPost(request, response);
        verify(authorService).create(author);
        verify(response).setStatus(200);
        Assertions.assertThat(request.getParameter("name")).isEqualTo("Пушкин");

    }

    @Test
    @SneakyThrows
    void createAuthorAndExpectSQLException() {
        when(request.getParameter("name")).thenReturn("Пушкин");
        when(request.getParameter("licence_id")).thenReturn("1");
        Author author = new Author();
        author.setName("Пушкин");
        author.setLicence(new Licence().setId(1));

        doThrow(new SQLException()).when(authorService).create(author);

        servlet.doPost(request, response);
        verify(authorService).create(author);
        verify(response).setStatus(500);

    }

    @SneakyThrows
    @Test
    void updateAuthorAndExpectOk() {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("НовоеИмя");
        Author author = new Author()
                .setId(1)
                .setName("НовоеИмя");
        servlet.doPut(request, response);
        verify(authorService).update(author);
        verify(response).setStatus(200);

    }

    @SneakyThrows
    @Test
    void updateAuthorAndExpectSQLException() {
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("НовоеИмя");
        Author author = new Author()
                .setId(1)
                .setName("НовоеИмя");
        doThrow(new NoSuchElementException()).when(authorService).update(author);
        servlet.doPut(request, response);
        verify(authorService).update(author);


    }

    @Test
    @SneakyThrows
    void deleteAuthorAndExpectOk() {
        when(request.getParameter("id")).thenReturn("1");
        Long id = 1L;
        servlet.doDelete(request, response);
        verify(authorService).delete(id);
        verify(response).setStatus(200);
    }
    @Test
    @SneakyThrows
    void deleteAuthorAndExpectNoSuchElementException() {
        when(request.getParameter("id")).thenReturn("1");
        Long id = 1L;

        doThrow(new NoSuchElementException()).when(authorService).delete(id);

        servlet.doDelete(request, response);
        verify(authorService).delete(id);
        verify(response).setStatus(500);
    }


}