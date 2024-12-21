package com.thick124.LopLTDD03.nhomNA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.thick124.LopLTDD03.nhomNA.Model.Book_Model;
import com.thick124.LopLTDD03.nhomNA.R;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private List<Book_Model> bookList;

    public BookAdapter(List<Book_Model> books) {
        this.bookList = books;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, author;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title);
            author = itemView.findViewById(R.id.txt_author);
        }
    }

    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book_Model book = bookList.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
