package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Message_cnt {
    private String mess;

    public void setMess(String mess) {
        this.mess = mess;
    }

    @FXML
    TextArea text;
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
}
