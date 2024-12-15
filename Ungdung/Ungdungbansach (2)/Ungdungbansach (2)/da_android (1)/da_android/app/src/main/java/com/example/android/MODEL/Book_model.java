package com.example.android.MODEL;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;

public class Book_model {
    int id, price, quantity, id_category,star, image;
    String name, author, summary;

    // Constructor không tham số cần thiết cho Firestore

    public Book_model(int id, int price, int quantity, int id_category, int star, String name, String author, String summary, int image) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.id_category = id_category;
        this.star = star;
        this.name = name;
        this.author = author;
        this.summary = summary;
        this.image = image;
    }

    public Book_model() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    // Phương thức chuyển đổi Book_model thành ContentValues để chèn vào cơ sở dữ liệu
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put("id", this.id);
        values.put("price", this.price);
        values.put("quantity", this.quantity);
        values.put("id_category", this.id_category);
        values.put("star", this.star);
        values.put("name", this.name);
        values.put("author", this.author);
        values.put("summary", this.summary);
        values.put("image", this.image);
        return values;
    }

    // Phương thức để khôi phục dữ liệu từ Cursor
    @SuppressLint("Range")
    public static Book_model fromCursor(Cursor cursor) {
        Book_model book = new Book_model();
        book.setId(cursor.getInt(cursor.getColumnIndex("id")));
        book.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
        book.setQuantity(cursor.getInt(cursor.getColumnIndex("quantity")));
        book.setId_category(cursor.getInt(cursor.getColumnIndex("id_category")));
        book.setStar(cursor.getInt(cursor.getColumnIndex("star")));
        book.setName(cursor.getString(cursor.getColumnIndex("name")));
        book.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
        book.setSummary(cursor.getString(cursor.getColumnIndex("summary")));
        book.setImage(cursor.getInt(cursor.getColumnIndex("image")));
        return book;
    }
}
