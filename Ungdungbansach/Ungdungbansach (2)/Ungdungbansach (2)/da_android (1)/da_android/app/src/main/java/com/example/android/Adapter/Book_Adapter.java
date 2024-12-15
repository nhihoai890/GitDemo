package com.example.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.Activity.Detail_Book_Activity;
import com.example.android.MODEL.Book_model;
import com.example.android.R;

import java.util.ArrayList;

public class Book_Adapter extends RecyclerView.Adapter<Book_Adapter.ViewHolder> {

    private ArrayList<Book_model> bookModels;
    private Context context;
    private ArrayList<Book_model> originalBookList;  // Danh sách gốc dùng để tìm kiếm

    // Constructor
    public Book_Adapter(ArrayList<Book_model> bookModels, Context context) {
        this.bookModels = bookModels;
        this.context = context;
        this.originalBookList = new ArrayList<>(bookModels); // Lưu danh sách gốc
    }

    @NonNull
    @Override
    public Book_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Book_Adapter.ViewHolder holder, int position) {
        Book_model bookModel = bookModels.get(position);

        // Cập nhật thông tin sách vào View
        holder.txt_name.setText(bookModel.getName());
        holder.txt_author.setText(bookModel.getAuthor());
        holder.img_book.setImageResource(bookModel.getImage()); // Giả sử bookModel.getImage() là ID của hình ảnh

        // Khi người dùng nhấn vào một sách trong RecyclerView
        holder.linearLayout.setOnClickListener(v -> {
            // Tạo Intent chuyển đến Detail_Book_Activity
            Intent intent = new Intent(context, Detail_Book_Activity.class);

            // Truyền thông tin sách qua Intent
            intent.putExtra("book_name", bookModel.getName());
            intent.putExtra("book_author", bookModel.getAuthor());
            intent.putExtra("book_price", bookModel.getPrice()); // Giá của sách
            intent.putExtra("book_summary", bookModel.getSummary()); // Tóm tắt sách
            intent.putExtra("book_image", bookModel.getImage()); // Hình ảnh của sách (ID drawable)

            // Khởi động activity chi tiết sách
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return bookModels.size();
    }

    // Cập nhật danh sách sách sau khi tìm kiếm
    public void updateList(ArrayList<Book_model> updatedList) {
        this.bookModels = updatedList;
        notifyDataSetChanged(); // Cập nhật giao diện
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_author;
        public ImageView img_book;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.id_txt_name_book);
            txt_author = itemView.findViewById(R.id.id_txt_author_book);
            img_book = itemView.findViewById(R.id.id_img_book);
            linearLayout = itemView.findViewById(R.id.id_item_book);
        }
    }

    // Hàm tìm kiếm sách
    public void filter(String query) {
        ArrayList<Book_model> filteredList = new ArrayList<>();

        // Nếu query không rỗng, lọc sách theo tên hoặc tác giả
        if (query != null && !query.isEmpty()) {
            for (Book_model book : originalBookList) {
                if (book.getName().toLowerCase().contains(query.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(book);
                }
            }
        } else {
            // Nếu query rỗng, hiển thị tất cả sách
            filteredList.addAll(originalBookList);
        }

        // Cập nhật danh sách hiển thị
        updateList(filteredList);
    }
}
