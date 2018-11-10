package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

public class Store implements Serializable {
    private static final long serialVersionUID=7L;
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
