package mainClasses;

import java.io.Serializable;
import java.util.ArrayList;

import static sample.Main.deserialize;
import static sample.Main.serialize;

public class Client implements Serializable {
    private static final long serialVersionUID=7L;
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

    public Client(String name, String pass, String uid)
    {
        this.name = name;
        this.password = pass;
        this.uid = uid;
    }
    void check_out(){

    }
    public void add_product(String store, String name, int quant){
        Product temp = Database.getDatabase().getStoreHashMap().get(store).getLinkedWarehouse().getProductHashMap().get(name);
        Product prod = new Product(temp.getName(), temp.getPrice(), quant, temp.getfCostQuater(), temp.getcCostQuater(), temp.getItemDemand());
        Database.getDatabase().getClientHashMap().get(this.name).getCart().getCartList().put(prod, quant);
        Database.getDatabase().getClientHashMap().get(this.name).getCart().getStoreprod().put(prod, Database.getDatabase().getStoreHashMap().get(store));
        serialize();
    }
    public void add_funds(double fund){
        Database.getDatabase().getClientHashMap().get(this.name).wallet+=fund;
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


    public void Search()
    {

    }

    public void addToCart()
    {

    }

    public void CheckOut()
    {

    }
    public void currentOrder()
    {

    }

}
