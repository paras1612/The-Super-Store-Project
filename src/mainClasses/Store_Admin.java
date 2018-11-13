package mainClasses;

import java.io.Serializable;

public class Store_Admin extends Admin implements Serializable {
    private static final long serialVersionUID=7L;
    private Store assignedStore;

    protected Store_Admin(String uid) {
        super(uid);
    }

    void Check_Data(){

    }

    public void setAssignedStore(Store assignedStore) {
        this.assignedStore = assignedStore;
    }

    public Store getAssignedStore() {
        return assignedStore;
    }
}
