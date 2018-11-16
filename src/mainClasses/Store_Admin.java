package mainClasses;

import java.io.Serializable;

import static sample.Main.serialize;

public class Store_Admin extends Admin implements Serializable {
    private static final long serialVersionUID=7L;
    private Store assignedStore;

    protected Store_Admin(String uid, String pass, Store store)
    {
        super(uid);
        Password =pass;
        assignedStore=store;
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
