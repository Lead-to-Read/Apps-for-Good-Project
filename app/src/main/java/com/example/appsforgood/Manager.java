package com.example.appsforgood;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Manager extends Application {

    public void onCreate() {
        Log.v("doesStart", "hasStarted");
        super.onCreate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("books");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Book current = ds.getValue(Book.class);
                    addBook(current);
                    //Log.e("FirebaseActivity", "Title: " + current.getTitle() + "Length: " + current.getNumPages());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });
    }
    private ArrayList<Book> books = new ArrayList<Book>();

    /**
     * Gets arrayList of books
     * @return arrayList of books
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Adds a book to the arrayList
     * @param book contains the book to be added to the arraylist books
     */
    public void addBook(Book book) {
        books.add(book);
    }

}
