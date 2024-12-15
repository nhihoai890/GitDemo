package com.example.android.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.MODEL.Cart_Model;
import com.example.android.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.ViewHolder> {

    ArrayList<Cart_Model> cart_models;
    Context context;

    private static final String CART_PREF = "cart_pref";
    private static final String CART_KEY = "cart_list";

    public Cart_Adapter(ArrayList<Cart_Model> cart_models, Context context) {
        this.cart_models = cart_models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart_Model cartModel = cart_models.get(position);
        holder.txt_name.setText(cartModel.getName());
        holder.txt_author.setText(cartModel.getAuthor());

        // Cập nhật lại số tiền tổng khi thay đổi số lượng
        int totalPrice = cartModel.getPrice() * cartModel.getQuantity();
        holder.txt_price.setText(String.valueOf(totalPrice)); // Hiển thị tổng tiền

        holder.imageView.setImageResource(cartModel.getImage());
        holder.editText.setText(String.valueOf(cartModel.getQuantity()));

        // Sự kiện giảm số lượng
        holder.txt_min.setOnClickListener(v -> {
            int currentQuantity = cartModel.getQuantity();
            if (currentQuantity > 0) {
                currentQuantity -= 1;
                cartModel.setQuantity(currentQuantity);
                holder.editText.setText(String.valueOf(currentQuantity));

                // Cập nhật lại tổng tiền sau khi thay đổi số lượng
                int updatedTotalPrice = cartModel.getPrice() * currentQuantity;
                holder.txt_price.setText(String.valueOf(updatedTotalPrice));
            }
        });

        // Sự kiện tăng số lượng
        holder.txt_add.setOnClickListener(v -> {
            int currentQuantity = cartModel.getQuantity();
            currentQuantity += 1;
            cartModel.setQuantity(currentQuantity);
            holder.editText.setText(String.valueOf(currentQuantity));

            // Cập nhật lại tổng tiền sau khi thay đổi số lượng
            int updatedTotalPrice = cartModel.getPrice() * currentQuantity;
            holder.txt_price.setText(String.valueOf(updatedTotalPrice));
        });

        // Nút xóa sản phẩm
        holder.imageButton.setOnClickListener(v -> {
            cart_models.remove(position); // Xóa sản phẩm khỏi danh sách
            notifyItemRemoved(position); // Cập nhật giao diện RecyclerView
            notifyItemRangeChanged(position, cart_models.size());

            // Cập nhật dữ liệu lưu trữ
            updateCartData();

            Toast.makeText(context, "Đã xóa sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
        });
    }


    @Override
    public int getItemCount() {
        return cart_models.size();
    }

    // Lưu danh sách giỏ hàng vào SharedPreferences
    private void updateCartData() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(CART_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(cart_models);
        editor.putString(CART_KEY, json);
        editor.apply();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_author, txt_price, txt_min, txt_add;
        ImageView imageView;
        ImageButton imageButton;
        EditText editText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_name = itemView.findViewById(R.id.id_txt_name_cart);
            txt_price = itemView.findViewById(R.id.id_txt_name_price_cart);
            txt_author = itemView.findViewById(R.id.id_txt_author_cart);
            txt_min = itemView.findViewById(R.id.id_txt_min_cart);
            txt_add = itemView.findViewById(R.id.id_txt_add_cart);
            imageView = itemView.findViewById(R.id.id_img_book_cart);
            imageButton = itemView.findViewById(R.id.id_btn_clone_cart);
            editText = itemView.findViewById(R.id.id_txt_quantity_cart);
        }
    }
}
