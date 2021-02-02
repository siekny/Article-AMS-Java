package siekny.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import siekny.models.dao.AuthorDao;
import siekny.models.dto.Author;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AuthorController implements Initializable {
    @FXML private TableView tbAuthor;
    @FXML private TableColumn colID;
    @FXML private TableColumn colName;
    @FXML private TableColumn colGender;
    @FXML private TableColumn colBirthDate;
    @FXML private JFXComboBox cboGender;

    @FXML private JFXTextField txtName;
    @FXML private JFXDatePicker dateOfBirth;

    private AuthorDao authorDao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create three columns
        cboGender.getItems().add("Female");
        cboGender.getItems().add("Male");

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colBirthDate.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        authorDao = new AuthorDao();
        loadArticle();
    }

    private void loadArticle () {
        List<Author> authors = authorDao.getAllUsers();
        ObservableList list = FXCollections.observableArrayList(authors);
        tbAuthor.setItems(list);

    }

    private String getGenderSelected () {
        Integer index = cboGender.getSelectionModel().getSelectedIndex();
        Object itemSelected = cboGender.getSelectionModel().getSelectedItem();

        if (index != -1 || itemSelected != null) {
            return itemSelected.toString();
        }
        return null;
    }



    @FXML private void onBtnSaveClicked () {
        Author author = new Author();
        author.setName(txtName.getText().trim());
        author.setGender(getGenderSelected());
        author.setDateOfBirth(dateOfBirth.getValue());

        if (author != null) {
            tbAuthor.getItems().add(author);
            authorDao.insertAuthor(author);
        }
    }

    @FXML private void onBtnEditClicked () {
        int index = tbAuthor.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Author author = (Author) tbAuthor.getSelectionModel().getSelectedItem();
            tbAuthor.getItems().set(index, new Author());
        }
    }

    @FXML private void onBtnDeleteClicked () {
        int index = tbAuthor.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Author author = (Author) tbAuthor.getSelectionModel().getSelectedItem();
            tbAuthor.getItems().remove(index);
            authorDao.deleteAuthor(author);
        }
    }

    @FXML private void onBtnExportClicked () {

    }
}
