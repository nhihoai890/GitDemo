package com.example.android.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android.MODEL.Cart_Model;
import com.example.android.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Detail_Book_Activity extends AppCompatActivity {

    private static final String CART_PREF = "cart_pref"; // Tên SharedPreferences
    private static final String CART_KEY = "cart_list";  // Key để lưu danh sách giỏ hàng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        int bookId = intent.getIntExtra("book_id", 0); // ID của sách
        String bookName = intent.getStringExtra("book_name");
        String bookAuthor = intent.getStringExtra("book_author");
        int bookPrice = intent.getIntExtra("book_price", 0);
        int bookImage = intent.getIntExtra("book_image", 0); // Lấy ID hình ảnh, mặc định là 0 nếu không có

        // Ánh xạ các View trong layout
        TextView txtName = findViewById(R.id.id_txt_name_detail_book);
        TextView txtAuthor = findViewById(R.id.id_txt_author_detail_book);
        TextView txtPrice = findViewById(R.id.id_txt_price_detail_book);
        ImageView imgBook = findViewById(R.id.id_img_detail_book);

        // Cập nhật giao diện với dữ liệu nhận được
        txtName.setText(bookName);
        txtAuthor.setText(bookAuthor);
        txtPrice.setText(String.valueOf(bookPrice));
        imgBook.setImageResource(bookImage); // Hiển thị hình ảnh sách

        // Xử lý nút "Thêm vào giỏ hàng"
        Button btnAddToCart = findViewById(R.id.id_btn_add_to_cart);
        btnAddToCart.setOnClickListener(v -> {
            addToCart(bookId, bookPrice, 1, bookName, bookAuthor, bookImage);
        });
    }

    private void addToCart(int id, int price, int quantity, String name, String author, int image) {
        // Lấy giỏ hàng hiện tại từ SharedPreferences
        ArrayList<Cart_Model> cartList = getCartList();

        // Kiểm tra nếu sản phẩm đã có trong giỏ hàng
        boolean isAlreadyInCart = false;
        for (Cart_Model item : cartList) {
            if (item.getId() == id) { // Nếu sách đã tồn tại trong giỏ hàng
                // Tăng số lượng sản phẩm trong giỏ hàng
                item.setQuantity(item.getQuantity() + quantity);
                isAlreadyInCart = true;
                break;
            }
        }

        // Nếu sách chưa có trong giỏ hàng, thêm vào giỏ hàng
        if (!isAlreadyInCart) {
            cartList.add(new Cart_Model(id, price, quantity, name, author, image));
        }

        // Lưu lại giỏ hàng vào SharedPreferences
        saveCartList(cartList);

        // Thông báo đã thêm thành công
        Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();

        // Chuyển đến màn hình giỏ hàng sau khi thêm sản phẩm
        Intent intent = new Intent(Detail_Book_Activity.this, Order_Activity.class); // Điều hướng tới màn hình giỏ hàng
        startActivity(intent); // Mở màn hình giỏ hàng
    }


    private ArrayList<Cart_Model> getCartList() {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREF, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(CART_KEY, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Cart_Model>>() {}.getType();
            return gson.fromJson(json, type);
        }

        return new ArrayList<>(); // Nếu chưa có giỏ hàng, trả về danh sách rỗng
    }

    private void saveCartList(ArrayList<Cart_Model> cartList) {
        SharedPreferences sharedPreferences = getSharedPreferences(CART_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(cartList); // Chuyển danh sách giỏ hàng thành chuỗi JSON
        editor.putString(CART_KEY, json); // Lưu chuỗi JSON vào SharedPreferences
        editor.apply(); // Lưu thay đổi
    }

}
