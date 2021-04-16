package com.example.appsforgood;

import android.app.Application;

import java.util.ArrayList;

public class Manager extends Application {

    static ArrayList<Book> books = new ArrayList<Book>();

    /**
     * Gets arrayList of books
     * @return arrayList of books
     */
    public static ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Adds a book to the arrayList
     * @param book contains the book to be added
     */
    public void addBook(Book book) {
        books.add(book);
    }
}
