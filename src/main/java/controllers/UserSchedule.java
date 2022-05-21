package controllers;

import entity.ScheduleCheckEntity;
import entity.SheduleEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.ResourceBundle;

public class UserSchedule {

    ObservableList<SheduleEntity> listEntity;
    ObservableList<ScheduleCheckEntity> listEntity2;
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
    private Button backBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TableView<SheduleEntity> mainTable;
    @FXML
    private TableColumn<SheduleEntity, Date> dateCol;
    @FXML
    private TableColumn<SheduleEntity, String> statusCol;
    @FXML
    private TableColumn<SheduleEntity, Time> beginCol;
    @FXML
    private TableColumn<SheduleEntity, Time> endCol;
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private Button searchBtn;
    @FXML
    private Button clearBtn;
    @FXML
    private TableView<ScheduleCheckEntity> secTable;
    @FXML
    private TableColumn<ScheduleCheckEntity, Date> dateCol2;
    @FXML
    private TableColumn<ScheduleCheckEntity, Time> startCol2;
    @FXML
    private TableColumn<ScheduleCheckEntity, Time> endCol2;
    @FXML
    private TableColumn<ScheduleCheckEntity, Integer> totalCol;
    @FXML
    private Label hourLbl;
    @FXML
    private Label salaryLbl;
    @FXML
    private Button refreshBtn;


    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == addBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/AddSchedule.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        if (event.getSource() == refreshBtn) {
            try (Session session = HiberCfg.getSession()) {
                session.beginTransaction();
                Query query = session.createQuery("FROM SheduleEntity where userUsername = '" + Users.getUsernameMem() + "' ORDER BY date DESC");
                List<SheduleEntity> list = (List<SheduleEntity>) query.list();
                session.getTransaction().commit();
                listEntity = FXCollections.observableArrayList(list);
                mainTable.setItems(listEntity);
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        beginCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM SheduleEntity where userUsername = '" + Users.getUsernameMem() + "' ORDER BY date DESC");
            List<SheduleEntity> list = (List<SheduleEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTable.setItems(listEntity);
        } catch (Throwable cause) {
            cause.printStackTrace();
        }


        dateCol2.setCellValueFactory(new PropertyValueFactory<>("today"));
        startCol2.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endCol2.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalTime"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM ScheduleCheckEntity where userUsername = '" + Users.getUsernameMem() + "' ORDER BY today DESC");
            List<ScheduleCheckEntity> list = (List<ScheduleCheckEntity>) query.list();
            session.getTransaction().commit();
            listEntity2 = FXCollections.observableArrayList(list);
            secTable.setItems(listEntity2);
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
        try (Session session = HiberCfg.getSession()) {
            int s = Integer.parseInt(Helper.getSQLString(session, session.createQuery("SELECT sum(totalTime) FROM ScheduleCheckEntity where userUsername = '" + Users.getUsernameMem() + "' AND MONTH(today)=MONTH(NOW())")));
            double h = s / 60 / 60;
            hourLbl.setText(String.format("%.1f", h));
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }
}
