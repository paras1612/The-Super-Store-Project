package mainClasses;

import java.io.Serializable;

import static sample.Main.deserialize;

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
        Warehouse init = new Warehouse(warehouseName);
        database.getWarehouseHashMap().put(warehouseName,init);
    }
    public void createStore(String StoreName){
        Store init = new Store(StoreName);
        database.getStoreHashMap().put(StoreName,init);
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
}
