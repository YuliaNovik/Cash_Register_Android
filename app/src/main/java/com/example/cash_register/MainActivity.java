package com.example.cash_register;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnBuy, btnC, btnManager;
    TextView tvDisplay, tvTotalAm, tvQnt;
    ListView stockListView;
    ArrayList<StoreItem> itemsInformation;
    double price;
    int totalQnt;
    double totalPrice;
    int quantity;
    double p;
    String description;
    int selectedPosition;
    int  newQnt;
    String timeSt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnBuy = findViewById(R.id.btnBuy);
        btnC = findViewById(R.id.btnC);
        btnManager = findViewById(R.id.btnManager);
        tvQnt = findViewById(R.id.qnt);
        tvDisplay = findViewById(R.id.display);
        tvTotalAm = findViewById(R.id.totalAm);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnManager.setOnClickListener(this);

        stockListView = findViewById(R.id.stockListView);  //Adapter View
     //   StockBaseAdapter adapter = new StockBaseAdapter(((MyApp)getApplication()).getStockList(), MainActivity.this);
        itemsInformation = ((MyApp)getApplication()).getStockList(); //Adapter Data
        StockBaseAdapter adapter = new StockBaseAdapter(itemsInformation, MainActivity.this);
        stockListView.setAdapter(adapter);

        stockListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StoreItem selectedItem = (StoreItem) parent.getItemAtPosition(position);
                selectedPosition = position;
                description = selectedItem.getDescription();
                quantity = selectedItem.getQuantity();
                price = selectedItem.getPrice();

                tvDisplay.setText(description);

            }
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9:
            case R.id.btn0:
                // Extract the digit from the button's text
                String digit = ((Button) view).getText().toString();

                // Append the digit to the current text of tvQnt
                String currentText = tvQnt.getText().toString();
                tvQnt.setText(currentText + digit);

                String quantityText = tvQnt.getText().toString();

                if (!quantityText.isEmpty()) {
                    totalQnt = Integer.parseInt(quantityText);
                }


                // Log the values for debugging
                Log.d("cash register", "quantity: " + quantity + ", totalQnt: " + totalQnt);



                if(quantity < totalQnt){

                        Toast.makeText(this, "Not Enough Quantity In Stock", Toast.LENGTH_LONG).show();

                        tvQnt.setText("");
                        totalQnt = 0;

                }

                totalPrice = totalQnt * price;
                p = (int)(Math.round(totalPrice * 100))/100.0;
                tvTotalAm.setText(String.valueOf(p));
                break;
            case R.id.btnC:
                tvDisplay.setText("");
                tvTotalAm.setText("");
                tvQnt.setText("");
                p = 0;
                totalPrice=0;
                totalQnt=0;
                break;
            case R.id.btnBuy:
                Log.d("cash register", " btn Buy clicked");
                if(tvDisplay.getText().toString().isEmpty() || tvQnt.getText().toString().isEmpty()){
                    Toast.makeText(this, "No Product or Quantity is chosen", Toast.LENGTH_LONG).show();
                }else {
                    timeSt = generateTimestamp();

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Thank you for your purchase");
                    builder.setMessage("Your purchase is " + totalQnt + " " + description + " for $" + p);

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    PurchaseHistory newHistory = new PurchaseHistory(description, price, quantity, totalPrice, totalQnt, timeSt);
                    MyApp.addToHistoryList(newHistory);


                }
                if (totalQnt > 0) {
                    newQnt = quantity - totalQnt;
                    updateItemQuantity(selectedPosition, newQnt);
                }

//                newQnt = quantity - totalQnt;
//                updateItemQuantity(selectedPosition, newQnt);
                tvDisplay.setText("");
                tvTotalAm.setText("");
                tvQnt.setText("");
                p = 0;
                totalPrice = 0;
                totalQnt = 0;



                break;
            case R.id.btnManager:
                Intent toManagerView = new Intent(this, ManagerView.class);
                //toHistoryList.putExtra("list", historyList );
                startActivity(toManagerView);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }


    }
    public void updateItemQuantity(int position, int newQuantity) {
        if (itemsInformation != null && position >= 0 && position < itemsInformation.size()) {
            itemsInformation.get(position).setQuantity(newQuantity);
            // Notify the adapter that the data has changed
            StockBaseAdapter adapter = (StockBaseAdapter) stockListView.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private String generateTimestamp() {
        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

        // Set the time zone to EDT (Eastern Daylight Time)
        sdf.setTimeZone(TimeZone.getTimeZone("EDT"));

        // Get the current date and time
        Date currentDate = new Date();

        // Format the date and return the timestamp
        return sdf.format(currentDate);
    }
    
}