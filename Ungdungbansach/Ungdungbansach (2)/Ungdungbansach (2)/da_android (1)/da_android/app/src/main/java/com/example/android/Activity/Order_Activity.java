package com.example.android.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Adapter.Order_Adapter;
import com.example.android.MODEL.Cart_Model;
import com.example.android.MODEL.Order_model;
import com.example.android.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Order_Activity extends AppCompatActivity {

    private static final String CART_PREF = "cart_pref";
    private static final String CART_KEY = "cart_list";

    private RecyclerView recyclerView;
    private ArrayList<Cart_Model> cartModels;
    private ArrayList<Order_model> orderModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought);

        recyclerView = findViewById(R.id.id_recyclerView_bought);

        // Load cart data
        cartModels = getCartList();
        orderModels = convertCartToOrder(cartModels);

        // Display orders in RecyclerView
        Order_Adapter adapter = new Order_Adapter(orderModels, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if (orderModels.isEmpty()) {
            Toast.makeText(this, "Cart is empty!", Toast.LENGTH_SHORT).show();
        }
    }

    private ArrayList<Cart_Model> getCartList() {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREF, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(CART_KEY, null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<Cart_Model>>() {}.getType();
            return new Gson().fromJson(json, type);
        }
        return new ArrayList<>();
    }

    private ArrayList<Order_model> convertCartToOrder(ArrayList<Cart_Model> cartModels) {
        ArrayList<Order_model> orders = new ArrayList<>();
        for (Cart_Model cart : cartModels) {
            orders.add(new Order_model(
                    cart.getId(),
                    cart.getQuantity(),
                    cart.getPrice() * cart.getQuantity(),
                    0,
                    cart.getName(),
                    cart.getAuthor()
            ));
        }
        return orders;
    }
}
