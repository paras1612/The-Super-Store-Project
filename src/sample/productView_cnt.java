package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML private ImageView image;
    private Client client;
    private String prod_Store;
    private String prod_ware;
    private Warehouse_Admin warehouse_admin;
    private Store_Admin store_admin;
    private Super_usr user;
    private String product;

    public void setUser(Super_usr user) {
        this.user = user;
    }

    public void setImage() throws IOException {
        imageSearch search = new imageSearch();
        String url = search.imgsearch(name.getText());
        System.out.println(url);
        //image = new ImageView(new Image(url));
        image.setImage(new Image(url));

    }

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin, String warehouse, String product) throws IOException {
        this.warehouse_admin = warehouse_admin;
        this.product= product;
        this.prod_ware= warehouse;
        Product temp = Database.getDatabase().getWarehouseHashMap().get(warehouse).getProductHashMap().get(product);
        setData(temp, warehouse);
    }

    private void setData(Product temp, String warehouse) throws IOException {
        if(client!=null){
            quantAvail.setText(Integer.toString(Database.getDatabase().getStoreHashMap().get(warehouse).getInventory().get(temp)));
        }
        if(warehouse_admin!=null) {
            quantAvail.setText(Integer.toString(Database.getDatabase().getWarehouseHashMap().get(warehouse).getInventory().get(temp)));
        }
        else if(store_admin!=null){
            quantAvail.setText(Integer.toString(Database.getDatabase().getStoreHashMap().get(warehouse).getInventory().get(temp)));
        }
        name.setText(temp.getName());
        price.setText(Double.toString(temp.getPrice()));
        price.setEditable(false);
        quantAvail.setEditable(false);
        setImage();
        //description.setText(temp.getDescription());
    }

    public void setStore_admin(Store_Admin store_admin, String warehouse, String product) throws IOException {
        this.store_admin = store_admin;
        this.product = product;
        this.prod_Store = warehouse;
        Product temp = new Product();
        for(Product temp1:  Database.getDatabase().getStoreHashMap().get(warehouse).getInventory().keySet()){
            if(temp1.getName().equals(product)){
                temp=temp1;
            }
        }
        setData(temp, warehouse);
    }

    void setClient(Client client1, String store, String product) throws IOException {
        client=client1;
        Product temp = new Product();
        for(Product product1: Database.getDatabase().getStoreHashMap().get(store).getInventory().keySet()){
            if(product1.getName().equals(product)){
                temp=product1;
                break;
            }
        }
        setData(temp, store);

    }

    public Client getClient() {
        return client;
    }

    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        AnchorPane root = loader.load();
        if(client!=null){
            loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            root = loader.load();
            Home_cnt cnt = loader.getController();
            cnt.setClient(client);
        }
        else if(store_admin!=null){
            loader = new FXMLLoader(getClass().getResource("Store_home.fxml"));
            root = loader.load();
            Store_home_cnt cnt = loader.getController();
            cnt.setStore_admin(store_admin);
        }
        else if(warehouse_admin!=null){
            loader = new FXMLLoader(getClass().getResource("Warehouse_home.fxml"));
            root = loader.load();
            Warehouse_home_cnt cnt = loader.getController();
            cnt.setWarehouse_admin(warehouse_admin);
        }
        else if (user!=null){
            loader = new FXMLLoader(getClass().getResource("Super_user.fxml"));
            root = loader.load();
            Super_user_cnt cnt = loader.getController();
            cnt.setUser(user);
        }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("help.fxml"));
        AnchorPane root = loader.load();
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
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void Cart_btn(ActionEvent e) throws IOException {
        System.out.println("Cart");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        AnchorPane root = loader.load();
        Cart_cnt cnt= loader.getController();
        Scene scene = new Scene(root);
        if(client!=null){
            cnt.setClient(client);
        }
        else if(store_admin!=null){
            cnt.setStore_admin(store_admin);
        }
        else if(warehouse_admin!=null){
            cnt.setWarehouse_admin(warehouse_admin);
        }
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void searchBtn(ActionEvent e){
        System.out.println("Search button pressed");
    }

    public void addToCart(ActionEvent actionEvent) {
        if(client!=null) {
            client.add_product(prod_Store ,product, Integer.parseInt(quant.getText()));
        }
        else if(warehouse_admin!=null){
            warehouse_admin.getAssigned_ware().add_product(prod_ware, product, Integer.parseInt(quant.getText()));
        }
        else if(store_admin!=null){
            store_admin.getAssignedStore().add_product(prod_ware, product, Integer.parseInt(quant.getText()));
        }
    }
}
