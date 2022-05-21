package controllers;

import entity.PalnEntity;
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

public class GenPlan {

    ObservableList<PalnEntity> listEntity;
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
    private TableView<PalnEntity> mainTab;
    @FXML
    private TableColumn<PalnEntity, Integer> idCol;
    @FXML
    private TableColumn<PalnEntity, String> nameCol;
    @FXML
    private TableColumn<PalnEntity, Date> startCol;
    @FXML
    private TableColumn<PalnEntity, Date> endCol;
    @FXML
    private TableColumn<PalnEntity, Double> sumCol;
    @FXML
    private TableColumn<PalnEntity, String> descCol;
    @FXML
    private TableColumn<PalnEntity, String> statusCol;
    @FXML
    private Button searchBtn;
    @FXML
    private Button addPlanBtn;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == addPlanBtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/AddGenPlan.fxml"));
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
        idCol.setCellValueFactory(new PropertyValueFactory<>("idpaln"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        sumCol.setCellValueFactory(new PropertyValueFactory<>("resource"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));


        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            Query query = session.createQuery("FROM PalnEntity ");
            List<PalnEntity> list = (List<PalnEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTab.setItems(listEntity);

        } catch (Throwable cause) {
            cause.printStackTrace();
        }
    }
}
