package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

import static sample.Main.deserialize;

public class Store implements Serializable {
    private static final long serialVersionUID=7L;
    private Database database = deserialize();
    private Warehouse linkedWarehouse;
    private HashMap<Product, Integer> ProductSold;
    private HashMap<String, Categories> categoriesList = new HashMap<>();
    private double sale;
    private String Message;
    private HashMap<Product, Integer> Inventory;
    private final String uid;
    private Cart cart;

    public Store(String uid) {
        this.uid = uid;
    }

    void Generate_Alert(){

    }

    public void setLinkedWarehouse(Warehouse linkedWarehouse) {
        this.linkedWarehouse = linkedWarehouse;
    }

    public HashMap<Product, Integer> getInventory() {
        return Inventory;
    }

    public HashMap<String, Categories> getCategoriesList() {
        return categoriesList;
    }

    public HashMap<Product, Integer> getProductSold() {
        return ProductSold;
    }

    public Warehouse getLinkedWarehouse() {
        return linkedWarehouse;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public Database getDatabase() {
        return database;
    }

    void Order(Cart cart){

    }

    void orderAuto(){

    }
}
