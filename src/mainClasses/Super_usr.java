package mainClasses;

import java.io.Serializable;

import static sample.Main.deserialize;
import static sample.Main.serialize;

public class Super_usr implements Serializable {
    private static final long serialVersionUID=7L;
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
        Warehouse init = new Warehouse(warehouseName);
        System.out.println(Database.getDatabase());
        Database.getDatabase().getWarehouseHashMap().put(warehouseName,init);
        serialize();
    }
    public void createStore(String StoreName){
        Store init = new Store(StoreName);
        Database.getDatabase().getStoreHashMap().put(StoreName,init);
        serialize();
    }
    public void createWarehouseAdmin(String name, String pass,  String warehouse){
        Database.getDatabase().createWarehouseAdmin(name,pass,warehouse);
        serialize();
    }
    public void createStoreAdmin(String name, String pass,  String store){
        Database.getDatabase().createStoreAdmin(name,pass,store);
        System.out.println("Serialized");
        serialize();
    }
    public String getName() {
        return Name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void removeWarehouse(String warehouse) {
        for(String admin : Database.getDatabase().getWarehouse_AdminHashMap().keySet()){
            if(Database.getDatabase().getWarehouse_AdminHashMap().get(admin).getAssigned_ware()!=null) {
                if (Database.getDatabase().getWarehouse_AdminHashMap().get(admin).getAssigned_ware().getUid().equals(warehouse)) {
                    Database.getDatabase().getWarehouse_AdminHashMap().get(admin).setAssigned_ware(null);
                }
            }
        }
        Database.getDatabase().getWarehouseHashMap().remove(warehouse);
        serialize();
        //Database temp =deserialize();
        //System.out.println(temp);
    }
    public void removeStore(String store) {
        for(String admin : Database.getDatabase().getStore_AdminHashMap().keySet()){
            if(Database.getDatabase().getStore_AdminHashMap().get(admin).getAssignedStore()!=null) {
                if (Database.getDatabase().getStore_AdminHashMap().get(admin).getAssignedStore().getUid().equals(store)) {
                    Database.getDatabase().getStore_AdminHashMap().get(admin).setAssignedStore(null);
                }
            }
        }
        Database.getDatabase().getStoreHashMap().remove(store);
        serialize();
        Database temp =deserialize();
        System.out.println(temp);
    }
}
