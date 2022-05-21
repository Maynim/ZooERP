package controllers;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import entity.BudgetEntity;
import entity.UserEntity;
import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.hibernate.Session;

public class UpdateJob {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameFld;

    @FXML
    private Button addBtn;

    @FXML
    private MenuButton jobSlct;

    @FXML
    private MenuItem maleItem;

    @FXML
    private MenuItem femaleItem;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        if (event.getSource() == addBtn) {
            try (Session session = HiberCfg.getSession()) { //add transaction
                session.beginTransaction();
                UserEntity userEntity = session.get(UserEntity.class, Users.getUsernameMem());
                userEntity.setJobName(nameFld.getText());
                session.save(userEntity);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();
            List list = session.createQuery("SELECT name FROM JobEntity").list();
            for (int i = 0; i < list.size(); i++) {
                String nameJob = list.get(i).toString();
                MenuItem menuItem = new MenuItem(nameJob);
                jobSlct.getItems().add(menuItem);
                final String name = nameJob;
                menuItem.setOnAction(actionEvent -> nameFld.setText(name));
            }
        }
    }
}
