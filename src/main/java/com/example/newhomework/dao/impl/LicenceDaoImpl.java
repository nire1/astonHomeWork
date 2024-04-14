package com.example.newhomework.dao.impl;

import com.example.newhomework.config.ConnectionConfigImpl;
import com.example.newhomework.dao.LicenceDao;
import com.example.newhomework.entity.Licence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LicenceDaoImpl implements LicenceDao {
    private Connection connection;
    private ConnectionConfigImpl connectionConfig;

    public LicenceDaoImpl() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.connectionConfig = ConnectionConfigImpl.create();
        try {
            this.connection = connectionConfig.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int create(Licence licence) throws SQLException {
        int status;
        try
                (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO licences(number) VALUES (?)")){
            preparedStatement.setString(1, licence.getNumber());
            status = preparedStatement.executeUpdate();
        }
        return status;
    }

    @Override
    public List<Licence> getAll() {
        List<Licence> licenceList = new ArrayList<>();
        try
                (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM licences")){
            while (resultSet.next()) {
                Licence licence = new Licence()
                        .setId(resultSet.getInt("id"))
                        .setNumber(resultSet.getString("number"));
                licenceList.add(licence);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return licenceList;
    }

    @Override
    public int delete(String number) throws SQLException {
        int status;
        try
                (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM licences WHERE number=?")){
            preparedStatement.setString(1, number);
            status = preparedStatement.executeUpdate();
        }
        return status;
    }

    @Override
    public int update(Licence licence) throws SQLException {
        int status;
        try
                (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE licences SET number=? WHERE id=?")) {
            preparedStatement.setString(1, licence.getNumber());
            preparedStatement.setLong(2, licence.getId());
            status = preparedStatement.executeUpdate();
        }
        return status;
    }

    @Override
    public boolean existByNumber(String number) {

        boolean exist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT EXISTS (SELECT 1 FROM licences WHERE number = ?)")){
            preparedStatement.setString(1,number);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                exist = resultSet.getBoolean("exists");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exist;
    }

    @Override
    public boolean existById(long id) {

        boolean exist = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT EXISTS (SELECT 1 FROM licences WHERE id = ?)")){
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                exist = resultSet.getBoolean("exists");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exist;
    }
}
