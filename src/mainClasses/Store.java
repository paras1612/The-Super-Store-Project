package mainClasses;

import java.util.HashMap;

public class Store {
    private Warehouse linkedWarehouse;
    private HashMap<Product, Integer> ProductSold;
    private double sale;
    private String Message;
    private final String uid;
    private Cart cart;

    public Store(String uid) {
        this.uid = uid;
    }

    void Generate_Alert(){

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

    void Order(Cart cart){

    }

    void orderAuto(){

    }
}
