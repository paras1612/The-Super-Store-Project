package mainClasses;

import java.io.Serializable;
import java.util.HashMap;
import static sample.Main.serialize;

public class Store implements Serializable {
    private static final long serialVersionUID=7L;
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

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getUid() {
        return uid;
    }

    public Cart getCart() {
        return cart;
    }

    public boolean addProduct(Store_Admin store_admin, String product, String parent){
        Warehouse linkware= linkedWarehouse;
        Product curr=linkware.getProductHashMap().get(product);
        Product one = new Product(curr.getName(),curr.getPrice(),0,curr.getfCostQuater(),curr.getcCostQuater(),curr.getItemDemand());
        one.setParent(curr.getParent());
        for(Product prod: Inventory.keySet()){
            if(prod.getUid().equals(product)){
                return false;
            }
        }
        Database.getDatabase().getStoreHashMap().get(this.uid).getCategoriesList().get(parent).getProduct_list().add(one);
        if(linkware.getProductHashMap().get(product).getQuantity()>curr.getEOQ()){
            System.out.println(Database.getDatabase().getStore_AdminHashMap().get(store_admin.uid));
            Database.getDatabase().getStore_AdminHashMap().get(store_admin.uid).getAssignedStore().getInventory().put(one,(int)curr.getEOQ());
            Database.getDatabase().getWarehouseHashMap().get(linkware.getUid()).getProductHashMap().get(curr.getUid()).setQuantity(curr.getQuantity()-(int)curr.getEOQ());
            Database.getDatabase().getWarehouseHashMap().get(linkedWarehouse.getUid()).getInventory().put(curr,curr.getQuantity()-(int)curr.getEOQ());
        }
        else {
            System.out.println("Product quantity not available");
        }
        serialize();
        return true;
    }
    public boolean addCategory(Store_Admin admin, String name, String parent){
        Categories init= new Categories(name);
        init.setParent(Database.getDatabase().getStoreHashMap().get(this.uid).getCategoriesList().get(parent));
        Database.getDatabase().getStoreHashMap().get(this.uid).getCategoriesList().get(parent).getSubCategories().add(init);
        Database.getDatabase().getStoreHashMap().get(this.uid).getCategoriesList().put(name,init);
        if(categoriesList.containsKey(name)){
            return false;
        }
        categoriesList.put(name, init);
        serialize();
        return true;
    }
    public void add_product(String warehouse, String name, Integer quantity) {
        Product temp =Database.getDatabase().getWarehouseHashMap().get(warehouse).getProductHashMap().get(name);
        Product init = new Product(temp.getName(), temp.getPrice(), quantity, temp.getfCostQuater(), temp.getcCostQuater(), temp.getItemDemand());
        Database.getDatabase().getStoreHashMap().get(uid).cart.getCartList().put(init, quantity);
        Database.getDatabase().getStoreHashMap().get(uid).cart.getWareprod().put(init, this.linkedWarehouse);
        cart.getCartList().put(init, quantity);
        cart.getWareprod().put(init, this.linkedWarehouse);
        serialize();
    }

    void Generate_Alert(){

    }

    public void checkout() {
        int flag=0;
        for(Product product: Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().keySet()){
            Product product_ware= linkedWarehouse.getProductHashMap().get(product.getUid());
            if(cart.getCartList().get(product) > linkedWarehouse.getInventory().get(product_ware)){
                System.out.println("Product Out of Stock: "+product.getUid());
                flag=1;
            }
        }
        if(flag==0){
            for(Product product: Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().keySet()){
                Warehouse prod_ware= Database.getDatabase().getStoreHashMap().get(uid).getCart().getWareprod().get(product);
                Product ware_prod = Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getProductHashMap().get(product.getUid());
                prod_ware.getProductSold().put(ware_prod, cart.getCartList().get(product));
                if(Database.getDatabase().getStoreHashMap().get(uid).getInventory().containsKey(product.getUid())){
                    Product prod_ware1 = Database.getDatabase().getWarehouseHashMap().get(uid).getProductHashMap().get(product.getUid());
                    Database.getDatabase().getStoreHashMap().get(uid).categoriesList.get("Main").getProduct_list().add(ware_prod);
                    //Inventory.put(prod_ware1, Database.getDatabase().getWarehouseHashMap().get(uid).getInventory().get(prod_ware1)+Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().get(product));
                    Database.getDatabase().getStoreHashMap().get(uid).getInventory().put(prod_ware1, Database.getDatabase().getStoreHashMap().get(uid).getInventory().get(prod_ware1)+Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().get(product));
                    Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().put(ware_prod, Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().get(ware_prod) - Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().get(product));
                }
                else {
                    Product init = product;
                    init.setParent(Database.getDatabase().getStoreHashMap().get(uid).categoriesList.get("Main"));
                    Database.getDatabase().getStoreHashMap().get(uid).categoriesList.get("Main").getProduct_list().add(init);
                    //Inventory.put(product, Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product));
                    Database.getDatabase().getStoreHashMap().get(uid).getInventory().put(product, Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().get(product));
                    Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().put(ware_prod, Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().get(ware_prod) - Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().get(product));
                }
                Database.getDatabase().getStoreHashMap().get(uid).cart = new Cart();
            }
        }
        serialize();
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

    void Order(Cart cart){

    }

    void orderAuto(){

    }


    public void deleteProduct(String product) {
        Inventory.remove(product);
        for(String cat :categoriesList.keySet()){
            for(Product product1: categoriesList.get(cat).getProduct_list()){
                if(product1.getUid().equals(product)){
                    categoriesList.get(cat).getProduct_list().remove(product1);
                }
            }
        }
    }

    public void deleteProdCart(String name) {
        Product temp = null;
        for(Product product : cart.getCartList().keySet()) {
            if(product.getUid().equals(name)) {
                temp=product;
            }
        }
        cart.getCartList().remove(temp);
        cart.getWareprod().remove(temp);
        for(Product temp1: cart.getCartList().keySet()){
            if(temp1.getUid().equals(temp)){
                temp = temp1;
            }
        }
        cart.getCartList().remove(temp);
        cart.getWareprod().remove(temp);
        serialize();
    }

    public void search(String name, String cat){

    }
}
