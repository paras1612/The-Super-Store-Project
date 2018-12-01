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
import mainClasses.Database;
import mainClasses.Product;
import mainClasses.Warehouse_Admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse_home_cnt{
    private Warehouse_Admin warehouse_admin;
    private HashMap<String, Integer> selected = new HashMap<>();
    @FXML private ComboBox chooseWare;
    @FXML private ScrollPane catPane;
    @FXML private ScrollPane prodPane;
    @FXML private ComboBox delchoose;
    @FXML private TextField search_fld;
    private ArrayList<String> prodList = new ArrayList<>();
    private ArrayList<String> catList = new ArrayList<>();

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin) {
        this.warehouse_admin = warehouse_admin;
        setChooseWare();
        setDelchoose();
    }

    public Warehouse_Admin getWarehouse_admin() {
        return warehouse_admin;
    }

    public void setChooseWare() {
        for(String warehouse :Database.getDatabase().getWarehouseHashMap().keySet()){
            this.chooseWare.getItems().add(warehouse);
        }
    }
    public void setDelchoose() {
        if(warehouse_admin.getAssigned_ware().getProductHashMap()!=null) {
            for (String name : warehouse_admin.getAssigned_ware().getProductHashMap().keySet()) {
                this.delchoose.getItems().add(name);
            }
        }
    }

    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Warehouse_home.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Warehouse_home_cnt cnt= loader.getController();
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
        primaryStage.show();System.out.println("Login");
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
        cnt.setWarehouse_admin(warehouse_admin);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void Message_btn(ActionEvent e) throws IOException {
        System.out.println("Message");
        //((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Message_cnt cnt =loader.getController();
        cnt.text.setText(warehouse_admin.getAssigned_ware().getMessage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void orders(ActionEvent e){
        System.out.println("Orders pressed");
    }

    public void deleteProduct(ActionEvent e){
        warehouse_admin.getAssigned_ware().deleteProduct(delchoose.getValue().toString());
        delchoose.getItems().clear();
        setDelchoose();
        setDatafn(warehouse_admin.getAssigned_ware().getUid(), "Main");
        System.out.println("Product Deleted");
    }

    public void search(ActionEvent e){
        System.out.println(chooseWare.getValue().toString());
        Pair<ArrayList<String>, ArrayList<String>> temp = Database.getDatabase().searchWare(search_fld.getText(), chooseWare.getValue().toString(), "Main", new ArrayList<String>(), new ArrayList<String>());
        prodList=temp.getKey();
        catList = temp.getValue();
        sort_cat(catList, prodList);
        System.out.println("Search pressed");
    }

    public void addCategory(ActionEvent e) throws IOException {((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Product_mod.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Product_mod_cnt cnt= loader.getController();
        cnt.setWarehouse_admin(warehouse_admin);
        cnt.getPriceTxt().setDisable(true);
        cnt.getQtyTxt().setDisable(true);
        cnt.getdTxt().setDisable(true);
        cnt.gethTxt().setDisable(true);
        cnt.getkTxt().setDisable(true);
        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Product added");
        System.out.println("Add category pressed");
    }
    public void setData(ActionEvent e){
        setDatafn(chooseWare.getValue().toString(),"Main");
    }
    public void setDatafn(String warehouse, String catChosen){
        catList = new ArrayList<>();
        VBox vbcat = new VBox();
        for(Categories cat: Database.getDatabase().getWarehouseHashMap().get(warehouse).getCategoryHashMap().get(catChosen).getSubCategories()){
            catList.add(cat.getUid());
            Button catbut = new Button(cat.getUid());
            catbut.setPrefWidth(catPane.getPrefWidth());
            catbut.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    setDatafn(warehouse, catbut.getText());
                }
            });
            HBox hbcat= new HBox();
            hbcat.getChildren().add(catbut);
            vbcat.getChildren().add(hbcat);
        }
        catPane.setContent(vbcat);

        VBox vbprod= new VBox();
        prodList= new ArrayList<>();
        for(Product product: Database.getDatabase().getWarehouseHashMap().get(warehouse).getCategoryHashMap().get(catChosen).getProduct_list()){
            prodList.add(product.getUid());
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
                        cnt.setWarehouse_admin(warehouse_admin,chooseWare.getValue().toString(),prodName.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    primaryStage.setScene(scene);
                    primaryStage.show();
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
            warehouse_admin.getAssigned_ware().add_product(chooseWare.getValue().toString(), name, selected.get(name));
        }
        System.out.println("Add To Cart pressed");
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
                        if(warehouse_admin!=null) {
                            cnt.setWarehouse_admin(warehouse_admin);
                        }
                        cnt.setData(chooseWare.getValue().toString(),catbut.getText());
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

}
