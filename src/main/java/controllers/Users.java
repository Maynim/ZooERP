package controllers;

import entity.UserEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class Users {

    private static String usernameMem;
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
    private TableView<UserEntity> mainTab;
    @FXML
    private TableColumn<UserEntity, String> userNameCol;
    @FXML
    private TableColumn<UserEntity, String> firstCol;
    @FXML
    private TableColumn<UserEntity, String> secCol;
    @FXML
    private TableColumn<UserEntity, String> medCol;
    @FXML
    private TableColumn<UserEntity, Byte> genderCol;
    @FXML
    private TableColumn<UserEntity, Date> birthdayCol;
    @FXML
    private TableColumn<UserEntity, String> phoneCol;
    @FXML
    private TableColumn<UserEntity, String> jobCol;
    @FXML
    private TableColumn<UserEntity, Date> registrCol;
    @FXML
    private Button searchBtn;
    @FXML
    private Button addBtn;
    private ObservableList<UserEntity> listEntity;
    private int index;

    public static String getUsernameMem() {
        return usernameMem;
    }

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == addBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/AddUser.fxml"));
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
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        medCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        jobCol.setCellValueFactory(new PropertyValueFactory<>("jobName"));
        registrCol.setCellValueFactory(new PropertyValueFactory<>("createTime"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM UserEntity");
            List<UserEntity> list = (List<UserEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTab.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }

        mainTab.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                index = mainTab.getSelectionModel().getSelectedIndex();
                usernameMem = userNameCol.getCellData(index);
                Helper.switchScene("/fxml/UserInfo.fxml", profileBtn);
            }
        });
    }
}
