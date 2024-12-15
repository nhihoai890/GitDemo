package com.example.android.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android.Activity.Auth.Login_Activity;
import com.example.android.Activity.Auth.Sale_Activity;
import com.example.android.Activity.Profile_Activity;
import com.example.android.R;

public class Setting_Fragment extends Fragment {

    LinearLayout btn_tttk, btn_logout, btn_tichdiem;

    public Setting_Fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting_, container, false);
        btn_tttk = v.findViewById(R.id.btn_tttk);
        btn_logout = v.findViewById(R.id.btn_logout);
        btn_tichdiem = v.findViewById(R.id.btn_tichdiem);
        btn_tttk.setOnClickListener(new  View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Profile_Activity.class);
                startActivity(intent);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Login_Activity.class);
                startActivity(intent);
            }
        });
       btn_tichdiem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), Sale_Activity.class);
               startActivity(intent);
           }
       });


        return v;
    }
}