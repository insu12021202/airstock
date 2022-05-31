package com.example.airstock;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable{

    private Context context;
    private Activity activity;
    private  List<StockList> stockList = new ArrayList<>();
    private List<StockList> stockListAll = new ArrayList<>();
//    private ArrayList stock_id, stock_name, stock_count, stock_inDate, stock_outDate, stock_inMemo,
//            stock_outMemo, stock_inPrice, stock_outPrice, stock_receiveClient, stock_isPositioned;

    CustomAdapter(Activity activity, Context context, /* ArrayList stock_id, ArrayList stock_name,
                  ArrayList stock_count, ArrayList stock_inDate, ArrayList stock_outDate,
                  ArrayList stock_inMemo, ArrayList stock_outMemo, ArrayList stock_inPrice,
                  ArrayList stock_outPrice, ArrayList stock_receiveClient, ArrayList stock_isPositioned */
    ArrayList<StockList> stockList){
        this.activity = activity;
        this.context = context;
//        this.stock_id = stock_id;
//        this.stock_name = stock_name;
//        this.stock_count = stock_count;
//        this.stock_inDate = stock_inDate;
//        this.stock_outDate = stock_outDate;
//        this.stock_inMemo = stock_inMemo;
//        this.stock_outMemo = stock_outMemo;
//        this.stock_inPrice = stock_inPrice;
//        this.stock_outPrice = stock_outPrice;
//        this.stock_receiveClient = stock_receiveClient;
//        this.stock_isPositioned = stock_isPositioned;

        this.stockList = stockList;
        this.stockListAll = new ArrayList<>(stockList);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        holder.stock_id_txt.setText(String.valueOf(stock_id.get(position)));
//        holder.stock_name_txt.setText(String.valueOf(stock_name.get(position)));
//        holder.stock_inMemo_txt.setText(String.valueOf(stock_inMemo.get(position)));
//        holder.stock_count_txt.setText(String.valueOf(stock_count.get(position)));
        StockList slist =  stockList.get(position);
        holder.stock_id_txt.setText(String.valueOf(slist.getId()));
        holder.stock_name_txt.setText(String.valueOf(slist.getName()));
        holder.stock_inMemo_txt.setText(String.valueOf(slist.getInMemo()));
        holder.stock_count_txt.setText(String.valueOf(slist.getCount()));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, StockSpec.class);

//                intent.putExtra("id", String.valueOf(stock_id.get(holder.getAdapterPosition())));
//                intent.putExtra("name", String.valueOf(stock_name.get(holder.getAdapterPosition())));
//                intent.putExtra("count", String.valueOf(stock_count.get(holder.getAdapterPosition())));
//                intent.putExtra("inDate", String.valueOf(stock_inDate.get(holder.getAdapterPosition())));
//                intent.putExtra("outDate", String.valueOf(stock_outDate.get(holder.getAdapterPosition())));
//                intent.putExtra("inMemo", String.valueOf(stock_inMemo.get(holder.getAdapterPosition())));
//                intent.putExtra("outMemo", String.valueOf(stock_outMemo.get(holder.getAdapterPosition())));
//                intent.putExtra("inPrice", String.valueOf(stock_inPrice.get(holder.getAdapterPosition())));
//                intent.putExtra("outPrice", String.valueOf(stock_outPrice.get(holder.getAdapterPosition())));
//                intent.putExtra("receiveClient", String.valueOf(stock_receiveClient.get(holder.getAdapterPosition())));
//                intent.putExtra("isPositioned", String.valueOf(stock_isPositioned.get(holder.getAdapterPosition())));
//
                intent.putExtra("id", String.valueOf(slist.getId()));
                intent.putExtra("name", String.valueOf(slist.getName()));
                intent.putExtra("count", String.valueOf(slist.getCount()));
                intent.putExtra("inDate", String.valueOf(slist.getInDate()));
                intent.putExtra("outDate", String.valueOf(slist.getOutDate()));
                intent.putExtra("inMemo", String.valueOf(slist.getInMemo()));
                intent.putExtra("outMemo", String.valueOf(slist.getOutMemo()));
                intent.putExtra("inPrice", String.valueOf(slist.getInPrice()));
                intent.putExtra("outPrice", String.valueOf(slist.getOutPrice()));
                intent.putExtra("receiveClient", String.valueOf(slist.getReceiveClient()));
                intent.putExtra("isPositioned", String.valueOf(slist.getIsPositioned()));

                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constaint) {

            List<StockList> filteredList = new ArrayList<>();

            if (constaint.toString().isEmpty()) {
                filteredList.addAll(stockListAll);
            } else {
                for (StockList stock : stockListAll) {
                    if (stock.getName().toLowerCase().contains(constaint.toString().toLowerCase())) {
                        filteredList.add(stock);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            stockList.clear();
            stockList.addAll((Collection<? extends StockList>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stock_id_txt, stock_name_txt, stock_inMemo_txt, stock_count_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stock_id_txt = itemView.findViewById(R.id.stock_id_txt);
            stock_name_txt = itemView.findViewById(R.id.stock_name_txt);
            stock_inMemo_txt = itemView.findViewById(R.id.stock_inMemo_txt);
            stock_count_txt = itemView.findViewById(R.id.stock_count_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }


}
