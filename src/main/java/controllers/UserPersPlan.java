package controllers;

import entity.InfoAnimalEntity;
import entity.PersPlanEntity;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class UserPersPlan {

    ObservableList<PersPlanEntity> listEntity;
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
    private TextField searchField;
    @FXML
    private TableView<PersPlanEntity> mainTab;
    @FXML
    private TableColumn<PersPlanEntity, Integer> idCol;
    @FXML
    private TableColumn<PersPlanEntity, String> nameCol;
    @FXML
    private TableColumn<PersPlanEntity, Date> dateCol;
    @FXML
    private TableColumn<PersPlanEntity, String> descCol;
    @FXML
    private TableColumn<PersPlanEntity, String> statusCol;
    @FXML
    private Button searchBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Label fullNameFld;



    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == addBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/AddPersPlan.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        if (event.getSource() == searchBtn) {
            try (Session session = HiberCfg.getSession()) {
                session.beginTransaction();

                Query query = session.createQuery("FROM PersPlanEntity p WHERE p.userUsername like '" + Users.getUsernameMem() +"'"+ "AND p.name like '%" + searchField.getText() + "%'");
                List<PersPlanEntity> list = (List<PersPlanEntity>) query.list();
                session.getTransaction().commit();
                listEntity = FXCollections.observableArrayList(list);
                mainTab.setItems(listEntity);
            }
        }
    }

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idpaln"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        try (Session session = HiberCfg.getSession()) {
            fullNameFld.setText(Helper.getSQLString(session,session.createQuery("SELECT  u.firstName FROM UserEntity u WHERE u.username LIKE '"+Users.getUsernameMem()+"'"))+" "+Helper.getSQLString(session,session.createQuery("SELECT  u.secondName FROM UserEntity u WHERE u.username LIKE '"+Users.getUsernameMem()+"'")));

        }

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM PersPlanEntity where userUsername like '"+Users.getUsernameMem()+"'");
            List<PersPlanEntity> list = (List<PersPlanEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTab.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }
}
