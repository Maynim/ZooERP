package controllers;

import entity.MedHistoryEntity;
import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMedHistory {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameFld;

    @FXML
    private TextField countFld;

    @FXML
    private TextField descFld;

    @FXML
    private Button addBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        if (event.getSource() == addBtn) {
            try (Session session = HiberCfg.getSession()) { //add transaction
                session.beginTransaction();
                MedHistoryEntity medHistory = new MedHistoryEntity();
                medHistory.setMedicament(nameFld.getText());
                medHistory.setCount(countFld.getText());
                medHistory.setDescription(descFld.getText());
                medHistory.setMedcardIdmedcard(Animals.getIdAnimalMem());
                session.save(medHistory);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
        if (event.getSource() == cancelBtn) {

        }
    }

    @FXML
    void initialize() {

    }
}
