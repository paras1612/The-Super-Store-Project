package mainClasses;

import java.io.Serializable;

abstract public class Admin implements Serializable {
    private static final long serialVersionUID=7L;
    protected String Name;
    protected String Password;
    protected final String uid;
    protected Admin(String uid) {
        this.uid = uid;
    }

}
