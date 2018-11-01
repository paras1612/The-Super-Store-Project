package mainClasses;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private ArrayList<Product> ProductList;
    private ArrayList<Categories> CategoriesList;
    private HashMap<Warehouse, ArrayList<Categories>> Warehouse_categ;
    private HashMap<Store, ArrayList<Categories>> Store_categ;
    private ArrayList<Client> ClientList;
    private HashMap<Store, ArrayList<Product>> Store_Product;
    private HashMap<Warehouse, ArrayList<Product>> Warehousr_Product;

    void addProduct(){

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
