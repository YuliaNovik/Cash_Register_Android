package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerView extends AppCompatActivity implements View.OnClickListener {
    Button btnHistory, btnRestock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view);

        btnHistory = findViewById(R.id.btnHistory);
        btnRestock = findViewById(R.id.btnRestock);

        btnHistory.setOnClickListener(this);
        btnRestock.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnHistory:
                Intent toHistoryList = new Intent(this, HistoryList.class);
                startActivity(toHistoryList);

                break;
            case R.id.btnRestock:
                Intent toRestockOption = new Intent(this, RestockActivity.class);
                startActivity(toRestockOption);
                break;
        }
    }
}