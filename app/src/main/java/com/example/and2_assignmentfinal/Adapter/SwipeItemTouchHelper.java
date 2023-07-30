package com.example.and2_assignmentfinal.Adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeItemTouchHelper extends ItemTouchHelper.Callback{

    private  final  ItemTouchHelperAdapter mAdapter;
    private Paint overlayPaint = new Paint();
    private RecyclerView.ViewHolder currentItemViewHolder;
    private int overlayBackgroundColor = Color.parseColor("#E82C59"); // Màu nền overlay
    public SwipeItemTouchHelper(ItemTouchHelperAdapter mAdapter) {
        this.mAdapter = mAdapter;
        overlayPaint.setAntiAlias(true);
        overlayPaint.setFilterBitmap(true);
        overlayPaint.setDither(true);
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        // Lấy vị trí của item nguồn và item đích.
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
//
//        // Di chuyển item trong danh sách dựa vào vị trí nguồn và đích.
        mAdapter.onItemMoved(fromPosition, toPosition);

        return true; }// Trả về true để xác nhận việc di chuyển đã thành công.    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        mAdapter.onItemSwiped(viewHolder.getAdapterPosition(),direction);
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && isCurrentlyActive) {
            // Lấy độ rộng của item.
            int itemWidth = viewHolder.itemView.getWidth();

            // Tính toán phạm vi vuốt (dX) so với độ rộng của item.
            float alpha = 1.0f - Math.abs(dX) / itemWidth;

            // Giới hạn giá trị alpha trong khoảng từ 0.3 đến 1.0 để tạo hiệu ứng lung linh.
            alpha = Math.max(1.0f, alpha);

            // Thiết lập màu sắc và độ trong suốt cho overlay.
            overlayPaint.setColor(overlayBackgroundColor);
            overlayPaint.setAlpha((int) (255 * alpha));

            // Vẽ overlay trên item.
            c.drawRect(
                    viewHolder.itemView.getLeft(), viewHolder.itemView.getTop(),
                    viewHolder.itemView.getRight(), viewHolder.itemView.getBottom(), overlayPaint
            );
        }
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            currentItemViewHolder = viewHolder;
            currentItemViewHolder.itemView.setElevation(10f); // Đặt độ cao của item khi kéo và thả.
        } else if (currentItemViewHolder != null) {
            currentItemViewHolder.itemView.setElevation(0f); // Đặt độ cao mặc định khi không được kéo và thả.
            currentItemViewHolder = null;
        }
        super.onSelectedChanged(viewHolder, actionState);
    }
}
