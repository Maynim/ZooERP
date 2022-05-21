package controllers;

import entity.UserEntity;
import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.hibernate.Session;

import java.sql.Date;
import java.util.List;

public class AddUser {

    @FXML
    private TextField usernameFld;

    @FXML
    private TextField passFld;

    @FXML
    private Button addBtn;

    @FXML
    private DatePicker bdFld;

    @FXML
    private TextField firstFld;

    @FXML
    private TextField secFld;

    @FXML
    private TextField midFld;

    @FXML
    private TextField genFld;

    @FXML
    private TextField phoneFld;

    @FXML
    private TextField jobFld;

    @FXML
    private MenuButton select;

    @FXML
    private MenuButton genderSelect;

    @FXML
    private MenuItem maleItem;

    @FXML
    private MenuItem femaleItem;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        if (event.getSource() == addBtn) {
            try (Session session = HiberCfg.getSession()) { //add transaction
                session.beginTransaction();
                UserEntity userEntity = new UserEntity();
                userEntity.setUsername(usernameFld.getText());
                userEntity.setPassword(passFld.getText());
                userEntity.setFirstName(firstFld.getText());
                userEntity.setSecondName(secFld.getText());
                userEntity.setMiddleName(midFld.getText());
                userEntity.setBirthday(Date.valueOf(bdFld.getValue()));
                userEntity.setGender(genFld.getText());
                userEntity.setPhoneNumber(phoneFld.getText());
                userEntity.setJobName(jobFld.getText());
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
                select.getItems().add(menuItem);
                final String name = nameJob;
                menuItem.setOnAction(actionEvent -> jobFld.setText(name));
            }
            final String name2 = maleItem.getText();
            final String name3 = femaleItem.getText();
            maleItem.setOnAction(actionEvent -> genFld.setText(name2));
            femaleItem.setOnAction(actionEvent -> genFld.setText(name3));
        }
    }
}
