package controllers;

import entity.AviaryEntity;
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
import java.util.List;
import java.util.ResourceBundle;

public class Aviary {

    private static int idAviaryMem;
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
    private TableView<AviaryEntity> mainTab;
    @FXML
    private TableColumn<AviaryEntity, Integer> idCol;
    @FXML
    private TableColumn<AviaryEntity, Double> lenCol;
    @FXML
    private TableColumn<AviaryEntity, Double> widCol;
    @FXML
    private TableColumn<AviaryEntity, Double> heigCol;
    @FXML
    private TableColumn<AviaryEntity, Integer> countCol;
    @FXML
    private TableColumn<AviaryEntity, String> descCol;
    @FXML
    private Button searchBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TextField lenFld;
    @FXML
    private TextField widthFld;
    @FXML
    private TextField heightFld;
    @FXML
    private TextField descFld;
    private ObservableList<AviaryEntity> listEntity;

    public static int getIdAviaryMem() {
        return idAviaryMem;
    }

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
    }

    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idaviary"));
        lenCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        widCol.setCellValueFactory(new PropertyValueFactory<>("width"));
        heigCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("countAnimals"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM AviaryEntity ");
            List<AviaryEntity> list = (List<AviaryEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTab.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }


        mainTab.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                int index = mainTab.getSelectionModel().getSelectedIndex();
                idAviaryMem = idCol.getCellData(index);
                Helper.switchScene("/fxml/AnimalsAviary.fxml", profileBtn);
            }
        });
    }


}
