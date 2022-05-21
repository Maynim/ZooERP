package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddGenPlan {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameFld;

    @FXML
    private TextField descFld;

    @FXML
    private Button addBtn;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField resFld;

    @FXML
    void handleButtonClicks(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }
}
