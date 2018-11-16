package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

import static sample.Main.deserialize;
import static sample.Main.serialize;

public class Warehouse implements Serializable {
    private static final long serialVersionUID=7L;
    private final String uid;
    private Database database= deserialize();
    private Cart cart = new Cart();
    private HashMap<Product,Integer> Inventory = new HashMap<>();
    private HashMap<String, Categories> CategoryHashMap= new HashMap<>();
    private HashMap<String, Product> ProductHashMap= new HashMap<>();
    private String Message;
    private HashMap<Product, Integer> ProductSold = new HashMap<>();
    private double sale;

    public Warehouse(String uid) {
        serialize(database);
        CategoryHashMap.put("Main", new Categories("SuperStore"));
        this.uid = uid;
        serialize(database);
    }
    public boolean addCategory(Warehouse_Admin admin, String name, String parent){
        System.out.println(uid);
        database=deserialize();
        System.out.println(database.getWarehouseHashMap().get(uid));
        Categories init=new Categories(name);
        init.setParent(CategoryHashMap.get(parent));
        if(CategoryHashMap.containsKey(name)){
            return false;
        }
        database.getWarehouseHashMap().get(uid).CategoryHashMap.get(parent).getSubCategories().add(init);
        database.getWarehouseHashMap().get(uid).CategoryHashMap.put(name, init);
        CategoryHashMap.put(name, init);
        serialize(database);
        this.updateData();
        return true;
    }

    public HashMap<String, Product> getProductHashMap() {
        return ProductHashMap;
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

    public HashMap<String, Categories> getCategoryHashMap() {
        return CategoryHashMap;
    }

    public String getMessage() {
        return Message;
    }

    public Database getDatabase() {
        return database;
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

    public boolean addProduct(Warehouse_Admin warehouse_admin, String name, double price, int quant, double fcost, double ccost, double idem, String parent) {
        System.out.println(uid);
        database = deserialize();
        System.out.println(database.getWarehouseHashMap().get(uid));
        Product init = new Product(name, price, quant, fcost, ccost, idem);
        init.setParent(CategoryHashMap.get(parent));
        if (getProductHashMap().containsKey(name)) {
            return false;
        }
        database.getWarehouseHashMap().get(uid).CategoryHashMap.get(parent).getProduct_list().add(init);
        database.getWarehouseHashMap().get(uid).ProductHashMap.put(name, init);
        serialize(database);
        updateData();
        return true;
    }
    public void updateData(){
        database=deserialize();
    }

    public void add_product(String warehouse, String name, int quantity) {
        updateData();
        Product temp = database.getWarehouseHashMap().get(warehouse).getProductHashMap().get(name);
        Product init = new Product(temp.getName(), temp.getPrice(), quantity, temp.getfCostQuater(), temp.getcCostQuater(), temp.getItemDemand());
        cart.getCartList().put(init, quantity);
        database.getWarehouseHashMap().get(uid).cart.getCartList().put(init, quantity);
        serialize(database);
        Database hello = deserialize();
        System.out.println(hello);
    }
}
