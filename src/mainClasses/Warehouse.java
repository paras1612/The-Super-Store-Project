package mainClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Warehouse implements Serializable {
    private static final long serialVersionUID=7L;
    private final String uid;
    private Cart cart;
    private HashMap<Product,Integer> Inventory;
    private ArrayList<Categories> categoriesList;
    private String Message;
    private HashMap<Product, Integer> ProductSold;
    private double sale;

    public Warehouse(String uid) {
        this.uid = uid;
    }

    public HashMap<Product, Integer> getInventory() {
        return Inventory;
    }

    void GenerateAlert(){

    }
    void order(Cart cart){

    }
    void send_confirm(){

    }

    public ArrayList<Categories> getCategoriesList() {
        return categoriesList;
    }

    public String getMessage() {
        return Message;
    }

    public HashMap<Product, Integer> getProductSold() {
        return ProductSold;
    }

    public String getUid() {
        return uid;
    }

    public Cart getCart() {
        return cart;
    }

    public double getSale() {
        return sale;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
