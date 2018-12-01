package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mainClasses.Client;
import mainClasses.Store_Admin;
import mainClasses.Super_usr;
import mainClasses.Warehouse_Admin;

import java.io.IOException;

public class help_cnt {
    private Super_usr user;
    private Client client;
    private Store_Admin store_admin;
    private Warehouse_Admin warehouse_admin;

    public void setUser(Super_usr user) {
        this.user = user;
    }

    public void setStore_admin(Store_Admin store_admin) {
        this.store_admin = store_admin;
    }

    public void setWarehouse_admin(Warehouse_Admin warehouse_admin) {
        this.warehouse_admin = warehouse_admin;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void Home(ActionEvent e) throws IOException {
        System.out.println("Home");
        ((javafx.scene.Node)e.getSource()).getScene().getWindow().hide();
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        AnchorPane root = loader.load();
        if(client!=null){
            loader = new FXMLLoader(getClass().getResource("Home.fxml"));
            root = loader.load();
            Home_cnt cnt = loader.getController();
            cnt.setClient(client);
        }
        else if(store_admin!=null){
            loader = new FXMLLoader(getClass().getResource("Store_home.fxml"));
            root = loader.load();
            Store_home_cnt cnt = loader.getController();
            cnt.setStore_admin(store_admin);
        }
        else if(warehouse_admin!=null){
            loader = new FXMLLoader(getClass().getResource("Warehouse_home.fxml"));
            root = loader.load();
            Warehouse_home_cnt cnt = loader.getController();
            cnt.setWarehouse_admin(warehouse_admin);
        }
        else if (user!=null){
            loader = new FXMLLoader(getClass().getResource("Super_user.fxml"));
            root = loader.load();
            Super_user_cnt cnt = loader.getController();
            cnt.setUser(user);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("home.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
