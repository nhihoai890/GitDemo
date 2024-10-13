package com.example.android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Adapter.Order_Adapter;
import com.example.android.MODEL.Order_model;
import com.example.android.R;

import java.util.ArrayList;

public class Order_Activity extends AppCompatActivity {
    ImageButton btn_out, btn_chat;
    RecyclerView recyclerView;
    ArrayList<Order_model> orderModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bought);
        Order_Adapter orderAdapter = new Order_Adapter(orderModels, this);
        btn_out = findViewById(R.id.id_btn_out_change_password);
        btn_chat = findViewById(R.id.id_btn_chat);
        recyclerView = findViewById(R.id.id_recyclerView_bought);
        orderModels.add(new Order_model(1, 100000, 200, 2, "Số Đỏ", "Vũ Trọng Phụng"));
        orderModels.add(new Order_model(2, 100000, 200, 2, "Số Đỏ", "Vũ Trọng Phụng"));
        orderModels.add(new Order_model(2, 100000, 200, 2, "Số Đỏ", "Vũ Trọng Phụng"));
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager1);
        recyclerView.setAdapter(orderAdapter);
        btn_chat.setOnClickListener(v -> {
            Intent intent = new Intent(Order_Activity.this, Chat_Activity.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}