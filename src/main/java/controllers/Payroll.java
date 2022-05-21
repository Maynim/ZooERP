package controllers;

import entity.BudgetEntity;
import entity.TransactionEntity;
import entity.UserEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class Payroll {

    String usernameMem;
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
    private TableColumn<UserEntity, String> usernameCol;
    @FXML
    private TableColumn<UserEntity, String> firstCol;
    @FXML
    private TableColumn<UserEntity, String> secCol;
    @FXML
    private TableColumn<UserEntity, String> medCol;
    @FXML
    private TableColumn<UserEntity, String> genderCol;
    @FXML
    private TableColumn<UserEntity, Double> earnCol;
    @FXML
    private TableColumn<UserEntity, Date> lastPayCol;
    @FXML
    private TableColumn<UserEntity, String> jobCol;
    @FXML
    private Button payBtn;
    @FXML
    private TextField sumFld;
    @FXML
    private Label nameFld;
    @FXML
    private Button searchBtn;
    private ObservableList<UserEntity> listEntity;

    @FXML
    void handleButtonClicks(ActionEvent event) {
        Helper.bar(event, animalsBtn, budgetBtn, storageBtn, profileBtn);
        if (event.getSource() == payBtn) {
            try (Session session = HiberCfg.getSession()) { //add transaction
                TransactionEntity transaction = new TransactionEntity();
                transaction.setSummBefore(Helper.getSQLDouble(session, session.createQuery("SELECT b.balance FROM BudgetEntity b WHERE b.id like 3")));
                transaction.setSumm(Double.parseDouble("-" + sumFld.getText()));
                transaction.setUserUsername(Helper.getLogin());
                transaction.setBudgetIdbudget(3);
                transaction.setDescrip("Salary for " + Helper.getSQLString(session, session.createQuery("SELECT firstName FROM UserEntity WHERE username like '" + usernameMem + "'")) + " " + Helper.getSQLString(session, session.createQuery("SELECT secondName FROM UserEntity WHERE username like '" + usernameMem + "'")));
                session.beginTransaction();
                session.save(transaction);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
            try (Session session = HiberCfg.getSession()) { //changing BudgetEntity
                BudgetEntity budgetEntity = session.get(BudgetEntity.class, 3);
                budgetEntity.setBalance(Helper.getSQLDouble(session, session.createQuery
                        ("SELECT b.balance From BudgetEntity b WHERE idbudget like 3"))
                        + Double.parseDouble("-" + sumFld.getText()));
                session.beginTransaction();
                session.save(budgetEntity);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
            try (Session session = HiberCfg.getSession()) { //changing BudgetEntity
                UserEntity user = session.get(UserEntity.class, usernameMem);
                user.setMoneyEarned(user.getMoneyEarned() - Double.parseDouble(sumFld.getText()));
                user.setLastPayout(new Date());
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
            try (Session session = HiberCfg.getSession()) {
                session.beginTransaction();
                Query query = session.createQuery("FROM UserEntity");
                List<UserEntity> list;
                list = (List<UserEntity>) query.list();
                session.getTransaction().commit();
                listEntity = FXCollections.observableArrayList(list);
                mainTab.setItems(listEntity);
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        firstCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        secCol.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        medCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        earnCol.setCellValueFactory(new PropertyValueFactory<>("moneyEarned"));
        lastPayCol.setCellValueFactory(new PropertyValueFactory<>("lastPayout"));
        jobCol.setCellValueFactory(new PropertyValueFactory<>("jobName"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM UserEntity");
            List<UserEntity> list;
            list = (List<UserEntity>) query.list();
            session.getTransaction().commit();
            listEntity = FXCollections.observableArrayList(list);
            mainTab.setItems(listEntity);
        } catch (Throwable cause) {
            cause.printStackTrace();
        }
        mainTab.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                int index = mainTab.getSelectionModel().getSelectedIndex();
                usernameMem = usernameCol.getCellData(index);
                sumFld.setText(String.valueOf(earnCol.getCellData(index)));
                try (Session session = HiberCfg.getSession()) {
                    nameFld.setText(Helper.getSQLString(session, session.createQuery("SELECT firstName FROM UserEntity WHERE username like '" + usernameMem + "'")) + " " + Helper.getSQLString(session, session.createQuery("SELECT secondName FROM UserEntity WHERE username like '" + usernameMem + "'")));
                }
            }
        });
    }
}
