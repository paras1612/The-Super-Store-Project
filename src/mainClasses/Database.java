package mainClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Database implements Serializable {
    private ArrayList<Product> ProductList=new ArrayList<>();
    private ArrayList<Categories> CategoriesList = new ArrayList<>();
    private HashMap<Warehouse, ArrayList<Categories>> Warehouse_categ = new HashMap<>();
    private HashMap<Store, ArrayList<Categories>> Store_categ = new HashMap<>();
    private ArrayList<Client> ClientList = new ArrayList<>();
    private ArrayList<Warehouse> WarehouseList = new ArrayList<>();
    private ArrayList<Store> StoreList = new ArrayList<>();
    private HashMap<Store, HashMap<Product, Integer>> Store_Product = new HashMap<>();
    private HashMap<Warehouse, HashMap<Product, Integer>> Warehouse_Product = new HashMap<>();
    private static final long serialVersionUID=7L;
    private Auth auth = new Auth();
    
    public void createClient(String name, String password, String email){
    //    System.out.println("Entered");
        if(auth.getCredentials().containsKey(email)){
            System.out.println("Email already registered");
        }
        else {
      //      System.out.println("here");
            Client init = new Client(name, password, email);
            ClientList.add(init);
            auth.getCredentials().put(email, password);
        }
    }
    public boolean login(String uid, String password){
        if(auth.login(uid, password)){
            return true;
        }
        return false;
    }
    void addProduct(Warehouse_Admin admin, Product product, int quant){
        if(admin.getClass().equals("Warehouse")){
            ProductList.add(product);
            admin.getAssigned_ware().getInventory().put(product, quant);
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
    void addCategory(){

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
    void search(){

    }
    void sort(){

    }
    public ArrayList<Product> getProductList() {
        return ProductList;
    }

    public ArrayList<Categories> getCategoriesList() {
        return CategoriesList;
    }

}
