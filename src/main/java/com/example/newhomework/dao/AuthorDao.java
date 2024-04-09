package com.example.newhomework.dao;

import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Licence;

import java.sql.*;

public class AuthorDao {
    private Connection connection;

    public AuthorDao() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/books_shop",
                    "postgres",
                    "131270");
        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(Author author) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO authors(name, licence_id) VALUES (?,?)");
            preparedStatement.setString(1, author.getName());
            preparedStatement.setLong(2, author.getLicence().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Author getById(long id) {
        Author author = new Author();
        Licence licence = new Licence();
        try
                (PreparedStatement preparedStatement = connection.prepareStatement("SELECT authors.id,authors.name,authors.licence_id,licences.id as l_id,licences.number as l_number\n" +
                        "FROM authors\n" +
                        "LEFT JOIN licences on authors.licence_id = licences.id\n" +
                        "         WHERE authors.id=?")) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    author.setId(resultSet.getLong("id"));
                    author.setName(resultSet.getString("name"));
                    licence.setId(resultSet.getLong("l_id"))
                            .setNumber(resultSet.getString("l_number"));
                    author.setLicence(licence);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
    public void delete(long id){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM authors WHERE id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
    public void update(Author author){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE authors SET name=?,licence_id=? WHERE id=?");
            preparedStatement.setString(1, author.getName());
            preparedStatement.setLong(2,author.getLicence().getId());
            preparedStatement.setLong(3,author.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
