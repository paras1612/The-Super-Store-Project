package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Profile_cnt{
    @FXML
    AnchorPane login;
    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void Login(ActionEvent e){
        System.out.println("Login");
    }
    public void SignUp(ActionEvent e) throws IOException {
        System.out.println("SignUp");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("sign_up.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("sign_up.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void help(ActionEvent e) throws IOException {
        System.out.println("Help");
        ((javafx.scene.Node) e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("help.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void about(ActionEvent e) throws IOException {
        System.out.println("About");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("about.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
