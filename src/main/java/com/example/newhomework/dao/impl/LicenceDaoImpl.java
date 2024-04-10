package com.example.newhomework.dao.impl;

import com.example.newhomework.dao.LicenceDao;
import com.example.newhomework.entity.Licence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LicenceDaoImpl implements LicenceDao {
    private Connection connection;

    public LicenceDaoImpl() {
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
    public int create(Licence licence) {
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO licences(number) VALUES (?)");
            preparedStatement.setString(1, licence.getNumber());
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Licence> getAll() {
        List<Licence> licenceList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM licences");
            while (resultSet.next()) {
                Licence licence = new Licence()
                        .setId(resultSet.getInt("id"))
                        .setNumber(resultSet.getString("number"));
                licenceList.add(licence);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return licenceList;
    }

    @Override
    public int delete(String number) {
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM licences WHERE number=?");
            preparedStatement.setString(1, number);
            status = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public int update(Licence licence) {
        int status = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE licences SET number=? WHERE id=?");
            preparedStatement.setString(1, licence.getNumber());
            preparedStatement.setLong(2, licence.getId());
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return status;
    }
}
