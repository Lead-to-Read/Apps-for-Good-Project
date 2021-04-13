package com.example.appsforgood;

import android.app.Application;

import java.util.ArrayList;

public class Manager extends Application {

    ArrayList<Book> books = new ArrayList<Book>();

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
}
