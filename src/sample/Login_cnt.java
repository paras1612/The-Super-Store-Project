package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Database;

import java.io.IOException;

import static sample.Main.deserialize;

public class Login_cnt{
    private Database database = deserialize();
    @FXML
    AnchorPane loginPane;
    @FXML
    TextField user_fld;
    @FXML
    TextField pass_fld;
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
    public void Login(ActionEvent e) throws IOException {
        System.out.println("Login");
        if(database.login(user_fld.getText(), pass_fld.getText())){
            ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(getClass().getResource("Home.fxml"));
            Button profile = new Button(user_fld.getText());
            profile.setLayoutX(17.0);
            profile.setLayoutY(349.0);
            profile.setPrefHeight(26.0);
            profile.setPrefWidth(194.0);
            profile.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader();
                    try {
                        AnchorPane root = loader.load(getClass().getResource("Profile.fxml"));
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            root.getChildren().set(4, profile);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else {
            //((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Label warn = new Label("     Wrong Username or Password    ");
            AnchorPane root = new AnchorPane();
            root.getChildren().add(warn);
            Scene scene = new Scene(root, 200, 50);
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
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
