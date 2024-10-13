package com.example.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Activity.Evaluate_Activity;
import com.example.android.MODEL.Order_model;
import com.example.android.R;

import java.util.ArrayList;

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.ViewHolder> {
    ArrayList<Order_model> order_models;
    Context context;

    public Order_Adapter(ArrayList<Order_model> order_models, Context context) {
        this.order_models = order_models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bought, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order_model orderModel = order_models.get(position);
        holder.txt_name.setText(orderModel.getName());
        holder.txt_author.setText(orderModel.getAuthor());
        holder.txt_price.setText(String.valueOf(orderModel.getPrice()));
        holder.imageView.setImageResource(R.drawable.so_do);
        int status = orderModel.getStatus();
        switch (status) {
            case 1:
                holder.txt_status.setText("Chờ xác nhận");
                break;
            case 2:
                holder.txt_status.setText("Đã xác nhận");
                break;
            case 3:
                holder.txt_status.setText("Đang giao hàng");
                break;
            case 4:
                holder.txt_status.setText("Đã giao hàng");
                break;
            default:
                holder.txt_status.setText("Trạng thái không xác định: " + status);
                break;
        }
        holder.btn_evaluate.setOnClickListener(v -> {
            Intent intent = new Intent(context, Evaluate_Activity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return order_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_author, txt_price, txt_status;
        ImageView imageView;
        Button btn_evaluate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.id_txt_name);
            txt_price = itemView.findViewById(R.id.id_txt_price);
            txt_author = itemView.findViewById(R.id.id_txt_author);
            txt_status = itemView.findViewById(R.id.id_txt_status);
            imageView = itemView.findViewById(R.id.id_img_book);
            btn_evaluate = itemView.findViewById(R.id.id_btn_evaluate);
        }

    }
}
