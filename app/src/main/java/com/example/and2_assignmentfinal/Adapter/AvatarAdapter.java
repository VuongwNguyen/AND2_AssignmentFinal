package com.example.and2_assignmentfinal.Adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.and2_assignmentfinal.Model.Avatar;
import com.example.and2_assignmentfinal.R;
import com.example.and2_assignmentfinal.Validation.FetchImage;

import java.util.List;


public class AvatarAdapter extends BaseAdapter {
    Context context;
    List<Avatar> ListAva;

    public AvatarAdapter(Context context, List<Avatar> listAva) {
        this.context = context;
        ListAva = listAva;
    }

    @Override
    public int getCount() {
        return ListAva.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        view = inflater.inflate(R.layout.item_avatar, viewGroup, false);

        ImageView ivAvatars = view.findViewById(R.id.ivAvatars);
        TextView tvNameAva = view.findViewById(R.id.tvNameAva);

        tvNameAva.setText(ListAva.get(i).getNameAV());
        new FetchImage(ivAvatars, context, ListAva.get(i).getURl()).start();

        return view;
    }
}
