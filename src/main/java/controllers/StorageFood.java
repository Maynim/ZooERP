package controllers;

import entity.FoodStorageEntity;
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

public class StorageFood {

    static private int foodMem;
    ObservableList<FoodStorageEntity> listEntity;
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
    private TableView<FoodStorageEntity> mainTable;
    @FXML
    private TableColumn<FoodStorageEntity, Integer> idCol;
    @FXML
    private TableColumn<FoodStorageEntity, Integer> storageCol;
    @FXML
    private TableColumn<FoodStorageEntity, String> nameCol;
    @FXML
    private TableColumn<FoodStorageEntity, String> typeCol;
    @FXML
    private TableColumn<FoodStorageEntity, Integer> countCol;
    @FXML
    private Button foodBtn;
    @FXML
    private Button medBtn;
    @FXML
    private Button materialBnt;
    @FXML
    private Button otherBtn;

    public static int getFoodMem() {
        return foodMem;
    }

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Storage.storageSelect(event, animalsBtn, budgetBtn, storageBtn, profileBtn, foodBtn, medBtn, materialBnt, otherBtn);

    }

    @FXML
    void initialize() {

        idCol.setCellValueFactory(new PropertyValueFactory<>("idfoodStorage"));
        storageCol.setCellValueFactory(new PropertyValueFactory<>("storageIdstorage"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM FoodStorageEntity");
            List<FoodStorageEntity> list = (List<FoodStorageEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTable.setItems(listEntity);

        } catch (Throwable cause) {
            System.out.println(cause);
        }

        mainTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                int index = mainTable.getSelectionModel().getSelectedIndex();
                foodMem = idCol.getCellData(index);
                Helper.switchScene("/fxml/HistoryFood.fxml", profileBtn);
            }
        });
    }
}
