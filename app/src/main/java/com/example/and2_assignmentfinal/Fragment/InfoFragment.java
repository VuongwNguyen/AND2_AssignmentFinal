package com.example.and2_assignmentfinal.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.and2_assignmentfinal.R;


public class InfoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        TextView presentedI = view.findViewById(R.id.txtPresentedI);
        TextView name = view.findViewById(R.id.txtName);
        name.setText(Html.fromHtml("<u><i><b>PS33232-Nguyễn Quốc Vương",Html.FROM_HTML_MODE_COMPACT));
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font_pesented.ttf");
        presentedI.setTypeface(typeface);

        return view;
    }
}