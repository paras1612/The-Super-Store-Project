package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Super_usr;

import java.io.IOException;

public class Warehouse_data_supr_cnt {
    private Super_usr user;
    @FXML
    private TextField warehouseName;
    public void setUser(Super_usr user) {
        this.user = user;
    }

    public Super_usr getUser() {
        return user;
    }

    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Super_user.fxml"));
        AnchorPane root = loader.load();
        if(user!=null){
            Label name= new Label(user.getName());
            name.setLayoutX(15.0);
            name.setLayoutY(60.0);
            root.getChildren().add(name);
        }
        Scene scene = new Scene(root);
        Super_user_cnt cnt = loader.getController();
        cnt.setUser(user);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("about.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Super_user_cnt cnt = loader.getController();
        cnt.setUser(user);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void removeWarehouse(ActionEvent e){
        System.out.println("Remove warehouse pressed");
    }

    public void addWarehouse(ActionEvent e){
        user.updateDatabase();
        user.createWarehouse(warehouseName.getText());
        System.out.println("warehouse created");
    }

    public void linkStore(ActionEvent e){
        System.out.println("Link Store pressed");
    }
}
