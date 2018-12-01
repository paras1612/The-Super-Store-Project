package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Database;
import mainClasses.Product;
import mainClasses.Store_Admin;
import mainClasses.Warehouse_Admin;

import java.io.IOException;

import static sample.Main.serialize;

public class Product_mod_cnt{
    //private Database database = deserialize();
    private Store_Admin store_admin;
    private Warehouse_Admin warehouse_admin;
    @FXML private ComboBox ProdCat;
    @FXML private TextField nameTxt;
    @FXML private TextField priceTxt;
    @FXML private TextField qtyTxt;
    @FXML private TextField dTxt;
    @FXML private TextField kTxt;
    @FXML private TextField hTxt;
    @FXML private ComboBox category;
    @FXML private AnchorPane pane;
    @FXML private ComboBox prodList = new ComboBox();
    @FXML private ComboBox updateProd = new ComboBox();
    //@FXML private ComboBox catList = new ComboBox();
    public ComboBox getCategory() {
        return category;
    }

    public ComboBox getProdCat() {
        return ProdCat;
    }

    public void setCategory() {
        if(warehouse_admin!=null) {
            System.out.println(warehouse_admin.getAssigned_ware().getCategoryHashMap().toString());
            for (String name : warehouse_admin.getAssigned_ware().getCategoryHashMap().keySet()) {
                category.getItems().add(name);
            }
            for(Product product : warehouse_admin.getAssigned_ware().getInventory().keySet()){
                updateProd.getItems().add(product.getName());
            }
        }
        else if(store_admin!=null){
            for (String name : store_admin.getAssignedStore().getCategoriesList().keySet()) {
                category.getItems().add(name);
            }
            for (Product product: store_admin.getAssignedStore().getInventory().keySet()){
                updateProd.getItems().add(product);
            }
        }
    }

    public void setProdCat() {
        ProdCat.getItems().addAll("Product", "Category");
        setCategory();
    }

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin) {
        this.warehouse_admin = warehouse_admin;
        setProdCat();
    }

    public Warehouse_Admin getWarehouse_admin() {
        return warehouse_admin;
    }

    public void setStore_admin(Store_Admin store_admin) {
        this.store_admin = store_admin;
        prodList.setPromptText("Choose Product");
        if(store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap()!=null){
            for(String name: store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap().keySet()){
                this.prodList.getItems().add(name);
            }
        }
        prodList.setLayoutX(priceTxt.getLayoutX());
        prodList.setLayoutY(priceTxt.getLayoutY());
        prodList.setPrefHeight(priceTxt.getPrefHeight());
        prodList.setPrefWidth(priceTxt.getPrefWidth());
        pane.getChildren().set(2,prodList);
        pane.getChildren().get(1).setVisible(false);
        pane.getChildren().get(3).setVisible(false);
        pane.getChildren().get(5).setVisible(false);
        pane.getChildren().get(6).setVisible(false);
        pane.getChildren().get(7).setVisible(false);
        setProdCat();
    }

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
        if(warehouse_admin!=null) {
            ((javafx.scene.Node) e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Product_mod.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Product_mod_cnt cnt = loader.getController();
            cnt.setWarehouse_admin(warehouse_admin);
            if (ProdCat.getValue().toString().equals("Category")) {
                cnt.priceTxt.setDisable(true);
                cnt.qtyTxt.setDisable(true);
                cnt.dTxt.setDisable(true);
                cnt.hTxt.setDisable(true);
                cnt.kTxt.setDisable(true);
                updateProd.setDisable(true);
            }
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        else if(store_admin!=null){
            ((javafx.scene.Node) e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Product_mod.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            Product_mod_cnt cnt = loader.getController();
            cnt.setStore_admin(store_admin);
            if (ProdCat.getValue().toString().equals("Category")) {
                cnt.nameTxt.setVisible(true);
                cnt.nameTxt.setDisable(false);
                cnt.pane.getChildren().get(2).setVisible(false);
                cnt.priceTxt.setDisable(true);
                cnt.qtyTxt.setDisable(true);
                cnt.dTxt.setDisable(true);
                cnt.hTxt.setDisable(true);
                cnt.kTxt.setDisable(true);
            }
            scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    public void set(ActionEvent e)
    {
        if(warehouse_admin!=null) {
            if (priceTxt.isDisable()) {
                if(warehouse_admin.getAssigned_ware().addCategory(warehouse_admin, nameTxt.getText(), category.getValue().toString())){
                    category.getItems().add(nameTxt.getText());
                }

                System.out.println("serialized");
                //Add Category
            } else if (!priceTxt.isDisable()) {
                if(category.isDisable()){
                    if(warehouse_admin.getAssigned_ware().getProductHashMap().containsKey(nameTxt.getText())){
                        Product temp = warehouse_admin.getAssigned_ware().getProductHashMap().get(nameTxt.getText());
                        temp.setPrice(Double.parseDouble(priceTxt.getText()));
                        temp.setQuantity(Integer.parseInt(qtyTxt.getText()));
                        temp.setfCostQuater(Double.parseDouble(dTxt.getText()));
                        temp.setcCostQuater(Double.parseDouble(hTxt.getText()));
                        temp.setItemDemand(Double.parseDouble(kTxt.getText()));
                        serialize();
                    }
                }
                else if(warehouse_admin.getAssigned_ware().addProduct(warehouse_admin, nameTxt.getText(), Double.parseDouble(priceTxt.getText()), Integer.parseInt(qtyTxt.getText()), Double.parseDouble(dTxt.getText()), Double.parseDouble(hTxt.getText()), Double.parseDouble(kTxt.getText()), category.getValue().toString())){
                }
                System.out.println("serialized");
                //Add Product
            }
            System.out.println("Warehouse Set pressed");
        }
        else if(store_admin!=null){

            if (nameTxt.isVisible()) {
                if(store_admin.getAssignedStore().addCategory(store_admin, nameTxt.getText(), category.getValue().toString())) {
                    category.getItems().add(nameTxt.getText());
                }
                System.out.println("serialized");
                //Add Category
            } else if (!nameTxt.isVisible()) {
                if(store_admin.getAssignedStore().addProduct(prodList.getValue().toString(), category.getValue().toString())){
                }
                System.out.println("serialized");
                //Add Product
            }
            System.out.println("Store Set pressed");
        }
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

    public void updateProduct(ActionEvent actionEvent) {
        nameTxt.setText(updateProd.getValue().toString());
        nameTxt.setEditable(false);
        category.setDisable(true);
        priceTxt.setDisable(false);
        qtyTxt.setDisable(false);
        hTxt.setDisable(false);
        kTxt.setDisable(false);
        dTxt.setDisable(false);
    }
}
