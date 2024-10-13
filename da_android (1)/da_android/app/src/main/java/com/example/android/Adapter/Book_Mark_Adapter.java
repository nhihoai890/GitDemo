package com.example.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.MODEL.Book_model;
import com.example.android.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Book_Mark_Adapter extends RecyclerView.Adapter<Book_Mark_Adapter.ViewHolder> {
    ArrayList<Book_model> book_models;
    Context context;
    NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public Book_Mark_Adapter(ArrayList<Book_model> book_models, Context context) {
        this.book_models = book_models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book_mark, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book_model bookModel = book_models.get(position);
        holder.txt_name.setText(bookModel.getName());
        holder.txt_author.setText(bookModel.getAuthor());
        holder.txt_start.setText(String.valueOf(bookModel.getStar()));
        holder.txt_price.setText(String.valueOf(bookModel.getPrice()));
        holder.img_book.setImageResource(R.drawable.so_do);
    }

    @Override
    public int getItemCount() {
        return book_models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_author, txt_start, txt_price;
        public ImageView img_book;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.id_txt_name_book_book_mark);
            txt_author = itemView.findViewById(R.id.id_txt_author_book_mark);
            txt_start = itemView.findViewById(R.id.id_txt_name_start_book_mark);
            txt_price = itemView.findViewById(R.id.id_txt_name_price_book_mark);
            img_book = itemView.findViewById(R.id.id_img_book_book_mark);
            linearLayout = itemView.findViewById(R.id.id_item_book_mark);
        }

    }
}
