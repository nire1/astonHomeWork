package com.example.newhomework.dao.impl;

import com.example.newhomework.config.ConnectionConfigImpl;
import com.example.newhomework.dao.AuthorDao;
import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;
import com.example.newhomework.entity.Licence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AuthorDaoImpl implements AuthorDao {
    private Connection connection;
    private ConnectionConfigImpl connectionConfig;

    public AuthorDaoImpl() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.connectionConfig = ConnectionConfigImpl.create();
        this.connection = connectionConfig.getConnection();

    }

    @Override
    public void create(Author author) throws SQLException {

        try
                (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO authors(name, licence_id) VALUES (?,?)")) {
            preparedStatement.setString(1, author.getName());
            preparedStatement.setLong(2, author.getLicence().getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Author getById(long id) {
        Author author = new Author();
        Licence licence = new Licence();
        List<Book> bookList;
        try
                (
                        PreparedStatement preparedStatement = connection.prepareStatement(
                                "SELECT authors.id, authors.name, authors.licence_id, licences.id as l_id, licences.number as l_number\n" +
                                        "FROM authors\n" +
                                        "          JOIN licences on authors.licence_id = licences.id\n" +
                                        "WHERE authors.id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.wasNull()) {
                    throw new NoSuchElementException("Не найден");
                }
                if (resultSet.next()) {
                    author.setId(resultSet.getLong("id"));
                    author.setName(resultSet.getString("name"));
                    licence.setId(resultSet.getLong("l_id"))
                            .setNumber(resultSet.getString("l_number"));
                    author.setLicence(licence);
                    bookList = getBookByAuthor(id);
                    author.setBookList(bookList);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return author;
    }

    public List<Book> getBookByAuthor(long id) {
        List<Book> bookList = new ArrayList<>();
        Book book = new Book();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books b WHERE b.author_id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookList;
    }


    @Override
    public int delete(long id) throws SQLException {
        int status;
        try
                (PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM authors WHERE id=?")) {
            preparedStatement.setLong(1, id);
            status = preparedStatement.executeUpdate();
        }
        return status;
    }

    @Override
    public int update(Author author) throws SQLException {
        int status ;
        try
                (PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE authors SET name=? WHERE id=?")) {
            preparedStatement.setString(1, author.getName());
//            preparedStatement.setLong(2,author.getLicence().getId());
            preparedStatement.setLong(2, author.getId());
            status = preparedStatement.executeUpdate();
        }
        return status;
    }

    public boolean existById(Long id) {
        boolean exist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT EXISTS (SELECT 1 FROM authors WHERE id = ?)")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exist = resultSet.getBoolean("exists");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exist;
    }
}
