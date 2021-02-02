package siekny.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import siekny.models.dao.ArticleDao;
import siekny.models.dto.Article;
import siekny.models.dto.Author;
import siekny.models.dto.Category;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ArticleController implements Initializable {

    /**
     * Map java variables with xml file
     */
    @FXML private TableView tbArticle;
    @FXML private TableColumn colID;
    @FXML private TableColumn colTitle;
    @FXML private TableColumn colDescription;
    @FXML private TableColumn colAuthor;
    @FXML private TableColumn colCategory;
    @FXML private TableColumn colCreatedDate;
    @FXML private JFXComboBox cboAuthor;
    @FXML private JFXComboBox cboCategory;

    @FXML private JFXTextField txtTitle;
    @FXML private JFXTextArea txtDescription;
    @FXML private JFXDatePicker createdDate;

    private ArticleDao articleDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create three columns
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
//        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
//        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCreatedDate.setCellValueFactory(new PropertyValueFactory<>("createdDate"));
        colAuthor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article,String>, ObservableValue<String>>() {
             @Override
             public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> param) {
                 Author author = param.getValue().getAuthor();
                 if(author != null) {
                     return new SimpleStringProperty(author.getName());
                 }
                 return null;
             }
         }
        );

        colCategory.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Article,String>, ObservableValue<String>>() {
                                            @Override
                                            public ObservableValue<String> call(TableColumn.CellDataFeatures<Article, String> param) {
                                                Category category = param.getValue().getCategory();
                                                if (category != null) {
                                                    return new SimpleStringProperty(category.getName());
                                                }
                                                return null;
                                            }
                                        }

        );

        articleDao = new ArticleDao();
        loadArticles();
    }

    /**
     * load all articles from dao
     */
    private void loadArticles () {
        List<Article> articles = articleDao.getAllAllArticles();
        ObservableList list = FXCollections.observableArrayList(articles);
        tbArticle.setItems(list);

    }

    /**
     * result from selecting author combobox
     * @return
     */
    private String getAuthorSelected () {
        Integer index = cboAuthor.getSelectionModel().getSelectedIndex();
        Object itemSelected = cboAuthor.getSelectionModel().getSelectedItem();

        if (index != -1 || itemSelected != null) {
            return itemSelected.toString();
        }
        return null;
    }

    /**
     * result from selecting category combobox
     * @return
     */
    private String getCategorySelected () {
        Integer index = cboCategory.getSelectionModel().getSelectedIndex();
        Object itemSelected = cboCategory.getSelectionModel().getSelectedItem();

        if (index != -1 || itemSelected != null) {
            return itemSelected.toString();
        }
        return null;
    }


    /**
     * save articles
     */
    @FXML private void onBtnSaveClicked () {
        Article article = new Article();
        article.setTitle(txtTitle.getText().trim());
        article.setDescription(txtDescription.getText().trim());
        article.setCreatedDate(createdDate.getValue());

        Author author = new Author();
        author.setName(getAuthorSelected());
        article.setAuthor(author);

        Category category = new Category();
        category.setName(getCategorySelected());
        article.setCategory(category);

        if (article != null) {
            tbArticle.getItems().add(article);
            articleDao.insertArticle(article);
        }
    }

    /**
     * edit article
     */
    @FXML private void onBtnEditClicked () {
        int index = tbArticle.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Author author = (Author) tbArticle.getSelectionModel().getSelectedItem();
            tbArticle.getItems().set(index, new Author());
        }
    }

    /**
     * delete article
     */
    @FXML private void onBtnDeleteClicked () {
        int index = tbArticle.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Article article = (Article) tbArticle.getSelectionModel().getSelectedItem();
            tbArticle.getItems().remove(index);
            articleDao.deleteArticle(article);
        }
    }

    /**
     * export articles
     */
    @FXML private void onBtnExportClicked () {

    }
}
