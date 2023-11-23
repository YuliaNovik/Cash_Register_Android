package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class HistoryList extends AppCompatActivity implements RecyclerViewInterface{

    RecyclerView  historyRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        ArrayList<PurchaseHistory> productData = ((MyApp)getApplication()).historyList;

        historyRecyclerView = findViewById(R.id.recyclerHistoryList);
        HistoryListRecyclerAdaptor adaptor = new HistoryListRecyclerAdaptor(productData, this, this);
        historyRecyclerView.setAdapter(adaptor);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClick(int position) {
        PurchaseHistory selectedHistory = ((MyApp)getApplication()).historyList.get(position);

//        Intent toHistoryDetails = new Intent(HistoryList.this, PurchaseDetailActivity.class);
//        String msg = "Product Name: " + selectedHistory.getDescription() + "/nTotal Price: " + selectedHistory.getTotalP();
//        toHistoryDetails.putExtra("purchase_details", msg);
        // Handle item click here


        // Example: Show a detail activity with the selected item details
        Intent intent = new Intent(this, PurchaseDetailActivity.class);

        intent.putExtra("purchase_details", "Product Name: " + selectedHistory.getDescription() + ". Total Price:  " +  (int)(Math.round(selectedHistory.getTotalP() * 100))/100.0 );
        startActivity(intent);
    }
}