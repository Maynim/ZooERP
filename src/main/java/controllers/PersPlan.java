package controllers;

import entity.PersPlanEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PersPlan {

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
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
    }

    @FXML
    void initialize() {
        if (Authorization.getJob().equals("Keeper")) {
            budgetBtn.setStyle("-fx-background-color:GRAY;-fx-background-radius:0");
            addBtn.setVisible(false);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("idpaln"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));


        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM PersPlanEntity where userUsername like '"+Helper.getLogin()+"'");
            List<PersPlanEntity> list = (List<PersPlanEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTab.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }
}
