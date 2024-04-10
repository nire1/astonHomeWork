package com.example.newhomework.dao.impl;

import com.example.newhomework.dao.AuthorDao;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;
import com.example.newhomework.entity.Licence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    private Connection connection;

    public AuthorDaoImpl() {
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
    public int create(Author author) {
        int status = 0;
        try
                (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO authors(name, licence_id) VALUES (?,?)")) {
            preparedStatement.setString(1, author.getName());
            preparedStatement.setLong(2, author.getLicence().getId());
            status = preparedStatement.executeUpdate();

        } catch (SQLException e) {
             e.printStackTrace();
        }
        return status;
    }

    @Override
    public Author getById(long id) {
        Author author = new Author();
        Licence licence = new Licence();
        List<Book> bookList = new ArrayList<>();
        try
                (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT authors.id, authors.name, authors.licence_id, licences.id as l_id, licences.number as l_number,books.id as b_id,books.name as b_name\n" +
                                "FROM authors\n" +
                                "         INNER JOIN  licences on authors.licence_id = licences.id\n" +
                                "        INNER JOIN books on authors.id = books.author_id\n" +
                                "WHERE authors.id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    author.setId(resultSet.getLong("id"));
                    author.setName(resultSet.getString("name"));
                    licence.setId(resultSet.getLong("l_id"))
                            .setNumber(resultSet.getString("l_number"));
                    author.setLicence(licence);
                    while (resultSet.next()) {
                        bookList.add(parseBookFromResultSet(resultSet));
                    }
                    author.setBookList(bookList);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    private Book parseBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setId(rs.getLong("b_id"));
        book.setName(rs.getString("b_name"));
        return book;
    }

    @Override
    public int delete(long id) {
        int status = 0;
        try
                (PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM authors WHERE id=?")) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int update(Author author) {
        int status = 0;
        try
                (PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE authors SET name=? WHERE id=?")) {
            preparedStatement.setString(1, author.getName());
//            preparedStatement.setLong(2,author.getLicence().getId());
            preparedStatement.setLong(2, author.getId());
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
}
