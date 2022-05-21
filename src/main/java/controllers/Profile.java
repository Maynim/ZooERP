package controllers;

import entity.ScheduleCheckEntity;
import entity.SheduleEntity;
import entity.UserEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Profile {

    ObservableList<SheduleEntity> listEntity;
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
    private Button animalsBtn;
    @FXML
    private Button budgetBtn;
    @FXML
    private Button storageBtn;
    @FXML
    private Button profileBtn;
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
    private Label genPlLbl;
    @FXML
    private DatePicker fromDate;
    @FXML
    private DatePicker toDate;
    @FXML
    private Button daylyBtn;
    @FXML
    private Button medBtn;
    @FXML
    private Button userBtn;
    @FXML
    private Button otherBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button arrivBtn;
    @FXML
    private Button leavBtn;
    @FXML
    private Button genViewBtn;
    @FXML
    private Button persViewBtn;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == daylyBtn) {
            Helper.switchScene("/fxml/DailyReport.fxml", daylyBtn);
        }

        if (event.getSource() == medBtn) {
            Helper.switchScene("/fxml/VetReport.fxml", medBtn);
        }

        if (event.getSource() == userBtn) {
            Helper.switchScene("/fxml/Users.fxml", userBtn);
        }

        if (event.getSource() == otherBtn) {
            Helper.switchScene("/fxml/OtherReport.fxml", otherBtn);
        }

        if (event.getSource() == genViewBtn) {
            Helper.switchScene("/fxml/GenPlan.fxml", otherBtn);
        }

        if (event.getSource() == persViewBtn) {
            Helper.switchScene("/fxml/PersPlan.fxml", otherBtn);
        }
        if (event.getSource() == searchBtn) {
            try (Session session = HiberCfg.getSession()) {
                session.beginTransaction();

                Query query = session.createQuery("FROM SheduleEntity WHERE DATE(date) BETWEEN DATE(" + "'" + fromDate.getValue().toString() + "'" + ") AND DATE(" + "'" + toDate.getValue().toString() + "'" + ") ORDER BY date DESC");
                List<SheduleEntity> list = (List<SheduleEntity>) query.list();
                session.getTransaction().commit();
                listEntity = FXCollections.observableArrayList(list);
                mainTable.setItems(listEntity);

            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
        if (event.getSource() == arrivBtn) {
            try (Session session = HiberCfg.getSession()) {
                session.beginTransaction();
                ScheduleCheckEntity scheduleCheckEntity = new ScheduleCheckEntity();
                scheduleCheckEntity.setUserUsername(Helper.getLogin());
                scheduleCheckEntity.setStartTime(new Date());
                session.save(scheduleCheckEntity);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
        if (event.getSource() == leavBtn) {
            try (Session session = HiberCfg.getSession()) {
                ScheduleCheckEntity scheduleCheckEntity = session.get(ScheduleCheckEntity.class, (Helper.getSQLInteger(session, session.createQuery("select max(idScheduleCheck) FROM ScheduleCheckEntity WHERE userUsername like '" + Helper.getLogin() + "'"))));
                scheduleCheckEntity.setEndTime(new Date());

                SimpleDateFormat formater = new SimpleDateFormat("HH:mm:ss");
                Date date1 = formater.parse(String.valueOf(scheduleCheckEntity.getStartTime()));
                Date date2 = formater.parse(Helper.getSQLString(session, session.createQuery("select endTime FROM ScheduleCheckEntity WHERE userUsername like '" + Helper.getLogin() + "' order by idScheduleCheck DESC")));

                long milliseconds = date2.getTime() - date1.getTime();

                // 1000 миллисекунд = 1 секунда
                int seconds = (int) (milliseconds / (1000));
                scheduleCheckEntity.setTotalTime(seconds);
                UserEntity user = session.get(UserEntity.class, Helper.getLogin());
                user.setMoneyEarned(user.getMoneyEarned() + (seconds * Helper.getSQLDouble(session, session.createQuery("SELECT j.salary FROM JobEntity j INNER JOIN UserEntity u ON j.name like u.jobName WHERE u.username like '" + Helper.getLogin() + "'"))));
                session.beginTransaction();
                session.save(user);
                session.save(scheduleCheckEntity);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        if (Authorization.getJob().equals("Keeper")) {
            budgetBtn.setStyle("-fx-background-color:GRAY;-fx-background-radius:0");

            userBtn.setVisible(false);
            medBtn.setVisible(false);
            otherBtn.setVisible(false);
            genViewBtn.setVisible(false);
            genPlLbl.setVisible(false);


        }
        try (Session session = HiberCfg.getSession()) {
            firstNameLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT u.firstName From UserEntity u WHERE username like '" + Helper.getLogin() + "'")));

            secondNameLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT u.secondName From UserEntity u WHERE username like '" + Helper.getLogin() + "'")));

            midNameLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT u.middleName From UserEntity u WHERE username like '" + Helper.getLogin() + "'")));

            birthdayLbl.setText(Helper.getSQLResult(session, session.createQuery
                    ("SELECT u.birthday From UserEntity u WHERE username like '" + Helper.getLogin() + "'")));

            ProfLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT j.name From JobEntity j WHERE (SELECT u.jobName From UserEntity u WHERE username like '" + Helper.getLogin() + "') like j.name")));

        }
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        beginCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM SheduleEntity WHERE userUsername like '" + Helper.getLogin() + "' ORDER BY date DESC");
            List<SheduleEntity> list = (List<SheduleEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTable.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }


}
