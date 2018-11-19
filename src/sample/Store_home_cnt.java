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
import mainClasses.Categories;
import mainClasses.Product;
import mainClasses.Store_Admin;
import mainClasses.Warehouse;

import java.io.IOException;
import java.util.HashMap;

public class Store_home_cnt{
    @FXML private ComboBox prod_cat;
    @FXML private ComboBox sort_menu;
    @FXML private ComboBox delchoose;
    @FXML private ScrollPane catPane;
    @FXML private ScrollPane prodPane;

    private Store_Admin store_admin;
    private HashMap<String, Integer> selected = new HashMap<>();
    public void setStore_admin(Store_Admin store_admin) {
        this.store_admin = store_admin;
        prod_cat.getItems().addAll("Product","Category");
        sort_menu.getItems().addAll("Name");
        setDelchoose(delchoose);
        setData("Main");
    }

    public Store_Admin getStore_admin() {
        return store_admin;
    }



    public void setDelchoose(ComboBox delChoose) {
        if(store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap()!=null) {
            for (String name : store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap().keySet()) {
                this.delchoose.getItems().add(name);
            }
        }
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Cart_cnt cnt = loader.getController();
        cnt.setStore_admin(store_admin);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void message(ActionEvent e) throws IOException {
        System.out.println("Message Button pressed");
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Message_cnt cnt =loader.getController();
        cnt.text.setText(store_admin.getAssignedStore().getMessage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void orders(ActionEvent e){
        System.out.println("Orders pressed");
    }
    public void addProduct(ActionEvent e) throws IOException {
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Product_mod.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Product_mod_cnt cnt = loader.getController();
        cnt.setStore_admin(store_admin);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
 //       store_admin.getAssignedStore().addProduct(store_admin,addchoose.getValue().toString());
        System.out.println("Product added");
    }
    public void deleteProduct(ActionEvent e){
        store_admin.getAssignedStore().deleteProduct(delchoose.getValue().toString());
        System.out.println("Product Deleted");
    }

    public void search(ActionEvent e){
        System.out.println("Search pressed");
    }

    public void setData(String catChosen){
        VBox vbcat = new VBox();
        Warehouse assign = store_admin.getAssignedStore().getLinkedWarehouse();
        for(Categories cat: assign.getCategoryHashMap().get(catChosen).getSubCategories()){
            Button catbut = new Button(cat.getUid());
            catbut.setPrefWidth(catPane.getPrefWidth());
            catbut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setData(catbut.getText());
                }
            });
            HBox hbcat= new HBox();
            hbcat.getChildren().add(catbut);
            vbcat.getChildren().add(hbcat);
        }
        catPane.setContent(vbcat);

        VBox vbprod= new VBox();
        for(Product product: store_admin.getAssignedStore().getLinkedWarehouse().getCategoryHashMap().get(catChosen).getProduct_list()){
            Button prodName = new Button(product.getName());
            prodName.setPrefWidth(prodPane.getPrefWidth()*0.75);
            TextField qty = new TextField("1");
            qty.setPrefWidth(prodPane.getPrefWidth()*0.20);
            qty.setPromptText("Enter Quantity");
            CheckBox chk = new CheckBox();
            chk.setPrefSize(prodPane.getPrefWidth()*0.05,prodPane.getPrefWidth()*0.05);
            //chk.setText(product.getName());
            chk.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(chk.isSelected()){
                        selected.put(prodName.getText(),Integer.parseInt(qty.getText()));
                    }
                    else {
                        selected.remove(product.getName());
                    }
                }
            });
            HBox hb = new HBox();
            hb.getChildren().addAll(prodName,qty,chk);
            vbprod.getChildren().add(hb);
        }
        prodPane.setContent(vbprod);
    }
    public void addtoCart(ActionEvent e){
        for(String name: selected.keySet()) {
            store_admin.getAssignedStore().add_product(store_admin.getAssignedStore().getLinkedWarehouse().getUid(), name, selected.get(name));
        }
        System.out.println("Add To Cart pressed");
    }
}
