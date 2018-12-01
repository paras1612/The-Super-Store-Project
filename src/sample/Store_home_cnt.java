package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Pair;
import mainClasses.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static sample.Main.serialize;

public class Store_home_cnt{
    @FXML private ComboBox prod_cat;
    @FXML private ComboBox sort_menu;
    @FXML private ComboBox delchoose;
    @FXML private ScrollPane catPane;
    @FXML private ScrollPane prodPane;
    @FXML private Label wareNameLabel;
    @FXML private TextField search_fld;
    private ArrayList<String> catList = new ArrayList<>();
    private ArrayList<String> prodList = new ArrayList<>();
    private VBox vbprod;
    private HBox hb;
    private CheckBox chk;
    private TextField qty;

    private Store_Admin store_admin;
    private HashMap<String, Integer> selected = new HashMap<>();
    public void setStore_admin(Store_Admin store_admin) {
        this.store_admin = store_admin;
        wareNameLabel.setText(store_admin.getAssignedStore().getLinkedWarehouse().getUid());
        prod_cat.getItems().addAll("Product","Category");
        sort_menu.getItems().addAll("Name");
        setDelchoose();
        setData("Main");
    }

    public Store_Admin getStore_admin() {
        return store_admin;
    }



    public void setDelchoose() {
        if(store_admin.getAssignedStore().getLinkedWarehouse().getProductHashMap()!=null) {
            for (Product name : store_admin.getAssignedStore().getInventory().keySet()) {
                this.delchoose.getItems().add(name.getUid());
            }
        }
    }

    public void sort(ActionEvent actionEvent) {
        sort_cat(catList, prodList);
    }

    private void sort_cat(ArrayList<String> cat, ArrayList<String> prod) {
        cat.sort(String::compareTo);
        VBox vb = new VBox();
        for(String category: cat){
            Button catbut= new Button(category);
            catbut.setPrefWidth(catPane.getPrefWidth());
            catbut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Category View.fxml"));

                    try {
                        AnchorPane root = loader.load();
                        Scene scene = new Scene(root);
                        CategoryView_cnt cnt = loader.getController();
                        System.out.println(cnt);
                        if(store_admin!=null) {
                            cnt.setStore_admin(store_admin);
                        }
                        cnt.setData(store_admin.getAssignedStore().getUid(),catbut.getText());
                        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            HBox hb = new HBox();
            hb.getChildren().add(catbut);
            vb.getChildren().add(hb);
        }
        catPane.setContent(vb);
        vb = new VBox();
        prod.sort(String::compareTo);
        for( String product: prod){
            Button prodName = new Button(product);
            TextField qty = new TextField("1");
            qty.setPromptText("Enter Quantity");
            CheckBox chk = new CheckBox();
            chk.setText(product);
            chk.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(chk.isSelected()){
                        selected.put(chk.getText(),Integer.parseInt(qty.getText()));
                    }
                    else {
                        selected.remove(product);
                    }
                }
            });
            HBox hb = new HBox();
            hb.getChildren().addAll(prodName,qty,chk);
            vb.getChildren().add(hb);
        }
        prodPane.setContent(vb);
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
        delchoose.getItems().remove(delchoose.getValue().toString());
        System.out.println("Product Deleted");
        serialize();
    }

    public void search(ActionEvent e){
        Pair<ArrayList<String>, ArrayList<String>> temp = Database.getDatabase().searchWare(search_fld.getText(), store_admin.getAssignedStore().getLinkedWarehouse().getUid(), "Main", new ArrayList<String>(), new ArrayList<String>());
        prodList=temp.getKey();
        catList = temp.getValue();
        sort_cat(catList, prodList);
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
        vbprod= new VBox();
        for(Product product: store_admin.getAssignedStore().getLinkedWarehouse().getCategoryHashMap().get(catChosen).getProduct_list()){
        Button prodName = new Button(product.getName());
        Label prodQty = new Label();
        prodQty.setText(String.valueOf(product.getQuantity()));
        prodQty.setPrefWidth(prodPane.getPrefWidth()*0.15);
        prodQty.setTextAlignment(TextAlignment.CENTER);
        prodName.setPrefWidth(prodPane.getPrefWidth()*0.60);
        qty = new TextField("1");
        qty.setPrefWidth(prodPane.getPrefWidth()*0.20);
        qty.setPromptText("Enter Quantity");
        chk = new CheckBox();
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
            prodName.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("productView.fxml"));
                    AnchorPane root = null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    productView_cnt cnt = loader.getController();
                    try {
                        cnt.setStore_admin(store_admin,store_admin.getAssignedStore().getLinkedWarehouse().getUid(),prodName.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            });
        hb = new HBox();
        hb.getChildren().addAll(prodName,prodQty,qty,chk);
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
