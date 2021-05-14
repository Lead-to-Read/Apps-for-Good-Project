package com.example.appsforgood;

import android.app.Application;
import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * This class manages the ArrayList of Book objects from Firebase.
 */
public class Manager extends Application {

    //Instance Variables
    private ArrayList<Book> books = new ArrayList<>();

    public void onCreate() {
        super.onCreate();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("books");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again whenever data at this location is updated.
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Book current = ds.getValue(Book.class);
                    addBook(current);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                if (InitialSurvey.debug)
                    Log.v("MainActivity", "Failed to read value.", error.toException());
            }
        });
    }


    /**
     * Gets ArrayList of books
     * @return ArrayList of books
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * Adds a book to the ArrayList
     * @param book contains the book to be added to the ArrayList books
     */
    public void addBook(Book book) {
        books.add(book);
    }

}
