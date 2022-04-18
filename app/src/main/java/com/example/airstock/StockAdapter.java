package com.example.airstock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class StockAdapter extends ArrayAdapter<Stock> {


    public StockAdapter(Context context, int resource, List<Stock> stockList){

        super(context, resource, stockList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Stock stock = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stock_item, parent, false);
        }

        TextView tv = convertView.findViewById(R.id.stock_detail_name);
        ImageView iv = convertView.findViewById(R.id.stock_detail_image);

        tv.setText(stock.getName());
        iv.setImageResource(stock.getImage());

        return convertView;
    }
}