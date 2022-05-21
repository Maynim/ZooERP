package controllers;

import entity.InfoAnimalEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Animals {

    private static int idAnimalMem;
    static private String species;
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
    private TableColumn<InfoAnimalEntity, Integer> aviaryCol;
    @FXML
    private Button searchBtn;
    @FXML
    private Label speciesLbl;
    @FXML
    private Label subsoeciesLbl;
    @FXML
    private Label countLbl;
    @FXML
    private Label breedingLbl;
    @FXML
    private Label descLbl;
    @FXML
    private Button addBtn;
    @FXML
    private Button showBtn;
    @FXML
    private Button updateBtn;


    private ObservableList<InfoAnimalEntity> listEntity;
    private int index;
    private static String specieAnimalMem;

    public static int getIdAnimalMem() {
        return idAnimalMem;
    }

    public static void setIdAnimalMem(int idAnimalMem) {
        Animals.idAnimalMem = idAnimalMem;
    }

    public static String getSpecies() {
        return species;
    }

    @FXML
    void handleButtonClicks(ActionEvent event) throws IOException {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == searchBtn) {
            try (Session session = HiberCfg.getSession()) {
                session.beginTransaction();

                Query query = session.createQuery("FROM InfoAnimalEntity i WHERE i.name like '" + searchField.getText() + "%' OR i.animalSubspecies like '%" + searchField.getText() + "%'");
                List<InfoAnimalEntity> list = (List<InfoAnimalEntity>) query.list();
                session.getTransaction().commit();
                listEntity = FXCollections.observableArrayList(list);
                mainTab.setItems(listEntity);
            }
        }
        if (event.getSource() == showBtn) {
            Helper.switchScene("/fxml/Aviary.fxml", showBtn);
        }
        if (event.getSource() == addBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/AddAnimal.fxml"));
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        if (event.getSource() == updateBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/UpdateCollection.fxml"));
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

    @FXML
    void initialize() {
        descLbl.setWrapText(true);
        if (Authorization.getJob().equals("Keeper")) {
            budgetBtn.setStyle("-fx-background-color:GRAY;-fx-background-radius:0");
        }
        idCol.setCellValueFactory(new PropertyValueFactory<>("idinfoAnimal"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        subspeciesCol.setCellValueFactory(new PropertyValueFactory<>("animalSubspecies"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthdayDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("birthdayTime"));
        aviaryCol.setCellValueFactory(new PropertyValueFactory<>("aviaryIdaviary"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM InfoAnimalEntity");
            List<InfoAnimalEntity> list = (List<InfoAnimalEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTab.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }

        mainTab.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
//                index = mainTab.getSelectionModel().getSelectedIndex();
//                specieAnimalMem = subspeciesCol.getCellData(index);
//                try (Session session = HiberCfg.getSession()) {
//                    session.beginTransaction();
//                    subsoeciesLbl.setText(Helper.getSQLString(session,session.createQuery("SELECT a.subspecies FROM AnimalEntity a where a.subspecies like '"+specieAnimalMem+"'")));
//                }
            }
        });
        mainTab.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                index = mainTab.getSelectionModel().getSelectedIndex();
                idAnimalMem = idCol.getCellData(index);
                try (Session session = HiberCfg.getSession()) {
                    species = Helper.getSQLString(session, session.createQuery("select animalSubspecies from InfoAnimalEntity where idinfoAnimal =" + idAnimalMem + ""));
                }
                Helper.switchScene("/fxml/InfoAnimal.fxml", profileBtn);
            }
        });
    }

    @FXML
    void selected(MouseEvent event) {
        index = mainTab.getSelectionModel().getSelectedIndex();
        specieAnimalMem = subspeciesCol.getCellData(index);
        try (Session session = HiberCfg.getSession()) {
            speciesLbl.setText(Helper.getSQLString(session,session.createQuery("SELECT a.species FROM AnimalEntity a where a.subspecies like '"+specieAnimalMem+"'")));
            subsoeciesLbl.setText(Helper.getSQLString(session,session.createQuery("SELECT a.subspecies FROM AnimalEntity a where a.subspecies like '"+specieAnimalMem+"'")));
            countLbl.setText(Helper.getSQLString(session,session.createQuery("SELECT a.count FROM AnimalEntity a where a.subspecies like '"+specieAnimalMem+"'")));
            breedingLbl.setText(Helper.getSQLString(session,session.createQuery("SELECT a.breeding FROM AnimalEntity a where a.subspecies like '"+specieAnimalMem+"'")));
            descLbl.setText(Helper.getSQLString(session,session.createQuery("SELECT a.disc FROM AnimalEntity a where a.subspecies like '"+specieAnimalMem+"'")));
        }

    }

    public static String getSpecieAnimalMem() {
        return specieAnimalMem;
    }
}


