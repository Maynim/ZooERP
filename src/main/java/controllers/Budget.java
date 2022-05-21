package controllers;

import entity.BudgetEntity;
import entity.TransactionEntity;
import hibercfg.HiberCfg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;


public class Budget {

    @FXML
    private Button animalsBtn;

    @FXML
    private Button profileBtn;

    @FXML
    private Button budgetBtn;

    @FXML
    private Button storageBtn;

    @FXML
    private TableView<TransactionEntity> mainTable;

    @FXML
    private TableColumn<TransactionEntity, Integer> idCol;

    @FXML
    private TableColumn<TransactionEntity, Double> sumCol;

    @FXML
    private TableColumn<TransactionEntity, String> userCol;

    @FXML
    private TableColumn<TransactionEntity, Date> dateCol;

    @FXML
    private TableColumn<TransactionEntity, Double> oldSumCol;

    @FXML
    private TableColumn<TransactionEntity, String> descripCol;

    @FXML
    private Label currentLbl;

    @FXML
    private Label changesLbl;

    @FXML
    private Button statisticBtn;

    @FXML
    private Button addBtn;

    @FXML
    private TextField sumField;

    @FXML
    private TextField descField;


    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private Button searchBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button payrollBtn;




    private ObservableList<TransactionEntity> listEntity;
    private int index;


    @FXML
    void handleButtonClicks(ActionEvent mouseEvent) {
        Helper.bar(mouseEvent, animalsBtn, budgetBtn, storageBtn, profileBtn);

        if (mouseEvent.getSource() == addBtn) {
            try (Session session = HiberCfg.getSession()) { //add transaction
                TransactionEntity transaction = new TransactionEntity();
                transaction.setSummBefore(Helper.getSQLDouble(session, session.createQuery("SELECT b.balance FROM BudgetEntity b WHERE b.id like 3")));
                transaction.setSumm(Double.parseDouble(sumField.getText()));
                transaction.setUserUsername(Helper.getLogin());
                transaction.setBudgetIdbudget(3);
                transaction.setDescrip(descField.getText());
                session.save(transaction);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }
            try (Session session = HiberCfg.getSession()) { //changing BudgetEntity
                BudgetEntity budgetEntity = session.get(BudgetEntity.class, 3);
                budgetEntity.setBalance(Helper.getSQLDouble(session, session.createQuery
                        ("SELECT b.balance From BudgetEntity b WHERE idbudget like 3"))
                        + Double.parseDouble(sumField.getText()));
                session.save(budgetEntity);
                session.getTransaction().commit();
            } catch (Throwable cause) {
                cause.printStackTrace();
            }

            try (Session session = HiberCfg.getSession()) {
                session.beginTransaction();
                List<TransactionEntity> list = session.createQuery("from TransactionEntity ORDER BY datetime DESC").getResultList();
                listEntity = FXCollections.observableArrayList(list);

                mainTable.setItems(listEntity);
            }

            try (Session session = HiberCfg.getSession()) {
                currentLbl.setText(Helper.getSQLResult(session, session.createQuery
                        ("SELECT b.balance From BudgetEntity b WHERE name like 'main'")));

            }
        }
        if (mouseEvent.getSource() == statisticBtn) {

        }
        if (mouseEvent.getSource() == searchBtn) {
            try (Session session = HiberCfg.getSession()) {
                String str = fromDate.getValue().toString();
                System.out.println(str);
                session.beginTransaction();
//              Query query2 = session.createQuery("FROM TransactionEntity t WHERE DATE(t.datetime) BETWEEN DATE("+"'"+fromDate.getValue().toString()+"'"+") AND MONTH(t.datetime)=MONTH("+"'"+fromDate.getValue().toString()+"'"+") AND YEAR(t.datetime)=YEAR("+"'"+fromDate.getValue().toString()+"'"+")");
                Query query2 = session.createQuery("FROM TransactionEntity t WHERE DATE(t.datetime) BETWEEN DATE(" + "'" + fromDate.getValue().toString() + "'" + ") AND DATE(" + "'" + toDate.getValue().toString() + "'" + ") ORDER BY datetime DESC");
                List<TransactionEntity> list = (List<TransactionEntity>) query2.list();
                System.out.println(fromDate.getValue().toString());
                session.getTransaction().commit();
                listEntity = FXCollections.observableArrayList(list);
                mainTable.setItems(listEntity);
            }
        }
        if (mouseEvent.getSource() == clearBtn) {
            try (Session session = HiberCfg.getSession()) {
                String str = fromDate.getValue().toString();
                System.out.println(str);
                session.beginTransaction();
//              Query query2 = session.createQuery("FROM TransactionEntity t WHERE DATE(t.datetime) BETWEEN DATE("+"'"+fromDate.getValue().toString()+"'"+") AND MONTH(t.datetime)=MONTH("+"'"+fromDate.getValue().toString()+"'"+") AND YEAR(t.datetime)=YEAR("+"'"+fromDate.getValue().toString()+"'"+")");
                Query query2 = session.createQuery("FROM TransactionEntity ORDER BY datetime DESC");
                List<TransactionEntity> list = (List<TransactionEntity>) query2.list();
                session.getTransaction().commit();
                listEntity = FXCollections.observableArrayList(list);
                mainTable.setItems(listEntity);
                fromDate.setValue(null);
                toDate.setValue(null);
            }
        }
        if (mouseEvent.getSource() == payrollBtn) {
            Helper.switchScene("/fxml/Payroll.fxml", profileBtn);
        }
    }


    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idtransaction"));
        sumCol.setCellValueFactory(new PropertyValueFactory<>("summ"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("userUsername"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("datetime"));
        oldSumCol.setCellValueFactory(new PropertyValueFactory<>("summBefore"));
        descripCol.setCellValueFactory(new PropertyValueFactory<>("descrip"));

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();
            Query query = session.createQuery("SELECT sum(t.summ) FROM TransactionEntity t WHERE MONTH(t.datetime)=MONTH(NOW()) AND YEAR(t.datetime)=YEAR(NOW())");
            changesLbl.setText(query.list().get(0).toString());
        }

        try (Session session = HiberCfg.getSession()) {
            currentLbl.setText(Helper.getSQLResult(session, session.createQuery
                    ("SELECT b.balance From BudgetEntity b WHERE name like 'main'")));

        } catch (Throwable cause) {
            cause.printStackTrace();
        }

        try (Session session = HiberCfg.getSession()) {
            session.beginTransaction();

            try {
                Query query = session.createQuery("FROM TransactionEntity t ORDER BY datetime DESC ");
                List<TransactionEntity> list = (List<TransactionEntity>) query.list();
                session.getTransaction().commit();
                listEntity = FXCollections.observableArrayList(list);
                mainTable.setItems(listEntity);

            } catch (Throwable cause) {
                cause.printStackTrace();
            }

        } catch (Throwable cause) {
            cause.printStackTrace();
        }

        mainTable.setOnMousePressed(event -> {
            if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                System.out.println("Double!!!");
                index = mainTable.getSelectionModel().getSelectedIndex();
                System.out.println(idCol.getCellData(index).toString());
            }
        });
    }


    public void getSelect(MouseEvent mouseEvent) {
    }
}
