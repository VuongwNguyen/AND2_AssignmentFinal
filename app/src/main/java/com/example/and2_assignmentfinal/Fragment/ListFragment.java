package com.example.and2_assignmentfinal.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.and2_assignmentfinal.Adapter.AvatarAdapter;
import com.example.and2_assignmentfinal.Adapter.ProductAdapter;
import com.example.and2_assignmentfinal.Adapter.SwipeItemTouchHelper;
import com.example.and2_assignmentfinal.Dao.ProductDAO;
import com.example.and2_assignmentfinal.Model.Avatar;
import com.example.and2_assignmentfinal.Model.Product;
import com.example.and2_assignmentfinal.R;
import com.example.and2_assignmentfinal.Validation.FetchImage;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.FadeInAnimator;
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator;
import jp.wasabeef.recyclerview.animators.LandingAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;


public class ListFragment extends Fragment {
    private Context context;
    private ProductDAO productDAO;
    String NameAv, link;

    private List<Product> ListPD;
    private String URL;


    public ListFragment(Context context, ProductDAO productDAO, List<Product> listPD) {
        this.context = context;
        this.productDAO = productDAO;
        ListPD = listPD;
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rcvList);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.swipeRe);
//        hiển thị recyclerView
        productDAO = new ProductDAO(context);
        ListPD = productDAO.getListPD();
//        GridLayoutManager layoutManager = new GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));


        ProductAdapter adapter = new ProductAdapter(ListPD, context, productDAO);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new LandingAnimator());
        recyclerView.setHasFixedSize(true);
//      kết nối sự kiện vuốt
        ItemTouchHelper.Callback callback = new SwipeItemTouchHelper(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                List<Product> newDataList = new ArrayList<>();
                // Thêm d6ữ liệu mới vào newDataList
                // ...

                // Cập nhật dữ liệu mới vào adapter
                ListPD.clear();
                ListPD.addAll(productDAO.getListPD());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                // Kết thúc quá trình kéo xuống (nếu đang mở)
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        view.findViewById(R.id.floatingButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_add_products, null);
//              ánh xạ
                TextInputEditText edtNamePDa = view1.findViewById(R.id.edtNamePDa);
                TextInputEditText edtPricePDa = view1.findViewById(R.id.edtPricePDa);
                TextInputEditText edtAmounta = view1.findViewById(R.id.edtAmountPDa);
                ImageView imageView = view1.findViewById(R.id.ivAddpicture);
                ImageView imageViewHint = view1.findViewById(R.id.ivHintPictures);
                Button buttonAdd = view1.findViewById(R.id.btnAdd);

                builder.setView(view1);
                AlertDialog dialog = builder.create();

                buttonAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (edtAmounta.getText().toString().isEmpty() || edtNamePDa.getText().toString().isEmpty() || edtPricePDa.getText().toString().isEmpty()) {
                            Toast.makeText(context, "Please Fill ALL Fields", Toast.LENGTH_SHORT).show();
                        } else {
                            Product product = new Product();
                            product.setName(edtNamePDa.getText().toString());
                            product.setPrice(Integer.parseInt(edtPricePDa.getText().toString()));
                            product.setAmount(Integer.parseInt(edtAmounta.getText().toString()));
                            product.setAvatar(URL);
                            if (productDAO.addProducts(product)) {
                                Toast.makeText(context, "Add Completed !", Toast.LENGTH_SHORT).show();
                                ListPD.clear();
                                ListPD.addAll(productDAO.getListPD());
                                adapter.notifyDataSetChanged();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(context, "Error Adding...!", Toast.LENGTH_SHORT).show();
                            }
                            URL = "";
                        }
                    }
                });
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                        View viewAddPictures = inflater.inflate(R.layout.fragment_pictures, null);

                        TextInputEditText edtURL = viewAddPictures.findViewById(R.id.edtURL);
                        Button btnURL = viewAddPictures.findViewById(R.id.btnInsertImage);
                        Spinner spinner = viewAddPictures.findViewById(R.id.Spinner);
                        builder1.setView(viewAddPictures);
                        AlertDialog dialog1 = builder1.create();
                        btnURL.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                URL = edtURL.getText().toString();
                                new FetchImage(imageViewHint, context, URL).start();
                                dialog1.dismiss();
                            }
                        });
                        List<Avatar> ListAvatars = new Avatar().getListAva();
                        AvatarAdapter adapter1 = new AvatarAdapter(context, ListAvatars);
                        spinner.setAdapter(adapter1);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                NameAv = ListAvatars.get(i).getNameAV();
                                link = ListAvatars.get(i).getURl();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                        viewAddPictures.findViewById(R.id.btnCancelJ).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                edtNamePDa.setText(NameAv);
                                new FetchImage(imageViewHint, context, link).start();
                                URL = link;
                                dialog1.dismiss();
                            }
                        });
                        dialog1.show();
                    }
                });
                dialog.show();
            }
        });
//load ảnh
        return view;
    }
}