package mainClasses;



import java.io.Serializable;
import java.util.HashMap;

public class Auth implements Serializable {
    private static final long serialVersionUID=7L;
    private HashMap<String, String> clientAuth;
    private HashMap<String, String> storeAdminAuth;
    private HashMap<String, String> warehouseAdminAuth;
    private HashMap<String, String> superUserAdminAuth;
    Auth(){
        clientAuth = new HashMap<String, String>();
        storeAdminAuth = new HashMap<String, String>();
        warehouseAdminAuth = new HashMap<String, String>();
        superUserAdminAuth = new HashMap<String, String>();
    }

    int login(String uid, String password){
        System.out.println(uid+password);
        if(clientAuth.containsKey(uid)){
            System.out.println("e");
            if(clientAuth.get(uid).equals(password)){
                return 1;
            }
        }
        else if(warehouseAdminAuth.containsKey(uid)){
            System.out.println("e");
            if(warehouseAdminAuth.get(uid).equals(password)){
                return 2;
            }
        }
        else if(storeAdminAuth.containsKey(uid)){
            System.out.println("e");
            if(storeAdminAuth.get(uid).equals(password)){
                return 3;
            }
        }
        else if(superUserAdminAuth.containsKey(uid)){
            System.out.println("e");
            if(superUserAdminAuth.get(uid).equals(password)){
                return 4;
            }
        }
        return -1;

    }

    public HashMap<String, String> getclientAuth() {
        return clientAuth;
    }

    public HashMap<String, String> getstoreAdminAuth() {
        return this.storeAdminAuth;
    }

    public HashMap<String, String> getwarehouseAdmintAuth() {
        return this.warehouseAdminAuth;
    }

    public HashMap<String, String> getSuperUserAdminAuth() {
        return superUserAdminAuth;
    }
}
