package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Database;
import mainClasses.Product;
import java.io.IOException;

import static sample.Main.deserialize;

public class Product_mod_cnt{
    private Database database= deserialize();
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

        public void Home(ActionEvent e) throws IOException {
            System.out.println("Home");
            ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
            Stage primaryStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = loader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
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

        public void set(ActionEvent e){
            System.out.println("Set pressed");
            Product p = new Product(this.nameTxt.getText(),Double.parseDouble(this.priceTxt.getText()),Integer.parseInt(this.qtyTxt.getText()),"product_"+this.nameTxt.getText(),Double.parseDouble(this.dTxt.getText()),Double.parseDouble(this.hTxt.getText()),Double.parseDouble(this.kTxt.getText()));
            database.getProductList().put(p.getUid(),p);
            System.out.println("Success");
        }
}
