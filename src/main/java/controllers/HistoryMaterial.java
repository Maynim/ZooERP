package controllers;

import entity.HistoryMaterialEntity;
import entity.MaterialStorageEntity;
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

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class HistoryMaterial {

    ObservableList<HistoryMaterialEntity> listEntity;
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
    private TableView<HistoryMaterialEntity> mainTable;
    @FXML
    private TableColumn<HistoryMaterialEntity, Integer> idCol;
    @FXML
    private TableColumn<HistoryMaterialEntity, Date> dateCol;
    @FXML
    private TableColumn<HistoryMaterialEntity, String> userCol;
    @FXML
    private TableColumn<HistoryMaterialEntity, Integer> countCol;
    @FXML
    private Button backBtn;

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private Button searchBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TextField countFld;

    @FXML
    private Label nameLbl;

    @FXML
    private Label typeLbl;

    @FXML
    private Label countLbl;


    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
    }

    @FXML
    void initialize() {
        try (Session session = HiberCfg.getSession()) {

            nameLbl.setText(Helper.getSQLString( session,session.createQuery("SELECT materialName FROM MaterialStorageEntity WHERE idmaterialStorage = "+ StorageMaterial.getMaterialMem() +"")));
            typeLbl.setText(Helper.getSQLString( session,session.createQuery("SELECT type FROM MaterialStorageEntity WHERE idmaterialStorage = "+ StorageMaterial.getMaterialMem() +"")));
            countLbl.setText(Helper.getSQLString( session,session.createQuery("SELECT m.count  FROM MaterialStorageEntity m WHERE idmaterialStorage = "+ StorageMaterial.getMaterialMem() +"")));
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("idhistoryMaterial"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userUsername"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM HistoryMaterialEntity WHERE materialStorageIdmaterialStorage = " + StorageMaterial.getMaterialMem() + "");
            List<HistoryMaterialEntity> list = (List<HistoryMaterialEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTable.setItems(listEntity);

        } catch (Throwable cause) {
            System.out.println(cause);
        }

    }
}
