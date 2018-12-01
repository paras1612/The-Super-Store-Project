package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Database;
import mainClasses.Super_usr;

import java.io.IOException;

public class addAdmin_cnt{
    @FXML private ComboBox role;
    @FXML private TextField name;
    @FXML private TextField pass;
    private Super_usr user;

    public void setUser(Super_usr user) {
        this.user = user;
    }
    public Super_usr getUser() {
        return user;
    }

    public void setRole(ComboBox role) {
        System.out.println(user);
        System.out.println(Database.getDatabase().getStoreHashMap().toString());
        for(String name: Database.getDatabase().getStoreHashMap().keySet()) {
            this.role.getItems().add(name);
        }
        for(String name: Database.getDatabase().getWarehouseHashMap().keySet()) {
            this.role.getItems().add(name);
        }
    }

    public ComboBox getRole() {
        return role;
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

    public void addAdmin(ActionEvent e)
    {
        if(Database.getDatabase().getStoreHashMap().containsKey(role.getValue().toString())){
            user.createStoreAdmin(name.getText(),pass.getText(),role.getValue().toString());

        }
        else if(Database.getDatabase().getWarehouseHashMap().containsKey(role.getValue().toString())){
            user.createWarehouseAdmin(name.getText(), pass.getText(),role.getValue().toString());
        }
        System.out.println("Store/warehouse chosen");
    }


    public void adminRemove(ActionEvent e)
    {
        System.out.println("Admin removed");
    }

}
