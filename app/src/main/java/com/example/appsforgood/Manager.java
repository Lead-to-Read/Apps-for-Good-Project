package com.example.appsforgood;

import android.app.Application;

import java.util.ArrayList;

public class Manager extends Application {

    ArrayList<Book> books = new ArrayList<Book>();

    /**
     * Returns arraylist
     * @return
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Adds a book to the arraylist
     * @param book
     */
    public void addBook(Book book) {
        books.add(book);
    }
}
