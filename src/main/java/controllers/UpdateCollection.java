package controllers;

import entity.AnimalEntity;
import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateCollection {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField healthFld;

    @FXML
    private TextField descFld;

    @FXML
    private Button addBtn;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        if (event.getSource() == addBtn) {
            try (Session session = HiberCfg.getSession()) { //add transaction
                session.beginTransaction();
                AnimalEntity animalEntity = session.get(AnimalEntity.class, Animals.getSpecieAnimalMem());
                animalEntity.setBreeding(healthFld.getText());
                animalEntity.setDisc(descFld.getText());
                session.save(animalEntity);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
    }
}
