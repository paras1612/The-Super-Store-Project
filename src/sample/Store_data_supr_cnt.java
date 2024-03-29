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
import mainClasses.Database;
import mainClasses.Super_usr;

import java.io.IOException;

import static sample.Main.serialize;

public class Store_data_supr_cnt {
    private Super_usr user;
    @FXML
    private TextField StoreName;
    @FXML private ComboBox storeList;
    @FXML private ComboBox warehouseList;
    @FXML private ComboBox delStoreList;
    public void setStoreList() {
        System.out.println(user);
        for(String name :Database.getDatabase().getStoreHashMap().keySet()){
            delStoreList.getItems().add(name);
            storeList.getItems().add(name);
        }
    }

    public void setWarehouseList() {
        for(String name: Database.getDatabase().getWarehouseHashMap().keySet()){
            this.warehouseList.getItems().add(name);
        }
    }

    public ComboBox getStoreList() {
        return storeList;
    }

    public ComboBox getWarehouseList() {
        return warehouseList;
    }

    public void setUser(Super_usr user) {
        this.user = user;
        setWarehouseList();
        setStoreList();
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
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("about.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void warehouseLink(ActionEvent e){
        Database.getDatabase().getStoreHashMap().get(storeList.getValue().toString()).setLinkedWarehouse(Database.getDatabase().getWarehouseHashMap().get(warehouseList.getValue().toString()));
        serialize();
        System.out.println("Warehouse Linked");
    }

    public void addStore(ActionEvent e){
        System.out.println(user);
        user.createStore(StoreName.getText());
        System.out.println("Store added");
        storeList.getItems().clear();
        delStoreList.getItems().clear();
        setStoreList();
    }

    public void removeStore(ActionEvent e){
        System.out.println("Store removed");
        user.removeStore(delStoreList.getValue().toString());
        storeList.getItems().clear();
        delStoreList.getItems().clear();
        setStoreList();
    }
}
