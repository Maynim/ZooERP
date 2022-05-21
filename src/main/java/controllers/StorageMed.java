package controllers;

import entity.MedStorageEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StorageMed {

    ObservableList<MedStorageEntity> listEntity;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button animalsBtn;
    @FXML
    private Button sheduleBtn;
    @FXML
    private Button budgetBtn;
    @FXML
    private Button storageBtn;
    @FXML
    private Button reportBtn;
    @FXML
    private Button profileBtn;
    @FXML
    private TableView<MedStorageEntity> mainTable;
    @FXML
    private TableColumn<MedStorageEntity, Integer> idCol;
    @FXML
    private TableColumn<MedStorageEntity, Integer> storageCol;
    @FXML
    private TableColumn<MedStorageEntity, String> nameCol;
    @FXML
    private TableColumn<MedStorageEntity, String> typeCol;
    @FXML
    private TableColumn<MedStorageEntity, Integer> countCol;
    @FXML
    private Button foodBtn;
    @FXML
    private Button medBtn;
    @FXML
    private Button materialBnt;
    @FXML
    private Button otherBtn;

    public static String getMedMem() {
        return null;
    }

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Storage.storageSelect(event, animalsBtn, budgetBtn, storageBtn, profileBtn, foodBtn, medBtn, materialBnt, otherBtn);
    }

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idmedHistory"));
        storageCol.setCellValueFactory(new PropertyValueFactory<>("storageIdstorage"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM MedStorageEntity ");
            List<MedStorageEntity> list = (List<MedStorageEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTable.setItems(listEntity);

        } catch (Throwable cause) {

        }
    }
}
