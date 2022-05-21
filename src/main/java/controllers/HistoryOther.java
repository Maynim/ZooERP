package controllers;

import entity.HistoryFoodEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryOther {

    ObservableList<HistoryFoodEntity> listEntity;
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
    private TableView<?> mainTable;
    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> dateCol;
    @FXML
    private TableColumn<?, ?> userCol;
    @FXML
    private TableColumn<?, ?> countCol;
    @FXML
    private Label nameLbl;
    @FXML
    private Button backBtn;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
    }

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idhistoryStorage"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userUsername"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM HistoryFoodEntity WHERE foodStorageIdfoodStorage = " + StorageFood.getFoodMem() + "");
            List<HistoryFoodEntity> list = (List<HistoryFoodEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            //mainTable.setItems(listEntity);

        } catch (Throwable cause) {
            System.out.println(cause);
        }
    }
}
