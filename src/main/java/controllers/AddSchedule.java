package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ResourceBundle;

import entity.MedHistoryEntity;
import entity.SheduleEntity;
import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;

public class AddSchedule {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addBtn;

    @FXML
    private DatePicker dateDate;

    @FXML
    private TextField descFld1;

    @FXML
    private TextField descFld11;

    @FXML
    private TextField descFld111;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        if (event.getSource() == addBtn) {
            try (Session session = HiberCfg.getSession()) { //add transaction
                session.beginTransaction();
                SheduleEntity sheduleEntity = new SheduleEntity();
                sheduleEntity.setUserUsername(Users.getUsernameMem());
                sheduleEntity.setDate(Date.valueOf(dateDate.getValue()));
                sheduleEntity.setStatus(descFld1.getText());
                if (!descFld11.getText().isEmpty()) {
                    sheduleEntity.setStart(Time.valueOf(descFld11.getText()));
                    sheduleEntity.setEnd(Time.valueOf(descFld111.getText()));
                }
                session.save(sheduleEntity);
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
