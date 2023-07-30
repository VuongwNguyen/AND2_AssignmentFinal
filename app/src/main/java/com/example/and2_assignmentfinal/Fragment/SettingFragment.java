package com.example.and2_assignmentfinal.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.and2_assignmentfinal.Database.DBHelper;
import com.example.and2_assignmentfinal.R;
import com.example.and2_assignmentfinal.Validation.Validate;


public class SettingFragment extends Fragment {

    private Context context;
    DBHelper dbHelper;


    Validate validate;

    public SettingFragment() {
        // Required empty public constructor
    }


    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();

//
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        RelativeLayout changeNewPass = view.findViewById(R.id.ChangNewPassword);


        return view;
    }


}
