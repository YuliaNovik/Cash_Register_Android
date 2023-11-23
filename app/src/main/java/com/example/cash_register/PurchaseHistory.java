package com.example.cash_register;

public class PurchaseHistory extends StoreItem{
    private double totalP;
    private int totalQ;



    public PurchaseHistory(String description, double price, int quantity, double totalP, int totalQ) {
        super(description, price, quantity);
        this.totalP = totalP;
        this.totalQ = totalQ;
    }

    public double getTotalP() {
        return totalP;
    }

    public void setTotalP(double totalP) {
        this.totalP = totalP;
    }

    public void setTotalQ(int totalQ) {
        this.totalQ = totalQ;
    }

    public int getTotalQ() {
        return totalQ;
    }


    public PurchaseHistory(String description, double price, int quantity) {
        super(description, price, quantity);
    }
}
