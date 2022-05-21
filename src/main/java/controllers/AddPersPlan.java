package controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;

import entity.PersPlanEntity;
import entity.SheduleEntity;
import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.hibernate.Session;

public class AddPersPlan {

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
    void handleButtonClicks(ActionEvent event) {
        if (event.getSource() == addBtn) {
            try (Session session = HiberCfg.getSession()) { //add transaction
                session.beginTransaction();
                PersPlanEntity persPlan = new PersPlanEntity();
                persPlan.setName(nameFld.getText());
                persPlan.setUserUsername(Users.getUsernameMem());
                persPlan.setDate(Date.valueOf(startDate.getValue()));
                persPlan.setEndDate(Date.valueOf(startDate.getValue()));
                persPlan.setStatus("Planned");
                persPlan.setDescription(descFld.getText());
                session.save(persPlan);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }

    }

    @FXML
    void initialize() {

    }
}
