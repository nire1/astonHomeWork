package com.example.newhomework.servlet;

import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;
import com.example.newhomework.service.impl.BookServiceImpl;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
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

class BookServletTest {
    @Mock
    private BookServiceImpl bookService;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @InjectMocks
    private BookServlet servlet;
    @BeforeEach
    void setUp() throws IOException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    @SneakyThrows
    void createBookAndExpectOk(){
        when(request.getParameter("name")).thenReturn("Книга");
        when(request.getParameter("author_id")).thenReturn("1");
        Book book = new Book()
                .setName("Книга")
                .setAuthor(new Author().setId(1));
     servlet.doPost(request,response);
     verify(bookService).create(book);
     verify(response).setStatus(HttpServletResponse.SC_OK);

    }
    @Test
    @SneakyThrows
    void createBookAndExpectSQLException(){
        when(request.getParameter("name")).thenReturn("Книга");
        when(request.getParameter("author_id")).thenReturn("1");
        Book book = new Book()
                .setName("Книга")
                .setAuthor(new Author().setId(1));
        doThrow(new SQLException()).when(bookService).create(book);
        servlet.doPost(request,response);
        verify(bookService).create(book);
        verify(response).setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

    }

    @Test
    @SneakyThrows
    void updateBookAndExpectOk(){
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("НовоеИмя");
        when(request.getParameter("author_id")).thenReturn("2");
        Book book = new Book()
                .setId(1L)
                .setName("НовоеИмя")
                .setAuthor(new Author()
                        .setId(2L));
        servlet.doPut(request,response);
        verify(bookService).update(book);
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
    @Test
    @SneakyThrows
    void updateBookAndExpectNoSuchElementException(){
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("name")).thenReturn("НовоеИмя");
        when(request.getParameter("author_id")).thenReturn("2");
        Book book = new Book()
                .setId(1L)
                .setName("НовоеИмя")
                .setAuthor(new Author()
                        .setId(2L));
        doThrow(new NoSuchElementException("Не найден")).when(bookService).update(book);

        servlet.doPut(request,response);
        verify(bookService).update(book);
        verify(response).setStatus(500);
    }

    @Test
    @SneakyThrows
    void deleteBookAndExpectOk(){
        when(request.getParameter("id")).thenReturn("1");
        Long id = 1L;
        servlet.doDelete(request,response);
        verify(bookService).delete(id);
        verify(response).setStatus(HttpServletResponse.SC_OK);
    }
    @Test
    @SneakyThrows

    void deleteBookAndExpectNoSuchElementException(){
        when(request.getParameter("id")).thenReturn("1");
        Long id = 1L;
        doThrow(new NoSuchElementException("Не найден")).when(bookService).delete(id);
        servlet.doDelete(request,response);
        verify(bookService).delete(id);
        verify(response).setStatus(500);
    }

}