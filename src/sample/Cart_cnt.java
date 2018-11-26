package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainClasses.*;

import java.io.IOException;
import java.util.HashMap;

public class Cart_cnt {
    @FXML
    private ScrollPane dataPane;
    private Client client;
    private Warehouse_Admin warehouse_admin;
    private Store_Admin store_admin;
    private HashMap<String, Integer> selected= new HashMap<>();

    void setClient(Client client1){
        client=client1;
        setDisplayData();
    }

    public Client getClient() {
        return client;
    }

    public void setStore_admin(Store_Admin store_admin) {
        this.store_admin = store_admin;
        setDisplayData();
    }

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin) {
        this.warehouse_admin = warehouse_admin;
        setDisplayData();
    }

    @FXML
    AnchorPane login;
    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        if(warehouse_admin!=null) {
            ((javafx.scene.Node) e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Warehouse_home.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Warehouse_home_cnt cnt = loader.getController();
            System.out.println(warehouse_admin);
            cnt.setWarehouse_admin(warehouse_admin);
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else if(store_admin!=null){
            ((javafx.scene.Node) e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Store_home.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Store_home_cnt cnt = loader.getController();
            System.out.println(store_admin);
            cnt.setStore_admin(store_admin);
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else if(client!=null) {
            ((javafx.scene.Node) e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            AnchorPane root = loader.load();
            if (client != null) {
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
            cnt.setChooseStore(cnt.getChooseStore());
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    public void Login(ActionEvent e) throws IOException {
        System.out.println("Login");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        AnchorPane root = loader.load();
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
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("about.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addProduct(ActionEvent e) throws IOException{
        System.out.println("Add Product");
        Home(e);
    }

    public void setDisplayData(){
        Cart cart= new Cart();
        if(warehouse_admin!=null){
            cart=warehouse_admin.getAssigned_ware().getCart();
        }
        else if(store_admin!=null){
            cart=store_admin.getAssignedStore().getCart();
        }
        else if(client!=null){
            cart=client.getCart();
        }
        VBox vb = new VBox();
        for(Product product : cart.getCartList().keySet()){
            Button prodName = new Button(product.getName());
            TextField qty = new TextField(cart.getCartList().get(product).toString());
            qty.setPromptText("Enter Quantity");
            Label price = new Label(Double.toString(product.getPrice()));
            CheckBox chk = new CheckBox();
            chk.setText(product.getName());
            chk.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(chk.isSelected()){
                        selected.put(chk.getText(),Integer.parseInt(qty.getText()));
                    }
                    else {
                        selected.remove(product.getName());
                    }
                }
            });
            HBox hb = new HBox();
            hb.getChildren().addAll(prodName,qty,price,chk);
            vb.getChildren().add(hb);
        }
            dataPane.setContent(vb);
    }

    public void checkout(ActionEvent e){
        if(warehouse_admin!=null){
            warehouse_admin.getAssigned_ware().checkout();
        }
        else if(store_admin!=null){
            store_admin.getAssignedStore().checkout();
        }
        else if(client!=null){
            client.checkout();
        }
        setDisplayData();
    }


    public void deleteprod(ActionEvent actionEvent) {
        if(warehouse_admin!=null) {
            for (String name : selected.keySet()) {
                warehouse_admin.getAssigned_ware().delProdCart(warehouse_admin.getAssigned_ware().getUid(), name, selected.get(name));
            }
        }
        if(store_admin!=null){
            for (String name : selected.keySet()){
                store_admin.getAssignedStore().deleteProdCart(name);
            }
        }
        if(client!=null){
            for (String name: selected.keySet()){
                System.out.println("NOT IMPLEMENTED");
            }
        }
        setDisplayData();
        System.out.println("Add To Cart pressed");
    }
}


