package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PurchaseDetailActivity extends AppCompatActivity {

    TextView purchaseDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);
        purchaseDetails = findViewById(R.id.tvPurchaseDetails);

       String message = getIntent().getExtras().getString("purchase_details");
       purchaseDetails.setText(message);
    }
}