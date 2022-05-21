package controllers;

import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.Helper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.hibernate.Session;

import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class VetReport {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button animalsBtn;

    @FXML
    private Button sheduleBtn;

    @FXML
    private Button budgetBtn;

    @FXML
    private Button storageBtn;

    @FXML
    private Button reportBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField animalFld;

    @FXML
    private TextField descAnimalFld;

    @FXML
    private TextField diagnosisFld;

    @FXML
    private TextField dayFld;

    @FXML
    private Button createBtn;

    @FXML
    private TextField preparationFld;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == cancelBtn) {
            Helper.switchScene("/fxml/Profile.fxml", cancelBtn);
        }
        if (event.getSource() == createBtn) {
            XWPFDocument document = new XWPFDocument();

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            run.setText("DateTime: ");
            run.setText(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            run.addBreak();
            run.setText("Vet: ");
            try (Session session = HiberCfg.getSession()) {
                run.setText(Helper.getSQLString(session, session.createQuery("SELECT u.firstName FROM UserEntity u WHERE u.username like '" + Helper.getLogin() + "'")) + " ");
                run.setText(Helper.getSQLString(session, session.createQuery("SELECT u.secondName FROM UserEntity u WHERE u.username like '" + Helper.getLogin() + "'")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            XWPFParagraph paragraph2 = document.createParagraph();
            XWPFRun run2 = paragraph2.createRun();
            run2.setText("The specimen over which the inspection took place:");
            run2.addBreak();
            run2.setText(animalFld.getText());

            XWPFParagraph paragraph3 = document.createParagraph();
            XWPFRun run3 = paragraph3.createRun();
            run3.setText("Description of the health status of the animal:");
            run3.addBreak();
            run3.setText(descAnimalFld.getText());

            XWPFParagraph paragraph4 = document.createParagraph();
            XWPFRun run4 = paragraph4.createRun();
            run4.setText("Species of animals requiring attention:");
            run4.addBreak();
            run4.setText(diagnosisFld.getText());

            XWPFParagraph paragraph5 = document.createParagraph();
            XWPFRun run5 = paragraph5.createRun();
            run5.setText("Animal condition:");
            run5.addBreak();
            run5.setText(preparationFld.getText());

            XWPFParagraph paragraph6 = document.createParagraph();
            XWPFRun run6 = paragraph6.createRun();
            run6.setText("What was done in a day:");
            run6.addBreak();
            run6.setText(dayFld.getText());

            try (Session session = HiberCfg.getSession()) {
                FileOutputStream output = new FileOutputStream("Vet_" + new SimpleDateFormat("yyyy.MM.dd__HH.mm.ss").format(Calendar.getInstance().getTime()) + "_" + Helper.getSQLString(session, session.createQuery("SELECT u.firstName FROM UserEntity u WHERE u.username like '" + Helper.getLogin() + "'")) + "__" + Helper.getSQLString(session, session.createQuery("SELECT u.secondName FROM UserEntity u WHERE u.username like '" + Helper.getLogin() + "'")) + ".docx");
                document.write(output);
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {

    }
}
