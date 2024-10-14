package com.example.android.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.android.Activity.Order_Activity;
import com.example.android.Adapter.Cart_Adapter;
import com.example.android.MODEL.Cart_Model;
import com.example.android.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class Cart_Fragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Cart_Model> cartModels = new ArrayList<>();
    Button button;
    ImageButton btn_chat;

    public Cart_Fragment() {

    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.id_recyclerView_cart);
        button = view.findViewById(R.id.id_btn_add_to_cart);
        Cart_Adapter bookMarkAdapter = new Cart_Adapter(cartModels, getContext());
        cartModels.add(new Cart_Model(1, 100000, 2, "Vũ Trụ", "Carl Sagan", R.drawable.harry_potter_));
        cartModels.add(new Cart_Model(2, 100000, 1, "Số đỏ", "Vũ Trọng Phụng", R.drawable.so_do));
        cartModels.add(new Cart_Model(3, 100000, 1, "Atomic Habit", "Jame Clear", R.drawable.automic));
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager1);
        recyclerView.setAdapter(bookMarkAdapter);
        btn_chat = view.findViewById(R.id.id_img_btn_order);
        btn_chat.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Order_Activity.class);
            startActivity(intent);
        });
        button.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_order, null);

            EditText ed_userName = dialogView.findViewById(R.id.ed_userName);
            EditText ed_phone = dialogView.findViewById(R.id.ed_phone);
            EditText ed_address = dialogView.findViewById(R.id.ed_address);
            Button id_btn_cancel = dialogView.findViewById(R.id.id_btn_cancel);
            Button id_btn_send = dialogView.findViewById(R.id.id_btn_send);

            id_btn_cancel.setOnClickListener(v1 -> {
                bottomSheetDialog.dismiss();
            });

            bottomSheetDialog.setContentView(dialogView);
            bottomSheetDialog.show();
        });
        return view;
    }
}