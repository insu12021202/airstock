package com.example.airstock;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class StockDiffCallback extends DiffUtil.Callback {

    private final List<StockList> mOldStockList;
    private final List<StockList> mNewStockList;

    public StockDiffCallback(List<StockList> oldStockList, List<StockList> newStockList) {
        this.mOldStockList = oldStockList;
        this.mNewStockList = newStockList;
    }

    @Override
    public int getOldListSize() {
        return mOldStockList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewStockList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldStockList.get(oldItemPosition).getId() == mNewStockList.get(
                newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final StockList oldStock = mOldStockList.get(oldItemPosition);
        final StockList newStock = mNewStockList.get(newItemPosition);

        return oldStock.getName().equals(newStock.getName());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}