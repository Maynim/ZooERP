package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import logic.Helper;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button animalsBtn;

    @FXML
    private Button budgetBtn;

    @FXML
    private Button storageBtn;

    @FXML
    private Button profileBtn;


    @FXML
    void handleButtonClicks(ActionEvent mouseEvent) {
        Helper.bar(mouseEvent, animalsBtn, budgetBtn, storageBtn, profileBtn);
    }

    @FXML
    void initialize() {
    }
}
