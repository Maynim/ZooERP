package controllers;

import entity.MaterialStorageEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StorageMaterial {

    ObservableList<MaterialStorageEntity> listEntity;
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
    private TableView<MaterialStorageEntity> mainTable;
    @FXML
    private TableColumn<MaterialStorageEntity, Integer> idCol;
    @FXML
    private TableColumn<MaterialStorageEntity, Integer> storageCol;
    @FXML
    private TableColumn<MaterialStorageEntity, String> nameCol;
    @FXML
    private TableColumn<MaterialStorageEntity, String> typeCol;
    @FXML
    private TableColumn<MaterialStorageEntity, Integer> countCol;
    @FXML
    private Button foodBtn;
    @FXML
    private Button medBtn;
    @FXML
    private Button materialBnt;
    @FXML
    private Button otherBtn;

    static private int materialMem;

    public static int getMaterialMem() {
        return materialMem;
    }

    public static void setMaterialMem(int materialMem) {
        StorageMaterial.materialMem = materialMem;
    }


    @FXML
    void handleButtonClicks(ActionEvent event) {
        Storage.storageSelect(event, animalsBtn, budgetBtn, storageBtn, profileBtn, foodBtn, medBtn, materialBnt, otherBtn);
    }

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idmaterialStorage"));
        storageCol.setCellValueFactory(new PropertyValueFactory<>("storageIdstorage"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("materialName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM MaterialStorageEntity");
            List<MaterialStorageEntity> list = (List<MaterialStorageEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTable.setItems(listEntity);

        } catch (Throwable cause) {
            System.out.println(cause);
        }
        mainTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                int index = mainTable.getSelectionModel().getSelectedIndex();
                materialMem = idCol.getCellData(index);
                Helper.switchScene("/fxml/HistoryMaterial.fxml", profileBtn);
            }
        });
    }
}
