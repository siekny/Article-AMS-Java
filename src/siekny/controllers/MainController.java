package siekny.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML private BorderPane mainLayout;

    @FXML private void onBtnAuthorClicked () throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/AuthorView.fxml"));
        mainLayout.setCenter(parent);
    }

    @FXML private void onBtnCategoryClicked () throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/CategoryView.fxml"));
        mainLayout.setCenter(parent);
    }

    @FXML private void onBtnArticleClicked () throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/ArticleView.fxml"));
        mainLayout.setCenter(parent);
    }

    @FXML private void onBtnExitClicked () {

    }

}
