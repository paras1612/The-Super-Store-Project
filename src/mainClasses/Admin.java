package mainClasses;

abstract public class Admin {
    protected String Name;
    protected String Password;
    protected final String uid;
    protected Admin(String uid) {
        this.uid = uid;
    }
}
