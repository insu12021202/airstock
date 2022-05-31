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

    CustomAdapter(Activity activity, Context context, ArrayList<StockList> stockList){
        this.activity = activity;
        this.context = context;

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
