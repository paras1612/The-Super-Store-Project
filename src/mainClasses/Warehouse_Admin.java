package mainClasses;

import java.io.Serializable;

public class Warehouse_Admin extends Admin implements Serializable {
    private static final long serialVersionUID=7L;
    private Warehouse assigned_ware;

    protected Warehouse_Admin(String uid) {
        super(uid);
    }
    void Check_Data_allWare(){
    }
    public Warehouse getAssigned_ware() {
        return assigned_ware;
    }
}
