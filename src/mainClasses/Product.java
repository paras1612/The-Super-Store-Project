package mainClasses;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID=7L;
    private String Name;
    private double Price;
    private int Quantity;
    private final String uid;
    private Categories parent;
    private double fCostQuater;
    private double cCostQuater;
    private double itemDemand;
    private double EOQ;

    public Product(String uid, double price, int quantity, double fcost, double ccost, double idemand) {
        this.uid = uid;
        Name= uid;
        Price= price;
        Quantity= quantity;
        fCostQuater= fcost;
        cCostQuater= ccost;
        itemDemand= idemand;
        EOQ=calcEOQ(fcost,ccost,idemand);
    }

    public Product() {
        Name = "a";
        Price = 420;
        Quantity = 10;
        this.uid = "a101";
        this.fCostQuater = 3;
        this.cCostQuater = 3;
        this.itemDemand = 3;
        this.EOQ = calcEOQ(fCostQuater,cCostQuater,itemDemand);
    }



    double calcEOQ(double fCostQuater, double cCostQuater, double itemDemand) throws IllegalArgumentException{
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
