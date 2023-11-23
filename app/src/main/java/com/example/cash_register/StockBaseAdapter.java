package com.example.cash_register;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StockBaseAdapter extends BaseAdapter {
    ArrayList<StoreItem> stockItemsList;
    Context context;

    @Override
    public int getCount() {
        return stockItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return stockItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public StockBaseAdapter(ArrayList<StoreItem> stockItemsList, Context context) {
        this.stockItemsList = stockItemsList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v = LayoutInflater.from(context).inflate(R.layout.stocklistitem_row, parent,  false);
        TextView description = v.findViewById(R.id.description);
        TextView itemQnt = v.findViewById(R.id.itemQnt);
        TextView itemPrice = v.findViewById(R.id.itemPrice);



        description.setText(stockItemsList.get(position).getDescription());
        itemQnt.setText(String.valueOf(stockItemsList.get(position).getQuantity()));
        itemPrice.setText(String.valueOf(stockItemsList.get(position).getPrice()));

        return v;
    }
}

