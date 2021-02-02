package siekny.models.dao;

import siekny.models.dto.Article;
import siekny.models.dto.Author;
import siekny.models.dto.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao {
    private ConnectionDB connectionDB = new ConnectionDB();

    /**
     * Get all articles
     * @return
     */
    public List<Article> getAllAllArticles () {
        Statement statement = connectionDB.openConnection();
        List<Article> articles = new ArrayList<>();
        try {
            String sql = "SELECT article.id, article.title, article.description, article.user_id, u.name, category.name" +
                    "     FROM tb_article AS article " +
                    "     LEFT JOIN tb_user AS u " +
                    "           ON article.user_id = u.id " +
                    "     LEFT JOIN tb_category AS category " +
                    "           ON article.category_id = category.id";

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt(1));
                article.setTitle(resultSet.getString(2));
                article.setDescription(resultSet.getString(3));

                Author author = new Author();
                author.setId(resultSet.getInt(4));
                author.setName(resultSet.getString(5));

                Category category = new Category();
                category.setName(resultSet.getString(6));

                article.setCategory(category);
                article.setAuthor(author);
                articles.add(article);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionDB.closeConnection();
        }
        return articles;
    }

    /**
     * Insert article
     * @param article
     */
    public void insertArticle (Article article) {
        Statement statement = connectionDB.openConnection();
    }

    /**
     * Delete article
     * @param article
     */
    public void deleteArticle (Article article) {

    }


    /**
     * Update Article
     * @param article
     */
    public void updateArticle (Article article) {

    }
}
