package controllers;

import entity.InfoAnimalEntity;
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
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AnimalsAviary {

    static private String species;
    private static int idAnimalinAviaryMem;
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
    private TableView<InfoAnimalEntity> mainTab;
    @FXML
    private TableColumn<InfoAnimalEntity, Integer> idCol;
    @FXML
    private TableColumn<InfoAnimalEntity, String> nameCol;
    @FXML
    private TableColumn<InfoAnimalEntity, Date> birthdayCol;
    @FXML
    private TableColumn<InfoAnimalEntity, String> genderCol;
    @FXML
    private TableColumn<InfoAnimalEntity, Time> timeCol;
    @FXML
    private TableColumn<InfoAnimalEntity, String> subspeciesCol;
    @FXML
    private Button searchBtn;
    private ObservableList<InfoAnimalEntity> listEntity;
    private int index;

    public static int getIdAnimalinAviaryMem() {
        return idAnimalinAviaryMem;
    }

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
    }

    @FXML
    void initialize() {
        if (Authorization.getJob().equals("Keeper")) {
            budgetBtn.setStyle("-fx-background-color:GRAY;-fx-background-radius:0");
        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("idinfoAnimal"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        subspeciesCol.setCellValueFactory(new PropertyValueFactory<>("animalSubspecies"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthdayDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("birthdayTime"));


        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM InfoAnimalEntity WHERE aviaryIdaviary like '" + Aviary.getIdAviaryMem() + "'");
            List<InfoAnimalEntity> list = (List<InfoAnimalEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTab.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }

        mainTab.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                index = mainTab.getSelectionModel().getSelectedIndex();
                Animals.setIdAnimalMem(idCol.getCellData(index));
                try (Session session = HiberCfg.getSession()) {
                    species = Helper.getSQLString(session, session.createQuery("select animalSubspecies from InfoAnimalEntity where idinfoAnimal =" + Animals.getIdAnimalMem() + ""));
                }
                Helper.switchScene("/fxml/InfoAnimal.fxml", profileBtn);
            }
        });
    }
}
