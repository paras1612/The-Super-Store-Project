package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable {
    private HashMap<String, Product> ProductList=new HashMap<>();
    private HashMap<String, Categories> CategoriesList = new HashMap<>();
    private HashMap<String, Client> ClientHashMap = new HashMap<>();
    private HashMap<String, Super_usr> Super_userHashMap = new HashMap<>();
    private HashMap<String, Store_Admin> Store_AdminHashMap = new HashMap<>();
    private HashMap<String, Warehouse_Admin> Warehouse_AdminHashMap = new HashMap<>();
    private HashMap<String, Warehouse> WarehouseHashMap = new HashMap<>();
    private HashMap<String, Store> StoreHashMap = new HashMap<>();
    private HashMap<Store, HashMap<Product, Integer>> Store_Product = new HashMap<>();
    private HashMap<Warehouse, HashMap<Product, Integer>> Warehouse_Product = new HashMap<>();
    private static final long serialVersionUID=7L;
    private Auth auth = new Auth();
    public boolean createSuperuser(String name, String password, String email){
        if(auth.getSuperUserAdminAuth().containsKey(email)){
            System.out.println("Email already registered");
        }
        else {
            //      System.out.println("here");
            Super_usr init = new Super_usr(name, password, email);
            getSuper_userHashMap().put(email,init);
            System.out.println(getSuper_userHashMap().toString());
            auth.getSuperUserAdminAuth().put(email, password);
            return true;
        }
        return false;
    }

    public boolean createStoreAdmin(String name, String password, String store){
        if(auth.getstoreAdminAuth().containsKey(name)){
            System.out.println("Email already registered");
        }
        else {
            //      System.out.println("here");
            Store_Admin init = new Store_Admin(name, password);
            getStore_AdminHashMap().put(name,init);
            System.out.println(getStore_AdminHashMap().toString());
            auth.getstoreAdminAuth().put(name, password);
            return true;
        }
        return false;
    }
    public boolean createWarehouseAdmin(String name, String password, String warehouse){
        if(auth.getwarehouseAdmintAuth().containsKey(name)){
            System.out.println("Email already registered");
        }
        else {
            //      System.out.println("here");
            Warehouse_Admin init = new Warehouse_Admin(name, password);
            getWarehouse_AdminHashMap().put(name,init);
            System.out.println(getWarehouse_AdminHashMap().toString());
            auth.getwarehouseAdmintAuth().put(name, password);
            return true;
        }
        return false;
    }

    public boolean createClient(String name, String password, String email){
    //    System.out.println("Entered");
        if(auth.getclientAuth().containsKey(email)){
            System.out.println("Email already registered");
        }
        else {
      //      System.out.println("here");
            Client init = new Client(name, password, email);
            getClientHashMap().put(email,init);
            System.out.println(getClientHashMap().toString());
            auth.getclientAuth().put(email, password);
            return true;
        }
        return false;
    }
    public int login(String uid, String password){
        if(auth.login(uid, password)==1){
            return 1;
        }
        if(auth.login(uid, password)==3){
            return 2;
        }
        if(auth.login(uid, password)==3){
            return 3;
        }
        if(auth.login(uid, password)==4){
            return 4;
        }
        return -1;
    }
    void addProduct(Warehouse_Admin admin, String product, int quant){
        if(admin.getClass().equals("Warehouse")){
            Product currProd = new Product(product);
            ProductList.put(product, currProd);
            admin.getAssigned_ware().getInventory().put(currProd, quant);
            Warehouse_Product.put(admin.getAssigned_ware(), admin.getAssigned_ware().getInventory());
        }
    }
    void deleteProduct(Warehouse_Admin admin, Product prod){
        admin.getAssigned_ware().getInventory().remove(prod);
    }
    void deleteProduct(Store_Admin admin, Product prod){
        admin.getAssignedStore().getInventory().remove(prod);
    }
    void updateProduct(Warehouse_Admin admin, Product product, double price, int quant, double fcost, double ccost, double idem){
        admin.getAssigned_ware().getInventory().remove(product);
        Product product1= new Product(product.getUid());
        product1.setPrice(price);
        product1.setQuantity(quant);
        product1.setfCostQuater(fcost);
        product1.setcCostQuater(ccost);
        product1.setItemDemand(idem);
        admin.getAssigned_ware().getInventory().put(product1, quant);
    }
    void updateProduct(Store_Admin admin, Product product, double price, int quant, double fcost, double ccost, double idem){
        admin.getAssignedStore().getInventory().remove(product);
        Product product1= new Product(product.getUid());
        product1.setPrice(price);
        product1.setQuantity(quant);
        product1.setfCostQuater(fcost);
        product1.setcCostQuater(ccost);
        product1.setItemDemand(idem);
        admin.getAssignedStore().getInventory().put(product1, quant);
    }
    void addCategory(Warehouse_Admin current, String s){

    }
    void delCategory(Warehouse_Admin admin, Categories category){
        if(admin.getAssigned_ware().getCategoriesList().contains(category)){
            admin.getAssigned_ware().getCategoriesList().remove(category);
        }
    }
    void delCategory(Store_Admin admin, Categories category){
        if(admin.getAssignedStore().getCategoriesList().contains(category)){
            admin.getAssignedStore().getCategoriesList().remove(category);
        }
    }
    void updateCategory(){

    }
    void search(String name){

    }
    void sort(){

    }

    public HashMap<String, Client> getClientHashMap() {
        return ClientHashMap;
    }

    public HashMap<String, Product> getProductList() {
        return ProductList;
    }

    public HashMap<String, Categories> getCategoriesList() {
        return CategoriesList;
    }

    public HashMap<String, Warehouse_Admin> getWarehouse_AdminHashMap() {
        return Warehouse_AdminHashMap;
    }

    public HashMap<String, Super_usr> getSuper_userHashMap() {
        return Super_userHashMap;
    }

    public HashMap<String, Store_Admin> getStore_AdminHashMap() {
        return Store_AdminHashMap;
    }

    public HashMap<String, Store> getStoreHashMap() {
        return StoreHashMap;
    }

    public HashMap<String, Warehouse> getWarehouseHashMap() {
        return WarehouseHashMap;
    }
}
