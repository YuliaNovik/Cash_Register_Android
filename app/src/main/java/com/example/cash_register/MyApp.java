package com.example.cash_register;

import android.app.Application;

import java.util.ArrayList;

public class MyApp  extends Application {
    ArrayList<StoreItem> stockList;
    static ArrayList<PurchaseHistory> historyList;
    @Override
    public void onCreate() {
        super.onCreate();
        historyList = new ArrayList<>(); // Initialize the historyList
    }
    public static void addToHistoryList(PurchaseHistory purchaseHistory) {
       historyList.add(purchaseHistory);
    }



    public ArrayList<StoreItem> getStockList(){
        if(stockList == null) {
            stockList = new ArrayList<>(4);
            stockList.add(new StoreItem("Pants", 55.60, 25));
            stockList.add(new StoreItem("Shirts", 39.55, 57));
            stockList.add(new StoreItem("Shoes", 85.00, 50));
            stockList.add(new StoreItem("Hats", 27, 35));
        }
        return stockList;
    }
}
