package siekny.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
    Connection connection;

    public Statement openConnection () {
        Statement statement = null;
        try {
            // 1- load driver
            Class.forName("org.postgresql.Driver");

            // 2- create connection
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/short_course_article_db",
                    "postgres",
                    "123");

            // 3- create statement
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public void closeConnection () {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
