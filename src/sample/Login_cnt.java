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
import mainClasses.*;

import java.io.IOException;

public class Login_cnt extends Main{
    //private Database database = deserialize();
    Database database = deserialize();
    @FXML
    AnchorPane loginPane;
    @FXML
    TextField user_fld;
    @FXML
    TextField pass_fld;

    @Override
    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

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
        System.out.println(database);
        System.out.println(database.getClientHashMap().toString());
        System.out.println("Login");
        if(database.login(user_fld.getText(),pass_fld.getText())==1){
            Client curr = database.getClientHashMap().get(user_fld.getText());
            System.out.println(database.getClientHashMap().toString());
            System.out.println(curr);
            ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            AnchorPane root = loader.load();
            Button profile = new Button(curr.getName());
            profile.setLayoutX(17.0);
            profile.setLayoutY(349.0);
            profile.setPrefHeight(26.0);
            profile.setPrefWidth(194.0);
            profile.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                    try {
                        AnchorPane root = loader.load();
                        Scene scene = new Scene(root);
                        Profile_cnt cnt=loader.getController();
                        System.out.println(cnt);
                        System.out.println(curr);
                        cnt.setClient(curr);
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
            Scene scene = new Scene(root);
            Home_cnt cnt = loader.getController();
            cnt.setClient(curr);
            cnt.setChooseStore(cnt.getChooseStore());
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else if(database.login(user_fld.getText(),pass_fld.getText())==2){
            Warehouse_Admin curr = database.getWarehouse_AdminHashMap().get(user_fld.getText());
            System.out.println(database.getWarehouse_AdminHashMap().toString());
            System.out.println(curr);
            System.out.println(curr.getAssigned_ware().getCategoryHashMap());
            ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Warehouse_home.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Warehouse_home_cnt cnt = loader.getController();
            cnt.setWarehouse_admin(curr);
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else if(database.login(user_fld.getText(),pass_fld.getText())==3){
            Store_Admin curr = database.getStore_AdminHashMap().get(user_fld.getText());
            System.out.println(database.getStore_AdminHashMap().toString());
            System.out.println(curr);
            ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Store_home.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Store_home_cnt cnt = loader.getController();
            cnt.setStore_admin(curr);
            cnt.setAddchoose(cnt.getAddchoose());
            cnt.setDelchoose(cnt.getDelchoose());
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else if(database.login(user_fld.getText(),pass_fld.getText())==4){
            Super_usr curr = database.getSuper_userHashMap().get(user_fld.getText());
            System.out.println(database.getClientHashMap().toString());
            System.out.println(curr);
            ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Super_user.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Super_user_cnt cnt = loader.getController();
            cnt.setUser(curr);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sign_up.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Sign_up_cnt cnt = loader.getController();
        cnt.setDatabase(database);
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
