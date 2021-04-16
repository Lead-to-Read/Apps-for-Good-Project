package com.example.appsforgood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import java.util.ArrayList;

public class initialSurvey extends AppCompatActivity {

    //private Book b;
    private ArrayList<Book> correctLangBooks = new ArrayList<Book>();
    private ArrayList<Integer> bookScores = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_survey);
    }
    public void onContinueClick (View v) {
        EditText langText = findViewById(R.id.langEditText);
        String lang = langText.getText().toString();
        Log.v("Lang", "User Language: " + lang);

        for (Book b: Manager.getBooks()) {
            Log.v("Book", "All Book Titles: " + b.getTitle());
            if (b.getLanguage().equalsIgnoreCase(lang)) {
                correctLangBooks.add(b);
                Log.v("Book", "Book w/ Correct Language: " + b.getTitle());
            }
        }

        for (Book b: correctLangBooks) {
            Log.v("Book","Book in correctLangBooks" + b.getTitle());
        }
        Intent start = new Intent(this, DisplayBooks.class);
        startActivity(start);
    }
}

