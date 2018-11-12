package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

public class Cart implements Serializable {
    private static final long serialVersionUID=7L;
    private HashMap<Product, Integer> cartList;

    public HashMap<Product, Integer> getCartList() {
        return cartList;
    }
}
