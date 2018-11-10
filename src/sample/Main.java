package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainClasses.Database;

import java.io.*;

public class Main extends Application {
    private Database database = deserialize();
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

    public static void serialize(Database Main_Database) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Database.txt"));
            out.writeObject(Main_Database);
            out.close();
        }
        catch(Exception e)
        {

        }
    }

    public static Database deserialize(){
        Database Main_Database=null;
        ObjectInputStream infile = null;
        try {
            infile = new ObjectInputStream(new FileInputStream("Database.txt"));
            Main_Database=(Database) infile.readObject();
            //System.out.println(Main_Database.categoriesHashMap);
            infile.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            Main_Database = new Database();
            System.out.println("NO database found");
            //System.out.println(Main_Database.categoriesHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return Main_Database;
    }
}
