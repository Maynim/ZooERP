package logic;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.List;

public class Helper {

    private static String login;

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Helper.login = login;
    }

    public static void switchScene(String fxml, Button btn) {  //переключение сцен
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Helper.class.getResource(fxml));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void newScene(String fxml, Button btn) throws IOException {
        //переключение сцен
//        Stage stage = new Stage();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(Helper.class.getResource(fxml));
//        try {
//            loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Parent root = loader.getRoot();
//        stage.setScene(new Scene(root));
//        stage.show();
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(Helper.class.getResource(fxml));
//        stage.setTitle("Hello World");
//        stage.setScene(new Scene(root));
//        stage.show();

    }

    public static String getSQLString(Session session, Query query) {  //переключение сцен
        session.beginTransaction();
        List list = query.list();
        session.getTransaction().commit();
        return String.valueOf(list.get(0));
    }

    public static Integer getSQLInteger(Session session, Query query) {  //переключение сцен
        session.beginTransaction();
        List list = query.list();
        session.getTransaction().commit();
        return (Integer) list.get(0);
    }

    public static String getSQLResult(Session session, Query query) {  //переключение сцен
        session.beginTransaction();
        List list = query.list();
        session.getTransaction().commit();
        return list.get(0).toString();
    }

    public static Double getSQLDouble(Session session, Query query) {  //переключение сцен
        session.beginTransaction();
        List list = query.list();
        session.getTransaction().commit();
        return (Double) list.get(0);
    }

    public static void bar(ActionEvent mouseEvent, Button animalsBtn, Button budgetBtn, Button storageBtn, Button profileBtn) {
        if (mouseEvent.getSource() == animalsBtn) {
            switchScene("/fxml/Animals.fxml", animalsBtn);
        }

        if (mouseEvent.getSource() == budgetBtn) {
            switchScene("/fxml/Budget.fxml", budgetBtn);
        }

        if (mouseEvent.getSource() == storageBtn) {
            switchScene("/fxml/Storage.fxml", storageBtn);
        }

        if (mouseEvent.getSource() == profileBtn) {
            switchScene("/fxml/Profile.fxml", profileBtn);
        }


    }
}
