package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainClasses.*;

import java.io.IOException;
import java.util.HashMap;

public class CategoryView_cnt {
    private Client client;
    private Super_usr user;
    private Warehouse_Admin warehouse_admin;
    private Store_Admin store_admin;
    private String store;
    private HashMap<String, Integer> selected = new HashMap<>();
    @FXML private ScrollPane catPane;
    @FXML private ScrollPane prodPane;

    public void setUser(Super_usr user) {
        this.user = user;
    }

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin) {
        this.warehouse_admin = warehouse_admin;
    }

    public void setStore_admin(Store_Admin store_admin) {
        this.store_admin = store_admin;
    }

    public void setClient(Client clien) {
        client=clien;
    }

    public void setData(String store, String catChosen){
        this.store=store;
        VBox vbcat = new VBox();
        for(Categories cat: Database.getDatabase().getStoreHashMap().get(store).getCategoriesList().get(catChosen).getSubCategories()){
            Button catbut = new Button(cat.getUid());
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
                        System.out.println(client);
                        cnt.setClient(client);
                        cnt.setData(store,catbut.getText());
                        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            HBox hbcat= new HBox();
            hbcat.getChildren().add(catbut);
            vbcat.getChildren().add(hbcat);
        }
        catPane.setContent(vbcat);

        VBox vbprod = new VBox();
        prodPane.setFitToWidth(true);
        System.out.println(Database.getDatabase().getStoreHashMap().get(store).getCategoriesList().get(catChosen).getProduct_list());
        for(Product product: Database.getDatabase().getStoreHashMap().get(store).getCategoriesList().get(catChosen).getProduct_list()){
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
            prodName.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                }
            });
            HBox hb = new HBox();
            hb.getChildren().addAll(prodName,qty,chk);
            vbprod.getChildren().add(hb);
        }
        prodPane.setContent(vbprod);
    }
    /*public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        AnchorPane root = loader.load();
        if(client!=null) {
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
    }*/
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
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        //System.out.println(loader.getController().toString());
        Cart_cnt cnt = loader.getController();
        System.out.println(client);
        if(client!=null) {
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
            root.getChildren().set(1, profile);
        }
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        cnt.setClient(client);
        cnt.setDisplayData();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void search(ActionEvent e){
        System.out.println("Search Button Pressed");
    }

    public void addToCart(ActionEvent e){
        if(user!=null){

        }
        else {
            for (String name : selected.keySet()) {
                if(client!=null) {
                    client.add_product(store, name, selected.get(name));
                }
                else if(warehouse_admin!=null){
                    warehouse_admin.getAssigned_ware().add_product(warehouse_admin.getAssigned_ware().getUid(), name, selected.get(name));
                }
                else if(store_admin!=null){
                    store_admin.getAssignedStore().add_product(store_admin.getAssignedStore().getUid(), name, selected.get(name));
                }
            }
        }System.out.println("Add To Cart pressed");
    }

    public void cartBtn(ActionEvent actionEvent) throws IOException {
        System.out.println("CartButton");
        ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        System.out.println(loader.getController().toString());
        Cart_cnt cnt = loader.getController();
        //System.out.println(client);
        if(client!=null) {
            javafx.scene.control.Button profile = new Button(client.getName());
            profile.setLayoutX(17.0);
            profile.setLayoutY(349.0);
            profile.setPrefHeight(26.0);
            profile.setPrefWidth(194.0);
            profile.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ((javafx.scene.Node) event.getSource()).getScene().getWindow().hide();
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
            root.getChildren().set(1, profile);
            cnt.setClient(client);
        }
        else if(warehouse_admin!=null){
            cnt.setWarehouse_admin(warehouse_admin);
        }
        else if(store_admin!=null){
            cnt.setStore_admin(store_admin);
        }
        primaryStage.setScene(scene);
        primaryStage.show();
        ((javafx.scene.Node)actionEvent.getSource()).getScene().getWindow().hide();
    }
}
