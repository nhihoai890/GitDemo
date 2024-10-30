package com.example.android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.Adapter.Book_Adapter;
import com.example.android.Adapter.Category_Adapter;
import com.example.android.MODEL.Book_model;
import com.example.android.MODEL.Category_book;
import com.example.android.R;

import java.util.ArrayList;

public class Home_Fragment extends Fragment {
    RecyclerView recyclerView, recyclerView_1, recyclerView_2;
    ArrayList<Category_book> category_books = new ArrayList<>();
    ArrayList<Book_model> bookModels = new ArrayList<>();

    public Home_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khởi tạo RecyclerViews
        recyclerView = view.findViewById(R.id.id_recyclerView);
        recyclerView_1 = view.findViewById(R.id.id_recyclerView_1);
        recyclerView_2 = view.findViewById(R.id.id_recyclerView_2);

        // Thêm dữ liệu cho category_books và bookModels
        category_books.add(new Category_book(1, "Tất Cả"));
        category_books.add(new Category_book(2, "Ebook"));
        category_books.add(new Category_book(3, "Giảm Giá"));
        category_books.add(new Category_book(4, "Sách Mới"));

        bookModels.add(new Book_model(1, 100000, 200, 2, 4 / 5, "Số Đỏ", "Vũ Trọng Phụng", "Sách hay", R.drawable.so_do));
        bookModels.add(new Book_model(2, 100000, 200, 2, 5 / 5, "Atomic Habit", "Jame Clears", "Sách hay", R.drawable.automic));
        bookModels.add(new Book_model(3, 100000, 200, 2, 5 / 5, "Harry Potter 2", "J.K Rowling", "Sách hay", R.drawable.harry_potter_));

        // Tạo các Adapter
        Category_Adapter categoryAdapter = new Category_Adapter(category_books, getContext());
        Book_Adapter bookAdapter = new Book_Adapter(bookModels, getContext());

        // Tạo LayoutManager riêng biệt cho mỗi RecyclerView
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        // Set LayoutManager và Adapter cho từng RecyclerView
        recyclerView.setLayoutManager(linearLayoutManager1);
        recyclerView.setAdapter(categoryAdapter);

        recyclerView_1.setLayoutManager(linearLayoutManager2);
        recyclerView_1.setAdapter(bookAdapter);

        recyclerView_2.setLayoutManager(linearLayoutManager3);
        recyclerView_2.setAdapter(bookAdapter);

        return view;
    }
}
