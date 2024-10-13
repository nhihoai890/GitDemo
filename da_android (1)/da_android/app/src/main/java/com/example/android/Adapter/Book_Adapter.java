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

    ArrayList<Book_model> book_models;
    Context context;

    public Book_Adapter(ArrayList<Book_model> book_models, Context context) {
        this.book_models = book_models;
        this.context = context;
    }

    @NonNull
    @Override
    public Book_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Book_Adapter.ViewHolder holder, int position) {
        Book_model bookModel = book_models.get(position);
        holder.txt_name.setText(bookModel.getName());
        holder.txt_author.setText(bookModel.getAuthor());
        holder.img_book.setImageResource(R.drawable.so_do);
        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(context, Detail_Book_Activity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return book_models.size();
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
}
