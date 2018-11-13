package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Client;

import java.io.IOException;

public class Cart_cnt {
    private Client client;

    void setClient(Client client1){
        client=client1;
    }

    public Client getClient() {
        return client;
    }

    @FXML
    AnchorPane login;
    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        AnchorPane root = loader.load();
        if(client!=null) {
            javafx.scene.control.Button profile = new Button(client.getName());
            profile.setLayoutX(17.0);
            profile.setLayoutY(349.0);
            profile.setPrefHeight(26.0);
            profile.setPrefWidth(194.0);
            profile.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ((javafx.scene.Node) e.getSource()).getScene().getWindow().hide();
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                    try {
                        AnchorPane root = loader.load();
                        Scene scene = new Scene(root);
                        Profile_cnt cnt = loader.getController();
                        System.out.println(cnt);
                        System.out.println(client);
                        cnt.setClient(client);
                        System.out.println(cnt.getClient().getName());
                        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            root.getChildren().set(4, profile);
        }
        Scene scene = new Scene(root);
        Home_cnt cnt = loader.getController();
        cnt.setClient(client);
        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void Login(ActionEvent e) throws IOException {
        System.out.println("Login");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
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

    public void addProduct(ActionEvent e) throws IOException{
        System.out.println("Add Product");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
