package mainClasses;

public class Store_Admin extends Admin {
    private Store assignedStore;

    protected Store_Admin(String uid) {
        super(uid);
    }

    void Check_Data(){

    }
    public Store getAssignedStore() {
        return assignedStore;
    }
}
