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
import mainClasses.Warehouse_Admin;

import java.io.IOException;
import java.util.HashMap;

public class Warehouse_home_cnt{
    private Warehouse_Admin warehouse_admin;
    private HashMap<String, Integer> selected = new HashMap<>();
    @FXML private ComboBox chooseWare;
    @FXML private ScrollPane catPane;
    @FXML private ScrollPane prodPane;

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin) {
        this.warehouse_admin = warehouse_admin;
        setChooseWare();
    }

    public Warehouse_Admin getWarehouse_admin() {
        return warehouse_admin;
    }

    public void setChooseWare() {
        warehouse_admin.getAssigned_ware().updateData();
        for(String warehouse :warehouse_admin.getAssigned_ware().getDatabase().getWarehouseHashMap().keySet()){
            this.chooseWare.getItems().add(warehouse);
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
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("Cart.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void Message_btn(ActionEvent e) throws IOException {
        System.out.println("Message");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = loader.load(getClass().getResource("Message.fxml"));
        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
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
        Product_mod_cnt cnt= loader.getController();
        cnt.setWarehouse_admin(warehouse_admin);
        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("Product added");
    }
    public void deleteProduct(ActionEvent e){

        System.out.println("Product Deleted");
    }

    public void search(ActionEvent e){
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
        VBox vbcat = new VBox();
        for(Categories cat: warehouse_admin.getAssigned_ware().getDatabase().getWarehouseHashMap().get(warehouse).getCategoryHashMap().get(catChosen).getSubCategories()){
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
        for(Product product: warehouse_admin.getAssigned_ware().getDatabase().getWarehouseHashMap().get(warehouse).getCategoryHashMap().get(catChosen).getProduct_list()){
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
}
