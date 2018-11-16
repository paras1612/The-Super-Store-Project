package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Super_usr;

import java.io.IOException;

import static sample.Main.serialize;

public class Warehouse_data_supr_cnt {
    private Super_usr user;
    @FXML private TextField warehouseName;
    @FXML private ComboBox storeList;
    @FXML private ComboBox warehouseList;
    @FXML private ComboBox delwarehouseList;

    public void setUser(Super_usr user) {
        this.user = user;
        setStoreList();
        setWarehouseList();
    }

    public Super_usr getUser() {
        return user;
    }

    public void setStoreList() {
        System.out.println(user);
        for(String name :user.getDatabase().getStoreHashMap().keySet()){
            storeList.getItems().add(name);
        }
    }

    public void setWarehouseList() {
        for(String name: user.getDatabase().getWarehouseHashMap().keySet()){
            this.warehouseList.getItems().add(name);
            delwarehouseList.getItems().add(name);
        }
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
        user.getDatabase().getWarehouseHashMap().remove(delwarehouseList.getValue().toString());
        serialize(user.getDatabase());
        System.out.println("Remove warehouse pressed");
    }

    public void addWarehouse(ActionEvent e){
        user.updateDatabase();
        user.createWarehouse(warehouseName.getText());
        System.out.println("warehouse created");
    }

    public void linkStore(ActionEvent e){
        user.getDatabase().getStoreHashMap().get(storeList.getValue().toString()).setLinkedWarehouse(user.getDatabase().getWarehouseHashMap().get(warehouseList.getValue().toString()));
        serialize(user.getDatabase());
        System.out.println("Warehouse Linked");
        System.out.println("Link Store pressed");
    }
}
