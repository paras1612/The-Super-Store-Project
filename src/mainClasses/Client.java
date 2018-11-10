package mainClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Client implements Serializable {
    private String name;
    private String password;
    private final String uid;
    private Cart cart;
    private double wallet;
    private ArrayList<Product> prevOrder;
    private Categories favCategory;
    public String getName() {
        return name;
    }
    public Client(String name, String password, String uid)
    {
        this.name = name;
        this.password = password;
        this.uid = uid;
    }

    public Client()
    {
        super();
        this.name = null;
        this.password=null;
        this.uid = "1001";
        this.cart =null;
        this.wallet = 100;
        this.prevOrder =null;
        this.favCategory = null;

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

    public void addFunds()
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
