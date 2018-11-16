package mainClasses;

import java.io.Serializable;

import static sample.Main.deserialize;
import static sample.Main.serialize;

public class Super_usr implements Serializable {
    private static final long serialVersionUID=7L;
    private Database database= deserialize();
    private String Name;
    private String uid;
    private String password;
    private String message;
    public Super_usr(String name, String id, String pass){
        Name=name;
        uid=id;
        password=pass;
    }
    public void createWarehouse(String warehouseName){
        database=deserialize();
        Warehouse init = new Warehouse(warehouseName);
        //init.getCategoryHashMap().put("Main",new Categories("SuperStore"));
        System.out.println(database);


        database.getWarehouseHashMap().put(warehouseName,init);
        serialize(database);
    }
    public void createStore(String StoreName){
        Store init = new Store(StoreName);
        database=deserialize();
        database.getStoreHashMap().put(StoreName,init);
        serialize(database);
    }
    public void createWarehouseAdmin(String name, String pass,  String warehouse){
        database=deserialize();
        database.createWarehouseAdmin(name,pass,warehouse);
        serialize(database);
    }
    public void createStoreAdmin(String name, String pass,  String store){
        database=deserialize();
        database.createStoreAdmin(name,pass,store);
        System.out.println("Serialized");
        serialize(database);
    }
    public String getName() {
        return Name;
    }

    public Database getDatabase() {
        return database;
    }

    public void updateDatabase() {
        this.database = deserialize();
    }
    public void updateDatabase(Database database) {
        this.database = database;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void removeWarehouse(String warehouse) {
        updateDatabase();
        for(String admin : getDatabase().getWarehouse_AdminHashMap().keySet()){
            if(getDatabase().getWarehouse_AdminHashMap().get(admin).getAssigned_ware()!=null) {
                if (getDatabase().getWarehouse_AdminHashMap().get(admin).getAssigned_ware().getUid().equals(warehouse)) {
                    getDatabase().getWarehouse_AdminHashMap().get(admin).setAssigned_ware(null);
                }
            }
        }
        getDatabase().getWarehouseHashMap().remove(warehouse);
        serialize(getDatabase());
        //Database temp =deserialize();
        //System.out.println(temp);
    }
    public void removeStore(String store) {
        updateDatabase();
        for(String admin : getDatabase().getStore_AdminHashMap().keySet()){
            if(getDatabase().getStore_AdminHashMap().get(admin).getAssignedStore()!=null) {
                if (getDatabase().getStore_AdminHashMap().get(admin).getAssignedStore().getUid().equals(store)) {
                    getDatabase().getStore_AdminHashMap().get(admin).setAssignedStore(null);
                }
            }
        }
        getDatabase().getStoreHashMap().remove(store);
        serialize(getDatabase());
        Database temp =deserialize();
        System.out.println(temp);
    }
}
