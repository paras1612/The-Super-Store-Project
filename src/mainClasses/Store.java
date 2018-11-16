package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

import static sample.Main.deserialize;
import static sample.Main.serialize;

public class Store implements Serializable {
    private static final long serialVersionUID=7L;
    private Database database = deserialize();
    private Warehouse linkedWarehouse;
    private HashMap<Product, Integer> ProductSold = new HashMap<>();
    private HashMap<String, Categories> categoriesList = new HashMap<>();
    private double sale;
    private String Message;
    private HashMap<Product, Integer> Inventory= new HashMap<>();
    private final String uid;
    private Cart cart = new Cart();

    public Store(String uid) {
        categoriesList.put("Main", new Categories("SuperStore"));
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public boolean addProduct(Store_Admin store_admin, String product, String parent){
        database=deserialize();
        Warehouse linkware= linkedWarehouse;
        Product curr=linkware.getProductHashMap().get(product);
        Product one = new Product(curr.getName(),curr.getPrice(),0,curr.getfCostQuater(),curr.getcCostQuater(),curr.getItemDemand());
        one.setParent(curr.getParent());
        for(Product prod: Inventory.keySet()){
            if(prod.getUid().equals(product)){
                return false;
            }
        }
        database.getStoreHashMap().get(this.uid).getCategoriesList().get(parent).getProduct_list().add(one);
        if(linkware.getProductHashMap().get(product).getQuantity()>curr.getEOQ()){
            System.out.println(database.getStore_AdminHashMap().get(store_admin.uid));
            database.getStore_AdminHashMap().get(store_admin.uid).getAssignedStore().getInventory().put(one,(int)curr.getEOQ());
            database.getWarehouseHashMap().get(linkware.getUid()).getProductHashMap().get(curr.getUid()).setQuantity(curr.getQuantity()-(int)curr.getEOQ());
            database.getWarehouseHashMap().get(linkedWarehouse.getUid()).getInventory().put(curr,curr.getQuantity()-(int)curr.getEOQ());
        }
        else {
            System.out.println("Product quantity not available");
        }
        serialize(database);
        return true;
    }
    public boolean addCategory(Store_Admin admin, String name, String parent){
        database=deserialize();
        Categories init= new Categories(name);
        init.setParent(database.getStoreHashMap().get(this.uid).getCategoriesList().get(parent));
        database.getStoreHashMap().get(this.uid).getCategoriesList().get(parent).getSubCategories().add(init);
        database.getStoreHashMap().get(this.uid).getCategoriesList().put(name,init);
        if(categoriesList.containsKey(name)){
            return false;
        }
        categoriesList.put(name, init);
        serialize(database);
        return true;
    }

    void Generate_Alert(){

    }

    public void setLinkedWarehouse(Warehouse linkedWarehouse) {
        this.linkedWarehouse = linkedWarehouse;
    }

    public HashMap<Product, Integer> getInventory() {
        return Inventory;
    }

    public HashMap<String, Categories> getCategoriesList() {
        return categoriesList;
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

    public Database getDatabase() {
        return database;
    }

    void Order(Cart cart){

    }

    void orderAuto(){

    }
}
