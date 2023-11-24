package com.example.cash_register;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class PurchaseHistory extends StoreItem{
    private double totalP;
    private int totalQ;

    private String timestamp;


    public String getTimestamp() {
        return timestamp;
    }

    public PurchaseHistory(String description, double price, int quantity, double totalP, int totalQ, String time) {
        super(description, price, quantity);
        this.totalP = totalP;
        this.totalQ = totalQ;
        this.timestamp = time;
    }

    public double getTotalP() {
        return totalP;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
