package controllers;

import entity.DietEntity;
import entity.DietInfoEntity;
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

public class Diet {

    private static int idDietMem;
    ObservableList<DietEntity> listEntity;
    ObservableList<DietInfoEntity> listEntity2;
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
    private TableView<DietEntity> mainTable;
    @FXML
    private TableColumn<DietEntity, Integer> idCol;
    @FXML
    private TableColumn<DietEntity, String> seasonCol;
    @FXML
    private TableColumn<DietEntity, String> timeCol;
    @FXML
    private TableColumn<DietEntity, String> typeCol;
    @FXML
    private TableView<DietInfoEntity> mainTable2;
    @FXML
    private TableColumn<DietInfoEntity, String> foodCol;
    @FXML
    private TableColumn<DietInfoEntity, Integer> countCol;
    @FXML
    private TableColumn<DietInfoEntity, Double> kcalCol;
    @FXML
    private Button backBtn;
    private int index;

    public static int getIdDietMem() {
        return idDietMem;
    }

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == backBtn) {
            Helper.switchScene("/fxml/InfoAnimal.fxml", backBtn);
        }
    }

    @FXML
    void initialize() {
        if (Authorization.getJob().equals("Keeper")) {
            budgetBtn.setStyle("-fx-background-color:GRAY;-fx-background-radius:0");
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("iddiet"));
        seasonCol.setCellValueFactory(new PropertyValueFactory<>("season"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM DietEntity WHERE animalSubspecies = '" + Animals.getSpecies() + "'");
            List<DietEntity> list = (List<DietEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTable.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }
        mainTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                index = mainTable.getSelectionModel().getSelectedIndex();
                try (Session session = HiberCfg.getSession()) {
                    session.beginTransaction();
                    index = mainTable.getSelectionModel().getSelectedIndex();
                    idDietMem = idCol.getCellData(index);
                    foodCol.setCellValueFactory(new PropertyValueFactory<>("food"));
                    countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
                    kcalCol.setCellValueFactory(new PropertyValueFactory<>("kcal"));
                    Query query = session.createQuery("FROM DietInfoEntity WHERE dietIddiet = '" + Diet.getIdDietMem() + "'");
                    List<DietInfoEntity> list = (List<DietInfoEntity>) query.list();
                    session.getTransaction().commit();
                    listEntity2 = FXCollections.observableArrayList(list);
                    mainTable2.setItems(listEntity2);

                } catch (Throwable cause) {
                    cause.printStackTrace();
                }
            }
        });
    }
}


