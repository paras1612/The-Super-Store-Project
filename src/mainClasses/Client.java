package mainClasses;

import java.io.Serializable;
import java.util.ArrayList;

import static sample.Main.deserialize;
import static sample.Main.serialize;

public class Client implements Serializable {
    private static final long serialVersionUID = 7L;
    private String name;
    private String password;
    private final String uid;
    private Cart cart = new Cart();
    private double wallet;
    private ArrayList<Product> prevOrder;
    private Categories favCategory;

    public String getName() {
        return name;
    }

    public Client(String name, String pass, String uid) {
        this.name = name;
        this.password = pass;
        this.uid = uid;
    }

    public void add_product(String store, String name, int quant) {
        Product temp = Database.getDatabase().getStoreHashMap().get(store).getLinkedWarehouse().getProductHashMap().get(name);
        Product prod = new Product(temp.getName(), temp.getPrice(), quant, temp.getfCostQuater(), temp.getcCostQuater(), temp.getItemDemand());
        cart.getCartList().put(prod, quant);
        cart.getStoreprod().put(prod, Database.getDatabase().getStoreHashMap().get(store));
        serialize();
    }

    public void add_funds(double fund) {
        Database.getDatabase().getClientHashMap().get(this.name).wallet += fund;
        serialize();
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Product> getPrevOrder() {
        return prevOrder;
    }

    public void setPrevOrder(ArrayList<Product> prevOrder) {
        this.prevOrder = prevOrder;
    }

    public Categories getFavCategory() {
        return favCategory;
    }

    public void setFavCategory(Categories favCategory) {
        this.favCategory = favCategory;
    }

    public String getUid() {
        return uid;
    }


    public void Search(String prod_name, String store, String cat) {
        //Database.getDatabase().search(prod_name, store, cat);
    }

    public void currentOrder() {

    }

    public void checkout() {
        int flag = 0;
        for (Product product : cart.getCartList().keySet()) {
            Store prod_store = cart.getStoreprod().get(product);
            Product store_prod = new Product();
            for (Product store_pord1 : Database.getDatabase().getStoreHashMap().get(prod_store.getUid()).getInventory().keySet()) {
                if (store_pord1.getUid().equals(product.getUid())) {
                    store_prod = store_pord1;
                }
            }
            if (cart.getCartList().get(product) > Database.getDatabase().getStoreHashMap().get(prod_store.getUid()).getInventory().get(store_prod)) {
                System.out.println("Product Out of Stock: " + product.getUid());
                flag = 1;
            }
        }
        if (flag == 0) {
            Double amount_req = Double.valueOf(0);
            for(Product product: cart.getCartList().keySet()){
                amount_req+=cart.getCartList().get(product)*product.getPrice();
            }
            if(amount_req<wallet){
                System.out.println("Insufficient Funds");
            }
            //Orders of Client can be made here
            else {
                for (Product product : cart.getCartList().keySet()) {
                    Store prod_store = cart.getStoreprod().get(product);
                    prod_store.getProductSold().put(product, cart.getCartList().get(product));
                    prod_store.setMessage(prod_store.getMessage()+"Product Name: "+product.getName() +"\n" + "Quantity: "+cart.getCartList().get(product));
                }
                cart = new Cart();
            }
        }
        serialize();
    }
}
