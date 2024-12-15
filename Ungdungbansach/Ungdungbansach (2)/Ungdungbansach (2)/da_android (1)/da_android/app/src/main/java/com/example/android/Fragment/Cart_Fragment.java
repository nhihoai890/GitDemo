package com.example.android.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.Activity.Order_Activity;
import com.example.android.Adapter.Cart_Adapter;
import com.example.android.MODEL.Cart_Model;
import com.example.android.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Cart_Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Cart_Model> cartModels = new ArrayList<>();
    Cart_Adapter cartAdapter;
    Button button;
    ImageButton btnChat;
    LinearLayout saleLayout;

    private static final String CART_PREF = "cart_pref";
    private static final String CART_KEY = "cart_list";

    public Cart_Fragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.id_recyclerView_cart);
        button = view.findViewById(R.id.id_btn_add_to_cart);
        btnChat = view.findViewById(R.id.id_img_btn_order);
        saleLayout = view.findViewById(R.id.sale_layout);

        // Lấy danh sách sản phẩm từ SharedPreferences
        loadCartData();

        // Khởi tạo RecyclerView
        cartAdapter = new Cart_Adapter(cartModels, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(cartAdapter);

        // Xử lý sự kiện nút "Đặt hàng"
        btnChat.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Order_Activity.class);
            startActivity(intent);
        });

        // Xử lý sự kiện nút "Thêm đơn hàng"
        button.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_order, null);

            EditText edUserName = dialogView.findViewById(R.id.ed_userName);
            EditText edPhone = dialogView.findViewById(R.id.ed_phone);
            EditText edAddress = dialogView.findViewById(R.id.ed_address);
            Button btnCancel = dialogView.findViewById(R.id.id_btn_cancel);
            Button btnSend = dialogView.findViewById(R.id.id_btn_send);

            btnCancel.setOnClickListener(v1 -> bottomSheetDialog.dismiss());

            btnSend.setOnClickListener(v1 -> {
                String userName = edUserName.getText().toString().trim();
                String phone = edPhone.getText().toString().trim();
                String address = edAddress.getText().toString().trim();

                if (userName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    // Xử lý logic đặt hàng tại đây (lưu thông tin hoặc gửi tới server)
                    Toast.makeText(getContext(), "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                }
            });

            bottomSheetDialog.setContentView(dialogView);
            bottomSheetDialog.show();
        });

        // Xử lý sự kiện nút "Ưu đãi"
        saleLayout.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sale, null);
            bottomSheetDialog.setContentView(dialogView);
            bottomSheetDialog.show();
        });

        return view;
    }

    // Hàm tải dữ liệu từ SharedPreferences
    private void loadCartData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(CART_PREF, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CART_KEY, null);

        if (json != null) {
            Type type = new TypeToken<ArrayList<Cart_Model>>() {}.getType();
            cartModels = gson.fromJson(json, type);
        } else {
            cartModels = new ArrayList<>();
        }
    }

    private void saveCartData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(CART_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(cartModels);
        editor.putString(CART_KEY, json);
        editor.apply();
    }
}
