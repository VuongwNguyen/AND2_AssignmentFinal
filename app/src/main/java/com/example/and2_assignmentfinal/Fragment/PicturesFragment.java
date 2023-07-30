package com.example.and2_assignmentfinal.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.and2_assignmentfinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PicturesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PicturesFragment extends Fragment {





    public PicturesFragment() {
        // Required empty public constructor
    }


    public static PicturesFragment newInstance(String param1, String param2) {
        PicturesFragment fragment = new PicturesFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pictures, container, false);

//
        return view;
    }

}
