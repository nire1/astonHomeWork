package com.example.newhomework.dao.impl;

import com.example.newhomework.dao.BookDao;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private Connection connection;

    public BookDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/books_shop",
                    "postgres",
                    "postgres");
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int create(Book book) {
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO books (name, author_id) VALUES (?,?)");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setLong(2, book.getAuthor().getId());
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT books.id,books.name,books.author_id, authors.name as authors_name \n" +
                            "FROM books \n" +
                            "    LEFT JOIN public.authors  on books.author_id = authors.id");
            while (resultSet.next()) {
                Book book = new Book();
                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("authors_name"));
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(author);

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int delete(long id) {
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM books WHERE id=?");
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int update(Book book, long author_id) {
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE books SET name=?,author_id=? WHERE id=?");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setLong(2, author_id);
            preparedStatement.setLong(3, book.getId());
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

}
