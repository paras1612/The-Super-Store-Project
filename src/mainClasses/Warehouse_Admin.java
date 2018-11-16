package mainClasses;

import java.io.Serializable;

import static sample.Main.deserialize;
import static sample.Main.serialize;

public class Warehouse_Admin extends Admin implements Serializable {
    private static final long serialVersionUID=7L;
    private Warehouse assigned_ware;

    protected Warehouse_Admin(String uid, String pass, Warehouse warehouse) {
        super(uid);
        Password=pass;
        assigned_ware= warehouse;
    }
    void Check_Data_allWare(){
    }
    public Warehouse getAssigned_ware() {
        return assigned_ware;
    }

    public void setAssigned_ware(Warehouse assigned_ware) {
        this.assigned_ware = assigned_ware;
    }

    @Override
    public void add_product(String warehouse, String name, Integer quantity) {
        assigned_ware.updateData();
        Product temp = assigned_ware.getDatabase().getWarehouseHashMap().get(warehouse).getProductHashMap().get(name);
        Product init = new Product(temp.getName(), temp.getPrice(), quantity, temp.getfCostQuater(), temp.getcCostQuater(), temp.getItemDemand());
        assigned_ware.getCart().getCartList().put(init, quantity);
        serialize(assigned_ware.getDatabase());
        Database hello = deserialize();
        System.out.println(hello);
    }
}
