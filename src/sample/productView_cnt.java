package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.*;

import java.awt.*;
import java.io.IOException;

public class productView_cnt {
    @FXML private Label name;
    @FXML private TextField price;
    @FXML private TextField quantAvail;
    @FXML private TextField quant;
    @FXML private TextArea description;

    private Client client;
    private Warehouse_Admin warehouse_admin;
    private Store_Admin store_admin;
    private Super_usr user;
    private String product;

    public void setUser(Super_usr user) {
        this.user = user;
    }

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin, String warehouse, String product) {
        this.warehouse_admin = warehouse_admin;
        Product temp = Database.getDatabase().getWarehouseHashMap().get(warehouse).getProductHashMap().get(product);
        setData(temp, warehouse);
    }

    private void setData(Product temp, String warehouse) {
        name.setText(temp.getName());
        price.setText(Double.toString(temp.getPrice()));
        quantAvail.setText(Integer.toString(Database.getDatabase().getWarehouseHashMap().get(warehouse).getInventory().get(temp)));
        price.setEditable(false);
        quantAvail.setEditable(false);
        //description.setText(temp.getDescription());
    }

    public void setStore_admin(Store_Admin store_admin, String warehouse, String product) {
        this.store_admin = store_admin;
        Product temp = Database.getDatabase().getWarehouseHashMap().get(warehouse).getProductHashMap().get(product);
        setData(temp, warehouse);
    }

    void setClient(Client client1){
        client=client1;
    }

    public Client getClient() {
        return client;
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
    public void Cart_btn(ActionEvent e) throws IOException {
        System.out.println("Cart");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("Cart.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void searchBtn(ActionEvent e){
        System.out.println("Search button pressed");
    }
}
