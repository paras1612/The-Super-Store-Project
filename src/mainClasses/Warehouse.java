package mainClasses;

import Exceptions.duplicateCategoryException;
import Exceptions.productNotFoundException;

import java.io.Serializable;
import java.util.HashMap;
import java.util.SortedMap;

import static sample.Main.deserialize;
import static sample.Main.serialize;

public class Warehouse implements Serializable {
    private static final long serialVersionUID=7L;
    private final String uid;
    private Cart cart = new Cart();
    private HashMap<Product,Integer> Inventory = new HashMap<>();
    private HashMap<String, Categories> CategoryHashMap= new HashMap<>();
    private HashMap<String, Product> ProductHashMap= new HashMap<>();
    private String Message;
    private HashMap<Product, Integer> ProductSold = new HashMap<>();
    private double sale;

    public Warehouse(String uid) {
        serialize();
        CategoryHashMap.put("Main", new Categories("SuperStore"));
        this.uid = uid;
        serialize();
    }
    public boolean addCategory(Warehouse_Admin admin, String name, String parent)throws duplicateCategoryException{
        System.out.println(uid);
        System.out.println(Database.getDatabase().getWarehouseHashMap().get(uid));
        Categories init=new Categories(name);
        init.setParent(CategoryHashMap.get(parent));
        if(CategoryHashMap.containsKey(name)){
            throw new duplicateCategoryException("");
//            return false;
        }
        Database.getDatabase().getWarehouseHashMap().get(uid).CategoryHashMap.get(parent).getSubCategories().add(init);
        Database.getDatabase().getWarehouseHashMap().get(uid).CategoryHashMap.put(name, init);
        CategoryHashMap.put(name, init);
        serialize();
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
    void orderAuto(){
        Cart tempCart = this.cart;
        Cart temp2 = new Cart();
        for(Product product: Inventory.keySet()){
            if(Inventory.get(product)<product.getEOQ()){
                double price = Double.POSITIVE_INFINITY;
                Warehouse finalware = null;
                for(String warehouse: Database.getDatabase().getWarehouseHashMap().keySet()){
                    Warehouse temp = Database.getDatabase().getWarehouseHashMap().get(warehouse);
                    Product wareprod= temp.getProductHashMap().get(product.getUid());
                    if(temp.getInventory().get(wareprod)>product.getEOQ()){
                        //order can be placed
                        if(temp.getProductHashMap().get(product).getPrice()<price){
                            price=temp.getProductHashMap().get(product).getPrice();
                            finalware = temp;
                        }
                    }
                }
                temp2.getCartList().put(product, (int)product.getEOQ());
                temp2.getWareprod().put(product, finalware);
                checkout();
            }
        }
        cart= tempCart;
    }
    void send_confirm(){

    }

    public void setCart(Cart cart){
        this.cart=cart;
    }

    public HashMap<String, Categories> getCategoryHashMap() {
        return CategoryHashMap;
    }

    public String getMessage() {
        return Message;
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
        orderAuto();
        Message = message;
    }

    public void deleteProduct(String product) throws productNotFoundException {
        String cat1 = "";
        Product product2=new Product();
        HashMap<String,Categories> c = CategoryHashMap;
        for(String cat :CategoryHashMap.keySet()){

            for(Product product1: CategoryHashMap.get(cat).getProduct_list()){
                if(product1.getUid().equals(product)){
                    product2=product1;
                    cat1=cat;
                    break;
                }
            }
        }
        if(cat1.equals("")){
            throw new productNotFoundException("Not found");
        }
        ProductHashMap.remove(product2.getUid());
        Inventory.remove(product2);
        CategoryHashMap.get(cat1).getProduct_list().remove(product2);
        serialize();
    }

    public boolean addProduct(Warehouse_Admin warehouse_admin, String name, double price, int quant, double fcost, double ccost, double idem, String parent) throws productNotFoundException {
        System.out.println(uid);
        System.out.println(Database.getDatabase().getWarehouseHashMap().get(uid));
        Product init = new Product(name, price, quant, fcost, ccost, idem);
        init.setParent(CategoryHashMap.get(parent));
        if (getProductHashMap().containsKey(name)) {
            throw new productNotFoundException("");
//            return false;
        }
        Database.getDatabase().getWarehouseHashMap().get(uid).CategoryHashMap.get(parent).getProduct_list().add(init);
        Database.getDatabase().getWarehouseHashMap().get(uid).ProductHashMap.put(name, init);
        Database.getDatabase().getWarehouseHashMap().get(uid).getInventory().put(init, quant);
        serialize();
        return true;
    }

    public void add_product(String warehouse, String name, int quantity) {//ADD PRODUCT TO CART
        Product temp = Database.getDatabase().getWarehouseHashMap().get(warehouse).getProductHashMap().get(name);
        Product init = new Product(temp.getName(), temp.getPrice(), quantity, temp.getfCostQuater(), temp.getcCostQuater(), temp.getItemDemand());
        cart.getCartList().put(init, quantity);
        cart.getWareprod().put(init, Database.getDatabase().getWarehouseHashMap().get(warehouse));
        Database.getDatabase().getWarehouseHashMap().get(uid).cart.getCartList().put(init, quantity);
        Database.getDatabase().getWarehouseHashMap().get(uid).cart.getWareprod().put(init, Database.getDatabase().getWarehouseHashMap().get(warehouse));
        serialize();
        Database hello = deserialize();
        System.out.println(hello);
    }

    public void delProdCart(String uid, String name, Integer integer) {
        Product temp = null;
        for(Product product : cart.getCartList().keySet()) {
            if(product.getUid().equals(name)) {
                temp=product;
            }
        }
        cart.getCartList().remove(temp);
        cart.getWareprod().remove(temp);
        for(Product temp1: Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().keySet()){
            if(temp1.getUid().equals(temp)){
                temp = temp1;
            }
        }
        Database.getDatabase().getWarehouseHashMap().get(uid).cart.getCartList().remove(temp);
        Database.getDatabase().getWarehouseHashMap().get(uid).cart.getWareprod().remove(temp);
        serialize();
    }

    public void checkout() {
        int flag=0;
        for(Product product: Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().keySet()){
            Warehouse prod_ware= Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getWareprod().get(product);
            Product ware_prod = Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getProductHashMap().get(product.getUid());
            System.out.println(Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product));
            System.out.println(Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().get(ware_prod));
            if(Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product) > Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().get(ware_prod)){
                System.out.println("Product Out of Stock: "+product.getUid());
                flag=1;
            }
        }
        if(flag==0){
            for(Product product: Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().keySet()){
                Warehouse prod_ware= Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getWareprod().get(product);
                Product ware_prod = Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getProductHashMap().get(product.getUid());
                prod_ware.getProductSold().put(ware_prod, cart.getCartList().get(product));
                if(ProductHashMap.containsKey(product.getUid())){
                    Product prod_ware1 = ProductHashMap.get(product.getUid());
                    CategoryHashMap.get("Main").getProduct_list().add(ware_prod);
                    //Inventory.put(prod_ware1, Database.getDatabase().getWarehouseHashMap().get(uid).getInventory().get(prod_ware1)+Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product));
                    Inventory.put(prod_ware1, Inventory.get(prod_ware1)+cart.getCartList().get(product));
                    Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().put(ware_prod, Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().get(ware_prod) - Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product));
                }
                else {
                    Product init = product;
                    init.setParent(CategoryHashMap.get("Main"));
                    CategoryHashMap.get("Main").getProduct_list().add(init);
                    ProductHashMap.put(product.getUid(), product);
                    //Inventory.put(product, Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product));
                    Inventory.put(product, Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product));
                    Database.getDatabase().getWarehouseHashMap().get(prod_ware).getInventory().put(ware_prod, Database.getDatabase().getWarehouseHashMap().get(prod_ware.getUid()).getInventory().get(ware_prod) - Database.getDatabase().getWarehouseHashMap().get(uid).getCart().getCartList().get(product));
                }
                prod_ware.setMessage(prod_ware.getMessage()+"Product Name: "+product.getName() + "\t" + "Quantity: "+ cart.getCartList().get(product)+"\n");
            }
            cart = new Cart();
        }
        serialize();
    }

    public boolean removeCategory(String category) {
        for(String cat: CategoryHashMap.keySet()){
            if(CategoryHashMap.get(cat).getSubCategories().contains(CategoryHashMap.get(category))){
                CategoryHashMap.get(cat).getSubCategories().remove(category);
            }
        }
        for(Product product: CategoryHashMap.get(category).getProduct_list()){
            ProductHashMap.remove(product.getUid());
        }
        CategoryHashMap.remove(category);
        return true;
    }
}
