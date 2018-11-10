package mainClasses;

import java.io.Serializable;

public class Product implements Serializable {
    private String Name;
    private double Price;
    private int Quantity;
    private final String uid;
    private Categories parent;
    private double fCostQuater;
    private double cCostQuater;
    private double itemDemand;
    private double EOQ;

    public Product(String uid) {
        this.uid = uid;
    }

    double calcEOQ(double fCostQuater, double cCostQuater, double itemDemand){
        return Math.sqrt((2*fCostQuater*itemDemand)/cCostQuater);
    }

    public String getName() {
        return Name;
    }

    public double getcCostQuater() {
        return cCostQuater;
    }

    public double getEOQ() {
        return EOQ;
    }

    public double getfCostQuater() {
        return fCostQuater;
    }

    public double getItemDemand() {
        return itemDemand;
    }

    public double getPrice() {
        return Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public String getUid() {
        return uid;
    }

    public Categories getParent() {
        return parent;
    }

    public void setParent(Categories parent) {
        this.parent = parent;
    }

    public void setcCostQuater(double cCostQuater) {
        this.cCostQuater = cCostQuater;
    }

    public void setfCostQuater(double fCostQuater) {
        this.fCostQuater = fCostQuater;
    }

    public void setItemDemand(double itemDemand) {
        this.itemDemand = itemDemand;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
