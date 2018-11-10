package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

public class Auth implements Serializable {
    private HashMap<String, String> credentials;
    Auth(){
        credentials = new HashMap<String, String>();
    }
    boolean login(String uid, String password){
        if(credentials.get(uid)==password){
            return true;
        }
        return false;
    }

    public HashMap<String, String> getCredentials() {
        return credentials;
    }
}
