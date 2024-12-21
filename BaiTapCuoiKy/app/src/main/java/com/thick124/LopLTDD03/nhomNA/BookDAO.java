package com.thick124.LopLTDD03.nhomNA;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thick124.LopLTDD03.nhomNA.Model.Book_Model;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private SQLiteDatabase database;
    private BookDatabaseHelper dbHelper;

    public BookDAO(Context context) {
        dbHelper = new BookDatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Thêm sách
    public void addBook(Book_Model book) {
        ContentValues values = new ContentValues();
        values.put(BookDatabaseHelper.COLUMN_TITLE, book.getTitle());
        values.put(BookDatabaseHelper.COLUMN_AUTHOR, book.getAuthor());
        values.put(BookDatabaseHelper.COLUMN_IMAGE, book.getImageResId());
        database.insert(BookDatabaseHelper.TABLE_BOOKS, null, values);
    }

    // Lấy tất cả sách
    public List<Book_Model> getAllBooks() {
        List<Book_Model> books = new ArrayList<>();
        Cursor cursor = database.query(BookDatabaseHelper.TABLE_BOOKS, null,
                null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String author = cursor.getString(2);
            int image = cursor.getInt(3);
            books.add(new Book_Model(id, title, author, image));
        }
        cursor.close();
        return books;
    }
}
