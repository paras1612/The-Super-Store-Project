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

    public Warehouse getAssigned_ware() {
        return assigned_ware;
    }

    public void setAssigned_ware(Warehouse assigned_ware) {
        this.assigned_ware = assigned_ware;
    }

}
