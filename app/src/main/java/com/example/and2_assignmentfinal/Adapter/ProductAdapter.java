package com.example.and2_assignmentfinal.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and2_assignmentfinal.Dao.ProductDAO;
import com.example.and2_assignmentfinal.Model.Product;
import com.example.and2_assignmentfinal.R;
import com.example.and2_assignmentfinal.Validation.FetchImage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements ItemTouchHelperAdapter {
    private List<Product> itemList;
    private Context context;
    private int pinnedPosition = RecyclerView.NO_POSITION;

    private ProductDAO productDAO;

    public ProductAdapter(List<Product> itemList, Context context, ProductDAO productDAO) {
        this.itemList = itemList;
        this.context = context;
        this.productDAO = productDAO;
    }

    //   view cho layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        return new ViewHolder(inflater.inflate(R.layout.item_list_product, parent, false));
    }

    // sét dữ liệu
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewTitle.setText(itemList.get(position).getName());
        holder.textViewDescription.setText(String.valueOf(itemList.get(position).getPrice() + "VNĐ" + " - " + " SL:" + itemList.get(position).getAmount()));
// lấy ảnh drawable
        if (itemList.get(position).getAvatar() != null) {
            int idImage = ((Activity) context).getResources().getIdentifier(String.valueOf(itemList.get(position).getAvatar()), "drawable", ((Activity) context).getPackageName());
            holder.ivAvatar.setImageResource(idImage);
            //ảnh treen internet
            if (itemList.get(position).getAvatar().startsWith("http://") || itemList.get(position).getAvatar().startsWith("https://")) {
                Picasso.get().load(itemList.get(position).getAvatar()).into(holder.ivAvatar);
            }
        }


        holder.itemView.setTag(itemList.get(position));
        holder.bind(itemList.get(position));

//        sự kiện bấm delete
//        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setTitle("Cảnh Báo");
//                builder.setMessage("Xác Nhận Xoá Sản Phẩm '" + itemList.get(holder.getAdapterPosition()).getName() + "' ?");
//                builder.setIcon(R.drawable.ic_warning);
//                builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if (productDAO.deleteProducts(itemList.get(holder.getAdapterPosition()).getID())) {
//                            Toast.makeText(context, "Delete Completed !", Toast.LENGTH_SHORT).show();
//                            itemList.clear();
//                            itemList = productDAO.getListPD();
//                            notifyItemRemoved(holder.getAdapterPosition());
//                        } else {
//                            Toast.makeText(context, "Error Deleting !", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });
//    sự kiện edit
//        holder.ivUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Product productUpadte = itemList.get(holder.getAdapterPosition());
//                dialogUpdate(productUpadte);
//            }
//        });
    }

    private void dialogUpdate(Product productUpadate, int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_product, null);

//        ánh xạ
        TextInputEditText edtNamePDe = view.findViewById(R.id.edtNamePD);
        TextInputEditText edtPricePDe = view.findViewById(R.id.edtPricePD);
        TextInputEditText edtAmountPDe = view.findViewById(R.id.edtAmountPD);
        TextInputEditText edtedtLinkPD = view.findViewById(R.id.edtLinkPD);
        ImageView ivHintPicturesUD = view.findViewById(R.id.ivHintPicturesUD);
        Button btnEdit = view.findViewById(R.id.btnUpdate);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        ImageButton ivFindPic = view.findViewById(R.id.findPictures);
// set dữ liệu lên lại ~~
        edtNamePDe.setText(productUpadate.getName());
        edtPricePDe.setText(String.valueOf(productUpadate.getPrice()));
        edtAmountPDe.setText(String.valueOf(productUpadate.getAmount()));
        edtedtLinkPD.setText(productUpadate.getAvatar());
        // lấy ảnh drawable
        if (itemList.get(i).getAvatar() != null) {
            int idImage = ((Activity) context).getResources().getIdentifier(String.valueOf(itemList.get(i).getAvatar()), "drawable", ((Activity) context).getPackageName());
            ivHintPicturesUD.setImageResource(idImage);
            //ảnh treen internet
            if (itemList.get(i).getAvatar().startsWith("http://") || itemList.get(i).getAvatar().startsWith("https://")) {
                Picasso.get().load(itemList.get(i).getAvatar()).into(ivHintPicturesUD);
            } else {
                ivHintPicturesUD.setImageResource(R.drawable.ic_all_inclusive);
            }
        }
        builder.setView(view);
        AlertDialog dialog = builder.create();
//        sự kiện bấm nút
        ivFindPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchImage(ivHintPicturesUD, context, edtedtLinkPD.getText().toString()).start();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();
                product.setID(productUpadate.getID());
                product.setName(edtNamePDe.getText().toString());
                product.setPrice(Integer.parseInt(edtPricePDe.getText().toString()));
                product.setAmount(Integer.parseInt(edtAmountPDe.getText().toString()));
                product.setAvatar(edtedtLinkPD.getText().toString());
                if (productDAO.updateProducts(product)) {
                    Toast.makeText(context, "Update Completed !", Toast.LENGTH_SHORT).show();
                    itemList.clear();
                    itemList = productDAO.getListPD();
                    notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Error Updating!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // ánh xạ
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewTitle, textViewDescription;
        ImageView ivPinned, ivAvatar;
        FloatingActionButton actionButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.txtNameProduct);
            textViewDescription = itemView.findViewById(R.id.txtDescription);
            actionButton = itemView.findViewById(R.id.floatingButton);
            ivAvatar = itemView.findViewById(R.id.ivPictures);
            ivPinned = itemView.findViewById(R.id.ivPinned);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showpopup(view);
                    return true;
                }
            });
        }

        private void showpopup(View view) {
            PopupMenu popupMenu = new PopupMenu(context, view);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.menu_item_rcv, popupMenu.getMenu());

            Product product = itemList.get(getAdapterPosition());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.actionPin) {
                        if (getAdapterPosition() != pinnedPosition) {
//                            itemList.get(getAdapterPosition()).setPinned(true);
                            pinnedPosition = getAdapterPosition();
                            itemList.remove(getAdapterPosition());
                            itemList.add(0, product);
                            product.setPinned(!product.isPinned());
                            notifyDataSetChanged();
                        }


                        notifyDataSetChanged();
                        return true;
                    }
                    return false;
                }
            });
            popupMenu.show();
        }

        public void bind(Product item) {
            if (item.isPinned()) {
                ivPinned.setVisibility(View.VISIBLE);
            } else {
                ivPinned.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View view) {
//          Lấy parent của View (RecyclerView)
            View parentview = (View) view.getParent();
//           Kiểm tra nếu parent là một RecyclerView
            if (parentview instanceof RecyclerView) {
                RecyclerView recyclerView = (RecyclerView) parentview;
//               Lấy ViewHolder từ RecyclerView
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
//              Kiểm tra nếu ViewHolder là một phiên bản của ViewHolder  (ViewHolder được khai báo trong Adapter)
                if (holder instanceof ViewHolder) {
                    Product productUpadte = itemList.get(holder.getAdapterPosition());
                    dialogUpdate(productUpadte, holder.getAdapterPosition());
                }
            }
        }

    }


    @Override
    public void onItemSwiped(int position, int direction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cảnh Báo");
        builder.setMessage("Xác Nhận Xoá Sản Phẩm '" + itemList.get(position).getName() + "' ?");
        builder.setIcon(R.drawable.ic_warning);
        notifyDataSetChanged();
        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (productDAO.deleteProducts(itemList.get(position).getID())) {
                    Toast.makeText(context, "Delete Completed !", Toast.LENGTH_SHORT).show();
                    itemList.clear();
                    itemList = productDAO.getListPD();
                    notifyItemRemoved(position);
                } else {
                    Toast.makeText(context, "Error Deleting !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onItemMoved(int fromPosition, int toPosition) {
        // Di chuyển item trong danh sách dựa vào vị trí nguồn (fromPosition) và đích (toPosition).
        // Đầu tiên, lưu trữ item tại vị trí nguồn.
        String movedItem = String.valueOf(itemList.get(fromPosition));


        // Sử dụng Collections.swap để hoán đổi vị trí của hai item trong danh sách.
        Collections.swap(itemList, fromPosition, toPosition);

        // Thông báo cho RecyclerView rằng item đã di chuyển để cập nhật giao diện.
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemSelected(RecyclerView.ViewHolder viewHolder) {
        // Áp dụng hiệu ứng khi item được chọn (đang kéo và thả).
        viewHolder.itemView.setBackgroundColor(Color.CYAN);
        applyScaleAnimation(viewHolder.itemView, 1.8f); // Áp dụng animation co giãn
    }

    @Override
    public void onItemClear(RecyclerView.ViewHolder viewHolder) {
// Thực hiện các xử lý khi item không còn được chọn (kéo và thả kết thúc).
        viewHolder.itemView.setBackgroundColor(Color.WHITE);
        applyScaleAnimation(viewHolder.itemView, 1.0f); // Đặt về kích thước ban đầu
    }

    private void applyScaleAnimation(View view, float scale) {
        Animation anim = new ScaleAnimation(
                1.0f, scale, // Bắt đầu và kết thúc scale theo trục X
                1.0f, scale, // Bắt đầu và kết thúc scale theo trục Y
                Animation.RELATIVE_TO_SELF, 0.5f, // Trung tâm scale theo trục X
                Animation.RELATIVE_TO_SELF, 0.5f // Trung tâm scale theo trục Y
        );
        anim.setFillAfter(true); // Giữ lại trạng thái mới sau khi animation kết thúc
        anim.setDuration(200); // Thời gian animation
        view.startAnimation(anim);
    }

}
