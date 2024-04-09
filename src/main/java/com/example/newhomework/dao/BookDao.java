package com.example.newhomework.dao;

import com.example.newhomework.entity.Author;
import com.example.newhomework.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private Connection connection;
    public BookDao(){
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
    public void create(Book book){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books (name, author_id) VALUES (?,?)");
            preparedStatement.setString(1,book.getName());
            preparedStatement.setLong(2,book.getAuthor().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public List<Book> getAll(){
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT books.id,books.name,books.author_id, authors.name as authors_name FROM books LEFT JOIN public.authors  on books.author_id = authors.id");
            while (resultSet.next()){
                Book book = new Book();
                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("authors_name"));
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(author);

                books.add(book);
            }
        }
        catch (SQLException e ){
            e.printStackTrace();
        }
        return books;
    }
    public void delete(long id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE id=?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void update(Book book,long author_id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books SET name=?,author_id=? WHERE id=?");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setLong(2,author_id);
            preparedStatement.setLong(3,book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
