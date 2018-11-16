package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Super_usr;

import java.io.IOException;

public class Super_user_cnt{
    @FXML private ComboBox prod_cat = new ComboBox();
    @FXML private ComboBox sort_menu;
    private Super_usr user;
    public void setUser(Super_usr use) {
        this.user = use;
        prod_cat.getItems().addAll("Product","Category");
        sort_menu.getItems().addAll("Name");
    }

    public Super_usr getUser() {
        return user;
    }

    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Super_user.fxml"));
        AnchorPane root = loader.load();
        if(user!=null){
            Label name= new Label(user.getName());
            name.setLayoutX(15.0);
            name.setLayoutY(60.0);
            root.getChildren().add(name);
        }
        Scene scene = new Scene(root);
        Super_user_cnt cnt = loader.getController();
        cnt.setUser(user);
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

    public void warehouse_data_supr_btn(ActionEvent e) throws IOException
    {
        System.out.println("Warehouse button pressed from Super User");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Warehouse_data_supr.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Warehouse_data_supr_cnt cnt = loader.getController();
        cnt.setUser(user);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void store_data_supr_btn(ActionEvent e) throws IOException
    {
        System.out.println("Store button pressed from Super User");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Store_data_supr.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Store_data_supr_cnt cnt = loader.getController();
        cnt.setUser(user);
        //scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void search(ActionEvent e){
        System.out.println("Search Button");
    }
    public void product(ActionEvent e){
        System.out.println("Product Pressed");
    }
    public void message(ActionEvent e) throws IOException {
        System.out.println("message pressed");
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Message_cnt cnt = loader.getController();
        cnt.text.setText("kjsdbkjfbjksb");
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void warehouseLink(ActionEvent e) throws IOException {
        System.out.println("Link");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Store_data_supr.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        Store_data_supr_cnt cnt = loader.getController();
        System.out.println("User");
        System.out.println(user);
        cnt.setUser(user);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addAdmin(ActionEvent e) throws IOException {
        System.out.println("addAdmin");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addAdmin.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);
        addAdmin_cnt cnt = loader.getController();
        System.out.println("User");
        System.out.println(user);
        cnt.setUser(user);
        cnt.setRole(cnt.getRole());
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
