package controllers;

import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Helper;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InfoAnimal {


    private static int isChild;
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
    private Button medcardBtn;
    @FXML
    private Button dietBtn;
    @FXML
    private Label nameLbl;
    @FXML
    private Label subspeciesLbl;
    @FXML
    private Label birthdayLbl;
    @FXML
    private Label timeLbl;
    @FXML
    private Label fatherLbl;
    @FXML
    private Label motherLbl;
    @FXML
    private Label aviaryLbl;
    @FXML
    private Button backBtn;
    @FXML
    private Button changeAviarybtn;

    public static int getIsChild() {
        return isChild;
    }

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == backBtn) {
            Helper.switchScene("/fxml/Animals.fxml", backBtn);
        }
        if (event.getSource() == medcardBtn) {
            Helper.switchScene("/fxml/Medcard.fxml", medcardBtn);
        }
        if (event.getSource() == dietBtn) {
            Helper.switchScene("/fxml/Diet.fxml", medcardBtn);
        }
        if (event.getSource() == changeAviarybtn) {
            try {
                Parent parent = FXMLLoader.load(getClass().getResource("/fxml/UpdateAnimal.fxml"));
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
        try (Session session = HiberCfg.getSession()) {
            nameLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT i.name From InfoAnimalEntity i WHERE idinfoAnimal like '" + Animals.getIdAnimalMem() + "'")));

            subspeciesLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT i.animalSubspecies From InfoAnimalEntity i WHERE idinfoAnimal like '" + Animals.getIdAnimalMem() + "'")));

            birthdayLbl.setText(Helper.getSQLResult(session, session.createQuery
                    ("SELECT i.birthdayDate From InfoAnimalEntity i WHERE idinfoAnimal like '" + Animals.getIdAnimalMem() + "'")));

            timeLbl.setText(Helper.getSQLResult(session, session.createQuery
                    ("SELECT i.birthdayTime From InfoAnimalEntity i WHERE idinfoAnimal like '" + Animals.getIdAnimalMem() + "'")));

            fatherLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT i.idfather From InfoAnimalEntity i WHERE idinfoAnimal like '" + Animals.getIdAnimalMem() + "'")));

            motherLbl.setText(Helper.getSQLString(session, session.createQuery
                    ("SELECT i.idmother From InfoAnimalEntity i WHERE idinfoAnimal like '" + Animals.getIdAnimalMem() + "'")));

            aviaryLbl.setText(Helper.getSQLResult(session, session.createQuery
                    ("SELECT i.aviaryIdaviary From InfoAnimalEntity i WHERE idinfoAnimal like '" + Animals.getIdAnimalMem() + "'")));


        }
    }
}
