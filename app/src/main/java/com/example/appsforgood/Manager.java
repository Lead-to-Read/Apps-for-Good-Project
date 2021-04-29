package com.example.appsforgood;

import android.app.Application;

import java.util.ArrayList;

public class Manager extends Application {

    private static ArrayList<Book> books = new ArrayList<Book>();
    private static ArrayList<BookScores> bookScores;

    /**
     * Gets arrayList of books
     * @return arrayList of books
     */
    public static ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Adds a book to the arrayList
     * @param book contains the book to be added to the arraylist books
     */
    public void addBook(Book book) {
        books.add(book);
    }

    public void addBookScore(Book initBook, double initScore) {
        BookScores bookScoreNew = new BookScores(initBook, initScore);
        bookScores.add(bookScoreNew);
    }
}
