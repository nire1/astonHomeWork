package com.example.newhomework.dao;

import com.example.newhomework.entity.Licence;
import org.postgresql.ds.PGPooledConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LicenceDao {
    private Connection connection;

    public LicenceDao() {
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

    public void create(Licence licence) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO licences(number) VALUES (?)");
            preparedStatement.setString(1, licence.getNumber());
            int i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

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

    public void delete(String number){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM licences WHERE number=?");
            preparedStatement.setString(1,number);
            preparedStatement.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
    public void update(Licence licence){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE licences SET number=? WHERE id=?");
            preparedStatement.setString(1, licence.getNumber());
            preparedStatement.setLong(2,licence.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
