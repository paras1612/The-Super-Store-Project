package mainClasses;

import java.util.HashMap;

public class Warehouse {
    private final String uid;
    private Cart cart;
    private String Message;
    private HashMap<Product, Integer> ProductSold;
    private double sale;

    public Warehouse(String uid) {
        this.uid = uid;
    }

    void GenerateAlert(){

    }
    void order(Cart cart){

    }
    void send_confirm(){

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
