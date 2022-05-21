package controllers;

import hibercfg.HiberCfg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.Helper;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class Authorization {


    private static String job;
    private static int access;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button loginBtn;
    private String loginCheck = null;
    private String passCheck = null;

    public static int getAccess() {
        return access;
    }

    public static String getJob() {
        return job;
    }


    @FXML
    void initialize() {

    }


    @FXML
    void handleButtonClicks(ActionEvent mouseEvent) {
        List list;

        if (mouseEvent.getSource() == loginBtn) {
            String login = loginField.getText().trim();
            String password = passField.getText();
            try (Session session = HiberCfg.getSession()) {
                session.beginTransaction();
                try {
                    Query query = session.createQuery
                            ("SELECT u.username From UserEntity u WHERE username like '" + login + "'");
                    list = query.list();
                    session.getTransaction().commit();
                    loginCheck = (String) list.get(0);
                    Helper.setLogin(loginCheck);
                } catch (Throwable ignored) {
                    System.out.println("Ошибка");
                }
                session.beginTransaction();
                try {
                    Query query = session.createQuery
                            ("SELECT u.password From UserEntity u WHERE username like '" + login + "'");
                    list = query.list();
                    session.getTransaction().commit();
                    passCheck = (String) list.get(0);
                } catch (Throwable ignored) {
                    System.out.println("Ошибка");
                }
                if (login.equals(loginCheck) && password.equals(passCheck)) {
                    System.out.println("Success");
                    Helper.switchScene("/fxml/MainMenu.fxml", loginBtn);
                    try {
                        session.beginTransaction();
                        Query query = session.createQuery
                                ("SELECT u.jobName From UserEntity u WHERE username like '" + loginCheck + "'");
                        list = query.list();
                        session.getTransaction().commit();
                        job = (String) list.get(0);
                    } catch (Throwable cause) {
                        cause.printStackTrace();
                    }
                } else
                    System.out.println("Имя пользователя или пароль введены не верно");
            } catch (Throwable cause) {
                cause.printStackTrace();
            }

        }

    }

}
