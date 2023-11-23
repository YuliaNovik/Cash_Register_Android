package com.example.cash_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnOk, btnCancel;
    ListView restockListView;
    EditText editQuantity;
    ArrayList<StoreItem> storeStock;
    int positionIndex = -1;


    int newQnt;
    int currentQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);


        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);
        restockListView = findViewById(R.id.restockListView);
        editQuantity = findViewById(R.id.editQnt);

        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);



        storeStock = ((MyApp)getApplication()).getStockList(); //Adapter Data
        StockBaseAdapter adapter = new StockBaseAdapter(storeStock, RestockActivity.this);
        restockListView.setAdapter(adapter);

        restockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StoreItem selectedItem = (StoreItem) parent.getItemAtPosition(position);
                positionIndex = position;

                currentQuantity = selectedItem.getQuantity();
               // Log.d("RestockActivity", "Position Index: " + positionIndex);

                //editQuantity.setText(String.valueOf(currentQuantity));


            }
        });




    }

    @Override
    public void onClick(View v) {
        Log.d("RestockActivity", "Button OK" );
        switch (v.getId()) {
            case R.id.btnOk:
                Log.d("RestockActivity", "Position Index: " + positionIndex);

                if (positionIndex == -1) {
                    if (!isFinishing()) {
                        Toast.makeText(this, "Choose the Product Item", Toast.LENGTH_LONG).show();
                    }
                } else if (editQuantity.getText().toString().isEmpty()) {
                    if (!isFinishing()) {
                        Toast.makeText(this, "Enter New Quantity", Toast.LENGTH_LONG).show();
                    }

                }

                else {
                    String setQuantity = editQuantity.getText().toString();
                    if (!setQuantity.isEmpty()) {
                        newQnt = Integer.parseInt(setQuantity);
                    }
                    updateItemQuantity(positionIndex, newQnt);
                }

//                if (positionIndex == -1 ||  editQuantity.getText().toString().isEmpty()) {
//                    Toast.makeText(this, "Choose a Product Item", Toast.LENGTH_LONG).show();
//                } else {
//                    String setQuantity = editQuantity.getText().toString();
//                    if (!setQuantity.isEmpty()) {
//                        newQnt = Integer.parseInt(setQuantity);
//                    }
//                    updateItemQuantity(positionIndex, newQnt);
//                }


                break;
            case R.id.btnCancel:
                editQuantity.setText("");
                Intent toManagerPage = new Intent(this, ManagerView.class);
                startActivity(toManagerPage);
                break;
        }

    }

    public void updateItemQuantity(int position, int newQuantity) {
        if (storeStock != null && position >= 0 && position < storeStock.size()) {
            storeStock.get(position).setQuantity(newQuantity);
            // Notify the adapter that the data has changed
            StockBaseAdapter adapter = (StockBaseAdapter) restockListView.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }
}