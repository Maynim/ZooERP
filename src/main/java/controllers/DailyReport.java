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

public class DailyReport {

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
    private Button cancelBtn;

    @FXML
    private TextField aviaryFld;

    @FXML
    private TextField descAviaryFld;

    @FXML
    private TextField animalFld;

    @FXML
    private TextField dayFld;

    @FXML
    private Button createBtn;

    @FXML
    private TextField descAnimalFld;

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
            run.setText("Keeper: ");
            try (Session session = HiberCfg.getSession()) {
                run.setText(Helper.getSQLString(session, session.createQuery("SELECT u.firstName FROM UserEntity u WHERE u.username like '" + Helper.getLogin() + "'")) + " ");
                run.setText(Helper.getSQLString(session, session.createQuery("SELECT u.secondName FROM UserEntity u WHERE u.username like '" + Helper.getLogin() + "'")));
            } catch (Exception e) {
                e.printStackTrace();
            }
            XWPFParagraph paragraph2 = document.createParagraph();
            XWPFRun run2 = paragraph2.createRun();
            run2.setText("Where the animals were observed:");
            run2.addBreak();
            run2.setText(aviaryFld.getText());

            XWPFParagraph paragraph3 = document.createParagraph();
            XWPFRun run3 = paragraph3.createRun();
            run3.setText("Aviary condition:");
            run3.addBreak();
            run3.setText(descAviaryFld.getText());

            XWPFParagraph paragraph4 = document.createParagraph();
            XWPFRun run4 = paragraph4.createRun();
            run4.setText("Species of animals requiring attention:");
            run4.addBreak();
            run4.setText(animalFld.getText());

            XWPFParagraph paragraph5 = document.createParagraph();
            XWPFRun run5 = paragraph5.createRun();
            run5.setText("Animal condition:");
            run5.addBreak();
            run5.setText(descAnimalFld.getText());


            XWPFParagraph paragraph6 = document.createParagraph();
            XWPFRun run6 = paragraph6.createRun();
            run6.setText("What was done in a day:");
            run6.addBreak();
            run6.setText(dayFld.getText());


            try (Session session = HiberCfg.getSession()) {
                FileOutputStream output = new FileOutputStream("Daily_" + new SimpleDateFormat("yyyy.MM.dd__HH.mm.ss").format(Calendar.getInstance().getTime()) + "_" + Helper.getSQLString(session, session.createQuery("SELECT u.firstName FROM UserEntity u WHERE u.username like '" + Helper.getLogin() + "'")) + "__" + Helper.getSQLString(session, session.createQuery("SELECT u.secondName FROM UserEntity u WHERE u.username like '" + Helper.getLogin() + "'")) + ".docx");
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
