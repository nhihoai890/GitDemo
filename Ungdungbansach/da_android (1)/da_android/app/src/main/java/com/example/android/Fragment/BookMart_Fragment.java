package com.example.android.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.Adapter.Book_Mark_Adapter;
import com.example.android.MODEL.Book_model;
import com.example.android.R;

import java.util.ArrayList;


public class BookMart_Fragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Book_model> bookModels = new ArrayList<>();

    public BookMart_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_boock_mat_, container, false);
        Book_Mark_Adapter bookMarkAdapter = new Book_Mark_Adapter(bookModels, getContext());
        recyclerView = view.findViewById(R.id.id_recyclerView_book_mat);
        bookModels.add(new Book_model(1, 100000, 200, 2, 4 / 5, "Automic Habit", "James Clear", "Sách hay", R.drawable.automic));
        bookModels.add(new Book_model(2, 100000, 200, 2, 5 / 5, "Số Đỏ", "Vũ Trọng Phụng", "Sách hay", R.drawable.so_do));
        bookModels.add(new Book_model(3, 100000, 200, 2, 5 / 5, "Harry Potter 2", "J.K Rowling", "Sách hay", R.drawable.harry_potter_));
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager1);
        recyclerView.setAdapter(bookMarkAdapter);
        return view;
    }
}