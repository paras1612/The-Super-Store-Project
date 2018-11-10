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
    void deleteProduct(){

    }
    void updateProduct(){

    }
    void addCategory(){

    }
    void delCategory(){

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
