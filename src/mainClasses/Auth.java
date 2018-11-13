package mainClasses;

import java.io.Serializable;
import java.util.HashMap;

public class Auth implements Serializable {
    private static final long serialVersionUID=7L;
    private HashMap<String, String> credentials;
    Auth(){
        credentials = new HashMap<String, String>();
    }

    boolean login(String uid, String password){
        System.out.println(uid+password);
        if(credentials.containsKey(uid)){
            System.out.println("e");
            if(credentials.get(uid).equals(password)){
                return true;
            }
        }
        return false;
    }

    public HashMap<String, String> getCredentials() {
        return credentials;
    }
}
