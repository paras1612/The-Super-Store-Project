package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Product;
import mainClasses.Store_Admin;
import mainClasses.Warehouse;

import java.io.IOException;

public class Store_home_cnt{
    private Store_Admin store_admin;

    public void setStore_admin(Store_Admin store_admin) {
        this.store_admin = store_admin;
    }

    public Store_Admin getStore_admin() {
        return store_admin;
    }

    @FXML private ComboBox addchoose;
    @FXML private ComboBox delchoose;

    public void setAddchoose(ComboBox addchoose) {
        System.out.println(store_admin);
        if(store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap()!=null){
            for(String name: store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap().keySet()){
                this.addchoose.getItems().add(name);
            }
        }
    }

    public void setDelchoose(ComboBox delChoose) {
        if(store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap()!=null) {
            for (String name : store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap().keySet()) {
                this.delchoose = delchoose;
            }
        }
    }

    public ComboBox getAddchoose() {
        return addchoose;
    }

    public ComboBox getDelchoose() {
        return delchoose;
    }

    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Store_home.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Store_home_cnt cnt = loader.getController();
        cnt.setStore_admin(store_admin);
        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void Login(ActionEvent e){
        System.out.println("Login");
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
    public void message(ActionEvent e){
        System.out.println("Message Button pressed");
    }

    public void orders(ActionEvent e){
        System.out.println("Orders pressed");
    }
    public void addProduct(ActionEvent e){
        Warehouse linkware= store_admin.getAssignedStore().getLinkedWarehouse();
        Product curr=linkware.getProductHashMap().get(addchoose.getValue().toString());
        Product one = new Product(curr.getName(),curr.getPrice(),curr.getQuantity(),curr.getfCostQuater(),curr.getcCostQuater(),curr.getItemDemand());
        one.setParent(curr.getParent());
        if(linkware.getProductHashMap().get(addchoose.getValue().toString()).getQuantity()>curr.getEOQ()){
            store_admin.getAssignedStore().getInventory().put(one,(int)curr.getEOQ());
            curr.setQuantity(curr.getQuantity()-(int)curr.getEOQ());
        }
        else {
            System.out.println("Product quantity not available");
        }
        System.out.println("Product added");
    }
    public void deleteProduct(ActionEvent e){
        System.out.println("Product Deleted");
    }

    public void search(ActionEvent e){
        System.out.println("Search pressed");
    }

}
