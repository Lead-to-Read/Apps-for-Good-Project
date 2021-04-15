package com.example.appsforgood;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {

    private Book b;
    private ArrayList<Book> correctLangBooks;
    private ArrayList<Integer> bookScores;

    @Override
    /**
     * Open survey at proper time
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);
        final Manager manager = (Manager) getApplicationContext();
        EditText langText = findViewById(R.id.langEditText);
        String lang = langText.getText().toString();
        Log.v("Lang","lang: " + lang);
        for (Book book : manager.getBooks()) {
            if (book.getLanguage().equalsIgnoreCase(lang))
                correctLangBooks.add(book);
        }
        for (Book b : correctLangBooks) {
            Log.v("Final", b.getLanguage());
        }
    }

    /**public void langFilter () {
        EditText langText = findViewById(R.id.langEditText);
        String lang = langText.getText().toString();
        for (Book book : manager.getBooks()) {
            if (book.getLanguage().equalsIgnoreCase(lang))
                correctLangBooks.add(book);
        }
        for (Book b : correctLangBooks) {
            Log.v("Final: ", b.getLanguage());
        }**/
    }

