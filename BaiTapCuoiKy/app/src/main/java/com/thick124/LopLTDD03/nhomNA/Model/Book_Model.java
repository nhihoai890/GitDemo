package com.thick124.LopLTDD03.nhomNA.Model;

public class Book_Model {
    private int id;
    private String title;
    private String author;
    private int imageResId;

    public Book_Model(int id, String title, String author, int imageResId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.imageResId = imageResId;
    }

    public Book_Model(String title, String author, int imageResId) {
        this.title = title;
        this.author = author;
        this.imageResId = imageResId;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getImageResId() { return imageResId; }
}
