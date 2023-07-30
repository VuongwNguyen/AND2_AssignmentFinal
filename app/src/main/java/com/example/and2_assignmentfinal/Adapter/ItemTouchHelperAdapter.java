package com.example.and2_assignmentfinal.Adapter;

import androidx.recyclerview.widget.RecyclerView;

public interface ItemTouchHelperAdapter {
    void onItemSwiped(int position, int direction);
    void onItemMoved(int fromPosition, int toPosition);
    void onItemSelected(RecyclerView.ViewHolder viewHolder);
    void onItemClear(RecyclerView.ViewHolder viewHolder);
}
