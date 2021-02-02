package siekny.models.dao;

import siekny.models.dto.Author;
import siekny.models.dto.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    ConnectionDB connectionDB = new ConnectionDB();

    /**
     * Get all Categories
     * @return
     */
    public List<Category> getAllCategories() {
        Statement statement = connectionDB.openConnection();
        List<Category> categories = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM tb_category");
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
        return categories;
    }

    public void insertCategory (Category category) {
        Statement statement = connectionDB.openConnection();
        try {
            statement.executeUpdate(
                    "INSERT INTO tb_category (name) " +
                            "VALUES ('" + category.getName() +"')");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
    }

    public void deleteCategory (Category category) {
        Statement statement = connectionDB.openConnection();
        try {
            statement.executeUpdate(
                    "DELETE FROM tb_category WHERE id = " + category.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
    }

    public void updateCategory (Category category) {
        Statement statement = connectionDB.openConnection();
        try {
            statement.executeUpdate(" UPDATE tb_category SET name = '" + category.getName() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
    }
}
