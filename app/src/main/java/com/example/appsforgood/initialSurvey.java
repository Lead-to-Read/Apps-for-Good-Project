package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;

import java.util.ArrayList;

public class initialSurvey extends AppCompatActivity {

    //private Book b;
    private ArrayList<Book> correctLangBooks = new ArrayList<Book>();
    private ArrayList<Integer> bookScores = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);
        performDisplaySuggestions();

    }
    public void performDisplaySuggestions() {
        Log.v("Hi", "Hello");
        Intent start = new Intent(this, DisplayBooks.class);
        startActivity(start);
        //Log.v("Bye", "Bello");
    }

    public void langFilter(View v) {
        Log.v("Bye", "Bello");
        final Manager manager = (Manager) getApplicationContext();
        EditText langText = findViewById(R.id.langEditText);
        String lang = langText.getText().toString();
        Log.v("Lang","lang: " + lang);
        for (Book book : manager.getBooks()) {
            if (book.getLanguage().equalsIgnoreCase(lang))
                correctLangBooks.add(book);
        }
        for (Book entry : correctLangBooks) {
            Log.v("Final", entry.getLanguage());
        }
    }




}