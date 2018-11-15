package mainClasses;

import java.io.Serializable;
import java.util.ArrayList;

public class Categories implements Serializable {
    private static final long serialVersionUID=7L;
    private String Name;
    private ArrayList<Product> Product_list = new ArrayList<>();
    private ArrayList<Categories> subCategories = new ArrayList<>();
    private Categories Parent;
    private final String uid;

    public Categories(String uid) {
        this.uid = uid;
        Name =uid;
    }

    public Categories getParent() {
        return Parent;
    }

    public void setParent(Categories parent) {
        Parent = parent;
    }

    public String getUid() {
        return uid;
    }

    public ArrayList<Categories> getSubCategories() {
        return subCategories;
    }

    public ArrayList<Product> getProduct_list() {
        return Product_list;
    }

    public String getName() {
        return Name;
    }
}
