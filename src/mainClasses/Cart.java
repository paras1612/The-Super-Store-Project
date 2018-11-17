package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {
    private static final long serialVersionUID=7L;
    private HashMap<Product, Integer> cartList = new HashMap<>();
    private HashMap<Product, Warehouse> wareprod = new HashMap<>();
    private HashMap<Product, Store> storeprod = new HashMap<>();
    public HashMap<Product, Integer> getCartList() {
        return cartList;
    }

    public HashMap<Product, Warehouse> getWareprod() {
        return wareprod;
    }

    public HashMap<Product, Store> getStoreprod() {
        return storeprod;
    }
}
