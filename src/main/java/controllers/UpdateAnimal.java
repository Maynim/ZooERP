package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UpdateAnimal {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBtn;

    @FXML
    private TextField countFld111;

    @FXML
    void handleButtonClicks(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'UpdateAnimal.fxml'.";
        assert countFld111 != null : "fx:id=\"countFld111\" was not injected: check your FXML file 'UpdateAnimal.fxml'.";

    }
}
