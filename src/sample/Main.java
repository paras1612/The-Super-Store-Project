package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mainClasses.Database;

import java.io.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Database.setDatabase(deserialize());
        if(Database.getDatabase().getSuper_userHashMap().get("q")==null){
            System.out.println("Made a new one haha");
            Database.getDatabase().createSuperuser("q","q", "q");
            serialize();
            System.out.println(Database.getDatabase());
            System.out.println(Database.getDatabase().getSuper_userHashMap().toString());
        }
        System.out.println(Database.getDatabase());
        System.out.println(Database.getDatabase().getWarehouseHashMap());
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("The Super Store");
        Scene one = new Scene(root);//, 600, 400);
        System.out.println(Database.getDatabase().getStore_AdminHashMap().toString());
        one.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        primaryStage.setScene(one);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void serialize() {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("Database.txt"));
            Database curr = Database.getDatabase();
            out.writeObject(curr);
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
