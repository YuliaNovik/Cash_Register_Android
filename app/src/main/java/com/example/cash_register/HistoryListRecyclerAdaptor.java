package com.example.cash_register;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryListRecyclerAdaptor extends RecyclerView.Adapter<HistoryListRecyclerAdaptor.HistoryListViewHolder> {

    ArrayList<PurchaseHistory> historyList;
    Context context;
   RecyclerViewInterface recyclerInterface;



    public HistoryListRecyclerAdaptor(ArrayList<PurchaseHistory> historyList, Context context, RecyclerViewInterface rInterface) {
        this.historyList = historyList;
        this.context = context;
        this.recyclerInterface = rInterface;
    }




    class HistoryListViewHolder extends RecyclerView.ViewHolder{

        public HistoryListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerInterface != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            recyclerInterface.onItemClick(position);
                        }
                    }

                }
            });
        }
    }


    @NonNull
    @Override
    public HistoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.purchase_history_row, parent, false);
       //HistoryListViewHolder vh = new HistoryListViewHolder(v);
        //item = v.findViewById(R.id.description);

        return new HistoryListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListViewHolder holder, int position) {
        TextView totalQ = holder.itemView.findViewById(R.id.purchaseQnt);
        TextView totalP = holder.itemView.findViewById(R.id.purchasePrice);
       TextView desc = holder.itemView.findViewById(R.id.purchaseName);

       String purchName =String.valueOf( historyList.get(position).getDescription());
      desc.setText(purchName);
      String purchQnt = String.valueOf(historyList.get(position).getTotalQ());
        totalQ.setText(purchQnt);

        String purchPrice = String.valueOf((int)(Math.round(historyList.get(position).getTotalP() * 100))/100.0);
        totalP.setText(purchPrice);
    }

    @Override
    public int getItemCount() {

        return historyList.size();
    }


}
