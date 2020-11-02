package repos;

import manager.DBConnector;
import models.Book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public void create(Book book) {
        if (book != null) {
            Connection connection = null;
            try {
                connection = DBConnector.getConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String sql = "insert into books(name, author) values(?,?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.executeUpdate();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Book> findByName(String name) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        ResultSet rs = null;
        if (name != null) {
            Connection connection = null;
            try {
                connection = DBConnector.getConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String sql = "select * from books where name = ?";
            PreparedStatement preparedStatement = null;
            try{
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, name);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Book book = new Book();
                    book.setName(rs.getString("name"));
                    book.setAuthor(rs.getString("author"));
                    books.add(book);
                }
                connection.close();

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return books;
    }
}
