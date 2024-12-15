package com.example.android.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.MODEL.Book_model;
import com.example.android.R;

import java.util.ArrayList;

public class BookDatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bookStore.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_BOOK = "book";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_ID_CATEGORY = "id_category";
    private static final String COLUMN_STAR = "star";
    private static final String COLUMN_SUMMARY = "summary";
    private static final String COLUMN_IMAGE = "image";

    public BookDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_PRICE + " INTEGER, "
                + COLUMN_QUANTITY + " INTEGER, "
                + COLUMN_ID_CATEGORY + " INTEGER, "
                + COLUMN_STAR + " INTEGER, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_AUTHOR + " TEXT, "
                + COLUMN_SUMMARY + " TEXT, "
                + COLUMN_IMAGE + " INTEGER)";
        db.execSQL(CREATE_BOOK_TABLE);

        // Thêm dữ liệu mẫu vào cơ sở dữ liệu
        addSampleBooks(db);
    }

    private void addSampleBooks(SQLiteDatabase db) {
        // Thêm dữ liệu mẫu
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "Số Đỏ");
        values.put(COLUMN_AUTHOR, "Vũ Trọng Phụng");
        values.put(COLUMN_PRICE, 100000);
        values.put(COLUMN_QUANTITY, 200);
        values.put(COLUMN_ID_CATEGORY, 1);  // Ví dụ: Tất Cả
        values.put(COLUMN_STAR, 4);
        values.put(COLUMN_SUMMARY, "Sách hay");
        values.put(COLUMN_IMAGE, R.drawable.so_do);
        db.insert(TABLE_BOOK, null, values);

        values.clear();
        values.put(COLUMN_NAME, "Atomic Habit");
        values.put(COLUMN_AUTHOR, "James Clear");
        values.put(COLUMN_PRICE, 120000);
        values.put(COLUMN_QUANTITY, 150);
        values.put(COLUMN_ID_CATEGORY, 2);  // Ví dụ: Ebook
        values.put(COLUMN_STAR, 5);
        values.put(COLUMN_SUMMARY, "Sách hay về thói quen");
        values.put(COLUMN_IMAGE, R.drawable.automic);
        db.insert(TABLE_BOOK, null, values);

        values.clear();
        values.put(COLUMN_NAME, "Harry Potter 2");
        values.put(COLUMN_AUTHOR, "J.K. Rowling");
        values.put(COLUMN_PRICE, 200000);
        values.put(COLUMN_QUANTITY, 100);
        values.put(COLUMN_ID_CATEGORY, 3);  // Ví dụ: Giảm Giá
        values.put(COLUMN_STAR, 5);
        values.put(COLUMN_SUMMARY, "Sách kỳ ảo nổi tiếng");
        values.put(COLUMN_IMAGE, R.drawable.harry_potter_);
        db.insert(TABLE_BOOK, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        onCreate(db);
    }

    // Phương thức để lấy tất cả sách từ cơ sở dữ liệu
    public ArrayList<Book_model> getAllBooks() {
        ArrayList<Book_model> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOK, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex(COLUMN_AUTHOR));
                @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));
                @SuppressLint("Range") int quantity = cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY));
                @SuppressLint("Range") int idCategory = cursor.getInt(cursor.getColumnIndex(COLUMN_ID_CATEGORY));
                @SuppressLint("Range") int star = cursor.getInt(cursor.getColumnIndex(COLUMN_STAR));
                @SuppressLint("Range") String summary = cursor.getString(cursor.getColumnIndex(COLUMN_SUMMARY));
                @SuppressLint("Range") int image = cursor.getInt(cursor.getColumnIndex(COLUMN_IMAGE));

                Book_model book = new Book_model(id, price, quantity, idCategory, star, name, author, summary, image);
                books.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return books;
    }
}
