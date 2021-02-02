package siekny.controllers;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import siekny.models.dao.AuthorDao;
import siekny.models.dao.CategoryDao;
import siekny.models.dto.Author;
import siekny.models.dto.Category;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryController implements Initializable {
    @FXML private TableView tbCategory;
    @FXML private TableColumn colID;
    @FXML private TableColumn colName;

    @FXML private JFXTextField txtName;

    private CategoryDao categoryDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create three columns

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        categoryDao = new CategoryDao();
        loadArticle();
    }

    private void loadArticle () {
        List<Category> categories = categoryDao.getAllCategories();
        ObservableList list = FXCollections.observableArrayList(categories);
        tbCategory.setItems(list);

    }




    @FXML private void onBtnSaveClicked () {
        Category category = new Category();
        category.setName(txtName.getText().trim());

        if (category != null) {
            tbCategory.getItems().add(category);
        }
    }

    @FXML private void onBtnEditClicked () {
        int index = tbCategory.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Author author = (Author) tbCategory.getSelectionModel().getSelectedItem();
            tbCategory.getItems().set(index, new Author());
        }
    }

    @FXML private void onBtnDeleteClicked () {
        int index = tbCategory.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Category category = (Category) tbCategory.getSelectionModel().getSelectedItem();
            tbCategory.getItems().remove(index);
            categoryDao.deleteCategory(category);
        }
    }

    @FXML private void onBtnExportClicked () {

    }
}
