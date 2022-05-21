package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logic.Helper;

import java.net.URL;
import java.util.ResourceBundle;

public class Storage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button animalsBtn;

    @FXML
    private Button sheduleBtn;

    @FXML
    private Button budgetBtn;

    @FXML
    private Button storageBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private TableView<?> mainTable;

    @FXML
    private TableColumn<?, ?> seasonCol;

    @FXML
    private TableColumn<?, ?> timeCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private Button foodBtn;

    @FXML
    private Button medBtn;

    @FXML
    private Button materialBnt;

    @FXML
    private Button otherBtn;

    static void storageSelect(ActionEvent event, Button animalsBtn, Button budgetBtn, Button storageBtn, Button profileBtn, Button foodBtn, Button medBtn, Button materialBnt, Button otherBtn) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == foodBtn) {
            Helper.switchScene("/fxml/StorageFood.fxml", foodBtn);
        }
        if (event.getSource() == medBtn) {
            Helper.switchScene("/fxml/StorageMed.fxml", medBtn);
        }
        if (event.getSource() == materialBnt) {
            Helper.switchScene("/fxml/StorageMaterial.fxml", materialBnt);
        }
        if (event.getSource() == otherBtn) {
            Helper.switchScene("/fxml/StorageOther.fxml", otherBtn);
        }
    }

    @FXML
    void handleButtonClicks(ActionEvent event) {
        storageSelect(event, animalsBtn, budgetBtn, storageBtn, profileBtn, foodBtn, medBtn, materialBnt, otherBtn);
    }

    @FXML
    void initialize() {

    }
}
