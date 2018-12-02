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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        AnchorPane root = loader.load();
        Home_cnt cnt = loader.getController();
        cnt.setClient(new Client("Guest", "pass", "guest"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void Login(ActionEvent e) throws IOException {
        System.out.println(Database.getDatabase());
        System.out.println(Database.getDatabase().getClientHashMap().toString());
        System.out.println("Login");
        if(Database.getDatabase().login(user_fld.getText(),pass_fld.getText())==1){
            Client curr = Database.getDatabase().getClientHashMap().get(user_fld.getText());
            System.out.println(Database.getDatabase().getClientHashMap().toString());
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
            profile.setOnAction(new EventHandler<>() {
                @Override
                public void handle(ActionEvent event) {
                    ((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
                    try {
                        AnchorPane root = loader.load();
                        Scene scene = new Scene(root);
                        Profile_cnt cnt = loader.getController();
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
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else if(Database.getDatabase().login(user_fld.getText(),pass_fld.getText())==2){
            Warehouse_Admin curr = Database.getDatabase().getWarehouse_AdminHashMap().get(user_fld.getText());
            System.out.println(Database.getDatabase().getWarehouse_AdminHashMap().toString());
            System.out.println(curr);
            System.out.println(curr.getAssigned_ware().getCategoryHashMap());
            //((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Warehouse_home.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Warehouse_home_cnt cnt = loader.getController();
            cnt.setWarehouse_admin(curr);
            cnt.setPop();
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.toBack();
        }
        else if(Database.getDatabase().login(user_fld.getText(),pass_fld.getText())==3){
            Store_Admin curr = Database.getDatabase().getStore_AdminHashMap().get(user_fld.getText());
            System.out.println(Database.getDatabase().getStore_AdminHashMap().toString());
            System.out.println(curr);
            //((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Store_home.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Store_home_cnt cnt = loader.getController();
            cnt.setStore_admin(curr);
            cnt.setPop();
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.toBack();
        }
        else if(Database.getDatabase().login(user_fld.getText(),pass_fld.getText())==4){
            Super_usr curr = Database.getDatabase().getSuper_userHashMap().get(user_fld.getText());
            System.out.println(Database.getDatabase().getClientHashMap().toString());
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
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
