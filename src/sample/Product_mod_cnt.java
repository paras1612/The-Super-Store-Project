package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Warehouse_Admin;

import java.io.IOException;

public class Product_mod_cnt{
    //private Database database = deserialize();
    private Warehouse_Admin warehouse_admin;
    @FXML
    private ComboBox ProdCat;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField qtyTxt;
    @FXML
    private TextField dTxt;
    @FXML
    private TextField kTxt;
    @FXML
    private TextField hTxt;
    @FXML
    private ComboBox category;

    public ComboBox getCategory() {
        return category;
    }

    public ComboBox getProdCat() {
        return ProdCat;
    }

    public void setCategory(ComboBox Category) {
        System.out.println(warehouse_admin.getAssigned_ware().getCategoryHashMap().toString());
        for(String name: warehouse_admin.getAssigned_ware().getCategoryHashMap().keySet()){
            category.getItems().add(name);
        }
    }

    public void setProdCat(ComboBox prodCat) {
        ProdCat.getItems().addAll("Product", "Category");
    }

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin) {
        this.warehouse_admin = warehouse_admin;
    }

    public Warehouse_Admin getWarehouse_admin() {
        return warehouse_admin;
    }
    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Warehouse_home.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Warehouse_home_cnt cnt= loader.getController();
        System.out.println(warehouse_admin);
        cnt.setWarehouse_admin(warehouse_admin);
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

    public void changeType(ActionEvent e) throws IOException {
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Product_mod.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Product_mod_cnt cnt= loader.getController();
        cnt.setWarehouse_admin(warehouse_admin);
        cnt.setProdCat(ProdCat);
        if(ProdCat.getValue().toString().equals("Category")){
            cnt.priceTxt.setDisable(true);
            cnt.qtyTxt.setDisable(true);
            cnt.dTxt.setDisable(true);
            cnt.hTxt.setDisable(true);
            cnt.kTxt.setDisable(true);
        }
        cnt.setCategory(category);
        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void set(ActionEvent e)
    {
        System.out.println(warehouse_admin.getAssigned_ware());
        if(priceTxt.isDisable()){
          //  serialize(warehouse_admin.getAssigned_ware().getDatabase());
            warehouse_admin.getAssigned_ware().addCategory(warehouse_admin, nameTxt.getText(), category.getValue().toString());
//            serialize(warehouse_admin.getAssigned_ware().getDatabase());
            System.out.println("serialized");
            //Add Category
        }
        else if(!priceTxt.isDisable()){
            warehouse_admin.getAssigned_ware().addProduct(warehouse_admin, nameTxt.getText(), Double.parseDouble(priceTxt.getText()), Integer.parseInt(qtyTxt.getText()), Double.parseDouble(dTxt.getText()), Double.parseDouble(hTxt.getText()), Double.parseDouble(kTxt.getText()), category.getValue().toString());
            //serialize(warehouse_admin.getAssigned_ware().getDatabase());
            System.out.println("serialized");
            //Add Product
        }
        System.out.println("Set pressed");
    }

    public TextField getPriceTxt() {
        return priceTxt;
    }

    public TextField getQtyTxt() {
        return qtyTxt;
    }

    public TextField getdTxt() {
        return dTxt;
    }

    public TextField gethTxt() {
        return hTxt;
    }

    public TextField getkTxt() {
        return kTxt;
    }
}
