package siekny.models.dao;

import siekny.models.dto.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    ConnectionDB connectionDB = new ConnectionDB();

    /**
     * Get all Authors
     * @return
     */
    public List<Author> getAllUsers() {
        Statement statement = connectionDB.openConnection();
        List<Author> users = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tb_user");
            while (resultSet.next()) {
                Author user = new Author();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setGender(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4).toLocalDate());

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
        return users;
    }

    public void insertAuthor (Author author) {
        Statement statement = connectionDB.openConnection();
        try {
            statement.executeUpdate(
                    "INSERT INTO tb_user (name, gender, birthDate) " +
                            "VALUES ('" + author.getName() + "', '" + author.getGender() + "', '" + author.getDateOfBirth() +"')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
    }

    public void deleteAuthor (Author author) {
        Statement statement = connectionDB.openConnection();
        try {
            statement.executeUpdate(
                    "DELETE FROM tb_user WHERE id = " + author.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
    }

    public void updateAuthor (Author author) {
        Statement statement = connectionDB.openConnection();
        try {
            statement.executeUpdate(" UPDATE tb_user SET name = '" + author.getName() + "', gender='" + author.getGender() +"', date_birth='" +author.getDateOfBirth() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
    }
}
