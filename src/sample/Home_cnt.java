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
import javafx.util.Pair;
import mainClasses.Categories;
import mainClasses.Client;
import mainClasses.Database;
import mainClasses.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Home_cnt{
    private Client client;
    @FXML private TextField searchBar;
    @FXML private ComboBox chooseStore;
    @FXML private ScrollPane dataPane;
    @FXML private ComboBox sortBy;
    private ArrayList<String> prodList = new ArrayList<>();
    private ArrayList<String> catList = new ArrayList<>();
    private HashMap<String, Integer> selected= new HashMap<>();
    public ComboBox getChooseStore() {
        return chooseStore;
    }

    public void setChooseStore() {
        for(String name:(Database.getDatabase().getStoreHashMap().keySet())){
            chooseStore.getItems().add(name);
        }
    }

    void setClient(Client client1){
        sortBy.getItems().addAll("Name");
        client=client1;
        setChooseStore();
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
            root.getChildren().set(4, profile);
        }
        Scene scene = new Scene(root);
        if(client!=null){
            Home_cnt cnt = loader.getController();
            cnt.setClient(client);
        }
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
        System.out.println("CartButton");
        ((javafx.scene.Node) e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Cart.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        System.out.println(loader.getController().toString());
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
        primaryStage.setScene(scene);
        primaryStage.show();
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
    }

    public void addToCart(ActionEvent e){
        for(String name: selected.keySet()) {
            client.add_product(chooseStore.getValue().toString(), name, selected.get(name));
        }System.out.println("Add To Cart pressed");
    }
    public void search(ActionEvent e){
        Pair<ArrayList<String>, ArrayList<String>> temp =Database.getDatabase().search(searchBar.getText(), chooseStore.getValue().toString(), "Main", new ArrayList<String>(), new ArrayList<String>());
        prodList=temp.getKey();
        catList = temp.getValue();
        sort_cat(catList, prodList);
        System.out.println("Search pressed");
    }

    public void dispStData(ActionEvent e){
        prodList = new ArrayList<>();
        catList = new ArrayList<>();
        VBox vb = new VBox();
        for(Categories cat: Database.getDatabase().getStoreHashMap().get(chooseStore.getValue().toString()).getCategoriesList().get("Main").getSubCategories()){
            catList.add(cat.getUid());
            Button catbut= new Button(cat.getUid());
            catbut.setPrefWidth(dataPane.getPrefWidth());
            catbut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Category View.fxml"));
                    try {
                        AnchorPane root = loader.load();
                        if(client!=null){
                            Button profile = new Button(client.getUid());
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
                            root.getChildren().set(4,profile);
                        }
                        Scene scene = new Scene(root);
                        CategoryView_cnt cnt = loader.getController();
                        System.out.println(cnt);
                        System.out.println(client);
                        if(client!=null) {
                            cnt.setClient(client);
                        }
                        cnt.setData(chooseStore.getValue().toString(),catbut.getText());
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
        for(Product product: Database.getDatabase().getStoreHashMap().get(chooseStore.getValue().toString()).getInventory().keySet()){
            prodList.add(product.getName());
            Button prodName = new Button(product.getName());
            TextField qty = new TextField("1");
            qty.setPromptText("Enter Quantity");
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
            hb.getChildren().addAll(prodName,qty,chk);
            vb.getChildren().add(hb);
        }
        dataPane.setContent(vb);
    }

    public void sort(ActionEvent actionEvent) {
        sort_cat(catList, prodList);
    }

    private void sort_cat(ArrayList<String> cat, ArrayList<String> prod) {
        cat.sort(String::compareTo);
        VBox vb = new VBox();
        for(String category: cat){
            Button catbut= new Button(category);
            catbut.setPrefWidth(dataPane.getPrefWidth());
            catbut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage primaryStage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Category View.fxml"));

                    try {
                        AnchorPane root = loader.load();
                        if(client!=null){
                            Button profile = new Button(client.getUid());
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
                            root.getChildren().set(4,profile);
                        }
                        Scene scene = new Scene(root);
                        CategoryView_cnt cnt = loader.getController();
                        System.out.println(cnt);
                        System.out.println(client);
                        if(client!=null) {
                            cnt.setClient(client);
                        }
                        cnt.setData(chooseStore.getValue().toString(),catbut.getText());
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
        dataPane.setContent(vb);
    }
}
