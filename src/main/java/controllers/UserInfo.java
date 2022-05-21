package controllers;

import entity.SheduleEntity;
import hibercfg.HiberCfg;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Helper;
import org.hibernate.Session;

import java.io.IOException;

public class UserInfo {

    ObservableList<SheduleEntity> listEntity;
    @FXML
    private Button animalsBtn;
    @FXML
    private Button budgetBtn;
    @FXML
    private Button storageBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button upadateBtn;
    @FXML
    private Label firstNameLbl;
    @FXML
    private Label secondNameLbl;
    @FXML
    private Label midNameLbl;
    @FXML
    private Label birthdayLbl;
    @FXML
    private Label ProfLbl;
    @FXML
    private Button planViewBtn;
    @FXML
    private Button schViewBtn;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == backBtn) {
            Helper.switchScene("/fxml/Users.fxml", backBtn);
        }

        if (event.getSource() == planViewBtn) {
            Helper.switchScene("/fxml/UserPersPlan.fxml", backBtn);
        }
        if (event.getSource() == schViewBtn) {
            Helper.switchScene("/fxml/UserSchedule.fxml", backBtn);
        }
        if (event.getSource() == upadateBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/UpdateJob.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    void initialize() {
        try (Session session = HiberCfg.getSession()) {
            firstNameLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT u.firstName From UserEntity u WHERE username like '" + Users.getUsernameMem() + "'")));
            secondNameLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT u.secondName From UserEntity u WHERE username like '" + Users.getUsernameMem() + "'")));
            midNameLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT u.middleName From UserEntity u WHERE username like '" + Users.getUsernameMem() + "'")));
            birthdayLbl.setText(Helper.getSQLResult(session, session.createQuery
                    ("SELECT u.birthday From UserEntity u WHERE username like '" + Users.getUsernameMem() + "'")));
            ProfLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT j.name From JobEntity j WHERE (SELECT u.jobName From UserEntity u WHERE username like '" + Users.getUsernameMem() + "') like j.name")));

        }

    }
}
