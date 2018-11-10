package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainClasses.Database;

public class Main extends Application {
    private Database database = new Database();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("The Super Store");
        Scene one = new Scene(root, 600, 400);
        one.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(one);
        primaryStage.show();
    }

    public Database getDatabase() {
        return database;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
