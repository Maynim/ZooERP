package controllers;

import entity.MedHistoryEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class Medcard {

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
    private TableView<MedHistoryEntity> mainTable;

    @FXML
    private TableColumn<MedHistoryEntity, Integer> idCol;

    @FXML
    private TableColumn<MedHistoryEntity, Date> dateCol;

    @FXML
    private TableColumn<MedHistoryEntity, String> medCol;

    @FXML
    private TableColumn<MedHistoryEntity, String> descCol;

    @FXML
    private TableColumn<MedHistoryEntity, Integer> countCol;

    @FXML
    private Button backBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button upadateBtn;

    @FXML
    private Label nameLbl;

    @FXML
    private Label healthLbl;

    @FXML
    private Label descLbl;

    @FXML
    private Button refreshBtn;

    private ObservableList<MedHistoryEntity> listEntity;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == backBtn) {
            Helper.switchScene("/fxml/InfoAnimal.fxml", backBtn);
        }
        if (event.getSource() == addBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/AddMedHistory.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        if (event.getSource() == refreshBtn) {
            setterItems();
        }
        if (event.getSource() == upadateBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/ChangeMed.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private void setterItems() {
        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM MedHistoryEntity m WHERE m.medcardIdmedcard = '" + Animals.getIdAnimalMem() + "' ");
            List<MedHistoryEntity> list = (List<MedHistoryEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTable.setItems(listEntity);
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        descLbl.setWrapText(true);
        try (Session session = HiberCfg.getSession()) {
            nameLbl.setText(Helper.getSQLString(session, session.createQuery("SELECT name from InfoAnimalEntity where idinfoAnimal = " + Animals.getIdAnimalMem() + "")));
            healthLbl.setText(Helper.getSQLString(session, session.createQuery("SELECT m.health from MedcardEntity m WHERE m.idmedcard = '" + Animals.getIdAnimalMem() + "'")));
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
        try (Session session = HiberCfg.getSession()) {
            descLbl.setText(Helper.getSQLString(session, session.createQuery("SELECT m.description from MedcardEntity m WHERE m.idmedcard = '" + Animals.getIdAnimalMem() + "'")));
        } catch (Throwable cause) {
            cause.printStackTrace();
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("idmedHistory"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        medCol.setCellValueFactory(new PropertyValueFactory<>("medicament"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("count"));
        setterItems();

    }
}
