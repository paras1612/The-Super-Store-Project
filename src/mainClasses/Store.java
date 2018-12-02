package mainClasses;

import Exceptions.categoryNotFoundException;
import Exceptions.duplicateCategoryException;
import Exceptions.insufficientQty;
import Exceptions.productNotFoundException;

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
    public void setCart(Cart cart){
        this.cart=cart;
    }

    public boolean addProduct(String product, String parent) throws insufficientQty {
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
            Inventory.put(one,(int)curr.getEOQ());
            Database.getDatabase().getWarehouseHashMap().get(linkware.getUid()).getProductHashMap().get(curr.getUid()).setQuantity(curr.getQuantity()-(int)curr.getEOQ());
            Database.getDatabase().getWarehouseHashMap().get(linkedWarehouse.getUid()).getInventory().put(curr,curr.getQuantity()-(int)curr.getEOQ());
        }
        else {
            System.out.println("Product quantity not available");
            setMessage(getMessage()+"Some Product not available in Warehouse: Order cancelled" );
        }
        serialize();
        return true;
    }
    public boolean addCategory(Store_Admin admin, String name, String parent) throws duplicateCategoryException {
        Categories init= new Categories(name);
        if(categoriesList.containsKey(name)){
            System.out.println("cat exists");
            return false;
        }
        init.setParent(Database.getDatabase().getStoreHashMap().get(this.uid).getCategoriesList().get(parent));
        Database.getDatabase().getStoreHashMap().get(this.uid).getCategoriesList().get(parent).getSubCategories().add(init);
        Database.getDatabase().getStoreHashMap().get(this.uid).getCategoriesList().put(name,init);
        categoriesList.put(name, init);
        serialize();
        return true;
    }
    public void add_product(String warehouse, String name, Integer quantity) {
        Product temp =Database.getDatabase().getWarehouseHashMap().get(warehouse).getProductHashMap().get(name);
        int flag=0;
        for(Product product: cart.getCartList().keySet()){
            if(product.getName().equals(name) && cart.getWareprod().get(product).getUid().equals(warehouse)){
                cart.getCartList().put(product, cart.getCartList().get(product)+quantity);
                flag++;
            }
        }
        if(flag==0){
            Product prod = new Product(temp.getName(), temp.getPrice(), quantity, temp.getfCostQuater(), temp.getcCostQuater(), temp.getItemDemand());
            cart.getCartList().put(prod, quantity);
            cart.getWareprod().put(prod, this.linkedWarehouse);
        }
        serialize();
    }

    void Generate_Alert(){

    }

    public void checkout() throws insufficientQty {
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
                Product store_prod = ware_prod;
                for(Product product1: Inventory.keySet()){
                    if(product.getName().equals(product1.getUid())){
                        store_prod = product1;
                    }
                }
                if(Database.getDatabase().getStoreHashMap().get(uid).getInventory().containsKey(store_prod)){
                    Product prod_ware1 = Database.getDatabase().getWarehouseHashMap().get(cart.getWareprod().get(product).getUid()).getProductHashMap().get(product.getUid());
                    Database.getDatabase().getStoreHashMap().get(uid).categoriesList.get("Main").getProduct_list().add(ware_prod);
                    //Inventory.put(prod_ware1, Database.getDatabase().getWarehouseHashMap().get(uid).getInventory().get(prod_ware1)+Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().get(product));
                    Inventory.put(store_prod, Inventory.get(store_prod)+cart.getCartList().get(product));
                    Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().put(ware_prod, Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().get(ware_prod) - cart.getCartList().get(product));
                }
                else {
                    Product init = product;
                    init.setParent(Database.getDatabase().getStoreHashMap().get(uid).categoriesList.get("Main"));
                    Database.getDatabase().getStoreHashMap().get(uid).categoriesList.get("Main").getProduct_list().add(init);
                    //Inventory.put(product, Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product));
                    Inventory.put(store_prod,cart.getCartList().get(product));
                    Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().put(ware_prod, Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().get(ware_prod) - Database.getDatabase().getStoreHashMap().get(uid).getCart().getCartList().get(product));
//                    Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).setMessage(Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getMessage()+"Product: "+product.getName()+"\tQuantity"+cart.getCartList().get(product)+"\n");
                }
                prod_ware.setMessage(prod_ware.getMessage()+"Product Name: "+product.getName() + "\t" + "Quantity: "+ cart.getCartList().get(product)+"\n");
            }
            Database.getDatabase().getStoreHashMap().get(uid).cart = new Cart();
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
        orderAuto();
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    void Order(Cart cart){

    }
//Automatically surrounded with try catch
    void orderAuto(){
        System.out.println("Auto Ordered");
        for(Product product: Inventory.keySet()){
            if(Inventory.get(product)<product.getEOQ()){
                try {
                    addProduct(product.getUid(), product.getParent().getUid());
                } catch (Exceptions.insufficientQty insufficientQty) {
                    insufficientQty.printStackTrace();
                }
            }
        }
    }


    public void deleteProduct(String product) throws productNotFoundException {
        String cat1 = "";
        Product product2=new Product();
        for(String cat :categoriesList.keySet()){
            for(Product product1: categoriesList.get(cat).getProduct_list()){
                if(product1.getUid().equals(product)){
                    cat1=cat;
                    Inventory.remove(product1);
                    product2=product1;
                }
            }
        }
        categoriesList.get(cat1).getProduct_list().remove(product2);
        serialize();
    }

    public void deleteProdCart(String name) throws productNotFoundException {
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
        if(temp!=null) {
            cart.getCartList().remove(temp);
            cart.getWareprod().remove(temp);
        }
        else{
            
            throw new productNotFoundException("Not found");
        }
        serialize();
    }

    public void search(String name, String cat){

    }

    public boolean removeCategory(String category) throws categoryNotFoundException {
        try {
            for (String cat : categoriesList.keySet()) {
                if (categoriesList.get(cat).getSubCategories().contains(categoriesList.get(category))) {
                    categoriesList.get(cat).getSubCategories().remove(category);

                }
            }
            for (Categories categories : categoriesList.get(category).getSubCategories()) {
                categoriesList.remove(categories);
            }
            for (Product product : categoriesList.get(category).getProduct_list()) {
                Inventory.remove(product);
            }
            categoriesList.remove(category);
            serialize();
            return true;
        }
        catch (NullPointerException e){
            throw new categoryNotFoundException("Category not found");
        }
    }
}
