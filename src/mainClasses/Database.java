package mainClasses;

import Exceptions.duplicateClientException;
import Exceptions.duplicateStoreAdminException;
import Exceptions.duplicateWarehouseAdminException;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

import static sample.Main.deserialize;

public class Database implements Serializable {
    private static Database database = null;
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

    public static Database getDatabase() {
        if(database==null){
            database =deserialize();
            return database;
        }
        return database;
    }

    public static void setDatabase(Database database) {
        Database.database = database;
    }

    public boolean createSuperuser(String name, String password, String email)  {
        if(auth.getSuperUserAdminAuth().containsKey(email) || auth.getstoreAdminAuth().containsKey(name) || auth.getwarehouseAdmintAuth().containsKey(name) || auth.getclientAuth().containsKey(email)){
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

    public boolean createStoreAdmin(String name, String password, String store) throws duplicateStoreAdminException {
        if((auth.getSuperUserAdminAuth().containsKey(name) || auth.getstoreAdminAuth().containsKey(name) || auth.getwarehouseAdmintAuth().containsKey(name) || auth.getclientAuth().containsKey(name)) && getStore_AdminHashMap().get(name).getAssignedStore()!=null){
//            System.out.println("Email already registered");s
        }
        else {
            //      System.out.println("here");
            Store_Admin init = new Store_Admin(name, password,getStoreHashMap().get(store));
            getStore_AdminHashMap().put(name,init);
            System.out.println(getStore_AdminHashMap().toString());
            auth.getstoreAdminAuth().put(name, password);
            return true;
        }
        return false;
    }
    public boolean createWarehouseAdmin(String name, String password, String warehouse) throws duplicateWarehouseAdminException {
        if((auth.getSuperUserAdminAuth().containsKey(name) || auth.getstoreAdminAuth().containsKey(name) || auth.getwarehouseAdmintAuth().containsKey(name) || auth.getclientAuth().containsKey(name)) && getWarehouse_AdminHashMap().get(name).getAssigned_ware()!=null){
            System.out.println("Email already registered");
        }
        else {
            //      System.out.println("here");
            Warehouse_Admin init = new Warehouse_Admin(name, password, getWarehouseHashMap().get(warehouse));
            getWarehouse_AdminHashMap().put(name,init);
            System.out.println(getWarehouse_AdminHashMap().toString());
            auth.getwarehouseAdmintAuth().put(name, password);
            return true;
        }
        return false;
    }

    public boolean createClient(String name, String password, String email) throws duplicateClientException {
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
        else if(auth.login(uid, password)==2){
            return 2;
        }
        else if(auth.login(uid, password)==3){
            return 3;
        }
        else if(auth.login(uid, password)==4){
            return 4;
        }
        return -1;
    }


    public Pair<ArrayList<String>, ArrayList<String>> search(String name, String Store, String cat1, ArrayList<String> product_result, ArrayList<String> cat_result) {
        for (String cat : StoreHashMap.get(Store).getCategoriesList().keySet()) {
            for (Product product : StoreHashMap.get(Store).getCategoriesList().get(cat).getProduct_list()) {
                if (product.getUid().contains(name)) {
                    product_result.add(product.getUid());
                }
            }
            for (Categories categories : StoreHashMap.get(Store).getCategoriesList().get(cat).getSubCategories()) {
                if (categories.getUid().contains(name)) {
                    cat_result.add(categories.getUid());
                }
            }
        }
        return new Pair<>(product_result, cat_result);
    }


    public Pair<ArrayList<String>, ArrayList<String>> searchWare(String name, String ware, String cat1, ArrayList<String> product_result, ArrayList<String> cat_result){
        for(String cat: WarehouseHashMap.get(ware).getCategoryHashMap().keySet()) {
            for (Product product : WarehouseHashMap.get(ware).getCategoryHashMap().get(cat).getProduct_list()) {
                if (product.getUid().contains(name)) {
                    product_result.add(product.getUid());
                }
            }
            for (Categories categories : WarehouseHashMap.get(ware).getCategoryHashMap().get(cat).getSubCategories()) {
                if (categories.getUid().contains(name)) {
                    cat_result.add(categories.getUid());
                }
            }
        }
        return new Pair<>(product_result, cat_result);
    }

//    public void searchWare(String name, String ware, String cat, ArrayList<String> product_result, ArrayList<String> cat_result){
//        for (Product product : WarehouseHashMap.get(ware).getCategoryHashMap().get(cat).getProduct_list()) {
//            if (product.getUid().contains(name)) {
//                product_result.add(product.getUid());
//            }
//        }
//        for (Categories categories : WarehouseHashMap.get(ware).getCategoryHashMap().get(cat).getSubCategories()) {
//            if (categories.getUid().contains(name)) {
//                cat_result.add(categories.getUid());
//            }
//        }
//    }


    public ArrayList<String> sort(ArrayList<String> list){
        list.sort(String::compareTo);
        return list;
    }

    public HashMap<String, Client> getClientHashMap() {
        return ClientHashMap;
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
